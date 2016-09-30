package web;

import com.leo.androidtest.MainActivity;
import com.leo.androidtest.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import base.activity.BaseActionBarCenterActivity;

public class MapWebActivity extends BaseActionBarCenterActivity {

	private MapWebView webView;
	private String uriStr;
	
	public static final String KEY_TITLE = "title";
	public static final String KEY_URL = "url";
	
	@Override
	public void initRootView() {
		setContentView(R.layout.item_map_webview);
		webView = (MapWebView) findViewById(R.id.item_map_webview_wv);
	}

	@Override
	public void initView() {
		super.initView();
		setTitle(getIntent().getStringExtra(KEY_TITLE));
		uriStr = getIntent().getStringExtra(KEY_URL);
		webView.initWebView();
		webView.loadUrl(uriStr);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (webView != null) {
			webView.saveState(outState);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (webView != null) {
			webView.onResume();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (webView != null) {
			webView.stopLoading();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null) {
			webView.onPause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null) {
			webView = null;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//设置返回键是webview返回
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView != null) {
				if (webView.canGoBack()) {
					webView.goBack();
					return true;
				} 
			}
		}
		if (!goBackMain()) {
			finish();
			return super.onKeyDown(keyCode, event);
		}
		return false;
	}

	@Override  
	public void onBackPressed() {
		if(webView != null) {  
			if(webView.canGoBack()){  
				webView.goBack();
				return;
			}
		}
		if (!goBackMain()) {
			super.onBackPressed();
			finish();
		}
	} 

	public boolean goBackMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
		return true;
	}
	
}
