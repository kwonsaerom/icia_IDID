package model;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Forward;
import bean.Member;
import bean.MemberList;
import bean.Mexercise;
import bean.Mfood;
import bean.Mphysial;
import bean.Program;
import bean.Trainer;
import dao.MemberDao;
import dao.ProgramDao;
import dao.TrainerDao;

public class TrainerService {
	HttpServletRequest request;
	HttpServletResponse response;
	private Forward fw;
    private String msg;
    
    public TrainerService(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}
    
    public Forward execute(int code){
		switch(code){
		case 1:
			join(); break;
		case 2:
			check(); break;
		case 3:
	        delete(); break;
		case 4:
			pInsert(); break;
		case 5:
			tFoodMList(); break;
		case 6:
			tExerciseMList();
			break;
		case 7:
	        update();
	        break;
	    case 8:
	        updateCom();
	        break;
	    case 9:
	    	tfoodReg();
	        break;
	    case 10:
	    	tExerciseReg();
	        break;
	    case 11:
			tmfoodList();
			break;
		case 12:
			tmExerciseList();
			break;
		case 13:
			tmPhysialList();
			break;
		case 14:
	         tMemberDetail();
	         break;
		default:break;
		}
		return fw;
	}
    private void tMemberDetail() {
        HttpSession session = request.getSession();
        String t_id = (String) session.getAttribute("t_id");
        TrainerDao tDao=new TrainerDao();
        Member mb = new Member();
        if(t_id!=null){
           ArrayList<Member> list=tDao.DetailMember(t_id);
           String tmDetail = tmDetailmakeHtml(list);
           request.setAttribute("tmDetail", tmDetail);
           fw = new Forward();
          fw.setRedirect(false);
          fw.setPath("memberList.jsp");
          tDao.close();
        }
    }
    private String tmDetailmakeHtml(ArrayList<Member> list) {
        StringBuilder sb=new StringBuilder();
        sb.append("<form name='mainForm' class='mainForm'>");
        for(int i=0; i<list.size(); i++){
           Member mb = list.get(i);
           sb.append("<table class='memberI' class='table table-striped'>");
           sb.append("<tr><td>"+"이름:"+mb.getmName()+"</td></tr>");
           sb.append("<tr><td>"+"성별:"+mb.getmGender()+"</td></tr>");
           sb.append("<tr><td>"+"생년월일:"+mb.getmBirth()+"</td></tr>");
           sb.append("<tr><td>"+"주소:"+mb.getmDomicile()+"</td></tr>");
           sb.append("<tr><td>"+"이메일:"+mb.getmEmail()+"</td></tr>");
           sb.append("<tr><td>"+"신장:"+mb.getmBheight()+"</td></tr>");
           sb.append("<tr><td>"+"체중:"+mb.getmBweight()+"</td></tr>");
           sb.append("<tr><td>"+"희망사항:"+mb.getmWish()+"</td></tr>");
           sb.append("</table>");
        }
        sb.append("<input type='button' onclick='back()' value='메인으로 돌아가기' class='return'");
        sb.append("</form>");
        return sb.toString();
     }
    private void tmPhysialList() {
    	HttpSession session = request.getSession();
		Mphysial ph = new Mphysial();
		TrainerDao tDao=new TrainerDao();
		String t_id = (String) session.getAttribute("t_id");
		if(t_id!=null){
			ArrayList<Mphysial> list =tDao.tmphgraph(t_id);
			String tmPhysiallist = tmPhysialListmakeHtml(list);
			request.setAttribute("tmPhysiallist", tmPhysiallist);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("amrchart.jsp");
			tDao.close();
		}
	}

	private String tmPhysialListmakeHtml(ArrayList<Mphysial> list) {
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
    private void tmExerciseList() {
    	HttpSession session = request.getSession();
		Mexercise fd = new Mexercise();
		TrainerDao tDao=new TrainerDao();
		String t_id = (String) session.getAttribute("t_id");
		if(t_id!=null){
			ArrayList<Mexercise> list =tDao.tmdgraph(t_id);
			String tmExerciselist = tmExerciseListmakeHtml(list);
			request.setAttribute("tmExerciselist", tmExerciselist);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./tmPhysialgraph");
			tDao.close();
		}
	}

	private String tmExerciseListmakeHtml(ArrayList<Mexercise> list) {
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

    private void tmfoodList() {
    	HttpSession session = request.getSession();
		Mfood fd = new Mfood();
		TrainerDao tDao=new TrainerDao();
		String t_id = (String) session.getAttribute("t_id");
		if(t_id!=null){
			ArrayList<Mfood> list =tDao.tmgraph(t_id);
			String tmfoodlist = tmFoodListmakeHtml(list);
			request.setAttribute("tmfoodlist", tmfoodlist);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./tmExercisegraph");
			tDao.close();
		}
	}
    private String tmFoodListmakeHtml(ArrayList<Mfood> list) {
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
    private void tExerciseReg() {
    	HttpSession session = request.getSession();
		String t_id = (String) session.getAttribute("t_id");
		TrainerDao tDao=new TrainerDao();
		if(t_id!=null){
			String edate = request.getParameter("edate");
			String exname = request.getParameter("exercisename");
			int time = Integer.valueOf(request.getParameter("time"));
			tDao.exInsert(t_id,edate,exname,time);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./tExerciseList");
			tDao.close();
		}
	}
    private void tfoodReg() {
    	HttpSession session = request.getSession();
		String t_id = (String) session.getAttribute("t_id");
		TrainerDao tDao=new TrainerDao();
		if(t_id!=null){
			String fdate = request.getParameter("fdate");
			String fday = request.getParameter("fday");
			String fkind = request.getParameter("foodkind");
			String fname = request.getParameter("foodname");
			int famount = Integer.valueOf(request.getParameter("famount"));
			tDao.foodInsert(t_id,fdate,fday,fkind,fname,famount);
			fw = new Forward();
			fw.setRedirect(false);
			fw.setPath("./tFoodList");
			tDao.close();
		}
	}
    private void updateCom() {
       String tId=request.getParameter("id");
       String tPw=request.getParameter("pw");
       String tName=request.getParameter("name");
       String tGender=request.getParameter("gender");
       String tBirth =request.getParameter("birth");
       String addr1=request.getParameter("addr1");
       String addr2=request.getParameter("addr2");
       String tDomicile =addr1+" "+addr2;
       String tEmail =request.getParameter("email");
       String tProfilePhoto=request.getParameter("profilePhoto");
       Trainer tr=new Trainer(tId,tPw,tName,tGender,tBirth,tDomicile,tEmail,tProfilePhoto);
       TrainerDao tDao=new TrainerDao();
       tr=tDao.updateTrainerInfo(tr);
       System.out.println(tr);
       fw=new Forward();
       fw.setRedirect(false);
       fw.setPath("tMain.jsp");
       tDao.close();
       
    }

    private void update() {
       HttpSession session=request.getSession();
       String tId=(String) request.getSession().getAttribute("t_id");
       System.out.println(tId);
       TrainerDao tDao=new TrainerDao();
       Trainer tr = tDao.getInfo(tId);
       session.getId();
       String id = tr.gettId();
       request.setAttribute("id", id);
       
       String pw = tr.gettPw();
       request.setAttribute("pw", pw);
       
       String name = tr.gettName();
       request.setAttribute("name", name);
       
       String gender = tr.gettGender();
       request.setAttribute("gender", gender);
       System.out.println(gender);
       
       String domicile = tr.gettDomicile();
       request.setAttribute("domicile", domicile);
       
       String email = tr.gettEmail();
       request.setAttribute("email", email);
       
       String birth=tr.gettBirth().substring(0,10);
       request.setAttribute("birth", birth);
       
       String profilePhoto=tr.gettProfilephoto();
       request.setAttribute("profilePhoto", profilePhoto);
       tDao.close();
    }
    private void tExerciseMList() {
    	MemberDao mDao = new MemberDao();
    	HttpSession ss = request.getSession();
		String tId = (String)ss.getAttribute("t_id");
		System.out.println("tID확인=="+tId);
		ArrayList<MemberList> list = mDao.tExerciseMList(tId);
		String tExerciseListHtml = maketExerMListHtml(list);
		request.setAttribute("tExerciseListHtml", tExerciseListHtml);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("tExercise_main.jsp");
		mDao.close();
	}

    private String maketExerMListHtml(ArrayList<MemberList> list) {
        StringBuilder sb = new StringBuilder();
        MemberList ml = null;
        sb.append("<table border='1' class='table table-striped'>");
        sb.append("<tr><th>이름</th><th>프로그램 종류</th><th>운동등록하기</th><th>회원이 등록한 운동보기</th></tr>");
        for(int i=0; i<list.size(); i++){
           ml = list.get(i);
           sb.append("<tr><td>"+ml.getM_name()+"</td>");
           sb.append("<td>"+ml.getP_name()+"</td>");
           sb.append("<td><input type='button' onclick='exreg(this)' id='btn' value='운동등록' /></td>");
           sb.append("<td><input type='button' onclick='exlist(this)' value='운동보기'/></td></tr>");
        }
        sb.append("</table>");
        sb.append("<script>function btn(){");
        sb.append("");
        sb.append("}</script>");
        return sb.toString();
     }

	private void tFoodMList() {
    	HttpSession ss = request.getSession();
    	String tId = (String)ss.getAttribute("t_id");
    	MemberDao mDao = new MemberDao();
    	ArrayList<MemberList> list = mDao.mFoodList(tId);
    	String foodMListHtml = makeFoodMList(list);
    	request.setAttribute("foodMListHtml", foodMListHtml);
    	fw = new Forward();
    	fw.setRedirect(false);
    	fw.setPath("tFood_main.jsp");
    	mDao.close();
	}

	private String makeFoodMList(ArrayList<MemberList> list) {
	      StringBuilder sb = new StringBuilder();
	      MemberList ml = null;
	      sb.append("<table border='1' class='table table-striped'>");
	      sb.append("<tr><th>이름</th><th>프로그램 종류</th><th>식단관리</th><th>회원이 등록한 식단보기</th></tr>");
	      for(int i=0; i<list.size(); i++){
	         ml = list.get(i);
	         sb.append("<tr><td>"+ml.getM_name()+"</td>");
	         sb.append("<td>"+ml.getP_name()+"</td>");
	         sb.append("<td><input type='button' id='fdregister' onclick='fdreg(this)' value='식단등록'/></td>");
	         sb.append("<td><input type='button' onclick='fdlist(this)' value='식단보기'/></td></tr>");
	      }
	      sb.append("</table>");
	      return sb.toString();
	   }

	private void pInsert() {
    	TrainerDao tDao = new TrainerDao();
    	ProgramDao pDao = new ProgramDao();
    	HttpSession ss= request.getSession();
		String p_tId = (String)ss.getAttribute("t_id");
		System.out.println("p_tId="+p_tId);
		Trainer t = tDao.tSelect(p_tId);		
		String p_tName = t.gettName(); //트레이너 이름
		String pName = request.getParameter("pName");//프로그램 이름
		String pInfo = request.getParameter("pInfo");//프로그램 소개
		long pPrice = Long.parseLong(request.getParameter("pPrice"));//프로그램 가격
		
		Program program = new Program(p_tId, p_tName, pName, pInfo, pPrice);
		pDao.pInsert(program);
		fw = new Forward();
		fw.setRedirect(false);
		fw.setPath("tMain.jsp");
		pDao.close();
	}

	private void delete() {
	      TrainerDao tDao = new TrainerDao();
	      HttpSession ss=request.getSession();
	      String id=(String) ss.getAttribute("t_id");
	      String pw=request.getParameter("pw");
	      int cnt = tDao.delete(id,pw);
	      if (cnt != 0) {
	         request.setAttribute("msg", "탈퇴 완료");
	      } else {
	         System.out.println("실패");
	         request.setAttribute("msg", "탈퇴 실패");
	      }
	      fw=new Forward();
	      fw.setRedirect(false);
	      fw.setPath("home.jsp");
	      tDao.close();      
	   }
	private void check() {
		 int size=1*1024*1024; //1MB
	      String uploadPath=request.getRealPath("image"); //upload라는 폴더에 이미지를 저장한다.
	      //String uploadPath=request.getServletContext().getRealPath("upload");
	      System.out.println("서버폴더:"+uploadPath);
	      MultipartRequest multi;
	      try {
	         multi = new MultipartRequest(
	               request,
	               uploadPath, //업로드 경로  
	               size, //업로드 이미지 크기(용량)
	               "UTF-8",
	               new DefaultFileRenamePolicy() //업로드 파일의 이름이 같을 경우 덮어쓰기를 하지 않고, 올릴 때 이름 뒤에 일련번호를 써서 관리
	               //불러올 때는 서버 파일명으로 불러오기 때문에 DB에 서버 파일명으로 저장한다.
	               );
	         String tId=multi.getParameter("id");
	         TrainerDao tDao=new TrainerDao();
	         if(tDao.isId(tId)){
	            request.setAttribute("msg","중복된 아이디");
	            fw=new Forward();
	            fw.setRedirect(false);
	            fw.setPath("trainerJoin.jsp");
	         }
	         else{
	            request.setAttribute("msg","사용 가능한 아이디");
	            System.out.println(tId);
	            request.setAttribute("tId", tId);
	            fw=new Forward();
	            fw.setRedirect(false);
	            fw.setPath("trainerJoin.jsp");
	         }
	         tDao.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}

	private void join() {
		TrainerDao tDao=new TrainerDao();
	      int size=1*1024*1024; //1MB
	      String uploadPath=request.getRealPath("image"); //upload라는 폴더에 이미지를 저장한다.
	      //String uploadPath=request.getServletContext().getRealPath("upload");
	      System.out.println("서버폴더:"+uploadPath);
	      try {
	         MultipartRequest multi=new MultipartRequest(
	               request,
	               uploadPath, //업로드 경로  
	               size, //업로드 이미지 크기(용량)
	               "UTF-8",
	               new DefaultFileRenamePolicy() //업로드 파일의 이름이 같을 경우 덮어쓰기를 하지 않고, 올릴 때 이름 뒤에 일련번호를 써서 관리
	               //불러올 때는 서버 파일명으로 불러오기 때문에 DB에 서버 파일명으로 저장한다.
	               );
	         tDao=new TrainerDao();
	         String tId=multi.getParameter("id");
	         String tPw=multi.getParameter("pw");
	         String tName=multi.getParameter("name");
	         String tGender=multi.getParameter("gender");
	         String tBirth=multi.getParameter("birth");
	         String addr1=multi.getParameter("addr1");
	         String addr2=multi.getParameter("addr2");
	         String tDomicile=addr1+" "+addr2;
	         String tEmail=multi.getParameter("email");
	         String tProfilephoto=multi.getFilesystemName("profilePhoto");
	         Trainer tn=new Trainer(tId,tPw,tName,tGender,tBirth,tDomicile,tEmail,tProfilephoto);
	         if(tDao.isId(tId)){ 
	            msg="이미 존재하는 아이디";
	            request.setAttribute("msg",msg);
	         }else{//회원가입
	            if(tDao.insertTrainer(tn)){
	               fw=new Forward();
	               fw.setRedirect(false);
	               fw.setPath("home.jsp");

	            }else{ //회원가입 실패
	               request.setAttribute("msg", "회원가입 실패");
	               fw=new Forward();
	               fw.setRedirect(false);
	               fw.setPath("trainerJoin.jsp");
	            }
	         }
	         fw=new Forward();
	         fw.setRedirect(false);
	         fw.setPath("home.jsp");
	         tDao.close();
	      }catch (IOException e) {
	         System.out.println("업로드 에러");
	         e.printStackTrace();
	      }
	}

}
