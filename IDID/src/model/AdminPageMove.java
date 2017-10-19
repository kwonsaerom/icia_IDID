package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import bean.Member;
import bean.Review;
import bean.Trainer;
import dao.AdminDao;
import dao.MemberDao;
import dao.TrainerDao;

public class AdminPageMove {
   HttpServletRequest request;
   HttpServletResponse response;
   private Forward fw = null;
   TrainerDao tDao = null;
   String str = null;
   TrainerService ts;
   public AdminPageMove(HttpServletRequest request, HttpServletResponse response) {
      this.request=request;
      this.response=response;
   }
   public Forward execute(int code){
      switch (code){
      case 1:
         MemberList();
         break;
      case 2:
         TrainerList();
         break;
      case 3:
         MemberDelete();
         break;
      case 4:
         TrainerDelete();
         break;
      case 5:
         ReviewList();
         break;
      case 6:
         ReviewDelete();
         break;
      case 7:
         ReviewDetail();
         break;
      case 8:
         totList();
         break;
      }
      return fw;

   }

   private void totList() {
      Member mb=new Member();
      MemberDao mDao=new MemberDao();
      ArrayList<Member> list=mDao.memberSelect();
      String mList = makeMemberList_tot(list); 
      TrainerDao tDao=new TrainerDao();
      ArrayList<Trainer> list2=tDao.TrainerSelect();
      String tList = makeTrainerList_tot(list2); 
      request.setAttribute("totList", mList+tList);
      fw=new Forward();
      fw.setRedirect(false);
      fw.setPath("aMain.jsp");
   }
   
   
   private String makeTrainerList_tot(ArrayList<Trainer> list2) {
      StringBuilder sb = new StringBuilder();
      Trainer tn=new Trainer();
      sb.append("<table border='1' class='table table-striped'>");
      sb.append("<tr><th>아이디</th><th>이름</th><th>생년월일</th><th>주소</th><th>이메일</th></tr>");
      for(int i=0; i<list2.size(); i++){
         tn = list2.get(i);
         sb.append("<tr><td>"+tn.gettName()+"</td>");
         sb.append("<td>"+tn.gettName()+"</td>");
         sb.append("<td>"+tn.gettBirth1()+"년 "+tn.gettBirth2()+"월 "+tn.gettBirth3()+"일"+"</td>");
         sb.append("<td>"+tn.gettDomicile()+"</td>");
         sb.append("<td>"+tn.gettEmail()+"</td></tr>");
      }
      sb.append("</table>");
      return sb.toString();
   }
   private String makeMemberList_tot(ArrayList<Member> list) {
      StringBuilder sb = new StringBuilder();
      Member mb = new Member();
      sb.append("<table border='1' class='table table-striped'>");
      sb.append("<tr><th>아이디</th><th>이름</th><th>생년월일</th><th>주소</th><th>이메일</th></tr>");
      for(int i=0; i<list.size(); i++){
         mb = list.get(i);
         sb.append("<tr><td>"+mb.getmName()+"</td>");
         sb.append("<td>"+mb.getmName()+"</td>");
         sb.append("<td>"+mb.getMbirth1()+"년 "+mb.getMbirth2()+"월 "+mb.getMbirth3()+"일"+"</td>");
         sb.append("<td>"+mb.getmDomicile()+"</td>");
         sb.append("<td>"+mb.getmEmail()+"</td></tr>");
      }
      return sb.toString();
   }
   
   
   private void ReviewDetail() {
      int r_code=Integer.valueOf(request.getParameter("r_code"));
      System.out.println(r_code);
      AdminDao aDao=new AdminDao();
      Review rv=aDao.ReviewDetail(r_code);
      request.setAttribute("r_code", r_code);
      request.setAttribute("r_title", rv.getR_title());
      request.setAttribute("r_m_id", rv.getR_m_id());
      request.setAttribute("r_substance", rv.getR_substance());
      fw= new Forward();
      fw.setRedirect(false);
      fw.setPath("aReviewDetail.jsp");
   }
   
   private void ReviewDelete() {
      int r_code=Integer.valueOf(request.getParameter("r_code"));
      System.out.println(r_code);
      AdminDao aDao=new AdminDao();
      int cnt = aDao.delete(r_code);
      if (cnt != 0) {
         request.setAttribute("msg", "삭제 완료");
         fw=new Forward();
         fw.setRedirect(false);
         fw.setPath("aMain.jsp");
         aDao.close();
      } else {
         request.setAttribute("msg", "삭제 실패");
         aDao.close();
      }
   }
   
   
   private void ReviewList() {
      AdminDao aDao=new AdminDao();
      ArrayList<Review> list=aDao.ReviewSelect();
      String rList = makeReviewList(list); 
      request.setAttribute("rList", rList);
      fw=new Forward();
      fw.setRedirect(false);
      fw.setPath("aReviewDelete.jsp");
   }
   
   
   private String makeReviewList(ArrayList<Review> list) {
      StringBuilder sb = new StringBuilder();
      Review rv=new Review();
      sb.append("<table border='1' class='table table-striped'>");
      sb.append("<tr><th>글 번호</th><th>작성 아이디</th><th>작성 이름</th><th>글 제목</th></tr>");
      for(int i=0; i<list.size(); i++){
         rv = list.get(i);
         sb.append("<tr><td>"+rv.getR_code()+"</td>");
         sb.append("<td>"+rv.getR_m_id()+"</td>");
         sb.append("<td>"+rv.getR_m_name()+"</td>");
         sb.append("<td>"+rv.getR_title()+"</td>");
         sb.append("<td><a href='rvDetail?r_code="+rv.getR_code()+"'><input type='button' value='상세보기'/></a></td></tr>");
      }
      sb.append("</table>");
      return sb.toString();
   }
   private void TrainerDelete() {
      String t_id=request.getParameter("t_id");
      System.out.println(t_id);
      TrainerDao tDao=new TrainerDao();
      int cnt = tDao.delete(t_id);
      if (cnt != 0) {
         request.setAttribute("msg", "탈퇴 완료");
         fw=new Forward();
         fw.setRedirect(false);
         fw.setPath("aMain.jsp");
         tDao.close();
      } else {
         request.setAttribute("msg", "탈퇴 실패");
         tDao.close();
      }

   }
   private void MemberDelete() {
      String m_id=request.getParameter("m_id");
      System.out.println(m_id);
      MemberDao mDao=new MemberDao();
      System.out.println("delete실행");
      int cnt = mDao.delete(m_id);
      if (cnt != 0) {
         request.setAttribute("msg", "탈퇴 완료");
         fw=new Forward();
         fw.setRedirect(false);
         fw.setPath("aMain.jsp");
         mDao.close();
      } else {
         request.setAttribute("msg", "탈퇴 실패");
         mDao.close();
      }
   }

   private void TrainerList() {
      TrainerDao tDao=new TrainerDao();
      ArrayList<Trainer> list=tDao.TrainerSelect();
      String tList = makeTrainerList(list); 
      request.setAttribute("tList", tList);
      fw=new Forward();
      fw.setRedirect(false);
      fw.setPath("aTrainerList.jsp");
   }

   private String makeTrainerList(ArrayList<Trainer> list) {
      StringBuilder sb = new StringBuilder();
      Trainer tn=new Trainer();
      sb.append("<table border='1' class='table table-striped'>");
      sb.append("<tr><th>아이디</th><th>이름</th><th>생년월일</th><th>주소</th><th>이메일</th><th></th></tr>");
      for(int i=0; i<list.size(); i++){
         tn = list.get(i);
         sb.append("<tr><td>"+tn.gettName()+"</td>");
         sb.append("<td>"+tn.gettName()+"</td>");
         sb.append("<td>"+tn.gettBirth1()+"년 "+tn.gettBirth2()+"월 "+tn.gettBirth3()+"일"+"</td>");
         sb.append("<td>"+tn.gettDomicile()+"</td>");
         sb.append("<td>"+tn.gettEmail()+"</td>");
         sb.append("<td><a href='./tDelete?t_id="+tn.gettId()+"'><input type='button' value='탈퇴'/></a></td></tr>");

      }
      sb.append("</table>");
      return sb.toString();
   }


   private void MemberList() {
      MemberDao mDao=new MemberDao();
      ArrayList<Member> list=mDao.memberSelect();
      String mList = makeMemberList(list); 
      request.setAttribute("mList", mList);
      fw=new Forward();
      fw.setRedirect(false);
      fw.setPath("aMemberList.jsp");
   }

   private String makeMemberList(ArrayList<Member> list) {
      StringBuilder sb = new StringBuilder();
      Member mb=new Member();
      sb.append("<table border='1' class='table table-striped'>");
      sb.append("<tr><th>아이디</th><th>이름</th><th>생년월일</th><th>주소</th><th>이메일</th><th></th></tr>");
      for(int i=0; i<list.size(); i++){
         mb = list.get(i);
         sb.append("<tr><td>"+mb.getmId()+"</td>");
         sb.append("<td>"+mb.getmName()+"</td>");
         sb.append("<td>"+mb.getMbirth1()+"년 "+mb.getMbirth2()+"월 "+mb.getMbirth3()+"일"+"</td>");
         sb.append("<td>"+mb.getmDomicile()+"</td>");
         sb.append("<td>"+mb.getmEmail()+"</td>");
         sb.append("<td><a href='./mDelete?m_id="+mb.getmId()+"'><input type='button' value='탈퇴'/></a></td></tr>");

      }
      sb.append("</table>");
      return sb.toString();
   }

}