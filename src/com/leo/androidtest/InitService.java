package com.leo.androidtest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import activity.FlashActivity;
import android.content.Intent;
import android.util.Log;
import base.service.BaseService;
import util.data.ConfigUtil.KeyConfig;
import util.data.DataUtil;

public class InitService extends BaseService{

	public static final String TAG = "InitService";
	
	public static final int TASK_LOGIN = 1;
	
	@Override
	protected void toStartCommand(int requestCode) {
		if ((requestCode & TASK_LOGIN) == TASK_LOGIN) {
			logining();
		}
	}
	
	private void logining(){
		List<String> keyList = Arrays.asList(KeyConfig.LOGIN_ARR);
		Map<String, String> map = DataUtil.getMapInfo(this, DataUtil.ROOT_SHAREPREFERENCE_USER_INFO, keyList);
		Log.i(TAG, map.toString());
		if (map.get(KeyConfig.LOGIN_ARR[0]) == null || map.get(KeyConfig.LOGIN_ARR[1]) == null) {
			sendBroadcast(new Intent(FlashActivity.ACTION_STRAT_ERROR));
		}else {
			sendBroadcast(new Intent(FlashActivity.ACTION_STRAT_SUCCESS));
		}
	}
	
}
