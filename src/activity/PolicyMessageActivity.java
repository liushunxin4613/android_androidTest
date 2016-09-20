package activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import adapter.Tv2VerAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import base.activity.BaseDynamiclistviewActivity;
import customLib.DynamicListView;
import util.VolleyUtil;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.PolicyActivityConfig;

/**
 * 政策资讯
 */
public class PolicyMessageActivity extends BaseDynamiclistviewActivity{

	private VolleyUtil util;
	
	@Override
	public void initData() {
		util = new VolleyUtil(this);
		util.setResponseListener(this);
		util.setJSONObject(INIT_WHAT,HttpConfig.NEWS_LIST_URL + HttpConfig.CALLBACK_JSON);
	}

	@Override
	public void onListResponse(int what, List<JSONObject> data) {
		super.onListResponse(what, data);
		switch (what) {
		case INIT_WHAT://初始化数据
			adapter = new Tv2VerAdapter(this, data, PolicyActivityConfig.ITEM_LAYOUT_ID,
					PolicyActivityConfig.ITEM_ARR_ID,PolicyActivityConfig.ITEM_ARR,PolicyActivityConfig.KEY);
			listview.setAdapter(adapter);
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, PolicyDetailActivity.class);
		try {
			//传参分开传,有利于分离重组
			intent.putExtra(PolicyDetailActivity.KEY_ID, adapter.getItem(position-1).getString(PolicyActivityConfig.KEY));
			intent.putExtra(PolicyDetailActivity.KEY_TITLE, adapter.getItem(position-1).getString(PolicyActivityConfig.ITEM_ARR[0]));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(intent);
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		util.setJSONObject(REFRESH_WHAT, HttpConfig.NEWS_LIST_URL + HttpConfig.CALLBACK_JSON);
		return false;
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		if (adapter.getCount() % JsonDataConfig.LIST_NUM == 0) {
			util.setJSONObject(LOADMORE_WHAT, HttpConfig.NEWS_LIST_URL + HttpConfig.CALLBACK_JSON + HttpConfig.LIST_NEXT_URL + (adapter.getCount() / JsonDataConfig.LIST_NUM + 1));
		}else {
			listview.setOnMoreListener(null);
			return true;
		}
		return false;
	}

}
