package activity;

import base.activity.BaseWebViewActivity;
import util.data.ConfigUtil.HttpConfig;

/**
 * ���ӵ�ͼ
 * @author macos
 *
 */
public class MapWebViewActivity extends BaseWebViewActivity {

	@Override
	public String getUriStr() {
		return HttpConfig.MAP_DETAIL_URL;
	}

}
