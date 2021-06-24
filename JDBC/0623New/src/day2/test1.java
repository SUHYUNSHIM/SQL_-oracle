package day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test1 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		System.out.println("접속했습니다.");
	
	
		//이름이 s로 시작하는 사원 정보를 출력하시오
		//단, 이름과 입사일만 출력
		
		String sql1 = "select * from employees"
					+" where lower(first_name) like ?";
		PreparedStatement ps1 = con1.prepareStatement(sql1);
		
		Scanner scanner =new Scanner(System.in);
		String input = scanner.next();
		ps1.setString(1, input+"%");
		ResultSet rs1 = ps1.executeQuery(); 
		
		while(rs1.next()) {
			int id = rs1.getInt("employee_id");				
			String fname = rs1.getString("first_name");
			String h_date = rs1.getString("hire_date");
			String hire_date = h_date.substring(0,10);
			System.out.println(rs1.getRow()+"\t" +id+"\t" +fname +"\t"+hire_date);
		}
		con1.close();
		System.out.println("접속끝");
		
		
	}
}
