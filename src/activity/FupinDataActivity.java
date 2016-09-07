package activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.SpLVAdapter;
import base.activity.BaseSpinnerListViewActivity;
import customLib.DynamicListView;
import util.data.ConfigUtil.ItemTextViewConfig;
import util.data.ConfigUtil.SpinnerListViewConfig;

/**
 * 扶贫大数据
 * @author macos
 *
 */
public class FupinDataActivity extends BaseSpinnerListViewActivity {

	private String leftArr[];
	//假数据
	private String deleteArr[] = {"何慧友","5人","一般贫困户","郧阳区大石头路谢家湾村三组"};

	@Override
	public int getRootViewId() {
		return SpinnerListViewConfig.LAYOUT_ID;
	}

	@Override
	public void initView() {
		setSpinnerConfig(SpinnerListViewConfig.SPINNNER_ARR,SpinnerListViewConfig.LISTVIEW_ID);
		super.initView();
		
	}

	@Override
	public void initData() {
		leftArr = getResources().getStringArray(ItemTextViewConfig.TV_LEFT_ID);

		List<Map<String,String>> mList = getDataList(15);

		mAdapter = new SpLVAdapter(this, mList, ItemTextViewConfig.LAYOUT_ID);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public boolean onRefresh(DynamicListView dynamicListView) {
		mAdapter.addItemsToHead(getDataList(5));
		return false;
	}

	@Override
	public boolean onLoadMore(DynamicListView dynamicListView) {
		mAdapter.addItems(getDataList(10));
		return false;
	}

	//TODO 需要删除
	public List<Map<String, String>> getDataList(int num){
		ArrayList<Map<String,String>> mList = new ArrayList<Map<String,String>>();
		for (int i = 0; i < num; i++) {
			mList.add(getDataMap());
		}
		return mList;
	}

	private int key = 0;

	//TODO 需要删除
	public Map<String, String> getDataMap(){
		Map<String, String> map = new HashMap<String, String>();
		for (int j = 0; j < ItemTextViewConfig.TV_ARR.length; j++) {
			if (j == 0) {
				map.put(ItemTextViewConfig.TV_ARR[j],leftArr[j] + deleteArr[j] + key++);
			}else {
				map.put(ItemTextViewConfig.TV_ARR[j],leftArr[j] + deleteArr[j]);
			}
		}
		return map;
	}

}
