package day2;
//수정
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class test6 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		
		String sql2 = 
				"update teltable set tel= ? where name = ?";
		PreparedStatement pst1 = con1.prepareStatement(sql2);
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("수정하기 위해 검색할 이름은 : ");
		String name =sc1.nextLine();
		
		System.out.print("변경 후 전화번호는: ");
		String tel2 = sc1.nextLine();
		
		pst1.setString(1, tel2);
		pst1.setString(2,name);
		
		int rowcnt1 = pst1.executeUpdate();
		System.out.println(rowcnt1+"행 update");
		con1.close();
		System.out.println("접속끝");
		
	}
}
