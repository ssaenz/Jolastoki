package eu.lapecera.jolastoki.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.dragndrop.ModTwoDragNDropGameView;

public enum ModTwoDragNDropGameViewConfig implements DragNDropGameViewConfig {
	
	MERCADO_3_3 (GameLevel.THREE, R.layout.layout_mercado_3_3, R.string.mercado_3_3_title, 60000l, -1,
					Arrays.asList(R.id.mercado_3_3_figure_eq, R.id.mercado_3_3_figure_gt, R.id.mercado_3_3_figure_lt),
					Arrays.asList(R.id.mercado_3_3_target1, R.id.mercado_3_3_target2, R.id.mercado_3_3_target3),
					new HashMap<Integer, Map<Integer, Integer>>() {
						private static final long serialVersionUID = 1L;
						{
							put(R.layout.screen_mercado_3_3_1, new HashMap<Integer, Integer>() {
								private static final long serialVersionUID = 1L;
								{
									put(R.id.mercado_3_3_target1, R.id.mercado_3_3_figure_lt);
									put(R.id.mercado_3_3_target2, R.id.mercado_3_3_figure_eq);
									put(R.id.mercado_3_3_target3, R.id.mercado_3_3_figure_gt);
								}
							});
							put(R.layout.screen_mercado_3_3_2, new HashMap<Integer, Integer>() {
								private static final long serialVersionUID = 1L;
								{
									put(R.id.mercado_3_3_target1, R.id.mercado_3_3_figure_eq);
									put(R.id.mercado_3_3_target2, R.id.mercado_3_3_figure_gt);
									put(R.id.mercado_3_3_target3, R.id.mercado_3_3_figure_lt);
								}
							});
							put(R.layout.screen_mercado_3_3_3, new HashMap<Integer, Integer>() {
								private static final long serialVersionUID = 1L;
								{
									put(R.id.mercado_3_3_target1, R.id.mercado_3_3_figure_gt);
									put(R.id.mercado_3_3_target2, R.id.mercado_3_3_figure_lt);
									put(R.id.mercado_3_3_target3, R.id.mercado_3_3_figure_eq);
								}
							});
						}
					}
				);
	
	
	private GameLevel level;
	private int layout;
	private int title;
	private long time;
	private List<Integer> figures;
	private List<Integer> targets;
	private Map<Integer, Map<Integer, Integer>> targetMap;
	private int endTargetBackground;
	
	private ModTwoDragNDropGameViewConfig (GameLevel level, int layout, int title, long time, int endTargetBackground, List<Integer> figures, List<Integer> targets, Map<Integer, Map<Integer, Integer>> targetMap) {
		this.level = level;
		this.layout = layout;
		this.title = title;
		this.time = time;
		this.figures = figures;
		this.targets = targets;
		this.targetMap = targetMap;
		this.endTargetBackground = endTargetBackground;
	}

	public List<Integer> getTargets() {
		return targets;
	}

	public List<Integer> getFigures() {
		return figures;
	}

	public Map<Integer, Map<Integer, Integer>> getTargetMap() {
		return targetMap;
	}

	@Override
	public long getTime() {
		return this.time;
	}

	@Override
	public int getLayout() {
		return this.layout;
	}

	@Override
	public int getTitle() {
		return this.title;
	}

	@Override
	public GameLevel getLevel() {
		return this.level;
	}

	@Override
	public GameView getGameView(Context context) {
		return new ModTwoDragNDropGameView(context, this);
	}

	@Override
	public int getEndTargetBackground() {
		return this.endTargetBackground;
	}

}
