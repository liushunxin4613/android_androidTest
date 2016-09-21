package dialog;

import com.leo.androidtest.R;
import android.content.Context;
import base.dialog.BaseDialog;

public class DialogFactory {

	private BaseDialog dialog;
	private boolean dialogShowCheck;
	private boolean dialogDismissCheck;

	/**
	 * 下次是否开启show
	 */
	public void setDialogShowCheck(boolean dialogShowCheck){
		this.dialogShowCheck = dialogShowCheck;
	}

	/**
	 * 下次是否开启dismiss
	 */
	public void setDialogDismissCheck(boolean dialogDismissCheck){
		this.dialogDismissCheck = dialogDismissCheck;
	}

	/**
	 * @param context
	 * @param title 为空则设置为默认值
	 * @return
	 */
	public void newLoadingDialog(Context context,CharSequence title){
		dialog = new LoadingDialog(context, 
				R.style.MyDialogTheme_Loading, R.layout.dialog_loading, R.id.dialog_loading_text);
		if (title != null) {
			dialog.setTitle(title);
		}else {
			dialog.setTitle(R.string.dialog_loading_title);
		}
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
			}
		}
	}

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

	public void dismissDialogDefault(){
		if (isDialog) return;
		if (dialog != null) {
			dialog.dismiss();
		}
	}


}
