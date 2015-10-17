package eu.lapecera.jolastoki;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import eu.lapecera.jolastoki.util.MusicManager;

public class InstructionsActivity extends Activity implements OnClickListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
		
		View button = findViewById(R.id.close_button);
		button.setOnClickListener(this);
		
		TextView textView = (TextView) findViewById(R.id.instructionsText);
		textView.setText(Html.fromHtml(getResources().getText(R.string.texto_instrucciones).toString()));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.close_button:
			finish();
			break;

		default:
			break;
		}
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
