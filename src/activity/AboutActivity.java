package activity;

import com.leo.androidtest.R;

import util.data.ConfigUtil.HttpConfig;
import web.WebActivity;

public class AboutActivity extends WebActivity {

	@Override
	public String getUriStr() {
		return HttpConfig.ABOUT_URI;
	}
	
	@Override
	public void initView() {
		super.initView();
		setTitle(getString(R.string.action_about));
	}
	
	@Override
	public boolean goBackMain() {
		return false;
	}
	
}
