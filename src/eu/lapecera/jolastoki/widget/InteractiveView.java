package eu.lapecera.jolastoki.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.IntBuffer;
import java.util.Arrays;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import eu.lapecera.jolastoki.R;

/**
 * Interactive picture view.
 */
public class InteractiveView extends View {
	
	public interface OnCompletedColorsListener {
		public void onCompletedColor ();
	}
	
	private static final String TAG = "EditorView";
	
	private Paint mPaint;
	private Bitmap mBitmap = null;
	private File[] mFiles;
	private SparseIntArray mFilters = new SparseIntArray();
	private int[][] mMatrixRegion;
	private int mColor = 0;
	private int mEnabledRegion = -1;
	private int mLeft;
	private int mTop;
	private boolean mIsBusy = false;
	private boolean mIsScalable = true;
	
	private OnCompletedColorsListener listener;

	
	public InteractiveView(Context context) {
		super(context);
		initialize();
	}
	
	public InteractiveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initialize();

		TypedArray customAttrs = context.obtainStyledAttributes(attrs, R.styleable.InteractiveView);
		String assetsDir = customAttrs.getString(R.styleable.InteractiveView_assets_dir);
		Log.i("IntegeractiveView", "Dir: " + assetsDir);
		File dir = new File( getContext().getExternalCacheDir(), assetsDir);
		this.copyFromAssets(dir, assetsDir);
		File[] files = dir.listFiles();
		this.setRegionFiles(files);
	}
	
	private void initialize() {
		mPaint = new Paint();
		mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        Log.d(TAG, "[TRACE] onMeasure() width=" + widthSize + ", heigth=" + heightSize + ", widthMode=" +widthMode + ", heightMode=" + heightMode);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if ( mBitmap != null ) {
			// Center it
			float left = ( canvas.getWidth()  - mBitmap.getWidth()	) / 2;
			float top  = ( canvas.getHeight() - mBitmap.getHeight()	) / 2;
			canvas.drawBitmap(mBitmap, left, top, mPaint);
			mTop = (int) top;
			mLeft = (int) left;
			mIsBusy = false;
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mMatrixRegion == null)
			setRegionFiles(mFiles);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if ( mEnabledRegion == -1 ) return true;
		
		float x = event.getX();
		float y = event.getY();
		
		Log.d(TAG, "" + x + "," + y );
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			
			int i = (int) x - mLeft;
			int j = (int) y - mTop;
			
			try {
				int region = mMatrixRegion[j][i];
				Log.d(TAG, "REGION = " + region + ", Enabled region: " + mEnabledRegion);
				
				if ( region == mEnabledRegion ) {
					addFilter(region, mColor);
				}
			}
			catch (Exception e) {}
		}
		return super.dispatchTouchEvent(event);
	}
	
	public void setOnCompletedColorListener (OnCompletedColorsListener listener) {
		this.listener = listener;
	}
	
    /**
     * Free the native object associated with the object' bitmap, and clear the
     * reference to the pixel data. This will not free the pixel data synchronously;
     * it simply allows it to be garbage collected if there are no other references.
     * The view will not be touchable.
     */
	public void recycle() {
		if ( mBitmap != null ) {
			mBitmap.recycle();
			mBitmap = null;
		}
	}
	
	/**
	 * Set the image as scalable.
	 */
	public void setScalable() {
		mIsScalable = true;
	}
	
	/**
	 * @return True if is scalable, false otherwise.
	 */
	public boolean isScalable() {
		return mIsScalable;
	}
	
	/**
	 * Set the color to draw.
	 * @param color The color ARGB.
	 */
	public void setColorSelection(int color) {
		mColor = color;
	}
	
	/**
	 * @return The selection color to draw, 0 if none is selected.
	 */
	public int getColorSelection() {
		return mColor;
	}
	
	/**
	 * Set the region that can be draw. -1 to disable all.
	 * @param region The region id.
	 */
	public void setEnabledRegion(int region) {
		mEnabledRegion = region;
	}
	
	/**
	 * Set the enabled region and selection color.
	 * @param region The region id.
	 * @param color The selection color.
	 * @see #setEnabledRegion(int)
	 * @see #setColorSelection(int)
	 */
	public void setRegionAndColor(int region, int color) {
		setEnabledRegion(region);
		setColorSelection(color);
	}
	
	/**
	 * Load the empty picture.
	 */
	private void load() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				mBitmap = loadBitmap(0);
				mHandler.sendEmptyMessage(0);
			}
		}).start();
	}
	
	/**
	 * Clear view.
	 */
	public void clear() {
		recycle();
		mFilters.clear();
		load();
	}
	
	/**
	 * @return The list ordered of all ids exclude the special '0'.
	 */
	public int[] getRegionIds() {
		int[] ids = new int[mFiles.length-1];
		int j = 0;
		for (int i = 0; i < mFiles.length; i ++) {
			int id = getNumber( mFiles[i].getName() );
			if ( id != 0 ) {
				ids[j] = id;
				j ++;
			}
		}
		Arrays.sort(ids);
		return ids;
	}
	
	/**
	 * @return True if it is completed, false otherwise.
	 */
	public boolean isCompleted() {
		return getRegionIds().length == mFilters.size();
	}
	
	/**
	 * @return  The filters applied to the view. The 'key' is the region id
	 * 			and the 'value' is the color.
	 */
	public SparseIntArray getFilters() {
		return mFilters;
	}
	
	/**
	 * Add to current state a filter.
	 * @param id The id of the region.
	 * @param color The color to apply.
	 */
	public void addFilter(final int id, final int color) {
		if ( mIsBusy ) {
			return;
		}
		
		
		mFilters.put(id, color);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				mIsBusy = true;
				mBitmap = process(mBitmap, loadBitmap(id), color);
				mHandler.sendEmptyMessage(0);
			}
			
		}).start();
	}
	
	/**
	 * Remove the filer.
	 * @param id The id of the region.
	 */
	public void removeFilter(final int id) {
		mFilters.removeAt(id);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				mBitmap = process(mBitmap, loadBitmap(id), 0);
				mHandler.sendEmptyMessage(0);
			}
		}).start();
	}
	
	/**
	 * Store the view in a PNG.
	 * @param file The path of the PNG.
	 */
	public void store(File file) {
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		}
		catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
	
	/**
	 * Set the files of the regions and load the empty picture.
	 * @param files Files list.
	 */
	public void setRegionFiles(File[] files) {
		mFiles = files;
		if (getWidth() > 0 && getHeight() > 0)
		post(new Runnable() {
			
			@Override
			public void run() {
				// Load touch region
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						mIsBusy = true;
						
						// Load empty picture like load()
						mBitmap = loadBitmap(0);
						mHandler.sendEmptyMessage(1);	// Not set mIsbusy = true
						
						//TODO añadir ultima capa aquí con el método loadBitmap.
						
						// Calc regions
						Bitmap main = mBitmap;
						mMatrixRegion = new int[main.getHeight()][main.getWidth()];
						
						for ( int id : getRegionIds() ) {
							Bitmap bmp = loadBitmap(id);
							
						    IntBuffer buffer = IntBuffer.allocate(bmp.getWidth() * bmp.getHeight());
						    bmp.copyPixelsToBuffer(buffer);
						    buffer.rewind();
						    
						    while (buffer.position() < buffer.limit()) {
						    	int pixel = buffer.get();
						    	if ( pixel != 0 ) {
						    		int i = buffer.position() / bmp.getWidth();
						    		int j = buffer.position() % bmp.getWidth();
						    		
						    		try {
						    			mMatrixRegion[i][j] = id;
						    		}
						    		catch (Exception e) {
						    			Log.e(TAG, 
						    				"Position (" + i +"," + j + "), buffer=" + bmp.getWidth()*bmp.getHeight() +	", matrix=" + mMatrixRegion.length + ", " + mMatrixRegion[0].length,
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
	
	private Bitmap loadBitmap(int id) {
		for ( File i : mFiles ) {
			int pos = getNumber( i.getName() );
			
			// Match id
			if ( pos == id ) {
				Log.d(TAG, "[TRACE] loadBitmap() with=" + getWidth() + ", heigth=" + getHeight());
				Bitmap src = BitmapFactory.decodeFile( i.getAbsolutePath());
				
				// The available space is greather or equal that the imgage
				if ( src.getWidth() <= getWidth() && src.getHeight() <= getHeight() ) {
					// Scale up
					if ( mIsScalable ) {
						Bitmap newBitmap = Bitmap.createScaledBitmap(src, getWidth(), getHeight(), false);
					    return newBitmap;
					}
					
					return src;
				}
				// Reduce image proporcionally to fit with the available space
				else {
					float maxImageSize = Math.min(getWidth(), getHeight());
					float ratio = Math.max(
				            (float) maxImageSize / src.getWidth(),
				            (float) maxImageSize / src.getHeight());
				    int width  = Math.round((float) ratio * src.getWidth());
				    int height = Math.round((float) ratio * src.getHeight());

				    Bitmap newBitmap = Bitmap.createScaledBitmap(src, width, height, false);
				    return newBitmap;
				}
			}
		}
		return null;
	}
	
	private int getNumber(String name) {
		name = name.split("-")[1];
		name = name.substring(0, name.length()-4);
		return Integer.parseInt(name);
	}
	
	//-------------------------------------------------------------------------
	// The magic
	
	/**
	 * Process to generate the bitmap.
	 * @param output The based bitmap to add a colored region.
	 * @param region The bitmap region to apply color.
	 * @param color The color to apply.
	 * @return The result bitmap.
	 */
	private Bitmap process(Bitmap output, Bitmap region, int color) {
		if ( color == 0 ) return output;
		
		IntBuffer bufferOutput = IntBuffer.allocate(output.getWidth() * output.getHeight());
	    output.copyPixelsToBuffer(bufferOutput);
	    bufferOutput.rewind();

	    IntBuffer bufferRegion = IntBuffer.allocate(output.getWidth() * output.getHeight());
	    region.copyPixelsToBuffer(bufferRegion);
	    bufferRegion.rewind();
	    
	    IntBuffer bufferOut = IntBuffer.allocate(output.getWidth() * output.getHeight());
	    bufferOut.rewind();

	    while (bufferOut.position() < bufferOut.limit()) {
	    	int pixelRegion = bufferRegion.get();
	    	int pixelOutput = bufferOutput.get();
	        
	    	int pixel = pixelOutput;
	    	if ( Color.alpha(pixelRegion) > 0 ) {
	    		// Set the pixel with the color
	    		Log.i("process", "pixel alpha: " + Color.alpha(pixelRegion));
	    		Log.i("process", "pixel red: " + Color.red(pixelRegion));
	    		Log.i("process", "pixel green: " + Color.green(pixelRegion));
	    		Log.i("process", "pixel blue: " + Color.blue(pixelRegion));
	    		int alpha = multiply(Color.alpha(pixelRegion), Color.alpha(pixel));
	    		int red = multiply (Color.red(pixelRegion), Color.red(pixel));
	    		int blue = multiply (Color.blue(pixelRegion), Color.blue(pixel));
	    		int green = multiply (Color.green(pixelRegion), Color.green(pixel));

	    		pixel = Color.argb(Color.alpha(alpha), Color.red(red), Color.green(green), Color.blue(blue));
//	    		pixel = Color.argb(Color.alpha(pixelRegion), Color.red(color), Color.green(color), Color.blue(color));
	    		
	        	// Hack to swap the RED and BLUE chanels of ARGB to
	        	// preserve the REAL color.
	        	// WTF!
	    		// Friends -> http://stackoverflow.com/questions/19772558/bitmap-setpixels-distorts-colors-under-android
	    		
	        	pixel = ((pixel & 0xff00ff00)) | ((pixel & 0x000000ff) << 16) | ((pixel & 0x00ff0000) >> 16);
	    		Log.i("alpha", "parsed pixel alpha: " + Color.alpha(pixel));
	        }

	        bufferOut.put( pixel );
	    }

	    bufferOut.rewind();
	    output.copyPixelsFromBuffer(bufferOut);
	    
	    region.recycle();
	    
	    return output;
	}
	
	private int multiply(int in1, int in2) {
	    return in1 * in2 / 255;
	}
	
	//-------------------------------------------------------------------------
	// Handler
	private EditorHandler mHandler = new EditorHandler(this);
	private static class EditorHandler extends Handler {
		
		private final WeakReference<InteractiveView> mView;
		
		public EditorHandler(InteractiveView view) {
			mView = new WeakReference<InteractiveView>(view);
		}
		
		@Override
		public void handleMessage(Message msg) {
			mView.get().handleMessage(msg);
		}
	}
	
	/**
	 * Handle the messsages sent to the handler.
	 * @param msg The message to handle.
	 */
	protected void handleMessage(Message msg) {
		Log.d(TAG, "handleMessage()");
		invalidate();
		mIsBusy = msg.what != 0;
		if (mFilters.size() == mFiles.length - 1 && listener != null)
			listener.onCompletedColor();
	}
	
	private void copyFromAssets(File dir, String dirName) {
		if ( dir.exists() ) return; 
			
		// Create dir
		dir.mkdirs();
		
		// Copy files from assets
		try {
			String[] names = getContext().getAssets().list(dirName);
			for (String name : names ) {
			     InputStream is = getContext().getAssets().open(dirName + File.separator + name);

			     File outFile = new File(dir, name);
			     FileOutputStream fos = new FileOutputStream(outFile);
			     
			     byte[] buffer = new byte[1024];
			     int read;
			     while ( (read = is.read(buffer)) != -1 ) {
			    	 fos.write(buffer, 0, read);
			     }
			     
			     is.close();
			     fos.close();
			} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
