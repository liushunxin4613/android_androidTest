package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import dialog.DialogFactory;
import util.entity.JsonUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * volley工具包
 * @author macos
 *
 */
public class VolleyUtil {

	public static String TAG = "VolleyUtil";

	private RequestQueue mQueue;

	private OnVolleyResponseListener volleyResponseListener;
	private OnVolleyMessageListener volleyMessageListener;
	
	public static final int POST_DEFALUAT = -1;
	public static final int POST_SUCCESS = 0;
	public static final int POST_ERROR = 1;

	/**
	 * 消息标记
	 */
	public static int what;

	private DialogFactory factory;

	public VolleyUtil(Context context) {
		mQueue = Volley.newRequestQueue(context);
		factory = new DialogFactory();
		factory.newLoadingDialog(context, null);
	}
	
	public void setResponseListener(OnVolleyResponseListener volleyResponseListener){
		this.volleyResponseListener = volleyResponseListener;
	}
	
	public void setMessageListener(OnVolleyMessageListener volleyMessageListener){
		this.volleyMessageListener = volleyMessageListener;
	}

	public void setDialogDismissCheck(boolean dialogDismissCheck){
		factory.setDialogDismissCheck(dialogDismissCheck);
	} 

	public void setJSONObject(final int what,String url){

		factory.showDialog();

		mQueue.add(new JsonObjectRequest(Method.GET, url, null,new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				factory.dismissDialog();

				Log.i(TAG, response.toString());
				List<JSONObject> data = new ArrayList<JSONObject>();
				for (int i = 0; i < JsonUtil.getResultJSONArray(response).length(); i++) {
					try {
						data.add(JsonUtil.getResultJSONArray(response).getJSONObject(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				volleyResponseListener.onListResponse(what,data);
			}}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					factory.dismissDialogDefault();
					error.printStackTrace();
				}
			}));
	};
	/**
	 * post 方式
	 * @param what
	 * @param url
	 */
	public void setStringPost(final int what,String url,final Map<String, String> map){

		factory.showDialog();

		mQueue.add(new StringRequest(Method.POST, url, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				factory.dismissDialog();
				
				Log.i(TAG, response.toString());
				
				Object obj = JsonUtil.getResult(response);
				if (obj != null) {
					int a = POST_DEFALUAT;
					if (obj instanceof String) {
						a = POST_ERROR;
					}else if (obj instanceof JSONObject) {
						a = POST_SUCCESS;
					}
					volleyMessageListener.onMessage(what, a,obj);
				}
			}
		},new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				factory.dismissDialogDefault();
				error.printStackTrace();
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return map;
			}
		});
		
	};

	public void start(){
		mQueue.start();
	}

	/**
	 * 返回Response数据
	 * @author macos
	 *
	 */
	public interface OnVolleyResponseListener {
		
		/**
		 * jsonobject数据
		 * @param jsonObj
		 */
		void onListResponse(int what,List<JSONObject> data);
		
	}
	
	/**
	 * 返回Response数据
	 * @author macos
	 *
	 */
	public interface OnVolleyMessageListener {
		
		/**
		 * jsonobject数据
		 * @param jsonObj
		 */
		void onMessage(int what,int post,Object obj);
		
	}
	
	

}
