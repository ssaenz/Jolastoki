package eu.lapecera.jolastoki.config;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.quiz.QuizGameView;


public enum QuizGameViewConfig implements GameViewConfig {
	MERCADO_1_1 (GameLevel.ONE, R.layout.layout_mercado_1_1, R.string.mercado_1_1_title, 
					new int[]{R.id.mercado_1_1_answer_1, R.id.mercado_1_1_answer_2, R.id.mercado_1_1_answer_3, R.id.mercado_1_1_answer_4}, 
					-1, new int[]{}, 
					new int[]{R.id.mercado_1_1_answer_3}, 60000),
	MERCADO_2_1 (GameLevel.TWO, R.layout.layout_mercado_2_1, R.string.mercado_1_1_title, 
					new int[]{R.id.mercado_1_1_answer_1, R.id.mercado_1_1_answer_2, R.id.mercado_1_1_answer_3, R.id.mercado_1_1_answer_4}, 
					-1, new int[]{}, 
					new int[]{R.id.mercado_1_1_answer_3}, 60000),
	MERCADO_3_1 (GameLevel.THREE, R.layout.layout_mercado_3_1, R.string.mercado_1_1_title, 
					new int[]{R.id.mercado_1_1_answer_1, R.id.mercado_1_1_answer_2, R.id.mercado_1_1_answer_3, R.id.mercado_1_1_answer_4}, 
					-1, new int[]{}, 
					new int[]{R.id.mercado_1_1_answer_2}, 60000),
	PARQUE_1_1 (GameLevel.ONE, R.layout.layout_parque_1_1, R.string.parque_1_1_title, 
					new int[]{R.id.parque_1_1_bt1, R.id.parque_1_1_bt2, R.id.parque_1_1_bt3}, 
					-1, new int[]{}, 
					new int[]{R.id.parque_1_1_bt1}, 60000),
	PARQUE_2_1 (GameLevel.TWO, R.layout.layout_parque_2_1, R.string.parque_1_1_title, 
					new int[]{R.id.parque_2_1_bt1, R.id.parque_2_1_bt2, R.id.parque_2_1_bt3, R.id.parque_2_1_bt4}, 
					-1, new int[]{}, 
					new int[]{R.id.parque_2_1_bt2}, 60000),
	PARQUE_2_3 (GameLevel.TWO, R.layout.layout_parque_2_3, R.string.parque_2_3_title, 
					new int[]{R.id.parque_2_3_bt1, R.id.parque_2_3_bt2, R.id.parque_2_3_bt3, R.id.parque_2_3_bt4, R.id.parque_2_3_bt5}, 
					R.id.container, 
					new int[]{R.layout.screen_parque_2_3_triangulo, R.layout.screen_parque_2_3_cuadrado, R.layout.screen_parque_2_3_pentagono, R.layout.screen_parque_2_3_hexagono}, 
					new int[]{R.id.parque_2_3_bt3, R.id.parque_2_3_bt4, R.id.parque_2_3_bt5, R.id.parque_2_3_bt1}, 60000),
	PARQUE_3_1 (GameLevel.THREE, R.layout.layout_parque_3_1, R.string.parque_1_1_title, 
					new int[]{R.id.parque_3_1_bt1, R.id.parque_3_1_bt2, R.id.parque_3_1_bt3, R.id.parque_3_1_bt4, R.id.parque_3_1_bt5}, 
					-1, new int[]{}, 
					new int[]{R.id.parque_3_1_bt4}, 60000),
	PARQUE_3_3 (GameLevel.THREE, R.layout.layout_parque_3_3, R.string.parque_3_3_title, 
					new int[]{R.id.parque_3_3_bt_cilindro, R.id.parque_3_3_bt_cubo, R.id.parque_3_3_bt_esfera, R.id.parque_3_3_bt_priramide, R.id.parque_3_3_bt_prisma}, 
					R.id.container, new int[]{R.layout.screen_parque_3_3_cilindro, R.layout.screen_parque_3_3_cubo, R.layout.screen_parque_3_3_esfera, R.layout.screen_parque_3_3_piramide, R.layout.screen_parque_3_3_prisma}, 
					new int[]{R.id.parque_3_3_bt_cilindro, R.id.parque_3_3_bt_cubo, R.id.parque_3_3_bt_esfera, R.id.parque_3_3_bt_priramide, R.id.parque_3_3_bt_prisma}, 60000);

	private GameLevel level;
	
	private int layout;
	private long time;
	private int title;
	private int[] answers;
	private int container;
	private int[] screens;
	private int[] rightAnswers;
	
	private QuizGameViewConfig (GameLevel level, int layout, int title, int[] answers, int container, int[] screens, int[] rightAnswers, long time){
		this.level = level;
		this.layout = layout;
		this.time = time;
		this.title = title;
		this.answers = answers;
		this.container = container;
		this.screens = screens;
		this.rightAnswers = rightAnswers;
	}
	
	public GameView getGameView (Context context) {
		return new QuizGameView(context, this);
	}

	public int getContainer() {
		return container;
	}

	public int[] getScreens() {
		return screens;
	}

	public int[] getRightAnswers() {
		return rightAnswers;
	}

	@Override
	public GameLevel getLevel() {
		return level;
	}

	@Override
	public int getLayout() {
		return layout;
	}

	public int[] getAnswers() {
		return answers;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public int getTitle() {
		return this.title;
	}
	
}
