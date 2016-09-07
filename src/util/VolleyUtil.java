package util;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * volley工具包
 * @author macos
 *
 */
public class VolleyUtil {
	
	public static String TAG = "VolleyUtil";

	private RequestQueue mQueue;

	private ResponseListener listener;
	
	/**
	 * 消息标记
	 */
	public static int what;

	public VolleyUtil(Context context,ResponseListener listener) {
		mQueue = Volley.newRequestQueue(context);
		this.listener = listener;
	}

	public void setJSONObject(int what,String url){
		VolleyUtil.what = what;
		mQueue.add(new JsonObjectRequest(Method.GET, url, null,new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Log.i(TAG, response.toString());
				listener.onJsonResponse(VolleyUtil.what,response);
			}}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					error.printStackTrace();
				}
			}));
	};
	
	public void start(){
		mQueue.start();
	}
	
	/**
	 * 返回Response数据
	 * @author macos
	 *
	 */
	public interface ResponseListener {
		/**
		 * jsonobject数据
		 * @param jsonObj
		 */
		void onJsonResponse(int what,JSONObject jsonObj);
	}
	
}
