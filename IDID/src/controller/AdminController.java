package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import model.AdminPageMove;

@WebServlet({"/aMain","/aMemberList","/aTrainerList","/aReviewDeleteMove","/mDelete","/tDelete","/rvDetail","/rvDelete","/totList"})
public class AdminController  extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request,response);
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request,response);
   }
   private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=utf-8");
      String uri=request.getRequestURI();
      String conPath=request.getContextPath();
      String url=uri.substring(conPath.length());
      System.out.println("url="+url);
      Forward fw=null;
      AdminPageMove ap;
      if(url.equals("/aMain")){
         //관리자 메인 페이지로 이동
         /*fw=new Forward();
         fw.setRedirect(false);
         fw.setPath("aMain.jsp");*/
         ap=new AdminPageMove(request,response);
         fw=ap.execute(8);
      }
      else if(url.equals("/aMemberList")){
         //일반회원의 리스트  출력 페이지 이동
         ap=new AdminPageMove(request,response);
         fw=ap.execute(1);
      }
      else if(url.equals("/aTrainerList")){
         //트레이너의 리스트 출력 페이지 이동
         ap=new AdminPageMove(request, response);
         fw=ap.execute(2);
      }
      else if(url.equals("/aReviewDeleteMove")){
         //리뷰글 삭제 페이지로 이동
         ap=new AdminPageMove(request,response);
         fw=ap.execute(5);
      }else if(url.equals("/mDelete")){
         System.out.println("멤버삭제");
         ap=new AdminPageMove(request,response);
         fw=ap.execute(3);
      }else if(url.equals("/tDelete")){
         System.out.println("트레이너 삭제");
         ap=new AdminPageMove(request,response);
         fw=ap.execute(4);
      }else if(url.equals("/rvDelete")){
         System.out.println("리뷰 삭제");
         ap=new AdminPageMove(request,response);
         fw=ap.execute(6);
      }else if(url.equals("/rvDetail")){
         System.out.println("리뷰 상세보기");
         ap=new AdminPageMove(request,response);
         fw=ap.execute(7);
      }else if(url.equals("/totList")){
         System.out.println("토탈 리스트 보기");
         ap=new AdminPageMove(request,response);
         fw=ap.execute(8);
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
