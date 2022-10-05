package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

	Statement getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/hotelroom";
		String username = "root";
		String password = "mySQL@123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		Statement statement = connection.createStatement();
		return statement;
	}

	void setData(String query) {
		try {
			getConnection().executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void passData(String query, String tableName) {

		try {
			ResultSet resultSet = getConnection().executeQuery(query);
			if (tableName.equals("employeeDetails")) {
				Employee employee;
				List<Employee> employeeList = new ArrayList<Employee>();
				while (resultSet.next()) {
					employee = new Employee();
					employee.setEmployeeId(resultSet.getString(1));
					employee.setEmployeeName(resultSet.getString(2));
					employee.setDateOfBirth(resultSet.getString(3));
					employee.setDesignation(resultSet.getString(4));
					employee.setAadharNumber(resultSet.getLong(5));
					employee.setPhoneNumber(resultSet.getLong(6));
					employee.setEmailId(resultSet.getString(7));
					employee.setSalary(resultSet.getInt(8));
					employeeList.add(employee);
				}
				EmployeeDetailsDatabase.getInstance().insertEmployee(employeeList);
			} else if (tableName.equals("attendance")) {
				Attendance attendance;
				List<Attendance> attendanceList = new ArrayList<Attendance>();
				while (resultSet.next()) {
					attendance = new Attendance();
					attendance.setMonth(resultSet.getInt(1));
					attendance.setYear(resultSet.getInt(2));
					attendance.setEmployeeId(resultSet.getString(3));
					attendance.setNumberOfDaysPresent(resultSet.getInt(4));
					attendanceList.add(attendance);
				}
				EmployeeDetailsDatabase.getInstance().insertAttendance(attendanceList);
			} else if (tableName.equals("salarydetails")) {
				SalaryDetails salaryDetails;
				List<SalaryDetails> salaryDetailsList = new ArrayList<SalaryDetails>();
				while (resultSet.next()) {
					salaryDetails = new SalaryDetails();
					salaryDetails.setMonth(resultSet.getInt(1));
					salaryDetails.setYear(resultSet.getInt(2));
					salaryDetails.setEmployeeId(resultSet.getString(3));
					salaryDetails.setEmployeeName(resultSet.getString(4));
					salaryDetails.setNetSalary(resultSet.getInt(5));
					salaryDetails.setNumberOfWorkingDays(resultSet.getInt(6));
					salaryDetails.setNumberOfDaysPresent(resultSet.getInt(7));
					salaryDetailsList.add(salaryDetails);
				}
				EmployeeDetailsDatabase.getInstance().insertSalaryDetails(salaryDetailsList);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
