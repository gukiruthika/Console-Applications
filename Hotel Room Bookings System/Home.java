package hotelRoomBookingSystem;

import java.sql.SQLException;

public class Home extends Utility {
	void home() throws SQLException {
		Home home = new Home();
		String option;
		String subOption;
		System.out.println("Welcome to GUK Resorts");
		do {
			System.out.print("""
					\n
					1)Check in
					2)Check out
					3)Guest details
					4)Room
					5)Exit

					Enter the option..
					""");
			option = scan.nextLine();
			switch (option) {
			case "1":
				home.checkIn();
				break;
			case "2":
				home.checkOut();
				break;
			case "3":
				do {
					System.out.print("""
							\n
							1)Present Guest Details
							2)Past Guest Details
							3)Exit to Main menu

							Enter the option..
							""");
					subOption = scan.nextLine();
					switch (subOption) {
					case "1":
						home.getPresentGuest();
						break;
					case "2":
						home.getPastGuest();
						break;
					case "3":
						break;
					default:
						System.out.println("~~~ Invalid option ~~~");
					}
				} while (!subOption.equals("3"));
				break;
			case "4":
				
				do {
					System.out.print("""
							\n
							1)Add room
							2)View rooms
							3)Available rooms
							4)Occupied rooms
							5)Remove room
							6)Exit to Main menu

							Enter the option..
							""");
					subOption = scan.nextLine();
					switch (subOption) {
					case "1":
						home.addRoom();
						break;
					case "2":
						home.viewRoom();
						break;
					case "3":
						home.viewAvailableRoom();
						break;
					case "4":
						home.occupiedRoom();
						break;
					case "5":
						home.removeRoom();
						break;
					case "6":
						break;
					default:
						System.out.println("~~~ Invalid option ~~~");
					}
				} while (!subOption.equals("6"));
				break;
			case "5":
				break;
			default:
				System.out.println("~~~ Invalid option ~~~");
			}
		} while (!option.equals("5"));
		System.out.println("~~~ Thank you!! ~~~");
	}
}