package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Body;
import bean.Forward;
import bean.Member;
import bean.Program;
import bean.Register;
import bean.Trainer;
import dao.MemberDao;
import dao.ProgramDao;
import dao.TrainerDao;

public class MemberService {
	HttpServletRequest request;
	HttpServletResponse response;
	private Forward fw;
	private String msg;


	public MemberService(HttpServletRequest request, HttpServletResponse response) {
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
	         //내정보보기 페이지
	         update();
	         break;
	    case 5:
	         //정보 업데이트 시동
	         updateCom();
	         break;
	    case 6:
	         trainerSelect(); break;
	    case 7:
	         programPick(); break;
	    /*case 8:
            mPhysical(); break;*/
		default:break;
		}
		return fw;
	}
	/*private void mPhysical() {
	       HttpSession ss=request.getSession();
	       String m_id=(String) ss.getAttribute("m_id");
	       System.out.println(m_id);
	       MemberDao mDao=new MemberDao();
	       Register rg=new Register();
	       rg=mDao.t_nameSelect(m_id);
	       String t_id=rg.getRg_t_id();
	       String date=request.getParameter("date");
	       int height=Integer.valueOf(request.getParameter("height"));
	       int weight=Integer.valueOf(request.getParameter("weight"));
	       Body bd=new Body(m_id,t_id,date,height,weight);
	       boolean result=mDao.insertBody(bd);
	       if(result==true){
	        request.setAttribute("msg", "체중 등록 성공");
	       fw=new Forward();
	       fw.setPath("mMain.jsp");
	       fw.setRedirect(false);
	       }else if(result==false){
	          request.setAttribute("msg", "체중 등록 실패");
	          fw=new Forward();
	          fw.setPath("mPhysical.jsp");
	          fw.setRedirect(false);
	       }
	   }*/
    private void programPick() {
        HttpSession ss=request.getSession();
        String m_id=(String) ss.getAttribute("m_id");
        String p_name=request.getParameter("p_name");
        String t_name=request.getParameter("p_t_name");
        System.out.println(t_name);
        MemberDao mDao=new MemberDao();
        Member mb=new Member();
        mb=mDao.nameSelect(m_id);
        String m_name=mb.getmName();
        mDao.close();
        TrainerDao tDao=new TrainerDao();
        Trainer tn=new Trainer();
        tn=tDao.idSelect(t_name);
        String t_id=tn.gettId();
        tDao.close();
        ProgramDao pDao=new ProgramDao();
        Program pg=new Program();
        pg=pDao.p_t_codeSelect(p_name);
        int p_code=pg.getP_code();
        Register rg=new Register(m_id,t_id,m_name,t_name,p_code);
        pDao.insertRegister(rg);
        fw=new Forward();
        fw.setPath("mMain.jsp");
        fw.setRedirect(false);
        pDao.close();
        
     }


    private void trainerSelect() {
        TrainerDao tDao=new TrainerDao();
        ArrayList<Trainer> list = tDao.select();
        String li = makeHtml(list);
        request.setAttribute("li", li);
        
        ProgramDao pDao=new ProgramDao();
        String t_name=request.getParameter("selectedValue");
        ArrayList<Program> pList = pDao.select(t_name);
        String li2 = makeHtml2(pList);
        request.setAttribute("li2", li2);
        
        if(t_name==null)
        {
           fw=new Forward();
           fw.setRedirect(false);
           fw.setPath("trainerPick.jsp");
        }
        else
        {
           try
           {
              response.getWriter().write(li2);
           }
           catch(Exception e)
           {
              e.printStackTrace();
           }
        }
        tDao.close();
        pDao.close();
        
     }

    private String makeHtml2(ArrayList<Program> pList) {
        StringBuilder sb2 = new StringBuilder();
           sb2.append("<option value='선택'>"+"프로그램 선택"+"</option>,");
        for (int i = 0; i < pList.size(); i++) {
           Program value = pList.get(i);
           sb2.append("<option value='"+value.getP_name()+"'>" + value.getP_name() + "</option>,");
        }
        return sb2.toString();
     }
  

     public String makeHtml(ArrayList<Trainer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<select name='p_t_name' id='p_t_name' onchange='tname()'>");
        sb.append("<option value='선택'>"+"트레이너 선택"+"</option>");
        for (int i = 0; i < list.size(); i++) {
           Trainer value = list.get(i);
           sb.append("<option value='"+value.gettName()+"'>" + value.gettName() + "</option>");
        }
        sb.append("</select>");
        return sb.toString();
     }
	private void updateCom() {
	      //정보업데이트 시동
	      String mId=request.getParameter("id");
	      String mPw=request.getParameter("pw");
	      String mName=request.getParameter("name");
	      String mGender=request.getParameter("gender");
	      String mBirth =request.getParameter("birth");
	      String addr1=request.getParameter("addr1");
	      String addr2=request.getParameter("addr2");
	      String mDomicile =addr1+" "+addr2;
	      String mEmail =request.getParameter("email");
	      int mBheight =Integer.parseInt(request.getParameter("height"));
	      int mBweight =Integer.parseInt(request.getParameter("weight"));
	      String mWish =request.getParameter("hope");
	      Member mb=new Member(mId,mPw,mName,mGender,mBirth,mDomicile,mEmail,mBheight,mBweight,mWish);
	      MemberDao mDao=new MemberDao();
	      mb=mDao.updateMemberInfo(mb);
	      System.out.println(mb);
	      fw=new Forward();
	      fw.setRedirect(false);
	      fw.setPath("home.jsp");
	      mDao.close();
	   }

	   private void update() {
	      //내 정보보기 페이지 정보값 가져오기
	      HttpSession session=request.getSession();
	      String mId=(String) request.getSession().getAttribute("m_id");
	      MemberDao mDao=new MemberDao();
	      Member mb = mDao.getInfo(mId);
	      session.getId();
	      String id = mb.getmId();
	      request.setAttribute("id", id);
	      
	      String pw = mb.getmPw();
	      request.setAttribute("pw", pw);
	      
	      String name = mb.getmName();
	      request.setAttribute("name", name);
	      
	      String gender = mb.getmGender();
	      request.setAttribute("gender", gender);
	      System.out.println(gender);
	      
	      String domicile = mb.getmDomicile();
	      request.setAttribute("domicile", domicile);
	      
	      String email = mb.getmEmail();
	      request.setAttribute("email", email);
	      
	      String birth=mb.getmBirth().substring(0,10);
	      request.setAttribute("birth", birth);
	      
	      String wish=mb.getmWish();
	      request.setAttribute("wish", wish);
	      
	      int height=mb.getmBheight();
	      request.setAttribute("height", height);
	      
	      int weight=mb.getmBweight();
	      request.setAttribute("weight", weight);
	      mDao.close();
	   }
	private void delete() {
	      MemberDao mDao = new MemberDao();
	      HttpSession ss=request.getSession();
	      String id=(String) ss.getAttribute("m_id");
	      String m_pw=request.getParameter("deletePw");
	      int cnt = mDao.delete(id,m_pw);
	      if (cnt != 0) {
	         request.setAttribute("msg", "탈퇴 완료");
	      } else {
	         request.setAttribute("msg", "탈퇴 실패");
	      }
	      fw=new Forward();
	      fw.setRedirect(false);
	      fw.setPath("home.jsp");
	      mDao.close();
	   }

	private void check() {
		String mId=request.getParameter("id");
		MemberDao mDao=new MemberDao();
		if(mDao.isId(mId)){
			request.setAttribute("msg","중복된 아이디");
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("memberJoin.jsp");
			mDao.close();
		}else{
			 request.setAttribute("msg","사용 가능한 아이디");
			 request.setAttribute("mId", mId);
			 fw=new Forward();
			 fw.setRedirect(false);
			 fw.setPath("memberJoin.jsp");
			 mDao.close();
		}
	}
	private void join() {
		String mId=request.getParameter("id");
		String mPw=request.getParameter("pw");
		String mName=request.getParameter("name");
		String mGender=request.getParameter("gender");
		String mBirth =request.getParameter("birth");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String mDomicile =addr1+" "+addr2;
		String mEmail =request.getParameter("email");
		int mBheight =Integer.parseInt(request.getParameter("height"));
		int mBweight =Integer.parseInt(request.getParameter("weight"));
		String mWish =request.getParameter("hope");
		Member mb=new Member(mId,mPw,mName,mGender,mBirth,mDomicile,mEmail,mBheight,mBweight,mWish);
		MemberDao mDao=new MemberDao();
		if(mDao.isId(mId)){ 
			msg="이미 존재하는 아이디";
			request.setAttribute("msg",msg);
		}else{//회원가입
			if(mDao.insertMember(mb)){
				fw=new Forward();
				fw.setRedirect(false);
				fw.setPath("home.jsp");
			}else{ //회원가입 실패
				request.setAttribute("msg", "회원가입 실패");
				fw=new Forward();
				fw.setRedirect(false);
				fw.setPath("memberJoin.jsp");
			}
		}
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("home.jsp");
		mDao.close();
	}
}
