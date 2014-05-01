package sand.actionhandler.open;

import java.util.List;


import org.apache.commons.lang3.StringUtils;

import tool.dao.BizObject;

public class WebChatProcessor {
	
	
	
	public static BizObject textProcess(BizObject b){
		String wxid=b.getString("FromUserName");
		String text=b.getString("content");
		 b.set("content", "谢谢你的发言，请继续..");
		if(text.equals("图片")){
			b.set("MsgType", "image");
			b.set("PicUrl", "http://mmbiz.qpic.cn/mmbiz/dIvyelSpNbkbCbibniaGbHsjRZzLPy6ZNibyB6H5Y2IFIiakkwoaQzkdAd7rFTNZSUp9wEpBn2C0A8S99LLgZ0JAIw/0");
			b.set("MediaId", "0ScwNYrc1mcWY_xztaFGKAbMZL-Z1Adnrryq0XuIIc321ofBz_cA90-5n1MKatjK");
		}
		return b;
	}
	public static BizObject eventProcess(BizObject b){
        String eventKey = b.getString("EventKey");
        String wxid=b.getString("FromUserName");
		
        /**
         * 绑定卡
         */
        if(eventKey.equals("bound")){
        	String url = WebChatKit.createUrl("/open.WebChatService.boundCard", wxid) ;
        	String url2 = WebChatKit.createUrl("/open.WebChatService.boundMember", wxid) ;
        	
        	//String ret="点击返回图片<a href=/open.WebChatSer";
        	
        	String ret="绑定易生卡后，您可以快速对您的卡片进行查询、管理，操作更便捷。\r\n"
								+"<a href=\""+url+"\">请点击此处绑定卡片</a>\r\n"
								+"如果您已经是易生支付(my.bhecard.com)的会员并且已经在会员下绑定过卡片，则可以在此关联您的会员账号，同步您名下卡片信息。\r\n"
								+"<a href=\""+url+"\">请点击此处关联会员账号</a>";
        	b.set("content", ret);
        }
        
        
        /**
         * 余额查询
         */
        else if(eventKey.equals("query")){
        	
    	
        }

        /**
         * 历史交易查询
         */
        else if(eventKey.equals("hisquery")){
        	String url = WebChatKit.createUrl("/card.WeixinService.getTradeListBySN", wxid) ;
        	String cardinfo="29022*****222    点击查询\r\n29022*****256    点击查询\r\n29022*****345    点击查询\r\n29022*****190    点击查询\r\n";
        //	String url2 = WebChatKit.createUrl("/open.WebChatService.boundMember", wxid) ;
        	String ret="已绑定卡，点击对应链接查询指定卡的历史交易信息\r\n"
								+cardinfo+"\r\n"
								+"如果您还需要查询其他未绑定卡片历史交易，<a href=\""+url+"\">请点击此处</a>\r\n";
								
        	b.set("content", ret);            	
        }

        
        /**
         * 修改密码
         */
        else if(eventKey.equals("modpass")){
        	String url = WebChatKit.createUrl("/open.WebChatService.modPass", wxid) ;
        	String cardinfo="• 29022*****222    更改密码\r\n• 29022*****256    更改密码\r\n•• 29022*****345    更改密码\r\n";
        //	String url2 = WebChatKit.createUrl("/open.WebChatService.boundMember", wxid) ;
        	String ret="点击对应卡后方链接操作\r\n"
								+cardinfo+"\r\n"
								+"如果您还需要修改其他未绑定卡片支付密码，<a href=\""+url+"\">请点击此处</a>\r\n";
								
        	b.set("content", ret);            	
        }

        /**
         * 解除绑定
         */
        else if(eventKey.equals("unbound")){
        	String url = WebChatKit.createUrl("/open.WebChatService.bound", wxid) ;
        	String cardinfo="• 29022*****222    解除绑定\r\n• 29022*****256    解除绑定\r\n•• 29022*****345    解除绑定\r\n";
        //	String url2 = WebChatKit.createUrl("/open.WebChatService.boundMember", wxid) ;
        	String ret="点击对应卡后方链接操作\r\n"
								+cardinfo+"\r\n"
								+"如果您还需要解除与易生会员的关联，<a href=\""+url+"\">请点击此处</a>\r\n";
								
        	b.set("content", ret);            	
        }


        /**
         * 手机充值
         */
        else if(eventKey.equals("charge")){
        	String url = WebChatKit.createUrl("/open.PhoneRechargeWeiXinService.entry", wxid) ;
        	String cardinfo="• 29022*****222    解除绑定\r\n• 29022*****256    解除绑定\r\n•• 29022*****345    解除绑定\r\n";
        //	String url2 = WebChatKit.createUrl("/open.WebChatService.boundMember", wxid) ;
        	String ret="易生支付致力于为用户提供便捷的支付服务。您可以使用易生卡为您的手机充值。\r\n"
								+"<a href=\""+url+"\">请点击此处为手机充值</a>\r\n"
								+"每次输入卡号太不方便？赶紧去绑定一张易生卡，操作更快捷。\r\n"
								+"<a href=\""+url+"\">请点击此处绑定卡片</a>\r\n";
								
        	b.set("content", ret);            	
        }
        b.set("msgType", "text");
		return b;
		
	}

}
