package eu.lapecera.jolastoki.common;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class AudibleOnClickListener implements OnClickListener {
	
	private Context context;
	private int resid;
	
	public AudibleOnClickListener(Context context, int resid) {
		this.context = context;
		this.resid = resid;
	}
	@Override
	public void onClick(View v) {
		MediaPlayer mp = MediaPlayer.create(this.context, resid);
		mp.setLooping(false);
		onAudibleClick(v);
		mp.start();
	}
	
	public abstract void onAudibleClick (View v);

}
