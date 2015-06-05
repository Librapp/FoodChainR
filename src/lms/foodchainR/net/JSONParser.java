package lms.foodchainR.net;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.data.MessageData;
import lms.foodchainR.data.RestaurantData;
import lms.foodchainR.data.SeatData;
import lms.foodchainR.data.TableData;
import lms.foodchainR.data.TableStyleData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author 李梦思
 * @description JSONParser
 * 
 */
public class JSONParser {
	public static String msg = "";

	/** 解析CaseStyleDataList */
	public static String caseStyleListParse(String result,
			ArrayList<CaseStyleData> list) {
		msg = "";
		try {
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject item = array.getJSONObject(i);
				CaseStyleData csd = new CaseStyleData();
				msg = caseStyleDataParse(item.toString(), csd);
				if (msg.equals(""))
					list.add(csd);
				else
					return msg;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析CaseStyleData */
	public static String caseStyleDataParse(String result, CaseStyleData csd) {
		msg = "";
		try {
			JSONObject data = new JSONObject(result);
			csd.id = data.optInt("id");
			csd.name = data.optString("name");
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析CaseData */
	public static String caseDataParse(String result, CaseData cd) {
		msg = "";
		try {
			JSONObject data = new JSONObject(result);
			cd.id = data.optInt("caseId");
			cd.state = data.optInt("state");
			cd.style = data.optInt("styleId");
			cd.cookTime = data.optInt("cookTime");
			cd.name = data.optString("name");
			cd.pic = data.optString("picPath");
			cd.intro = data.optString("intro");
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析restaurantInfo */
	public static String restaurantInfoParse(String result,
			RestaurantData current) {
		msg = "";
		try {
			JSONObject data = new JSONObject(result);
			current.name = data.optString("name");
			current.headPic = data.optString("headPic");
			current.address = data.optString("address");
			current.phone = data.optString("tel");
			current.sms = data.optString("sms");
			current.opentime = data.optString("opentime");
			current.email = data.optString("email");
			current.id = data.optInt("id");
			current.credit = data.optInt("credit");
			current.state = data.optInt("state");
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析hallInfo */
	public static String hallInfoParse(String result,
			ArrayList<TableStyleData> tableStyleList) {
		msg = "";
		try {
			JSONArray data = new JSONArray(result);
			for (int i = 0; i < data.length(); i++) {
				JSONObject item = data.getJSONObject(i);
				TableStyleData ts = new TableStyleData();
				msg = tableStyleDataParse(item.toString(), ts);

			}
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析tableStyleData */
	public static String tableStyleDataParse(String result, TableStyleData ts) {
		msg = "";
		try {
			JSONObject data = new JSONObject(result);
			ts.id = data.optInt("id");
			ts.icon = data.optString("icon");
			ts.pic = data.optString("pic");
			ts.seatCount = data.optInt("seatCount");
			ts.count = data.optInt("tableCount");
			JSONArray array = data.optJSONArray("tableList");
			ArrayList<TableData> list = new ArrayList<TableData>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject item = array.getJSONObject(i);
				TableData td = new TableData();
				msg = tableDataParse(item.toString(), td);
				if (msg.equals(""))
					list.add(td);
				else
					return msg;
			}
			ts.setTable(list);
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析tableData */
	public static String tableDataParse(String result, TableData td) {
		msg = "";
		try {
			JSONObject data = new JSONObject(result);
			td.id = data.optInt("id");
			td.seatCount = data.optInt("seatCount");
			JSONArray array = data.optJSONArray("seatList");
			ArrayList<SeatData> list = new ArrayList<SeatData>();
			for (int i = 0; i < array.length(); i++) {
				JSONObject item = array.getJSONObject(i);
				SeatData sd = new SeatData();
				msg = seatDataParse(item.toString(), sd);
				if (msg.equals(""))
					list.add(sd);
				else
					return msg;
			}
			// td.setSeat(list);
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/** 解析seatData */
	private static String seatDataParse(String result, SeatData sd) {
		msg = "";

		return msg;
	}

	/** 解析messageData */
	public static String messageDataParse(String result, MessageData md) {
		msg = "";
		int id = -1;
		int type = -1;
		String content = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			JSONObject jData = new JSONObject(result);
			id = jData.getInt("id");
			type = jData.getInt("type");
			content = jData.getString("content");
			MessageData c = new MessageData();
			c.content = content;
			c.time = sdf.format(d);
			// switch (type) {
			// case 0:
			// Self.current().getCustomer().get(Self.current().cMap.get(id))
			// .getMessage().add(c);
			// break;
			// case 1:
			// Self.current().getWaiter().get(Self.current().wMap.get(id))
			// .getMessage().add(c);
			// break;
			// case 2:
			// Self.current().getCooker()
			// .get(Self.current().cookerMap.get(id)).getMessage()
			// .add(c);
			// break;
			// case 3:
			// Self.current().getCustomer().get(Self.current().cMap.get(id));
			// break;
			// case 4:
			// Self.current().getCustomer().get(Self.current().cMap.get(id))
			// .getMessage().add(c);
			// break;
			// default:
			// break;
			// }
		} catch (JSONException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}
}
