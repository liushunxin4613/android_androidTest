package activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.CustomSpinnerAdapter;
import adapter.Tv2VerAdapter;
import android.content.Intent;
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

	private VolleyUtil util;
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
		initAddress();
		
		util = new VolleyUtil(this);
		util.setResponseListener(this);
		util.setJSONObject(INIT_WHAT, HttpConfig.FAMILY_LIST_URL);//��ȡ��ʼ����
		
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
		initCunSpinner();
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
				typeList.add(new Find(i, obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
			}
			//��ʾ����
			mSpinnerArr[2].setAdapter(new CustomSpinnerAdapter(this, typeList, resource, textviewId));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ʼ��������������Ϣ
	 */
	private void initCunSpinner() {
		mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(0), resource, textviewId));
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
				zhenList.add(new Find(i, obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
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
				cunList.add(new Find(i, obj.getString(KeyConfig.NAME), obj.getString(KeyConfig.ID)));
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
	
	private String zhenId,cunId,typeId;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
		case 0://ѡ������
			mSpinnerArr[1].setAdapter(new CustomSpinnerAdapter(this, getCunList(position), resource, textviewId));
			zhenId = zhenList.get(position).getId();
			break;
		case 1://ѡ���
			cunId = cunList.get(position).getId();
			break;
		case 2://ѡ��ƶ��״̬
			typeId = typeList.get(position).getId();
			break;
		}
		String str = "?z=" + zhenId + "&c=" + cunId + "&t=" + typeId;
		util.setJSONObject(INIT_WHAT, HttpConfig.FAMILY_LIST_URL + str);//��ȡ��ʼ����
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
