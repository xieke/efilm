package sand.actionhandler.open;

import java.util.HashMap;

import sand.depot.tool.system.ControllableException;

public class OpenServiceException extends ControllableException {
	
	private static HashMap errorMap = new HashMap();
	static{
		errorMap.put("0000","处理成功"          );
		errorMap.put("0001","姓名错误"          );
		errorMap.put("0002","CVN校验失败"       );
		errorMap.put("0003","证件号错误"        );
		errorMap.put("0004","卡号不存在"        );
		errorMap.put("0005","卡账户不存在"      );
		errorMap.put("0006","密码错误"          );
		errorMap.put("0007","方法参数不能为空"  );
		errorMap.put("0008","卡片状态异常"      );
		errorMap.put("0009","密码格式错误"      );
		errorMap.put("0010","证件号与卡号不匹配");
		errorMap.put("0011","数据长度超过最大长度"     );
		errorMap.put("0012","卡账户状态异常"    );
		errorMap.put("0013","密码类型错误"      );
		errorMap.put("0014","交易类型错误"      );
		errorMap.put("0015","日期格式错误"      );
		errorMap.put("0016","机构编号不存在"    );
		errorMap.put("0017","交易不存在"        );
		errorMap.put("0018","第三方流水号重复"  );
		errorMap.put("0019","帐户不能充值"      );
		errorMap.put("0020","充值金额超出账户余额限制"   );
		errorMap.put("0021","卡号不是记名卡"    );
		errorMap.put("0022","新密码不能与原密码重复"    );
		errorMap.put("0023","卡号已绑定，不能重复绑定"    );
		errorMap.put("0024","找不到记录"    );
		errorMap.put("0025","图片不能为空"    );
		errorMap.put("0026","手机已绑定"    );
		errorMap.put("0027","手机未绑定"    );
		errorMap.put("2000","其他错误"          );
		errorMap.put("2001","系统错误"                     );
		errorMap.put("3001",	"未获取到手机充值产品信息                         ");
		errorMap.put("3002",	"获取交流流水码失败                               ");
		errorMap.put("3003",	"支付系统返回数据错误，本地订单号系统中不存在     ");
		errorMap.put("3004",	"支付系统返回数据错误，签名验证未通过             ");
		errorMap.put("3005",	"支付失败                                         ");
		errorMap.put("3006",	"欧菲充值接口状态不通                             ");
		errorMap.put("3007",	"欧菲充值返回信息提示充值失败                     ");
		errorMap.put("3008",	"获取手机号归属地失败                             ");
		errorMap.put("3009",	"运营商地区维护，暂不能充值                       ");
		errorMap.put("4001","当前用户未绑定此卡号"                     );
		errorMap.put("4002","金额不正确"                     );
		
		errorMap.put("5001","验证码发送失败"                     );
		errorMap.put("5002","验证码错误"                     );
		errorMap.put("5003","手机号错误"                     );
		errorMap.put("5004","密码错误"                     );
		errorMap.put("5005","手机号已注册"                     );
		errorMap.put("5006","登录账号错误"                     );
		errorMap.put("5007","找回类型错误"                     );
		errorMap.put("5008","未找到匹配账号"                     );
		errorMap.put("5009","参数转换错误"                     );
		errorMap.put("5010","商户编号错误"                     );
		errorMap.put("5011","loginId错误"                     );
		
		errorMap.put("8000","没有访问权限"                 );
		errorMap.put("8001","签名校验错误"                 );
		errorMap.put("8002","用户名密码错误"                 );
		errorMap.put("8003","许可令牌失效"                 );
		errorMap.put("8004","非法应用"                 );
		errorMap.put("8005","加密解密错误"                 );
		errorMap.put("8006","未找到签名密钥"                 );
		
		//errorMap.put("8101","您的操作已经失效"                 );
		//errorMap.put("8007","未找到加密密钥"                 );
		errorMap.put("9999","连接卡账失败"                 );
		
	}
		
	
	public OpenServiceException(String code,String msg){
		super(msg);
		super.type=super.OPENERROR;
		super.code=code;
	}
	public OpenServiceException(String code){
		super((String)errorMap.get(code));
		super.type=super.OPENERROR;
		super.code=code;
	}
//	public OpenServiceException(String code,Throwable t){
//		super((String)errorMap.get(code));
//		/super.addSuppressed(t);		
//		//super.setStackTrace(stackTrace);
//		super.type=super.OPENERROR;
//		super.code=code;
//	}
}
