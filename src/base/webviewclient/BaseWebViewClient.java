package base.webviewclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import dialog.DialogFactory;

public class BaseWebViewClient extends WebViewClient {
	
	private DialogFactory factory;
	
	public BaseWebViewClient(Context context) {
		factory = new DialogFactory();
		factory.newLoadingDialog(context, null);
	}

	@Override// �Ƿ���õ����������
	public boolean shouldOverrideUrlLoading(WebView view, String url) {// trueΪʹ��webview,false��ʾ������
		if (url.startsWith("http:") || url.startsWith("https:")) {
			view.loadUrl(url);
		}
		return true;
	}
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {//����ǰ����
		factory.showDialog();
		view.setAlpha(0);
		view.getSettings().setBlockNetworkImage(true); //ͼƬ��������
		super.onPageStarted(view, url, favicon);
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {//���غ����
		factory.dismissDialog();
		view.setAlpha(1);
		view.getSettings().setBlockNetworkImage(false); 
		super.onPageFinished(view, url);
	}
	
}
