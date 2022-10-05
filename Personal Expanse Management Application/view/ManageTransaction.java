package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.TransactionDatabase;
import model.Transaction;

public class ManageTransaction {
	private Scanner scan = new Scanner(System.in);
	private Transaction transaction;

	public void addTransaction() {
		transaction = new Transaction();
		transaction.setDate(getInput("Date (DD/MM/YYYY) : ", "(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/][0-9]{4}"));
		transaction.setKind(getInput("Kind (Income/Expense) : ", "Income|Expense|income|expense"));
		transaction.setCategory(getInput("Category : ", "[A-Za-z ]+"));
		transaction.setAmount(Integer.valueOf(getInput("Amount : Rs.", "[0-9]+")));
		transaction.setDescription(getInput("Description : ", ".+"));
		TransactionDatabase.getInstance().insertTransaction(transaction);
		System.out.print("Transaction added Successfully!!");
	}

	public void viewTransaction() {
		System.out.printf("\n%-5s %-15s %-15s %-20s %-15s %-20s", "S.no", "Date", "Kind", "Category", "Amount",
				"Description");
		for (int i = 0; i < TransactionDatabase.getInstance().getTransactionList().size(); i++) {
			transaction = TransactionDatabase.getInstance().getTransactionList().get(i);
			System.out.printf("\n%-5s %-15s %-15s %-20s %-15s %-20s", (i+1), transaction.getDate(), transaction.getKind(),
					transaction.getCategory(), transaction.getAmount(), transaction.getDescription());
		}
		viewBalance();
	}

	public void viewBalance() {
		int totalIncome = 0, totalExpense = 0;
		for (int i = 0; i < TransactionDatabase.getInstance().getTransactionList().size(); i++) {
			transaction = TransactionDatabase.getInstance().getTransactionList().get(i);

			if (transaction.getKind().equalsIgnoreCase("Income")) {
				totalIncome += transaction.getAmount();
			} else {
				totalExpense += transaction.getAmount();
			}
		}
		System.out.println("\n\nTotal Income Earned ----> Rs." + totalIncome);
		System.out.println("\nTotal Expense Made ----> Rs." + totalExpense);
		if (totalIncome > totalExpense) {
			System.out.println("\nBalance (surplus) ----> Rs." + (totalIncome - totalExpense));
		} else if (totalIncome < totalExpense) {
			System.out.println("\nBalance (deficit) ----> Rs." + (totalExpense - totalIncome));
		} else {
			System.out.println("\nBalance ----> Rs.0");
		}

	}

	public void searchTransaction() {
		String category = getInput("\nCategory : ", "[A-Za-z ]+");
		List<Transaction> transactionList = TransactionDatabase.getInstance().getTransactionList();
		List<Transaction> transactionSubList = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			if ((transaction.getCategory().equalsIgnoreCase(category))) {
				transactionSubList.add(transaction);
			}
		}
		if(transactionSubList.size()>0) {
		System.out.printf("\n%-5s %-10s %-10s %-20s %-15s %-20s", "S.no", "Date", "Kind", "Category", "Amount",
				"Description");
		for (int i = 0; i < transactionSubList.size(); i++) {
			transaction = transactionSubList.get(i);
			System.out.printf("\n%-5s %-10s %-10s %-20s %-15s %-20s", (i+1), transaction.getDate(), transaction.getKind(),
					transaction.getCategory(), transaction.getAmount(), transaction.getDescription());
		}
		}
		else {
		System.out.println("No such category!!");
		searchTransaction();
		}
	}

	String getInput(String field, String regex) {
		String input;
		System.out.print("\nEnter " + field);
		input = scan.nextLine();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches())
			return input;
		else {
			System.out.println("Invalid input!!");
			return getInput(field, regex);
		}
	}
}
