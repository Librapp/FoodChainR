package lms.foodchainR.upnp.service;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.StateVariable;

public class BillDlnaService extends Service {
	// ��ȡ�˵�
	Action getBill;
	// �¶���
	Action setBill;
	// ��Ӳ�
	Action addCase;
	// ɾ���
	Action delCase;
	StateVariable result;
	StateVariable seat;
	StateVariable seatId;
	StateVariable customerId;
	StateVariable menu;
	StateVariable bill;

	public BillDlnaService() {
		setServiceID("Bill");
		setServiceType("Bill");
		setControlURL("");
		setDescriptionURL("");
		result = new StateVariable();
		result.setName("Result");
		result.setDataType("boolean");

		seat = new StateVariable();
		seat.setName("Seat");
		seat.setDataType("String");

		getBill = new Action(getServiceNode());
		setBill = new Action(getServiceNode());
		addCase = new Action(getServiceNode());
		delCase = new Action(getServiceNode());

	}
}
