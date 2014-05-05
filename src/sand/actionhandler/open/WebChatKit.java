package sand.actionhandler.open;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.e;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sand.annotation.Ajax;
import sand.basic.Global;
import sand.depot.tool.system.InfoException;
import sand.ws.network.JsonTool;
import sand.ws.network.SSLService;
import tool.dao.BizObject;
import tool.dao.UidGenerator;

public class WebChatKit {
	
	public static String TOKEN="haodelawson"; 
	public static String APP_ID="wxc1e7dabee2a0d0fd"; 
	public static String APP_SECRET="b3b1884fbc0ad1bbbf5b00224fd5b9e6";
	
	static String access_token="";
	static long token_time=0;
	static Logger logger = Logger.getLogger(WebChatKit.class);
	static String url="http://zfeeee.gicp.net";
    static String textTpl = "<xml>"+  
            "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
            "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
            "<CreateTime>%3$s</CreateTime>"+  
            "<MsgType><![CDATA[%4$s]]></MsgType>"+  
            "<Content><![CDATA[%5$s]]></Content>"+            
            "<Image>"+
            "<MediaId><![CDATA[%6$s]]></MediaId>"+
            "</Image>"+            
            "</xml>";    
	
//    //向请求端发送返回数据  
//    public void print(String content){  
//        try{  
//            final_response.getWriter().print(content);  
//            final_response.getWriter().flush();  
//            final_response.getWriter().close();  
//        }catch(Exception e){  
//              
//        }  
//    }  
//	 @Ajax
//	 public static String getTokenqjdble(String userid){
//        //用户退出时，sso也退出
//        Map<String,String> m = Global.SSoCenter;
//        if(m.containsKey(userid))
//       	 return m.get(userid).toString();
//        
//        return createToken(userid);
//	 }
//    
    
	public static String createToken(String userid) {

		String sign = UidGenerator.getUUId();

		Global.SSoCenter.put(sign,userid + "." + System.currentTimeMillis());
		// currentUser().setSSoToken(sign);
		logger.info("ssoCenter size :"+Global.SSoCenter.size());

		return sign;
	}
    public static String createUrl(String method,String wxid){
    	String token=createToken(wxid);
    	return url+method+"?token="+token;
    }
    //数组转字符串  
    public static String ArrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
    //sha1加密  
    public static String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");
           
         //  resultString = new BigInteger(1, md.digest(resultString.getBytes())).toString(16);  
           resultString = byte2hexString2(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  

    /**
     * 这个似乎很简洁
     * @param bytes
     * @return
     */
    public static final String byte2hexString2(byte[] bytes) {  
    	
    	return new BigInteger(1, bytes).toString(16);  
    }  


	
    public static String getAccessToken(){

    	
    	if(!access_token.equals("")){
    		
    		long time = (System.currentTimeMillis()-token_time)/1000;
    		if(time<7200){
    			return access_token;
    		}
    	}
    	
    	 //https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
    	String contentType="application/x-www-form-urlencoded";
    	//SSLService sslService;
    	String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;
    	SSLService sslService=new SSLService();
    	String str = sslService.sendRequest(url,contentType, "",null);
		BizObject biz = JsonTool.toBizObj(str);
		logger.info("biz is :"+biz.toString());
		access_token=biz.getString("access_token");
		token_time=System.currentTimeMillis();
		
		return access_token;

    }
    public final String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    }  
    
    public static String  render(BizObject b){
    	String time = new Date().getTime()+""; 
            String resultStr = textTpl.format(textTpl, b.getString("fromusername"), b.getString("toUsername"),time, b.getString("msgType"),b.getString("content"),b.getString("MediaId") );
            logger.info("return:"+resultStr);
            return resultStr;
    }
    
    public static  BizObject read(HttpServletRequest req){
        String postStr=null;  
        try{  
            postStr=readStreamParameter(req.getInputStream());  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        logger.info(postStr);
        //System.out.println(postStr);  
        if (null!=postStr&&!postStr.isEmpty()){  
            Document document=null;  
            try{  
                document = DocumentHelper.parseText(postStr);  
            }catch(Exception e){  
                e.printStackTrace();  
                logger.error("error:",e);
            }  
            if(null==document){  
                //this.print("");  
                return null;
                //return;  
            }  
            Element root=document.getRootElement();  
            String fromUsername = root.elementText("FromUserName");  
            String toUsername = root.elementText("ToUserName");  
            String content = root.elementTextTrim("Content");  
            String createtime = root.elementTextTrim("CreateTime");  
            String msgType = root.elementTextTrim("MsgType");
            String event = root.elementTextTrim("Event");
            String eventKey = root.elementTextTrim("EventKey");
            String ticket = root.elementTextTrim("Ticket");
            String msgId = root.elementTextTrim("MsgId");
            String Latitude = root.elementTextTrim("Latitude");
            String Longitude = root.elementTextTrim("Longitude");
            String Precision = root.elementTextTrim("Precision");
            String time = new Date().getTime()+"";  
            BizObject b = new BizObject();
            b.set("FromUserName", fromUsername);
            b.set("ToUserName", toUsername);
            b.set("Content", content);
            b.set("createtime",createtime);
            b.set("msgtype", msgType);
            b.set("event", event);
            b.set("eventkey", eventKey);
            b.set("ticket", ticket);
            b.set("Latitude", Latitude);
            b.set("msgid", msgId);
            b.set("Longitude", Longitude);
            b.set("Precision", Precision);
            //b.set("msgtype", o);
            b.set("time", time);
            return b;

        }
		return null;
    }
    public static BizObject getUserInfo(String openid){
    	
    	String contentType="application/x-www-form-urlencoded";
    	String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getAccessToken()+"&openid="+openid;
    	//String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken();
			//+"&body="+menu.toString();
    	SSLService sslService=new SSLService();
		String str = sslService.sendRequest(url,contentType, null,null);
		BizObject biz = JsonTool.toBizObj(str);
	
		return biz;

    }
    public static String getMenu(){
       	String contentType="application/x-www-form-urlencoded";
       	//SSLService sslService;
       	url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+getAccessToken();
       	//url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken();
       			//+"&body="+menu.toString();
       	SSLService sslService=new SSLService();
       	String str = sslService.sendRequest(url,contentType, null,null);
    		BizObject biz = JsonTool.toBizObj(str);
        	
        	return biz.toString();

    }
    public static String createMenu(){
    	
    	BizObject menu=new BizObject();

    	
    	ArrayList<BizObject> v = new ArrayList(); // menu list 
    	ArrayList<BizObject> v1 = new ArrayList(); // menu list 2


    	
    	BizObject b = new BizObject("");
    	b.set("type", "menu");
    	b.set("name", "影片");    	
    	v.add(b);
    	
    	String url = createUrl("/open.FilmService.home", "");
    	BizObject b1 = new BizObject("");
    	b1.set("type", "view");
    	b1.set("name", "首页");
    	b1.set("url", url);
    	//b.set("key", "adfad1f");
    	v1.add(b1);    	
    	
    	url = createUrl("/open.FilmService.chooseCinema", "");
    	 b1=new BizObject();
    	b1.set("type", "view");
    	b1.set("name", "选择影院");
    	b1.set("url",url);    	

    	v1.add(b1);

    	url = createUrl("/open.FilmService.chooseFilm", "");
    	b1=new BizObject();
    	b1.set("type", "view");
    	b1.set("name", "选择影片");
    	b1.set("url", url);    	

    	v1.add(b1);
    	   	
    	b.set("sub_button", v1);
    	
    	
    	
    	
    	b = new BizObject("");
    	b.set("type", "menu");
    	b.set("name", "活动");
    	//b.set("key", "adfad1f");
    	v.add(b);
    	
    	
    	v1 = new ArrayList();
    	
    	b1=new BizObject();
    	b1.set("type", "click");
    	b1.set("name", "活动1");
    	b1.set("key", "nearby");    	
    	v1.add(b1);
    	
    	b1=new BizObject();
    	b1.set("type", "click");
    	b1.set("name", "最新活动");
    	b1.set("key", "news");    	
    	v1.add(b1);
    	
    	b.set("sub_button", v1);

    	
    	
    	b = new BizObject("");
    	b.set("type", "menu");
    	b.set("name", "我的");
    	//b.set("key", "adfad2f");
    	//b1.set("key", "charge");    	
    	v.add(b);
    	
    	v1 = new ArrayList();
    	
    	b1=new BizObject();
    	b1.set("type", "click");
    	b1.set("name", "会员卡");
    	b1.set("key", "charge");    	

    	v1.add(b1);
    	
    	b1=new BizObject();
    	b1.set("type", "click");
    	b1.set("name", "订单");
    	b1.set("key", "cardCharge");    	    	
    	v1.add(b1);
    	
    	b1=new BizObject();
    	b1.set("type", "click");
    	b1.set("name", "活动");
    	b1.set("key", "cardCharge");    	    	
    	v1.add(b1);    	
    	
    	b.set("sub_button", v1);
    	
    	menu.set("button", v);
    	logger.info("menu: "+menu.toString());
   	 //
   	String contentType="application/x-www-form-urlencoded";
   	//SSLService sslService;
   	url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken();
   			//+"&body="+menu.toString();
   	SSLService sslService=new SSLService();
   	String str = sslService.sendRequest(url,contentType, menu.toString(),null);
		BizObject biz = JsonTool.toBizObj(str);
    	
    	return biz.toString();
    	
    }
    //从输入流读取post参数  
    public static String readStreamParameter(ServletInputStream in){  
        StringBuilder buffer = new StringBuilder();  
        BufferedReader reader=null;  
        try{  
            reader = new BufferedReader(new InputStreamReader(in));  
            String line=null;  
            while((line = reader.readLine())!=null){  
                buffer.append(line);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(null!=reader){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return buffer.toString();  
    }  	
    
    
	public static String getWxIdByToken(String token) throws InfoException  {
		
		InfoException t = new InfoException("您的操作已经超时，请点击下方菜单重新生成请求");
		if(StringUtils.isBlank(token))
			return null;

		Map<String, String> m = Global.SSoCenter;

		String uuid = m.get(token);
		
		
		if(uuid==null)  return null; // 令牌失效
		String[] uids = uuid.split("\\.");
		if (uids.length == 2) {
			String userid = uids[0];
			long time = Long.parseLong(uids[1]);
			long min = (System.currentTimeMillis() - time) / 60000;
			logger.info(" min is "+min);
			if (min > Global.wechat_timeout) {
				Global.exit(userid, token);
				return null;
				//throw t; // 令牌失效
			}
			return userid;
		}
		else{
			//throw t; // 令牌失效
			return null;
		}
		//return "";

	}
	
	
	public void getToken(){
		
	}
	
	

}
