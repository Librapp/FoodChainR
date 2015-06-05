package lms.foodchainR;

import java.io.File;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

public class FoodchainRApplication extends Application {
	public static final int DB_VERSION = 1;
	public static final String TABLE_BILL = "bill";
	public static final String TABLE_CASE = "case";
	public static final String TABLE_CASESTYLE = "caseStyle";
	public static final String TABLE_CASETYPE = "caseType";
	public static final String TABLE_COOKER = "cooker";
	public static final String TABLE_CUSTOMER = "customer";
	public static final String TABLE_MATERIAL = "material";
	public static final String TABLE_ORDER = "order";
	public static final String TABLE_RESTAURANT = "restaurant";
	public static final String TABLE_SEAT = "seat";
	public static final String TABLE_TABLE = "table";
	public static final String TABLE_TABLESTYLE = "tableStyle";
	public static final String TABLE_TABLETYPE = "tableType";

	public static final String ADDRESS = "address";
	public static final String BACKUP = "backup";
	public static final String BOOK = "book";
	public static final String CASE = "case";
	public static final String CODE = "code";
	public static final String COMETIME = "comeTime";
	public static final String COOKER = "cooker";
	public static final String COUNT = "count";
	public static final String CREDIT = "credit";
	public static final String CREATETIME = "createTime";
	public static final String CUSTOMER = "customer";
	public static final String DEVICE = "device";
	public static final String DISCOUNT = "discount";
	public static final String EMAIL = "email";
	public static final String FAMILY = "family";
	public static final String ID = "id";
	public static final String INFO = "info";
	public static final String GENDER = "gender";
	public static final String LEAVETIME = "leaveTime";
	public static final String LEVEL = "level";
	public static final String MARK = "mark";
	public static final String MONEY = "money";
	public static final String NAME = "name";
	public static final String OPENTIME = "openTime";
	public static final String ORDER = "order";
	public static final String PHONE = "phone";
	public static final String PIC = "pic";
	public static final String POINT = "point";
	public static final String PRICE = "price";
	public static final String REMARK = "remark";
	public static final String RESTAURANT = "restaurant";
	public static final String SEASON = "season";
	public static final String SEAT = "seat";
	public static final String SEATCOUNT = "seatCount";
	public static final String SEX = "sex";
	public static final String SHELFCONDATION = "shelfCondation";
	public static final String SHELFTIME = "shelfTime";
	public static final String STATE = "state";
	public static final String STYLE = "style";
	public static final String TABLE = "table";
	public static final String TIME = "time";
	public static final String TYPE = "type";
	public static final String UNIT = "unit";
	public static final String URL = "url";

	// 编辑
	public static boolean isEdit = false;

	public static Bitmap tempBitmap;
	// 当前位置
	public static String currentCity;

	// 添加按钮
	public static final int MENULIST_BTN_MORE = 0x11;
	public static final int MENULIST_BTN_ALLMORE = 0x12;
	// 所有菜单创建新菜
	public static final int MENULIST_BTN_CREATE = 0x13;
	// 结束编辑
	public static final int MENULIST_BTN_CONFIRM = 0x14;
	/* 图片相关 */
	public static final String PICTYPE = ".jpg";
	// 储存菜图片
	public static final String CASE_PIC = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ "FCR/CASE/";
	public static final String CASE_PIC_SHORT = "FCR/CASE/";
	// 储存餐馆图片
	public static final String RDETAIL_PIC = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ "FCR/RDETAIL/";
	public static final String RDETAIL_PIC_SHORT = "FCR/RDETAIL/";

	/* 数据库相关 */
	// 一般数据库
	public static final String NORMAL_DB = "fcrn.db";
	// 加密数据库
	public static final String SECRET_DB = "fcrs.db";
	// 顾客表
	public static final String CUSTOMERDATA = "customerData";
	public static final String CUSTOMERDATA_DB = "fcrcd.db";
	// 雇员表
	public static final String EMPLOYEEDATA = "employeeData";
	public static final String EMPLOYEEDATA_DB = "fcred.db";
	// 原料表
	public static final String MATERIALDATA = "materialData";
	public static final String MATERIALDATA_DB = "fcrmad.db";
	// 评论表
	public static final String COMMENTDATA = "commentData";
	// 消息表
	public static final String MEASSAGEDATA = "messageData";
	// 聊天表
	public static final String CHATDATA = "chatData";

	/* 配置文件 */
	// 本地数据
	public static final String LOCALSETTING = "localSetting";
	// 网络数据
	public static final String ONLINESETTING = "onlineSetting";

	// 设备类型
	public static final String CUSTOMERDEVICETYPE = "urn:schemas-upnp-org:device:Customer";
	public static final String WAITERDEVICETYPE = "urn:schemas-upnp-org:device:Waiter";
	public static final String RESTAURANTDEVICETYPE = "urn:schemas-upnp-org:device:Restaurant";
	public static final String CLEANERDEVICETYPE = "urn:schemas-upnp-org:device:Cleaner";
	public static final String COOKERDEVICETYPE = "urn:schemas-upnp-org:device:Cooker";

	// 描述文件
	public static final String DESCRIPTIONURL = Environment
			.getExternalStorageDirectory()
			+ File.separator
			+ "FCR"
			+ File.separator + "description.xml";

	/** 版本号 */
	public static final String VERSION = "1.0";
	public static final String CAMERA = "camera";
	/** 临时图片目录 */
	public static final String TEMP_FOLDER = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ "YouErYuanDaQuan" + File.separator;
	/** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
	public static final String APP_KEY = "1090992504";

	/**
	 * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
	 * 
	 * <p>
	 * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响， 但是没有定义将无法使用 SDK 认证登录。
	 * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
	 * </p>
	 */
	public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

	/**
	 * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
	 * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利 选择赋予应用的功能。
	 * 
	 * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的 使用权限，高级权限需要进行申请。
	 * 
	 * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
	 * 
	 * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	 * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
	 */
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";
}
