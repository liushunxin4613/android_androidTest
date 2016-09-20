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

	private String ss[] = new String[]{"����ƶ��ũ��","һ��ƶ��ũ��"};
	
	private VolleyUtil util;
	private final int SPINNER_WHAT = 3;
	private final int INIT_WHAT = 0;
	private final int REFRESH_WHAT = 1;
	private final int LOADMORE_WHAT = 2;
	
	private int resource;
	private int textviewId;
	
	@Override
	public void initData() {
		//���ó�ʼ����
		resource = ItemSpinnerConfig.LAYOUT_ID;
		textviewId = ItemSpinnerConfig.TEXTVIEW_ID;
		
		mSpinnerArr[2].setAdapter(new CustomSpinnerAdapter(this, Arrays.asList(ss), resource, textviewId));
		
		util = new VolleyUtil(this);
		util.setResponseListener(this);
		
		util.setJSONObject(SPINNER_WHAT, HttpConfig.AREA_URL);//��ȡ������Ϣ
		util.setDialogDismissCheck(true);//�˴β�����
		
	}

	private List<String> zhenList;
	private List<String> cunList;
	private List<Find> findList;
	private List<JSONObject> addData;
	
	@Override
	public void onListResponse(int what,List<JSONObject> data) {
		switch (what) {
		case SPINNER_WHAT:
			//�������洢��������
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
			
			//��ʾ����
			mSpinnerArr[0].setAdapter(new CustomSpinnerAdapter(this, zhenList, resource, textviewId));
			
			mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(0), resource, textviewId));
			
			util.setJSONObject(INIT_WHAT, HttpConfig.FAMILY_LIST_URL);//��ȡ��ʼ����
			
			break;
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
	
	/**
	 * ��ȡ�弶��λ�б�
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
		case 0://ѡ������
			Log.i(TAG, getCunList(position).toString());
			mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(position), resource, textviewId));
			break;
		case 1://ѡ���
			
			break;
		case 2://ѡ��ƶ��״̬
			
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
		//TODO�ݲ����
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
			//���ηֿ���,�����ڷ�������
			intent.putExtra(NoticeDetailActivity.KEY_ID, mAdapter.getItem(position-1).getString(FupinDataActivityConfig.KEY));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(intent);
	}

}
