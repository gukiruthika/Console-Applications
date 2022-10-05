package View;

import java.util.Scanner;

import controller.LoginController;

public class LoginView {
	private Scanner scan = new Scanner(System.in);
	private LoginController loginController;

	LoginView() {
		loginController = new LoginController();
	}

	private void initLogin() {
		System.out.println("Welcome to GUK Enterprise\n ------------------------");
		chooseUser();
	}

	private void chooseUser() {
		System.out.println("Login as ( Admin / Employee ) : ");
		int option = loginController.checkUser(scan.nextLine());
		if (option == 1)
			checkForAdminLogin();
		else if (option == 0)
			checkForEmployeeLogin();
		else {
			System.out.println("\nInvalid Input.. Please try again!\n");
			chooseUser();
		}

	}

	private void checkForAdminLogin() {
		System.out.println("Enter User Name");
		String userName = scan.nextLine();
		System.out.println("Enter password");
		String password = scan.nextLine();
		if (loginController.checkAdminCredentials(userName, password)) {
			SetupEmployeeDetails setupEmployeeDetails = new SetupEmployeeDetails();
			setupEmployeeDetails.init();
			AdminMenu adminMenu = new AdminMenu();
			adminMenu.init();
		} else {
			System.out.println("\nInvalid Credentials. Please try again!\n");
			checkForAdminLogin();
		}
	}

	private void checkForEmployeeLogin() {
		System.out.println("Enter Employee id (Eg. Emp0001)");
		String employeeId = scan.nextLine();
		System.out.println("Enter password");
		String password = scan.nextLine();
		if (loginController.checkEmployeeCredentials(employeeId, password)) {
			SetupEmployeeDetails setupEmployeeDetails = new SetupEmployeeDetails();
			setupEmployeeDetails.init();
			EmployeeMenu employeeMenu = new EmployeeMenu();
			employeeMenu.init(employeeId);
		} else {
			System.out.println("\nInvalid Credentials. Please try again!\n");
			checkForEmployeeLogin();
		}
	}

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.initLogin();
	}

}
