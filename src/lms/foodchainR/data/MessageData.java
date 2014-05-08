package lms.foodchainR.data;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 消息数据类
 * 
 */
public class MessageData {
	/** 分类 系统消息0 私人消息1 */
	public static final int SYSTEM = 1, PERSONAL = 0;
	/** 类型 文字0 音频1 图片2 */
	public static final int WORD = 0, VOICE = 1, IMAGE = 2;
	/** id */
	public int messageId;
	/** 时间 */
	public String time;
	/** 内容 */
	public String content;
	/** 发送用户 */
	public UserData sender;
	/** 接收用户 */
	public UserData receiver;
	/** 来源 0发1收 */
	public int direction = 1;
	/** 是否已读 0否1是 */
	public int hasRead = 0;
	/** 分类 */
	public int type = PERSONAL;
	/** 类型 */
	public int style = WORD;
	private static MessageData current;

	public static MessageData current() {
		if (current == null) {
			current = new MessageData();
		}
		return current;
	}
}
