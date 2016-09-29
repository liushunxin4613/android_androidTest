package web;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import base.webview.BaseWebViewClient;

public class MapWebViewClient extends BaseWebViewClient {

	public MapWebViewClient(Context context) {
		super(context);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		Log.i("MapWebViewClient", "url= " + url);
	}
	
}
