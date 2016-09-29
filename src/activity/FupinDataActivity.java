package activity;

import java.util.ArrayList;
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
import util.data.DataUtil;
import util.data.ConfigUtil.FupinDataActivityConfig;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.ItemSpinnerConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.KeyConfig;
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

	private VolleyUtil util;
	private final int INIT_WHAT = 0;
	private final int REFRESH_WHAT = 1;
	private final int LOADMORE_WHAT = 2;

	private int resource;
	private int textviewId;

	public static final String INTENT_CID = "intent_cid";
	public static final String INTENT_ZID = "intent_zid";
	public static final String INTENT_TID = "intent_tid";
	public static final String INTENT_URL = "intent_url";

	private String zhenId,cunId,typeId;

	public String getUrl(){
		String url = HttpConfig.FAMILY_LIST_URL
				+ "?z=" + zhenId
				+ "&c=" + cunId
				+ "&t=" + typeId;
		Log.i(TAG, "url : " + url);
		return url;
	}

	@Override
	public void initData() {
		//设置初始参数
		resource = ItemSpinnerConfig.LAYOUT_ID;
		textviewId = ItemSpinnerConfig.TEXTVIEW_ID;
		initAddress();

		util = new VolleyUtil(this);
		util.setResponseListener(this);

		getIntentDataId();
		util.setJSONObject(INIT_WHAT, getUrl());//获取初始数据
	}

	public void getIntentDataId(){
		//获取getIntent()
		zhenId = getIntent().getStringExtra(INTENT_ZID);
		if (zhenId == null) {
			zhenId = "";
		}
		cunId = getIntent().getStringExtra(INTENT_CID);
		if (cunId == null) {
			cunId = "";
		}
		typeId = getIntent().getStringExtra(INTENT_TID);
		if (typeId == null) {
			typeId = "";
		}
		
		Log.i(TAG, "zhenId = " + zhenId);
		Log.i(TAG, "cunId = " + cunId);
		Log.i(TAG, "typeId = " + typeId);
		
		for (int i = 0; i < zhenList.size(); i++) {
			if (zhenId.equals(zhenList.get(i).getId())) {
				mSpinnerArr[0].setSelection(i);
				initCunSpinner(i);
			}
		}
		for (int j = 0; j < cunList.size(); j++) {
			Log.i(TAG, "cunList.get(i).getId() = " + cunList.get(j).getId());
			if (cunId.equals(cunList.get(j).getId())) {
				Log.i(TAG, "I = " + j);
				mSpinnerArr[1].setSelection(j);
			}
		}
		for (int i = 0; i < typeList.size(); i++) {
			if (typeId.equals(typeList.get(i).getId())) {
				mSpinnerArr[2].setSelection(i);
			}
		}
	}

	private JSONArray addressArray;
	private List<Find> typeList;
	private List<Find> zhenList;
	private List<Find> cunList;

	/**
	 * 提取村镇信息
	 */
	private void initAddress(){
		initZhenSpinner();
		initCunSpinner(0);
		initTypeSpinner();
	}

	/**
	 * 初始化类型下拉框信息
	 */
	private void initTypeSpinner() {
		try {
			typeList = new ArrayList<Find>();
			JSONArray typeArray = new JSONArray(DataUtil.getInfo(this, DataUtil.ROOT_ADDRESS, KeyConfig.TYPE_STRING));
			for (int i = 0; i < typeArray.length(); i++) {
				JSONObject obj = typeArray.getJSONObject(i);
				typeList.add(new Find(obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
			}
			//显示数据
			mSpinnerArr[2].setAdapter(new CustomSpinnerAdapter(this, typeList, resource, textviewId));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化村下拉框信息
	 */
	private void initCunSpinner(int position) {
		mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(position), resource, textviewId));
	}

	/**
	 * 初始化乡镇下拉框信息
	 */
	private void initZhenSpinner(){
		try {
			zhenList = new ArrayList<Find>();
			addressArray = new JSONArray(DataUtil.getInfo(this, DataUtil.ROOT_ADDRESS, KeyConfig.ADDRESS_STRING));
			for (int i = 0; i < addressArray.length(); i++) {
				JSONObject obj = addressArray.getJSONObject(i);
				zhenList.add(new Find(obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
			}
			//显示数据
			mSpinnerArr[0].setAdapter(new CustomSpinnerAdapter(this, zhenList, resource, textviewId));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取村级单位列表
	 * @param index
	 */
	public List<Find> getCunList(int index){
		cunList = new ArrayList<Find>();
		try {
			JSONArray jsonArr = addressArray.getJSONObject(index).getJSONArray(KeyConfig.LIST);
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject obj = jsonArr.getJSONObject(i);
				cunList.add(new Find(obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cunList;
	}

	@Override
	public void onListResponse(int what,List<JSONObject> data) {
		switch (what) {
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

	private boolean bs;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		if (!bs) {
			bs = true;
			return;
		}
		switch (parent.getId()) {
		case SP_ZHEN_ID://选择乡镇
			initCunSpinner(position);
			if (!zhenList.get(position).getId().equals(zhenId)) {
				zhenId = zhenList.get(position).getId();
				util.setJSONObject(INIT_WHAT, getUrl());
			}
			break;
		case SP_CUN_ID://选择村
			if (!cunList.get(position).getId().equals(cunId)) {
				cunId = cunList.get(position).getId();
				util.setJSONObject(INIT_WHAT, getUrl());
			}
			break;
		case SP_TYPE_ID://选择贫困状态
			if (!typeList.get(position).getId().equals(typeId)) {
				typeId = typeList.get(position).getId();
				util.setJSONObject(INIT_WHAT, getUrl());
			}
			break;//获取初始数据
		}
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		util.setJSONObject(REFRESH_WHAT, getUrl());
		return false;
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		//TODO暂不清楚
		if (mAdapter.getCount() % JsonDataConfig.LIST_NUM == 0) {
			util.setJSONObject(LOADMORE_WHAT, getUrl() + "&page="+ (mAdapter.getCount() / JsonDataConfig.LIST_NUM + 1));
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
