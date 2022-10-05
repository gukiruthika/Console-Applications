package model;

public class Attendance {
	private int month;
	private int year;
	private String employeeId;
	private int numberOfDaysPresent;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

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

	public int getNumberOfDaysPresent() {
		return numberOfDaysPresent;
	}

	public void setNumberOfDaysPresent(int numberOfDaysPresent) {
		this.numberOfDaysPresent = numberOfDaysPresent;
	}
}
