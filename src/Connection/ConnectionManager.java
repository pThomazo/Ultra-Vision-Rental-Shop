package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	//we may change the DB
	public static final String URL = "jdbc:mysql://database-1.ckpglua8ft7e.eu-west-1.rds.amazonaws.com:3306/ultra_vision_rs?useTimezone=true&severTimezone=UTC";
	private Connection conn;
	
	//getting  connection from the database on AWS
	public Connection getConnection() throws SQLException{
		if(conn==null) {
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(URL,"admin", "CCT20201234!");
				//conn = DriverManager.getConnection(URL_Local,"root", "");
			}
			catch(SQLException e) {
				e.printStackTrace();
				
				//Here the program should fail
				
				throw e;
			}
			this.conn = conn;
		}
		
		//returning the connection status
		return conn;
	}

}
