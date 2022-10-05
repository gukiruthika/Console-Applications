package View;

import java.util.Scanner;

import controller.EmployeeMenuController;

public class EmployeeMenu {
	private Scanner scan = new Scanner(System.in);
	private EmployeeMenuController menuController;

	EmployeeMenu() {
		menuController = new EmployeeMenuController();
	}

	void init(String employeeId) {
		displayOptions(employeeId);
	}

	private void displayOptions(String employeeId) {
		System.out.println("""


				1) Check in
				2) Pay Slip
				3) Attendance details
				4) Log out
				Enter option..
				""");
		if (menuController.checkOption(employeeId, scan.nextLine()) == 1)
			displayOptions(employeeId);
	}
}
