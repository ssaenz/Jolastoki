package eu.lapecera.jolastoki.games;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.common.BaseActivity;
import eu.lapecera.jolastoki.common.Constants;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.domain.GameArea;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.quiz.OnGameOverListener;
import eu.lapecera.jolastoki.widget.GameNumber;

public class GameActivity extends BaseActivity implements OnGameOverListener {

	private SimpleDateFormat format = new SimpleDateFormat("mm:ss");

	private GameLevel level;
	private GameArea area;

	private long gameTime;
	private	int score = 0;;
	private TextView scoreView;
	private TextView timeView;
	private TextView gameTitle;
	private ImageView gameIcon;
	private TextView levelIcon;
	private GameNumber gameNumber;

	private LinearLayout gameContent;

	private int currentGame;
	private List<GameView> games = new ArrayList<GameView>();

	private Handler timeChanger = new Handler();

	private boolean timeStopped = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Bundle extras = getIntent().getExtras();
		level = (GameLevel) extras.get(Constants.LEVEL_KEY);
		area = (GameArea) extras.get(Constants.AREA_KEY);

		scoreView = (TextView) findViewById(R.id.game_score);
		timeView = (TextView) findViewById(R.id.game_time);

		gameIcon = (ImageView) findViewById(R.id.game_icon);
		levelIcon = (TextView) findViewById(R.id.header_ico_level);
		gameTitle = (TextView) findViewById(R.id.game_title);
		gameContent = (LinearLayout) findViewById(R.id.game_content);
		gameNumber = (GameNumber) findViewById(R.id.game_number);

		gameIcon.setImageResource(area.getIcon());
		scoreView.setText("0");
		levelIcon.setText(Integer.toString(level.ordinal() + 1));

		List<GameViewConfig> configs = area.getGamesByLevel(level);
		for (GameViewConfig c : configs) {
			GameView game = c.getGameView(this);
			game.setOnGameOverListener(this);
			games.add(game);
		}

		currentGame = 0;
		loadNextGame();
		timeChanger.postDelayed(timeUpdater, 1000);

	}

	Runnable timeUpdater = new Runnable() {

		@Override
		public void run() {
			updateTime();
		}
	};

	/**
	 * Carga el siguiente juego si todavía quedan o muestra el escore final.
	 */
	@Override
	public void OnGameOver() {
		calculateTotalScore ();
		if (currentGame < games.size()) {
			loadNextGame();
		} else {
			goToRanking();
		}
	}

	@Override
	public void OnStopTime() {
		stopTime();
	}

	private synchronized void stopTime() {
		this.timeStopped = true;
	}

	private synchronized boolean isTimeStopped() {
		return this.timeStopped;
	}

	private void calculateTotalScore () {
		long timeRest = (Long) this.timeView.getTag();
		score = score + (int)(timeRest/100);
		scoreView.setText(Long.toString(score));
	}

	private void loadNextGame () {
		gameContent.removeAllViews();
		GameView game = games.get(currentGame);
		gameContent.addView(game);
		currentGame ++;
		gameTime = game.getTime();
		this.timeView.setText(format.format(new Date(gameTime)));
		this.timeView.setTag(gameTime);
		this.gameTitle.setText(game.getTitle());
		this.gameNumber.setGameNumber(currentGame);
	}

	private void updateTime () {
		if (!isTimeStopped()) {
			long time = (Long) this.timeView.getTag();
			if (time <= 0) {
				//TODO Mostrar mensaje de fin de juego por timeout.
				this.OnGameOver();
				return;
			}
			time = time - 1000;
			this.timeView.setText(format.format(new Date(time)));
			this.timeView.setTag(time);
			timeChanger.postDelayed(timeUpdater, 1000);
		}
	}

	private void goToRanking() {
		Intent i = new Intent(GameActivity.this, RankingActivity.class);
		i.putExtra(Constants.AREA_KEY, GameActivity.this.area);
		i.putExtra(Constants.LEVEL_KEY, GameActivity.this.level);
		i.putExtra(Constants.SCORE_KEY, GameActivity.this.score);
		startActivity(i);
	}

}
