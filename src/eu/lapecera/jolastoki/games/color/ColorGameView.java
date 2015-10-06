package eu.lapecera.jolastoki.games.color;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.config.ColorGameViewConfig;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.widget.MultiLayerView;
import eu.lapecera.jolastoki.widget.MultiLayerView.OnCompletedColorsListener;
import eu.lapecera.jolastoki.widget.MultiLayerView.OnRegionClickListener;

public class ColorGameView extends GameView implements OnClickListener, OnCompletedColorsListener, OnRegionClickListener {

	public ColorGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	private MultiLayerView interactiveView;

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
		interactiveView = (MultiLayerView) findViewById(R.id.interactiveView);
		interactiveView.setLastLayerDrawable(getResources().getDrawable(((ColorGameViewConfig) config).getLastLayer()));
		interactiveView.setOnCompletedColorListener(this);
		interactiveView.setOnRegionClickListener(this);
	}
	
	@Override
	protected void onDetachedFromWindow() {
		interactiveView.recycle();
		super.onDetachedFromWindow();
	}

	@Override
	public void onClick(View v) {
		if (selectedColor != null)
			selectedColor.setChecked(false);
		selectedColor = (ToggleButton)v;
		selectedColor.setChecked(true);
		
	}

	@Override
	public void onCompletedColor() {
		if (getGameOverListener() != null) {
			getGameOverListener().OnGameOver();
		}
	}

	@Override
	public void onRegionClick(int region) {
		int intValue = Integer.parseInt((String)selectedColor.getTag());
		Log.i(this.getClass().getName(), "Tag: " + intValue + ", region: " + region);
		if (intValue == region)
			interactiveView.addFilter(region);
	}

}
