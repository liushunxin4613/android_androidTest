package util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import dialog.DialogFactory;
import util.entity.JsonUtil;

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

	private OnResponseListener listener;

	/**
	 * 消息标记
	 */
	public static int what;
	
	private DialogFactory factory;

	public VolleyUtil(Context context,OnResponseListener listener) {
		this.listener = listener;
		mQueue = Volley.newRequestQueue(context);
		
		factory = new DialogFactory();
		factory.newLoadingDialog(context, null);
	}

	public void setDialogDismissCheck(boolean dialogDismissCheck){
		factory.setDialogDismissCheck(dialogDismissCheck);
	} 
	
	public void setJSONObject(int what,String url){
		VolleyUtil.what = what;

		factory.showDialog();

		mQueue.add(new JsonObjectRequest(Method.GET, url, null,new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				factory.dismissDialog();

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
					factory.dismissDialogDefault();

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
	public interface OnResponseListener {
		/**
		 * jsonobject数据
		 * @param jsonObj
		 */
		void onListResponse(int what,List<JSONObject> data);
	}

}
