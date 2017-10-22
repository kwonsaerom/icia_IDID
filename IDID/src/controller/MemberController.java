package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import model.MemberPageMove;
import model.MemberService;

@WebServlet({"/trainerPick","/mFoodReg","/mExercise","/mFood","/mfoodgraph","/mPhysial","/mExercisegraph","/mPhysialgraph", "/programPick", "/mExerReg","/mFoodInfo", "/mExerciseInfo","/mLeave","/mMain","/mInfo","/mUpdateSubmit","/mUpdate","/memberJoin","/memberSubmit","/mbCheck","/mLogin","/tLogin","/aLogin","/logout","/searchId","/pwSearch","/idPwSearch","/memberDelete", "/mPhysical","/trainerShow"})
public class MemberController extends HttpServlet {
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
		MemberService ms;
		Forward fw=null;
		MemberPageMove mp = null;
		if(url.equals("/memberJoin")){
			//일반회원가입페이지로 이동
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("memberJoin.jsp");
		}else if(url.equals("/mMain")){
			//일반회원 메인페이지 이동
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("mMain.jsp");
		}else if(url.equals("/idPwSearch")){
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("idPwSearch.jsp");
		}else if(url.equals("/memberSubmit")){
			ms=new MemberService(request,response);
			fw=ms.execute(1);
		}
		else if(url.equals("/mbCheck")){
			ms=new MemberService(request,response);
			fw=ms.execute(2);
		}else if(url.equals("/memberDelete")){
			//회원 탈퇴
			ms=new MemberService(request,response);
			fw=ms.execute(3);
		}else if(url.equals("/mUpdate")){
			//회원정보 수정 페이지 이동
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("mUpdate.jsp");
			ms=new MemberService(request, response);
			ms.execute(4);
		}else if(url.equals("/mUpdateSubmit")){
			//회원정보 수정완료 버튼
			ms=new MemberService(request, response);
			fw=ms.execute(5);
		}else if(url.equals("/trainerPick")){
			//트레이너 등록
			System.out.println("trainerPick");
			ms=new MemberService(request,response);
			fw=ms.execute(6);
		}else if(url.equals("/programPick")){
			//프로그램 등록
			System.out.println("programPick");
			ms=new MemberService(request,response);
			fw=ms.execute(7);
		}else if(url.equals("/mLogin")){
			//로그인 성공시 일반회원메인페이지로 이동
			mp=new MemberPageMove(request,response);
			fw=mp.execute(1);
		}else if(url.equals("/tLogin")){
			//로그인 성공시 트레이너메인페이지로 이동
			mp=new MemberPageMove(request,response);
			fw=mp.execute(2);
		}
		else if(url.equals("/aLogin")){
			//로그인 성공시 일반회원메인페이지로 이동
			mp=new MemberPageMove(request,response);
			fw=mp.execute(3);
		}
		else if(url.equals("/logout")){
			//로그인 성공시 일반회원메인페이지로 이동
			mp=new MemberPageMove(request,response);
			fw=mp.execute(4);
		}
		else if(url.equals("/searchId")){
			//아이디 찾기 페이지로 이동
			mp=new MemberPageMove(request,response);
			fw=mp.execute(5);
		}
		else if(url.equals("/pwSearch")){
			//비밀번호 찾기 페이지로 이동
			mp=new MemberPageMove(request,response);
			fw=mp.execute(6);
		}else if(url.equals("/mExerciseInfo")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(7);
		}else if(url.equals("/mFoodInfo")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(8);
		}else if(url.equals("/mExerReg")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(9);
		}else if(url.equals("/mInfo")){
			//일반회원 내정보 보기 페이지 이동
			mp=new MemberPageMove(request, response);
			fw=mp.execute(10);
		}else if(url.equals("/mFoodReg")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(11);
		}else if(url.equals("/mExercise")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(12);
		}else if(url.equals("/mFood")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(13);
		}else if(url.equals("/mfoodgraph")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(14);
		}else if(url.equals("/mPhysial")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(15);
		}else if(url.equals("/mExercisegraph")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(16);
		}else if(url.equals("/mPhysialgraph")){
			mp=new MemberPageMove(request,response);
			fw=mp.execute(17);
		}/*else if(url.equals("/mPhysical")){
        //신장, 체중 등록
        System.out.println("mPhysical");
        ms=new MemberService(request,response);
        fw=ms.execute(8);
    }else if(url.equals("/mPhysicalShow")){
        //mPhysical 페이지 이동
        System.out.println("mPhysicalShow");
        mp=new MemberPageMove(request,response);
        fw=mp.execute(11);
        //------------------------
    }*/

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
