package eu.lapecera.jolastoki.config;

import android.content.Context;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;

public interface GameViewConfig {
	
	public long getTime ();
	public int getLayout ();
	public int getTitle();
	public GameLevel getLevel();
	public GameView getGameView(Context context);

}
