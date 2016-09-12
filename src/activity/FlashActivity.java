package activity;

import com.leo.androidtest.MainActivity;

import android.content.Intent;
import android.view.WindowManager;
import base.activity.BaseActivity;
import util.data.ConfigUtil.FlashActivityConfig;

public class FlashActivity extends BaseActivity implements OnSysTime{

	@Override
	public int getRootViewId() {
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);//Òþ²Ø×´Ì¬À¸
		return FlashActivityConfig.LAYOUT_ID;
	}

	@Override
	public void initData() {
		SysTime sys = new SysTime(this,1);
		sys.onTime();
	}


	class SysTime{
		private OnSysTime onSysTime;
		private int time;
		public SysTime(OnSysTime onSysTime,int time) {
			this.onSysTime = onSysTime;
			this.time = time;
		}
		public void onTime(){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(time*1000);
						onSysTime.sysTime();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}


	@Override
	public void sysTime() {
		startActivity(new Intent(this, MainActivity.class));
		super.finish();
	}

}

interface OnSysTime{
	void sysTime();
};
