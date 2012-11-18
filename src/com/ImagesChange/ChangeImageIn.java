package com.ImagesChange;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;

public class ChangeImageIn {
	public interface TimersCallback {
		public void updateImage();
	}

	private TimersCallback timersCallback;
	private long seconds;
	private Timer timer;
	private TimerTask timerTask;
	public final int AA = 0011;

	public ChangeImageIn() {
		// TODO Auto-generated constructor stub
		this.timer = new Timer();
	}

	public void ChangeImage(long seconds, TimersCallback callback) {
		
		timersCallback = callback;
		this.timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				timersCallback.updateImage();
			}
		};
		timer.schedule(timerTask, 1, seconds * 1000);
	}

	public void timeout() throws InterruptedException {
		timerTask.cancel();
	}

	public void stop(){
		timer.cancel();
	}

}