package boardtest.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.tomcat.dbcp.dbcp.datasources.SharedPoolDataSource;

public class DBConnector {
	
	private static DataSource ds = null;
	
	static{
		DriverAdapterCPDS cpds = new DriverAdapterCPDS();
		
		try {
			cpds.setDriver("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		cpds.setUrl("jdbc:mysql://localhost:3306/newboard");
		cpds.setUser("root");
		cpds.setPassword("1234");
		
		SharedPoolDataSource tds = new SharedPoolDataSource();
		tds.setConnectionPoolDataSource(cpds);
		tds.setMaxActive(10);
		tds.setMaxWait(50);
		
		ds = tds;
		
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
		
	}

}
