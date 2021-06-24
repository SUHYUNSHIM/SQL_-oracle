package telinfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import telinfoDBConn.TelInfoDBConn;
import telinfoVO.TelInfoVO;

public class TelInfoDAO {

	//CRUD를 여기에 작성
	//이제까지는 insert.java , select.java , update.java , delete.java, 
	
	private Connection con;
	//결과적으로 con에는 DBConn 접속객체가 들어간다.
	//con은 telinfoDBConn.TelInfoDBConn에 있음
	
	PreparedStatement pstmt = null;
	ResultSet rs = null ;// 전역 
	
	public TelInfoDAO()  //생성자
		throws ClassNotFoundException, SQLException{
		
		con = new TelInfoDBConn().getConnection();
		//new는 객체를 생성할 때 그리고 위와 같이 다른 클래스를 가져올 때 사용할 수 있다.
		//생성자를 찾아서, 오라클드라이버 로딩하고, 실제로 접속한 객체를 con에 넣은 것
		//그 con을 반환하는 메소드를 호출한다. 
		//접속 객체 get -> con
		//그 결과 접속이 되었다. 
		
	} //생성자 end 
		 //반환형 
	public ArrayList<TelInfoVO> getAllInfo() throws SQLException{
		
		ArrayList<TelInfoVO> tiarray = new ArrayList<TelInfoVO>();
		String sql = "SELECT * FROM TelTable ORDER BY id";
		pstmt = con.prepareStatement(sql); // ? 없어도 prepare 쓸 수 있다.
		
		rs = pstmt.executeQuery(); //테이블 내용을 rs 종이 박스에 넣는다.
		while(rs.next()) {
			//종이 박스에 4개의 기억창고, 변수에 값을 넣는다.
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			Date d = rs.getDate("d");
			//table -> 변수 넣기
			
			TelInfoVO tv = new TelInfoVO(id,name,tel,d);
			//VO객체에 넣기
			tiarray.add(tv);
		} //while end
		return tiarray;
	} //getAllInfo() -end 
}
