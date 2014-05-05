package sand.actionhandler.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sand.annotation.AccessControl;

@AccessControl("no")
public class FilmService extends WebChatService {

	public FilmService(HttpServletRequest req, HttpServletResponse res) {
		super(req, res);
		// TODO Auto-generated constructor stub
	}

	//首页
	public void home(){
		logger.info("session id is "+	this._request.getSession(false).getId());
		this._nextUrl="/template/weixin/index.jsp";
	
	}
	public void chooseCinema(){
		logger.info("session id is "+	this._request.getSession(false).getId());
		this._nextUrl="/template/weixin/index.jsp";
	}
	
	public void chooseFilm(){
		logger.info("session id is "+	this._request.getSession(false).getId());
		this._nextUrl="/template/weixin/index.jsp";
	}
}
