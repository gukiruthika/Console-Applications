package controller;

import java.util.List;

import model.Employee;
import model.EmployeeDetailsDatabase;

public class EmployeeDetailsController {
	private InputController inputController;
	private List<Employee> employeeList = EmployeeDetailsDatabase.getInstance().getEmployeeList();
	private Employee employee;

	public EmployeeDetailsController() {
		inputController = new InputController();
	}

	public void toAddEmployee() {
		employee = new Employee();
		String employeeId = inputController.getInput("Employee id (Eg. Emp0001)", "(Emp\\d{4})");

		for (Employee employee : employeeList) {
			if ((employee.getEmployeeId().equals(employeeId))) {
				System.out.println("Employee id already exists!!");
				toAddEmployee();
			}
		}
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(inputController.getInput("name", "[A-Za-z ]+"));
		employee.setDateOfBirth(inputController.getInput("date of birth (DD/MM/YYYY)",
				"(0[1-9]|[12][0-9]|3[01])[/](0?[1-9]|1[012])[/](19[6-9][0-9]|20[01][0-5])"));
		employee.setDesignation(inputController.getInput("designation", "[A-Za-z ]+"));
		employee.setAadharNumber(Long.valueOf(inputController.getInput("aadharNumber", "[2-9][0-9]{11}")));
		employee.setPhoneNumber(Long.valueOf(inputController.getInput("phoneNumber", "[5-9][0-9]{9}")));
		employee.setEmailId(
				inputController.getInput("email address", "(^[a-zA-Z0-9.]+@[a-zA-Z0-9]+([.]?[a-zA-Z0-9-]+)+)"));
		employee.setSalary(Integer.valueOf(inputController.getInput("gross salary", "[1-9][0-9]+")));
		EmployeeDetailsDatabase.getInstance().insertEmployee(employee);
	}

	public void toViewEmployee() {
		for (Employee employee : employeeList) {
			System.out.printf("\n%-15s %-20s %-15s %-20s %-15s %-15s %-20s", employee.getEmployeeId(),
					employee.getEmployeeName(), employee.getDateOfBirth(), employee.getDesignation(),
					employee.getAadharNumber(), employee.getPhoneNumber(), employee.getEmailId());
		}
	}

	public String toRemoveEmployee(String employeeId) {
		for (Employee employee : employeeList) {
			if ((employee.getEmployeeId().equals(employeeId))) {
				EmployeeDetailsDatabase.getInstance().removeEmployee(employeeId);
				return "Removed Successfully";
			}
		}
		return "Such Employee ID doesn't exist!!";
	}
}
