package eu.lapecera.jolastoki.games.dragndrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import eu.lapecera.jolastoki.R;
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
	
	@Override
	protected void onCreateView(GameViewConfig config) {
		this.setOnDragListener(this);
		gameOver = false;
		dragStarted = false;
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_LOCATION:
			if (!v.equals(this)) {
				return false;
			}
			droppedX = event.getX();
			droppedY = event.getY();
			Log.i("Current location", "x: " + droppedX + ", y: " + droppedY);
			break;
		case DragEvent.ACTION_DROP:
			onDropView(v);
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			Log.i("DragEvent", "Drag exited " + event.getAction());
			if (v.equals(this))
				moveBack(droppedX, draggingView.getX(), droppedY, draggingView.getY());
			break;


		}
		return true;
	}
	
	protected abstract void onDropView (View view);

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (dragStarted) {
				moveBack(droppedX, draggingView.getX(), droppedY, draggingView.getY());
			}
			return true;
		case MotionEvent.ACTION_DOWN:
			if (!gameOver) {
				draggingView = view;
				
				droppedX = draggingView.getX() + draggingView.getWidth()/2;
				droppedY = draggingView.getY() + draggingView.getWidth()/2;
				
				MusicManager.playSingle(getContext(), R.raw.seleccion);

				ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());

				String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
				ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
				DragRotatedShadowBuilder shadowBuilder = new DragRotatedShadowBuilder(view);

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

	protected void moveBack(float _xFrom, float xTo, float _yFrom, float yTo) {
		float xFrom = _xFrom - draggingView.getWidth() / 2;
		float yFrom = _yFrom  - draggingView.getHeight() / 2;
		draggingView.setX(xFrom);
		draggingView.setY(yFrom);
		draggingView.setVisibility(View.VISIBLE);
		dragStarted = false;
		draggingView.animate().translationXBy(xTo - xFrom).translationYBy(yTo - yFrom);
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


}
