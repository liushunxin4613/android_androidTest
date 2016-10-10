package base.activity;

import util.VolleyUtil;

import com.leo.androidtest.MyApplication;

import inter.AcFmInterface;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class BaseActivity extends Activity implements AcFmInterface{

	public static String TAG;
	private MyApplication application;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application = (MyApplication) getApplication();
		TAG = getClass().getName();
		initRootView();
		if (getRootViewId() != 0) {
			setContentView(getRootViewId());
		}
		initView();
		initData();
	}

	public VolleyUtil getVolleyUtil(){
		return application.getUtil();
	}

	@Override
	public void initRootView() {
	}

	@Override
	public int getRootViewId() {
		return 0;
	}

	@Override
	public void initView() {
	}

	@Override
	public void initData() {
	}

	public DisplayMetrics getDisplayMetrics(){
		WindowManager wm = getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

}
