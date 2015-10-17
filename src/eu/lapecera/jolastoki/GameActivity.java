package eu.lapecera.jolastoki;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import eu.lapecera.jolastoki.common.BaseActivity;
import eu.lapecera.jolastoki.common.Constants;
import eu.lapecera.jolastoki.common.OnGameOverListener;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.domain.GameArea;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.widget.GameNumber;

public class GameActivity extends BaseActivity implements OnGameOverListener {



	private static final int UPDATE_TIME = 1;
	private static final int ANIMATE_TIME = 2;
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

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

	private Handler handler = new TimeHandler();

	private boolean timeStopped = false;

	private Dialog timeoutDialog;
	
	private Dialog exitDialog;

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
		handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);

	}

	/**
	 * Carga el siguiente juego si todavía quedan o muestra el escore final.
	 */
	@Override
	public void OnGameOver() {
		startScoreAnimation ();
	}

	@Override
	public void OnStopTime() {
		stopTime();
	}
	
	@Override
	protected void onPause() {
		clearHandler();
		super.onPause();
	}
	
	@Override
	public void onBackPressed() {
		showExitDialog();
	}
	
	private synchronized void stopTime() {
		this.timeStopped = true;
	}

	private synchronized boolean isTimeStopped() {
		return this.timeStopped;
	}

	private synchronized void playTime() {
		this.timeStopped = false;
	}

	private void startScoreAnimation () {
		long timeRest = (Long) this.timeView.getTag();
		Message msg = new Message();
		msg.what = ANIMATE_TIME;
		Bundle data = new Bundle();
		data.putLong("time", timeRest/100);
		msg.setData(data);
		handler.sendMessage(msg);
	}

	private void loadNextGame () {
		gameContent.removeAllViews();
		GameView game = games.get(currentGame);
		gameContent.addView(game);
		currentGame ++;
		gameTime = game.getTime();
		this.timeView.setText(timeFormat.format(new Date(gameTime)));
		this.timeView.setTag(gameTime);
		this.gameTitle.setText(game.getTitle());
		this.gameNumber.setGameNumber(currentGame);
		playTime();
	}

	private void showTimeoutDialog() {
		if (timeoutDialog == null) {

			timeoutDialog = new Dialog(this);
			timeoutDialog.setContentView(R.layout.dialog_timeout);
			timeoutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			timeoutDialog.setCanceledOnTouchOutside(false);

			Button accept = (Button) timeoutDialog.findViewById(R.id.accept);

			accept.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					GameActivity.this.OnGameOver();
					timeoutDialog.dismiss();
				}
			});
		}

		timeoutDialog.show();
	}
	
	private void showExitDialog () {
		if (exitDialog == null) {
			exitDialog = new Dialog(this);
			exitDialog.setContentView(R.layout.dialog_exit);
			exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			exitDialog.setCanceledOnTouchOutside(false);

			exitDialog.findViewById(R.id.play_again_btn).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					clearHandler();
					Intent playAgainIntent = new Intent(GameActivity.this, AreaActivity.class);
					startActivity(playAgainIntent);
				}
			});
			
			exitDialog.findViewById(R.id.exit_btn).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					clearHandler();
					Intent exitIntent = new Intent(GameActivity.this, PortadaActivity.class);
					startActivity(exitIntent);
				}
			});
		}
		
		if (!exitDialog.isShowing()) {
			exitDialog.show();
		}
	}

	private void goToGameOverActivity () {
		clearHandler();
		Intent i = new Intent(GameActivity.this, GameOverActivity.class);
		i.putExtra(Constants.AREA_KEY, GameActivity.this.area);
		i.putExtra(Constants.LEVEL_KEY, GameActivity.this.level);
		i.putExtra(Constants.SCORE_KEY, GameActivity.this.score);
		startActivity(i);
	}
	
	private void clearHandler () {
		handler.removeMessages(ANIMATE_TIME);
		handler.removeMessages(UPDATE_TIME);
		
	}

	private class TimeHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TIME:
				if (!isTimeStopped()) {
					long time = (Long) timeView.getTag();
					if (time <= 0) {
						showTimeoutDialog();
						return;
					}
					time = time - 1000;
					timeView.setText(timeFormat.format(new Date(time)));
					timeView.setTag(time);
				}
				this.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
				break;
				
			case ANIMATE_TIME:
				long count = msg.getData().getLong("time");
				handler.postDelayed(new CountDown(count), 1);
				break;

			default:
				break;
			}
		}
	}

	private class CountDown implements Runnable {

		private Long count;

		public CountDown (Long count) {
			this.count = count;
		}

		@Override
		public void run() {
			score = score + 5;
			count = count - 5;
			timeView.setText(timeFormat.format(new Date(count * 100)));
			scoreView.setText(Long.toString(score));
			if (count <= 0) {
				if (currentGame < games.size()) {
					loadNextGame();
				} else {
					goToGameOverActivity();
				}
			} else {
				handler.postDelayed(this, 1);
			}

		}

	}

}
