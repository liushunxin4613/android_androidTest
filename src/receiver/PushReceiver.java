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
			// TODO:����cid����
			Log.i(TAG,"cid= " + cid);
			break;
		case PushConsts.GET_MSG_DATA:
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");

			// smartPush��������ִ���ýӿڣ�actionid��ΧΪ90000-90999���ɸ���ҵ�񳡾�ִ��  
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);  
			Log.i(TAG,"��������ִ�ӿڵ���" + (result ? "�ɹ�" : "ʧ��"));

			byte[] payload = bundle.getByteArray("payload");
			if (payload != null) {
				String data = new String(payload);
				// TODO:���մ���͸����payload������
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
