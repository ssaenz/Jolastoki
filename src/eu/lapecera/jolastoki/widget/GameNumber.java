package eu.lapecera.jolastoki.widget;

import eu.lapecera.jolastoki.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameNumber extends LinearLayout {

	private static final int DEFAULT_GAME_NUMBER = 1;
	private static final int DEFAULT_MAX_NUMBER = 3;

	private int maxNumber = DEFAULT_MAX_NUMBER;	
	private int gameNumber = DEFAULT_GAME_NUMBER;

	public GameNumber(Context context, AttributeSet attrs) {
		super(context, attrs);
		setGameNumber (gameNumber);
	}

	public void setGameNumber (int gameNumber) {
		this.gameNumber = gameNumber;
		this.removeAllViews();
		this.removeAllViewsInLayout();
		for (int i = 0; i < this.gameNumber; i ++) {
			ImageView iv = new ImageView(getContext());
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			iv.setImageResource(eu.lapecera.jolastoki.R.drawable.cabecera_juegos_ico_partida);
			this.addView(iv, params);
		}
		
		for (int i = this.gameNumber; i < this.maxNumber; i ++) {
			ImageView iv = new ImageView(getContext());
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			iv.setImageResource(R.drawable.cabecera_juegos_ico_partida_base);
			this.addView(iv, params);
		}
		this.invalidate();

	}

}
