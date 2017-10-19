package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Reply;

public class ReplyDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public ReplyDao() {
		try {
			Context init = new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con=ds.getConnection();
			System.out.println("커넥션 풀 연결 성공");
		} catch (Exception e) {
			System.out.println("커넥션 풀 연결 실패");
			e.printStackTrace();
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
	public void insertReply(Reply reply) {
		int num = 0;
		String sql = "";
		try {
			pstmt = con.prepareStatement("SELECT max(rp_num) FROM reply");
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt(1)+1;
			}else{
				num = 1;
			}
			sql = "INSERT INTO reply(rp_num, rp_id, rp_date, rp_comment, rp_d_code) "
					+ "values(?,?,default,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setNString(2, reply.getRP_ID());
			pstmt.setNString(3, reply.getRP_COMMENT());
			pstmt.setInt(4, reply.getRP_D_CODE());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Reply> replySelect(int code) {
		Reply reply = null;
		ArrayList<Reply> list = new ArrayList<>();
		String sql = "SELECT rp_id, rp_comment, rp_date FROM reply WHERE rp_d_code= "+code;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				reply = new Reply();
				reply.setRP_ID(rs.getNString("rp_id"));
				reply.setRP_COMMENT(rs.getNString("rp_comment"));
				reply.setRP_DATE(rs.getNString("rp_date"));
				list.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
