package util.ui;

import util.data.ConfigUtil.GridView2Config;

import com.leo.androidtest.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MyGridView {
	
	public static final String TAG = "MyGridView";
	
	private Context context;
	private OnClickListener listener;
	
	private View view;
	
	private LinearLayout layout[];

	private Handler mHandler;
	
	public MyGridView(Context context,OnClickListener listener) {
		this.context = context;
		this.listener = listener;
	}
	
	@SuppressLint({ "InflateParams", "HandlerLeak" }) 
	public View initView() {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.item_gridview2, null);
		layout = new LinearLayout[GridView2Config.GRIDVIEW_COLOR_ARR.length];

		for (int i = 0; i < layout.length; i++) {
			layout[i] = (LinearLayout) view.findViewById(GridView2Config.INCLUDE_ARR_ID[i]);
			
			layout[i].setId(i);
			
			layout[i].setClickable(true);
			layout[i].setBackgroundResource(GridView2Config.GRIDVIEW_COLOR_DW_ARR[i]);
			layout[i].setOnClickListener(listener);
			
		}
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0x123:
					popUI(msg.arg1, msg.arg2);
					break;
				}
			}
		};

		layout[1].post(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x123;
				msg.arg1 = layout[1].getWidth();
				msg.arg2 = layout[1].getHeight();
				mHandler.sendMessage(msg);
			}
		});
		
		return view;
	}
	
	public void popUI(int width,int height) {
		Log.i(TAG, "width= " + width);
		Log.i(TAG, "height= " + height);
		int pad = GridView2Config.PAD;
		int h = height * 3 / 2 + pad * 4;
		Log.i(TAG, "h= " + h);
		ImageView img;
		TextView tv;
		for (int i = 0; i < layout.length; i++) {
			switch (i) {
			case 0:
			case 2:
			case 7:
			case 9:
				LayoutParams l = new LayoutParams(width, h);
				int m = pad * 2;
				l.setMargins(m, m, m, m);
				layout[i].setLayoutParams(l);
				
				img = (ImageView) layout[i].findViewById(GridView2Config.GRIDVIEW_STYLE_IV_ARRID[0]);
				img.setBackgroundResource(GridView2Config.GRIDVIEW_ICON_ARRID[i]);
				tv = (TextView) layout[i].findViewById(GridView2Config.GRIDVIEW_STYLE_TV_ARRID[0]);
				tv.setText(GridView2Config.GRIDVIEW_TEXT_ARRID[i]);
				break;
			default:
				img = (ImageView) layout[i].findViewById(GridView2Config.GRIDVIEW_STYLE_IV_ARRID[1]);
				img.setBackgroundResource(GridView2Config.GRIDVIEW_ICON_ARRID[i]);
				tv = (TextView) layout[i].findViewById(GridView2Config.GRIDVIEW_STYLE_TV_ARRID[1]);
				tv.setText(GridView2Config.GRIDVIEW_TEXT_ARRID[i]);
				break;
			}
		}
	}
	
}
