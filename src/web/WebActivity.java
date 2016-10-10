package web;

import com.leo.androidtest.MainActivity;

import android.content.Intent;
import base.activity.BaseWebViewActivity;

public class WebActivity extends BaseWebViewActivity {

	public static final String KEY_TITLE = "title";
	public static final String KEY_URL = "url";
	
	protected String url;
	
	@Override
	public String getUriStr() {
		url = getIntent().getStringExtra(KEY_URL);
		return url;
	}

	@Override
	public void initView() {
		super.initView();
		setTitle(getIntent().getStringExtra(KEY_TITLE));
	}
	
	@Override
	public boolean goBackMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
		return true;
	}
	
}
