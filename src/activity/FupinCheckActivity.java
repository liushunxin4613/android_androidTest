package activity;

import base.activity.BaseActionBarCenterActivity;
import dialog.DialogFactory;
import util.delete.TimerUtil;
import util.delete.TimerUtil.OnTime;

/**
 * ·öÆ¶¿¼ºË
 * @author macos
 *
 */
public class FupinCheckActivity extends BaseActionBarCenterActivity {

	private DialogFactory factory;

	@Override
	public void initView() {
		super.initView();

		factory = new DialogFactory();
		factory.newLoadingDialog(this, null);
		factory.showDialog();
		new TimerUtil(new OnTime() {
			@Override
			public void onTime() {
				factory.dismissDialogDefault();
			}
		}).start();

	}

}
