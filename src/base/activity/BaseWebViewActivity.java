package base.activity;

import inter.OnFinishedListener;

import com.leo.androidtest.R;

import android.os.Bundle;
import android.view.KeyEvent;
import base.webview.BaseWebView;

/**
 * webview 父类
 */
public abstract class BaseWebViewActivity extends BaseActionBarCenterActivity implements OnFinishedListener {

	protected BaseWebView webView;
	private String uriStr;


	public abstract String getUriStr();
	
	@Override
	public void initRootView() {
		setContentView(R.layout.item_webview);
		webView = (BaseWebView) findViewById(R.id.item_webview_wv);
	}

	@Override
	public void initView() {
		super.initView();
		uriStr = getUriStr();
		webView.initWebView();
		webView.loadData("", "text/html", "UTF-8");
		initListener();
		webView.loadUrl(uriStr);
	}
	
	/**
	 * 初始化监听
	 */
	protected void initListener(){	
	}

	@Override
	public void onFinishedListener(String url) {
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
			finish();
			super.onBackPressed();
		}
	} 

	public boolean goBackMain(){
		return false;
	}
}
