package eu.lapecera.jolastoki.common;

import android.app.Activity;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.util.MusicManager;

public class BaseActivity extends Activity {
	
	private static boolean finish = false;
	
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
		if (BaseActivity.finish) {
			finish();
			return;
		}
		MusicManager.start(this, R.raw.musica_fondo);
	}
	
	public static void setFinish (Boolean finish) {
		BaseActivity.finish = finish;
	}

}
