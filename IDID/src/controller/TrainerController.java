package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import model.TrainerPageMove;
import model.TrainerService;


@WebServlet({"/tMain","/tLeave","/tUpdateSubmit","/tUpdate","/tInfo", "/tExercise_main", "/tFood_main", "/pInsert", "/tProgram",
	"/trainerJoin","/career","/trainerDetail", "/trainerInfo","/homeInfo","/memberList","/trainerSubmit", "/check","/trainerDelete",
	"/tFoodList","/tFoodInfo","/tExerciseList","/tExerciseInfo","/tFoodReg","/tExerReg","/tmfoodgraph","/tmExercisegraph","/tmPhysialgraph","/mDetail"})
public class TrainerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String url=uri.substring(conPath.length());
		System.out.println("url="+url);
		TrainerPageMove tp=null;
		TrainerService ts;
		Forward fw=null;
		if(url.equals("/tMain")){
			//트레이너 메인페이지 Mapping
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("tMain.jsp");
		}
		else if(url.equals("/trainerJoin")){
			//트레이너회원가입페이지로 이동
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("trainerJoin.jsp");
		}else if(url.equals("/tLeave")){
			//트레이너 회원탈퇴페이지 이동
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("tLeave.jsp");
		}
		else if(url.equals("/career")){
			//경력사항 등록페이지 Mapping
			System.out.println("Career");
			tp=new TrainerPageMove(request,response);
			fw=tp.execute(1);
		}
		else if(url.equals("/homeInfo")){
			//홈페이지 소개페이지 Mapping
			System.out.println("homeInfo");
			tp=new TrainerPageMove(request,response);
			fw=tp.execute(2);
		}
		else if(url.equals("/memberList")){
			//회원관리 페이지로 이동
			System.out.println("MemberList");
			tp=new TrainerPageMove(request,response);
			fw=tp.execute(4);
		}
		else if(url.equals("/trainerPay")){
			//환전 페이지로 이동
			System.out.println("TrainerPay");
			tp=new TrainerPageMove(request,response);
			fw=tp.execute(5);
		}else if(url.equals("/trainerInfo")){
			//트레이너 소개
			tp=new TrainerPageMove(request,response);
			fw=tp.execute(6);
		}else if(url.equals("/trainerDetail")){
			//트레이너소개 상세페이지
			tp=new TrainerPageMove(request,response);
			fw=tp.execute(8);
		}else if(url.equals("/tInfo")){
			//트레이너의 내 정보보기 페이지 이동
			tp=new TrainerPageMove(request, response);
			fw=tp.execute(9);
		}else if(url.equals("/tFoodList")){
			//트레이너의 내 정보보기 페이지 이동
			tp=new TrainerPageMove(request, response);
			fw=tp.execute(10);
		}else if(url.equals("/tFoodInfo")){
			//트레이너의 내 정보보기 페이지 이동
			tp=new TrainerPageMove(request, response);
			fw=tp.execute(11);
		}else if(url.equals("/tExerciseList")){
			//트레이너의 내 정보보기 페이지 이동
			tp=new TrainerPageMove(request, response);
			fw=tp.execute(12);
		}else if(url.equals("/tExerciseInfo")){
			//트레이너의 내 정보보기 페이지 이동
			tp=new TrainerPageMove(request, response);
			fw=tp.execute(13);
		}else if(url.equals("/trainerSubmit")){
			//트레이너 가입 값 가져오는 서비스로 이동
			System.out.println("TrainerSubmit");
			ts=new TrainerService(request,response);
			fw=ts.execute(1);
		}else if(url.equals("/check")){
			//아이디 중복 체크
			System.out.println("check");
			ts=new TrainerService(request,response);
			fw=ts.execute(2);
		}else if(url.equals("/trainerDelete")){
			//트레이너 탈퇴
			System.out.println("trainerDelete");
			ts=new TrainerService(request,response);
			fw=ts.execute(3);
		}else if(url.equals("/tProgram")){
			fw= new Forward();
			fw.setRedirect(false);
			fw.setPath("tProgram.jsp");
		}else if(url.equals("/pInsert")){
			ts = new TrainerService(request, response);
			fw=ts.execute(4);
		}else if(url.equals("/tFood_main")){
			ts = new TrainerService(request, response);
			fw=ts.execute(5);
		}else if(url.equals("/tExercise_main")){
			ts = new TrainerService(request, response);
			fw=ts.execute(6);
		}else if(url.equals("/tUpdate")){
			System.out.println("트레이너 내정보 수정");
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("tUpdate.jsp");
			ts=new TrainerService(request, response);
			ts.execute(7);
		}else if(url.equals("/tUpdateSubmit")){
			ts=new TrainerService(request, response);
			fw=ts.execute(8);
		}else if(url.equals("/tFoodReg")){
			ts=new TrainerService(request, response);
			fw=ts.execute(9);
		}else if(url.equals("/tExerReg")){
			ts=new TrainerService(request, response);
			fw=ts.execute(10);
		}else if(url.equals("/tmfoodgraph")){
			ts=new TrainerService(request, response);
			fw=ts.execute(11);
		}else if(url.equals("/tmExercisegraph")){
			ts=new TrainerService(request, response);
			fw=ts.execute(12);
		}else if(url.equals("/tmPhysialgraph")){
			ts=new TrainerService(request, response);
			fw=ts.execute(13);
		}else if(url.equals("/mDetail")){
	         ts=new TrainerService(request, response);
	         fw=ts.execute(14);
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
