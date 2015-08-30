package eu.lapecera.jolastoki.games.dragndrop;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.DragNDropGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;

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
		targetMap = ((DragNDropGameViewConfig)config).getTargets();
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
			MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.acierto);
			mp.setLooping(false);
			mp.start();
			numMatches ++;
			if (numMatches == targetMap.size()) {
				if (getGameOverListener() != null) {
					getGameOverListener().OnGameOver();
				}
			}
		} else {
			if (targetMap.containsKey(v.getId())) {
				MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.fallo);
				mp.setLooping(false);
				mp.start();
			}
			moveBack(getDroppedX(), getDraggingView().getX(), getDroppedY(), getDraggingView().getY());
		}
	}
}
