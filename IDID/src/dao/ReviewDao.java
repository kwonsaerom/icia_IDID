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

import bean.Review;

public class ReviewDao {
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	DataSource ds; //추가
	ResultSetMetaData rsmd;

	public ReviewDao(){
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
	
	public ArrayList<Review> reviewList(int page, int limit){
		ArrayList<Review> list = new ArrayList<>();
		Review review = null;
		String sql = "SELECT * FROM (SELECT rownum rnum, r_code, r_m_id, r_m_name, r_title, r_substance, r_filename, r_group, r_date, r_count FROM (SELECT * FROM review order by r_code DESC)) WHERE rnum>=? and rnum<=?";
		int startrow = (page-1)*10+1;
		int endrow = startrow+limit-1;
		
		try { 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()){
				review = new Review();
				review.setR_m_id(rs.getNString("r_m_id"));
				review.setR_m_name(rs.getNString("r_m_name"));
				review.setR_title(rs.getNString("R_TITLE"));
				review.setR_substance(rs.getNString("R_SUBSTANCE"));
				review.setR_fileName(rs.getNString("R_FILENAME"));
				review.setR_date(rs.getNString("r_date"));
				review.setR_group(rs.getInt("r_group"));
				review.setR_code(rs.getInt("r_code"));
				review.setR_count(rs.getInt("r_count"));
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<Review> selectDetail(int code, String reTitle) {
		ArrayList<Review> list = new ArrayList<>();
		Review review = null;
		String sql = "SELECT * FROM review WHERE r_code=? AND r_title=?";
		try {
			System.out.println("code="+code);
			/*pstmt = con.prepareStatement("UPDATE review set r_count = r_count+1 WHERE r_code = "+code);
			pstmt.executeUpdate();*/
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setNString(2, reTitle);
			rs = pstmt.executeQuery();
			if(rs.next()){
				review = new Review();
				review.setR_code(rs.getInt("r_code"));
				review.setR_title(rs.getNString("r_title"));
				review.setR_m_id(rs.getNString("r_m_id"));
				review.setR_m_name(rs.getNString("r_m_name"));
				review.setR_date(rs.getNString("r_date"));
				review.setR_count(rs.getInt("r_count"));
				review.setR_substance(rs.getNString("r_substance"));
				review.setR_fileName(rs.getNString("r_filename"));
				list.add(review);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return list;
	}
	public boolean insertWrite(Review review) {
		int num = 0;//글번호
		String sql = "";
		int result = 0;//insert 추가 성공 flag
		
		try {
			pstmt = con.prepareStatement("SELECT max(r_code) FROM review");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
			}else{
				num = 1;
			}
			sql = "INSERT INTO review(r_code, r_m_id, r_m_name, r_title, r_substance, r_fileName, r_group, r_count, r_date) "
					+ "VALUES(?,?,?,?,?,?,?,?,default)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setNString(2, review.getR_m_id());
			pstmt.setNString(3, "5");
			pstmt.setNString(4, review.getR_title());
			pstmt.setNString(5, review.getR_substance());
			pstmt.setNString(6, review.getR_fileName());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			result = pstmt.executeUpdate();
			if(result==0){
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//조회수 업데이트
	public void countUpdate(int code){
		String sql ="UPDATE review set r_count = r_count+1 WHERE r_code = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
			/*if(result==1){
				System.out.println("업데이트 성공");
			}else{
				System.out.println("업데이트 실패");
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Review getReview(int code){
		Review review = null;
		String sql = "SELECT * FROM review WHERE r_code = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				review = new Review();
				review.setR_code(rs.getInt("r_code"));
				review.setR_m_id(rs.getNString("r_m_id"));
				review.setR_title(rs.getNString("r_title"));
				review.setR_fileName(rs.getNString("r_filename"));
				review.setR_substance(rs.getNString("r_substance"));
				review.setR_fileName(rs.getNString("r_filename"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return review;
	}
	public void mReviewModify(Review review) {
		/*String sql = "SELECT * FROM review WHERE r_code= "+code;*/
		String sql = "UPDATE review set r_substance=?, r_title=? ,r_filename=? WHERE r_code= "+review.getR_code();
		try {
			//pstmt = con.prepareStatement("SELECT * FROM review WHERE r_code= "+review.getR_code());
			//rs = pstmt.executeQuery();
			//if(rs.next()){
				pstmt = con.prepareStatement(sql);
				pstmt.setNString(1, review.getR_substance());
				pstmt.setNString(2, review.getR_title());
				pstmt.setNString(3, review.getR_fileName());
			//}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void mReviewDelete(int code) {
		String sql = "DELETE FROM review WHERE r_code= "+code;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	public int getListCount() {
		int x = 0;
		
		try {
			pstmt = con.prepareStatement("SELECT count(*) FROM review");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return x;
	}
}
