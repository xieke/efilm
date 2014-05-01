package sand.ws.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import tool.basic.DateUtils;

public class SSLService {
	
	Logger logger = Logger.getLogger("INTERFACE");
	Logger log = Logger.getLogger(SSLService.class);
	
	/**
	 * 发送http或https请求,https请求只支持单向认证
	 * @param urlStr 请求的url
	 * @param contentType 上下文类型
	 * @param content 发送内容，如果为空使用get请求否则使用post请求
	 * @return String
	 */
	public  String sendRequest(String urlStr, String contentType,String content,Map map) {
		URL url = null;
		URLConnection connection = null;
		boolean isHttps=false;
		long time = new Date().getTime();
		try {
			logger.info("");
			logger.info("-------------------------");
			logger.info("request:"+time);
			logger.info("url:"+urlStr);
			if(map==null) logger.info("body:"+urlStr+"?"+content);
			else logger.info("body:"+urlStr+"?"+linkString(map));
			url = new URL(urlStr);
			connection =  url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			isHttps= connection instanceof HttpsURLConnection; //是否为HTTPS请求
			if(isHttps){
				TrustManager [] trustAllCerts=new TrustManager[1];
					//
				trustAllCerts[0]=	new memberCenterX509TrustManager();
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				((HttpsURLConnection) connection).setSSLSocketFactory(sc.getSocketFactory());
				((HttpsURLConnection) connection).setHostnameVerifier(new memberCenterHostnameVerifier());
			}
			   
			connection.setUseCaches(false);
			connection.setConnectTimeout(10000);//连接30秒超时
			connection.setReadTimeout(10000);//读返回结果30秒超时
			connection.setRequestProperty("Content-Type",contentType);
			connection.connect();
			if(content!=null&&!content.trim().equals("")){//向服务器发送数据
				DataOutputStream out = new DataOutputStream(connection.getOutputStream());
				out.write(content.getBytes("utf-8"));
				logger.info("request Date:"+new Date()+"-----"+DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
				logger.info("Date: "+new Date(connection.getDate()));
				logger.info("Content-Type: "+connection.getContentType());
				logger.info("Content-Length: "+connection.getContentLength()); 
				logger.info("-----------------------------------------");
				out.flush();
				out.close();
			}
			int responseCode=0;//HTTP状态码
			if(isHttps){
				responseCode=((HttpsURLConnection) connection).getResponseCode();
			}else{
				responseCode=((HttpURLConnection) connection).getResponseCode();
			}
			
			InputStream input=null;//读返回结果流
			if(responseCode==HttpURLConnection.HTTP_OK){
				input=connection.getInputStream();
			}else{
				if(isHttps){
					input=((HttpsURLConnection) connection).getErrorStream();
				}else{
					input=((HttpURLConnection) connection).getErrorStream();
				}
					
			}
			StringBuffer buffer = new StringBuffer();
			if(input!=null){
				BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
				String line = "";
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				reader.close();
			}
			logger.info("-------------------------");
			logger.info("response:"+time);
			logger.info("response Date:"+new Date()+"-----"+DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
			logger.info("Date: "+new Date(connection.getDate()));
			logger.info("response code:"+responseCode);
			logger.info("Content-Type: "+connection.getContentType());
			logger.info("body:"+buffer.toString());
			logger.info("-----------------------------------------");
			logger.info("-----------------------------------------");
			logger.info("");
			return buffer.toString();
		} catch (Throwable e) {
			StackTraceElement []ste=e.getStackTrace();
			StringBuilder builder=new StringBuilder(e.getClass().toString()+":"+e.getMessage()+"\n");
			for(int i=0;i<ste.length;i++){
				builder.append("\t").append(ste[i].toString()).append("\n");
			}
			log.info("连接接口失败"+builder.toString());
			log.info("{result:false,code:'9999',error:'连接接口失败,请稍后再试!'}");
			logger.info(builder.toString());
			logger.info("{result:false,code:'9999',error:'连接接口失败,请稍后再试!'}");
			return "{result:false,code:'9999',error:'连接接口失败,请稍后再试!'}";
		} finally {
			if (connection != null) {
				if(isHttps){
					((HttpsURLConnection) connection).disconnect();
					logger.info("disconnect "+connection);
				}else{
					((HttpURLConnection) connection).disconnect();
					logger.info("disconnect "+connection);
				}
			}
		}
	}
	
	public String linkString(Map map){
		Map pwds = new HashMap();
		pwds.put("pwd", "******");
		pwds.put("opwd", "******");
		
		
		List keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        String prestr = "";
        for(int i = 0; i < keys.size(); i++){
            String key = (String)keys.get(i);
            String value = (String)map.get(key);
            if(i == keys.size() - 1)
            	if(pwds.get(key)!=null) prestr = (new StringBuilder(String.valueOf(prestr))).append(key).append("=").append(pwds.get(key)).toString();
            	else prestr = (new StringBuilder(String.valueOf(prestr))).append(key).append("=").append(value).toString();
            else
            	if(pwds.get(key)!=null) prestr = (new StringBuilder(String.valueOf(prestr))).append(key).append("=").append(pwds.get(key)).append("&").toString();
            	else prestr = (new StringBuilder(String.valueOf(prestr))).append(key).append("=").append(value).append("&").toString();
        }

        return prestr;
	}

}
