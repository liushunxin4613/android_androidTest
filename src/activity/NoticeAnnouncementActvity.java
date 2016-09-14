package activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import adapter.Tv2VerAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import base.activity.BaseDynamiclistviewActivity;
import customLib.DynamicListView;
import customLib.DynamicListView.LoadMoreListener;
import customLib.DynamicListView.RefreshListener;
import util.VolleyUtil;
import util.VolleyUtil.OnResponseListener;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.NoticeActivityConfig;

/**
 * 通知公告
 * @author macos
 *
 */
public class NoticeAnnouncementActvity extends BaseDynamiclistviewActivity implements OnItemClickListener,RefreshListener,LoadMoreListener,OnResponseListener{

	private VolleyUtil util;

	@Override
	public void initData() {
		util = new VolleyUtil(this,this);
		util.setJSONObject(INIT_WHAT,HttpConfig.NOTICE_LIST_URL + HttpConfig.CALLBACK_JSON);
	}

	@Override
	public void onListResponse(int what,List<JSONObject> data) {
		super.onListResponse(what, data);
		switch (what) {
		case INIT_WHAT://初始化数据
			adapter = new Tv2VerAdapter(this, data, NoticeActivityConfig.ITEM_LAYOUT_ID,
					NoticeActivityConfig.ITEM_ARR_ID,NoticeActivityConfig.ITEM_ARR,NoticeActivityConfig.KEY);
			listview.setAdapter(adapter);
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, NoticeDetailActivity.class);
		try {
			//传参分开传,有利于分离重组
			intent.putExtra(NoticeDetailActivity.KEY_ID, adapter.getItem(position-1).getString(NoticeActivityConfig.KEY));
			intent.putExtra(NoticeDetailActivity.KEY_TITLE, adapter.getItem(position-1).getString(NoticeActivityConfig.ITEM_ARR[0]));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(intent);
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		util.setJSONObject(REFRESH_WHAT, HttpConfig.NOTICE_LIST_URL + HttpConfig.CALLBACK_JSON);
		return false;
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		if (adapter.getCount() % JsonDataConfig.LIST_NUM == 0) {
			util.setJSONObject(LOADMORE_WHAT, HttpConfig.NOTICE_LIST_URL + HttpConfig.CALLBACK_JSON + HttpConfig.LIST_NEXT_URL + (adapter.getCount() / JsonDataConfig.LIST_NUM + 1));
		}else {
			listview.setOnMoreListener(null);
			return true;
		}
		return false;
	}

}
