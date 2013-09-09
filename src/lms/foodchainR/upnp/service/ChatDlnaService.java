package lms.foodchainR.upnp.service;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.StateVariable;

public class ChatDlnaService extends Service {
	Action getAll;
	Action getMessage;
	Action sendMessage;
	StateVariable result;
	StateVariable customerId;

	public ChatDlnaService() {
		setServiceID("Chat");
		setServiceType("Chat");
		setControlURL("");
		setDescriptionURL("");
		result = new StateVariable();
		result.setName("Result");
		result.setDataType("boolean");

		getAll = new Action(getServiceNode());
		getMessage = new Action(getServiceNode());
		sendMessage = new Action(getServiceNode());
	}
}
