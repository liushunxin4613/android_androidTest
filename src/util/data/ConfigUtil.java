package util.data;

import com.leo.androidtest.R;

public final class ConfigUtil {

	/**
	 * 全局配置
	 */
	public static final class ApplicationConfig{
		public static final int TITLE = R.string.app_name;
		public static final int TITLE_ARR_ID[] = {
				R.string.ac_gridview_0
				,R.string.ac_gridview_1
				,R.string.ac_gridview_2
				,R.string.ac_gridview_3
				,R.string.ac_gridview_4
				,R.string.ac_gridview_5
				,R.string.ac_gridview_6
				,R.string.ac_gridview_7
		};
	}
	
	/**
	 * actionBar center参数
	 * @author macos
	 *
	 */
	public static final class ActionBarCenterConfig{
		public static final int LAYOUT_ID = R.layout.actionbar_center;
		public static final int IMGBT_ID = R.id.ab_center_back;
		public static final int TITLE_ID = R.id.ab_center_title;
	}

	/**
	 * actionBar three参数
	 * @author macos
	 *
	 */
	public static final class ActionBarThreeConfig{
		public static final int LAYOUT_ID = R.layout.actionbar_three;
		public static final int IMGBT_FIND_ID = R.id.ab_three_find;
		public static final int IMGBT_LIST_ID = R.id.ab_three_list;
		public static final int TITLE_ID = R.id.ab_three_title;
	}
	
	/**
	 * actionBar title center参数
	 * @author macos
	 *
	 */
	public static class ActionBarTitleCenterConfig{
		public static final int LAYOUT_ID = R.layout.actionbar_title_center;
		public static final int TITLE_ID = R.id.ab_title_center_tv;
	}
	
	/**
	 * 网络配置
	 * @author macos
	 *
	 */
	public static final class HttpConfig{
		public static final String IP_ADD = "120.27.190.62:8000";
		public static final String IP_ADD1 = "192.168.1.198:90";
		public static final String IP_ADD2 = "www.momo55.com:8000";
		public static final String WITH_URL = "http://";
		public static final String IP_URL = WITH_URL + IP_ADD2;
		public static final String DOMAIN_URL = IP_URL + "/v1";
		public static final String BASE_INFO_URL = DOMAIN_URL + "/baseinfo";
		public static final String AREA_URL = DOMAIN_URL + "/area";
		public static final String FAMILY_LIST_URL = DOMAIN_URL + "/family/list";
		public static final String FAMILY_DETAIL_WITH_URL = DOMAIN_URL + "/family/";
		public static final String NOTICE_LIST_URL = DOMAIN_URL + "/notice/list";
		public static final String NOTICE_DETAIL_WITH_URL = DOMAIN_URL + "/notice/";
		public static final String MAP_DETAIL_URL = DOMAIN_URL + "/map";
		public static final String USER_JSON_URI = DOMAIN_URL + "/user/login";
		
		public static final String NEWS_LIST_URL = DOMAIN_URL + "/news/list";
		public static final String NEWS_DETAIL_WITH_URL = DOMAIN_URL + "/news/";
		public static final String PROJECT_LIST_URL = DOMAIN_URL + "/project/list";
		public static final String PROJECT_DETAIL_WITH_URL = DOMAIN_URL + "/project/";
		public static final String ANALYSIS_URL = DOMAIN_URL + "/analysis";
		public static final String KPI_URL = DOMAIN_URL + "/kpi";
		
		public static final String END_URL = "/show";
		public static final String LIST_NEXT_URL = "?page=";
		public static final String CALLBACK_JSON = "?callback=json";
		
	}

	/**
	 * 键值对
	 * @author macos
	 *
	 */
	public static final class KeyConfig{
		public static final String USERNAME = "username";
		public static final String PWD = "password";
		public static final String LOGIN_ARR[] = {
				"username"
				,"token"
		};
		public static final String ADDRESS_STRING = "address";
		public static final String TYPE_STRING = "type";
		public static final String NAME = "name";
		public static final String ID = "id";
		public static final String LIST = "list";
	}
	
	/**
	 * webview 配置
	 * @author macos
	 *
	 */
	public static final class WebViewConfig{
		public static final int LAYOUT_ID = R.layout.activity_webview;

		public static final int WEBVIEW_ID = R.id.ac_wv;
	}

	/**
	 * FlashActivity 参数
	 */
	public static final class FlashActivityConfig{
		public static final int LAYOUT_ID = R.layout.activity_flash;
	}
	
	/**
	 *MainActivity配置
	 */
	public static final class MainActivityConfig{
		public static final int LAYOUT_ID = R.layout.activity_main;
		public static final int IMG_ID = R.id.ac_main_iv;

	}

	/**
	 * GridView配置
	 * @author macos
	 *
	 */
	public static final class GridViewConfig{
		public static final int SCROLLVIEW_LAYOUT_ID = R.id.ac_scrollview;
		
		//GridView
		public static final int GRIDVIEW_ID = R.id.ac_main_gridView;
		public static final int GRIDVIEW_LAYOUT_ID = R.layout.item_gridview;

		public static final int GRIDVIEW_FROM_ARRID[] = {
				R.id.item_image
				,R.id.item_text
		};
		public static final String GRIDVIEW_TO_ARRID[] = {
				"img"
				,"text"
		};

		//gridview icon
		public static final int GRIDVIEW_ICON_ARRID[] = {
				R.drawable.icon_baseinfo
				,R.drawable.icon_map
				,R.drawable.icon_fupin_data
				,R.drawable.icon_fupin_project
				,R.drawable.icon_fupin_check
				,R.drawable.icon_tongji
				,R.drawable.icon_notice
				,R.drawable.icon_zhence
		};

		//gridview icon text
		public static final int GRIDVIEW_TEXT_ARRID[] = {
				R.string.ac_gridview_0
				,R.string.ac_gridview_1
				,R.string.ac_gridview_2
				,R.string.ac_gridview_3
				,R.string.ac_gridview_4
				,R.string.ac_gridview_5
				,R.string.ac_gridview_6
				,R.string.ac_gridview_7
		};

		public static final int NUM_COLUMNS = 2;

		public static final int ICON_WIDTH = 80;

		public static final int REST_WIDTH = 50;

		/**
		 *  此值通过img的值计算img*2+40
		 */
		public static final int MIN_WIDTH = 320;
		
		public static final int VIEW_LEFT = R.id.item_view_left;
		public static final int VIEW_BOTTOM = R.id.item_view_bottom;
		
	}

	/**
	 *自定义spinnerlistview布局
	 */
	public static final class SpinnerListViewConfig{

		public static final int LAYOUT_ID = R.layout.activity_fupin_data;

		public static final int SPINNNER_ARR[] = {
				R.id.splv_sp0
				,R.id.splv_sp1
				,R.id.splv_sp2
		};

		public static final int LISTVIEW_ID = R.id.splv_dylistview;

	}

	/**
	 * notice actvity 配置文件
	 * @author macos
	 *
	 */
	public static final class NoticeActivityConfig{
		public static final int ITEM_LAYOUT_ID = R.layout.item_notice;
		public static final int ITEM_ARR_ID[] = {
				R.id.item_notice0
				,R.id.item_notice1
				,R.id.item_notice2
		};
		public static final String ITEM_ARR[] = {
				"title"
				,"inputtime"
				,"type"
		};
		public static final String KEY = "id";
	}

	/**
	 * 扶贫大数据 actvity 配置文件
	 * @author macos
	 *
	 */
	public static final class FupinDataActivityConfig{
		public static final int LAYOUT_ID = R.layout.activity_fupin_data;
		public static final int LISTVIEW_ID = R.id.splv_dylistview;
		public static final int ITEM_LAYOUT_ID = R.layout.item_user;
		public static final int ITEM_ARR_ID[] = {
				R.id.item_user_name
				,R.id.item_user_dizhi
				,R.id.item_user_num
				,R.id.item_user_state
		};
		public static final String ITEM_ARR[] = {
				"name"
				,"address"
				,"total_num"
				,"property"
		};
		public static final String KEY = "id";
	}
	
	/**
	 * item spinner的配置参数
	 * @author macos
	 *
	 */
	public static final class ItemSpinnerConfig{
		public static final int LAYOUT_ID = R.layout.item_spinner;
		public static final int TEXTVIEW_ID = R.id.item_sp_tv;
	}
	
	/**
	 * item Dynamiclistview的配置参数
	 * @author macos
	 *
	 */
	public static final class ItemDynamiclistviewConfig{
		public static final int LAYOUT_ID = R.layout.item_dynamiclistview;
		public static final int LISTVIEW_ID = R.id.item_dylistview;
	}
	
	/**
	 * json解析参数
	 * @author macos
	 *
	 */
	public static final class JsonDataConfig{
		public static final int LIST_NUM = 20;
		public static final int RETCODE_SUCCESS = 200;
		public static final String INDEX_RETCODE = "retcode";
		public static final String ROOT_JSON_KEY_ARR[] = {
				"retcode"
				,"list"
				,"msg"
				,"info"
				,"family_type"
		};
	}
	
	/**
	 * 政策资讯参数
	 * @author macos
	 *
	 */
	public static final class PolicyActivityConfig{
		public static final int ITEM_LAYOUT_ID = R.layout.item_notice;
		public static final int ITEM_ARR_ID[] = {
				R.id.item_notice0
				,R.id.item_notice1
				,R.id.item_notice2
		};
		public static final String ITEM_ARR[] = {
				"title"
				,"inputtime"
				,"type"
		};
		public static final String KEY = "id";
	}
	
	/**
	 * 政策资讯参数
	 * @author macos
	 *
	 */
	public static final class FupinProjectActivityConfig{
		public static final int ITEM_LAYOUT_ID = R.layout.item_notice;
		public static final int ITEM_ARR_ID[] = {
				R.id.item_notice0
				,R.id.item_notice1
				,R.id.item_notice2
		};
		public static final String ITEM_ARR[] = {
				"title"
				,"status"
				,"type"
		};
		public static final String KEY = "id";
	}

	/**
	 * 登录Activity
	 */
	public static final class LoginActivityConfig{
		public static final int LAYOUT_ID = R.layout.activity_login;
		public static final int EDIT_ARR_ID[] = {
				R.id.ac_login_username_et
				,R.id.ac_login_pwd_et
		};
		public static final int BUTTON_ID = R.id.ac_login_bt;
	}
	
}
