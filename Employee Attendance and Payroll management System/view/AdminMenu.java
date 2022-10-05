package View;

import java.util.Scanner;

import controller.AdminMenuController;

public class AdminMenu {
	private Scanner scan = new Scanner(System.in);
	private AdminMenuController menuController;

	AdminMenu() {
		menuController = new AdminMenuController();
	}

	void init() {
		displayOptions();
	}

	private void displayOptions() {
		System.out.println("""


				1) Add Employee
				2) Remove Employee
				3) View Employees
				4) Update Salary
				5) View Salary
				6) View Attendance
				7) Log out
				Enter option..
				""");
		if (menuController.checkOption(scan.nextLine()) == 1)
			displayOptions();
	}
}
