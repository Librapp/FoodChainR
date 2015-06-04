package lms.foodchainR.data;

import java.util.List;

import org.cybergarage.upnp.Device;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author 李梦思
 * @description 用户基类
 */
public class UserData {
	private static UserData self;
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
	// 设备
	public Device device;
	/** 局域网内地址 */
	public String localURL;
	// 评论
	private List<CommentData> comment;
	// 消息
	private List<MessageData> message;

	public static UserData self() {
		if (self == null) {
			self = new UserData();
		}
		return self;
	}

	public void setComment(List<CommentData> comment) {
		this.comment = comment;
	}

	public List<CommentData> getComment() {
		return comment;
	}

	public void setMessage(List<MessageData> message) {
		this.message = message;
	}

	public List<MessageData> getMessage() {
		return message;
	}

}
