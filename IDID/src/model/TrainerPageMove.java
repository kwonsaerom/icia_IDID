package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Exercise;
import bean.Food;
import bean.Forward;
import bean.MemberList;
import bean.Texercise;
import bean.Tfood;
import bean.Trainer;
import dao.MemberDao;
import dao.TrainerDao;

public class TrainerPageMove {
	HttpServletRequest request;
	HttpServletResponse response;
	private Forward fw = null;
	TrainerDao tDao = null;
	String str = null;
	MemberDao md = new MemberDao();
	bean.Texercise te = null;
	TrainerService ts;
	public TrainerPageMove(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
	public Forward execute(int code){
		switch (code){
		case 1:
			//경력사항 등록페이지로 이동
			careerMove();
			break;
		case 2:
			//홈페이지 소개페이지로 이동
			homeInfoMove();
			break;
		case 3:
			//프로그램소개 페이지로 이동

			break;
		case 4:
			//회원관리 페이지로 이동
			memberListMove();
			break;
		case 5:
			//환전페이지로 이동
			trainerPayMove();
			break;
		case 6:
			//트레이너정보페이지로 이동
			trainerInfoMove();
			break;
		case 7:

			break;
		case 8:
			detailForm();
			break;
		case 9:
			//트레이너 자신의 정보 보기
			TrainerMyInfo();
			break;
		case 10:
			Tfoodselect();
			break;
		case 11:
			tFoodInfo();
			break;
		case 12:
			tExerciseselect();
			break;
		case 13:
			tExerciseInfo();
			break;
		}
		return fw;
	}
	private void tExerciseInfo() {
		tDao = new TrainerDao();
		String date1=request.getParameter("select1");
		String date2=request.getParameter("select2");
		ArrayList<Texercise> list = tDao.exerciselist(date1,date2);
		String Telist = EmakeHtml(list);
		request.setAttribute("Telist", Telist);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("tExerciseInfo.jsp");
		tDao.close();
	}
	private String EmakeHtml(ArrayList<Texercise> list) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<list.size(); i++){
			te = new bean.Texercise();
			te = list.get(i);
			sb.append("<tr>");
			sb.append("<td>"+te.getTe_date()+"</td>");
			sb.append("<td>"+te.getTe_e_name()+"</td>");
			sb.append("<td>"+te.getTe_e_time()+"분"+"</td>");
			sb.append("<td>"+te.getTe_e_cal()+"cal"+"</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	private void tExerciseselect() {
		ArrayList<Exercise> nList = md.exList();
		String exnlist = ExnmakeHtml(nList);
		request.setAttribute("exnlist", exnlist);
		if(exnlist!=null){
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("tExercise.jsp");
		}
	}
	private String ExnmakeHtml(ArrayList<Exercise> nList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<option>운동종류를 선택하세요</option>");
		for(int i=0; i<nList.size(); i++){
			Exercise ex = nList.get(i);
			sb.append("<option>"+ex.getE_name());
			sb.append("</option>");
		}
		return sb.toString();
	}
	private void tFoodInfo() {
		Tfood tf = new Tfood();
		TrainerDao tDao = new TrainerDao();
		String date1=request.getParameter("select1");
		String date2=request.getParameter("select2");
		ArrayList<Tfood> list = tDao.foodlist(date1,date2);
		String Tflist = tFmakeHtml(list);
		request.setAttribute("Tflist", Tflist);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("tFoodInfo.jsp");
		tDao.close();
	}
	private String tFmakeHtml(ArrayList<Tfood> list) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<list.size(); i++){
			Tfood tf = list.get(i);
			sb.append("<tr>");
			sb.append("<td>"+tf.getTf_date()+"</td>");
			sb.append("<td>"+tf.getTf_time()+"</td>");
			sb.append("<td>"+tf.getTf_f_kind()+"</td>");
			sb.append("<td>"+tf.getTf_f_name()+"</td>");
			sb.append("<td>"+tf.getTf_f_amount()+"g"+"</td>");
			sb.append("<td>"+tf.getTf_f_cal()+"cal"+"</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	private void Tfoodselect() {
		Food fd = new Food();
		ArrayList<Food> fkList = md.fList();
		String fklist = fkmakeHtml(fkList);
		request.setAttribute("fklist", fklist);

		String fkind = request.getParameter("selectedValue");
		ArrayList<Food> fnList = md.fList(fkind);
		String fnlist = fnmakeHtml(fnList);
		request.setAttribute("fnlist", fnlist);
		if(fkind==null){
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("tFood.jsp");
		}else{
			try {
				response.getWriter().write(fnlist);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String fkmakeHtml(ArrayList<Food> fnList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<option>음식분류를 선택하세요</option>");
		for(int i=0; i<fnList.size(); i++){
			Food fd = fnList.get(i);
			sb.append("<option value='"+fd.getF_kind()+"'>"+fd.getF_kind());
			sb.append("</option>");
		}
		return sb.toString();
	}
	private String fnmakeHtml(ArrayList<Food> fkList) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<fkList.size(); i++){
			Food fd = fkList.get(i);
			sb.append("<option>"+fd.getF_name());
			sb.append("</option>,");
		}
		return sb.toString();
	}
	private void TrainerMyInfo() {
		ts=new TrainerService(request, response);
		ts.execute(7);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("tInfo.jsp");
	}
	
	private void detailForm() {
		String id = request.getParameter("id");
		System.out.println(id);
		fw = new Forward();
		TrainerDao tDao = new TrainerDao();
		Trainer tn= tDao.detailTrainer(id);
		/*str = makeDetail();*/
		request.setAttribute("profilePhoto", "<img src='image"+"/"+tn.gettProfilephoto()+"'width='150px'/><br/>");
		request.setAttribute("name", tn.gettName());
		request.setAttribute("age", tn.gettBirth());
		request.setAttribute("gender", tn.gettGender());
		request.setAttribute("email", tn.gettEmail());
		request.setAttribute("birth1", tn.gettBirth1());
		request.setAttribute("birth2", tn.gettBirth2());
		request.setAttribute("birth3", tn.gettBirth3());
		fw.setRedirect(false);
		fw.setPath("trainerDetail.jsp");
		tDao.close();
	}

	 private String makeHtml_tList(ArrayList<Trainer> tList) {
	      StringBuilder sb = new StringBuilder();
	      sb.append("<div class='trainercontainer'>");
	      for(int i=0; i<tList.size(); i++){
	         Trainer t = tList.get(i);
	         /*sb.append("<div class='trainer' onclick=detail('"+t.gettId()+"')>");*/
	         sb.append("<div class='trainer' onclick=\"javascript:AJ('trainerDetail?id="+t.gettId()+"', '#printP')\">");
	         sb.append("<img src='image/"+t.gettProfilephoto()+"'width='150px'/><br/>");
	         sb.append("<h1>"+t.gettName()+"</h1>");
	         sb.append("</div>");
	      }
		sb.append("</div>");
		return sb.toString();
	}
	private void trainerInfoMove() {
		fw=new Forward();
		ArrayList<Trainer> tList = new ArrayList<Trainer>();
		tDao = new TrainerDao();
		tList = tDao.TrainerList();
		String html = null;
		if(tList != null){
			System.out.println("테스트");
			html = makeHtml_tList(tList);
			request.setAttribute("tList", html);
		}
		tDao.close();
		fw.setRedirect(false);
		fw.setPath("trainerInfo.jsp");

	}
	private void trainerPayMove() {
		// 환전페이지 이동
		fw=new Forward();
		fw.setRedirect(true);
		fw.setPath("trainerPay.jsp");

	}
	private void memberListMove() {
		HttpSession ss = request.getSession();
		String tId = (String)ss.getAttribute("t_id");
		MemberDao mDao = new MemberDao();	
		ArrayList<MemberList> mList = mDao.mSelectList(tId);
		String mListMake = makeMemberList(mList); 
		request.setAttribute("mListMake", mListMake);
		//회원관리페이지 이동
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("memberList.jsp");
		mDao.close();

	}
	private String makeMemberList(ArrayList<MemberList> mList) {
	      StringBuilder sb = new StringBuilder();
	      MemberList ml = new MemberList();
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);//2017
	      sb.append("<h1>"+"회원관리 페이지"+"</h1>");
	      sb.append("<table border='1' class='table table-striped'>");
	      sb.append("<tr><th>이름</th><th>나이</th><th>프로그램 종류</th><th>상세보기</th><th>변화그래프</th></tr>");
	      for(int i=0; i<mList.size(); i++){
	         ml = mList.get(i);
	         String birth = ml.getM_birth();
	         String trainerCal = birth.substring(0, 4);
	         int age = (year+1) - Integer.parseInt(trainerCal);
	         String age2=String.valueOf(age);
	         sb.append("<tr><td>"+ml.getM_name()+"</td>");
	         sb.append("<td>"+age+"</td>");
	         sb.append("<td>"+ml.getP_name()+"</td>");
	         sb.append("<td><input type='button' onclick='memberdetail(this)' value='상세보기'/></td>");
	         sb.append("<td><input type='button' onclick='graph(this)' value='그래프보기' /></td></tr>");
	      }
	      sb.append("</table>");
	      sb.append("<hr/>");
	      return sb.toString();
	   }
	private void careerMove() {
		//경력사항 등록페이지 이동
		fw=new Forward();
		fw.setRedirect(true);
		fw.setPath("career.jsp");

	}
	private void homeInfoMove() {
		//홈페이지 소개 페이지 이동
		fw=new Forward();
		fw.setRedirect(true);
		fw.setPath("homeInfo.jsp");
	}
}
