package dialog;

import com.leo.androidtest.R;

import base.dialog.BaseDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmDialog extends BaseDialog {

	private TextView msgView;

	private CharSequence msg;

	/**
	 * 对话框风格，居中
	 */
	public static final int CONFIRM_STYLE_CENTER = 0;
	/**
	 * 对话框风格，底部
	 */
	public static final int CONFIRM_STYLE_BOTTOM = 1;

	public ConfirmDialog(Context context,String title,
			String msg, String posText, String nevText) {
		super(context,R.style.Style_Dialog_Center);
		this.title = title;
		this.msg = msg;
		this.positiveText = posText;
		this.nevigativeText = nevText;
		this.theme = R.style.Style_Dialog_Center;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_confirm_center);
		titleView = (TextView) findViewById(R.id.dialog_confirm_title);
		msgView = (TextView) findViewById(R.id.dialog_confirm_msg);
		positiveBtn = (Button) findViewById(R.id.dialog_positive_button);
		nevigativeBtn = (Button) findViewById(R.id.dialog_nevigative_button);
		if (title != null) {
			titleView.setVisibility(View.VISIBLE);
			titleView.setText(title);
		}
		if (msg != null) {
			msgView.setVisibility(View.VISIBLE);
			msgView.setText(msg);
		}
		if (positiveText != null){
			positiveBtn.setText(positiveText);
			positiveBtn.setVisibility(View.VISIBLE);
		}
		if (nevigativeText != null){
			nevigativeBtn.setText(nevigativeText);
			nevigativeBtn.setVisibility(View.VISIBLE);
		}
		if (positiveText == null || nevigativeText == null){
			View line = findViewById(R.id.dialog_button_ling);
			line.setVisibility(View.GONE);
		}
		positiveBtn.setOnClickListener(this);
		nevigativeBtn.setOnClickListener(this);
	}

	public void setContentMsg(int resId) {
		msg = context.getString(resId);
		if (msgView != null) {
			msgView.setText(msg);
		}
	}
}
