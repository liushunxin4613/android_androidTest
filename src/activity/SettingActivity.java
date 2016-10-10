package activity;


import util.data.ConfigUtil.SettingActivityConfig;
import util.data.DataUtil;
import util.data.ConfigUtil.IncludeConfig;
import util.ui.IncludeUtil;

import com.leo.androidtest.R;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import base.activity.BaseActionBarCenterActivity;

public class SettingActivity extends BaseActionBarCenterActivity implements OnClickListener{

	@Override
	public int getRootViewId() {
		return R.layout.activity_setting;
	}

	private TextView nextTv;
	private TextView tv;

	public static final int NEXT_VIEW_ID = 10010;
	public static final int TV_VIEW_ID = 10011;

	@Override
	public void initView() {
		super.initView();

		nextTv = (TextView) IncludeUtil.findViewById(this,getWindow(), 
				SettingActivityConfig.INCLUDE_NEXT_ID, IncludeConfig.NEXT_TV_TV_ARR_ID[0],NEXT_VIEW_ID);

		tv = (TextView) IncludeUtil.findViewById(this, getWindow(), 
				SettingActivityConfig.INCLUDE_TV_ID, IncludeConfig.TV_TV_ID, TV_VIEW_ID);

		nextTv.setText(R.string.action_about);
		tv.setText(R.string.action_exit_login);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case NEXT_VIEW_ID:
			about();
			break;
		case TV_VIEW_ID:
			exit();
			break;
		}
	}

	/**
	 * 关于我们
	 */
	public void about(){
		startActivity(new Intent(this, AboutActivity.class));
	}

	/**
	 * 退出登录
	 */
	public void exit(){
		DataUtil.cleanInfo(this);//清除数据

		//退出应用
		Intent startMain = new Intent(Intent.ACTION_MAIN); 
		startMain.addCategory(Intent.CATEGORY_HOME); 
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		startActivity(startMain); 
		System.exit(0); 
	}

}
