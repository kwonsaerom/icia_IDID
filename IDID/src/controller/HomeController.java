package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import model.HomePageMove;
import model.HomeService;

@WebServlet({"/index","/mReviewDelete","/mReview_modifyForm","/mReview_modify","/mReview_write","/mReview_form","/programInfo","/mReview","/mReview_detail","/loginForm","/comment_write"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String url=uri.substring(conPath.length());
		System.out.println("url="+url);
		Forward fw=null;
		HomePageMove hm = null; 
		HomeService hs = null;
		if(url.equals("/index")){
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("home.jsp");
		}else if(url.equals("/loginForm")){
			//로그인폼페이지로 이동
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("login.jsp");
		}else if(url.equals("/mReview")){
			//후기게시판 목록
			hm = new HomePageMove(request, response);
			fw= hm.execute(1);
		}else if(url.equals("/mReview_detail")){
			//후기게시판 글쓰기
			hs = new HomeService(request, response);
			fw = hs.execute(1);
		}else if(url.equals("/comment_write")){
			//후기게시판 글쓰기
			hs = new HomeService(request, response);
			fw = hs.execute(2);
		}else if(url.equals("/programInfo")){
			//프로그램소개 페이지로 이동
			hm=new HomePageMove(request, response);
			fw= hm.execute(2);
		}else if(url.equals("/mReview_form")){
			hm=new HomePageMove(request, response);
			fw=hm.execute(3);
		}else if(url.equals("/mReview_write")){
			hs=new HomeService(request, response);
			fw=hs.execute(3);
		}else if(url.equals("/mReview_modifyForm")){
			hm=new HomePageMove(request, response);
			fw=hm.execute(4);
		}else if(url.equals("/mReview_modify")){
			hs=new HomeService(request, response);
			fw=hs.execute(4);
		}else if(url.equals("/mReviewDelete")){
			hs=new HomeService(request, response);
			fw=hs.execute(5);
		}
		if(fw!=null){
			if(fw.isRedirect()){
				System.out.println("path="+fw.getPath());
				response.sendRedirect(fw.getPath());
			}else{
				System.out.println("path="+fw.getPath());
				RequestDispatcher dis=
						request.getRequestDispatcher(fw.getPath());
				dis.forward(request, response);
			}
		}
	}

}
