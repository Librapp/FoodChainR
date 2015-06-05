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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_case")
public class CaseData {
	/** 菜品Id */
	@DatabaseField(id = true)
	public int id = 0;
	/** 图片 */
	@DatabaseField
	public String pic = "";
	/** 简介 */
	@DatabaseField
	public String intro = "经典";
	/** 菜名 */
	@DatabaseField
	public String name = "蛋炒饭";
	/** 评分 */
	@DatabaseField
	public float mark = 5;
	/** 价格 */
	@DatabaseField
	public float price = 10;
	/** 状态类型 */
	public static final int AVILIABLE = 0;
	public static final int UNAVILIABLE = 1;
	/** 状态 AVILIABLE、UNAVILIABLE */
	@DatabaseField
	public int state = AVILIABLE;
	/** 分类 冷菜热菜主副食 */
	@DatabaseField
	public int style = 0;
	@DatabaseField
	public int type = 0;
	@DatabaseField
	public int point = 0;
	/** 烹饪时间 */
	@DatabaseField
	public int cookTime = 10;
	// 是否是新创建
	public boolean isNew = false;
	// 评论
	private List<CommentData> comment;
	// 原料
	private List<MaterialData> material;

	public static CaseData current;

	public CaseData() {
		this.pic += this.name + "";
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
