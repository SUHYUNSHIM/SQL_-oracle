package day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class test2 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		System.out.println("전송했다고 전해라");
		
		//급여가 15000이상인 사원
		//또는 매니저 아이디가 150이상인 경우
		//단, 매니저 아이디가 null 제외
		//단, 입사일이 7로 끝나는 사원만
		String sql1 = "select first_name 이름, salary 봉급,"
				+"manager_id 매니저아이디, hire_date 입사일"
				+" from employees"
				+ " where (salary >= ? or manager_id >= ?)"
				+" and manager_id is not null"
				+ " and hire_date like ?";
		PreparedStatement pst1 = con1.prepareStatement(sql1);
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("봉급 얼마이상 다나와? ");
		int salary = scanner.nextInt();
				
		System.out.println("매니저 아이디 얼마 이상?");
		int manager_id = scanner.nextInt();
		
		System.out.println("입사일 언제 다나와?");
		int ip_nal = scanner.nextInt();
		
		pst1.setInt(1,salary);
		pst1.setInt(2,manager_id);
		pst1.setString(3,"%"+ip_nal);
		//값을 구해서 종이 박스에 넣는다. resultSet
		
		ResultSet rs1 = pst1.executeQuery();
		
		while(rs1.next()) {
				
			String fname = rs1.getString("이름"); //위에서 지정한 alias로 출력할 수 있다.
			int e_salary = rs1.getInt("봉급");
			int m_id = rs1.getInt("매니저아이디");
			String h_date = rs1.getString("입사일");
			
			//위에서 칼럼별 alias를 지정했기 때문에 원래 칼럼 명을 쓰면 작동하지 않는다. 잘못된 칼럼 명이라고 뜸.
			/*String fname = rs1.getString("first_name"); 
			int e_salary = rs1.getInt("salary");
			int m_id = rs1.getInt("manager_id");
			String h_date = rs1.getString("hire_date");*/
			
			
			System.out.println(rs1.getRow()+"\t" +fname+"\t" +e_salary +"\t"+m_id +"\t"+h_date);
		}
		con1.close();
		System.out.println("접속끝");
		
	}
}
