package activity;

import com.leo.androidtest.InitService;
import com.leo.androidtest.MainActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.WindowManager;
import base.activity.BaseActivity;
import util.ReceiveUtil;
import util.ReceiveUtil.ToReceive;
import util.data.ConfigUtil.FlashActivityConfig;
import util.delete.TimerUtil;
import util.delete.TimerUtil.OnTime;;

public class FlashActivity extends BaseActivity implements OnTime,ToReceive{

	@Override
	public int getRootViewId() {
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);//Òþ²Ø×´Ì¬À¸
		return FlashActivityConfig.LAYOUT_ID;
	}

	private ReceiveUtil receiveUtil;
	
	@Override
	public void initData() {
		receiveUtil = new ReceiveUtil(this, this);
		Intent intent = new Intent(this, InitService.class);
		intent.putExtra(InitService.KEY_START_SERVICE_FOR, InitService.TASK_LOGIN);
		startService(intent);
		new TimerUtil(this).start(5000);
	}
	
	private boolean is;
	
	@Override
	public void onTime() {
		if (!is) {
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}
	}
	
	@Override
	protected void onDestroy() {
		receiveUtil.unregisterReceiver();
		super.onDestroy();
	}

	public static final String ACTION_STRAT_SUCCESS = "actionStartSuccess";
	public static final String ACTION_STRAT_ERROR = "actionStartError";
	
	@Override
	public IntentFilter getIntentFilter() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_STRAT_SUCCESS);
		filter.addAction(ACTION_STRAT_ERROR);
		return filter;
	}

	@Override
	public void toReceive(String action) {
		if (action.equals(ACTION_STRAT_SUCCESS)) {
			is = true;
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}else if (action.equals(ACTION_STRAT_ERROR)) {
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}
	}

}