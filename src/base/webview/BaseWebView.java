package base.webview;

import inter.OnFinishedListener;
import inter.OnLoadListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import util.AppUtil;

@SuppressLint("SetJavaScriptEnabled")
public class BaseWebView extends WebView {

	private Context context;
	private BaseWebViewClient webViewClient;
	
	private OnLoadListener listener;

	public void setOnFinishedListener(OnFinishedListener listener) {
		webViewClient.setOnFinishedListener(listener);
	}
	
	public void setOnLoadListener(OnLoadListener listener) {
		this.listener = listener;
	}

	public BaseWebView(Context context) {
		super(context);
		this.context = context;
	}

	public BaseWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public BaseWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public void initWebView(){
		setWebViewSetting();
		webViewClient = new BaseWebViewClient(context);
		setWebViewClient(webViewClient);
	}

	public void setWebViewSetting(){
		WebSettings settings = getSettings();

		settings.setJavaScriptEnabled(true);// 支持JavaScript

		settings.setUseWideViewPort(true);//将图片调整到适合webview的大小

		settings.setLoadsImagesAutomatically(true);//支持自动加载图片

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {//解决硬件加速问题
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}

		settings.setCacheMode(WebSettings.LOAD_DEFAULT);

		settings.setDomStorageEnabled(true);

		settings.setSupportZoom(true);//设置缩放

		String ua = getSettings().getUserAgentString();
		getSettings().setUserAgentString(ua+"; FishOS /"+ AppUtil.getVersionName(context));
	}
	
	@Override
	public void loadUrl(String url) {
		if (listener != null) {
			listener.loadUrlStart(url);
		}
		super.loadUrl(url);
	}


}
