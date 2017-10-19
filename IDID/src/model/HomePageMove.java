package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import bean.Program;
import bean.Review;
import dao.HomeDao;
import dao.ReviewDao;
import dao.TrainerDao;

public class HomePageMove {
	HttpServletRequest request;
	HttpServletResponse response;
	private Forward fw = null;
	TrainerDao tDao = null;
	String str = null;
	TrainerService ts;
	HomeDao hDao = null;
	public HomePageMove(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	
	public Forward execute(int code){
		switch(code){
		case 1:
			mReviewMove();
			break;
		case 2:
			//프로그램소개 페이지로 이동
			programInfo();
			break;
		case 3:
			//글쓰기페이지로 이동
			mReview_formMove();
			break;
		case 4:
			mReview_modifyForm();
			break;
		}
		return fw;
	}
	
	private void mReview_modifyForm() {
		int code = Integer.parseInt((String)request.getParameter("code"));
		ReviewDao rvDao = new ReviewDao();
		Review review = rvDao.getReview(code);
		String mReviewModify = mReviewMakeList(review); 
		request.setAttribute("mReviewModify", mReviewModify);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("mReview_modify.jsp");
		rvDao.close();
	}

	private String mReviewMakeList(Review review) {
		StringBuilder sb = new StringBuilder();
		sb.append("<form action='mReview_modify' method='post' enctype='multipart/form-data'><table>");
		sb.append("<input type='hidden' name='modifyCode' value='"+review.getR_code()+"'/>");
		System.out.println("reviewCode="+review.getR_code());
		if(review!=null){
			sb.append("<tr><td><div align='center'>제목:</td><td><input type='text' name='modify_title' value='"+review.getR_title()+"'/></div></td></tr>");
			sb.append("<tr><td><span align='left'>글쓴이:</td><td>"+review.getR_m_id()+"</span></td></tr>");
			/*sb.append("<tr><td><div align='center'>내용:</td><td><textarea rows='10' cols='52'>"+review.getR_substance()+"<img id='image' src='image/"+review.getR_fileName()+"'/></textarea></div></td></tr>");*/
			sb.append("<tr><td><div align='center'>내용:</td><td><input type='text' name='modify_substance' rows='10' cols='52' value='"+review.getR_substance()+"'/><br/></div></td></tr>");
			sb.append("<tr><td>이미지 수정:</td><td><img id='image' src='image/"+review.getR_fileName()+"'/></td></tr>");
			sb.append("<tr><td><input type='file' name='modify_filename' id='modify_filename'/></td>");
			sb.append("<tr><td><input type='submit' value='수정완료' /></td>");
			sb.append("<tr><td><input type='button' onclick='back()' value='뒤로가기' /></td></tr>");
		}
		sb.append("</table></form>");
		return sb.toString();
	}

	private void mReview_formMove() {
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("mReview_form.jsp");
	}
	/*private String mReviewListHtml() {
		HttpSession ss = request.getSession();
		String isId = (String)ss.getAttribute("m_id");
		StringBuilder sb = new StringBuilder();
		
		return null;
	}*/

	private void programInfo() {
	      fw=new Forward();
	      ArrayList<Program> pList = new ArrayList<Program>();
	      hDao = new HomeDao();
	      pList = hDao.getProgramInfo();
	      System.out.println(pList);
	      String html = null;
	      if(pList != null){
	         System.out.println("테스트");
	         html = makeHtml_pList(pList);
	         request.setAttribute("pList", html);
	      }
	      hDao.close();
	      fw.setRedirect(false);
	      fw.setPath("programInfo.jsp");
	      
	   }

	private String makeHtml_pList(ArrayList<Program> pList) {
        StringBuilder sb = new StringBuilder();
        Program program = null;
        sb.append("<div id='pListContainer'>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<h1>이용안내 페이지입니다.</h1>");
        sb.append("<br/>");
        sb.append("<br/>");
        for(int i=0; i<pList.size(); i++){
           program = pList.get(i);
           sb.append("<table class='table table-striped'>");
           sb.append("<tr><td class='index'>"+"프로그램 이름:"+"</td>"+"<td class='delay'>"+program.getP_name()+"</td></tr>");
           sb.append("<tr><td class='index'>"+"프로그램 내용:"+"</td>"+"<td class='delay'>"+program.getP_info()+"</td></tr>");
           sb.append("<tr><td class='index'>"+"프로그램 가격"+"</td>"+"<td class='delay'>"+program.getP_price()+"</td></tr>");
           sb.append("<tr><td class='index'>"+"트레이너 이름"+"</td>"+"<td class='delay'>"+program.getP_t_name()+"</td></tr>");
           sb.append("</table>");
           sb.append("</br>");
        }
        sb.append("</div>");
        
        return sb.toString();
     }
	
	private void mReviewMove() {
		ReviewDao rvDao = new ReviewDao();
		Review review = new Review();
		int page = 1;
		int limit = 10;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int listcount = rvDao.getListCount();
		ArrayList<Review> list = rvDao.reviewList(page, limit);
		int maxpage = (int)((double)listcount/limit+0.95);//총 페이지 수
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;//시작페이지
		int endpage = startpage+10-1;//마지막페이지
		if(endpage>maxpage){
			endpage=maxpage;
		}
		request.setAttribute("page", page);//현재 페이지 수
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		HttpSession ss = request.getSession();
		String id = (String)ss.getAttribute("m_id");
		/*ArrayList<Review> list = rDao.reviewList();*/
		String reviewHtml = makeReviewHtml(list);
		request.setAttribute("reviewHtml", reviewHtml);
		if(id == null){
			request.setAttribute("login", 0);
		}else{
			request.setAttribute("login", 1);
		}
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("mReview.jsp");
		rvDao.close();
	}

	private String makeReviewHtml(ArrayList<Review> list) {
		StringBuilder sb = new StringBuilder();
		ReviewDao rvDao = new ReviewDao();
		Review review = new Review();
		
		/*int rowsize = 4; //한 페이지에 보일 게시물 수
		int block = 5; //아래에 보일 페이지 최대개수 1~5
		int page = 1; //기본 페이지값
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		int start = (page * rowsize) - (rowsize-1); //해당페이지에서 시작번호 
		int end = (page * rowsize); //해당페이지에서 끝번호
		
		int allPage = 0;//전체 페이지 수
		
		int startPage = ((page-1)/block*block)+1;//시작블럭 숫자 
		int endPage = ((page-1)/block*block)+block;//끝 블럭 숫자*/
		
		sb.append("<table class='table table-striped' id='table'>");
		sb.append("<tr><td>글번호</td><td>제목</td><td>글쓴이</td><td>날짜</td><td>조회수</td></tr>");
		for(int i=0; i<list.size(); i++){
			review = list.get(i);
			sb.append("<tr><td>"+review.getR_code()+"</td>");
			sb.append("<td><a href=\"javascript:AJ('mReview_detail?id="+review.getR_m_id()+"&code="+review.getR_code()+"&title="+review.getR_title()+"','#printP')\">"+review.getR_title()+"</a></td>");
			/*sb.append("<td><a href='mReview_detail?code="+review.getR_code()+"&title="+review.getR_title()+"'>"+review.getR_title()+"</a></td>");*/
			sb.append("<td>"+review.getR_m_id()+"</td>");
			sb.append("<td>"+review.getR_date()+"</td>");
			sb.append("<td>"+review.getR_count()+"</td></tr>");
		}
		sb.append("</table>");
		/*sb.append("<table width='100%' cellpadding='0' cellspacing='0' border='0'>");
		sb.append("<tr><td colspan='4' height='5'></td></tr>");
		if(page>block){
			sb.append("[<a href='mReview?page=1'>◀◀</a>]");
			sb.append("[<a href='mReview?page="+(startPage-1)+"'>◀</a>]");
		}
		for(int i=startPage; i<=endPage; i++){
			if(i==page){
				sb.append("<b>["+i+"]</b>");
			}else{
				sb.append("[<a href='mReview?page"+i+i+"']");
			}
		}
		if(endPage<allPage){
			sb.append("[<a href='mReview?page"+(endPage+1)+"'>▶</a>]");
			sb.append("[<a href='mReview?page"+allPage+"'>▶▶</a>]");
		}*/
		sb.append("</td></tr></table>");
		return sb.toString();
	}
}
