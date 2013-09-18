package boardtest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class userDao {
	
	public String view(int page){
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();

		int cnt = 0;
		sb.append("{\"list\":[");
		
		try {
			c = DBConnector.getConnection();
			ps = c.prepareStatement("SELECT * FROM board1 ORDER BY rowid DESC limit ?,10");
			ps.setInt(1, 3*(page-1));
			rs = ps.executeQuery();
			
			while(rs.next()){
				sb.append("{\"num\":\"").append(rs.getInt("rowid"))
				.append("\",\"title\":\"").append(rs.getString("title")).append("\"},");
				cnt++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cnt > 0){
			sb.setLength(sb.length() - 1);
		}
		
		sb.append("]}");
		
		return sb.toString();
	}
	
	public String detail(int num){
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		try {
			c = DBConnector.getConnection();
			ps = c.prepareStatement("SELECT * FROM board1 WHERE rowid = ?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			while(rs.next()){
				sb.append("{\"num\":\"").append(rs.getInt("rowid")).append("\",")
				.append("\"title\":\"").append(rs.getString("title")).append("\",")
				.append("\"text\":\"").append(rs.getString("naeyong")).append("\",")
				.append("\"time_reg\":\"").append(rs.getString("time_reg")).append("\"}");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
		
	public boolean login(String id, String passwd){
		
		boolean result = false;
		int count = 0;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBConnector.getConnection();
			ps = c.prepareStatement("SELECT count(*) FROM users WHERE id = ? and passwd= ?");
			ps.setString(1, id);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count == 1){
			result = true;
		}
		
		return result;
		
		
		
		
	}
	
public boolean register(String id, String passwd){
		
		int result = 0;
		boolean tf = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = DBConnector.getConnection();
			ps = c.prepareStatement("INSERT INTO users(id,passwd) VALUES(?,?)");
			ps.setString(1, id);
			ps.setString(2, passwd);
			result = ps.executeUpdate();
			
			// executeUpdate = INSERT, DELETE처럼 리턴되는 값이 없을때(성공 여부가 리턴됨), executeQuery = SELECT처럼 리턴값을 받아서 처리해야될때
			// executeUpdate : 이 함수는 int를 리턴하는데, 값은 적용된 row행수를 나타낸다.
			// INSERT 1row 성공하면 1이 리턴되고, 실패시 0리턴(항상)
			// 2row도 가능 "INSERT INTO users values(1,hello),(2,world);"
			
			if(result > 0){
				tf = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tf;
		
		
		
	}

public boolean delete(String id, String passwd){
	
	int result = 0;
	boolean tf = false;
	
	Connection c = null;
	PreparedStatement ps = null;
	
	try {
		c = DBConnector.getConnection();
		ps = c.prepareStatement("DELETE FROM users WHERE id = ? and passwd = ?");
		ps.setString(1, id);
		ps.setString(2, passwd);
		result = ps.executeUpdate();
		
		// executeUpdate = INSERT, DELETE처럼 리턴되는 값이 없을때(성공 여부가 리턴됨), executeQuery = SELECT처럼 리턴값을 받아서 처리해야될때
		// executeUpdate : 이 함수는 int를 리턴하는데, 값은 적용된 row행수를 나타낸다.
		// INSERT 1row 성공하면 1이 리턴되고, 실패시 0리턴(항상)
		// 2row도 가능 "INSERT INTO users values(1,hello),(2,world);"
		
		if(result > 0){
			tf = true;
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return tf;
	
	
	
}
}
