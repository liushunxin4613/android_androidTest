package activity;

import base.activity.BaseWebViewActivity;
import util.data.ConfigUtil.HttpConfig;

public class FupinDetailActivity extends BaseWebViewActivity {

	public final static String KEY_ID = "id";
	
	@Override
	public String getUriStr() {
		String id = getIntent().getStringExtra(KEY_ID);
		return HttpConfig.FAMILY_DETAIL_WITH_URL + id + HttpConfig.END_URL;
	}

}
