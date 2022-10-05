package controller;

import view.ManageTransaction;

public class MenuController {
	private ManageTransaction manageTransaction;
	public MenuController(){
		manageTransaction = new ManageTransaction();
	}
	public int checkOption(String option) {
		switch (option) {
		case "1":
			manageTransaction.addTransaction();
			break;
		case "2":
			manageTransaction.viewTransaction();
			break;
		case "3":
			manageTransaction.searchTransaction();;
			break;
		case "4":
			manageTransaction.viewBalance();;
			break;
		case "5":
			System.out.println("\nThank you!\n");
			return -1;
		default:
			System.out.println("\nInvalid option.. Please choose again!\n");
		}
		return 1;
	}
}
