package activity;

import manager.DataCleanManager;
import util.data.ConfigUtil.KeyConfig;
import util.data.ConfigUtil.SettingActivityConfig;
import util.data.DataUtil;
import util.data.ConfigUtil.IncludeConfig;
import util.ui.IncludeUtil;

import com.leo.androidtest.R;

import dialog.ConfirmDialog;
import dialog.SingleChoiceDialog;
import dialog.SingleChoiceDialog.OnItemSelectedListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import base.activity.BaseActionBarCenterActivity;

public class SettingActivity extends BaseActionBarCenterActivity implements OnClickListener{

	@Override
	public Integer getRootViewId() {
		return R.layout.activity_setting;
	}

	private TextView nextTv[];
	private TextView tv[];
	private TextView cleanSizeTv;

	public static final int NEXT_VIEW_ID0 = 10010;
	public static final int TV_VIEW_ID = 10011;
	public static final int NEXT_VIEW_ID1 = 10012;
	public static final int NEXT_VIEW_ID2 = 10013;

	@Override
	public void initView() {
		super.initView();

		View v = findViewById(SettingActivityConfig.INCLUDE_NEXT_ARR_ID[2]);
		cleanSizeTv = (TextView) v.findViewById(IncludeConfig.NEXT_TV_TV_ARR_ID[1]);
		
		nextTv = IncludeUtil.findViewArrById(this,getWindow(), 
				SettingActivityConfig.INCLUDE_NEXT_ARR_ID, IncludeConfig.NEXT_TV_TV_ARR_ID[0],
				new Integer[]{NEXT_VIEW_ID0,NEXT_VIEW_ID1,NEXT_VIEW_ID2});

		tv = IncludeUtil.findViewArrById(this, getWindow(), 
				SettingActivityConfig.INCLUDE_TV_ARR_ID, IncludeConfig.TV_TV_ID, 
				new Integer[]{TV_VIEW_ID});

		nextTv[0].setText(R.string.action_about);
		nextTv[1].setText(R.string.action_textsize_setting);
		nextTv[2].setText(R.string.action_clean_app);
		tv[0].setText(R.string.action_exit_login);

		cleanSizeTv.setGravity(Gravity.RIGHT);
		cleanSizeTv.setPadding(0, 0, 20, 0);
		cleanSizeTv.setText(getCleanData());
		
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
		case NEXT_VIEW_ID2:
			cleanApp();
			break;
		case TV_VIEW_ID:
			exit();
			break;
		}
	}

	/**
	 * 清理
	 */
	public void cleanApp() {
		ConfirmDialog dialog = new ConfirmDialog(this,
				getString(R.string.dialog_clean_title),
				getString(R.string.dialog_clean_msg),
				getString(R.string.dialog_sure),
				getString(R.string.dialog_cancel));
		dialog.setOnclickListener(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					cleanData();
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					break;
				}
			}
		});
		dialog.show();
	}

	/**
	 * 获取缓存
	 */
	public String getCleanData(){
		try {
			return DataCleanManager.getTotalCacheSize(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 清理缓存
	 */
	public void cleanData(){
		try {
			//清除所有缓存
			DataCleanManager.clearAllCache(this);
			cleanSizeTv.setText(getCleanData());
			Toast.makeText(this, getString(R.string.dialog_clean_success), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
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
