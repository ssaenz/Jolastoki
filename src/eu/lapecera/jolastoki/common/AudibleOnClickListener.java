package eu.lapecera.jolastoki.common;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import eu.lapecera.jolastoki.util.MusicManager;

public abstract class AudibleOnClickListener implements OnClickListener {
	
	private Context context;
	private int resid;
	
	public AudibleOnClickListener(Context context, int resid) {
		this.context = context;
		this.resid = resid;
	}
	@Override
	public void onClick(View v) {
		MusicManager.playSingle(this.context, this.resid);
		onAudibleClick(v);
	}
	
	public abstract void onAudibleClick (View v);

}
