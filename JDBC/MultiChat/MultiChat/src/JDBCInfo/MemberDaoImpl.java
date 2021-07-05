package JDBCInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MemberDaoImpl implements MemberDao{
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DriveManager = null;	

	@Override
	public Member getMemberByRegion(String region) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select nickname from member where region =?");
			ps.setString(1, region);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString("nickname")); //생성자 여럿 만들어야... 닉네임 컬럼만 출력할 것이기 때문.
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {					
				}
			}
		}
		return member;
	}

	@Override
	public Member getMemberByYear(String birthday) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select nickname from member where birthday like ?");
			String year = birthday.substring(0,4); // 생년웡일에서 년도만 빼내었음.			
			ps.setString(1,year+"%");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString("nickname"));
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {					
				}
			}
		}
		return member;
	}

	@Override
	public Member getMemberByMonth(String birthday) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select nickname from member where substr(birthday,5,2) = ?");
			String month = birthday.substring(4,6); // 입력받은 생년웡일에서 년도만 빼내었음.			
			ps.setString(1, month);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				member = new Member(rs.getString("nickname"));
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {					
				}
			}
		}
		return member;
	}

	@Override
	public void createMember() {
		Connection conn = null;
		ResultSet rs1;
		try {
			conn = DriverManager.getConnection(DB_URL,"hr","hr");
			Statement stmt = conn.createStatement();
			//stmt.executeUpdate("drop table member");
			
			String checkSql = "select count(*) from all_tables where table_name = 'member'";			
			rs1 = stmt.executeQuery(checkSql);
			if(!rs1.next()) {
				String sql = "create table member (nickname varchar(30), region varchar(30), birthday char(8))"; 
				//4글자 이상의 지역구 입력시 오류가 나는데, 이는 처음에 테이블 생성지 글자 제한을 작게 두었기 때문.
				//alter table member modify(nickname varchar(30));
				//alter table member modify(region varchar(30));
				//위의 두 명령어로 수동으로 바꿔주어야 함.
				stmt.executeUpdate(sql);
				stmt.close();
			}			
			//닉네임, 지역, 생년월일을 컬럼으로 하는 member 테이블을 만든다.
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
				}
			}
		}		
	}

	@Override
	public void insertMember(Member member) {
		Connection conn = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			//Statement stmt = conn.createStatement();
			String sql = "insert into member values(?,?,?)";
			String nn = member.getNickName();
			String rg = member.getRegion();
			String bd = member.getBirthday();
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = null;
			
			pst.setString(1,nn);
			pst.setString(2, rg);
			pst.setString(3, bd);
			
			pst.executeUpdate();			
			pst.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(conn !=null) {
				try {
					conn.close();
				}catch (SQLException e) {
					
				}
			}
		}		
	}	

}
