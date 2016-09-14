package activity;

import base.activity.BaseWebViewActivity;
import util.data.ConfigUtil.HttpConfig;

public class FupinProjectDetailActivity extends BaseWebViewActivity {

	public final static String KEY_ID = "id";
	public final static String KEY_TITLE = "title";
	
	@Override
	public String getUriStr() {
		String id = getIntent().getStringExtra(KEY_ID);
		return HttpConfig.PROJECT_DETAIL_WITH_URL + id + HttpConfig.END_URL;
	}
	
	@Override
	public void initView() {
		super.initView();
		
		setTitle(getIntent().getStringExtra(KEY_TITLE));
		
	}

}
