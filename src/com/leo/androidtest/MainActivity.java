package com.leo.androidtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import activity.BaseInfoActvity;
import activity.FupinCheckActivity;
import activity.FupinDataActivity;
import activity.FupinProjectManageActivity;
import activity.MapWebViewActivity;
import activity.NoticeAnnouncementActvity;
import activity.PolicyMessageActivity;
import activity.StatisticsAnalyzeActivity;
import activity.WebActivity;
import adapter.GridViewAdapter;
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
import util.data.ConfigUtil.GridViewConfig;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.MainActivityConfig;

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

			break;
		case R.id.setting1://退出登录

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//GridView
	private ExpandGridView gridView;
	private GridViewAdapter gridViewAdapter;

	private String to[];

	private int iconArr[];
	private int textArr[];

	private int numColumns;

	private ScrollView scrollView;

	@SuppressLint("ResourceAsColor")
	@Override
	public void initView() {
		super.initView();

		scrollView = (ScrollView) findViewById(GridViewConfig.SCROLLVIEW_LAYOUT_ID);

		//GridView
		gridView = (ExpandGridView) findViewById(GridViewConfig.GRIDVIEW_ID);

		to = GridViewConfig.GRIDVIEW_TO_ARRID;

		iconArr = GridViewConfig.GRIDVIEW_ICON_ARRID;
		textArr = GridViewConfig.GRIDVIEW_TEXT_ARRID;

		List<Map<String, Integer>> mList = new ArrayList<Map<String,Integer>>();

		for (int i = 0; i < iconArr.length; i++) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put(to[0], iconArr[i]);
			map.put(to[1], textArr[i]);
			mList.add(map);
		}

		gridViewAdapter = new GridViewAdapter(this, mList, GridViewConfig.GRIDVIEW_LAYOUT_ID,
				GridViewConfig.GRIDVIEW_FROM_ARRID, to);
		gridView.setAdapter(gridViewAdapter);

		numColumns = GridViewConfig.NUM_COLUMNS;

		gridView.setNumColumns(numColumns);
		gridViewAdapter.setNumColumns(numColumns);

		//设置点击事件
		gridView.setOnItemClickListener(this);

		//初始化在顶部
		scrollView.smoothScrollTo(0, 0);

		//重设图片高度
//		ImageView imageView = (ImageView) findViewById(MainActivityConfig.IMG_ID);
//		LayoutParams lp = (LayoutParams) imageView.getLayoutParams();
//		lp.height = getDisplayMetrics().heightPixels/4;

	}

	private Class<?> activityClassArr[] = {
			BaseInfoActvity.class
			,MapWebViewActivity.class
			,FupinDataActivity.class
			,FupinProjectManageActivity.class
			,FupinCheckActivity.class
			,StatisticsAnalyzeActivity.class
			,NoticeAnnouncementActvity.class
			,PolicyMessageActivity.class
	}; 

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		switch (position) {
		case 3://FupinProject
			intent.setClass(this, WebActivity.class);
			intent.putExtra(WebActivity.KEY_TITLE, getString(R.string.ac_gridview_3));
			intent.putExtra(WebActivity.KEY_URL, HttpConfig.PROJECT_LIST_URL);
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
		default:
			intent.setClass(this, activityClassArr[position]);
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
