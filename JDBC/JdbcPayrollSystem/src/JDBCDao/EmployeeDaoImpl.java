package JDBCDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBCModel.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	@Override
	public Employee getEmployeeById(int id) {
		Connection conn = null;
		Employee employee = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			PreparedStatement ps = conn.prepareStatement("select * from employee where id =?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				employee = new Employee(id, rs.getString("name"));
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
		return employee;
	}
	@Override
	public void createEmployee() {
		Connection conn = null;
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("drop table employee");
			stmt.executeUpdate("create table employee (id integer, name char(30))");
			stmt.close();
		} catch(SQLException e) {
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
	public void insertEmployee(Employee employee) {
		Connection conn = null;
		
		try {
			conn=DriverManager.getConnection( DB_URL,"hr","hr");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into employee values ("+ employee.getId() + ",'" + employee.getName() + "')");
			stmt.close();
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
