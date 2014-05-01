package sand.ws.network;

import java.util.Map;

import tool.dao.BizObject;

public interface NetworkService {
	String contentType="application/x-www-form-urlencoded";
	
	public String sendMember (String content);
	
	
	/**
	 * 修改密码
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com网上支付账户(必填)
	 * 		pwd=1234qwer新支付密码(必填)
	 * 		opwd=123456原支付密码(必填)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject modifyPass(Map content,String key) ;
	
	/**
	 * 重置密码
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com网上支付账户(必填)
	 * 		pwd=1234qwer重置的密码(必填)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject resetPass(Map content,String key) ;
	
	
	/**
	 * 密码校验
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com网上支付账户(必填)
	 * 		pwd=1234qwer支付密码(必填)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject verifyPass(Map content,String key);
	
	/**
	 * 支付账户注册
	 * 
	 * @param content 请求的参数,例如:email=1050001042@qq.com&pwd=1234qwer&name=%E4%B8%AD%E5%9B%BD   
	 * (注意:name是需要使用urlencode，编码格式：utf-8)
	 * 
	 * @return 如下: 
	 * 成功:result:true ,code:成功码 ,id:customerId用户ID 
	 * 		,apiKey:apiKey注册成功后，返回APIKEY用于验签,这个key是要保存到支付账户表中,以便后面接口用来验签用的

	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject register(String content);
	
	/**
	 * 转账(单笔转账,要多笔同时进行,进行多次调用接口,转账的限额业务自己控制,接口不负责此等业务) 金额的单位为:元
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com转出的网上支付账户(必填)
	 * 		pwd=1234qwer支付密码(必填)
	 * 		transferto=434411468@qq.com转入的网上支付账户(必填)
	 * 		amount=200转账金额(必填)
	 * 		subject=%E4%B8%AD%E5%9B%BD转账理由(必填)
	 * 		comment=%E4%B8%AD%E5%9B%BD备注(必填)
	 * @param key     签名的key,用户表中的apikey字段 
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject transfer(Map content,String key);
	
	/**
	 * 充值 
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com(必填)
	 * @param key     签名的key,用户表中的apikey字段 
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public String charge(Map content,String key);
	
	/**
	 * 交易查询
	 * 
	 * @param signContent  需要进行签名的参数
	 * 		email=1050001042@qq.com用户邮箱(必填)
	 * 		type=all交易类型(必填  取值范围参照接口文档)
	 * 		status=starting...交易状态(非必填   取值范围参照接口文档)
	 * 		orderId=12345...订单号(非必填)
	 * 		relateno=12345...关联交易号(非必填)
	 * 		datestart=20130908开始日期(非必填  格式:yyyyMMdd)
	 * 		dateend=2013909结束日期(非必填  格式:yyyyMMdd)
	 * 
	 * @param content 不进行签名的参数
	 * 		pagesize=15每页行数(必填  不参与验签)
	 * 		page=2页码(必填 不参与验签)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码,count:交易总条数, 
	 * 		datas:list<BizObject>
	 * 			(ID:ID 交易ID
					dateCreated:dateCreated交易创建时间
					tradeNo:tradeNo交易流水号
					typeType:tradeType交易类型
					amount:amount交易金额
					status:status交易状态
					subject:subject摘要
					outTradeNo:outTradeNo外部交易号(关联交易号)
					tradeTo:tradeto交易对方
				)
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject queryTrade(Map signContent,Map content,String key);
	
	/**
	 * 查询余额
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com网上支付账户(必填)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码,balance:账户余额, balanceFreeze:冻结余额, balanceWithdraw:可提现余额, balanceRefund:可退款余额
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */
	public BizObject queryBalance(Map content,String key);
	
	/**
	 * 充值退款
	 * 
	 * @param  email=zx01@163.com&pwd=123qwe&ordId=111303110119986&amount=0.01&comment=888&sign=b067f17aabebeabb0be784ea38300151
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com网上支付账户(必填)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码,balance:账户余额, balanceFreeze:冻结余额, balanceWithdraw:可提现余额, balanceRefund:可退款余额
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */	
	public BizObject refund(Map content,String key);

	/**
	 * 账务查询
	 * 
	 * @param  email=zx01@163.com&pwd=123qwe&ordId=111303110119986&amount=0.01&comment=888&sign=b067f17aabebeabb0be784ea38300151
	 * 
	 * @param content 请求的map参数包含:
	 * 		email=1050001042@qq.com网上支付账户(必填)
	 * 
	 * @param key     签名的key,用户表中的apikey字段
	 * 
	 * @return 如下: 
	 * 成功:result:true,code:成功码,balance:账户余额, balanceFreeze:冻结余额, balanceWithdraw:可提现余额, balanceRefund:可退款余额
	 * 失败:result:false,code:错误码 ,error:errorMessage错误信息
	 */	
	
	public BizObject query(Map content,Map signmap,String key);
	/**
	 * 账务明细查询
	 * @param content
	 * @param signmap
	 * @param key
	 * @return
	 */
	public BizObject queryDetail(Map content,String key);
	
	/**
	 * 交易查询—汇总查询
	 * @param content
	 * @param key
	 * @return
	 */
	public BizObject groupTrade(Map content,String key);
	/**
	 * 提现
	 * @param content
	 * @param key
	 * @return
	 */
	
	public BizObject  withdrawn(Map content,String key);
	
	/**
	 * 网上支付账户向预付费卡转账
	 * 
	 * @param content	签名参数
	 * 		email=zx01@163.com网上支付账户
	 * 		encodepwd=SHA1(SHA1(用户id + 'p' + 支付密码) +key)支付密码
	 * 		amount=1转账金额(单位:元)
	 * 		cardno=2902210002001496014预付费卡号
	 * @param key	签名的key,用户表中的apikey字段
	 * @return 如下:
	 * 		成功:result:true,tradeno:tradeNo交易流水号,code:反馈编码
	 * 		失败:result:false,code:反馈编码,error:errorMessage错误信息,tradeno:tradeNo交易流水号
	 */
	public BizObject transferToCard(Map content,String key);
	
	/**
	 * 网上支付账户向预付费卡转账的撤销
	 * 
	 * @param content	签名参数
	 * 		email=zx01@163.com网上支付账户
	 * 		tradeno=10110100原网上支付账户向预付费卡转账时网上支付返回的交易号
	 * 		amount=1原转账金额(单位:元)
	 * 
	 * @param key	签名的key,用户表中的apikey字段
	 * @return 如下:
	 * 		成功:result:true,tradeno:tradeNo交易流水号,code:反馈编码
	 * 		失败:result:false,code:反馈编码,error:errorMessage错误信息,tradeno:tradeNo交易流水号
	 */
	public BizObject transferToCardRefund(Map content,String key);
	
	/**
	 * 退出网上支付登录
	 * 
	 * @param content	签名参数
	 * 		email=zx01@163.com网上支付账户
	 * 
	 * @param key	签名的key,用户表中的apikey字段
	 * @return 如下:
	 * 		成功:result:true,code:反馈编码
	 * 		失败:result:false,code:反馈编码,error:errorMessage错误信息
	 */
	public BizObject logout(Map content,String key);
	
	/**
	 * 更新网上支付的手机信息
	 * 
	 * @param content	签名参数
	 * 		email=zx01@163.com网上支付账户
	 * 		phone=15801******更新后的手机号
	 * 
	 * @param key	签名的key,用户表中的apikey字段
	 * @return 如下:
	 * 		成功:result:true,code:反馈编码
	 * 		失败:result:false,code:反馈编码,error:errorMessage错误信息
	 */
	public BizObject updateMobile(Map content,String key);
	
	/**
	 * 用这个用户:1050001042@qq.com作测试的时候,可以用这个方法取key,以后都去取支付账户表中的apikey字段,不同的用户不同的key去验签
	 * @return
	 */
	public String getKey() ;
	
}
