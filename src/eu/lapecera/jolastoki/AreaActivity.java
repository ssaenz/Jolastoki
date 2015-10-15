package eu.lapecera.jolastoki;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.common.BaseActivity;
import eu.lapecera.jolastoki.common.Constants;
import eu.lapecera.jolastoki.domain.GameArea;
import eu.lapecera.jolastoki.domain.GameLevel;

public class AreaActivity extends BaseActivity {

	GameLevel level = GameLevel.ONE;
	Dialog dialog;

	private GameArea selectedArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area);

		ImageButton btn = (ImageButton) findViewById(R.id.mercado_area_btn);
		btn.setOnClickListener(clickListener);
		btn.setTag(GameArea.MERCADO);

		btn = (ImageButton) findViewById(R.id.charca_area_btn);
		btn.setOnClickListener(clickListener);
		btn.setTag(GameArea.CHARCA);

		btn = (ImageButton) findViewById(R.id.parque_area_btn);
		btn.setOnClickListener(clickListener);
		btn.setTag(GameArea.PARQUE);

		btn = (ImageButton) findViewById(R.id.colegio_area_btn);
		btn.setOnClickListener(clickListener);
		btn.setTag(GameArea.BIBLIOTECA);

		btn = (ImageButton) findViewById(R.id.instructions_btn);
		btn.setOnClickListener(clickListener);

		btn = (ImageButton) findViewById(R.id.go_back_btn);
		btn.setOnClickListener(clickListener);
	}

	private AudibleOnClickListener clickListener = new AudibleOnClickListener(this, R.raw.seleccion) {

		@Override
		public void onAudibleClick(View v) {
			switch (v.getId()) {
			case R.id.go_back_btn:
				Intent backIntent = new Intent (AreaActivity.this, PortadaActivity.class);
				startActivity(backIntent);
				break;
			case R.id.instructions_btn:
				break;
			case R.id.mercado_area_btn:
			case R.id.charca_area_btn:
			case R.id.parque_area_btn:
			case R.id.colegio_area_btn:
				AreaActivity.this.selectedArea = (GameArea) v.getTag();
				AreaActivity.this.showLevelDialog();
				break;
			case R.id.level_one_btn:
			case R.id.level_two_btn:
			case R.id.level_three_btn:
				if (selectedArea.getGames().length == 0) {
					Toast.makeText(AreaActivity.this, "Comming soon", Toast.LENGTH_SHORT).show();
					return;
				}
				AreaActivity.this.dialog.dismiss();
				Intent i = new Intent (AreaActivity.this, GameActivity.class);
				i.putExtra(Constants.AREA_KEY, AreaActivity.this.selectedArea);
				i.putExtra(Constants.LEVEL_KEY, (GameLevel) v.getTag());
				startActivity(i);
				break;
			}
		}
	};

	private void showLevelDialog () {
		if (this.dialog == null){
			this.dialog = new Dialog(this);
			this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			this.dialog.setContentView(R.layout.dialog_level);

			Button levelBtn = (Button) this.dialog.findViewById(R.id.level_one_btn);
			levelBtn.setTag(GameLevel.ONE);
			levelBtn.setOnClickListener(clickListener);

			levelBtn = (Button) this.dialog.findViewById(R.id.level_two_btn);
			levelBtn.setTag(GameLevel.TWO);
			levelBtn.setOnClickListener(clickListener);

			levelBtn = (Button) this.dialog.findViewById(R.id.level_three_btn);
			levelBtn.setTag(GameLevel.THREE);
			levelBtn.setOnClickListener(clickListener);
		}
		this.dialog.show();
	}
	
	@Override
	protected void onPause() {
		if (this.dialog != null) {
			dialog.dismiss();
		}
		super.onPause();
	}

}
