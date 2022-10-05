package View;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.AttendanceController;
import controller.InputController;
import model.Attendance;
import model.EmployeeDetailsDatabase;

public class ManageAttendance {

	private List<Attendance> attendanceList = EmployeeDetailsDatabase.getInstance().getAttendanceList();
	private AttendanceController attendanceController;
	private InputController inputController ;

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

		for (Attendance attendance : attendanceList) {
			if (attendance.getEmployeeId().equals(employeeId)) {
				System.out.println(attendance.getMonth() + "\t" + attendance.getYear() + "\t\t"
						+ attendance.getNumberOfDaysPresent());
			}

		}
	}

	public void viewAttendance() {
		System.out.printf("%-10s %-10s %-15s %-15s ", "Month", "Year", "Employee Id", "Number of Days Present");

		for (Attendance attendance : attendanceList) {
			System.out.printf("\n%-10s %-10s %-15s %-15s", attendance.getMonth(), attendance.getYear(),
					attendance.getEmployeeId(), attendance.getNumberOfDaysPresent());
		}

	}

}
