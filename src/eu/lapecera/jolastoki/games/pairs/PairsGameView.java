package eu.lapecera.jolastoki.games.pairs;

import java.util.HashMap;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.common.AudibleOnClickListener;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.config.PairsGameViewConfig;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.util.MusicManager;

public class PairsGameView extends GameView {

	private HashMap<Integer, Integer> texts;
	private HashMap<Integer, Integer> answers;

	private View selectedButton = null;

	private int pairsFound = 0;

	private boolean gameOver = false;

	public PairsGameView(Context context, GameViewConfig config) {
		super(context, config);
	}

	@Override
	protected void onCreateView(GameViewConfig config) {

		this.texts = (HashMap<Integer, Integer>) ((PairsGameViewConfig)config).getTexts();
		this.answers = (HashMap<Integer, Integer>) ((PairsGameViewConfig)config).getAnswers();

		for (Integer id : texts.keySet()) {
			Button btn = (Button) this.findViewById(id);
			btn.setText(texts.get(id));
			btn.setOnClickListener(new AudibleOnClickListener(PairsGameView.this.getContext(), R.raw.seleccion) {

				@Override
				public void onAudibleClick(View view) {

					if (!gameOver) {
						final View v = view;
						if (selectedButton == null) {
							selectedButton = v;
							selectedButton.setSelected(true);
						} else {
							if (answers.get(selectedButton.getId()).equals(v.getId())) {
								selectedButton.setBackgroundResource(R.drawable.charca_1_bt_acierto);
								v.setBackgroundResource(R.drawable.charca_1_bt_acierto);
								MusicManager.playSingle(getContext(), R.raw.acierto);
								selectedButton = null;
								pairsFound ++;
								if (pairsFound == 3 && getGameOverListener() != null) {
									getGameOverListener().OnGameOver();
								}
							} else {
								v.setBackgroundResource(R.drawable.charca_1_bt_fallo);
								MusicManager.playSingle(getContext(), R.raw.fallo);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										v.setBackgroundResource(R.drawable.charca_bt);
									}
								}, 1000);
							}
						}
					}
				}
			});
		}

	}



}
