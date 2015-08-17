package eu.lapecera.jolastoki.games;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import eu.lapecera.jolastoki.config.GameViewConfig;
import eu.lapecera.jolastoki.games.quiz.OnGameOverListener;

public abstract class GameView extends RelativeLayout {
	
	private long time;
	private int layout;
	private int title;
	
	private OnGameOverListener gameOverListener;
	
	public GameView(Context context, GameViewConfig config) {
		super(context);
		this.time = config.getTime();
		this.layout = config.getLayout();
		this.title = config.getTitle();
		onCreateView(config);
	}
	
	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	private GameView(Context context) {
		super(context);
	}
	
	private GameView(Context context, AttributeSet attr) {
		super(context, attr);
	}
	
	private GameView(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
	}

	public void setOnGameOverListener(OnGameOverListener gameOverListener) {
		this.gameOverListener = gameOverListener;
	}

	protected OnGameOverListener getGameOverListener( ) {
		return this.gameOverListener;
	}
	
	protected abstract void onCreateView (GameViewConfig config);
	
}
