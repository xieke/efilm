package sand.actionhandler.open;

public class WzOpenServiceException extends OpenServiceException {

	public WzOpenServiceException(String code) {
		super(code);
		//super.type=super.OPENERROR;
		super.codeStr="code";
		super.msgStr="message";
		// TODO Auto-generated constructor stub
	}

}
