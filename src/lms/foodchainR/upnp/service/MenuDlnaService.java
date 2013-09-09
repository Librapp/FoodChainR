package lms.foodchainR.upnp.service;

import lms.foodchainR.util.FileInfoUtils;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.StateVariable;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-23
 * @description 菜单DLNAservice
 * @changeLog
 */
public class MenuDlnaService extends BaseDlnaService {
	public static final String GETMENU = "GetMenu";
	public static final String FILTER = "filter";
	public StateVariable menuData;

	public MenuDlnaService() {
		setControlURL("FCR/service/menu/control");
		setSCPDURL("FCR/service/menu/menuServer.xml");
		setEventSubURL("FCR/service/menu/eventSub");
		setServiceID("Menu");
		setServiceType("Menu");

		menuData = new StateVariable();
		menuData.setName("menu");
		menuData.setDataType("json");
		addStateVariable(menuData);

		Argument Menu = new Argument();
		Menu.setRelatedStateVariableName("menu");
		Menu.setDirection(Argument.OUT);

		Argument MenuType = new Argument();
		MenuType.setRelatedStateVariableName("type");
		MenuType.setDirection(Argument.IN);

		Action getMenu = new Action(getServiceNode());
		getMenu.setName(GETMENU);
		ArgumentList al = getMenu.getArgumentList();
		Argument menuAr = new Argument();
		menuAr.setDirection(Argument.IN);
		menuAr.setName(FILTER);
		menuAr.setRelatedStateVariableName("menu");
		al.add(menuAr);
		getMenu.setArgumentList(al);
		addAction(getMenu);
		FileInfoUtils.writeFile(getServiceNode().toString().getBytes(),
				"FCR/service/menu", "menuServer.xml");
	}
}
