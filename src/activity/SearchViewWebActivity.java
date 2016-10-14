package activity;

import util.data.ConfigUtil.ApplicationConfig;
import util.data.ConfigUtil.KeyConfig;
import util.data.DataUtil;
import web.WebActivity;

import com.leo.androidtest.R;

import customLib.DySearchView;

import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView.OnQueryTextListener;

public class SearchViewWebActivity extends WebActivity {

	private DySearchView searchView;

	@Override
	public void initView() {
		super.initView();
	}
	
	/**
	 * ËÑË÷²Ù×÷
	 * @param queryStr
	 */
	public void searchData(String queryStr){
		if (url != null) {
			String qur;
			if (url.contains("?")) {
				qur = url + "&keyword=" + queryStr;
			} else {
				qur = url + "?keyword=" + queryStr;
			}
			Log.i(TAG,"qur = " + qur);
			webView.loadUrl(qur);
		}
	}

	private Menu menu;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.part_time, menu);
		this.menu = menu;
		
		searchView = (DySearchView)MenuItemCompat.getActionView(
				menu.findItem(R.id.menu_dysearch));

		Log.i(TAG, ":" + (searchView == null));

		searchView.setSubmitButtonEnabled(true);
		searchView.setQueryHint(getResources().getString(R.string.hint_search));

		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				String queryStr = searchView.getQuery().toString();
				Log.i(TAG, "queryStr = " + queryStr);
				searchData(queryStr);
				return false;
			}
			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});

		searchView.setResouce(R.drawable.search_bg,
				R.drawable.search_submit_bg,
				getResources().getColor(R.color.white), getResources()
				.getColor(R.color.search_hint_gray),
				R.drawable.ic_search_btn, R.drawable.ic_go_btn,
				R.drawable.ic_close_btn);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_dysearch:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void initListener() {
		webView.setOnFinishedListener(this);
	}
	
	@Override
	public void onFinishedListener(String url) {
		Log.i(TAG, "onFinishedListener:url" + url);
		if (url.contains("show")) {
			String textSize = DataUtil.getInfo(this, DataUtil.ROOT_SETTING, KeyConfig.TEXTSIZE_STRING);
			if (textSize == null) {
				textSize = "1";
			}
			Log.i(TAG, "textSize = " + textSize);
			
			webView.setVisibility(View.INVISIBLE);
			webView.loadUrl("javascript:setFontSize("+ 
					ApplicationConfig.TEXTSIZE_VALUE[Integer.parseInt(textSize)] +")");
			webView.post(new Runnable() {
				@Override
				public void run() {
					webView.setVisibility(View.VISIBLE);
				}
			});
		}
		
		if(menu == null) return;
		if(url.contains("list")){
			for (int i = 0; i < menu.size(); i++) {
				menu.getItem(i).setVisible(true);
			}
		}else {
			for (int i = 0; i < menu.size(); i++) {
				menu.getItem(i).setVisible(false);
			}
		}
	}

}
