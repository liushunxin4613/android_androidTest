package com.leo.androidtest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import activity.FlashActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import base.service.BaseService;
import util.VolleyUtil;
import util.VolleyUtil.OnVolleyMessageListener;
import util.VolleyUtil.OnVolleyResponseListener;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.JsonDataConfig;
import util.data.ConfigUtil.KeyConfig;
import util.data.DataUtil;

public class InitService extends BaseService implements OnVolleyResponseListener, OnVolleyMessageListener{

	public static final String TAG = "InitService";

	public static final int TASK_LOGIN = 1;
	public static final int TASK_ADDRESS = 2;

	private VolleyUtil volleyUtil;

	@Override
	public void onCreate() {
		volleyUtil = new VolleyUtil(this);
		volleyUtil.setResponseListener(this);
		volleyUtil.setMessageListener(this);
		volleyUtil.setIsDialog(true);
	}

	@Override
	protected void toStartCommand(int requestCode) {
		if ((requestCode & TASK_LOGIN) == TASK_LOGIN) {
			logining();
		}else if ((requestCode & TASK_ADDRESS) == TASK_ADDRESS) {
			Log.i(TAG, "******ad*******");
			addressing();
		}
	}

	public static final int WHAT_LOGIN = 1;
	public static final int WHAT_ADDRESS = 2;

	private void logining(){
		List<String> keyList = Arrays.asList(KeyConfig.LOGIN_ARR);
		Map<String, String> map = DataUtil.getMapInfo(this, DataUtil.ROOT_SHAREPREFERENCE_USER_INFO, keyList);
		Log.i(TAG, map.toString());
		if (map.get(KeyConfig.LOGIN_ARR[0]) == null || map.get(KeyConfig.LOGIN_ARR[1]) == null) {
			sendBroadcast(new Intent(FlashActivity.ACTION_STRAT_ERROR));
		}else {
			volleyUtil.setStringPost(WHAT_LOGIN, HttpConfig.USER_JSON_URI, map);
		}
	}

	private void addressing(){
		volleyUtil.setString(WHAT_ADDRESS, HttpConfig.AREA_URL);//获取区县信息
	};

	@Override
	public void onListResponse(int what, List<JSONObject> data) {
	}

	/**
	 * 登录成功
	 */
	private void loginSuccess(Object obj){
		JSONObject jsoLogin = (JSONObject) obj;
		Map<String, Object> map;
		try {
			map = new HashMap<String, Object>();
			map.put("uid", jsoLogin.getString("uid"));
			map.put("username", jsoLogin.getString("username"));
			map.put("roles", jsoLogin.getString("roles"));
			map.put("token", jsoLogin.getString("token"));

			DataUtil.saveMapInfo(this, DataUtil.ROOT_SHAREPREFERENCE_USER_INFO, map);//存储登录信息
			Toast.makeText(this, R.string.login_toa1, Toast.LENGTH_SHORT).show();
			sendBroadcast(new Intent(FlashActivity.ACTION_STRAT_SUCCESS));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 地址信息获取成功
	 */
	private void addressSuccess(Object obj){
		JSONObject jsoAddress;
		try {
			jsoAddress = new JSONObject((String)obj);
			
			Map<String, Object> mapAdd = new HashMap<String, Object>();
			
			mapAdd.put(KeyConfig.ADDRESS_STRING, jsoAddress.getString(JsonDataConfig.ROOT_JSON_KEY_ARR[1]));
			mapAdd.put(KeyConfig.TYPE_STRING, jsoAddress.getString(JsonDataConfig.ROOT_JSON_KEY_ARR[4]));
			
			DataUtil.saveMapInfo(this, DataUtil.ROOT_ADDRESS, mapAdd);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(int what, int post, Object obj) {
		switch (post) {
		case VolleyUtil.POST_SUCCESS:
			//WHAT
			switch (what) {
			case WHAT_LOGIN:
				loginSuccess(obj);
				break;
			case WHAT_ADDRESS:
				addressSuccess(obj);
				break;
			}
			break;
		case VolleyUtil.POST_ERROR:
			Toast.makeText(this, R.string.login_toa0, Toast.LENGTH_SHORT).show();
			sendBroadcast(new Intent(FlashActivity.ACTION_STRAT_ERROR));
			break;
		}
	}

}
