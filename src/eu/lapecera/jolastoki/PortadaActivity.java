package eu.lapecera.jolastoki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.common.BaseActivity;


public class PortadaActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_portada);
		View background = findViewById(R.id.background_front);
		background.setOnClickListener(clickListener);

	}
	
	private AudibleOnClickListener clickListener = new AudibleOnClickListener(this, R.raw.seleccion) {
		
		@Override
		public void onAudibleClick(View v) {
			switch (v.getId()) {
			case R.id.background_front:
				Intent i = new Intent(PortadaActivity.this, AreaActivity.class);
				startActivity(i);
				break;

			default:
				break;
			}

		}
	};

}
