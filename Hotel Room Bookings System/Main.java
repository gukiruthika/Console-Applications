package hotelRoomBookingSystem;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		Home home = new Home();
		try {
			home.home();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

}
