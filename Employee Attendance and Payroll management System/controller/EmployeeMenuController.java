package controller;

import View.ManageAttendance;
import View.ManageSalary;

public class EmployeeMenuController {
	private ManageAttendance manageAttendance;
	private ManageSalary manageSalary;

	public EmployeeMenuController() {
		manageAttendance = new ManageAttendance();
		manageSalary = new ManageSalary();
	}

	public int checkOption(String employeeId, String option) {
		switch (option) {
		case "1":
			manageAttendance.checkIn(employeeId);
			break;
		case "2":
			manageSalary.viewPaySlip(employeeId);
			break;
		case "3":
			manageAttendance.viewAttendance(employeeId);
			break;
		case "4":
			System.out.println("\nThank you!\n");
			return -1;
		default:
			System.out.println("\nInvalid option.. Please choose again!\n");
		}
		return 1;
	}

}
