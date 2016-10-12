package base.activity;

import inter.AcFmInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity implements AcFmInterface{

	public static String TAG;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = getClass().getName();
		initRootView();
		if (getRootViewId() != 0) {
			setContentView(getRootViewId());
		}
		initView();
		initData();
	}

	@Override
	public void initRootView() {
	}

	@Override
	public Integer getRootViewId(){
		return null;
	};
	
	@Override
	public void initView() {
	}

	@Override
	public void initData() {
	}
	
	
}
