package com.leo.androidtest;

import util.VolleyUtil;
import base.application.BaseApplication;

public class MyApplication extends BaseApplication{
	
	private int textSize = 14;
	private VolleyUtil util;

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	
	public VolleyUtil getUtil() {
		return util;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		util = new VolleyUtil(this);
	}
	
}
