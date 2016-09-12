package base.webviewclient;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BaseWebViewClient extends WebViewClient {

	@Override// �Ƿ���õ����������
	public boolean shouldOverrideUrlLoading(WebView view, String url) {// trueΪʹ��webview,false��ʾ������
		if (url.startsWith("http:") || url.startsWith("https:")) {
			view.loadUrl(url);
		}
		return true;
	}
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {//����ǰ����
		view.setVisibility(View.INVISIBLE);
		view.getSettings().setBlockNetworkImage(true); //ͼƬ��������
		super.onPageStarted(view, url, favicon);
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {//���غ����
		view.setVisibility(View.VISIBLE);
		view.getSettings().setBlockNetworkImage(false); 
		super.onPageFinished(view, url);
	}
	
}
