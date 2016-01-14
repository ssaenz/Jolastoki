package eu.lapecera.jolastoki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.common.BaseActivity;


public class IntroActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_intro);
		View view = findViewById(R.id.image_background);
		view.setOnClickListener(clickListener);

	}

	@Override
	public void onBackPressed() {
		BaseActivity.setFinish(true);
		finish();
	}

	private AudibleOnClickListener clickListener = new AudibleOnClickListener(this, R.raw.seleccion) {

		@Override
		public void onAudibleClick(View v) {
			Intent i = new Intent(IntroActivity.this, PortadaActivity.class);
			startActivity(i);

		}
	};

}
