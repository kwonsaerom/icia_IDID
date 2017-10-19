package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Exercise;
import bean.Food;
import bean.Forward;
import bean.Member;
import bean.Mexercise;
import bean.Mfood;
import bean.Mphysial;
import dao.AdminDao;
import dao.MemberDao;



public class MemberPageMove {
	HttpServletRequest request;
	HttpServletResponse response;
	private Forward fw;
	MemberDao md = new MemberDao();
	bean.Member mb=null;
	bean.Trainer tr=null;
	public MemberPageMove(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	public Forward execute(int code) {
		switch(code){
		case 1:
			memberLogin();
			break;
		case 2:
			trainerLogin();
			break;
		case 3:
			adminLogin();
			break;
		case 4:
			logout();
			break;
		case 5:
			idSearch();
			break;
		case 6:
			pwSearch();
			break;
		case 7:
			MexerciseInfo();
			break;
		case 8:
			MfoodInfo();
			break;
		case 9:
			MexerciseReg();
			break;
		case 10:
			memberInfo();
			break;
		case 11:
			MfoodReg();
			break;
		case 12:
			MexerciseList();
			break;
		case 13:
			MfoodList();
			break;
		case 14:
			foodList();
			break;
		case 15:
			mPhysialReg();
			break;
		case 16:
			ExerciseList();
			break;
		case 17:
			PhysialList();
			break;
		}
		return fw;
	}
	private void PhysialList() {
		HttpSession session = request.getSession();
		Mphysial ph = new Mphysial();
		String m_id = (String) session.getAttribute("m_id");
		if(m_id!=null){
			ArrayList<Mphysial> list =md.mphgraph(m_id);
			String mPhysiallist = mPhysialListmakeHtml(list);
			request.setAttribute("mPhysiallist", mPhysiallist);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("amrchart.jsp");
		}
	}
	private String mPhysialListmakeHtml(ArrayList<Mphysial> list) {
		StringBuilder sb=new StringBuilder();
		sb.append("[{");
		for(int i=0; i<list.size(); i++){
			Mphysial ph = list.get(i);
			sb.append("'country':"+"'"+ph.getB_date()+"'"+",");
			sb.append("'visits1':"+ph.getB_weight()+"}, {");
		}
		sb.append("}];");
		return sb.toString();
	}
	private void ExerciseList() {
		HttpSession session = request.getSession();
		Mexercise fd = new Mexercise();
		String m_id = (String) session.getAttribute("m_id");
		if(m_id!=null){
			ArrayList<Mexercise> list =md.mdgraph(m_id);
			String mExerciselist = mExerciseListmakeHtml(list);
			request.setAttribute("mExerciselist", mExerciselist);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./mPhysialgraph");
		}
	}
	private String mExerciseListmakeHtml(ArrayList<Mexercise> list) {
		StringBuilder sb=new StringBuilder();
		sb.append("[{");
		for(int i=0; i<list.size(); i++){
			Mexercise me = list.get(i);
			sb.append("'country':"+"'"+me.getE_name()+"'"+",");
			sb.append("'visits1':"+me.getE_cal()+"}, {");
		}
		sb.append("}];");
		return sb.toString();
	}
	private void foodList(){
		HttpSession session = request.getSession();
		Mfood fd = new Mfood();
		String m_id = (String) session.getAttribute("m_id");
		if(m_id!=null){
			ArrayList<Mfood> list =md.mgraph(m_id);
			String mfoodlist = mFoodListmakeHtml(list);
			request.setAttribute("mfoodlist", mfoodlist);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./mExercisegraph");
		}
	}
	private String mFoodListmakeHtml(ArrayList<Mfood> list) {
		StringBuilder sb=new StringBuilder();
		sb.append("[{");
		for(int i=0; i<list.size(); i++){
			Mfood fd = list.get(i);
			sb.append("'country':"+"'"+fd.getMf_f_name()+"'"+",");
			sb.append("'visits1':"+fd.getMf_f_cal()+"}, {");
		}
		sb.append("}];");
		return sb.toString();
	}
	private void mPhysialReg() {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		
		if(m_id!=null){
			String pdate = request.getParameter("Pdate");
			double pheight = Integer.parseInt(request.getParameter("Pheight"));
			double pweight = Integer.parseInt(request.getParameter("Pweight"));
			md.Physial(m_id,pdate,pheight,pweight);
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("mPhysial.jsp");
		}
	}
	private void MexerciseList(){
		ArrayList<Exercise> nList = md.exList();
		String exnlist = ExnmakeHtml(nList);
		request.setAttribute("exnlist", exnlist);
		if(exnlist!=null){
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("mExercise.jsp");
		}
	}
	private String ExnmakeHtml(ArrayList<Exercise> nList) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<nList.size(); i++){
			Exercise ex = nList.get(i);
			sb.append("<option>"+ex.getE_name());
			sb.append("</option>");
		}
		return sb.toString();
	}
	private void MfoodReg() {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		if(m_id!=null){
			String fdate = request.getParameter("fdate");
			String fday = request.getParameter("fday");
			String fkind = request.getParameter("foodkind");
			String fname = request.getParameter("foodname");
			int famount = Integer.valueOf(request.getParameter("famount"));
			md.foodInsert(m_id,fdate,fday,fkind,fname,famount);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./mFood");
		}
	}
	private void MfoodList() {
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
			fw.setPath("mFood.jsp");
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
		sb.append("<option value='음식을 선택하세요'>음식을 선택하세요</option>");
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
	private void memberInfo() {
		MemberService ms = new MemberService(request, response);
		ms.execute(4);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("mInfo.jsp");
	}
	private void MexerciseInfo() {
		Mexercise me = new Mexercise();
		String date1=request.getParameter("select1");
		String date2=request.getParameter("select2");
		ArrayList<Mexercise> list = md.exerciselist(date1,date2);
		String Melist = EmakeHtml(list);
		request.setAttribute("Melist", Melist);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("mExerciseInfo.jsp");
	}
	private String EmakeHtml(ArrayList<Mexercise> list) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<list.size(); i++){
			Mexercise me = list.get(i);
			sb.append("<tr>");
			sb.append("<td>"+me.getDate()+"</td>");
			sb.append("<td>"+me.getE_name()+"</td>");
			sb.append("<td>"+me.getE_time()+"분"+"</td>");
			sb.append("<td>"+me.getE_cal()+"cal"+"</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	private void MexerciseReg() {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		if(m_id!=null){
			String edate = request.getParameter("edate");
			String exname = request.getParameter("exercisename");
			int time = Integer.valueOf(request.getParameter("time"));
			md.exInsert(m_id,edate,exname,time);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./mExercise");
		}
	}
	private void MfoodInfo() {
		Mfood me = new Mfood();
		String date1=request.getParameter("select1");
		String date2=request.getParameter("select2");
		ArrayList<Mfood> list = md.foodlist(date1,date2);
		String Mflist = FmakeHtml(list);
		request.setAttribute("Mflist", Mflist);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("mFoodInfo.jsp");
	}
	private String FmakeHtml(ArrayList<Mfood> list) {
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<list.size(); i++){
			Mfood mf = list.get(i);
			sb.append("<tr>");
			sb.append("<td>"+mf.getMf_date()+"</td>");
			sb.append("<td>"+mf.getMf_time()+"</td>");
			sb.append("<td>"+mf.getMf_f_kind()+"</td>");
			sb.append("<td>"+mf.getMf_f_name()+"</td>");
			sb.append("<td>"+mf.getMf_f_amount()+"g"+"</td>");
			sb.append("<td>"+mf.getMf_f_cal()+"cal"+"</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	private void idSearch() {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String msg=null;
		mb = new bean.Member();
		mb = md.mSearchId(name,email);
		tr = new bean.Trainer();
		tr = md.tSearchId(name,email);
		if(mb!=null){
			String m_id = mb.getmId();
			request.setAttribute("m_id", m_id);
		}else if(tr!=null){
			String t_id = tr.gettId();
			request.setAttribute("t_id", t_id);
		}else{
			request.setAttribute("msg", "검색실패");
		}
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("idPwSearch.jsp");
	}
	private void pwSearch() {
		String id = request.getParameter("pwid");
		String name = request.getParameter("pwname");
		String email = request.getParameter("pwemail");
		String msg=null;
		mb = new bean.Member();
		mb = md.mSearchPw(id,name,email);
		tr = new bean.Trainer();
		tr = md.tSearchPw(id,name,email);
		if(mb!=null){
			String m_pw = mb.getmPw();
			request.setAttribute("m_pw", m_pw);
		}else if(tr!=null){
			String t_pw = tr.gettPw();
			request.setAttribute("t_pw", t_pw);
		}else{
			request.setAttribute("msg", "검색실패");
		}
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("idPwSearch.jsp");
	}
	/*private void adminLogin() {
		String a_id = request.getParameter("userId");
		String a_pw = request.getParameter("userPw");
		boolean result = md.isAdmin(a_id,a_pw);
		HttpSession session = request.getSession();
		if(result){
			session.setAttribute("a_id", a_id);
		}
		else{
			session.setAttribute("msg", "실패");
		}
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("aMain.jsp");

	}*/
	private void trainerLogin() {
		String t_id = request.getParameter("userId");
		String t_pw = request.getParameter("userPw");
		boolean result = md.isTrainer(t_id,t_pw);
		HttpSession session = request.getSession();
		MemberDao mDao = new MemberDao();
		//회원관리 테이블 완성하면 시작
		ArrayList<Member> list = new ArrayList<>();
		list = makeMemberList();

		if(result){
			session.setAttribute("t_id", t_id);

			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("tMain.jsp");
		}else{
			session.setAttribute("msg", "실패");
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("login.jsp");
		}

	}

	private ArrayList<Member> makeMemberList() {	
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1'>");

		return null;
	}
	private void memberLogin() {
		String m_id = request.getParameter("userId");
		String m_pw = request.getParameter("userPw");
		System.out.println("m_id"+m_id);
		System.out.println(m_pw);
		boolean result = md.isMember(m_id,m_pw);
		System.out.println("result : "+result);
		HttpSession session = request.getSession();
		if(result){
			session.setAttribute("m_id", m_id);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("mMain.jsp");
			System.out.println("로그인성공");
		}
		else{
			session.setAttribute("msg", "실패");
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("login.jsp");
			System.out.println("로그인실패");
		}
	}
	private void logout()
	{
		HttpSession session = request.getSession();
		session.invalidate();

		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("./index");
	}

	private void adminLogin() {
		String a_id = request.getParameter("userId");
		String a_pw = request.getParameter("userPw");
		System.out.println("a_id"+a_id);
		System.out.println(a_pw);
		AdminDao aDao=new AdminDao();
		boolean result = aDao.isAdmin(a_id,a_pw);
		System.out.println("result : "+result);
		HttpSession session = request.getSession();
		if(result){
			session.setAttribute("a_id", a_id);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("/aMain");
			System.out.println("로그인성공");
		}
		else{
			session.setAttribute("msg", "실패");
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("login.jsp");
			System.out.println("로그인실패");
		}
		aDao.close();
	}
}
