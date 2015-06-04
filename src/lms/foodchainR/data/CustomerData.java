package lms.foodchainR.data;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 顾客数据类
 * 
 */
import java.util.List;

import org.cybergarage.upnp.Device;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_customer")
public class CustomerData extends UserData {
	// ID
	@DatabaseField(id = true)
	public int id;
	// 用户名
	@DatabaseField
	public String name = "";
	/** 签名 */
	@DatabaseField
	public String signature;
	/** 昵称 */
	@DatabaseField
	public String nickname;
	// 头像
	@DatabaseField
	public String headPic = "";
	// 地址
	@DatabaseField
	public String address = "";
	// 简介
	@DatabaseField
	public String intro = "";
	// 邮箱
	@DatabaseField
	public String email = "";
	// 电话
	@DatabaseField
	public String phone = "";
	// 来的时间
	@DatabaseField
	public String comeTime = "";
	// 离开时间
	@DatabaseField
	public String leaveTime = "";
	/** 密码 */
	@DatabaseField
	public String password = "";
	// 级别
	@DatabaseField
	public int level = 0;
	// 信用
	@DatabaseField
	public int credit = 0;
	// 积分
	@DatabaseField
	public int point = 0;
	// 性别0女1男2其他
	@DatabaseField
	public int gender = 0;
	// 性取向0女1男2其他
	@DatabaseField
	public int sex = 0;
	// 状态
	@DatabaseField
	public int state = 0;
	/** 类型 */
	@DatabaseField
	public int style;
	// 座位Id
	@DatabaseField
	public int seatId;
	// 桌Id
	@DatabaseField
	public int tableId;
	// 是否在店
	public boolean isIn = false;
	// 是否好友
	public boolean isFriend = false;
	/** 是否拼桌 */
	public boolean share = false;
	/** 隐私设置 */
	public int provicy = 0;
	/** 就餐人数 */
	@DatabaseField
	public int count = 1;
	/** 余额 */
	@DatabaseField
	public float money;
	// 账单
	public BillData bill;
	// 喜欢的菜
	private List<CaseData> favoriteCase;
	// 喜欢的餐馆
	private List<RestaurantData> favoriteRes;
	// 黑名单
	private List<RestaurantData> blackList;

	private static CustomerData current;

	public static CustomerData current() {
		if (current == null) {
			current = new CustomerData();
		}
		return current;
	}

	public CustomerData(Device d) {
		this.name = d.getFriendlyName();
		this.device = d;
	}

	public CustomerData() {

	}

	public void setFavoriteCase(List<CaseData> favoriteCase) {
		this.favoriteCase = favoriteCase;
	}

	public List<CaseData> getFavoriteCase() {
		return favoriteCase;
	}

	public void setFavoriteRes(List<RestaurantData> favoriteRes) {
		this.favoriteRes = favoriteRes;
	}

	public List<RestaurantData> getFavoriteRes() {
		return favoriteRes;
	}

	public void setBlackList(List<RestaurantData> blackList) {
		this.blackList = blackList;
	}

	public List<RestaurantData> getBlackList() {
		return blackList;
	}

}
