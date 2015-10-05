package eu.lapecera.jolastoki.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import eu.lapecera.jolastoki.R;

public class MultiLayerView extends View {

	private static final String TAG = "MultiLayerView";

	private List<File> mFiles;
	private Map<Integer, File> mFilters;
	private int[][] mMatrixRegions;
	private Bitmap mBaseBitmap;
	private Bitmap mLastBitmap;
	private Drawable firstDrawableResource;
	private Drawable lastDrawableResource;
	private int mLeft;
	private int mTop;
	private OnRegionClickListener mRegionListener;
	private OnCompletedColorsListener mCompletionListener;

	private Paint mPaint;

	public interface OnRegionClickListener {
		public void onRegionClick (int region);
	}

	public interface OnCompletedColorsListener {
		public void onCompletedColor ();
	}

	public MultiLayerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mFilters = new HashMap<Integer, File>();
		TypedArray customAttrs = context.obtainStyledAttributes(attrs, R.styleable.InteractiveView);
		String assetsDir = customAttrs.getString(R.styleable.InteractiveView_assets_dir);
		firstDrawableResource = customAttrs.getDrawable(R.styleable.InteractiveView_first_layer);
		lastDrawableResource = customAttrs.getDrawable(R.styleable.InteractiveView_last_layer);
		File dir = new File(getContext().getExternalCacheDir(), assetsDir);
		this.copyFromAssets(dir, assetsDir);
		this.setRegionFiles(Arrays.asList(dir.listFiles()));
		customAttrs.recycle();
		initialize();
	}

	public Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
		Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(mutableBitmap);
		drawable.setBounds(0, 0, widthPixels, heightPixels);
		drawable.draw(canvas);

		return mutableBitmap;
	}

	public MultiLayerView(Context context) {
		super(context);
	}


	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mBaseBitmap == null) {
			drawBase();
		}
		if (mMatrixRegions == null)
			setRegionFiles(mFiles);
	}

	private void initialize() {
		mPaint = new Paint();
		mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if ( mBaseBitmap != null ) {
			canvas.drawBitmap(mBaseBitmap, 0, 0, mPaint);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {

			int i = (int) x - mLeft;
			int j = (int) y - mTop;

			try {
				int region = mMatrixRegions[j][i];
				if (mRegionListener != null)
					mRegionListener.onRegionClick(region);
			}
			catch (Exception e) {}
		}
		return super.dispatchTouchEvent(event);
	}

	public void addFilter (int region) {
		if (!mFilters.containsKey(region)) {
			mFilters.put(region, mFiles.get(region));
			applyFilter(region);
			this.invalidate();
		}
		if (mFilters.size() == mFiles.size() && mCompletionListener != null) {
			mCompletionListener.onCompletedColor();
		}
	}
	
	private Bitmap mLayerBitmap;

	private void applyFilter (int region) {
		Canvas canvas = null;
		if (mBaseBitmap == null) {
			canvas = drawBase();
		} else {
			canvas = new Canvas();
			canvas.setBitmap(mBaseBitmap);
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;

		File layer = mFiles.get(region);

		Bitmap layerTemp = BitmapFactory.decodeFile(layer.getAbsolutePath(), options);
		mLayerBitmap = Bitmap.createScaledBitmap(layerTemp, getWidth(), getHeight(), false);
		layerTemp.recycle();
		canvas.drawBitmap(mLayerBitmap, 0, 0, mPaint);
		mLayerBitmap.recycle();
		canvas.drawBitmap(mLastBitmap, 0, 0, mPaint);
	}

	private void loadLastLayer () {
		mLastBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		mLastBitmap = convertToBitmap(lastDrawableResource, getWidth(), getHeight());
	}

	private Canvas drawBase () {
		mBaseBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		mBaseBitmap = convertToBitmap(firstDrawableResource, getWidth(), getHeight());
		Canvas canvas = new Canvas();
		canvas.setBitmap(mBaseBitmap);
		canvas.drawBitmap(mBaseBitmap, 0, 0, mPaint);
		loadLastLayer();
		canvas.drawBitmap(mLastBitmap, 0, 0, mPaint);
		return canvas;
	}

	public void setOnRegionClickListener (OnRegionClickListener listener) {
		this.mRegionListener = listener;
	}

	public void setOnCompletedColorListener (OnCompletedColorsListener listener) {
		this.mCompletionListener = listener;
	}

	/**
	 * Free the native object associated with the object' bitmap, and clear the
	 * reference to the pixel data. This will not free the pixel data synchronously;
	 * it simply allows it to be garbage collected if there are no other references.
	 * The view will not be touchable.
	 */
	public void recycle() {
		if ( mBaseBitmap != null ) {
			mBaseBitmap.recycle();
			mBaseBitmap = null;
		}
		if ( mLastBitmap != null ) {
			mLastBitmap.recycle();
			mLastBitmap = null;
		}
		if (mLayerBitmap != null) {
			mLayerBitmap.recycle();
			mLayerBitmap = null;
		}
	}

	private void setRegionFiles(List<File> files) {
		this.mFiles = files;
		post(new Runnable() {

			@Override
			public void run() {
				// Load touch region
				new Thread(new Runnable() {

					@Override
					public void run() {
						if (mBaseBitmap == null) return;
						Bitmap main = mBaseBitmap;
						mMatrixRegions = new int[main.getHeight()][main.getWidth()];

						for (int index = 0; index < mFiles.size(); index++) {
							Bitmap bmp = loadBitmap(mFiles.get(index));

							IntBuffer buffer = IntBuffer.allocate(bmp.getWidth() * bmp.getHeight());
							bmp.copyPixelsToBuffer(buffer);
							buffer.rewind();

							while (buffer.position() < buffer.limit()) {
								int pixel = buffer.get();
								if (pixel != 0) {
									int i = buffer.position() / bmp.getWidth();
									int j = buffer.position() % bmp.getWidth();

									try {
										mMatrixRegions[i][j] = index;
									} catch (Exception e) {
										Log.e(TAG,
												"Position (" + i + "," + j + "), buffer="
														+ bmp.getWidth() * bmp.getHeight() + ", matrix="
														+ mMatrixRegions.length + ", " + mMatrixRegions[0].length,
														e);
									}
								}
							}

							// Recycle
							bmp.recycle();
						}
					}

				}).start();
			}
		});

	}

	private Bitmap loadBitmap(File file) {
		Log.d(TAG, "[TRACE] loadBitmap() with=" + getWidth() + ", heigth=" + getHeight());
		Bitmap src = BitmapFactory.decodeFile(file.getAbsolutePath());

		// The available space is greather or equal that the imgage
		if (src.getWidth() <= getWidth() && src.getHeight() <= getHeight()) {
			// Scale up
			Bitmap newBitmap = Bitmap.createScaledBitmap(src, getWidth(), getHeight(), false);
			src.recycle();
			return newBitmap;

		}
		// Reduce image proporcionally to fit with the available space
		else {
			float maxImageSize = Math.min(getWidth(), getHeight());
			float ratio = Math.max((float) maxImageSize / src.getWidth(), (float) maxImageSize / src.getHeight());
			int width = Math.round((float) ratio * src.getWidth());
			int height = Math.round((float) ratio * src.getHeight());

			Bitmap newBitmap = Bitmap.createScaledBitmap(src, width, height, false);
			src.recycle();
			return newBitmap;
		}
	}

	private void copyFromAssets(File dir, String dirName) {
		if (dir.exists())
			return;

		// Create dir
		dir.mkdirs();

		// Copy files from assets
		try {
			String[] names = getContext().getAssets().list(dirName);
			for (String name : names) {
				InputStream is = getContext().getAssets().open(dirName + File.separator + name);

				File outFile = new File(dir, name);
				FileOutputStream fos = new FileOutputStream(outFile);

				byte[] buffer = new byte[1024];
				int read;
				while ((read = is.read(buffer)) != -1) {
					fos.write(buffer, 0, read);
				}

				is.close();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
