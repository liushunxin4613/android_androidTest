package activity;

import base.activity.BaseWebViewActivity;
import util.data.ConfigUtil.HttpConfig;

/**
 * ������Ϣ
 * @author macos
 *
 */
public class BaseInfoActvity extends BaseWebViewActivity {

	@Override
	public String getUriStr() {
		return HttpConfig.BASE_INFO_URL;
	}

}
