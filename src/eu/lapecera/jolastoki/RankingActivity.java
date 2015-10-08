package eu.lapecera.jolastoki;

import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.common.BaseActivity;
import eu.lapecera.jolastoki.common.Constants;
import eu.lapecera.jolastoki.common.ScoreAdapter;
import eu.lapecera.jolastoki.database.DatabaseHandler;
import eu.lapecera.jolastoki.domain.GameArea;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.domain.Score;

public class RankingActivity extends BaseActivity implements OnClickListener {

	private GameLevel level;
	private GameArea area;
	private Integer score;
	
	private ListView scoreList;
	
	private LinearLayout rankingContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);

		Bundle extras = getIntent().getExtras();
		level = (GameLevel) extras.get(Constants.LEVEL_KEY);
		area = (GameArea) extras.get(Constants.AREA_KEY);
		score = (Integer) extras.get(Constants.SCORE_KEY);
		
		TextView levelView = (TextView) findViewById(R.id.header_icon_level);
		levelView.setText(Integer.toString(level.ordinal() + 1));

		scoreList = (ListView) findViewById(R.id.ranking_list);
		
		rankingContainer = (LinearLayout) findViewById(R.id.ranking_container);

		Button goBackBtn = (Button) findViewById(R.id.go_back_btn);
		goBackBtn.setOnClickListener(this);
		showScore();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go_back_btn:
			Intent i = new Intent(RankingActivity.this, AreaActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}
	
	private void showScore () {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_score);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setCanceledOnTouchOutside(false);
		Button accept = (Button) dialog.findViewById(R.id.accept);

		final EditText scoreInput = (EditText) dialog.findViewById(R.id.score_name);
		TextView scoreText = (TextView) dialog.findViewById(R.id.score);
		scoreText.setText(Integer.toString(score));

		accept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = scoreInput.getText().toString();
				Score score = new Score();
				score.setArea(area.name());
				score.setLevel(level.name());
				score.setName(name);
				score.setScore(RankingActivity.this.score);
				DatabaseHandler db = new DatabaseHandler(RankingActivity.this);
				db.insertScore(score);
				dialog.dismiss();
			}
		});

		dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				DatabaseHandler db = new DatabaseHandler(RankingActivity.this);
				List<Score> ranking = db.getScore(area, level);
				ScoreAdapter adapter = new ScoreAdapter(RankingActivity.this, ranking);
				scoreList.setAdapter(adapter);
				rankingContainer.setVisibility(View.VISIBLE);
			}
		});
		
		dialog.show();
	}
	
}
