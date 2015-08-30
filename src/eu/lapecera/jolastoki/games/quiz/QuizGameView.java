package eu.lapecera.jolastoki.games.quiz;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.QuizGameViewConfig;
import eu.lapecera.jolastoki.games.GameView;

public class QuizGameView extends GameView {

	private int[] answers;
	private int[] rightAnswers;
	private int container;
	private int[] screens;

	private int currentScreen;
	private int currentRightAnswers;
	private int currentIndex = 0;
	
	private LinearLayout containerLayout;

	private Handler handler = new Handler();
	private ButtonHandler buttonHandler = new ButtonHandler();

	public QuizGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	@Override
	protected void onCreateView(GameViewConfig config) {
		this.answers = ((QuizGameViewConfig)config).getAnswers();
		this.container = ((QuizGameViewConfig)config).getContainer();
		this.screens = ((QuizGameViewConfig)config).getScreens();
		this.rightAnswers = ((QuizGameViewConfig)config).getRightAnswers();

		for (int id : this.answers) {
			View view = (View) this.findViewById(id);
			view.setOnClickListener(new AudibleOnClickListener(QuizGameView.this.getContext(), R.raw.seleccion) {

				@Override
				public void onAudibleClick(View v) {
					final View button = v;
					button.setSelected(true);
					buttonHandler.setView(button);
					if (v.getId() == QuizGameView.this.currentRightAnswers) {
						if (getGameOverListener() != null && !hasMoreScreens()) {
							getGameOverListener().OnStopTime();
						}
						MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.acierto);
						mp.setLooping(false);
						mp.start();
						buttonHandler.setFallo(true);

					} else {
						MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.fallo);
						mp.setLooping(false);
						mp.start();
						buttonHandler.setFallo(false);
					}
					handler.postDelayed(buttonHandler, 1000);
				}
			});
		}

		if (this.container == -1) {
			this.currentRightAnswers = this.rightAnswers[0];
		} else {
			containerLayout = (LinearLayout) findViewById(this.container);
			setScreen(0);
			this.currentRightAnswers = this.rightAnswers[0];
		}

	}

	private boolean hasMoreScreens() {
		return this.container != -1 && this.currentIndex < this.screens.length - 1;
	}

	private void showNextScreen () {
		this.currentIndex ++;
		setScreen(this.currentIndex);
		this.currentRightAnswers = this.rightAnswers[this.currentIndex];
	}
	
	private void setScreen (int index) {
		this.currentScreen = this.screens[index];
		View screenView = LayoutInflater.from(getContext()).inflate(this.currentScreen, null);
		containerLayout.removeAllViewsInLayout();
		containerLayout.addView(screenView);
	}

	private class ButtonHandler implements Runnable {

		private View view;
		private boolean isOk = true;

		public void setView (View v) {
			this.view = v;
		}
		public void setFallo (boolean isOk) {
			this.isOk = isOk;
		}

		@Override
		public void run() {
			if (isOk) {
				if (hasMoreScreens()) {
					showNextScreen();
				} else if (getGameOverListener() != null) {
					getGameOverListener().OnGameOver();
				}
			}
			view.setSelected(false);
		}

	}

}
