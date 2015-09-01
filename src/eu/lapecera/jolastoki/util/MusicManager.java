package eu.lapecera.jolastoki.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class MusicManager {
	
	private static final String TAG = "MusicManager";
	private static MediaPlayer mediaPlayer;
	
	public static void playSingle (Context context, int resource) {
		MediaPlayer mp = MediaPlayer.create(context, resource);
		mp.setLooping(false);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
	}

	public static void start(Context context, int music) {
		start(context, music, false);
	}

	public static void start(Context context, int music, boolean force) {
		if (mediaPlayer != null) {
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		} else {
			mediaPlayer = MediaPlayer.create(context, music);
			if (mediaPlayer == null) {
				Log.e(TAG, "player was not created successfully");
			} else {
				try {
					mediaPlayer.setLooping(true);
					mediaPlayer.start();
				} catch (Exception e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
		}
	}

	public static void pause() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	public static void release() {
		try {
			if (mediaPlayer != null) {
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
				}
				mediaPlayer.release();
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
}
