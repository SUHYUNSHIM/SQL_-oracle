import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
		System.out.println("접속했습니다.");
		
		Scanner sc = new Scanner(System.in);
		String name = sc.next();	
		
		String sql2 = "select * from employees" 
							+" where upper(first_name)= '"+name+"'"; //의도적으로 두 둘로 만들었다.
		Statement st2 = con1.createStatement();
		ResultSet rs2 = st2.executeQuery(sql2); //검색을 실행하라.
				
		while(rs2.next()) {
			int id = rs2.getInt("employee_id");				
			String fname = rs2.getString("first_name");
			String h_date = rs2.getString("hire_date");
			String hire_date = h_date.substring(0,10);
			System.out.println(rs2.getRow()+"\t" +id+"\t" +fname +"\t"+hire_date);
		}
		con1.close();
		System.out.println("접속끝");
	}

}
