package web;

public class MapWebActivity extends WebActivity {

	@Override
	public void initRootView() {
		webView = new MapWebView(this);
		setContentView(webView);
	}
	
}
