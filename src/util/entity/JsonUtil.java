package util.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import util.data.ConfigUtil.JsonDataConfig;

public class JsonUtil {

	public static String TAG = "NoticeJsonUtil";
	
	public static JSONArray getResult(JSONObject response){
		try {
			int retcode = response.getInt(JsonDataConfig.ROOT_JSON_KEY_ARR[0]);
			JSONArray result = response.getJSONArray(JsonDataConfig.ROOT_JSON_KEY_ARR[1]);
			if (retcode == JsonDataConfig.RETCODE_SUCCESS) {
				return result;
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
}
