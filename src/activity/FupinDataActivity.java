package activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.CustomSpinnerAdapter;
import adapter.Tv2VerAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import base.activity.BaseSpinnerListViewActivity;
import customLib.DynamicListView;
import entity.Find;
import util.VolleyUtil;
import util.VolleyUtil.OnVolleyResponseListener;
import util.data.ConfigUtil.FupinDataActivityConfig;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.ItemSpinnerConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.SpinnerListViewConfig;

/**
 * 扶贫大数据
 * @author macos
 *
 */
public class FupinDataActivity extends BaseSpinnerListViewActivity implements OnVolleyResponseListener{

	@Override
	public int getRootViewId() {
		return SpinnerListViewConfig.LAYOUT_ID;
	}

	@Override
	public void initView() {
		setSpinnerConfig(SpinnerListViewConfig.SPINNNER_ARR,SpinnerListViewConfig.LISTVIEW_ID);
		super.initView();
	}

	private String ss[] = new String[]{"绝对贫困农户","一般贫困农户"};
	
	private VolleyUtil util;
	private final int SPINNER_WHAT = 3;
	private final int INIT_WHAT = 0;
	private final int REFRESH_WHAT = 1;
	private final int LOADMORE_WHAT = 2;
	
	private int resource;
	private int textviewId;
	
	@Override
	public void initData() {
		//设置初始参数
		resource = ItemSpinnerConfig.LAYOUT_ID;
		textviewId = ItemSpinnerConfig.TEXTVIEW_ID;
		
		mSpinnerArr[2].setAdapter(new CustomSpinnerAdapter(this, Arrays.asList(ss), resource, textviewId));
		
		util = new VolleyUtil(this);
		util.setResponseListener(this);
		
		util.setJSONObject(SPINNER_WHAT, HttpConfig.AREA_URL);//获取区县信息
		util.setDialogDismissCheck(true);//此次不消除
		
	}

	private List<String> zhenList;
	private List<String> cunList;
	private List<Find> findList;
	private List<JSONObject> addData;
	
	@Override
	public void onListResponse(int what,List<JSONObject> data) {
		switch (what) {
		case SPINNER_WHAT:
			//解析并存储乡镇数据
			Log.i(TAG,"data.size()= " + data.size());
			addData = data;
			findList = new ArrayList<Find>();
			zhenList = new ArrayList<String>();
			for (int i = 0; i < addData.size(); i++) {
				JSONObject obj = addData.get(i);
				try {
					String name = obj.getString("name");
					String id = obj.getString("id");
					Find find = new Find(i, name, id);
					zhenList.add(name);
					findList.add(find);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			//显示数据
			mSpinnerArr[0].setAdapter(new CustomSpinnerAdapter(this, zhenList, resource, textviewId));
			
			mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(0), resource, textviewId));
			
			util.setJSONObject(INIT_WHAT, HttpConfig.FAMILY_LIST_URL);//获取初始数据
			
			break;
		case INIT_WHAT://初始化数据
			mAdapter = new Tv2VerAdapter(this, data, FupinDataActivityConfig.ITEM_LAYOUT_ID,
					FupinDataActivityConfig.ITEM_ARR_ID,FupinDataActivityConfig.ITEM_ARR,FupinDataActivityConfig.KEY);
			mListView.setAdapter(mAdapter);
			break;
		case REFRESH_WHAT://刷新数据
			mAdapter.addItemsToHead(data);
			mListView.doneRefresh();
			break;
		case LOADMORE_WHAT://加载数据
			mAdapter.addItems(data);
			mListView.doneMore();
			break;
		}
	}
	
	/**
	 * 获取村级单位列表
	 * @param index
	 */
	public List<String> getCunList(int index){
		cunList = new ArrayList<String>();
		try {
			JSONArray jsonArr = addData.get(index).getJSONArray("list");
			for (int i = 0; i < jsonArr.length(); i++) {
				cunList.add(jsonArr.getJSONObject(i).getString("name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cunList;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case 0://选择乡镇
			Log.i(TAG, getCunList(position).toString());
			mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(position), resource, textviewId));
			break;
		case 1://选择村
			
			break;
		case 2://选择贫困状态
			
			break;
		}
	}
	
	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		util.setJSONObject(REFRESH_WHAT, HttpConfig.FAMILY_LIST_URL);
		return false;
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		//TODO暂不清楚
		if (mAdapter.getCount() % JsonDataConfig.LIST_NUM == 0) {
			util.setJSONObject(LOADMORE_WHAT, HttpConfig.FAMILY_LIST_URL + HttpConfig.LIST_NEXT_URL + (mAdapter.getCount() / JsonDataConfig.LIST_NUM + 1));
		}else {
			mListView.setOnMoreListener(null);
			return true;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, FupinDetailActivity.class);
		try {
			//传参分开传,有利于分离重组
			intent.putExtra(NoticeDetailActivity.KEY_ID, mAdapter.getItem(position-1).getString(FupinDataActivityConfig.KEY));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(intent);
	}

}
