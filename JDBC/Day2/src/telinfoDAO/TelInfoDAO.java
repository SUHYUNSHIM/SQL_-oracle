package telinfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import telinfoDBConn.TelInfoDBConn;
import telinfoVO.TelInfoVO;

public class TelInfoDAO {	
	
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

	
	public boolean insert_nametel(int id, String name , String tel, String sDate) {
		String sql = "insert into TelTable values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			pstmt.setString(3,tel);
			
			//20161123
			//문자를 날짜로 바꿔야 한다.
			
			int year = Integer.parseInt(sDate.substring(0,4)) -1900;
			int month = Integer.parseInt(sDate.substring(4,6))-1;
			int day = Integer.parseInt(sDate.substring(6,8));
			Date d = new Date(year,month,day);
			
			//java.util.Date d -> 만약 sql의 Date와 java의 Date를 다 써야 할 때.
			pstmt.setDate(4, d);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("insert Exception");
			return false;
		}
		return true;
	}
	
	public boolean insert_change_nametel(int id, String name , String tel, String sDate) {
		String sql = "insert into TelTable values(?,?,?, to_date(?))"; //to_date(?)
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			pstmt.setString(3,tel);
			
			//20161123
			//문자를 날짜로 바꿔야 한다.
			//지금은 날짜를 DB 입장에서 본다.
			//to_date(문자열)
			//main에서 전달되는 인자의 형식은 "20161206"이다.
			//문자 형태 그대로 넣어도 됨.
			//pstmt.setString(4,sDate);
			
			
			int year = Integer.parseInt(sDate.substring(0,4)) -1900;
			int month = Integer.parseInt(sDate.substring(4,6))-1;
			int day = Integer.parseInt(sDate.substring(6,8));
			
			java.util.Date d = new Date(year,month,day);
			pstmt.setString(4, sDate); // "20180907" --> to_date("20180907")  --> 18/09/07 로 저장된다.
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("insert Exception");
			return false;
		}
		return true;
	}
	
	public boolean update_name(String b_name, String a_name) {
		String sql = "update teltable set name = ? where name = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a_name);
			pstmt.setString(2, b_name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean delete_id(int id) {
		String sql = "delete from teltable where id =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Delete Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}
