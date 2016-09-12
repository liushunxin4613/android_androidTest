package base.activity;

import com.leo.androidtest.R;
import android.app.ActionBar;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import util.data.ConfigUtil.ActionBarCenterConfig;

public class BaseActionBarCenterActivity extends BaseImmersionActivity {

	@Override
	public void initView() {
		super.initView();
		initActionBar();
	}

	private TextView title;
	
	public boolean initActionBar(){
		ActionBar actionBar = getActionBar();
		if (actionBar == null) {
			return false;
		}
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(ActionBarCenterConfig.LAYOUT_ID);
		title = (TextView) actionBar.getCustomView().findViewById(ActionBarCenterConfig.TITLE_ID);
		title.setText(getTitle().toString());
		TextPaint paint = title.getPaint(); //���üӴ�
		paint.setFakeBoldText(true);
		LinearLayout ly = (LinearLayout) actionBar.getCustomView().findViewById(ActionBarCenterConfig.IMGBT_ID);
		ly.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		ly.setOnTouchListener(new OnTouchListener(){    
			@Override   
			public boolean onTouch(View v, MotionEvent event) {    
				if(event.getAction() == MotionEvent.ACTION_DOWN){    
					//����Ϊ����ʱ�ı���ͼƬ
					v.setBackgroundColor(R.color.immersionColor);
				}else if(event.getAction() == MotionEvent.ACTION_UP){    
					//��Ϊ̧��ʱ��ͼƬ
					v.getBackground().setAlpha(0);
				}    
				return false;    
			}
		});
		return true;
	}
	
	protected void setTitle(String title){
		this.title.setText(title);
	}

}
