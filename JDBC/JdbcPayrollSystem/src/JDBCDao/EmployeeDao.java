package JDBCDao;

import JDBCModel.Employee;

public interface EmployeeDao {
	Employee getEmployeeById(int id);
	void createEmployee();
	void insertEmployee(Employee employee);
}
