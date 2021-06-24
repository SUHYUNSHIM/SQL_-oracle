package day2;
//삽입
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class test4 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:"
				+"1521:xe","hr","hr");
		System.out.println("접속 완료");
		
		//자료 insert
		String sql2 = "insert into teltable values(?,?,?,?)";
		PreparedStatement pst1 = con1.prepareStatement(sql2);
		ResultSet rs = null;
		
		
		//GUI
		int id = Integer.parseInt(JOptionPane.showInputDialog("아이디는?"));
		String name  = JOptionPane.showInputDialog("이름은?");
		String tel = JOptionPane.showInputDialog("전화번호는?");
		String ipsail = JOptionPane.showInputDialog("날짜는?");
		
		//물음표 순서는 못바꾼다.
		pst1.setInt(1,id);
		pst1.setString(2, name);
		pst1.setString(3, tel);
		pst1.setString(4, ipsail);
		
		int rowcnt = pst1.executeUpdate();
		System.out.println("잘 들어봐"+rowcnt+"행 insert 했다");
		con1.close();
		System.out.println("접속 끝");
		
		
	}
}
