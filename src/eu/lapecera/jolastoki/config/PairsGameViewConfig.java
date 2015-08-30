package eu.lapecera.jolastoki.config;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.pairs.PairsGameView;

public enum PairsGameViewConfig implements GameViewConfig {
	
	CHARCA_1_1 (GameLevel.ONE, R.layout.layout_charca_x_1, R.string.charca_title_1_1, 
					new HashMap<Integer, Integer>(){
						private static final long serialVersionUID = 1L;
					{
						put(R.id.charca_bt1, R.string.charca_1_1_bt_1);
						put(R.id.charca_bt2, R.string.charca_1_1_bt_2);
						put(R.id.charca_bt3, R.string.charca_1_1_bt_3);
						put(R.id.charca_bt4, R.string.charca_1_1_bt_4);
						put(R.id.charca_bt5, R.string.charca_1_1_bt_5);
						put(R.id.charca_bt6, R.string.charca_1_1_bt_6);
					}},
					new HashMap<Integer, Integer>(){
						private static final long serialVersionUID = 1L;
					{
						put(R.id.charca_bt1, R.id.charca_bt6);
						put(R.id.charca_bt2, R.id.charca_bt4);
						put(R.id.charca_bt3, R.id.charca_bt5);
						put(R.id.charca_bt4, R.id.charca_bt2);
						put(R.id.charca_bt5, R.id.charca_bt3);
						put(R.id.charca_bt6, R.id.charca_bt1);
					}}, 60000l),
	CHARCA_2_1 (GameLevel.TWO, R.layout.layout_charca_x_1, R.string.charca_title_2_1, 
			new HashMap<Integer, Integer>(){
				private static final long serialVersionUID = 1L;
			{
				put(R.id.charca_bt1, R.string.charca_2_1_bt_1);
				put(R.id.charca_bt2, R.string.charca_2_1_bt_2);
				put(R.id.charca_bt3, R.string.charca_2_1_bt_3);
				put(R.id.charca_bt4, R.string.charca_2_1_bt_4);
				put(R.id.charca_bt5, R.string.charca_2_1_bt_5);
				put(R.id.charca_bt6, R.string.charca_2_1_bt_6);
			}},
			new HashMap<Integer, Integer>(){
				private static final long serialVersionUID = 1L;
			{
				put(R.id.charca_bt1, R.id.charca_bt3);
				put(R.id.charca_bt2, R.id.charca_bt4);
				put(R.id.charca_bt3, R.id.charca_bt1);
				put(R.id.charca_bt4, R.id.charca_bt2);
				put(R.id.charca_bt5, R.id.charca_bt6);
				put(R.id.charca_bt6, R.id.charca_bt5);
			}}, 60000l),
	CHARCA_3_1 (GameLevel.THREE, R.layout.layout_charca_x_1, R.string.charca_title_3_1, 
			new HashMap<Integer, Integer>(){
				private static final long serialVersionUID = 1L;
			{
				put(R.id.charca_bt1, R.string.charca_3_1_bt_1);
				put(R.id.charca_bt2, R.string.charca_3_1_bt_2);
				put(R.id.charca_bt3, R.string.charca_3_1_bt_3);
				put(R.id.charca_bt4, R.string.charca_3_1_bt_4);
				put(R.id.charca_bt5, R.string.charca_3_1_bt_5);
				put(R.id.charca_bt6, R.string.charca_3_1_bt_6);
			}},
			new HashMap<Integer, Integer>(){
				private static final long serialVersionUID = 1L;
			{
				put(R.id.charca_bt1, R.id.charca_bt6);
				put(R.id.charca_bt2, R.id.charca_bt5);
				put(R.id.charca_bt3, R.id.charca_bt4);
				put(R.id.charca_bt4, R.id.charca_bt3);
				put(R.id.charca_bt5, R.id.charca_bt2);
				put(R.id.charca_bt6, R.id.charca_bt1);
			}}, 60000l);
	
	private GameLevel level;
	private int layout;
	private int title;
	private long time;
	private Map<Integer, Integer> texts;
	private Map<Integer, Integer> answers;

	private PairsGameViewConfig(GameLevel level, int layout, int title, Map<Integer, Integer> texts, Map<Integer, Integer> answers, long time) {
		this.level = level;
		this.layout = layout;
		this.title = title;
		this.time = time;
		this.texts = texts;
		this.answers = answers;
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
	
	public Map<Integer, Integer> getTexts() {
		return texts;
	}

	public Map<Integer, Integer> getAnswers() {
		return answers;
	}

	@Override
	public GameView getGameView(Context context) {
		return new PairsGameView(context, this);
	}

}
