package lms.foodchainR.data;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 菜品数据类
 * 
 */
import java.util.List;

public class CaseData {
	/** 菜品Id */
	public int caseId = 0;
	/** 图片 */
	public String picPath = OtherData.CASE_PIC;
	/** 简介 */
	public String intro = "经典";
	/** 菜名 */
	public String name = "蛋炒饭";
	/** 评分 */
	public float mark = 5;
	/** 价格 */
	public float price = 10;
	/** 特价 */
	public float special = 0;
	/** 状态类型 */
	public static final int AVILIABLE = 0;
	public static final int UNAVILIABLE = 1;
	/** 状态 AVILIABLE、UNAVILIABLE */
	public int state = AVILIABLE;
	/** 分类 冷菜热菜主副食 */
	public int styleId = 0;
	/** 烹饪时间 */
	public int cookTime = 10;
	// 是否是新创建
	public boolean isNew = false;
	// 评论
	private List<CommentData> comment;
	// 原料
	private List<MaterialData> material;

	public static CaseData current;

	public CaseData() {
		this.picPath += this.name + OtherData.PICTYPE;
	}

	public static CaseData current() {
		if (current == null) {
			current = new CaseData();
		}
		return current;
	}

	public void setComment(List<CommentData> comment) {
		this.comment = comment;
	}

	public List<CommentData> getComment() {
		return comment;
	}

	public void setMaterials(List<MaterialData> material) {
		this.material = material;
	}

	public List<MaterialData> getMaterials() {
		return material;
	}
}
