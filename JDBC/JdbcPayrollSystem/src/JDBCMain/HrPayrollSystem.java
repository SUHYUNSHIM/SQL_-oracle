package JDBCMain;

import JDBCDao.EmployeeDao;
import JDBCDao.EmployeeDaoImpl;
import JDBCModel.Employee;

public class HrPayrollSystem {
	public static void main(String[] args) {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		//테이블 생성
		employeeDao.createEmployee();
		//employee 테이블에 데이터 추가
		employeeDao.insertEmployee(new Employee(1,"Ravi"));
		//employee id 기반으로 데이터 가져오기
		Employee employee = employeeDao.getEmployeeById(1);
		System.out.println("Employee name: " +employee.getName());
	}
}
