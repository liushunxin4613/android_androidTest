package base.webview;

import inter.OnFinishedListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import dialog.DialogFactory;
import dialog.LoadingDialog;
import dialog.LoadingDialog.OnBackPressedLisener;

public class BaseWebViewClient extends WebViewClient {

	private DialogFactory factory;
	private OnFinishedListener listener;
	
	public void setOnFinishedListener(OnFinishedListener listener) {
		this.listener = listener;
	}
	
	public BaseWebViewClient(Context context) {
		factory = new DialogFactory();
		LoadingDialog dialog = (LoadingDialog) factory.newLoadingDialog(context, null);
		dialog.setOnBackPressedListener(new OnBackPressedLisener() {
			@Override
			public void onBackPressed() {
				if(factory != null)
					factory.dismissDialogDefault();
			}
		});
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
		if (listener != null) {
			listener.onFinishedListener(url);
		}
		super.onPageFinished(view, url);
	}
	
}

