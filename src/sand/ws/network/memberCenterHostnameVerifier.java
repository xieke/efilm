package sand.ws.network;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class memberCenterHostnameVerifier implements HostnameVerifier{

	public boolean verify(String s, SSLSession sslsession) {
		return true;
	}
	
}

