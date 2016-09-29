package receiver;

import org.json.JSONException;
import org.json.JSONObject;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import web.WebActivity;

public class PushReceiver extends BroadcastReceiver {

	public static final String TAG = "PushReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		switch (bundle.getInt(PushConsts.CMD_ACTION)) {
		case PushConsts.GET_CLIENTID:
			String cid = bundle.getString("clientid");
			// TODO:处理cid返回
			Log.i(TAG,"cid= " + cid);
			break;
		case PushConsts.GET_MSG_DATA:
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");

			// smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行  
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);  
			Log.i(TAG,"第三方回执接口调用" + (result ? "成功" : "失败"));

			byte[] payload = bundle.getByteArray("payload");
			if (payload != null) {
				String data = new String(payload);
				// TODO:接收处理透传（payload）数据
				Log.i(TAG,"data= " + data);
				
				try {
					JSONObject obj = new JSONObject(data);
					String title = obj.getString("title");
					String url = obj.getString("url");
					Intent intent2 = new Intent(context, WebActivity.class);
					intent2.putExtra(WebActivity.KEY_TITLE, title);
					intent2.putExtra(WebActivity.KEY_URL, url);
					intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent2);
				} catch (JSONException e) {
					Log.e(TAG, e.getMessage());
				}
				
			}
			break;
		}
	}

}
