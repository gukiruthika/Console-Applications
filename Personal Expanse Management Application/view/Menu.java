package view;

import java.util.Scanner;

import controller.MenuController;
import model.Database;


public class Menu {
	private Scanner scan = new Scanner(System.in);
	private MenuController menuController;

	public Menu() {
		menuController = new MenuController();
	}

	public void init() {
		Database database = new Database();
		database.passData();
		displayOptions();
	}
	private void displayOptions() {
		System.out.println("""
				
				***********************
				1) Add Transaction
				2) View Transaction
				3) Search Transaction
				4) View Balance
				5) Exit
				***********************
				Enter option..
				""");
		if(menuController.checkOption(scan.nextLine())==1)
			displayOptions();
}
}
