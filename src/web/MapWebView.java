package web;

import activity.FupinDataActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import base.webview.BaseWebView;
import util.data.ConfigUtil.HttpConfig;

public class MapWebView extends BaseWebView {

	public static final String TAG = "MapWebView";

	public MapWebView(Context context) {
		super(context);
	}

	@Override
	public void loadUrl(String url) {
		Log.i(TAG,"loadUrl...");
		Log.i(TAG,"url= " + url);
		if (url.contains(HttpConfig.URL_FAMILY_LIST_EG)) {
			String cId = getStrText(url, "c=", "&z");
			String zId = getStrText(url, "z=", "&t");
			
			Intent intent = new Intent(context, FupinDataActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			intent.putExtra(FupinDataActivity.INTENT_CID, cId);
			intent.putExtra(FupinDataActivity.INTENT_ZID, zId);
			intent.putExtra(FupinDataActivity.INTENT_URL, url);
			
			context.startActivity(intent);
		} else {
			super.loadUrl(url);
		}
	}
	
	public String getStrText(String str,String eg,String end){
		if (end != null) {
			if (str.contains(end)) {
				return str.substring(str.indexOf(eg) + eg.length(), str.indexOf(end));
			}
		}
		return str.substring(str.indexOf(eg) + eg.length());
	}


}
