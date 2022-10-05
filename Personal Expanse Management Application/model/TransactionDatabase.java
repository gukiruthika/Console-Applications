package model;

import java.util.ArrayList;
import java.util.List;

public class TransactionDatabase {
	private static TransactionDatabase transactionDb;
	private List<Transaction> transactionList;
	Database database = new Database();

	TransactionDatabase() {
		transactionList = new ArrayList<Transaction>();
	}

	public static TransactionDatabase getInstance() {
		if (transactionDb == null) {
			transactionDb = new TransactionDatabase();

		}
		return transactionDb;
	}

	public void insertTransaction(List<Transaction> transactionList) {
		this.transactionList.addAll(transactionList);
	}

	public void insertTransaction(Transaction transaction) {
		this.transactionList.add(transaction);
		database.setData("Insert into transaction.transactions values ('" + transaction.getDate() + "' , '"
				+ transaction.getCategory() + "' , '" + transaction.getKind() + "' , " + transaction.getAmount()
				+ " , '" + transaction.getDescription() + "')");
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

}
