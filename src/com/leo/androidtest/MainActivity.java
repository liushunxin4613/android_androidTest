package com.leo.androidtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import activity.FupinDataActivity;
import activity.LoginActivity;
import adapter.GridView1Adapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import base.activity.BaseActionBarTitleCenterActivity;
import android.widget.ScrollView;
import android.widget.Toast;
import customLib.ExpandGridView;
import entity.GridViewInfo;
import util.data.ConfigUtil.GridView1Config;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.MainActivityConfig;
import web.MapWebActivity;
import web.WebActivity;
import util.data.DataUtil;

public class MainActivity extends BaseActionBarTitleCenterActivity implements OnItemClickListener{

	@Override
	public int getRootViewId() {
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
		case R.id.setting0://账户信息
			startActivity(new Intent(this, LoginActivity.class));
			break;
		case R.id.setting1://退出登录
			DataUtil.cleanInfo(this);//清除数据
			super.onBackPressed();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//GridView
	private ExpandGridView gridView;
	private GridView1Adapter gridViewAdapter;
	private List<GridViewInfo> gridViewData; 

	private int iconArr[];
	private int textArr[];
	private int colorArr[];

	private int numColumns;

	private ScrollView scrollView;

	@SuppressLint("ResourceAsColor")
	@Override
	public void initView() {
		super.initView();

		scrollView = (ScrollView) findViewById(GridView1Config.SCROLLVIEW_LAYOUT_ID);

		//GridView
		gridView = (ExpandGridView) findViewById(GridView1Config.GRIDVIEW_ID);

		iconArr = GridView1Config.GRIDVIEW_ICON_ARRID;
		textArr = GridView1Config.GRIDVIEW_TEXT_ARRID;
		colorArr = GridView1Config.GRIDVIEW_COLOR_ARR;

		//
		gridViewData = new ArrayList<GridViewInfo>();
		for (int i = 0; i < iconArr.length; i++) {
			GridViewInfo info = new GridViewInfo(textArr[i], colorArr[i], iconArr[i]);
			gridViewData.add(info);
		}
		
		gridViewAdapter = new GridView1Adapter(this, gridViewData, GridView1Config.GRIDVIEW_LAYOUT_ID, GridView1Config.GRIDVIEW_FROM_ARRID);
		gridView.setAdapter(gridViewAdapter);

		numColumns = GridView1Config.NUM_COLUMNS;

		gridView.setNumColumns(numColumns);

		//设置点击事件
		gridView.setOnItemClickListener(this);

		//初始化在顶部
		scrollView.smoothScrollTo(0, 0);

		//重设图片高度
//		ImageView imageView = (ImageView) findViewById(MainActivityConfig.IMG_ID);
//		LayoutParams lp = (LayoutParams) imageView.getLayoutParams();
//		lp.height = getDisplayMetrics().heightPixels/4;

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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		switch (position) {
		case 0://BaseInfo
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_0));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.BASE_INFO_URL);
			break;
		case 1://FupinProject
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_1));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.PROJECT_LIST_URL);
			break;
		case 2://FupinData
			intent.setClass(this, FupinDataActivity.class);
			break;
		case 3://Map
			intent.setClass(this, MapWebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_3));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.MAP_DETAIL_URL);
			break;
		case 4://FupinCheck
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_4));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.KPI_URL);
			break;
		case 5://StatisticsAnalyze
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_5));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.ANALYSIS_URL);
			break;
		case 6://Notice
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_6));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.NOTICE_LIST_URL);
			break;
		case 7://Policy
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_7));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.NEWS_LIST_URL);
			break;
		case 8://lvyou
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_8));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.TICKET_URL);
			break;
		case 9://Policy
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
