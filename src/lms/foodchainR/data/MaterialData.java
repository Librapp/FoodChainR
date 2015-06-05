package lms.foodchainR.data;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 材料数据类
 * 
 */
public class MaterialData {
	// ID
	public int id = 0;
	// 名字
	public String name = "猪肉";
	// 单位
	public String unit = "g";
	// 每单位单价
	public float price = 0;
	// 库存
	public float count = 0;
	// 保质期
	public String shelf_time;
	/** 保存条件 */
	public String shelf_condation;
	/** 生产日期 */
	public String create_time;
	/** 时令 */
	public String season;
	// 分类
	public int style = 0;
	/** 类别 */
	public int type = 0;

	public static MaterialData current = new MaterialData();

	public MaterialData() {
		// TODO Auto-generated constructor stub
	}
}
