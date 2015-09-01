package eu.lapecera.jolastoki;

import android.content.Intent;
import android.os.Bundle;
import eu.lapecera.jolastoki.common.BaseActivity;
import eu.lapecera.jolastoki.common.Constants;
import eu.lapecera.jolastoki.domain.GameArea;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.util.MusicManager;
import eu.lapecera.jolastoki.widget.AnimationView;
import eu.lapecera.jolastoki.widget.AnimationView.OnAnimationCompleteListener;

public class GameOverActivity extends BaseActivity {
	

	private GameLevel level;
	private GameArea area;
	private Integer score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gameover);
		Bundle extras = getIntent().getExtras();
		level = (GameLevel) extras.get(Constants.LEVEL_KEY);
		area = (GameArea) extras.get(Constants.AREA_KEY);
		score = (Integer) extras.get(Constants.SCORE_KEY);
		
		AnimationView animationView = (AnimationView) findViewById(R.id.motivation_animation);
		animationView.setOnAnimationCompleteListener(new OnAnimationCompleteListener() {
			
			@Override
			public void onAnimationComplete() {
				goToRanking();
			}
		});
		MusicManager.playSingle(this, R.raw.motivacion);
	}


	private void goToRanking() {
		Intent i = new Intent(GameOverActivity.this, RankingActivity.class);
		i.putExtra(Constants.AREA_KEY, GameOverActivity.this.area);
		i.putExtra(Constants.LEVEL_KEY, GameOverActivity.this.level);
		i.putExtra(Constants.SCORE_KEY, GameOverActivity.this.score);
		startActivity(i);
	}

}
