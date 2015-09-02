package eu.lapecera.jolastoki.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.color.ColorGameView;

public enum ColorGameViewConfig implements GameViewConfig {
	
	MERCADO_1_2 (GameLevel.ONE, R.layout.layout_mercado_x_2, R.string.mercado_x_2_title, 60000l,
					Arrays.asList(R.id.mercado_x_2_bt1, R.id.mercado_x_2_bt2, R.id.mercado_x_2_bt3, R.id.mercado_x_2_bt4, R.id.mercado_x_2_bt5, R.id.mercado_x_2_bt6, R.id.mercado_x_2_bt7, R.id.mercado_x_2_bt8, R.id.mercado_x_2_bt9, R.id.mercado_x_2_bt10),
					new HashMap<Integer, String> (){
						private static final long serialVersionUID = 1L;
						{
							put(R.id.mercado_x_2_bt1, "X");
							put(R.id.mercado_x_2_bt2, "G");
							put(R.id.mercado_x_2_bt3, "R");
							put(R.id.mercado_x_2_bt4, "O");
							put(R.id.mercado_x_2_bt5, "N");
							put(R.id.mercado_x_2_bt6, "U");
							put(R.id.mercado_x_2_bt7, "B");
							put(R.id.mercado_x_2_bt8, "M");
							put(R.id.mercado_x_2_bt9, "Z");
							put(R.id.mercado_x_2_bt10, "E");
						}
					}),
	MERCADO_2_2 (GameLevel.TWO, R.layout.layout_mercado_x_2, R.string.mercado_x_2_title, 60000l,
			Arrays.asList(R.id.mercado_x_2_bt1, R.id.mercado_x_2_bt2, R.id.mercado_x_2_bt3, R.id.mercado_x_2_bt4, R.id.mercado_x_2_bt5, R.id.mercado_x_2_bt6, R.id.mercado_x_2_bt7, R.id.mercado_x_2_bt8, R.id.mercado_x_2_bt9, R.id.mercado_x_2_bt10),
			new HashMap<Integer, String> (){
				private static final long serialVersionUID = 1L;
				{
					put(R.id.mercado_x_2_bt1, "PO");
					put(R.id.mercado_x_2_bt2, "KO");
					put(R.id.mercado_x_2_bt3, "GO");
					put(R.id.mercado_x_2_bt4, "ZO");
					put(R.id.mercado_x_2_bt5, "BO");
					put(R.id.mercado_x_2_bt6, "MO");
					put(R.id.mercado_x_2_bt7, "LO");
					put(R.id.mercado_x_2_bt8, "HO");
					put(R.id.mercado_x_2_bt9, "NO");
					put(R.id.mercado_x_2_bt10, "DO");
				}
			}),
	MERCADO_3_2 (GameLevel.THREE, R.layout.layout_mercado_x_2, R.string.mercado_x_2_title, 60000l,
			Arrays.asList(R.id.mercado_x_2_bt1, R.id.mercado_x_2_bt2, R.id.mercado_x_2_bt3, R.id.mercado_x_2_bt4, R.id.mercado_x_2_bt5, R.id.mercado_x_2_bt6, R.id.mercado_x_2_bt7, R.id.mercado_x_2_bt8, R.id.mercado_x_2_bt9, R.id.mercado_x_2_bt10),
			new HashMap<Integer, String> (){
				private static final long serialVersionUID = 1L;
				{
					put(R.id.mercado_x_2_bt1, "TXA");
					put(R.id.mercado_x_2_bt2, "TXE");
					put(R.id.mercado_x_2_bt3, "TXI");
					put(R.id.mercado_x_2_bt4, "TXO");
					put(R.id.mercado_x_2_bt5, "TXU");
					put(R.id.mercado_x_2_bt6, "HAZ");
					put(R.id.mercado_x_2_bt7, "HEZ");
					put(R.id.mercado_x_2_bt8, "HIZ");
					put(R.id.mercado_x_2_bt9, "HOZ");
					put(R.id.mercado_x_2_bt10, "HUZ");
				}
			});
	
	private long time;
	private GameLevel level;
	private int layout;
	private int title;
	private List<Integer> buttons;
	private Map<Integer, String> buttonTexts;
	
	private ColorGameViewConfig(GameLevel level, int layout, int title, long time, List<Integer> buttons,  Map<Integer, String> buttonColors) {
		this.time = time;
		this.level = level;
		this.layout = layout;
		this.title = title;
		this.buttons = buttons;
		this.buttonTexts = buttonColors;
	}

	public List<Integer> getButtons() {
		return buttons;
	}

	public Map<Integer, String> getButtonTexts() {
		return buttonTexts;
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
		return new ColorGameView (context, this);
	}

}
