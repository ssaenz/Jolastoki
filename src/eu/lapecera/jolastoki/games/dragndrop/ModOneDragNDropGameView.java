package eu.lapecera.jolastoki.games.dragndrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.ModOneDragNDropGameViewConfig;
import eu.lapecera.jolastoki.util.MusicManager;

public class ModOneDragNDropGameView extends DragNDropGameView {

	public ModOneDragNDropGameView(Context context, GameViewConfig config) {
		super(context, config);
	}
	
	private static final String VIEW_TAG = "Button answer";
	
	private Map<Integer, Integer> screensTarget;
	private Integer currentScreen;
	private Integer currentIndex;
	private LinearLayout container;
	private List<Integer> screens;
	
	private LinearLayout targetLayout;
	private Button targetButton;
	
	private Handler handler = new Handler();

	@Override
	protected void onCreateView(GameViewConfig config) {
		super.onCreateView(config);
		
		screensTarget = ((ModOneDragNDropGameViewConfig) config).getScreens();
		container = (LinearLayout) findViewById(R.id.container);
		
		currentIndex = 0;
		screens = new ArrayList<Integer>();
		for (Integer i : screensTarget.keySet()) {
			screens.add(i);
		}
		
		loadNextScreen();
		
		Button btn = (Button) findViewById(R.id.parque_x_2_bt1);
		btn.setOnTouchListener(this);
		btn.setTag(VIEW_TAG);
		btn = (Button) findViewById(R.id.parque_x_2_bt2);
		btn.setOnTouchListener(this);
		btn.setTag(VIEW_TAG);
		btn = (Button) findViewById(R.id.parque_x_2_bt3);
		btn.setOnTouchListener(this);
		btn.setTag(VIEW_TAG);
		btn = (Button) findViewById(R.id.parque_x_2_bt4);
		btn.setOnTouchListener(this);
		btn.setTag(VIEW_TAG);
		btn = (Button) findViewById(R.id.parque_x_2_bt5);
		btn.setOnTouchListener(this);
		btn.setTag(VIEW_TAG);
		
		targetButton = (Button) findViewById(R.id.parque_x_2_bt_target);
		targetLayout = (LinearLayout)findViewById(R.id.parque_x_2_target);
		targetLayout.setOnDragListener(this);
	}
	
	private void loadNextScreen() {
		currentScreen = screens.get(currentIndex);
		View screen = LayoutInflater.from(getContext()).inflate(currentScreen, null);
		container.removeAllViewsInLayout();
		container.addView(screen);
		currentIndex ++;
	}
	
	
	private Runnable resetRunnable = new Runnable() {
		
		@Override
		public void run() {
			targetButton.setVisibility(View.INVISIBLE);
			if (currentIndex < screens.size()) {
				loadNextScreen();
				getDraggingView().setVisibility(View.VISIBLE);
			} else {
				endGame();
			}
		}
	};

	@Override
	protected void onDropView(View v) {
		if (v.getId() == R.id.parque_x_2_target && screensTarget.get(currentScreen) == getDraggingView().getId()) {
			
			placeFigureOnTarget(getDraggingView(), targetButton);
			
			MusicManager.playSingle(getContext(), R.raw.acierto);
			handler.postDelayed(resetRunnable, 1000);
		} else {
			if (screensTarget.get(currentScreen) != getDraggingView().getId()) {
				MusicManager.playSingle(getContext(), R.raw.fallo);
			}
			moveBack(getDroppedX(), getDroppedY());
		}
		
	}

}
