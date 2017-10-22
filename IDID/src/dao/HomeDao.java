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
import bean.Trainer;

public class HomeDao {
   public  HomeDao() {
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
   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;
   DataSource ds;
   ResultSetMetaData rsmd;
   Program program;
   public ArrayList<Program> getProgramInfo() {
      ArrayList<Program> pList = new ArrayList<Program>();
      String sql="SELECT * FROM PROGRAM";
      try{
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while(rs.next()){
            Program program=new Program();
            //System.out.println("사진:"+rs.getNString("T_PROFILEPHOTO"));
            //tn.settId(rs.getNString("T_ID"));
            program.setP_code(rs.getInt("P_CODE"));
            program.setP_name(rs.getNString("P_NAME"));
            program.setP_info(rs.getNString("P_INFO"));
            program.setP_price(rs.getInt("P_PRICE"));
            program.setP_t_id(rs.getNString("P_T_ID"));
            program.setP_t_name(rs.getNString("P_T_NAME"));
            pList.add(program);
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      return pList;
      
   }
}