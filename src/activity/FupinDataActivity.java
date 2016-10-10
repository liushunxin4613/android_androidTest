package activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.leo.androidtest.R;

import adapter.CustomSpinnerAdapter;
import adapter.Tv2VerAdapter;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView.OnQueryTextListener;
import base.activity.BaseSpinnerListViewActivity;
import customLib.DySearchView;
import customLib.DynamicListView;
import entity.Find;
import util.VolleyUtil.OnVolleyResponseListener;
import util.data.DataUtil;
import util.data.ConfigUtil.FupinDataActivityConfig;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.ItemSpinnerConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.KeyConfig;
import util.data.ConfigUtil.SpinnerListViewConfig;

/**
 * ��ƶ������
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

	private final int INIT_WHAT = 0;
	private final int REFRESH_WHAT = 1;
	private final int LOADMORE_WHAT = 2;

	private int resource;
	private int textviewId;

	public static final String INTENT_CID = "intent_cid";
	public static final String INTENT_ZID = "intent_zid";
	public static final String INTENT_TID = "intent_tid";

	public static final String INTENT_URL = "intent_url";

	private Map<String, String> urlMap;

	private String mapArrType[] = new String[]{"z","c","t","keyword"};

	public String getUrl(){

		Log.i(TAG, urlMap.toString());

		StringBuffer sbParam=new StringBuffer();
		if (!urlMap.isEmpty()){
			Iterator<Map.Entry<String, String>> entries = urlMap.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, String> entry = entries.next();
				if (entry.getKey() != null && entry.getValue() != null) {
					String sg = entry.getValue();
					if (sg.trim().length() > 0) {
						sbParam.append("&");
						sbParam.append(entry.getKey());
						sbParam.append("=");
						if (entry.getKey().equals("keyword")) {
							try {
								sbParam.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						} else {
							sbParam.append(entry.getValue());
						}
						sbParam.setCharAt(0,'?');
					}
				}
			} 
			Log.i(TAG, "sbParam = " + sbParam);
		}
		Log.i(TAG, "getUrl = " + HttpConfig.FAMILY_LIST_URL + sbParam);
		return HttpConfig.FAMILY_LIST_URL + sbParam;
	}

	@Override
	public void initData() {
		//���ó�ʼ����
		urlMap = new HashMap<String, String>();

		resource = ItemSpinnerConfig.LAYOUT_ID;
		textviewId = ItemSpinnerConfig.TEXTVIEW_ID;
		initAddress();

		getVolleyUtil().openDialog(this);
		getVolleyUtil().setResponseListener(this);

		getIntentDataId();
		getVolleyUtil().setJSONObject(INIT_WHAT, getUrl());//��ȡ��ʼ����
	}

	public void getIntentDataId(){
		//��ȡgetIntent()
		String intentArrStr[] = new String[]{INTENT_ZID,INTENT_CID,INTENT_TID};
		for (int i = 0; i < intentArrStr.length; i++) {
			urlMap.put(mapArrType[i], getIntent().getStringExtra(INTENT_ZID));
		}

		for (int i = 0; i < zhenList.size(); i++) {
			if (zhenList.get(i).getId().equals(urlMap.get(mapArrType[0]))) {
				mSpinnerArr[0].setSelection(i);
				initCunSpinner(i);
			}
		}
		for (int i = 0; i < cunList.size(); i++) {
			Log.i(TAG, "cunList.get(i).getId() = " + cunList.get(i).getId());
			if (cunList.get(i).getId().equals(urlMap.get(mapArrType[1]))) {
				Log.i(TAG, "I = " + i);
				mSpinnerArr[1].setSelection(i);
			}
		}
		for (int i = 0; i < typeList.size(); i++) {
			if (typeList.get(i).getId().equals(urlMap.get(mapArrType[2]))) {
				mSpinnerArr[2].setSelection(i);
			}
		}
	}

	private JSONArray addressArray;
	private List<Find> typeList;
	private List<Find> zhenList;
	private List<Find> cunList;

	/**
	 * ��ȡ������Ϣ
	 */
	private void initAddress(){
		initZhenSpinner();
		initCunSpinner(0);
		initTypeSpinner();
	}

	/**
	 * ��ʼ��������������Ϣ
	 */
	private void initTypeSpinner() {
		try {
			typeList = new ArrayList<Find>();
			JSONArray typeArray = new JSONArray(DataUtil.getInfo(this, DataUtil.ROOT_ADDRESS, KeyConfig.TYPE_STRING));
			for (int i = 0; i < typeArray.length(); i++) {
				JSONObject obj = typeArray.getJSONObject(i);
				typeList.add(new Find(obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
			}
			//��ʾ����
			mSpinnerArr[2].setAdapter(new CustomSpinnerAdapter(this, typeList, resource, textviewId));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ������������Ϣ
	 */
	private void initCunSpinner(int position) {
		mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(position), resource, textviewId));
	}

	/**
	 * ��ʼ��������������Ϣ
	 */
	private void initZhenSpinner(){
		try {
			zhenList = new ArrayList<Find>();
			addressArray = new JSONArray(DataUtil.getInfo(this, DataUtil.ROOT_ADDRESS, KeyConfig.ADDRESS_STRING));
			for (int i = 0; i < addressArray.length(); i++) {
				JSONObject obj = addressArray.getJSONObject(i);
				zhenList.add(new Find(obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
			}
			//��ʾ����
			mSpinnerArr[0].setAdapter(new CustomSpinnerAdapter(this, zhenList, resource, textviewId));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�弶��λ�б�
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
		case INIT_WHAT://��ʼ������
			mAdapter = new Tv2VerAdapter(this, data, FupinDataActivityConfig.ITEM_LAYOUT_ID,
					FupinDataActivityConfig.ITEM_ARR_ID,FupinDataActivityConfig.ITEM_ARR,FupinDataActivityConfig.KEY);
			mListView.setAdapter(mAdapter);
			break;
		case REFRESH_WHAT://ˢ������
			mAdapter.addItemsToHead(data);
			mListView.doneRefresh();
			break;
		case LOADMORE_WHAT://��������
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
		case SP_ZHEN_ID://ѡ������
			initCunSpinner(position);
			if (!zhenList.get(position).getId().equals(urlMap.get(mapArrType[0]))) {
				urlMap.put(mapArrType[0], zhenList.get(position).getId());
				getVolleyUtil().setJSONObject(INIT_WHAT, getUrl());
			}
			break;
		case SP_CUN_ID://ѡ���
			if (!cunList.get(position).getId().equals(urlMap.get(mapArrType[1]))) {
				urlMap.put(mapArrType[1], cunList.get(position).getId());
				getVolleyUtil().setJSONObject(INIT_WHAT, getUrl());
			}
			break;
		case SP_TYPE_ID://ѡ��ƶ��״̬
			if (!typeList.get(position).getId().equals(urlMap.get(mapArrType[2]))) {
				urlMap.put(mapArrType[2], typeList.get(position).getId());
				getVolleyUtil().setJSONObject(INIT_WHAT, getUrl());
			}
			break;//��ȡ��ʼ����
		}
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		getVolleyUtil().setJSONObject(REFRESH_WHAT, getUrl());
		return false;
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		//TODO�ݲ����
		if (mAdapter.getCount() % JsonDataConfig.LIST_NUM == 0) {
			getVolleyUtil().setJSONObject(LOADMORE_WHAT, getUrl() + "&page="+ (mAdapter.getCount() / JsonDataConfig.LIST_NUM + 1));
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
			//���ηֿ���,�����ڷ�������
			intent.putExtra(NoticeDetailActivity.KEY_ID, mAdapter.getItem(position-1).getString(FupinDataActivityConfig.KEY));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(intent);
	}

	//����
	/**
	 * ��������
	 * @param queryStr
	 */
	public void searchData(String queryStr){
		urlMap.put(mapArrType[3], queryStr);
		getVolleyUtil().setJSONObject(INIT_WHAT, getUrl());
		urlMap.put(mapArrType[3], null);
	}

	private DySearchView searchView;

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
