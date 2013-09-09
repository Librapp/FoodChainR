package lms.foodchainR.upnp.service;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.StateVariable;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-12-27
 * @description 大厅状态DLNAservice
 * @changeLog
 */
public class TableDlnaService extends BaseDlnaService {
	Action getRinfo;
	Action getAllSeat;
	Action getSeatById;
	Action setSeat;

	Argument Rinfo;
	Argument CurrentSeat;
	Argument AllSeat;
	Argument Time;
	Argument CustomerId;
	Argument SeatId;

	StateVariable customerId;
	StateVariable info;
	StateVariable seat;
	StateVariable seatId;
	// 订座位时间
	StateVariable time;

	public TableDlnaService() {
		setControlURL("/service/table/control");
		setSCPDURL("/service/table/tableServer.xml");
		setEventSubURL("/service/table/eventSub");
		setServiceID("Table");
		setServiceType("Table");

		StateVariable menudata = new StateVariable();
		menudata.setName("table");
		menudata.setDataType("json");
		addStateVariable(menudata);

		StateVariable menutype = new StateVariable();
		menudata.setName("type");
		menudata.setDataType("int");
		addStateVariable(menutype);

		Argument Menu = new Argument();
		Menu.setRelatedStateVariableName("table");
		Menu.setDirection(Argument.OUT);

		Argument MenuType = new Argument();
		MenuType.setRelatedStateVariableName("type");
		MenuType.setDirection(Argument.IN);

		Action getMenu = new Action(getServiceNode());
		addAction(getMenu);

		customerId = new StateVariable();
		customerId.setName("customerId");
		customerId.setDataType("Int");

		info = new StateVariable();
		info.setName("info");
		info.setDataType("json");

		seat = new StateVariable();
		seat.setName("seat");
		seat.setDataType("json");

		time = new StateVariable();
		time.setName("time");
		time.setDataType("String");

		Rinfo = new Argument(getServiceNode());
		Rinfo.setRelatedStateVariableName("");

		getRinfo = new Action(getServiceNode());

		this.addAction(getRinfo);
	}
}
