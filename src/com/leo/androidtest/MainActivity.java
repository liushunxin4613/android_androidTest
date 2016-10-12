package com.leo.androidtest;

import java.util.Timer;
import java.util.TimerTask;

import activity.FupinDataActivity;
import activity.SearchViewWebActivity;
import activity.SettingActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import base.activity.BaseActionBarTitleCenterActivity;
import android.widget.ScrollView;
import android.widget.Toast;
import util.data.ConfigUtil.ApplicationConfig;
import util.data.ConfigUtil.GridView1Config;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.KeyConfig;
import util.data.ConfigUtil.MainActivityConfig;
import util.data.DataUtil;
import util.ui.MyGridView;
import web.MapWebActivity;
import web.WebActivity;

public class MainActivity extends BaseActionBarTitleCenterActivity implements OnClickListener{

	@Override
	public Integer getRootViewId() {
		return MainActivityConfig.LAYOUT_ID;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_main_setting://账户信息
			startActivity(new Intent(this, SettingActivity.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private ScrollView scrollView;

	@SuppressLint("ResourceAsColor")
	@Override
	public void initView() {
		super.initView();

		scrollView = (ScrollView) findViewById(GridView1Config.SCROLLVIEW_LAYOUT_ID);

		//GridView
		LinearLayout layout = (LinearLayout) findViewById(GridView1Config.GRIDVIEW_ID);
		
		MyGridView gridView = new MyGridView(this,this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layout.addView(gridView.initView(), params);

		//初始化在顶部
		scrollView.smoothScrollTo(0, 0);

	}
	
	@Override
	public void initData() {
		//启动服务队列
		//启动地址服务
		Intent intent = new Intent(MainActivity.this, InitService.class);
		intent.putExtra(InitService.KEY_START_SERVICE_FOR, InitService.TASK_ADDRESS);
		startService(intent);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case 0://BaseInfo
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_0));
			
			String textsize = DataUtil.getInfo(this, 
					DataUtil.ROOT_SETTING, KeyConfig.TEXTSIZE_STRING);	
			if (textsize == null) {
				textsize = "1";
			}
			int t = ApplicationConfig.TEXTSIZE_VALUE[Integer.parseInt(textsize)];
			String end = "?fontsize=" + t;
			
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.BASE_INFO_URL + end);
			break;
		case 1://FupinData
			intent.setClass(this, FupinDataActivity.class);
			break;
		case 2://StatisticsAnalyze
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_2));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.ANALYSIS_URL);
			break;
		case 3://Policy
			intent.setClass(this, SearchViewWebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_3));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.NEWS_LIST_URL);
			break;
		case 4://lvyou
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_4));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.TICKET_URL);
			break;
		case 5://FupinProject
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_5));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.PROJECT_LIST_URL);
			break;
		case 6://Map
			intent.setClass(this, MapWebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_6));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.MAP_DETAIL_URL);
			break;
		case 7://FupinCheck
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_7));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.KPI_URL);
			break;
		case 8://Notice
			intent.setClass(this, SearchViewWebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_8));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.NOTICE_LIST_URL);
			break;
		case 9://SHOP
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_9));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.SHOP_URL);
			break;
		}
		startActivity(intent);
	}
	
	/**
	 * 确定退出
	 */
	private boolean confirm = false;

	/**
	 * 回退键
	 */
	@Override
	public void onBackPressed() {
		if (!confirm) {
			confirm = true;
			Toast.makeText(this, R.string.main_exit_remind, Toast.LENGTH_SHORT)
			.show();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					confirm = false;
				}
			}, 2000);
		} else {
			super.onBackPressed();
		}
	}

}
