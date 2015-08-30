package eu.lapecera.jolastoki.common;

import android.app.Activity;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.util.MusicManager;

public class BaseActivity extends Activity {
	
//	private static final String TAG = "BaseActivity";
	
	@Override
	public void onBackPressed() {
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		MusicManager.pause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MusicManager.start(this, R.raw.musica_fondo);
	}

}
