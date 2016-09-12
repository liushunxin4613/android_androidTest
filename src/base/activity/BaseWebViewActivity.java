package base.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import base.webviewclient.BaseWebViewClient;
import util.data.ConfigUtil.WebViewConfig;

/**
 * webview 父类
 */
public abstract class BaseWebViewActivity extends BaseActionBarCenterActivity {

	private WebView webView;
	private String uriStr;

	public abstract String getUriStr();
	
	@Override
	public int getRootViewId() {
		return WebViewConfig.LAYOUT_ID;
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void initView() {
		super.initView();
		
		uriStr = getUriStr();
		
		webView = (WebView) findViewById(WebViewConfig.WEBVIEW_ID);
		
		WebSettings settings = webView.getSettings();

		settings.setJavaScriptEnabled(true);// 支持JavaScript
		
		settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
		
		settings.setLoadsImagesAutomatically(true);//支持自动加载图片
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {//解决硬件加速问题
			webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		

		if (Build.VERSION.SDK_INT >= 19) {
			settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 优先使用缓存
		}

		webView.setWebViewClient(new BaseWebViewClient());

		webView.loadUrl(uriStr);
		
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//设置返回键是webview返回
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();
				return true;
			} 
		}
    	return super.onKeyDown(keyCode, event);
    }

	@Override
	public void onBackPressed() {//重设返回按钮
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}
	
}
