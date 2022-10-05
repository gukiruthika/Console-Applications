package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	Statement getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/hotelroom";
		String username = "root";
		String password = "mySQL@123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		Statement statement = connection.createStatement();
		return statement;
	}

	void setData(String query) {
		try {
			getConnection().executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void passData() {
		try {
			ResultSet resultSet = getConnection().executeQuery("Select * from transaction.transactions");
				Transaction transaction;
				List<Transaction> transactionList = new ArrayList<Transaction>();
				while (resultSet.next()) {
					transaction = new Transaction();
					transaction.setDate(resultSet.getString(1));
					transaction.setCategory(resultSet.getString(2));
					transaction.setKind(resultSet.getString(3));
					transaction.setAmount(resultSet.getInt(4));
					transaction.setDescription(resultSet.getString(5));
					transactionList.add(transaction);
				}
				TransactionDatabase.getInstance().insertTransaction(transactionList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
