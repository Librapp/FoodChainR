package lms.foodchainR.data;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 评论数据类
 * 
 */
public class CommentData {
	// 用户姓名
	public String name = "吃货";
	// 用户头像
	public String icon;
	// 用户Id
	public String userId;
	// 评论Id
	public String commentId;
	/** 类别 */
	public int type;
	public static final int TXT = 0;
	public static final int PIC = 1;
	/** 类型 */
	public int style;
	// 文字内容
	public String txt;
	// 发表时间
	public String time;
	// 图片内容
	public String pic;

	public static CommentData current;

	public CommentData() {

	}

	public CommentData(String userId, String name, String icon, String id,
			int type, int style, String txt, String pic, String voice,
			String time) {
		this.userId = userId;
		this.name = name;
		this.icon = icon;
		this.commentId = id;
		this.type = type;
		this.style = style;
		this.txt = txt;
		this.pic = pic;
		this.time = time;
	}

	public static CommentData current() {
		if (current == null) {
			current = new CommentData();
		}
		return current;
	}

}
