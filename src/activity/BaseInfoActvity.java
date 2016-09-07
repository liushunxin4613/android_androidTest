package activity;

import base.activity.BaseWebViewActivity;
import util.data.ConfigUtil.HttpConfig;

/**
 * »ù´¡ÐÅÏ¢
 * @author macos
 *
 */
public class BaseInfoActvity extends BaseWebViewActivity {

	@Override
	public String getUriStr() {
		return HttpConfig.BASE_INFO_URL;
	}

}
