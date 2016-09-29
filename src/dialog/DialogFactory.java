package dialog;

import java.util.Timer;
import java.util.TimerTask;

import com.leo.androidtest.R;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import base.dialog.BaseDialog;
import util.data.ConfigUtil.VolleyConfig;

public class DialogFactory {

	private BaseDialog dialog;
	private boolean dialogShowCheck;
	private boolean dialogDismissCheck;

	/**
	 * �´��Ƿ���show
	 */
	public void setDialogShowCheck(boolean dialogShowCheck){
		this.dialogShowCheck = dialogShowCheck;
	}

	/**
	 * �´��Ƿ���dismiss
	 */
	public void setDialogDismissCheck(boolean dialogDismissCheck){
		this.dialogDismissCheck = dialogDismissCheck;
	}

	/**
	 * @param context
	 * @param title Ϊ��������ΪĬ��ֵ
	 * @return
	 */
	public BaseDialog newLoadingDialog(Context context,CharSequence title){
		dialog = new LoadingDialog(context, 
				R.style.MyDialogTheme_Loading, R.layout.dialog_loading, R.id.dialog_loading_text);
		if (title != null) {
			dialog.setTitle(title);
		}else {
			dialog.setTitle(R.string.dialog_loading_title);
		}
		return dialog;
	}

	public void setDialogTitle(CharSequence title){
		if (dialog != null) {
			dialog.setTitle(title);
		}
	}

	private boolean isDialog;

	public void setIsDialog(boolean isDialog){
		this.isDialog = isDialog;
	}

	public void showDialog(){
		if (isDialog) return;
		if (dialogShowCheck) {
			dialogShowCheck = false;
		}else {
			if (dialog != null) {
				dialog.show();

				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						handler.sendEmptyMessage(WHAT_TOAST);
					}
				}, VolleyConfig.OUT_TIME);
			}
		}
	}

	private final int WHAT_TOAST = 1001;

	private final Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WHAT_TOAST:
				dismissDialogDefault();
				break;
			}
		}
	};

	public void dismissDialog(){
		if (isDialog) return;
		if (dialogDismissCheck) {
			dialogDismissCheck = false;
		}else {
			if (dialog != null) {
				dialog.dismiss();
			}
		}
	}

	/**
	 * ǿ��ֹͣˢ��
	 */
	public void dismissDialogDefault(){
		if (isDialog) return;
		if (dialog != null) {
			dialog.dismiss();
		}
	}


}
