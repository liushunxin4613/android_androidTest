package base.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import util.data.ConfigUtil.WebViewConfig;

/**
 * webview 父类
 */
public abstract class BaseWebViewActivity extends BaseImmersionActivity {

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

		if (Build.VERSION.SDK_INT >= 19) {
			settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 优先使用缓存
		}

		webView.setWebViewClient(new WebViewClient() {// 是否采用第三方浏览器
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {// true为使用webview,false表示第三方
				if (uriStr.startsWith("http:") || uriStr.startsWith("https:")) {
					view.loadUrl(url);
				}
				return true;
			}
		});

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

}
