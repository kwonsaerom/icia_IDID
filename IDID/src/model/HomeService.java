package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Forward;
import bean.Reply;
import bean.Review;
import dao.ReplyDao;
import dao.ReviewDao;

public class HomeService {
	HttpServletRequest request;
	HttpServletResponse response;
	private Forward fw;
	private String msg;


	public HomeService(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	public Forward execute(int code){
		switch(code){
		case 1:
			mReview_detail();
			break;
		case 2:
			comment_write();
			break;
		case 3:
			mReview_write();
			break;
		case 4:
			mReview_modify();
			break;
		case 5:
			mReview_delete();
			break;
		}
		return fw;
	}

	private void mReview_delete() {
		Review review = new Review();
		ReviewDao rvDao = new ReviewDao();
		int code = Integer.parseInt(request.getParameter("code"));
		System.out.println("delete code= "+code);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("mReview");
		rvDao.mReviewDelete(code);
		rvDao.close();
		
	}
	private void mReview_modify() {
		Review review = null;
		ReviewDao rvDao = new ReviewDao();
		int size = 1*1024*1024;
		String uploadPath = request.getRealPath("image");
		try {
			MultipartRequest multi = new MultipartRequest(
									request,
									uploadPath,
									size,
									"UTF-8",
									new DefaultFileRenamePolicy()
					);
			int code = Integer.parseInt(multi.getParameter("modifyCode"));
			String modifyTitle = multi.getParameter("modify_title");
			String modifySubstance = multi.getParameter("modify_substance");
			String modifyFilename = multi.getFilesystemName("modify_filename");
			System.out.println("modifyFilename="+modifyFilename);
			review = new Review(code, modifyTitle, modifySubstance, modifyFilename);
			rvDao.mReviewModify(review);
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("./index");
		} catch(Exception e){
			
		}
		rvDao.close();
	}
	private void mReview_write() {
		int size = 1*1024*1024;
		String uploadPath = request.getRealPath("image");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadPath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			HttpSession ss = request.getSession();
			String m_id = (String)ss.getAttribute("m_id");
			String r_title = multi.getParameter("_R_TITLE");
			System.out.println("r_title:"+r_title);
			String r_substance = multi.getParameter("_R_SUBSTANCE");
			System.out.println("r_substance:"+r_substance);
			String r_fileName = multi.getFilesystemName("_R_FILENAME");
			System.out.println("r_fileName:"+r_fileName);
			ReviewDao rDao = new ReviewDao();
			Review review = new Review(m_id,r_title,r_substance,r_fileName);
			rDao.insertWrite(review);
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("./mReview");
			rDao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void comment_write() {
		HttpSession ss = request.getSession();
		ArrayList<Reply> rpList = null;
		Reply reply = null;
		String id = null;
		String m_id = (String)ss.getAttribute("m_id");
		String t_id = (String)ss.getAttribute("t_id");
		if(m_id!=null){
			id=m_id;
		}else{
			id=t_id;
		}
		String rP_COMMENT = request.getParameter("comment");
		int code = Integer.parseInt(request.getParameter("code"));
		System.out.println("comment:"+rP_COMMENT);
		System.out.println("댓글 코드:"+code);
		if(m_id!=null){
			System.out.println("실행확인2");
			reply = new Reply(id, rP_COMMENT,code);
			ReplyDao replyDao = new ReplyDao();
			replyDao.insertReply(reply);
			rpList = replyDao.replySelect(code);
			String rpListMake = reListMakeHtml(rpList);
			request.setAttribute("rpListMake", rpListMake);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("mReview_detail.jsp");
			replyDao.close();
			return;
		}else if(t_id!=null){
			reply = new Reply(id, rP_COMMENT,code);
			ReplyDao replyDao = new ReplyDao();
			replyDao.insertReply(reply);
			rpList = replyDao.replySelect(code);
			String rpListMake = reListMakeHtml(rpList);
			request.setAttribute("rpListMake", rpListMake);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("mReview_detail.jsp");
			replyDao.close();
			return;
		}
		
		/*fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("reply.jsp");
*/	}
	private String reListMakeHtml(ArrayList<Reply> rpList) {
		StringBuilder sb = new StringBuilder();
		HttpSession ss = request.getSession();
		Reply reply = null;
		for(int i=0; i<rpList.size(); i++){
			reply = rpList.get(i);
			sb.append("작성자:"+reply.getRP_ID()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+reply.getRP_DATE()+"<br/>");
			sb.append(reply.getRP_COMMENT()+"<hr/>");
		}
		return sb.toString();
	}
	private void mReview_detail() {
		int code = Integer.parseInt(request.getParameter("code"));
		String reTitle = request.getParameter("title");
		HttpSession ss = request.getSession();
		ReviewDao rDao = new ReviewDao();
		ReplyDao rpDao = new ReplyDao();
		rDao.countUpdate(code);
		Review review = rDao.getReview(code);
		//ss.setAttribute("r_filename", review.getR_fileName());
		ArrayList<Review> list = rDao.selectDetail(code, reTitle);
		ArrayList<Reply> rpList = rpDao.replySelect(code); 
		String review_detailHtml = makeReviewDetail(list, rpList);
		String replyWriteHtml = makeReplyHtml(code, rpList);
		String replyBtnHtml = makeReplyBtnHtml(code);
		request.setAttribute("review_detailHtml", review_detailHtml);
		request.setAttribute("replyWriteHtml", replyWriteHtml);
		request.setAttribute("replyBtnHtml", replyBtnHtml);
		System.out.println("실행확인");
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("mReview_detail.jsp");
		rDao.close();
		rpDao.close();
	}
	private String makeReplyBtnHtml(int code) {
		StringBuilder sb = new StringBuilder();
		sb.append("<textarea rows='5' cols='50' style='resize: none;' id='comment'></textarea><br/>");
		sb.append("<input type='button' onclick=\"javascript:reply_ajax('comment_write?code="+code+"','#reply')\" value='댓글달기'/>");
		return sb.toString();
	}
	private String makeReplyHtml(int code, ArrayList<Reply> rpList) {
		StringBuilder sb = new StringBuilder();
		Reply reply = null;
		for(int i=0; i<rpList.size(); i++){
			reply = rpList.get(i);
			sb.append("작성자:"+reply.getRP_ID()+"<br/>");
			sb.append(reply.getRP_COMMENT()+"<br/>");
			sb.append(reply.getRP_DATE()+"<hr/>");
		}
		/*sb.append("<textarea rows='5' cols='50' style='resize: none;' id='comment'></textarea><br/>");
		sb.append("<input type='button' onclick=\"javascript:reply_ajax('comment_write?code="+code+"','#reply')\" value='댓글달기'/>");*/
		return sb.toString();
	}
	private String makeReviewDetail(ArrayList<Review> list, ArrayList<Reply> rpList) {
		StringBuilder sb = new StringBuilder();
		Review review = new Review();
		HttpSession ss = request.getSession();
		String ss_mId = (String)ss.getAttribute("m_id");
		String id = request.getParameter("id");
		System.out.println("수정 id:"+id);
		sb.append("<table class='table table-condensed'>");
		for(int i=0; i<list.size(); i++){
			review = list.get(i);
			sb.append("<tr><td colspan='2' style='text-align:left;'>제목:"+review.getR_title()+"</td></tr>");
			sb.append("<tr><td colspan='2' style='text-align:left;'>글쓴이:"+review.getR_m_id()+"</td>"+"<td colspan='2'><span align='right'>조회수:"+review.getR_count()+"</span></td></tr>");
			sb.append("<tr><td>"+review.getR_substance()+"<br/><img id='image' style='width:350px; height: 250px;' src='image/"+review.getR_fileName()+"'/></div></td></tr>");
			sb.append("<tr><td style='text-align:left;'><a href=\"javascript:AJ('mReview','#printP')\"><input type='button' value='목록보기'/></a></td>");
			if(id.equals(ss_mId)){
				sb.append("<td style='text-align:left;'><a href=\"javascript:AJ('mReview_modifyForm?code="+review.getR_code()+"','#printP')\"><input type='button' value='수정'/></a></td>");
				sb.append("<td><a href=\"javascript:AJ('mReviewDelete?code="+review.getR_code()+"','#printP')\"><input type='button' value='삭제'/></a></td>");
			}
			sb.append("<td><a href=\"javascript:AJ('mReview_form','#printP')\"><input type='button' value='글쓰기'/></a></td></tr>");
			/*sb.append("<td><form name='writeBtn' action='mReview_form'><input type='button' onclick='javascript:AJ('mReview','#printP')' value='글쓰기'/></form></td></tr>");*/
		}
		sb.append("</table>");
		/*Reply reply = null;
		for(int i=0; i<rpList.size(); i++){
			reply = rpList.get(i);
			sb.append("작성자:"+reply.getRP_M_ID()+"<br/>");
			sb.append(reply.getRP_COMMENT()+"<br/>");
			sb.append(reply.getRP_DATE()+"<hr/>");
		}*/
		/*sb.append("<form action='comment_write' method='post'>");
		sb.append("<textarea rows='5' cols='50' style='resize: none;' id='comment'></textarea><br/>");
		sb.append("<input type='button' onclick=\"javascript:reply_ajax('comment_write?code="+review.getR_code()+"','#reply')\" value='댓글달기'/>");
		sb.append("</form>");*/
		return sb.toString();
	}
	
	
}