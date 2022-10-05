package controller;

import View.ManageAttendance;
import View.ManageEmployeeDetails;
import View.ManageSalary;

public class AdminMenuController {
	private ManageEmployeeDetails manageEmployeeDetails;
	private ManageAttendance manageAttendance;
	private ManageSalary manageSalary;
	public AdminMenuController(){
		manageEmployeeDetails = new ManageEmployeeDetails();
		manageAttendance = new ManageAttendance();
		manageSalary = new ManageSalary();
	}
	public int checkOption(String option) {
		switch (option) {
		case "1":
			manageEmployeeDetails.addEmployee();
			break;
		case "2":
			manageEmployeeDetails.removeEmployee();
			break;
		case "3":
			manageEmployeeDetails.viewEmployee();
			break;
		case "4":
			manageSalary.updateAllSalary();
			break;
		case "5":
			manageSalary.viewSalary();
			break;
		case "6":
			manageAttendance.viewAttendance();
			break;
		case "7":
			System.out.println("\nThank you!\n");
			return -1;
		default:
			System.out.println("\nInvalid option.. Please choose again!\n");
		}
		return 1;
	}
}
