package activity;

import com.leo.androidtest.MainActivity;

import android.content.Intent;
import android.view.WindowManager;
import base.activity.BaseActivity;
import util.data.ConfigUtil.FlashActivityConfig;
import util.delete.TimerUtil;
import util.delete.TimerUtil.OnTime;;

public class FlashActivity extends BaseActivity implements OnTime{

	@Override
	public int getRootViewId() {
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);//Òþ²Ø×´Ì¬À¸
		return FlashActivityConfig.LAYOUT_ID;
	}

	@Override
	public void initData() {
		new TimerUtil(this).start();
	}
	
	@Override
	public void onTime() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}