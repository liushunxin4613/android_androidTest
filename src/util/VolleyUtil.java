package util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import util.entity.JsonUtil;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * volley���߰�
 * @author macos
 *
 */
public class VolleyUtil {
	
	public static String TAG = "VolleyUtil";

	private RequestQueue mQueue;

	private OnResponseListener listener;
	
	/**
	 * ��Ϣ���
	 */
	public static int what;

	public VolleyUtil(Context context,OnResponseListener listener) {
		mQueue = Volley.newRequestQueue(context);
		this.listener = listener;
	}

	public void setJSONObject(int what,String url){
		VolleyUtil.what = what;
		mQueue.add(new JsonObjectRequest(Method.GET, url, null,new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Log.i(TAG, response.toString());
				List<JSONObject> data = new ArrayList<JSONObject>();
				for (int i = 0; i < JsonUtil.getResult(response).length(); i++) {
					try {
						data.add(JsonUtil.getResult(response).getJSONObject(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				listener.onListResponse(VolleyUtil.what,data);
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
	 * ����Response����
	 * @author macos
	 *
	 */
	public interface OnResponseListener {
		/**
		 * jsonobject����
		 * @param jsonObj
		 */
		void onListResponse(int what,List<JSONObject> data);
	}
	
}
