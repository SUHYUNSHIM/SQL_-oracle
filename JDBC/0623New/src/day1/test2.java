package day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
		
		String sql2 ="select first_name 이름, salary 봉급  from employees"
				+" where salary >=?";
		PreparedStatement ps2 = con1.prepareStatement(sql2);
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("봉급 얼마 이상 다 나와?");
		
		int salary_get = sc1.nextInt();
		//이 숫자는 ?를 채우기 위해 받았다.
		
		ps2.setInt(1, salary_get);
	
		ResultSet rs2 = ps2.executeQuery();
		
		while(rs2.next()) {
			String fname = rs2.getString("이름");
			int salary = rs2.getInt("봉급");
			System.out.println(rs2.getRow()+"\t"+fname+"\t"+salary);			
		}
		con1.close();
		System.out.println("접속 끝");		
	}
}
