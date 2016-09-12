package base.webviewclient;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BaseWebViewClient extends WebViewClient {

	@Override// 是否采用第三方浏览器
	public boolean shouldOverrideUrlLoading(WebView view, String url) {// true为使用webview,false表示第三方
		if (url.startsWith("http:") || url.startsWith("https:")) {
			view.loadUrl(url);
		}
		return true;
	}
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {//加载前调用
		view.setVisibility(View.INVISIBLE);
		view.getSettings().setBlockNetworkImage(true); //图片下载阻塞
		super.onPageStarted(view, url, favicon);
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {//加载后调用
		view.setVisibility(View.VISIBLE);
		view.getSettings().setBlockNetworkImage(false); 
		super.onPageFinished(view, url);
	}
	
}
