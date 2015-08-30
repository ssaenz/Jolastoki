package eu.lapecera.jolastoki.config;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.dragndrop.StandardDragNDropGameView;

public enum DragNDropGameViewConfig implements GameViewConfig {

	parque_1_3 (GameLevel.ONE, R.layout.layout_parque_1_3, R.string.parque_1_3_title, 60000l, 
			new HashMap<Integer, Integer>() { 
				private static final long serialVersionUID = 1L;
				{
					put( R.id.parque_1_3_target3, R.id.parque_1_3_bt1);
					put(R.id.parque_1_3_target2, R.id.parque_1_3_bt2);
					put(R.id.parque_1_3_target1, R.id.parque_1_3_bt3);
					put(R.id.parque_1_3_target6, R.id.parque_1_3_bt4);
					put(R.id.parque_1_3_target5, R.id.parque_1_3_bt5);
					put(R.id.parque_1_3_target4, R.id.parque_1_3_bt6);
				}});


	private GameLevel level;
	private int layout;
	private long time;
	private int title;
	private Map<Integer, Integer> targets;

	private DragNDropGameViewConfig(GameLevel level, int layout, int title, long time, Map<Integer, Integer> targets) {
		this.level = level;
		this.time = time;
		this.title = title;
		this.layout = layout;
		this.targets = targets;
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
