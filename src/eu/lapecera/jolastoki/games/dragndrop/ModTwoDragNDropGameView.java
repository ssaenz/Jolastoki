package eu.lapecera.jolastoki.games.dragndrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.ModTwoDragNDropGameViewConfig;
import eu.lapecera.jolastoki.util.MusicManager;

public class ModTwoDragNDropGameView extends DragNDropGameView {
	
	private static final String VIEW_TAG = "Button answer";

	public ModTwoDragNDropGameView(Context context, GameViewConfig config) {
		super(context, config);
	}
	
	private List<Integer> targets;
	private List<Integer> figures;
	private Map<Integer, Map<Integer, Integer>> targetMap;
	private List<Integer> screens;
	private LinearLayout container;
	
	private int currentIndex;
	private int currentScreen;
	
	private int targetCount;
	
	@Override
	protected void onCreateView(GameViewConfig config) {
		super.onCreateView(config);
		
		container = (LinearLayout) findViewById(R.id.container);
		
		figures = ((ModTwoDragNDropGameViewConfig) config).getFigures();
		
		for (int id: figures) {
			View v = findViewById(id);
			v.setOnTouchListener(this);
			v.setTag(VIEW_TAG);
		}
		
		targets = ((ModTwoDragNDropGameViewConfig) config).getTargets();

		targetMap = ((ModTwoDragNDropGameViewConfig) config).getTargetMap();
		screens = new ArrayList<Integer>();
		
		for (Integer screen : targetMap.keySet()) {
			screens.add(screen);
		}
		currentIndex = 0;
		targetCount = 0;
		loadNextScreen();
		
	}
	
	private void loadNextScreen() {
		if (currentIndex >= screens.size()) {
			endGame();
		} else {
			currentScreen = screens.get(currentIndex);
			View screen = LayoutInflater.from(getContext()).inflate(currentScreen, null);
			container.removeAllViewsInLayout();
			container.addView(screen);
			currentIndex ++;
	
			for (Integer target: targets) {
				findViewById(target).setOnDragListener(this);
			}
			for (Integer figure : figures) {
				findViewById(figure).setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	protected void onDropView(View view) {
		if (view != this && targets.contains(view.getId()) && targetMap.get(currentScreen).get(view.getId()) == getDraggingView().getId()) {
			placeFigureOnTarget(getDraggingView(), view);
			MusicManager.playSingle(getContext(), R.raw.acierto);
			targetCount ++;
			if (targetCount >= targetMap.get(currentScreen).keySet().size()) {
				targetCount = 0;
				loadNextScreen();
			}
		} else {
			if (view != this && targetMap.get(currentScreen).get(view.getId()) != getDraggingView().getId()) {
				MusicManager.playSingle(getContext(), R.raw.fallo);
			}
			moveBack(getDroppedX(), getDroppedY());
		}
	}

}
