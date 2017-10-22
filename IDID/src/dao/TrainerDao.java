package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Member;
import bean.Mexercise;
import bean.Mfood;
import bean.Mphysial;
import bean.Register;
import bean.Texercise;
import bean.Tfood;
import bean.Trainer;

public class TrainerDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	ResultSetMetaData rsmd;
	public TrainerDao() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();
			System.out.println("커넥션 연결 성공");
		} catch (Exception e) {
			System.out.println("커넥션 연결 실패");
			e.printStackTrace();
		}	
	}
	public void close(){
		try {
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Trainer detailTrainer(String id){
		ArrayList<Trainer> list = new ArrayList<Trainer>();
		String sql = "SELECT * FROM trainer WHERE T_ID=?";
		Trainer tn = null;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//2017
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				tn = new Trainer();
				String birth = rs.getNString("T_BIRTH");
				String trainerCal = birth.substring(0, 4); //트레이너 생년
				int age = (year+1) - Integer.parseInt(trainerCal);
				//System.out.println("현재 나이:"+(age));
				tn.settName(rs.getNString("T_NAME"));
				String age2=String.valueOf(age);
				tn.settBirth(age2);
				String birth1 = birth.substring(2, 4);
				String birth2 = birth.substring(5, 7);
				String birth3 = birth.substring(8, 10);
				tn.settGender(rs.getNString("T_GENDER"));
				tn.settEmail(rs.getNString("T_EMAIL"));
				tn.settProfilephoto(rs.getNString("T_PROFILEPHOTO"));
				tn.settBirth1(birth1);
				tn.settBirth2(birth2);
				tn.settBirth3(birth3);

				System.out.println(id);
				System.out.println(rs.getNString("T_NAME"));
				System.out.println(rs.getNString("T_EMAIL"));
				System.out.println(rs.getNString("T_GENDER"));
				System.out.println(rs.getNString("T_BIRTH"));
				System.out.println(rs.getNString("T_PROFILEPHOTO"));
				return tn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Trainer tSelect(String id){
		Trainer trainer = null;
		String sql = "SELECT * FROM trainer WHERE t_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				trainer = new Trainer();
				trainer.settName(rs.getNString("t_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return trainer;
	}

	public ArrayList<Trainer> TrainerList() {
		ArrayList<Trainer> list = new ArrayList<Trainer>();
		String sql = "SELECT T_ID,T_NAME,T_PROFILEPHOTO FROM trainer";
		Trainer tn = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				tn = new Trainer();
				//System.out.println("사진:"+rs.getNString("T_PROFILEPHOTO"));
				//tn.settId(rs.getNString("T_ID"));
				tn.settId(rs.getNString("T_ID"));
				tn.settProfilephoto(rs.getNString("T_PROFILEPHOTO"));
				tn.settName(rs.getNString("T_NAME"));
				list.add(tn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean isId(String tId) {
		String sql="SELECT COUNT(*) FROM TRAINER "+"WHERE T_ID=?";
		int result=0;
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, tId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("아이디 중복 확인 실패");
			e.printStackTrace();
		}
		return boolConvert(result);
	}
	private boolean boolConvert(int result) {
		return (result!=0)?true:false;
	}
	//트레이너 회원가입 
	public boolean insertTrainer(Trainer tn) {
		String sql="INSERT INTO TRAINER VALUES (?,?,?,?,?,?,?,?)";
		int result=0;
		try {
			System.out.println("회원가입 성공");
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, tn.gettId());
			pstmt.setNString(2, tn.gettPw());
			pstmt.setNString(3, tn.gettName());
			pstmt.setNString(4, tn.gettGender());
			pstmt.setNString(5, tn.gettBirth());
			pstmt.setNString(6, tn.gettDomicile());
			pstmt.setNString(7, tn.gettEmail());
			pstmt.setNString(8, tn.gettProfilephoto());
			result=pstmt.executeUpdate();
		}catch (SQLException e){
			System.out.println("trainer insert 실패");
			e.printStackTrace();
		}return boolConvert(result);
	}

	public Trainer TSearchId(String name, String email) {
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
	public Trainer TSearchPw(String id, String name, String email) {
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
	//아이디 비밀번호 확인 후 탈퇴
	public int delete(String id, String pw) {
		String sql="DELETE FROM TRAINER WHERE T_ID=? AND T_PW=?";
		int count = 0;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, pw);
			count=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public Trainer idSelect(String t_name) {
		String sql = "SELECT T_ID FROM TRAINER WHERE T_NAME=?";
		Trainer tn=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, t_name);
			rs=pstmt.executeQuery();
			if(rs.next()){
				tn=new Trainer();
				tn.settId(rs.getNString("T_ID"));
				return tn;
			}
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}
		return null;
	}

	//트레이너 내정보 가져오기
	public Trainer getInfo(String tId) {
		String sql="SELECT * FROM TRAINER WHERE T_ID=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, tId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				Trainer tr=new Trainer();
				tr.settId(rs.getNString("T_ID"));
				tr.settPw(rs.getNString("T_PW"));
				tr.settName(rs.getNString("T_NAME"));
				tr.settGender(rs.getNString("T_GENDER"));
				tr.settBirth(rs.getNString("T_BIRTH"));
				tr.settDomicile(rs.getNString("T_DOMICILE"));
				tr.settEmail(rs.getNString("T_EMAIL"));
				tr.settProfilephoto(rs.getNString("T_PROFILEPHOTO"));
				return tr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public Trainer updateTrainerInfo(Trainer tr) {
		String sql="UPDATE TRAINER SET T_PW=?, T_NAME=?, T_GENDER=?, T_BIRTH=?, T_EMAIL=?, "
				+ " T_DOMICILE=?, T_PROFILEPHOTO=?  WHERE T_ID=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, tr.gettPw());
			pstmt.setNString(2, tr.gettName());
			pstmt.setNString(3, tr.gettGender());
			pstmt.setNString(4, tr.gettBirth());
			pstmt.setNString(5, tr.gettEmail());
			pstmt.setNString(6, tr.gettDomicile());
			pstmt.setNString(7, tr.gettProfilephoto());
			pstmt.setNString(8, tr.gettId());
			int cnt=pstmt.executeUpdate();
			System.out.println(cnt);
			if(cnt>0){
				System.out.println("업데이트 성공");
			}
		} catch (SQLException e) {
			System.out.println("업데이트실패");
			e.printStackTrace();
		}
		return tr;
	}
	public ArrayList<Trainer> select() {
		String sql="SELECT DISTINCT P_T_NAME FROM PROGRAM";
		pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Trainer> tList=new ArrayList<Trainer>();
			Trainer tn=null;
			rsmd = rs.getMetaData();
			while(rs.next()){
				tn=new Trainer();
				tn.settName(rs.getNString("P_T_NAME"));
				tList.add(tn);
			}
			return tList;

		} catch (SQLException e) {
			System.out.println("sql실패");
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Trainer> TrainerSelect() {
		ArrayList<Trainer> list = new ArrayList<Trainer>();
		Trainer tn = null;
		String sql="SELECT * FROM TRAINER";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				tn=new Trainer();
				tn.settId(rs.getNString("t_id"));
				tn.settName(rs.getNString("t_name"));
				tn.settGender(rs.getNString("t_gender"));
				String birth1=rs.getNString("t_birth").substring(0, 4);
				String birth2=rs.getNString("t_birth").substring(5, 7);
				String birth3=rs.getNString("t_birth").substring(8, 10);
				tn.settBirth1(birth1);
				tn.settBirth2(birth2);
				tn.settBirth3(birth3);
				tn.settDomicile(rs.getNString("t_domicile"));
				tn.settEmail(rs.getNString("t_email"));
				list.add(tn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int delete(String t_id) {
		String sql="DELETE FROM TRAINER WHERE T_ID=?";
		int count = 0;
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, t_id);
			count=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public ArrayList<Tfood> foodlist(String date1, String date2) {
		String sql = "SELECT * FROM T_FOOD WHERE TF_DATE BETWEEN ? AND ? ORDER BY TF_DATE";
		Tfood tf = null;
		ArrayList<Tfood> list=new ArrayList<Tfood>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs=pstmt.executeQuery();
			while(rs.next()){
				tf = new Tfood();
				String date = rs.getNString("TF_DATE").substring(0, 10);
				tf.setTf_date(date);
				tf.setTf_time(rs.getNString("TF_TIME"));
				tf.setTf_f_kind(rs.getNString("TF_F_KIND"));
				tf.setTf_f_name(rs.getNString("TF_F_NAME"));
				tf.setTf_f_amount(rs.getInt("TF_F_AMOUNT"));
				tf.setTf_f_cal(rs.getDouble("TF_F_CAL"));
				list.add(tf);
			}
			System.out.println("검색성공");
			return list;
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}            
		return null;
	}
	public ArrayList<Texercise> exerciselist(String date1, String date2) {
		String sql = "SELECT * FROM T_EXERCISE WHERE TE_DATE BETWEEN ? AND ? ORDER BY TE_DATE";
		bean.Texercise te = null;
		ArrayList<Texercise> list=new ArrayList<Texercise>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			rs=pstmt.executeQuery();
			while(rs.next()){
				te = new bean.Texercise();
				String date = rs.getNString("TE_DATE").substring(0, 10);
				te.setTe_date(date);
				te.setTe_e_name(rs.getNString("TE_E_NAME"));
				te.setTe_e_time(rs.getInt("TE_E_TIME"));
				te.setTe_e_cal(rs.getDouble("TE_E_CAL"));
				list.add(te);
			}
			System.out.println("검색성공");
			return list;
		} catch (SQLException e) {
			System.out.println("검색실패");
			e.printStackTrace();
		}            
		return null;
	}
	public void foodInsert(String t_id, String fdate, String fday, String fkind, String fname, int famount) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_T_ID=?";
		String sql2 = "SELECT * FROM FOOD WHERE F_NAME=?";
		String sql3 = "INSERT INTO T_FOOD VALUES(TF_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
		Register rg = null;
		String m_id=null;
		double f_cal=0;
		double f_gram=0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, t_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m_id=rs.getNString("RG_M_ID");
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setNString(1, fname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				f_cal=rs.getDouble("F_CAL");
				f_gram=rs.getDouble("F_GRAM");
			}
			double Vgram = (double)(famount/f_gram);
			System.out.println("Vgram:"+Vgram);
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
	public void exInsert(String t_id, String edate, String exname, int time) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_T_ID=?";
		String sql2 = "SELECT * FROM EXERCISE WHERE E_NAME=?";
		String sql3 = "INSERT INTO T_EXERCISE VALUES(TE_SEQ.NEXTVAL,?,?,?,?,?,?)";
		String m_id=null;
		double e_cal=0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, t_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m_id=rs.getNString("RG_M_ID");
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setNString(1, exname);
			rs = pstmt.executeQuery();
			if(rs.next()){
				e_cal=rs.getDouble("E_CAL");
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
	public ArrayList<Mfood> tmgraph(String t_id) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_T_ID=?";
		String sql2 = "SELECT * FROM M_FOOD WHERE MF_M_ID=?";
		Mfood fd = null;
		String m_id=null;
		ArrayList<Mfood> list = new ArrayList<Mfood>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, t_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				m_id = rs.getNString("RG_M_ID");
			}
			pstmt = con.prepareStatement(sql2);
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
	public ArrayList<Mexercise> tmdgraph(String t_id) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_T_ID=?";
		String sql2 = "SELECT * FROM M_EXERCISE WHERE ME_M_ID=?";
		Mexercise me = null;
		String m_id=null;
		ArrayList<Mexercise> list = new ArrayList<Mexercise>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, t_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				m_id = rs.getNString("RG_M_ID");
			}
			pstmt = con.prepareStatement(sql2);
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
	public ArrayList<Mphysial> tmphgraph(String t_id) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_T_ID=?";
		String sql2 = "SELECT * FROM B_BODY WHERE B_M_ID=?";
		Mphysial ph = null;
		String m_id = null;
		ArrayList<Mphysial> list = new ArrayList<Mphysial>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, t_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				m_id = rs.getNString("RG_M_ID");
			}
			pstmt = con.prepareStatement(sql2);
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
	public ArrayList<Member> DetailMember(String t_id) {
		String sql1 = "SELECT * FROM REGISTER WHERE RG_T_ID=?";
		String sql2 = "SELECT * FROM MEMBER WHERE M_ID=?";
		String m_id=null;
		ArrayList<Member> list = new ArrayList<Member>();
		Member mb = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, t_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m_id=rs.getNString("RG_M_ID");
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setNString(1, m_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				mb = new Member();
				String date = rs.getNString("M_BIRTH").substring(0, 10);
				mb.setmName(rs.getNString("M_NAME"));
				mb.setmGender(rs.getNString("M_GENDER"));
				mb.setmBirth(date);
				mb.setmDomicile(rs.getNString("M_DOMICILE"));
				mb.setmEmail(rs.getNString("M_EMAIL"));
				mb.setmBheight(rs.getInt("M_BHEIGHT"));
				mb.setmBweight(rs.getInt("M_BWEIGHT"));
				mb.setmWish(rs.getNString("M_WISH"));
				list.add(mb);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
