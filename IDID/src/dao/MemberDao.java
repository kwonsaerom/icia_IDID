package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Body;
import bean.Exercise;
import bean.Food;
import bean.Member;
import bean.MemberList;
import bean.Mexercise;
import bean.Mfood;
import bean.Mphysial;
import bean.Register;
import bean.Trainer;


public class MemberDao {
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	DataSource ds; //추가
	ResultSetMetaData rsmd;

	public MemberDao(){
		try{
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con=ds.getConnection();
			System.out.println("커넥션 풀 연결 성공");
		}catch(Exception ex){
			System.out.println("커넥션 풀 연결 실패"); 
		}
	}
	public void close(){ //커넥션 풀에 커넥션 반납
		try
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	//아이디 존재 여부 판독
	public boolean isId(String mId) {
		String sql="SELECT COUNT(*) FROM MEMBER "+"WHERE M_ID=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("아이디 중복 확인 실패");
			e.printStackTrace();
		}return boolConvert(result);
	}

	private boolean boolConvert(int result) {
		return (result!=0)?true:false;
	}

	//일반회원 회원가입 
	public boolean insertMember(Member mb) {
		String sql="INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			System.out.println("회원가입 성공");
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mb.getmId());
			pstmt.setNString(2, mb.getmPw());
			pstmt.setNString(3, mb.getmName());
			pstmt.setNString(4, mb.getmGender());
			pstmt.setNString(5, mb.getmBirth());
			pstmt.setNString(6, mb.getmDomicile());
			pstmt.setNString(7, mb.getmEmail());
			pstmt.setInt(8, mb.getmBheight());
			pstmt.setInt(9, mb.getmBweight());
			pstmt.setNString(10, mb.getmWish());
			result=pstmt.executeUpdate();
		}catch (SQLException e){
			System.out.println("member insert 실패");
			e.printStackTrace();
		}return boolConvert(result);
	}
	public Member mSearchId(String name, String email) {
		String sql = "SELECT M_ID FROM MEMBER WHERE M_NAME=? AND M_EMAIL=?";
		Member mb=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, name);
			pstmt.setNString(2, email);

			rs=pstmt.executeQuery();
			if(rs.next()){
				mb=new Member();
				mb.setmId(rs.getNString("M_ID"));
				return mb;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	public Member mSearchPw(String id, String name, String email) {
		String sql = "SELECT M_PW FROM MEMBER WHERE M_ID=? AND M_NAME=? AND M_EMAIL=?";
		Member mb=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, name);
			pstmt.setNString(3, email);

			rs=pstmt.executeQuery();
			if(rs.next()){
				mb=new Member();
				mb.setmPw(rs.getNString("M_PW"));
				return mb;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	public Trainer tSearchId(String name, String email) {
		String sql = "SELECT T_ID FROM TRAINER WHERE T_NAME=? AND T_EMAIL=?";
		Trainer tr=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, name);
			pstmt.setNString(2, email);

			rs=pstmt.executeQuery();
			if(rs.next()){
				tr=new Trainer();
				tr.settId(rs.getNString("T_ID"));
				return tr;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	public Trainer tSearchPw(String id, String name, String email) {
		String sql = "SELECT T_PW FROM TRAINER WHERE T_ID=? AND T_NAME=? AND T_EMAIL=?";
		Trainer tr=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, name);
			pstmt.setNString(3, email);

			rs=pstmt.executeQuery();
			if(rs.next()){
				tr=new Trainer();
				tr.settPw(rs.getNString("T_PW"));
				return tr;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	public boolean isMember(String m_id, String m_pw) {
		String sql = "SELECT * FROM MEMBER WHERE M_ID=?";
		boolean result=false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getNString("m_pw").equals(m_pw)){
					result=true;
					return result;
				}
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return false;
	}

	public boolean isTrainer(String t_id, String t_pw) {
		String sql = "SELECT * FROM TRAINER WHERE T_ID=?";
		boolean result=false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, t_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getNString("T_PW").equals(t_pw)){
					result=true;
				}
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	public boolean isAdmin(String a_id, String a_pw) {
		String sql = "SELECT * FROM ADMIN WHERE M_ID=?";
		boolean result=false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, a_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getNString("a_pw").equals(a_pw)){
					result=true;
				}
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	//아이디 비밀번호 확인 후 탈퇴
	public int delete(String m_id,String m_pw) {
		String sql="DELETE FROM MEMBER WHERE M_ID=? AND M_PW=?";
		int count = 0;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			pstmt.setNString(2, m_pw);
			count=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	//트레이너 등록할 때 회원이름 갖고 온다.
	public Member nameSelect(String m_id) {
		String sql = "SELECT M_NAME FROM MEMBER WHERE M_ID=?";
		Member mb=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				System.out.println("?");
				mb=new Member();
				mb.setmName(rs.getNString("M_NAME"));
				return mb;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	//회원리스트
	public ArrayList<MemberList> mSelectList(String tId) {
		ArrayList<MemberList> list = new ArrayList<MemberList>();
		MemberList ml = null;
		pstmt=null;
		rs=null;
		String sql = "SELECT M.M_NAME, M.M_BIRTH, P.P_NAME FROM"
				+ " MEMBER M INNER JOIN REGISTER R ON"
				+ " M.M_ID = R.RG_M_ID INNER JOIN PROGRAM P ON"
				+ " R.RG_P_CODE = P.P_CODE"
				+ " WHERE R.RG_T_ID=?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, tId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ml = new MemberList();
				ml.setM_name(rs.getNString("m_name"));
				ml.setM_birth(rs.getNString("m_birth"));
				ml.setP_name(rs.getNString("p_name"));
				list.add(ml);
			}
		} catch (SQLException e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		return list; 
	}
	public ArrayList<MemberList> mFoodList(String tId) {
		ArrayList<MemberList> list = new ArrayList<MemberList>();
		MemberList ml = null;
		String sql="SELECT M.M_NAME, P.P_NAME FROM "
				+ "MEMBER M INNER JOIN REGISTER R ON "
				+ "M.M_ID=R.RG_M_ID INNER JOIN PROGRAM P ON "
				+ "R.RG_P_CODE=P.P_CODE "
				+ "WHERE R.RG_T_ID=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, tId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ml = new MemberList();
				ml.setM_name(rs.getNString("m_name"));
				ml.setP_name(rs.getNString("p_name"));
				list.add(ml);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<MemberList> tExerciseMList(String tId) {
		MemberList ml = null;
		ArrayList<MemberList> list = new ArrayList<>();
		String sql = "SELECT M.M_NAME,P.P_NAME FROM "
				+ "MEMBER M INNER JOIN REGISTER R ON "
				+ "M.M_ID=R.RG_M_ID INNER JOIN PROGRAM P ON "
				+ "R.RG_P_CODE=P.P_CODE "
				+ "WHERE R.RG_T_ID=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, tId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ml = new MemberList();
				ml.setM_name(rs.getNString("m_name"));
				ml.setP_name(rs.getNString("p_name"));
				list.add(ml);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Member getInfo(String mId) {
		//내 정보 가져오기
		System.out.println("정보가져오기");
		String sql="SELECT * FROM MEMBER WHERE M_ID=?";
		Member mb = null;
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				mb = new Member();
				mb.setmId(rs.getNString("M_ID"));
				mb.setmPw(rs.getNString("M_PW"));
				System.out.println(rs.getNString("M_PW"));
				mb.setmName(rs.getNString("M_NAME"));
				System.out.println(rs.getNString("M_NAME"));
				mb.setmBheight(rs.getInt("M_BHEIGHT"));
				System.out.println(rs.getInt("M_BHEIGHT"));
				mb.setmBweight(rs.getInt("M_BWEIGHT"));
				mb.setmBirth(rs.getNString("M_BIRTH"));
				mb.setmDomicile(rs.getNString("M_DOMICILE"));
				mb.setmEmail(rs.getNString("M_EMAIL"));
				mb.setmGender(rs.getNString("M_GENDER"));
				mb.setmWish(rs.getNString("M_WISH"));
				return mb;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;

	}
	public Member updateMemberInfo(Member mb) {
		//내 정보 수정
		String sql="UPDATE MEMBER SET M_PW=?, M_NAME=?, M_GENDER=?, M_BIRTH=?, M_EMAIL=?, "
				+ "M_BHEIGHT=?, M_BWEIGHT=?, M_WISH=?, M_DOMICILE=? WHERE M_ID=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mb.getmPw());
			pstmt.setNString(2, mb.getmName());
			pstmt.setNString(3, mb.getmGender());
			pstmt.setNString(4, mb.getmBirth());
			pstmt.setNString(5, mb.getmEmail());
			pstmt.setInt(6, mb.getmBheight());
			pstmt.setInt(7, mb.getmBweight());
			pstmt.setNString(8, mb.getmWish());
			pstmt.setNString(9, mb.getmDomicile());
			pstmt.setNString(10, mb.getmId());
			int cnt=pstmt.executeUpdate();
			System.out.println(cnt);
			if(cnt>0){
				System.out.println("업데이트 성공");
			}
		} catch (SQLException e) {
			System.out.println("업데이트실패");
			e.printStackTrace();
		}
		return mb;
	}
	public ArrayList<Mexercise> exerciselist(String date1, String date2) {
		String sql = "SELECT * FROM M_EXERCISE WHERE ME_DATE BETWEEN ? AND ? ORDER BY ME_DATE";
		Mexercise me = null;
		ArrayList<Mexercise> list=new ArrayList<Mexercise>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs=pstmt.executeQuery();
			while(rs.next()){
				me = new Mexercise();
				String date = rs.getNString("ME_DATE").substring(0, 10);
				me.setDate(date);
				me.setE_name(rs.getNString("ME_E_NAME"));
				me.setE_time(rs.getInt("ME_E_TIME"));
				me.setE_cal(rs.getDouble("ME_E_CAL"));
				list.add(me);
			}
			System.out.println("검색성공");
			return list;
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}            
		return null;
	}
	public void exInsert(String m_id, String edate, String exname, int time) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_M_ID=?";
		String sql2 = "SELECT * FROM EXERCISE WHERE E_NAME=?";
		String sql3 = "INSERT INTO M_EXERCISE VALUES(ME_SEQ.NEXTVAL,?,?,?,?,?,?)";
		Register rg = null;
		Exercise ex = null;
		String t_id=null;
		double e_cal=0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, m_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				t_id=rs.getNString("RG_T_ID");
				System.out.println("t_id : "+t_id);
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setNString(1, exname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				e_cal=rs.getDouble("E_CAL");
				System.out.println("e_cal : "+e_cal);
				
			}
			double Vcal = time*e_cal;
			pstmt = con.prepareStatement(sql3);
			pstmt.setNString(1, t_id);
			pstmt.setNString(2, m_id);
			pstmt.setNString(3, edate);
			pstmt.setNString(4, exname);
			pstmt.setInt(5, time);
			pstmt.setDouble(6, Vcal);
			pstmt.executeUpdate();
			System.out.println("삽입성공");
		} catch (SQLException e) {
			System.out.println("삽입실패");
			e.printStackTrace();
		}
	}
	/*public boolean insertBody(Body bd) {
		String sql="INSERT INTO B_BODY VALUES (B_BODY_SEQ.NEXTVAL,?,?,?,?,?)";
		int result=0;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, bd.getB_m_id());
			pstmt.setNString(2, bd.getB_t_id());
			pstmt.setNString(3, bd.getB_date());
			pstmt.setInt(4, bd.getB_weight());
			pstmt.setInt(5, bd.getB_height());
			result=pstmt.executeUpdate();
		}catch (SQLException e){
			System.out.println("body insert 실패");
			e.printStackTrace();
		}return boolConvert(result);

	}*/
	public ArrayList<Member> memberSelect() {
		ArrayList<Member> list = new ArrayList<Member>();
		Member mb = null;
		String sql="SELECT * FROM MEMBER";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				mb=new Member();
				mb.setmId(rs.getNString("m_id"));
				mb.setmName(rs.getNString("m_name"));
				mb.setmGender(rs.getNString("m_gender"));
				String birth1=rs.getNString("m_birth").substring(0, 4);
				String birth2=rs.getNString("m_birth").substring(5, 7);
				String birth3=rs.getNString("m_birth").substring(8, 10);
				mb.setMbirth1(birth1);
				mb.setMbirth2(birth2);
				mb.setMbirth3(birth3);
				mb.setmDomicile(rs.getNString("m_domicile"));
				mb.setmEmail(rs.getNString("m_email"));
				list.add(mb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Register t_nameSelect(String m_id) {
		String sql = "SELECT RG_T_ID FROM REGISTER WHERE RG_M_ID=?";
		Register rg;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				rg=new Register();
				rg.setRg_t_id(rs.getNString("RG_T_ID"));
				return rg;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	public int delete(String m_id) {
		String sql="DELETE FROM MEMBER WHERE M_ID=?";
		int count = 0;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			count=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public ArrayList<Food> fList() {
		String sql = "SELECT DISTINCT F_KIND FROM FOOD";
		Food fd = null;
		ArrayList<Food> list=new ArrayList<Food>();
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				fd=new Food();
				fd.setF_kind(rs.getNString("F_KIND"));
				list.add(fd);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Food> fList(String fkind) {
		String sql = "SELECT * FROM FOOD WHERE F_KIND=?";
		Food fd = null;
		ArrayList<Food> list=new ArrayList<Food>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, fkind);
			rs=pstmt.executeQuery();
			while(rs.next()){
				fd = new Food();
				fd.setF_name(rs.getNString("F_NAME"));
				list.add(fd);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		}
		return null;
	}
	public void foodInsert(String m_id, String fdate, String fday, String fkind, String fname, int famount) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_M_ID=?";
		String sql2 = "SELECT * FROM FOOD WHERE F_NAME=?";
		String sql3 = "INSERT INTO M_FOOD VALUES(MF_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
		Register rg = null;
		String t_id=null;
		int f_cal=0;
		double f_gram=0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, m_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				t_id=rs.getNString("RG_T_ID");
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setNString(1, fname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				f_cal=rs.getInt("F_CAL");
				f_gram=rs.getInt("F_GRAM");
			}
			double Vgram = (double)(famount/f_gram);
			System.out.println("gram:"+Vgram);
			double Vcal = Vgram*f_cal;
			pstmt = con.prepareStatement(sql3);
			pstmt.setNString(1, t_id);
			pstmt.setNString(2, m_id);
			pstmt.setNString(3, fdate);
			pstmt.setNString(4, fday);
			pstmt.setNString(5, fkind);
			pstmt.setNString(6, fname);
			pstmt.setInt(7, famount);
			pstmt.setDouble(8, Vcal);
			pstmt.executeUpdate();
			System.out.println("삽입성공");
		} catch (SQLException e) {
			System.out.println("삽입실패");
			e.printStackTrace();
		}
	}
	public ArrayList<Mfood> foodlist(String date1, String date2) {
		String sql = "SELECT * FROM M_FOOD WHERE MF_DATE BETWEEN ? AND ? ORDER BY MF_DATE";
		Mfood mf = null;
		ArrayList<Mfood> list=new ArrayList<Mfood>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs=pstmt.executeQuery();
			while(rs.next()){
				mf = new Mfood();
				String date = rs.getNString("MF_DATE").substring(0, 10);
				mf.setMf_date(date);
				mf.setMf_time(rs.getNString("MF_TIME"));
				mf.setMf_f_kind(rs.getNString("MF_F_KIND"));
				mf.setMf_f_name(rs.getNString("MF_F_NAME"));
				mf.setMf_f_amount(rs.getInt("MF_F_AMOUNT"));
				mf.setMf_f_cal(rs.getDouble("MF_F_CAL"));
				list.add(mf);
			}
			System.out.println("검색성공");
			return list;
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}            
		return null;
	}
	public ArrayList<Exercise> exList() {
		String sql = "SELECT * FROM EXERCISE";
		Exercise ex = null;
		ArrayList<Exercise> list=new ArrayList<Exercise>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ex = new Exercise();
				ex.setE_name(rs.getNString("E_NAME"));
				list.add(ex);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		}
		return null;
	}
	public void Physial(String m_id, String pdate, double pheight, double pweight) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_M_ID=?";
		String sql2 = "INSERT INTO B_BODY VALUES(B_BODY_SEQ.NEXTVAL,?,?,?,?,?)";
		String t_id=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, m_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				t_id=rs.getNString("RG_T_ID");
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setNString(1, m_id);
			pstmt.setNString(2, t_id);
			pstmt.setNString(3, pdate);
			pstmt.setDouble(4, pweight);
			pstmt.setDouble(5, pheight);
			pstmt.executeUpdate();
			System.out.println("신장/체중 등록성공");
		} catch (SQLException e) {
			System.out.println("신장/체중 등록실패");
			e.printStackTrace();
		}
	}
	public ArrayList<Mfood> mgraph(String m_id) {
		String sql = "SELECT * FROM M_FOOD WHERE MF_M_ID=?";
		Mfood fd = null;
		ArrayList<Mfood> list = new ArrayList<Mfood>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				fd = new Mfood();
				fd.setMf_f_name(rs.getNString("MF_F_NAME"));
				fd.setMf_f_cal(rs.getDouble("MF_F_CAL"));
				list.add(fd);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Mexercise> mdgraph(String m_id) {
		String sql = "SELECT * FROM M_EXERCISE WHERE ME_M_ID=?";
		Mexercise me = null;
		ArrayList<Mexercise> list = new ArrayList<Mexercise>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				me = new Mexercise();
				me.setE_name(rs.getNString("ME_E_NAME"));
				me.setE_cal(rs.getDouble("ME_E_CAL"));
				list.add(me);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Mphysial> mphgraph(String m_id) {
		String sql = "SELECT * FROM B_BODY WHERE B_M_ID=?";
		Mphysial ph = null;
		ArrayList<Mphysial> list = new ArrayList<Mphysial>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, m_id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ph = new Mphysial();
				String date = rs.getNString("B_DATE").substring(0, 10);
				ph.setB_date(date);
				ph.setB_weight(rs.getDouble("B_WEIGHT"));
				list.add(ph);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
