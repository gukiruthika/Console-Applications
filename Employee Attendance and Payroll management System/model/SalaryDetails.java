package model;

public class SalaryDetails {
	private int month;
	private int year;
	private String employeeId;
	private String employeeName;
	private int netSalary;
	private int numberOfWorkingDays;
	private int numberOfDaysPresent;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(int netSalary) {
		this.netSalary = netSalary;
	}

	public int getNumberOfWorkingDays() {
		return numberOfWorkingDays;
	}

	public void setNumberOfWorkingDays(int numberOfWorkingDays) {
		this.numberOfWorkingDays = numberOfWorkingDays;
	}

	public int getNumberOfDaysPresent() {
		return numberOfDaysPresent;
	}

	public void setNumberOfDaysPresent(int numberOfDaysPresent) {
		this.numberOfDaysPresent = numberOfDaysPresent;
	}
}
