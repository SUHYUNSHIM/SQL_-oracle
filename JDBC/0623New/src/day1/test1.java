package day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		
	}

}