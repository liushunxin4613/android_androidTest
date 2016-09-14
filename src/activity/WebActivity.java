package activity;

import base.activity.BaseWebViewActivity;

public class WebActivity extends BaseWebViewActivity {

	public static final String KEY_TITLE = "title";
	public static final String KEY_URL = "url";
	
	@Override
	public String getUriStr() {
		return getIntent().getStringExtra(KEY_URL);
	}

	@Override
	public void initView() {
		super.initView();
		setTitle(getIntent().getStringExtra(KEY_TITLE));
	}
	
}
