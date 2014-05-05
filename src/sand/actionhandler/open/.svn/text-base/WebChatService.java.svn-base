package sand.actionhandler.open;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sand.actionhandler.basic.GlobalAH;
import sand.actionhandler.system.ActionHandler;
import sand.annotation.AccessControl;
import sand.annotation.Ajax;
import sand.annotation.NeedLogin;
import sand.depot.tool.system.InfoException;
import tool.dao.BizObject;

@AccessControl("no")
public class WebChatService extends ActionHandler {

	protected String _wxID="" ;  //当前的微信id
	static Logger logger = Logger.getLogger(WebChatService.class);
	public WebChatService(HttpServletRequest req, HttpServletResponse res) {
		super(req, res);
		// TODO Auto-generated constructor stub
	}
	
	 
	/**
	 * 接入
	 */
	
	@Ajax
	public String access(){  
        String echostr=this.getParameter("echostr");  //微信认证随机字符串
        if(null==echostr||echostr.isEmpty()){  
            return responseMsg();  
        }else{  
            if(this.checkSignature()){  
                //this.print(echostr);  
                return echostr;
            }else{  
                //this.print("error");      
                return "error";
            }  
        }  
    }  
	@Ajax
	public String getAccessToken(){
		return WebChatKit.getAccessToken();//.textTpl 
	}
	@Ajax
	public String getMenu(){
		return WebChatKit.getMenu();
	} 	
	@Ajax
	public String createMenu(){
		return WebChatKit.createMenu();
	} 
	@Ajax
	public String getUserInfo(){
		return WebChatKit.getUserInfo(this.getParameter("openid")).toString();
	} 	
	public void boundCard(){
		logger.info("_wxId is "+_wxID);
		this._nextUrl="/weixin/v2.0/bind_card.jsp";
	}
	public void boundMember(){
		logger.info("_wxId is "+_wxID);
		this._nextUrl="/weixin/v2.0/bind_member.jsp";
	}
	public void queryCard(){
		logger.info("_wxId is "+_wxID);
		this._nextUrl="/weixin/v2.0/money.jsp";
		
	}
	public void queryLog(){
		logger.info("_wxId is "+_wxID);
		this._nextUrl="/weixin/v2.0/log.jsp";
		
	}
	public void modPass(){
		logger.info("_wxId is "+_wxID);
		this._nextUrl="/weixin/v2.0/mod_pwd.jsp";
		
	}
	public void charge(){
		logger.info("_wxId is "+_wxID);
		this._nextUrl="/weixin/v2.0/mobile.jsp";
		
	}
	//自动回复内容  
    public String responseMsg() {  
    		BizObject b = WebChatKit.read(_request);
    		logger.info("b is "+b);
//            String eventKey = b.getString("EventKey");
//            String wxid=b.getString("FromUserName");
            
            if(b==null)
            	return "";
            if(b.getString("msgtype").equals("event")){
                //处理菜单事件
                b=WebChatProcessor.eventProcess(b);
            	
            }
            
            else if(b.getString("msgtype").equals("text")){
                //处理普通文本
                b=WebChatProcessor.textProcess(b);
           //     return " <xml><ToUserName><![CDATA[o23_xt3S2UHyJLombtu860WQj20Y]]></ToUserName><FromUserName><![CDATA[gh_5bdbe9589628]]></FromUserName><CreateTime>1398758597</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/dIvyelSpNbkbCbibniaGbHsjRZzLPy6ZNibyB6H5Y2IFIiakkwoaQzkdAd7rFTNZSUp9wEpBn2C0A8S99LLgZ0JAIw/0]]></PicUrl><MsgId>6007622429317297719</MsgId><MediaId><![CDATA[0ScwNYrc1mcWY_xztaFGKAbMZL-Z1Adnrryq0XuIIc321ofBz_cA90-5n1MKatjK]]></MediaId></xml>";
                
            }
            
            //String msgType = "text";  
            //String contentStr = WebChatKit.createUrl(eventKey, wxid) ;
            
            
            return WebChatKit.render(b);

    }  
    
  
	  //微信接口验证  
    public boolean checkSignature(){  
        String signature = this.getParameter("signature");  
        String timestamp = this.getParameter("timestamp");  
        String nonce = this.getParameter("nonce");  
        String token=WebChatKit.TOKEN;  
        String[] tmpArr={token,timestamp,nonce};  
        Arrays.sort(tmpArr);  
        String tmpStr=WebChatKit.ArrayToString(tmpArr);  
        tmpStr=WebChatKit.SHA1Encode(tmpStr);  
        if(tmpStr.equalsIgnoreCase(signature)){  
            return true;  
        }else{  
            return false;  
        }  
    }  
	/**
	 * 初始化，这里要做验证签名
	 */
    public void initial(){
    	super._msgUrl="/weixin/v2.0/msg.jsp";
    	//_request;
    	//super.i
    	//如果是握手方法，直接进入握手
    //	_oskit= new OpenServiceKit();
    	
    	//if()
    	
    	if(this.getCurMethod().equals("access")) return;
    	if(this.getCurMethod().equals("createMenu")) return;
    	if(this.getCurMethod().equals("getMenu")) return;
    //	else{
    		
    		//检查令牌
    		//如果令牌有效
    		//取密钥
    		//检查签名
    		//更新令牌最后访问时间
    	HttpSession s =_request.getSession(false);
    	logger.info("session is "+s);
    	if(s!=null) logger.info("session id is "+s.getId());
    		String token=(String) _request.getSession().getAttribute("token");
    		if(StringUtils.isBlank(token)){
    			
    			token=this.getParameter("token");	
    			logger.info("no token , try to get from session  "+token);
    			if(!StringUtils.isBlank(token)) 			_request.getSession(false).setAttribute("token", token);
    		}
    		
    		logger.info("token is "+token);
    		try {
				_wxID=WebChatKit.getWxIdByToken(token);
				logger.info("_wxID is "+_wxID);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("",e);
				throw new OpenServiceException("2001") ; //系统异常
			}
    		
  		
    //	}
    	
    }

}
