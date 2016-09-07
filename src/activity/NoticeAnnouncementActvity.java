package activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import adapter.Tv2VerAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import base.activity.BaseImmersionActivity;
import customLib.DynamicListView;
import customLib.DynamicListView.LoadMoreListener;
import customLib.DynamicListView.RefreshListener;
import util.VolleyUtil;
import util.VolleyUtil.ResponseListener;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.ItemTv2VerConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.NoticeActivityConfig;
import util.entity.NoticeJsonUtil;

/**
 * 通知公告
 * @author macos
 *
 */
public class NoticeAnnouncementActvity extends BaseImmersionActivity implements OnItemClickListener,RefreshListener,LoadMoreListener,ResponseListener{

	@Override
	public int getRootViewId() {
		return NoticeActivityConfig.LAYOUT_ID;
	}

	private Tv2VerAdapter adapter;
	private VolleyUtil util;
	private DynamicListView listview;

	private final int INIT_WHAT = 0;
	private final int REFRESH_WHAT = 1;
	private final int LOADMORE_WHAT = 2;

	@Override
	public void initView() {
		super.initView();

		listview = (DynamicListView) findViewById(NoticeActivityConfig.LISTVIEW_ID);

		listview.setOnItemClickListener(this);
		listview.setOnRefreshListener(this);
		listview.setOnMoreListener(this);
	}

	@Override
	public void initData() {
		util = new VolleyUtil(this,this);
		util.setJSONObject(INIT_WHAT,HttpConfig.NOTICE_LIST_URL);
	}

	@Override
	public void onJsonResponse(int what,JSONObject jsonObj) {
		List<JSONObject> data = new ArrayList<JSONObject>();
		for (int i = 0; i < NoticeJsonUtil.getResult(jsonObj).length(); i++) {
			try {
				data.add(NoticeJsonUtil.getResult(jsonObj).getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		switch (what) {
		case INIT_WHAT://初始化数据
			adapter = new Tv2VerAdapter(this, data, NoticeActivityConfig.ITEM_LAYOUT_ID);
			listview.setAdapter(adapter);
			break;
		case REFRESH_WHAT://刷新数据
			adapter.addItemsToHead(data);
			listview.doneRefresh();
			break;
		case LOADMORE_WHAT://加载数据
			adapter.addItems(data);
			listview.doneMore();
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, NoticeDetailActivity.class);
		try {
			intent.putExtra(NoticeDetailActivity.KEY_ID, adapter.getItem(position-1).getString(ItemTv2VerConfig.KEY_ID));
			intent.putExtra(NoticeDetailActivity.KEY_TITLE, adapter.getItem(position-1).getString(ItemTv2VerConfig.TV_ARR[0]));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(intent);
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		util.setJSONObject(REFRESH_WHAT, HttpConfig.NOTICE_LIST_URL);
		return false;
	}

	@Override
	public void onCancelRefresh(DynamicListView dynamicListView) {}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		if (adapter.getCount() % JsonDataConfig.LIST_NUM == 0) {
			util.setJSONObject(LOADMORE_WHAT, HttpConfig.NOTICE_LIST_NEXT_URL + (adapter.getCount() / JsonDataConfig.LIST_NUM + 1));
		}else {
			listview.setOnMoreListener(null);
			return true;
		}
		return false;
	}

	@Override
	public void onCancelLoadMore(DynamicListView dynamicListView) {}

}
