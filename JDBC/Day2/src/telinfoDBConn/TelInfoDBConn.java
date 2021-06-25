package telinfoDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TelInfoDBConn {
	private Connection con; //접속 객체
	public Connection getConnection() { //메소드 3형식
		return con;		//접속 객체를 가져가며 접속이 된다.
	}
	
	public TelInfoDBConn() //클래스와 이름이 같다 -> 생성자이다.
	//디폴트 생성자로 개발자가 만든 것이다.
	//프로그램 수행 시 생성자를 제일 먼저 실행. 
			throws ClassNotFoundException, SQLException{
		//Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 로딩.
		//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1512:xe","hr","hr");
		//데이터 베이스에 연결. con에 접속 객체가 들어간다.
		//con에 값이 들어가면 접속이 된 것이다.
		
		Class.forName("oracle.jdbc.driver.OracleDriver");//����̺� �ε�
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr","hr");
	}
	
}
