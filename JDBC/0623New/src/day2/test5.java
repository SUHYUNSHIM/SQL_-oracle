package day2;
//삭제
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class test5 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		
		String sql2 = //사원 퇴사시
				"delete from teltable where id = ?";
				//"delete from teltable where name = ? ";
		PreparedStatement pst1 = con1.prepareStatement(sql2);
		Scanner sc1 = new Scanner(System.in);
		System.out.print("삭제할 아이디: ");
		int id2 = sc1.nextInt();
		
		pst1.setInt(1, id2);
		int rowcnt1 = pst1.executeUpdate();
		System.out.println(rowcnt1 + "행 delete");
		con1.close();
		System.out.println("접속 끝");
				
	}
}
