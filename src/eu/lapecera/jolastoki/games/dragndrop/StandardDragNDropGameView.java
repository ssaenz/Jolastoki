package eu.lapecera.jolastoki.games.dragndrop;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.view.View;
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
	
	@Override
	protected void onCreateView(GameViewConfig config) {
		super.onCreateView(config);
		targetMap = ((StandardDragNDropGameViewConfig)config).getTargets();
		Collection<Integer> figures = targetMap.values();
		Set<Integer> targets = targetMap.keySet();

		for (Integer figure : figures) {
			View v = findViewById(figure);
			v.setTag(VIEW_TAG);
			v.setOnTouchListener(this);
		}

		for (Integer target : targets) {
			findViewById(target).setOnDragListener(this);
		}
	}

	@Override
	protected void onDropView(View v) {
		if (targetMap.containsKey(v.getId()) && targetMap.get(v.getId()) == getDraggingView().getId()) {
			getDraggingView().setVisibility(View.INVISIBLE);
			v.setBackgroundDrawable(getDraggingView().getBackground());
			MusicManager.playSingle(getContext(), R.raw.acierto);
			numMatches ++;
			if (numMatches == targetMap.size()) {
				endGame();
			}
		} else {
			if (targetMap.containsKey(v.getId())) {
				MusicManager.playSingle(getContext(), R.raw.fallo);
			}
			moveBack(getDroppedX(), getDraggingView().getX(), getDroppedY(), getDraggingView().getY());
		}
	}
}
