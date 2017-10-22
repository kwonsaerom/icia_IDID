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

import bean.Member;
import bean.Review;
import bean.Trainer;

public class AdminDao {

   Connection con;
   ResultSet rs;
   PreparedStatement pstmt;
   DataSource ds; //추가
   ResultSetMetaData rsmd;

   public AdminDao(){
      try{
         Context init=new InitialContext();
         ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
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
   public boolean isAdmin(String a_id, String a_pw) {
      String sql = "SELECT * FROM ADMIN WHERE A_ID=?";
      boolean result=false;
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setNString(1, a_id);
         rs = pstmt.executeQuery();
         if(rs.next()){
            if(rs.getNString("a_pw").equals(a_pw)){
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


   public ArrayList<Review> ReviewSelect() {
      ArrayList<Review> list = new ArrayList<Review>();
      Review rv = null;
      String sql="SELECT * FROM REVIEW";
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while(rs.next()){
            rv=new Review();
            rv.setR_code(rs.getInt("r_code"));
            rv.setR_m_id(rs.getNString("r_m_id"));
            rv.setR_m_name(rs.getNString("r_m_name"));
            rv.setR_title(rs.getNString("r_title"));
            list.add(rv);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return list;
   }


   public int delete(int r_code) {
      String sql="DELETE FROM REVIEW WHERE R_CODE=?";
      int count = 0;
      try {
         con = ds.getConnection();
         pstmt=con.prepareStatement(sql);
         pstmt.setInt(1, r_code);
         count=pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return count;
   }

   public Review ReviewDetail(int r_code) {
      String sql = "SELECT * FROM REVIEW WHERE R_CODE= "+r_code;
      Review rv = null;      
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if(rs.next()){
            rv=new Review();
            rv.setR_m_id(rs.getNString("r_m_id"));
            rv.setR_title(rs.getNString("r_title"));
            rv.setR_substance(rs.getNString("r_substance"));
            return rv;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }return null;

   }
   
}