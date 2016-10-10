package activity;

import web.WebActivity;

import com.leo.androidtest.R;

import customLib.DySearchView;

import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView.OnQueryTextListener;

public class SearchViewWebActivity extends WebActivity {

	private DySearchView searchView;

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
			webView.loadUrl(qur);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.part_time, menu);

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
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.menu_dysearch:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
