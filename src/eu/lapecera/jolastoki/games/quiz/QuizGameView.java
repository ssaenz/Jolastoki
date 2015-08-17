package eu.lapecera.jolastoki.games.quiz;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.QuizGameViewConfig;
import eu.lapecera.jolastoki.games.GameView;

public class QuizGameView extends GameView {

	private int[] answers;
	private int[] rightAnswers;
	private int container;
	//	private int[] screens;

	//	private int currentScreen;
	private int currentRightAnswers;
	private int currentIndex = 0;


	public QuizGameView(Context context, GameViewConfig config) {
		super(context, config);

	}

	@Override
	protected void onCreateView(GameViewConfig config) {
		this.answers = ((QuizGameViewConfig)config).getAnswers();
		this.container = ((QuizGameViewConfig)config).getContainer();
		//		this.screens = ((QuizGameViewConfig)config).getScreens();
		this.rightAnswers = ((QuizGameViewConfig)config).getRightAnswers();

		LayoutInflater.from(getContext()).inflate(super.getLayout(), this);
		for (int id : this.answers) {
			Button buton = (Button) this.findViewById(id);
			System.out.println(buton.getClass().getName());
			System.out.println(buton.getText());
			buton.setOnClickListener(new AudibleOnClickListener(QuizGameView.this.getContext(), R.raw.seleccion) {

				@Override
				public void onAudibleClick(View v) {
					final View button = v;
					button.setSelected(true);
					if (v.getId() == QuizGameView.this.currentRightAnswers) {
						if (getGameOverListener() != null) {
							getGameOverListener().OnStopTime();
						}
						MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.acierto);
						mp.setLooping(false);
						mp.setOnCompletionListener(new OnCompletionListener() {

							@Override
							public void onCompletion(MediaPlayer mp) {
								button.setSelected(false);
								if (getGameOverListener() != null) {
									getGameOverListener().OnGameOver();
								}
							}
						});
						mp.start();

					} else {
						MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.fallo);
						mp.setLooping(false);
						mp.setOnCompletionListener(new OnCompletionListener() {

							@Override
							public void onCompletion(MediaPlayer mp) {
								button.setSelected(false);
							}
						});
						mp.start();
					}
				}
			});
		}

		if (this.container == -1) {
			this.currentRightAnswers = this.rightAnswers[this.currentIndex];
		} else {
			setScreen(this.currentIndex);
			this.currentRightAnswers = this.rightAnswers[0];
		}

	}

	private void setScreen (int index) {
		//		this.currentScreen = this.screens[0];
		//		View screen = 
		//		((LinearLayout)this.findViewById(this.container)).addView(child, params);
		//TODO

	}

}
