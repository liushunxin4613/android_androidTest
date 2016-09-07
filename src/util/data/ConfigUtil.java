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
	 * 网络配置
	 * @author macos
	 *
	 */
	public static class HttpConfig{
		public final static String WITH_URL = "http://";
		public final static String IP_URL = WITH_URL + "192.168.1.198";
		public final static String DOMAIN_URL = IP_URL + "/" + "v1";
		public final static String BASE_INFO_URL = DOMAIN_URL+ "/" + "baseinfo";
		public final static String NOTICE_LIST_URL = DOMAIN_URL+ "/" + "notice/list";
		public final static String NOTICE_LIST_NEXT_URL = NOTICE_LIST_URL + "?page=";
		public final static String NOTICE_DETAIL_WITH_URL = DOMAIN_URL+ "/" + "notice/";
		public final static String NOTICE_DETAIL_END_URL = "/show";
		public final static String MAP_DETAIL_URL = DOMAIN_URL+ "/" + "map";
		public final static String USER_JSON_URI = IP_URL + ":" + "8000" + "/v1/user/login";
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
		public static int GRIDVIEW_LAYOUT_ID = R.layout.item_home_layout;

		public static int GRIDVIEW_FROM_ARRID[] = {
				R.id.item_home_image
				,R.id.item_home_text
		};
		public static String GRIDVIEW_TO_ARRID[] = {
				"img"
				,"text"
		};

		//gridview icon
		public static int GRIDVIEW_ICON_ARRID[] = {
				R.drawable.icon_home_mall
				,R.drawable.icon_home_part
				,R.drawable.icon_home_second
				,R.drawable.icon_home_more
				,R.drawable.icon_home_more
				,R.drawable.icon_home_second
				,R.drawable.icon_home_part
				,R.drawable.icon_home_mall
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

		public static int ICON_WIDTH = 90;

		public static int REST_WIDTH = 160;

		/**
		 *  此值通过img的值计算img*2+40
		 */
		public static int MIN_WIDTH = 220;
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
	 * item_tv_ver 配置
	 * @author macos
	 *
	 */
	public static class ItemTv2VerConfig{
		public static int LAYOUT_ID = R.layout.item_tv2_ver;
		public static int TV_ARR_ID[] = {
				R.id.it_tv2_ver0
				,R.id.it_tv2_ver1
		};
		public static String KEY_ID = "id";
		public static String TV_ARR[] = {
				"title"
				,"inputtime"
		};
	}

	/**
	 * notice actvity 配置文件
	 * @author macos
	 *
	 */
	public static class NoticeActivityConfig{
		public static int LAYOUT_ID = R.layout.activity_notice;
		public static int LISTVIEW_ID = R.id.ac_notice_dylistview;
		public static int ITEM_LAYOUT_ID = R.layout.item_tv2_ver;
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


}
