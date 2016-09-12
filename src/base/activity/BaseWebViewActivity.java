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
 * webview ����
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

		settings.setJavaScriptEnabled(true);// ֧��JavaScript
		
		settings.setUseWideViewPort(true);//��ͼƬ�������ʺ�webview�Ĵ�С
		
		settings.setLoadsImagesAutomatically(true);//֧���Զ�����ͼƬ
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {//���Ӳ����������
			webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		

		if (Build.VERSION.SDK_INT >= 19) {
			settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// ����ʹ�û���
		}

		webView.setWebViewClient(new BaseWebViewClient());

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

	@Override
	public void onBackPressed() {//���践�ذ�ť
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}
	
}
