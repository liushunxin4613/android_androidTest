package util.delete;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {

	private OnTime onTime;

	public TimerUtil(OnTime onTime) {
		this.onTime = onTime;
	}

	public void start(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				onTime.onTime();
			}
		}, 2000);
	}

	public interface OnTime{
		void onTime();
	}
}

