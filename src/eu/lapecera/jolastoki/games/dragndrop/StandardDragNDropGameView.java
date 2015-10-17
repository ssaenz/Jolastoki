package eu.lapecera.jolastoki.games.dragndrop;

import java.util.Collection;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.StandardDragNDropGameViewConfig;
import eu.lapecera.jolastoki.util.MusicManager;

public class StandardDragNDropGameView extends DragNDropGameView {

	private static final String VIEW_TAG = "Button answer";
	public StandardDragNDropGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	private Map<Integer, Integer> targetMap;
	private int numMatches = 0;
	private Map<Integer, Integer> targetBackgournds;
	
	@Override
	protected void onCreateView(GameViewConfig config) {
		super.onCreateView(config);
		targetMap = ((StandardDragNDropGameViewConfig)config).getTargets();
		targetBackgournds = ((StandardDragNDropGameViewConfig)config).getMultiFigureTargetBackgound();
		Collection<Integer> figures = ((StandardDragNDropGameViewConfig)config).getFigures();
		Collection<Integer> targets = targetMap.values();

		for (Integer figure : figures) {
			View v = findViewById(figure);
			v.setOnTouchListener(this);
			v.setTag(VIEW_TAG);
		}

		for (Integer target : targets) {
			findViewById(target).setOnDragListener(this);
		}
	}

	@Override
	protected void onDropView(View v) {
		if ((targetMap.containsValue(v.getId()) && (targetMap.get(getDraggingView().getId()) != null && targetMap.get(getDraggingView().getId()) == v.getId()))) {
			getDraggingView().setVisibility(View.INVISIBLE);
			placeFigureOnTarget(getDraggingView(), v);
			MusicManager.playSingle(getContext(), R.raw.acierto);
			numMatches ++;
			if (numMatches == targetMap.size()) {
				endGame();
			}
		} else {
			if (targetMap.containsValue(v.getId())) {
				MusicManager.playSingle(getContext(), R.raw.fallo);
			}
			moveBack(getDroppedX(), getDroppedY());
		}
	}
	
	@Override
	protected void placeFigureOnTarget(View figure, View target) {
		if (targetBackgournds != null) {
			ImageView figureTarget = (ImageView) findViewById(targetBackgournds.get(figure.getId()));
			figureTarget.setVisibility(View.VISIBLE);
		} else {
			super.placeFigureOnTarget(figure, target);
		}
	}
}
