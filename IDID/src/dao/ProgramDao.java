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

import bean.Program;
import bean.Register;

public class ProgramDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	ResultSetMetaData rsmd;

	public ProgramDao() {
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
	
	
	public void pInsert(Program program) {
		String sql = "INSERT INTO PROGRAM VALUES(PROGRAM_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, program.getP_name());
			pstmt.setNString(2, program.getP_t_id());
			pstmt.setNString(3, program.getP_t_name());
			pstmt.setNString(4, program.getP_info());
			pstmt.setLong(5, program.getP_price());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("프로그램insert 실패");
			e.printStackTrace();
		}
		
	}
	public ArrayList<Program> select(String t_name) {
        String sql="SELECT P_NAME FROM PROGRAM WHERE P_T_NAME=?";
         pstmt = null;
         ResultSet rs = null;
         try {
            pstmt = con.prepareStatement(sql);
            pstmt.setNString(1, t_name);
            rs = pstmt.executeQuery();
            ArrayList<Program> pList=new ArrayList<Program>();
            Program pg=null;
            rsmd = rs.getMetaData();
            while(rs.next()){
               pg=new Program();
               pg.setP_name(rs.getNString("P_NAME"));
               pList.add(pg);
               System.out.println("프로그램 찾기 성공");
            }
            return pList;
         } catch (SQLException e) {
            System.out.println("sql실패");
            e.printStackTrace();
         }
         return null;
      }
	   
	 public Program p_t_codeSelect(String p_t_name) {
         String sql = "SELECT P_CODE FROM PROGRAM WHERE P_NAME=?";
         Program pg=null;
         try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setNString(1, p_t_name);
            rs=pstmt.executeQuery();
            if(rs.next()){
               pg=new Program();
               pg.setP_code(rs.getInt("P_CODE"));
               return pg;
            }
         } catch (SQLException e) {
            System.out.println("P코드 검색실패");
            e.printStackTrace();
         }
         return null;
      }

	   
	   
	   public boolean insertRegister(Register rg) {
	      String sql="INSERT INTO REGISTER VALUES(?,?,?,?,?)";
	      int result=0;
	      try {
	         con=ds.getConnection();
	         pstmt=con.prepareStatement(sql);
	         pstmt.setNString(1, rg.getRg_m_id());
	         pstmt.setNString(2, rg.getRg_t_id());
	         pstmt.setNString(3, rg.getRg_m_name());
	         pstmt.setNString(4, rg.getRg_t_name());
	         pstmt.setInt(5, rg.getRg_p_code());
	         result=pstmt.executeUpdate();
	      }catch (SQLException e){
	         System.out.println("trainer insert 실패");
	         e.printStackTrace();
	      }return boolConvert(result);
	      
	   }
	   
	   private boolean boolConvert(int result) {
	      return (result!=0)?true:false;
	   }

}
