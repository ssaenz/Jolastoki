package eu.lapecera.jolastoki.config;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.dragndrop.ModOneDragNDropGameView;

public enum ModOneDragNDropGameViewConfig implements GameViewConfig {

	parque_1_2 (GameLevel.ONE, R.layout.layout_parque_1_2, R.string.parque_x_2_title, 60000l, 
			R.drawable.parque_2_circulo_acierto, R.drawable.parque_2_circulo_normal,
			new HashMap<Integer, Integer>() { 
				private static final long serialVersionUID = 1L;
				{
					put(R.layout.screen_parque_1_2_5plus3, R.id.parque_x_2_bt1);
					put(R.layout.screen_parque_1_2_10plus1, R.id.parque_x_2_bt2);
					put(R.layout.screen_parque_1_2_7minus6, R.id.parque_x_2_bt5);
					put(R.layout.screen_parque_1_2_8minus8, R.id.parque_x_2_bt3);
					put(R.layout.screen_parque_1_2_4plus3, R.id.parque_x_2_bt4);
				}}),
	parque_2_2 (GameLevel.TWO, R.layout.layout_parque_2_2, R.string.parque_x_2_title, 60000l, 
			R.drawable.parque_2_circulo_acierto, R.drawable.parque_2_circulo_normal,
			new HashMap<Integer, Integer>() { 
				private static final long serialVersionUID = 1L;
				{
					put(R.layout.screen_parque_2_2_31minus29, R.id.parque_x_2_bt1);
					put(R.layout.screen_parque_2_2_45minus36, R.id.parque_x_2_bt2);
					put(R.layout.screen_parque_2_2_26plus67, R.id.parque_x_2_bt5);
					put(R.layout.screen_parque_2_2_44plus33, R.id.parque_x_2_bt3);
					put(R.layout.screen_parque_2_2_83minus25, R.id.parque_x_2_bt4);
				}}),
	parque_3_2 (GameLevel.THREE, R.layout.layout_parque_3_2, R.string.parque_x_2_title, 60000l, 
			R.drawable.parque_2_circulo_acierto, R.drawable.parque_2_circulo_normal,
			new HashMap<Integer, Integer>() { 
				private static final long serialVersionUID = 1L;
				{
					put(R.layout.screen_parque_3_2_450minus367, R.id.parque_x_2_bt1);
					put(R.layout.screen_parque_3_2_566minus548, R.id.parque_x_2_bt2);
					put(R.layout.screen_parque_3_2_888minus116, R.id.parque_x_2_bt3);
					put(R.layout.screen_parque_3_2_724plus209, R.id.parque_x_2_bt4);
					put(R.layout.screen_parque_3_2_234plus351, R.id.parque_x_2_bt5);
				}});


	private GameLevel level;
	private int layout;
	private long time;
	private int title;
	private int backgroundOk;
	private int backgroundNormal;
	private Map<Integer, Integer> screens;

	private ModOneDragNDropGameViewConfig(GameLevel level, int layout, int title, long time, int backgroundOk, int backgroundNormal, Map<Integer, Integer> screens) {
		this.level = level;
		this.time = time;
		this.title = title;
		this.layout = layout;
		this.backgroundOk = backgroundOk;
		this.backgroundNormal = backgroundNormal;
		this.screens = screens;
	}

	public int getBackgroundOk() {
		return backgroundOk;
	}

	public void setBackgroundOk(int backgroundOk) {
		this.backgroundOk = backgroundOk;
	}

	public int getBackgroundNormal() {
		return backgroundNormal;
	}

	public void setBackgroundNormal(int backgroundNormal) {
		this.backgroundNormal = backgroundNormal;
	}

	public Map<Integer, Integer> getScreens() {
		return screens;
	}

	public void setScreens(Map<Integer, Integer> screens) {
		this.screens = screens;
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
		return new ModOneDragNDropGameView(context, this);
	}

}
