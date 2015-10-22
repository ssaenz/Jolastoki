package eu.lapecera.jolastoki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.common.BaseActivity;
import eu.lapecera.jolastoki.util.MusicManager;


public class PortadaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().getExtras() != null && getIntent().getExtras().get("exit") !=null ) {
			BaseActivity.setFinish(true);
			finish();
		}
		setContentView(R.layout.activity_portada);
		View button = findViewById(R.id.exit_btn);
		button.setOnClickListener(clickListener);
		button = findViewById(R.id.play_btn);
		button.setOnClickListener(clickListener);

	}
	
	private AudibleOnClickListener clickListener = new AudibleOnClickListener(this, R.raw.seleccion) {
		
		@Override
		public void onAudibleClick(View v) {
			switch (v.getId()) {
			case R.id.play_btn:
				Intent i = new Intent(PortadaActivity.this, AreaActivity.class);
				startActivity(i);
				break;
			case R.id.exit_btn:
				BaseActivity.setFinish(true);
				finish();
				break;

			default:
				break;
			}

		}
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		MusicManager.pause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		BaseActivity.setFinish(false);
		MusicManager.start(this, R.raw.musica_fondo);
	}

}
