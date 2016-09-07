package activity;

import base.activity.BaseWebViewActivity;
import util.data.ConfigUtil.HttpConfig;

/**
 * µç×ÓµØÍ¼
 * @author macos
 *
 */
public class MapWebViewActivity extends BaseWebViewActivity {

	@Override
	public String getUriStr() {
		return HttpConfig.MAP_DETAIL_URL;
	}

}
