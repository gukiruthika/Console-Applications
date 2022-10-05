package controller;

import model.EmployeeDetailsDatabase;

public class SetupController {
	public boolean isSetuped() {
		return EmployeeDetailsDatabase.getInstance().getEmployeeList().size()>0;
	}
}
