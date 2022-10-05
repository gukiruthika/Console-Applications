package View;

import java.util.List;
import controller.InputController;
import controller.SalaryController;
import model.EmployeeDetailsDatabase;
import model.SalaryDetails;

public class ManageSalary {
	private SalaryController salaryController;
	private InputController inputController;

	public ManageSalary() {
		salaryController = new SalaryController();
		inputController = new InputController();

	}

	public void updateAllSalary() {
		int month = Integer.valueOf(inputController.getInput("month (MM)", "(0?[1-9]|1[012])"));
		int year = Integer.valueOf(inputController.getInput("year (YYYY)", "\\d{4}"));
		salaryController.toUpdateAllSalary(month, year);
		System.out.print("Salary updated Successfully!!");
	}

	public void viewSalary() {
		System.out.printf("%-10s %-10s %-15s %-20s %-15s %-15s ", "Month", "Year", "Employee Id", "Employee Name",
				"Net Salary", "Number of Days Present");
		salaryController.toViewSalary();
	}

	public void viewPaySlip(String employeeId) {
		int month = Integer.valueOf(inputController.getInput("month (MM)", "(0[1-9]|1[012])"));
		int year = Integer.valueOf(inputController.getInput("year (YYYY)", "\\d{4}"));
		boolean check = true;
		SalaryDetails salaryDetails = new SalaryDetails();
		List<SalaryDetails> salaryDetailsList = EmployeeDetailsDatabase.getInstance().getSalaryDetailsList();
		for (SalaryDetails eachSalaryDetails : salaryDetailsList) {
			if ((eachSalaryDetails.getMonth() == month) & (eachSalaryDetails.getYear() == year)
					& (eachSalaryDetails.getEmployeeId().equals(employeeId))) {
				salaryDetails = eachSalaryDetails;
				check = false;
				break;
			}
		}
		if (check) {
			System.out.println("Salary has not updated!!");
			return;
		}
		int numberOfDaysPresent = salaryDetails.getNumberOfDaysPresent();
		int grossSalary = EmployeeDetailsDatabase.getInstance().getEmployee(employeeId).getSalary();
		int basicPay = (grossSalary * numberOfDaysPresent * 40) / (25 * 100);
		int houseRentAllowances = basicPay * 40 / 100;
		int conveyanceAllowances = (1000 * numberOfDaysPresent) / 25;
		int medicalAllowances = (1000 * numberOfDaysPresent) / 25;
		int otherAllowances = ((grossSalary * numberOfDaysPresent) / 25)
				- (basicPay + houseRentAllowances + conveyanceAllowances + medicalAllowances);
		int totalEarnings = basicPay + houseRentAllowances + conveyanceAllowances + medicalAllowances + otherAllowances;
		int PFContribution = (basicPay < 15000) ? (basicPay * 12 / 100) : (15000 * 12 / 100);
		int ESIContribution = (totalEarnings <= 21000) ? (basicPay * 75 / 10000) : 0;
		int totalDeductions = PFContribution + ESIContribution;
		int netSalary = totalEarnings - totalDeductions;
		System.out.println("""
						   GUK Enterprise
					No.53/8,Samba Street, Tenkasi-627811
						     Pay Slip
				""");
		System.out.println("Name of the Employee  : \t"
				+ EmployeeDetailsDatabase.getInstance().getEmployee(employeeId).getEmployeeName());
		System.out.println("Employee Id           : \t" + employeeId);
		System.out.println("Designation           : \t"
				+ EmployeeDetailsDatabase.getInstance().getEmployee(employeeId).getDesignation());
		System.out.println("Number of Days Present: \t" + numberOfDaysPresent);
		System.out.println("----------------------------------------------------");
		System.out.println("Earnings");
		System.out.println("----------------------------------------------------");
		System.out.println("Basic Wages           : \t" + basicPay);
		System.out.println("HRA                   : \t" + houseRentAllowances);
		System.out.println("Conveynce Allowances  : \t" + conveyanceAllowances);
		System.out.println("Medical Allowances    : \t" + medicalAllowances);
		System.out.println("Other Allowances      : \t" + otherAllowances);
		System.out.println("Gross Salary          : \t" + totalEarnings);
		System.out.println("----------------------------------------------------");
		System.out.println("Deductions");
		System.out.println("----------------------------------------------------");
		System.out.println("EPF                   : \t" + PFContribution);
		System.out.println("ESI                   : \t" + ESIContribution);
		System.out.println("Total Deductions      : \t" + totalDeductions);
		System.out.println("----------------------------------------------------");
		System.out.println("Net Salary            : \t" + netSalary);
		System.out.println("----------------------------------------------------");

	}
}
