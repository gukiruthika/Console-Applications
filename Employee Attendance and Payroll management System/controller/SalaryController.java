package controller;

import java.util.List;

import model.Attendance;
import model.EmployeeDetailsDatabase;
import model.SalaryDetails;

public class SalaryController {
	private SalaryDetails salaryDetails;
	private List<SalaryDetails> salaryDetailsList = EmployeeDetailsDatabase.getInstance().getSalaryDetailsList();

	public void toUpdateAllSalary(int month, int year) {
		for (SalaryDetails salaryDetails : salaryDetailsList) {
			if ((salaryDetails.getMonth() == month) & (salaryDetails.getYear() == year)) {
				System.out.println("Salary already updated!!");
				return;
			}
		}
		for (int i = 0; i < EmployeeDetailsDatabase.getInstance().getEmployeeList().size(); i++) {
			String employeeId = EmployeeDetailsDatabase.getInstance().getEmployeeList().get(i).getEmployeeId();
			int numberOfDaysPresent = 0;
			List<Attendance> attendanceList = EmployeeDetailsDatabase.getInstance().getAttendanceList();
			for (Attendance attendance : attendanceList) {
				if ((attendance.getMonth() == month) & (attendance.getYear() == year)
						& (attendance.getEmployeeId().equals(employeeId))) {
					numberOfDaysPresent = attendance.getNumberOfDaysPresent();
				}
			}
			if (numberOfDaysPresent == 0) {
				continue;
			}
			salaryDetails = new SalaryDetails();
			salaryDetails.setMonth(month);
			salaryDetails.setYear(year);
			salaryDetails.setEmployeeId(employeeId);
			salaryDetails
					.setEmployeeName(EmployeeDetailsDatabase.getInstance().getEmployeeList().get(i).getEmployeeName());
			salaryDetails.setNumberOfWorkingDays(25);
			salaryDetails.setNumberOfDaysPresent(numberOfDaysPresent);
			int netSalary = calculateNetSalary(numberOfDaysPresent,
					EmployeeDetailsDatabase.getInstance().getEmployeeList().get(i).getSalary());
			salaryDetails.setNetSalary(netSalary);
			EmployeeDetailsDatabase.getInstance().insertSalaryDetails(salaryDetails);
		}
	}

	public void toViewSalary() {
		for (SalaryDetails salaryDetails : salaryDetailsList) {
			System.out.printf("\n%-10s %-10s %-15s %-20s %-15s %-15s", salaryDetails.getMonth(),
					salaryDetails.getYear(), salaryDetails.getEmployeeId(), salaryDetails.getEmployeeName(),
					salaryDetails.getNetSalary(), salaryDetails.getNumberOfDaysPresent());
		}
	}

	int calculateNetSalary(int numberOfDaysPresent, int grossSalary) {
		int basicPay = ((grossSalary * numberOfDaysPresent * 40) / (25 * 100));
		int PFContribution = (basicPay < 15000) ? (basicPay * 12 / 100) : (15000 * 12 / 100);
		int ESIContribution = (((grossSalary * numberOfDaysPresent) / 25) <= 21000) ? (basicPay * 75 / 10000) : 0;
		int netSalary = ((grossSalary * numberOfDaysPresent) / 25) - PFContribution - ESIContribution;
		return netSalary;
	}
}
