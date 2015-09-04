package eu.lapecera.jolastoki.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.dragndrop.StandardDragNDropGameView;

public enum StandardDragNDropGameViewConfig implements DragNDropGameViewConfig {

	parque_1_3 (GameLevel.ONE, R.layout.layout_parque_1_3, R.string.parque_1_3_title, 60000l, -1,
			Arrays.asList(R.id.parque_1_3_bt1, R.id.parque_1_3_bt2, R.id.parque_1_3_bt3, R.id.parque_1_3_bt4, R.id.parque_1_3_bt5, R.id.parque_1_3_bt6),
			new HashMap<Integer, Integer>() { 
		private static final long serialVersionUID = 1L;
		{
			put( R.id.parque_1_3_target3, R.id.parque_1_3_bt1);
			put(R.id.parque_1_3_target2, R.id.parque_1_3_bt2);
			put(R.id.parque_1_3_target1, R.id.parque_1_3_bt3);
			put(R.id.parque_1_3_target6, R.id.parque_1_3_bt4);
			put(R.id.parque_1_3_target5, R.id.parque_1_3_bt5);
			put(R.id.parque_1_3_target4, R.id.parque_1_3_bt6);
		}}),
	charca_1_3 (GameLevel.ONE, R.layout.layout_charca_1_3, R.string.charca_x_3_title, 60000l, -1,
			Arrays.asList(R.id.charca_3_figure1, R.id.charca_3_figure2, R.id.charca_3_figure3, R.id.charca_3_figure4),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_target1, R.id.charca_3_figure4);
			put(R.id.charca_3_target2, R.id.charca_3_figure3);
			put(R.id.charca_3_target3, R.id.charca_3_figure1);
		}

	}),
	charca_2_3 (GameLevel.TWO, R.layout.layout_charca_2_3, R.string.charca_x_3_title, 60000l, -1,
			Arrays.asList(R.id.charca_3_figure1, R.id.charca_3_figure2, R.id.charca_3_figure3, R.id.charca_3_figure4),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_target1, R.id.charca_3_figure3);
			put(R.id.charca_3_target2, R.id.charca_3_figure1);
			put(R.id.charca_3_target3, R.id.charca_3_figure2);
		}

	}),
	charca_3_3 (GameLevel.THREE, R.layout.layout_charca_3_3, R.string.charca_x_3_title, 60000l, -1,
			Arrays.asList(R.id.charca_3_figure1, R.id.charca_3_figure2, R.id.charca_3_figure3, R.id.charca_3_figure4),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_target1, R.id.charca_3_figure2);
			put(R.id.charca_3_target2, R.id.charca_3_figure1);
			put(R.id.charca_3_target3, R.id.charca_3_figure3);
		}

	}),
	charca_1_2 (GameLevel.ONE, R.layout.layout_charca_1_2, R.string.charca_x_2_title, 60000l, R.drawable.charca_2_target_selected,
			Arrays.asList(R.id.charca_2_bt1, R.id.charca_2_bt2, R.id.charca_2_bt3, R.id.charca_2_bt4, R.id.charca_2_bt5, R.id.charca_2_bt6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_1_2_target1, R.id.charca_2_bt2);
			put(R.id.charca_1_2_target2, R.id.charca_2_bt6);
			put(R.id.charca_1_2_target3, R.id.charca_2_bt3);
			put(R.id.charca_1_2_target4, R.id.charca_2_bt1);
			put(R.id.charca_1_2_target5, R.id.charca_2_bt4);
			put(R.id.charca_1_2_target6, R.id.charca_2_bt5);
		}

	}),
	charca_2_2 (GameLevel.TWO, R.layout.layout_charca_2_2, R.string.charca_x_2_title, 60000l, R.drawable.charca_2_target_selected,
			Arrays.asList(R.id.charca_2_bt1, R.id.charca_2_bt2, R.id.charca_2_bt3, R.id.charca_2_bt4, R.id.charca_2_bt5, R.id.charca_2_bt6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_2_2_target1, R.id.charca_2_bt5);
			put(R.id.charca_2_2_target2, R.id.charca_2_bt6);
			put(R.id.charca_2_2_target3, R.id.charca_2_bt4);
			put(R.id.charca_2_2_target4, R.id.charca_2_bt2);
			put(R.id.charca_2_2_target5, R.id.charca_2_bt3);
			put(R.id.charca_2_2_target6, R.id.charca_2_bt1);
		}

	}),
	charca_3_2 (GameLevel.THREE, R.layout.layout_charca_3_2, R.string.charca_x_2_title, 60000l, R.drawable.charca_2_target_selected,
			Arrays.asList(R.id.charca_2_bt1, R.id.charca_2_bt2, R.id.charca_2_bt3, R.id.charca_2_bt4, R.id.charca_2_bt5, R.id.charca_2_bt6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_2_target1, R.id.charca_2_bt2);
			put(R.id.charca_3_2_target2, R.id.charca_2_bt6);
			put(R.id.charca_3_2_target3, R.id.charca_2_bt3);
			put(R.id.charca_3_2_target4, R.id.charca_2_bt1);
			put(R.id.charca_3_2_target5, R.id.charca_2_bt4);
			put(R.id.charca_3_2_target6, R.id.charca_2_bt5);
		}

	});

	private GameLevel level;
	private int layout;
	private long time;
	private int title;
	private List<Integer> figures;
	private Map<Integer, Integer> targets;
	private int endTargetBackground;

	private StandardDragNDropGameViewConfig(GameLevel level, int layout, int title, long time, int endTargetBackground, List<Integer> figures, Map<Integer, Integer> targets) {
		this.level = level;
		this.time = time;
		this.title = title;
		this.layout = layout;
		this.endTargetBackground = endTargetBackground;
		this.targets = targets;
		this.figures = figures;
	}

	public List<Integer> getFigures() {
		return figures;
	}

	public Map<Integer, Integer> getTargets() {
		return targets;
	}

	@Override
	public long getTime() {
		return this.time;
	}

	@Override
	public int getLayout() {
		return this.layout;
	}

	public int getEndTargetBackground() {
		return endTargetBackground;
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
		return new StandardDragNDropGameView(context, this);
	}

}
