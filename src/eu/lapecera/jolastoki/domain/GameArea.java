package eu.lapecera.jolastoki.domain;

import java.util.ArrayList;
import java.util.List;

import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.ColorGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.ModOneDragNDropGameViewConfig;
import eu.lapecera.jolastoki.config.ModTwoDragNDropGameViewConfig;
import eu.lapecera.jolastoki.config.PairsGameViewConfig;
import eu.lapecera.jolastoki.config.QuizGameViewConfig;
import eu.lapecera.jolastoki.config.StandardDragNDropGameViewConfig;


public enum GameArea {
	
	MERCADO		(R.drawable.cabecera_juegos_ico_mercado, new GameViewConfig[]{	
																				QuizGameViewConfig.MERCADO_1_1, 
																				QuizGameViewConfig.MERCADO_2_1, 
																				QuizGameViewConfig.MERCADO_3_1,
																				ColorGameViewConfig.MERCADO_1_2,
																				ColorGameViewConfig.MERCADO_2_2,
																				ColorGameViewConfig.MERCADO_3_2,
																				StandardDragNDropGameViewConfig.mercado_1_3,
																				StandardDragNDropGameViewConfig.mercado_2_3,
																				ModTwoDragNDropGameViewConfig.MERCADO_3_3}),
	
	CHARCA		(R.drawable.cabecera_juegos_ico_charca, new GameViewConfig[]{	PairsGameViewConfig.CHARCA_1_1,
																				PairsGameViewConfig.CHARCA_2_1,
																				PairsGameViewConfig.CHARCA_3_1,
																				StandardDragNDropGameViewConfig.charca_1_3,
																				StandardDragNDropGameViewConfig.charca_2_3,
																				StandardDragNDropGameViewConfig.charca_3_3,
																				StandardDragNDropGameViewConfig.charca_1_2,
																				StandardDragNDropGameViewConfig.charca_2_2,
																				StandardDragNDropGameViewConfig.charca_3_2}),
	
	PARQUE		(R.drawable.cabecera_juegos_ico_parque, new GameViewConfig[]{	QuizGameViewConfig.PARQUE_1_1, 
																				ModOneDragNDropGameViewConfig.parque_1_2,
																				StandardDragNDropGameViewConfig.parque_1_3,
																				QuizGameViewConfig.PARQUE_2_1,
																				ModOneDragNDropGameViewConfig.parque_2_2,
																				QuizGameViewConfig.PARQUE_2_3,
																				QuizGameViewConfig.PARQUE_3_1,
																				ModOneDragNDropGameViewConfig.parque_3_2,
																				QuizGameViewConfig.PARQUE_3_3}),
	
	BIBLIOTECA		(R.drawable.cabecera_juegos_ico_colegio, new GameViewConfig[]{	
//																					StandardDragNDropGameViewConfig.biblioteca_1_1,
//																					StandardDragNDropGameViewConfig.biblioteca_2_1,
//																					StandardDragNDropGameViewConfig.biblioteca_3_1,
//																					StandardDragNDropGameViewConfig.biblioteca_1_2,
//																					StandardDragNDropGameViewConfig.biblioteca_2_2,
//																					StandardDragNDropGameViewConfig.biblioteca_3_2,
																					StandardDragNDropGameViewConfig.biblioteca_1_3,
																					StandardDragNDropGameViewConfig.biblioteca_2_3});

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
