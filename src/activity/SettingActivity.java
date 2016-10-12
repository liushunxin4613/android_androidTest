package activity;


import util.data.ConfigUtil.KeyConfig;
import util.data.ConfigUtil.SettingActivityConfig;
import util.data.DataUtil;
import util.data.ConfigUtil.IncludeConfig;
import util.ui.IncludeUtil;

import com.leo.androidtest.R;

import dialog.SingleChoiceDialog;
import dialog.SingleChoiceDialog.OnItemSelectedListener;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import base.activity.BaseActionBarCenterActivity;

public class SettingActivity extends BaseActionBarCenterActivity implements OnClickListener{

	@Override
	public Integer getRootViewId() {
		return R.layout.activity_setting;
	}

	private TextView nextTv[];
	private TextView tv;

	public static final int NEXT_VIEW_ID0 = 10010;
	public static final int NEXT_VIEW_ID1 = 10012;
	public static final int TV_VIEW_ID = 10011;

	@Override
	public void initView() {
		super.initView();
		
		nextTv = IncludeUtil.findViewArrById(this,getWindow(), 
				SettingActivityConfig.INCLUDE_NEXT_ARR_ID, IncludeConfig.NEXT_TV_TV_ARR_ID[0],
				new Integer[]{NEXT_VIEW_ID0,NEXT_VIEW_ID1});
		
		tv = (TextView) IncludeUtil.findViewById(this, getWindow(), 
				SettingActivityConfig.INCLUDE_TV_ID, IncludeConfig.TV_TV_ID, TV_VIEW_ID);

		nextTv[0].setText(R.string.action_about);
		nextTv[1].setText(R.string.action_textsize_setting);
		tv.setText(R.string.action_exit_login);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case NEXT_VIEW_ID0:
			about();
			break;
		case NEXT_VIEW_ID1:
			setTextSize();
			break;
		case TV_VIEW_ID:
			exit();
			break;
		}
	}

	/**
	 * 设置字体
	 */
	public void setTextSize(){
		String textSize = DataUtil.getInfo(this, DataUtil.ROOT_SETTING, KeyConfig.TEXTSIZE_STRING);
		if (textSize == null) {
			textSize = "1";
		}
		SingleChoiceDialog dialog = new SingleChoiceDialog(this, 
				getString(R.string.dialog_textsize_title), Integer.parseInt(textSize), 
				R.array.textsize_key);
		dialog.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int pos) {
				DataUtil.saveInfo(getApplicationContext(), DataUtil.ROOT_SETTING, KeyConfig.TEXTSIZE_STRING, pos+"");
			}
		});
		dialog.show();
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
		DataUtil.cleanInfo(this,DataUtil.ROOT_SHAREPREFERENCE_USER_INFO);//清除数据

		//退出应用
		Intent startMain = new Intent(Intent.ACTION_MAIN); 
		startMain.addCategory(Intent.CATEGORY_HOME); 
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		startActivity(startMain); 
		System.exit(0); 
	}

}
