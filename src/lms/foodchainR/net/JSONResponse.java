package lms.foodchainR.net;

import java.util.ArrayList;
import java.util.List;

import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.data.SeatData;
import lms.foodchainR.data.Self;
import lms.foodchainR.data.TableData;
import lms.foodchainR.data.TableStyleData;
import lms.foodchainR.service.MenuService;
import lms.foodchainR.service.TableService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2013-5-22
 * @description 请求响应基类
 * @changeLog
 */
public class JSONResponse {
	private final static String MSG = "msg";
	private final static String CODE = "code";
	private final static int SUCCESS = 0;
	private final static int FAIL = 1;

	public static JSONObject result(String msg) {
		JSONObject data = new JSONObject();
		try {
			if (msg.equals(""))
				data.put(CODE, SUCCESS);
			else
				data.put(CODE, FAIL);
			data.put(MSG, msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	};

	public static JSONObject menuData() {
		JSONObject data = new JSONObject();
		String msg = "";
		try {
			data.put(CODE, SUCCESS);
			data.putOpt("restaurantId", Self.current().id);
			JSONArray array = new JSONArray();
			ArrayList<CaseStyleData> list = MenuService.getStyle();
			for (CaseStyleData csd : list) {
				array.put(caseStyleDataResponse(csd));
			}
			if (msg.equals(""))
				data.putOpt("caseStyleList", array);
			else
				data.put(CODE, FAIL);
			data.put(MSG, msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	};

	private static JSONObject caseStyleDataResponse(CaseStyleData csd) {
		JSONObject data = new JSONObject();
		try {
			data.putOpt("id", csd.styleId);
			data.putOpt("name", csd.name);
			JSONArray array = new JSONArray();
			List<CaseData> list = csd.getList();
			for (CaseData c : list) {
				array.put(caseDataResponse(c));
			}
			data.putOpt("caseList", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	};

	public static JSONObject caseStyleDetailResponse(String msg,
			CaseStyleData csd) {
		JSONObject data = new JSONObject();
		try {
			if (msg.equals("")) {
				data.put(CODE, SUCCESS);
				data.putOpt("id", csd.styleId);
				data.putOpt("name", csd.name);
				data.putOpt("startTime", csd.startTime);
				data.putOpt("endTime", csd.endTime);
				List<CaseData> list = csd.getList();
				JSONArray array = new JSONArray();
				for (CaseData c : list) {
					array.put(caseDataResponse(c));
				}
				data.putOpt("caseList", array);
			} else
				data.put(CODE, FAIL);
			data.put(MSG, msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	};

	private static JSONObject caseDataResponse(CaseData c) {
		JSONObject data = new JSONObject();
		try {
			data.putOpt("id", c.id);
			data.putOpt("caseId", c.caseId);
			data.putOpt("name", c.name);
			data.putOpt("billId", c.billId);
			data.putOpt("cookerId", c.cookerId);
			data.putOpt("cookTime", c.cookTime);
			data.putOpt("count", c.count);
			data.putOpt("family", c.family);
			data.putOpt("orderId", c.orderId);
			data.putOpt("state", c.state);
			data.putOpt("style", c.style);
			data.putOpt("time", c.time);
			data.putOpt("type", c.type);
			data.putOpt("waiterId", c.waiterId);
			data.putOpt("weekday", c.weekday);
			data.putOpt("waitTime", c.waitTime);
			data.putOpt("intro", c.intro);
			data.putOpt("mark", c.mark);
			data.putOpt("message", c.message);
			data.putOpt("orderTime", c.orderTime);
			data.putOpt("picPath", c.picPath);
			data.putOpt("price", c.price);
			data.putOpt("special", c.special);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	};

	public static JSONObject caseDetailResponse(String msg, CaseData c) {
		JSONObject data = new JSONObject();
		try {
			if (msg.equals("")) {
				data.put(CODE, SUCCESS);
				data.putOpt("id", c.id);
				data.putOpt("caseId", c.caseId);
				data.putOpt("name", c.name);
				data.putOpt("billId", c.billId);
				data.putOpt("cookerId", c.cookerId);
				data.putOpt("cookTime", c.cookTime);
				data.putOpt("count", c.count);
				data.putOpt("family", c.family);
				data.putOpt("orderId", c.orderId);
				data.putOpt("state", c.state);
				data.putOpt("style", c.style);
				data.putOpt("time", c.time);
				data.putOpt("type", c.type);
				data.putOpt("waiterId", c.waiterId);
				data.putOpt("weekday", c.weekday);
				data.putOpt("waitTime", c.waitTime);
				data.putOpt("intro", c.intro);
				data.putOpt("mark", c.mark);
				data.putOpt("message", c.message);
				data.putOpt("orderTime", c.orderTime);
				data.putOpt("picPath", c.picPath);
				data.putOpt("price", c.price);
				data.putOpt("special", c.special);
			} else
				data.put(CODE, FAIL);
			data.put(MSG, msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	};

	public static JSONObject restaurantInfo() {
		JSONObject data = new JSONObject();
		try {
			data.put(CODE, SUCCESS);
			data.put(MSG, "");
			data.putOpt("name", Self.current().name);
			data.putOpt("id", Self.current().id);
			data.putOpt("headPic", Self.current().headPic);
			data.putOpt("address", Self.current().address);
			data.putOpt("tel", Self.current().tel);
			data.putOpt("sms", Self.current().sms);
			data.putOpt("opentime", Self.current().opentime);
			data.putOpt("credit", Self.current().credit);
			// data.putOpt("seatCount", Self.current().seatCount);
			data.putOpt("state", Self.current().state);
			data.putOpt("email", Self.current().email);
			// data.putOpt("freeseat", Self.current().freeseat);
			// data.putOpt("waitNumber", Self.current().waitNumber);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static JSONObject hallInfo() {
		JSONObject data = new JSONObject();
		try {
			data.put(CODE, SUCCESS);
			JSONArray array = new JSONArray();
			for (TableStyleData tsd : TableService.getTableStyles()) {
				array.put(tableStyleData(tsd));
			}
			data.putOpt("tableStyleList", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static JSONObject tableStyleData(TableStyleData ts) {
		JSONObject data = new JSONObject();
		try {
			data.putOpt("id", ts.styleId);
			data.putOpt("tableCount", ts.count);
			data.putOpt("seatCount", ts.seatCount);
			data.putOpt("icon", ts.icon);
			data.putOpt("pic", ts.pic);
			JSONArray array = new JSONArray();
			for (TableData td : ts.getTable()) {
				array.put(tableData(td));
			}
			data.putOpt("tableList", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static JSONObject tableData(TableData td) {
		JSONObject data = new JSONObject();
		try {
			data.putOpt("id", td.tableId);
			data.putOpt("seatCount", td.seatCount);
			data.putOpt("styleId", td.styleId);
			data.putOpt("state", td.state);
			data.putOpt("waiterId", td.waiterId);
			data.putOpt("customerId", td.customerId);
			data.putOpt("customerName", td.customerName);
			data.putOpt("customerPic", td.customerPic);
			data.putOpt("customerURL", td.customerURL);
			JSONArray array = new JSONArray();
			for (SeatData sd : td.getSeat()) {
				array.put(seatData(sd));
			}
			data.putOpt("seatList", array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static JSONObject seatData(SeatData sd) {
		JSONObject data = new JSONObject();
		try {
			data.putOpt("seatId", sd.seatId);
			data.putOpt("tableId", sd.tableId);
			data.putOpt("styleId", sd.styleId);
			data.putOpt("state", sd.state);
			data.putOpt("customerId", sd.customerId);
			data.putOpt("customerName", sd.customerName);
			data.putOpt("customerPic", sd.customerPic);
			data.putOpt("customerURL", sd.customerURL);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
}
