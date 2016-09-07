package base.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import util.data.ConfigUtil.WebViewConfig;

/**
 * webview ����
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

		settings.setJavaScriptEnabled(true);// ֧��JavaScript

		if (Build.VERSION.SDK_INT >= 19) {
			settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// ����ʹ�û���
		}

		webView.setWebViewClient(new WebViewClient() {// �Ƿ���õ����������
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {// trueΪʹ��webview,false��ʾ������
				if (uriStr.startsWith("http:") || uriStr.startsWith("https:")) {
					view.loadUrl(url);
				}
				return true;
			}
		});

		webView.loadUrl(uriStr);
		
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//���÷��ؼ���webview����
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();
				return true;
			} 
		}
    	return super.onKeyDown(keyCode, event);
    }

}
