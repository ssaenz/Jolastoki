package eu.lapecera.jolastoki.domain;

import java.util.ArrayList;
import java.util.List;

import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.DragNDropGameViewConfig;
import eu.lapecera.jolastoki.config.DragNDropSpecialGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.PairsGameViewConfig;
import eu.lapecera.jolastoki.config.QuizGameViewConfig;


public enum GameArea {
	MERCADO		(R.drawable.cabecera_juegos_ico_mercado, new GameViewConfig[]{QuizGameViewConfig.MERCADO_1_1, 
																				QuizGameViewConfig.MERCADO_2_1, 
																				QuizGameViewConfig.MERCADO_3_1}),
	CHARCA		(R.drawable.cabecera_juegos_ico_charca, new GameViewConfig[]{PairsGameViewConfig.CHARCA_1_1,
																				PairsGameViewConfig.CHARCA_2_1,
																				PairsGameViewConfig.CHARCA_3_1}),
	PARQUE		(R.drawable.cabecera_juegos_ico_parque, new GameViewConfig[]{
																				//QuizGameViewConfig.PARQUE_1_1, 
																				//DragNDropSpecialGameViewConfig.parque_1_2,
																				DragNDropGameViewConfig.parque_1_3,
																				QuizGameViewConfig.PARQUE_2_1,
																				DragNDropSpecialGameViewConfig.parque_2_2,
																				QuizGameViewConfig.PARQUE_2_3,
																				QuizGameViewConfig.PARQUE_3_1,
																				DragNDropSpecialGameViewConfig.parque_3_2,
																				QuizGameViewConfig.PARQUE_3_3}),
	COLEGIO		(R.drawable.cabecera_juegos_ico_colegio, new GameViewConfig[]{});

	private int icon;
	private GameViewConfig[] games;

	private GameArea (int icon, GameViewConfig[] games ) {
		this.icon = icon;
		this.games = games;
	}
	
	public List<GameViewConfig> getGamesByLevel (GameLevel level) {
		List<GameViewConfig> gameConfigs = new ArrayList<GameViewConfig>();
		for (GameViewConfig gameConfig : this.getGames()) {
			if (gameConfig.getLevel().equals(level))
				gameConfigs.add(gameConfig);
		}
		return gameConfigs;
	}
	
	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public GameViewConfig[] getGames() {
		return games;
	}

	public void setGames(GameViewConfig[] games) {
		this.games = games;
	}

}
