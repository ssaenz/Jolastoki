package eu.lapecera.jolastoki.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class AudioTrackSoundPlayer
{
	
	public interface OnAudioTrackEndListener {
		public void onAudioTrackEnd();
	}
	
	private HashMap<String, LoopPlayThread> threadMap = null;
	private Context context;
	
	private static AudioTrackSoundPlayer instance;

	private AudioTrackSoundPlayer(Context context)
	{
		this.context = context;
		threadMap = new HashMap<String, AudioTrackSoundPlayer.LoopPlayThread>();
	}
	
	public static synchronized AudioTrackSoundPlayer getInstance (Context context) {
		if (instance == null)
			instance = new AudioTrackSoundPlayer(context);
		return instance;
	}
	
	public void singlePlay (Context context, String fileName, OnAudioTrackEndListener audioTrackEndListener) {
		SinglePlayThread thread = new SinglePlayThread(context, fileName, audioTrackEndListener);
		thread.start();
	}

	public void playTrack(String fileName)
	{
		if (!isTrackPlaying(fileName))
		{
			LoopPlayThread thread = new LoopPlayThread(fileName);
			thread.start();
			threadMap.put(fileName, thread);
		}
	}

	public void stopTrack(String fileName)
	{
		LoopPlayThread thread = threadMap.get(fileName);
		if (thread != null)
		{
			thread.requestStop();
			threadMap.remove(fileName);
		}
	}

	public boolean isTrackPlaying(String fileName)
	{
		return threadMap.containsKey(fileName);
	}
	

	private class LoopPlayThread extends Thread
	{
		String fileName;
		boolean stop = false;
		AudioTrack audioTrack = null;

		public LoopPlayThread(String fileName)
		{
			super();
			this.fileName = fileName;
		}

		public void run()
		{
			try
			{
				String path = fileName;

				AssetManager assetManager = context.getAssets();
				AssetFileDescriptor ad = assetManager.openFd(path);
				long fileSize = ad.getLength();
				int bufferSize = 4096;
				byte[] buffer = new byte[bufferSize];

				audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 22050, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);
				audioTrack.play();

				InputStream audioStream = null;

				int headerOffset = 0x2C;
				long bytesWritten = 0;
				int bytesRead = 0;

				while (!stop) // loop sound
				{
					audioStream = assetManager.open(path);
					bytesWritten = 0;
					bytesRead = 0;

					audioStream.read(buffer, 0, headerOffset);

					// read until end of file
					while (!stop && bytesWritten < fileSize - headerOffset)
					{
						bytesRead = audioStream.read(buffer, 0, bufferSize);
						bytesWritten += audioTrack.write(buffer, 0, bytesRead);
					}
				}

				audioTrack.stop();
				audioTrack.release();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		public synchronized void requestStop()
		{
			stop = true;
		}
	}
	
	private class SinglePlayThread extends Thread
	{
		private String fileName;
		private AudioTrack audioTrack = null;
		private OnAudioTrackEndListener audioTrackEndListener;
		private Context context;

		public SinglePlayThread(Context context, String fileName, OnAudioTrackEndListener audioTrackEndListener)
		{
			super();
			this.fileName = fileName;
			this.audioTrackEndListener = audioTrackEndListener;
		}

		public void run()
		{
			try
			{
				String path = fileName;

				AssetManager assetManager = SinglePlayThread.this.context.getAssets();
				AssetFileDescriptor ad = assetManager.openFd(path);
				long fileSize = ad.getLength();
				int bufferSize = 4096;
				byte[] buffer = new byte[bufferSize];

				audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 22050, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);
				audioTrack.play();

				InputStream audioStream = null;

				int headerOffset = 0x2C;
				long bytesWritten = 0;
				int bytesRead = 0;

				audioStream = assetManager.open(path);
				bytesWritten = 0;
				bytesRead = 0;

				audioStream.read(buffer, 0, headerOffset);

				// read until end of file
				while (bytesWritten < fileSize - headerOffset)
				{
					bytesRead = audioStream.read(buffer, 0, bufferSize);
					bytesWritten += audioTrack.write(buffer, 0, bytesRead);
				}

				audioTrack.stop();
				audioTrack.release();
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			} finally {
				if (this.audioTrackEndListener != null) {
					this.audioTrackEndListener.onAudioTrackEnd();
				}
			}

		}

	}
}
