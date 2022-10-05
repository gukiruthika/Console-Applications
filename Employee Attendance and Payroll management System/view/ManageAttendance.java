package View;

import controller.AttendanceController;
import controller.InputController;

public class ManageAttendance {

	private AttendanceController attendanceController;
	private InputController inputController;

	public ManageAttendance() {
		attendanceController = new AttendanceController();
		inputController = new InputController();
	}

	public void checkIn(String employeeId) {
		int month = Integer.valueOf(inputController.getInput("month (MM)", "(0[1-9]|1[012])"));
		int year = Integer.valueOf(inputController.getInput("year (YYYY)", "202[2-9]"));
		attendanceController.toCheckIn(employeeId, month, year);
		System.out.print("Checked in Sucessfully!!");
	}

	public void viewAttendance(String employeeId) {
		System.out.println("Month\tYear\tNumber of Days Present");
		attendanceController.toViewAttendance(employeeId);
	}

	public void viewAttendance() {
		System.out.printf("%-10s %-10s %-15s %-15s ", "Month", "Year", "Employee Id", "Number of Days Present");
		attendanceController.toViewAttendance();
	}

}
