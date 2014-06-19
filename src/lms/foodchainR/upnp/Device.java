/******************************************************************
 *
 *	CyberLink for Java
 *
 *	Copyright (C) Satoshi Konno 2002-2004
 *
 *	File: Device.java
 *
 *	Revision:
 *
 *	11/28/02
 *		- first revision.
 *	02/26/03
 *		- URLBase is updated automatically.
 * 		- Description of a root device is returned from the XML node tree.
 *	05/13/03
 *		- URLBase is updated when the request is received.
 *		- Changed to create socket threads each local interfaces.
 *		  (HTTP, SSDPSearch)
 *	06/17/03
 *		- Added notify all state variables when a new subscription is received.
 *	06/18/03
 *		- Fixed a announce bug when the bind address is null on J2SE v 1.4.1_02 and Redhat 9.
 *	09/02/03
 *		- Giordano Sassaroli <sassarol@cefriel.it>
 *		- Problem : bad request response sent even with successful subscriptions
 *		- Error : a return statement is missing in the httpRequestRecieved method
 *	10/21/03
 *		- Updated a udn field by a original uuid.
 *	10/22/03
 *		- Added setActionListener().
 *		- Added setQueryListener().
 *	12/12/03
 *		- Added a static() to initialize UPnP class.
 *	12/25/03
 *		- Added advertiser functions.
 *	01/05/04
 *		- Added isExpired().
 *	03/23/04
 *		- Oliver Newell <newell@media-rush.com>
 *		- Changed to update the UDN only when the field is null.
 *	04/21/04
 *		- Added isDeviceType().
 *	06/18/04
 *		- Added setNMPRMode() and isNMPRMode().
 *		- Changed getDescriptionData() to update only when the NMPR mode is false.
 *	06/21/04
 *		- Changed start() to send a bye-bye before the announce.
 *		- Changed annouce(), byebye() and deviceSearchReceived() to send the SSDP
 *		  messsage four times when the NMPR and the Wireless mode are true.
 *	07/02/04
 *		- Fixed announce() and byebye() to send the upnp::rootdevice message despite embedded devices.
 *		- Fixed getRootNode() to return the root node when the device is embedded.
 *	07/24/04
 *		- Thanks for Stefano Lenzi <kismet-sl@users.sourceforge.net>
 *		- Added getParentDevice().
 *	10/20/04 
 *		- Brent Hills <bhills@openshores.com>
 *		- Changed postSearchResponse() to add MYNAME header.
 *	11/19/04
 *		- Theo Beisch <theo.beisch@gmx.de>
 *		- Added getStateVariable(String serviceType, String name).
 *	03/22/05
 *		- Changed httpPostRequestRecieved() to return the bad request when the post request isn't the soap action.
 *	03/23/05
 *		- Added loadDescription(String) to load the description from memory.
 *	03/30/05
 *		- Added getDeviceByDescriptionURI().
 *		- Added getServiceBySCPDURL().
 *	03/31/05
 *		- Changed httpGetRequestRecieved() to return the description stream using
 *		  Device::getDescriptionData() and Service::getSCPDData() at first.
 *	04/25/05
 *		- Thanks for Mikael Hakman <mhakman@dkab.net>
 *		  Changed announce() and byebye() to close the socket after the posting.
 *	04/25/05
 *		- Thanks for Mikael Hakman <mhakman@dkab.net>
 *		  Changed deviceSearchResponse() answer with USN:UDN::<device-type> when request ST is device type.
 * 	04/25/05
 *		- Thanks for Mikael Hakman <mhakman@dkab.net>
 * 		- Changed getDescriptionData() to add a XML declaration at first line.
 * 	04/25/05
 *		- Thanks for Mikael Hakman <mhakman@dkab.net>
 *		- Added a new setActionListener() and serQueryListner() to include the sub devices. 
 *	07/24/05
 *		- Thanks for Stefano Lenzi <kismet-sl@users.sourceforge.net>
 *		- Fixed a bug of getParentDevice() to return the parent device normally.
 *	02/21/06
 *		- Changed httpRequestRecieved() not to ignore HEAD requests.
 *	04/12/06
 *		- Added setUserData() and getUserData() to set a user original data object.
 *	03/29/08
 *		- Added isRunning() to know whether the device is running.
 * 
 ******************************************************************/

package lms.foodchainR.upnp;

import org.cybergarage.upnp.device.SearchListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.xml.Node;

public class Device extends org.cybergarage.upnp.Device implements
		org.cybergarage.http.HTTPRequestListener, SearchListener {

	/**
	 * @param root
	 * @param device
	 */
	public Device(Node root, Node device) {
		super(root, device);
	}

	public void deviceSearchResponse(SSDPPacket ssdpPacket) {
		String ssdpST = ssdpPacket.getST();

		if (ssdpST == null)
			return;

		String devUSN = getUDN();

		if (ssdpST.equals("FC") == true) {
			String devNT = devUSN;
			int repeatCnt = 3;
			for (int n = 0; n < repeatCnt; n++)
				postSearchResponse(ssdpPacket, ssdpST, devUSN);
		}
	}
}
