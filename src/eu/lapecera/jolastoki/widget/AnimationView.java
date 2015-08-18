package eu.lapecera.jolastoki.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import eu.lapecera.jolastoki.R;

public class AnimationView extends ImageView {
	
	//TODO añadir los drawables de la animación y el delay desde el xml para hacer un componente genérico
	
	public interface OnAnimationCompleteListener {
		public void onAnimationComplete ();
	}

	private OnAnimationCompleteListener listener;
	
	private int[] frames = new int[]{
			R.drawable.estrellas0001,
			R.drawable.estrellas0002,
			R.drawable.estrellas0003,
			R.drawable.estrellas0004,
			R.drawable.estrellas0005,
			R.drawable.estrellas0006,
			R.drawable.estrellas0007,
			R.drawable.estrellas0008,
			R.drawable.estrellas0009,
			R.drawable.estrellas0010,
			R.drawable.estrellas0012,
			R.drawable.estrellas0013,
			R.drawable.estrellas0014,
			R.drawable.estrellas0015,
			R.drawable.estrellas0016,
			R.drawable.estrellas0017,
			R.drawable.estrellas0018,
			R.drawable.estrellas0019,
			R.drawable.estrellas0020,
			R.drawable.estrellas0021,
			R.drawable.estrellas0022,
			R.drawable.estrellas0023,
			R.drawable.estrellas0024,
			R.drawable.estrellas0025,
			R.drawable.estrellas0026,
			R.drawable.estrellas0027,
			R.drawable.estrellas0028,
			R.drawable.estrellas0029,
			R.drawable.estrellas0030,
			R.drawable.estrellas0031,
			R.drawable.estrellas0032,
			R.drawable.estrellas0033,
			R.drawable.estrellas0034};

	private Handler mHandler = new Handler();
	private Runnable invalidateRunnable = new Runnable() {

		@Override
		public void run() {
			AnimationView.this.invalidate();
		}
	};


    private Bitmap mBitmap = null;
    private BitmapFactory.Options mBitmapOptions;
	
	private int imageIndex = 0;

	public AnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setImageResource(frames[0]);

        // use in place bitmap to save GC work (when animation images are the same size & type)
        if (Build.VERSION.SDK_INT >= 11) {
            Bitmap bmp = ((BitmapDrawable) this.getDrawable()).getBitmap();
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            Bitmap.Config config = bmp.getConfig();
            mBitmap = Bitmap.createBitmap(width, height, config);
            mBitmapOptions = new BitmapFactory.Options();
            // setup bitmap reuse options. 
            mBitmapOptions.inBitmap = mBitmap;
            mBitmapOptions.inMutable = true;
            mBitmapOptions.inSampleSize = 1;
        }
		
		mHandler.postDelayed(invalidateRunnable, 30);
	}
	
	private int getNext() {
        imageIndex++;
        if (imageIndex >= frames.length) {
        	return -1;
        }
        return frames[imageIndex];
    }

	@Override
	protected void onDraw(Canvas canvas) {
		int imageRes = getNext();
		if (imageRes == -1) {
			if (listener != null) {
				listener.onAnimationComplete();
			}
			return;
		}
		
		if (mBitmap != null) { // so Build.VERSION.SDK_INT >= 11
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeResource(this.getResources(), imageRes, mBitmapOptions);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                this.setImageBitmap(bitmap);
            } else {
                this.setImageResource(imageRes);
                mBitmap.recycle();
                mBitmap = null;
            }
        } else {
            this.setImageResource(imageRes);
        }
		mHandler.postDelayed(invalidateRunnable, 30);
		
		
		super.onDraw(canvas);
	}
	
	public void setOnAnimationCompleteListener (OnAnimationCompleteListener listener) {
		this.listener = listener;
	}


}
