package lms.foodchainR.service;

import lms.foodchainR.data.CustomerData;
import lms.foodchainR.data.EmployeeData;
import lms.foodchainR.data.OtherData;
import lms.foodchainR.data.Self;
import lms.foodchainR.upnp.ControlPoint;
import lms.foodchainR.util.FileInfoUtils;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.RootDescription;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.xml.Node;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-15
 * @description DlnaService
 * @changeLog
 */
public class DlnaService extends Service implements DeviceChangeListener,
		ActionListener {

	public static final String NEW_DEVICES_FOUND = "newDeviceFound";
	public static final String SEARCH_DEVICE = "search_device";
	private final IBinder binder = new DlnaServiceBinder();
	private ControlPoint c;
	// CP有没有启动
	private static boolean started = false;
	private lms.foodchainR.upnp.Device d;

	public class DlnaServiceBinder extends Binder {
		public DlnaService getService() {
			return DlnaService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onCreate() {
		initServer();
		initControlPoint();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (DlnaService.SEARCH_DEVICE.equals(intent.getAction())) {
			multicastSearch();
		}
		return Service.START_NOT_STICKY;
	}

	private void initServer() {
		Node root = new Node(RootDescription.ROOT_ELEMENT);
		root.setNameSpace("", RootDescription.ROOT_ELEMENT_NAMESPACE);
		Node spec = new Node(RootDescription.SPECVERSION_ELEMENT);
		Node maj = new Node(RootDescription.MAJOR_ELEMENT);
		maj.setValue("1");
		Node min = new Node(RootDescription.MINOR_ELEMENT);
		min.setValue("0");
		spec.addNode(maj);
		spec.addNode(min);
		root.addNode(spec);

		Node device = new Node(Device.ELEM_NAME);
		root.addNode(device);

		d = new lms.foodchainR.upnp.Device(root, device);
		d.setFriendlyName(Self.current().name);
		d.setActionListener(this);
		// TODO
		d.setDeviceType(OtherData.RESTAURANTDEVICETYPE);
		d.setDescriptionURI(OtherData.DESCRIPTIONURL);
		if (FileInfoUtils.writeFile(d.getRootNode().toString().getBytes(),
				"FCR", "description.xml"))
			d.start();
	}

	private void initControlPoint() {
		c = new ControlPoint();
		c.addDeviceChangeListener(this);
		c.addSearchResponseListener(new SearchResponseListener() {
			@Override
			public void deviceSearchResponseReceived(SSDPPacket ssdpPacket) {

			}
		});
	}

	public void multicastSearch() {
		if (!started) {
			c.start();
			started = true;
		} else {
			c.search();
		}
	}

	@Override
	public void deviceAdded(Device dev) {
		if (OtherData.CUSTOMERDEVICETYPE.equals(dev.getDeviceType())) {
			// TODO 发现新顾客
			CustomerData c = new CustomerData(dev);
		} else if (OtherData.WAITERDEVICETYPE.equals(dev.getDeviceType())) {
			// TODO 发现服务生
			EmployeeData c = new EmployeeData(dev, EmployeeData.WAITER);
		} else if (OtherData.CLEANERDEVICETYPE.equals(dev.getDeviceType())) {
			// TODO 发现保洁员
			EmployeeData c = new EmployeeData(dev, EmployeeData.CLEANER);
		} else if (OtherData.COOKERDEVICETYPE.equals(dev.getDeviceType())) {
			// TODO 发现厨师
			EmployeeData c = new EmployeeData(dev, EmployeeData.COOKER);
		}
		// sendBroadcast(new Intent(NEW_DEVICES_FOUND));
	}

	@Override
	public void deviceRemoved(Device dev) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean actionControlReceived(Action action) {
		ArgumentList argumentList = action.getArgumentList();
		if (action.getName().equals("SetSeat")) {
			// TODO
		}
		action.setArgumentList(argumentList);
		return true;
	}

}
