package util.data;

import com.leo.androidtest.R;

public class ConfigUtil {

	/**
	 * 全局配置
	 */
	public static class ApplicationConfig{
		public static int TITLE = R.string.app_name;
		public static int TITLE_ARR_ID[] = {
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
	public static class ActionBarCenterConfig{
		public static int LAYOUT_ID = R.layout.actionbar_center;
		public static int IMGBT_ID = R.id.ab_center_back;
		public static int TITLE_ID = R.id.ab_center_title;
	}

	/**
	 * actionBar three参数
	 * @author macos
	 *
	 */
	public static class ActionBarThreeConfig{
		public static int LAYOUT_ID = R.layout.actionbar_three;
		public static int IMGBT_FIND_ID = R.id.ab_three_find;
		public static int IMGBT_LIST_ID = R.id.ab_three_list;
		public static int TITLE_ID = R.id.ab_three_title;
	}
	
	/**
	 * actionBar title center参数
	 * @author macos
	 *
	 */
	public static class ActionBarTitleCenterConfig{
		public static int LAYOUT_ID = R.layout.actionbar_title_center;
		public static int TITLE_ID = R.id.ab_title_center_tv;
	}
	
	/**
	 * 网络配置
	 * @author macos
	 *
	 */
	public static class HttpConfig{
		
		public final static String IP_ADD = "120.27.190.62:8000";
		public final static String IP_ADD1 = "192.168.1.198:90";
		public final static String WITH_URL = "http://";
		public final static String IP_URL = WITH_URL + IP_ADD;
		public final static String DOMAIN_URL = IP_URL + "/v1";
		public final static String BASE_INFO_URL = DOMAIN_URL + "/baseinfo";
		public final static String AREA_URL = DOMAIN_URL + "/area";
		public final static String FAMILY_LIST_URL = DOMAIN_URL + "/family/list";
		public final static String FAMILY_DETAIL_WITH_URL = DOMAIN_URL + "/family/";
		public final static String NOTICE_LIST_URL = DOMAIN_URL + "/notice/list";
		public final static String NOTICE_DETAIL_WITH_URL = DOMAIN_URL + "/notice/";
		public final static String MAP_DETAIL_URL = DOMAIN_URL + "/map";
		public final static String USER_JSON_URI = DOMAIN_URL + "user/login";
		
		public final static String NEWS_LIST_URL = DOMAIN_URL + "/news/list";
		public final static String NEWS_DETAIL_WITH_URL = DOMAIN_URL + "/news/";
		public final static String PROJECT_LIST_URL = DOMAIN_URL + "/project/list";
		public final static String PROJECT_DETAIL_WITH_URL = DOMAIN_URL + "/project/";
		
		public final static String END_URL = "/show";
		public final static String LIST_NEXT_URL = "?page=";
		public final static String CALLBACK_JSON = "?callback=json";
		
	}

	/**
	 * webview 配置
	 * @author macos
	 *
	 */
	public static class WebViewConfig{
		public static int LAYOUT_ID = R.layout.activity_webview;

		public static int WEBVIEW_ID = R.id.ac_wv;
	}

	/**
	 * FlashActivity 参数
	 */
	public static class FlashActivityConfig{
		public static int LAYOUT_ID = R.layout.activity_flash;
	}
	
	/**
	 *MainActivity配置
	 */
	public static class MainActivityConfig{
		public static int LAYOUT_ID = R.layout.activity_main;
		public static int IMG_ID = R.id.ac_main_iv;

	}

	/**
	 * GridView配置
	 * @author macos
	 *
	 */
	public static class GridViewConfig{
		public static int SCROLLVIEW_LAYOUT_ID = R.id.ac_scrollview;
		
		//GridView
		public static int GRIDVIEW_ID = R.id.ac_main_gridView;
		public static int GRIDVIEW_LAYOUT_ID = R.layout.item_gridview;

		public static int GRIDVIEW_FROM_ARRID[] = {
				R.id.item_image
				,R.id.item_text
		};
		public static String GRIDVIEW_TO_ARRID[] = {
				"img"
				,"text"
		};

		//gridview icon
		public static int GRIDVIEW_ICON_ARRID[] = {
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
		public static int GRIDVIEW_TEXT_ARRID[] = {
				R.string.ac_gridview_0
				,R.string.ac_gridview_1
				,R.string.ac_gridview_2
				,R.string.ac_gridview_3
				,R.string.ac_gridview_4
				,R.string.ac_gridview_5
				,R.string.ac_gridview_6
				,R.string.ac_gridview_7
		};

		public static int NUM_COLUMNS = 2;

		public static int ICON_WIDTH = 80;

		public static int REST_WIDTH = 50;

		/**
		 *  此值通过img的值计算img*2+40
		 */
		public static int MIN_WIDTH = 320;
		
		public static int VIEW_LEFT = R.id.item_view_left;
		public static int VIEW_BOTTOM = R.id.item_view_bottom;
		
	}

	/**
	 *自定义spinnerlistview布局
	 */
	public static class SpinnerListViewConfig{

		public static int LAYOUT_ID = R.layout.activity_fupin_data;

		public static int SPINNNER_ARR[] = {
				R.id.splv_sp0
				,R.id.splv_sp1
				,R.id.splv_sp2
		};

		public static int LISTVIEW_ID = R.id.splv_dylistview;

	}

	/**
	 *	textview参数 
	 */
	public static class ItemTextViewConfig{
		public static int LAYOUT_ID = R.layout.item_textview;

		public static int TV_ARR_ID[] = {
				R.id.ittv_tv0
				,R.id.ittv_tv1
				,R.id.ittv_tv2
				,R.id.ittv_tv3
		};

		public static String TV_ARR[] = {
				"user"
				,"num"
				,"state"
				,"address"
		};

		public static int TV_LEFT_ID = R.array.splv_item_tv_left;

	}

	/**
	 * notice actvity 配置文件
	 * @author macos
	 *
	 */
	public static class NoticeActivityConfig{
		public static int ITEM_LAYOUT_ID = R.layout.item_notice;
		public static int ITEM_ARR_ID[] = {
				R.id.item_notice0
				,R.id.item_notice1
				,R.id.item_notice2
		};
		public static String ITEM_ARR[] = {
				"title"
				,"inputtime"
				,"type"
		};
		public static String KEY = "id";
	}

	/**
	 * 扶贫大数据 actvity 配置文件
	 * @author macos
	 *
	 */
	public static class FupinDataActivityConfig{
		public static int LAYOUT_ID = R.layout.activity_fupin_data;
		public static int LISTVIEW_ID = R.id.splv_dylistview;
		public static int ITEM_LAYOUT_ID = R.layout.item_user;
		public static int ITEM_ARR_ID[] = {
				R.id.item_user_name
				,R.id.item_user_dizhi
				,R.id.item_user_num
				,R.id.item_user_state
		};
		public static String ITEM_ARR[] = {
				"name"
				,"address"
				,"total_num"
				,"property"
		};
		public static String KEY = "id";
	}
	
	/**
	 * item spinner的配置参数
	 * @author macos
	 *
	 */
	public static class ItemSpinnerConfig{
		public static int LAYOUT_ID = R.layout.item_spinner;
		public static int TEXTVIEW_ID = R.id.item_sp_tv;
	}
	
	/**
	 * item Dynamiclistview的配置参数
	 * @author macos
	 *
	 */
	public static class ItemDynamiclistviewConfig{
		public static int LAYOUT_ID = R.layout.item_dynamiclistview;
		public static int LISTVIEW_ID = R.id.item_dylistview;
	}
	
	/**
	 * json解析参数
	 * @author macos
	 *
	 */
	public static class JsonDataConfig{
		public static int LIST_NUM = 20;
		public static int RETCODE_SUCCESS = 200;
		public static String ROOT_JSON_KEY_ARR[] = {
				"retcode"
				,"list"
		};
	}
	
	/**
	 * 政策资讯参数
	 * @author macos
	 *
	 */
	public static class PolicyActivityConfig{
		public static int ITEM_LAYOUT_ID = R.layout.item_notice;
		public static int ITEM_ARR_ID[] = {
				R.id.item_notice0
				,R.id.item_notice1
				,R.id.item_notice2
		};
		public static String ITEM_ARR[] = {
				"title"
				,"inputtime"
				,"type"
		};
		public static String KEY = "id";
	}
	
	/**
	 * 政策资讯参数
	 * @author macos
	 *
	 */
	public static class FupinProjectActivityConfig{
		public static int ITEM_LAYOUT_ID = R.layout.item_notice;
		public static int ITEM_ARR_ID[] = {
				R.id.item_notice0
				,R.id.item_notice1
				,R.id.item_notice2
		};
		public static String ITEM_ARR[] = {
				"title"
				,"status"
				,"type"
		};
		public static String KEY = "id";
	}

}
