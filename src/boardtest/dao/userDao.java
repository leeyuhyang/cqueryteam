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
			
			// executeUpdate = INSERT, DELETEó�� ���ϵǴ� ���� ������(���� ���ΰ� ���ϵ�), executeQuery = SELECTó�� ���ϰ��� �޾Ƽ� ó���ؾߵɶ�
			// executeUpdate : �� �Լ��� int�� �����ϴµ�, ���� ����� row����� ��Ÿ����.
			// INSERT 1row �����ϸ� 1�� ���ϵǰ�, ���н� 0����(�׻�)
			// 2row�� ���� "INSERT INTO users values(1,hello),(2,world);"
			
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
		
		// executeUpdate = INSERT, DELETEó�� ���ϵǴ� ���� ������(���� ���ΰ� ���ϵ�), executeQuery = SELECTó�� ���ϰ��� �޾Ƽ� ó���ؾߵɶ�
		// executeUpdate : �� �Լ��� int�� �����ϴµ�, ���� ����� row����� ��Ÿ����.
		// INSERT 1row �����ϸ� 1�� ���ϵǰ�, ���н� 0����(�׻�)
		// 2row�� ���� "INSERT INTO users values(1,hello),(2,world);"
		
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
