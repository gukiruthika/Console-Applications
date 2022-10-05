package View;

import controller.SetupController;
import model.Database;

public class SetupEmployeeDetails {

	private SetupController setupController;
	Database database = new Database();

	SetupEmployeeDetails() {
		setupController = new SetupController();
	}

	void init() {
		boolean isSetupCompleted = setupController.isSetuped();
		if (isSetupCompleted) {
			System.out.println("EmployeeDetails setup already completed");
		} else {
			Database database = new Database();
			database.passData("Select * from employee.employeedetails", "employeeDetails");
			database.passData("Select * from employee.attendance", "attendance");
			database.passData("Select * from employee.salarydetails", "salarydetails");
		}
	}
}
