package activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.leo.androidtest.MainActivity;
import com.leo.androidtest.R;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import base.activity.BaseImmersionActivity;
import util.VolleyUtil;
import util.VolleyUtil.OnVolleyMessageListener;
import util.data.ConfigUtil.HttpConfig;
import util.data.ConfigUtil.KeyConfig;
import util.data.ConfigUtil.LoginActivityConfig;
import util.data.DataUtil;

public class LoginActivity extends BaseImmersionActivity implements OnClickListener, OnVolleyMessageListener {

	@Override
	public int getRootViewId() {
		return LoginActivityConfig.LAYOUT_ID;
	}
	
	private EditText usernameEt,pwdEt;
	private Button loginBt;
	private final int INDEX_ID0 = 0;
	
	@Override
	public void initView() {
		super.initView();
		
		usernameEt = (EditText) findViewById(LoginActivityConfig.EDIT_ARR_ID[0]);
		pwdEt = (EditText) findViewById(LoginActivityConfig.EDIT_ARR_ID[1]);
		loginBt = (Button) findViewById(LoginActivityConfig.BUTTON_ID);
		loginBt.setOnClickListener(this);
		loginBt.setId(INDEX_ID0);
		
	}
	
	private VolleyUtil util;
	
	@Override
	public void initData() {
		
		usernameEt.setText(DataUtil.getInfo(this, DataUtil.ROOT_SHAREPREFERENCE_USER_INFO, KeyConfig.USERNAME));
		
		util = new VolleyUtil(this);
		util.setMessageListener(this);
	}
	
	private final int INDEX_WHAT0 = 0;
	@Override
	public void onClick(View v) {
		if (v.getId() == INDEX_ID0) {
			if (check()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(KeyConfig.USERNAME, usernameEt.getText().toString());
				map.put(KeyConfig.PWD, pwdEt.getText().toString());
				util.setStringPost(INDEX_WHAT0, HttpConfig.USER_JSON_URI, map);
			}
		}
	}

	private boolean check(){
		if (usernameEt.getText() == null || pwdEt.getText() == null) {
			return false;
		}else {
			if (usernameEt.getText().toString().trim().length() == 0 || pwdEt.getText().toString().trim().length() == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onMessage(int what, int post, Object obj) {
		switch (post) {
		case VolleyUtil.POST_SUCCESS:
			JSONObject jso = (JSONObject) obj;
			Map<String, Object> map;
			try {
				map = new HashMap<String, Object>();
				map.put("uid", jso.getString("uid"));
				map.put("username", jso.getString("username"));
				map.put("roles", jso.getString("roles"));
				map.put("token", jso.getString("token"));
				
				DataUtil.saveMapInfo(this, DataUtil.ROOT_SHAREPREFERENCE_USER_INFO, map);//´æ´¢µÇÂ¼ÐÅÏ¢
				Toast.makeText(this, R.string.login_toa1, Toast.LENGTH_SHORT).show();
				startActivity(new Intent(this, MainActivity.class));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		case VolleyUtil.POST_ERROR:
			Toast.makeText(this, R.string.login_toa0, Toast.LENGTH_SHORT).show();
			break;
		}
		
	}
	
}
