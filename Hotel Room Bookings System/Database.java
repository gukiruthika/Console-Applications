package hotelRoomBookingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	static String url = "jdbc:mysql://localhost:3306/hotelroom";
	static String username = "root";
	static String password = "mySQL@123";
	
	private Statement getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/hotelroom";
		String username = "root";
		String password = "mySQL@123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, username, password);
		Statement statement = connection.createStatement();
		return statement;
	}
	
	void db(String query) throws SQLException {
		try {
			getConnection().executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	int dbReturn(String query) throws SQLException {
		try {
			ResultSet resultSet = getConnection().executeQuery(query);
			if(resultSet.next()){
			return resultSet.getInt(1);
			}
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}

	void dbWithResult(String query, String option) throws SQLException {
		try {
			ResultSet resultSet = getConnection().executeQuery(query);
			switch (option) {
			case "room":
				System.out.printf("%-10s %-10s %-15s %-10s %-10s", "Room No.", "Type", "Accomdation", "Rent",
						"Availability");
				while (resultSet.next()) {
					System.out.printf("%n%-10s %-10s %-15s %-10s %-10s", resultSet.getInt(1), resultSet.getString(2),
							resultSet.getInt(3), resultSet.getInt(4), (resultSet.getBoolean(5) == true) ? "Yes" : "No");
				}
				break;
			case "guest":
				System.out.printf("%-10s %-10s %-15s %-18s %-15s %-15s", "Room No.", "Name", "Phone No.","Aadhar no.","No. of Persons","No. of Days");
				while (resultSet.next()) {
					System.out.printf("%n%-10s %-10s %-15s %-18s %-15s %-20s", resultSet.getInt(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6));
				}
				break;
				
			case "roomType":
				System.out.printf("%-10s %-15s %-10s", "Type", "Accomdation", "Rent");
				while (resultSet.next()) {
					System.out.printf("%n%-10s %-15s %-10s", resultSet.getString(1), resultSet.getInt(2),
							resultSet.getInt(3));
				}
				break;
			case "column":
				while (resultSet.next()) {
					System.out.print(resultSet.getString(1) + " | ");
				}
				break;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
