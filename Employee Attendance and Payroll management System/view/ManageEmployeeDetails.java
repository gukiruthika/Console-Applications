package View;

import controller.EmployeeDetailsController;
import controller.InputController;

public class ManageEmployeeDetails {
	private EmployeeDetailsController employeeDetailsController;
	private InputController inputController;

	public ManageEmployeeDetails() {
		inputController = new InputController();
		employeeDetailsController = new EmployeeDetailsController();
	}

	public void addEmployee() {
		employeeDetailsController.toAddEmployee();
		System.out.print("Employee added Successfully!!");
	}

	public void viewEmployee() {
		System.out.printf("%-15s %-20s %-15s %-20s %-15s %-15s %-20s", "Employee id", "Name", "Date of Birth",
				"Designation", "Aadhar Number", "Phone Number", "Email id");
		employeeDetailsController.toViewEmployee();
	}

	public void removeEmployee() {
		String employeeId = inputController.getInput("Employee id (Eg. Emp0001)", "(Emp\\d{4})");
		System.out.println(employeeDetailsController.toRemoveEmployee(employeeId));
	}

}
