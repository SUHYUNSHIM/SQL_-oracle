package day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class test3 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		
		String sql2 = "select id, name, tel, d from TelTable"
				+" where name= ?";
		PreparedStatement ps2 = con1.prepareStatement(sql2);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 이름: ");
		String strName = sc.next();
		ps2.setString(1, strName);
		
		ResultSet rs2 = ps2.executeQuery();
		
		while(rs2.next()) {
			int id =rs2.getInt("id");
			String fname = rs2.getString("name");
			String tel = rs2.getString("tel");
			Date d = rs2.getDate("d");
			System.out.println(rs2.getRow()+id+"\t"+fname+"\t"+tel+"\t"+d);
		}
		con1.close();
		System.out.println("접속끝");
	}
	
}
