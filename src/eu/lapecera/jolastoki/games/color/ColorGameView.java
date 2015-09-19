package eu.lapecera.jolastoki.games.color;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.ColorGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.widget.InteractiveView;
import eu.lapecera.jolastoki.widget.InteractiveView.OnCompletedColorsListener;

public class ColorGameView extends GameView implements OnClickListener, OnCompletedColorsListener {

	public ColorGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	private InteractiveView interactiveView;

	private List<Integer> buttons;
	private Map<Integer, String> buttonTexts;
	private ToggleButton selectedColor;

	@Override
	protected void onCreateView(GameViewConfig config) {
		buttons = ((ColorGameViewConfig) config).getButtons();
		buttonTexts = ((ColorGameViewConfig) config).getButtonTexts();

		for (Integer buttonId : buttons) {
			ToggleButton button = (ToggleButton) findViewById(buttonId);
			button.setOnClickListener(this);
			button.setText(buttonTexts.get(buttonId));
			button.setTextOn(buttonTexts.get(buttonId));
			button.setTextOff(buttonTexts.get(buttonId));
		}
		interactiveView = (InteractiveView) findViewById(R.id.interactiveView);
		interactiveView.setOnCompletedColorListener(this);
	}

	@Override
	public void onClick(View v) {
		if (selectedColor != null)
			selectedColor.setChecked(false);
		selectedColor = (ToggleButton)v;
		selectedColor.setChecked(true);
		
		int region = buttons.indexOf(v.getId()) + 1;
		interactiveView.setEnabledRegion( region );
		int color = Color.parseColor((String)v.getTag());
		interactiveView.setColorSelection( color );

	}

	@Override
	public void onCompletedColor() {
		if (getGameOverListener() != null)
			getGameOverListener().OnGameOver();
	}

}
