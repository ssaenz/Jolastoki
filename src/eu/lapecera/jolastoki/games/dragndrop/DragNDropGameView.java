package eu.lapecera.jolastoki.games.dragndrop;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.DragNDropGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.util.DragRotatedShadowBuilder;
import eu.lapecera.jolastoki.util.MusicManager;

public abstract class DragNDropGameView extends GameView implements OnTouchListener, OnDragListener {

	public DragNDropGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	private float droppedX;
	private float droppedY;
	private View draggingView;
	
	private boolean gameOver;
	
	private boolean dragStarted;
	
	private ImageView shadowImage;
	
	private int endTargetBackground;
	
	private boolean movingBack = false;
	private boolean dragDisabled = false;
	
	@Override
	protected void onCreateView(GameViewConfig config) {
		this.setOnDragListener(this);
		gameOver = false;
		dragStarted = false;
		this.endTargetBackground = ((DragNDropGameViewConfig) config).getEndTargetBackground();
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_LOCATION:
			if (!v.equals(this)) {
				return true;
			}
			droppedX = event.getX();
			droppedY = event.getY();
			break;
		case DragEvent.ACTION_DROP:
			onDropView(v);
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			if (v.equals(this)) {
				moveBack(droppedX, droppedY);
			}
			break;
		}
		return true;
	}
	
	protected void placeFigureOnTarget (View figure, View target) {
		
		if (target instanceof ImageView) {
			((ImageView) target).setImageDrawable(((ImageView)figure).getDrawable());
		} else if (target instanceof TextView || target instanceof Button) {
			((TextView)target).setText(((TextView)figure).getText(), TextView.BufferType.SPANNABLE);
		} 
		
		if (endTargetBackground != -1) {
			target.setBackgroundResource(endTargetBackground);
		} else {
			target.setBackgroundDrawable(figure.getBackground());
		}
		target.setVisibility(View.VISIBLE);
	}
	
	protected abstract void onDropView (View view);

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (dragStarted) {
				moveBack(droppedX, droppedY);
			}
			return true;
		case MotionEvent.ACTION_DOWN:
			if (!canDrag())
				return false;
			if (!gameOver) {
				draggingView = view;
				
				droppedX = getRelativeX(draggingView) + draggingView.getWidth()/2;
				droppedY = getRelativeY(draggingView) + draggingView.getWidth()/2;
				
				MusicManager.playSingle(getContext(), R.raw.seleccion);

				ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());

				String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
				ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
				DragRotatedShadowBuilder shadowBuilder = new DragRotatedShadowBuilder(view);
				
				Bitmap bitmap = Bitmap.createBitmap(draggingView.getWidth(), draggingView.getHeight(), Config.ARGB_8888);
				shadowBuilder.onDrawShadow(new Canvas(bitmap));
				
				if (shadowImage == null) { 
					shadowImage = new ImageView(getContext());
					this.addView(shadowImage, new LayoutParams(draggingView.getWidth(), draggingView.getHeight()));
				}
				shadowImage.setImageBitmap(bitmap);

				view.setVisibility(View.INVISIBLE);
				view.startDrag( data, //data to be dragged
						shadowBuilder, //drag shadow
						view, //local data about the drag and drop operation
						0   //no needed flags
						);

				dragStarted = true;
				return true;
			}
			break;
		}
		return false;
	}

	protected void moveBack(float _xFrom, float _yFrom) {
		movingBack = true;
		float xFrom = _xFrom - shadowImage.getWidth() / 2;
		float yFrom = _yFrom  - shadowImage.getHeight() / 2;
		float xTo = getRelativeX(draggingView);
		float yTo = getRelativeY(draggingView);
		shadowImage.setX(xFrom);
		shadowImage.setY(yFrom);
		shadowImage.setVisibility(View.VISIBLE);
		dragStarted = false;
		shadowImage.animate().translationXBy(xTo - xFrom).translationYBy(yTo - yFrom).setListener(new AnimatorListener() {
			
			@Override public void onAnimationStart(Animator animation) { }
			@Override public void onAnimationRepeat(Animator animation) { }
			@Override public void onAnimationCancel(Animator animation) { }
			
			@Override 
			public void onAnimationEnd(Animator animation) {
				shadowImage.setVisibility(View.INVISIBLE);
				draggingView.setVisibility(View.VISIBLE);
				movingBack = false;
			}
			
		});
	}
	
	protected View getDraggingView () {
		return this.draggingView;
	}
	
	protected float getDroppedX () {
		return this.droppedX;
	}
	
	protected float getDroppedY() {
		return this.droppedY;
	}
	
	protected void endGame () {
		if (getGameOverListener() != null) {
			gameOver = true;
			getGameOverListener().OnGameOver();
		}
	}
	
	protected synchronized void disableDrag() {
		dragDisabled = true;
	}
	
	protected synchronized void enableDrag () {
		dragDisabled = false;
	}
	
	private float getRelativeX(View myView) {
	    if (myView.getParent() == myView.getRootView())
	        return myView.getX();
	    else
	        return myView.getX() + getRelativeX((View) myView.getParent());
	}

	private float getRelativeY(View myView) {
	    if (myView.getParent() == myView.getRootView())
	        return myView.getY();
	    else
	        return myView.getY() + getRelativeY((View) myView.getParent());
	}
	
	private boolean canDrag () {
		return !movingBack && !dragDisabled;
	}


}
