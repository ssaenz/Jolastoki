package eu.lapecera.jolastoki.games.color;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.ColorGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.widget.InteractiveView;

public class ColorGameView extends GameView implements OnClickListener {

	public ColorGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	private InteractiveView interactiveView;

	private static final String ASSETS_DIR = "pictures";
	private File mDir;

	private List<Integer> buttons;
	private Map<Integer, String> buttonTexts;

	@Override
	protected void onCreateView(GameViewConfig config) {
		buttons = ((ColorGameViewConfig) config).getButtons();
		buttonTexts = ((ColorGameViewConfig) config).getButtonTexts();

		for (Integer buttonId : buttons) {
			Button button = (Button) findViewById(buttonId);
			button.setOnClickListener(this);
			button.setText(buttonTexts.get(buttonId));
		}

		mDir = new File( getContext().getExternalCacheDir(), ASSETS_DIR);

		// Copy files to assets
		copyFromAssets();

		// Filter files
		final File[] files = mDir.listFiles();
		// Add view to the container
		interactiveView = new InteractiveView(getContext());
		interactiveView.setRegionFiles(files);

		final ViewGroup container = (ViewGroup) findViewById(R.id.content);
		container.addView(interactiveView);
	}

	@Override
	public void onClick(View v) {
		int region = buttons.indexOf(v.getId()) + 1;
		interactiveView.setEnabledRegion( region );
		Log.i("ColorGameView", "tag: " + v.getTag());
		try {
			int colorParsed = Color.parseColor((String)v.getTag());
			Log.v("ColorGameView", "color: " + colorParsed);
			int color = getContext().getResources().getColor(R.color.mercado_cielo);
			interactiveView.setColorSelection( colorParsed );
		} catch (Exception e) {
			Log.e("ColorGameView", e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void copyFromAssets() {
		if ( mDir.exists() ) return; 

		// Create dir
		mDir.mkdirs();

		// Copy files from assets
		try {
			String[] names = getContext().getAssets().list(ASSETS_DIR);
			for (String name : names ) {
				InputStream is = getContext().getAssets().open(ASSETS_DIR + File.separator + name);

				File outFile = new File(mDir, name);
				FileOutputStream fos = new FileOutputStream(outFile);

				byte[] buffer = new byte[1024];
				int read;
				while ( (read = is.read(buffer)) != -1 ) {
					fos.write(buffer, 0, read);
				}

				is.close();
				fos.close();
			} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
