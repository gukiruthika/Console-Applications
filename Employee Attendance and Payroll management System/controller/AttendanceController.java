package controller;

import java.util.List;

import model.Attendance;
import model.EmployeeDetailsDatabase;

public class AttendanceController {
	Attendance attendance = new Attendance();
	List<Attendance> attendanceList = EmployeeDetailsDatabase.getInstance().getAttendanceList();

	public void toCheckIn(String employeeId, int month, int year) {
		boolean check = true;
		for (Attendance attendance : attendanceList) {
			if ((attendance.getEmployeeId().equals(employeeId)) & (attendance.getMonth() == month)
					& (attendance.getYear() == year)) {
				if (attendance.getNumberOfDaysPresent() > 24) {
					System.out.println("Exceeds Total Number of Workings days!!");
					return;
				}
				EmployeeDetailsDatabase.getInstance().updateAttendance(attendance);
				check = false;
			}
		}
		if (check) {
			attendance.setMonth(month);
			attendance.setYear(year);
			attendance.setEmployeeId(employeeId);
			attendance.setNumberOfDaysPresent(1);
			EmployeeDetailsDatabase.getInstance().insertAttendance(attendance);
		}
	}

}
