package lms.foodchainR.service;

import java.util.UUID;

import lms.foodchainR.data.CustomerData;
import lms.foodchainR.data.EmployeeData;
import lms.foodchainR.data.OtherData;
import lms.foodchainR.data.Self;

import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.RootDescription;
import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.util.Debug;
import org.cybergarage.xml.Node;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

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
	private static SearchDeviceTask searchDeviceTask;

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
		new Thread(new Runnable() {

			@Override
			public void run() {
				initServer();
			}
		}).start();
		initControlPoint();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (SEARCH_DEVICE.equals(intent.getAction())) {
			// if (searchDeviceTask != null) {
			// searchDeviceTask.cancel(true);
			// searchDeviceTask = null;
			// }
			// searchDeviceTask = new SearchDeviceTask();
			// searchDeviceTask.execute(RestaurantData.local().isLocal);
		}
		return Service.START_NOT_STICKY;
	}

	private void initServer() {
		Node root = new Node(RootDescription.ROOT_ELEMENT);
		// root.setNameSpace("", RootDescription.ROOT_ELEMENT_NAMESPACE);
		// Node spec = new Node(RootDescription.SPECVERSION_ELEMENT);
		// Node maj = new Node(RootDescription.MAJOR_ELEMENT);
		// maj.setValue("1");
		// Node min = new Node(RootDescription.MINOR_ELEMENT);
		// min.setValue("0");
		// spec.addNode(maj);
		// spec.addNode(min);
		// root.addNode(spec);

		Node device = new Node(Device.ELEM_NAME);
		root.addNode(device);
		d = new lms.foodchainR.upnp.Device(root, device);
		d.setFriendlyName(Self.current().name);
		d.setActionListener(this);
		// d.setUDN("uuid:" + getMyUUID());
		// d.setUserData(RestaurantData.self().id);
		// d.setDeviceType(OtherData.RESTAURANTDEVICETYPE);
		// d.setDescriptionURI(OtherData.DESCRIPTIONURL);
		// if (FileInfoUtils.writeFile(d.getRootNode().toString().getBytes(),
		// "FCR", "description.xml"))
		d.start();
	}

	private String getMyUUID() {
		final TelephonyManager tm = (TelephonyManager) getBaseContext()
				.getSystemService(TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		if (Debug.isOn())
			Log.d("debug", "uuid=" + uniqueId);
		return uniqueId;
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

	private class SearchDeviceTask extends AsyncTask<Object, Void, Void> {

		@Override
		protected Void doInBackground(Object... params) {
			if (!started) {
				c.start("FC");
				started = true;
			} else {
				c.search("FC");
			}
			return null;
		}
	}

}
