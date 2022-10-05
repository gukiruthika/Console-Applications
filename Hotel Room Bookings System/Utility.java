package hotelRoomBookingSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class Utility {
	static Scanner scan = new Scanner(System.in);
	static Validation validate = new Validation();
	static boolean check;
	static Database database = new Database();

	void addRoom() throws SQLException {
		int roomNo = getRoomNo();
		String type = getType();
		String query = "Insert into hotelroom.roomdetails values (" + roomNo + ",'" + type + "'," + getCanHold() + ","
				+ getRent() + "," + getAvailability() + ")";
		database.db(query);

	}

	void removeRoom() throws SQLException {

		System.out.print("\nRoom no. : ");
		int roomNo = Integer.valueOf(scan.nextLine());
		String query = "Delete from hotelroom.roomdetails where roomNo=" + roomNo;
		database.db(query);

	}
	void getPresentGuest() throws SQLException {
		String query = "Select * from hotelroom.guestdetails";
		database.dbWithResult(query, "guest");
	}
	void getPastGuest() throws SQLException {
		String query = "Select * from hotelroom.pastguestdetails";
		database.dbWithResult(query, "guest");
	}
	void viewRoom() throws SQLException {

		String query = "Select * from hotelroom.roomdetails";
		database.dbWithResult(query, "room");

	}
	void viewAvailableRoom() throws SQLException {

		String query = "Select * from hotelroom.roomdetails where Availability = 1";
		database.dbWithResult(query, "room");

	}

	void checkIn() throws SQLException {

		String name = getName();
		String phoneNo = getMobileNo();
		String aadharNo = getAadharNo();
		int noOfDays = getNoofDays();
		int noOfRooms = getNoOfRooms(); 
		while (noOfRooms-- > 0) {
			viewAvailableRoom();
			int roomNo = getRoomNo();
			int max = getValue("Occupy",roomNo,"room");
			int noOfPersons;
			do {
			noOfPersons = getNoOfPersons();
			if(noOfPersons>max) {
				System.out.println("Exceeds maximum accomadation of the room!!");
				check=false;
			}
			}while(check==false);
			
//			int advance = getAdvance();
			int advance = 0;
			int balance = getBalance(roomNo, noOfDays,advance);
			String query = "Insert into hotelroom.guestdetails values (" + roomNo + ",'" + name + "','" + phoneNo
					+ "','" + aadharNo + "'," + noOfPersons + "," + noOfDays + "," + advance + "," + balance  +")";
			database.db(query);
			query = "Update hotelroom.roomdetails set Availability = false where roomNo = " + roomNo;
			database.db(query);
			System.out.println("Successfully Checked in... Thank you!!");
		}

	}
	int getNoofDays() {
		int number = 0;
		do {
			System.out.print("\nNumber of Days : ");
			String numberS = scan.nextLine();
			check = validate.number(numberS);
			if (check == false)
				System.out.println("Invalid Input!!");
			else
				number = Integer.valueOf(numberS);
		} while (check == false);
		return number;
	}
	void occupiedRoom() throws SQLException {
		String query = "Select * from hotelroom.roomdetails where Availability = 0";
		database.dbWithResult(query, "room");
	}
	
	void checkOut() throws SQLException {
		getPresentGuest();
		int roomNo = getRoomNo();

		int balance = getBalance(roomNo);
		
		System.out.println("Balance amount : Rs." + balance);
		do {
		String mode=getPaymentMode();
		int payment=getPayment();
		if(payment==balance) {
			System.out.println("Payment successful");
			check=true;
		}
		else {
			System.out.println("Incorrect amount");
			check=false;
		}
		}while(check==false);
		String query = "Insert into hotelroom.pastguestdetails Select * from hotelroom.guestdetails where RoomNo = " + roomNo;
		database.db(query);
		query = "Update hotelroom.roomdetails set Availability = true where roomNo = " + roomNo;
		database.db(query);
		query = "Delete from hotelroom.guestdetails where RoomNo = " + roomNo;
		database.db(query);
		
		
		System.out.println("Successfully Checked out... Thank you!!");
		
	}
	String getPaymentMode() {
		String mode;
		do {
			System.out.print("\nPayment mode : ");
			mode = scan.nextLine();
			check = (mode.equalsIgnoreCase("cash")||mode.equalsIgnoreCase("card")||mode.equalsIgnoreCase("UPI"));
			if (check == false)
				System.out.println("Invalid Payment mode!!");
		} while (check == false);
		return mode;
	}

	int getBalance(int roomNo) throws SQLException {
		int balance = getValue("Balance", roomNo, "guest");
		return balance;
	}

	int getBalance(int roomNo, int days, int advance) throws SQLException {
		int balance = (days*(getValue("Rent", roomNo, "room"))) - advance;
		return balance;
	}

	int getValue(String columnName, int roomNo, String tableName) throws SQLException {
		String query = "Select " + columnName + " from hotelroom." + tableName + "details where roomNo = " + roomNo;
		return database.dbReturn(query);
	}

	int getNoOfRooms() {
		int number = 0;
		do {
			System.out.print("\nNumber of Rooms : ");
			String numberS = scan.nextLine();
			check = validate.number(numberS);
			if (check == false)
				System.out.println("Invalid Input!!");
			else
				number = Integer.valueOf(numberS);
		} while (check == false);
		return number;
	}

	int getNoOfPersons() {
		int number = 0;
		do {
			System.out.print("\nNumber of Persons : ");
			String numberS = scan.nextLine();
			check = validate.number(numberS);
			if (check == false)
				System.out.println("Invalid Input!!");
			else
				number = Integer.valueOf(numberS);
		} while (check == false);
		return number;
	}

	String getAadharNo() {
		String aadharNo;
		do {
			System.out.print("\nAadhar no. : ");
			aadharNo = scan.nextLine();
			check = validate.aadharNo(aadharNo);
			if (check == false)
				System.out.println("Invalid Aadhar No!!");
		} while (check == false);
		return aadharNo;
	}

	String getMobileNo() {
		String mobileNo;
		do {
			System.out.print("\nMobile no. : ");
			mobileNo = scan.nextLine();
			check = validate.mobileNo(mobileNo);
			if (check == false)
				System.out.println("Invalid Mobile No!!");
		} while (check == false);
		return mobileNo;
	}

	String getName() {
		String name;
		do {
			System.out.print("\nName : ");
			name = scan.nextLine();
			check = validate.name(name);
			if (check == false)
				System.out.println("Invalid Type!!");
		} while (check == false);
		return name;
	}

	String getType() throws SQLException {
		String type;
		do {
			System.out.print("\nType : ");
			type = scan.nextLine();
			check = validate.name(type);
			if (check == false)
				System.out.println("Invalid Type!!");
		} while (check == false);
		return type;
	}

	int getRoomNo() {
		int roomNo = 0;
		do {
			System.out.print("\nRoom no. : ");
			String roomNoS = scan.nextLine();
			check = validate.roomNo(roomNoS);
			if (check == false)
				System.out.println("Invalid Room No!!");
			else
				roomNo = Integer.valueOf(roomNoS);
		} while (check == false);
		return roomNo;
	}

	int getCanHold() {
		int canHold = 0;

		do {
			System.out.print("\nNumber of persons can accomodate : ");
			String canHoldS = scan.nextLine();
			check = validate.number(canHoldS);
			if (check == false)
				System.out.println("Invalid Number of Persons!!");
			else
				canHold = Integer.valueOf(canHoldS);
		} while (check == false);
		return canHold;
	}

	int getRent() {
		int rent = 0;

		do {
			System.out.print("\nRent : Rs.");
			String rentS = scan.nextLine();
			check = validate.number(rentS);
			if (check == false)
				System.out.println("Invalid rent");
			else
				rent = Integer.valueOf(rentS);
		} while (check == false);
		return rent;
	}
	
	int getPayment() {
		int rent = 0;

		do {
			System.out.print("\nAmount Received: Rs.");
			String rentS = scan.nextLine();
			check = validate.number(rentS);
			if (check == false)
				System.out.println("Invalid Amount");
			else
				rent = Integer.valueOf(rentS);
		} while (check == false);
		return rent;
	}
	int getAdvance() {
		int amount=0;
		do {
		System.out.println("is there any advance money payment? (Y/N)");
		char advanceC = scan.next().charAt(0);
		scan.nextLine();
		if (advanceC == 'Y' || advanceC == 'y') {
			do {
				System.out.print("\nAdvance : Rs.");
				String rentS = scan.nextLine();
				check = validate.number(rentS);
				if (check == false)
					System.out.println("Invalid amount");
				else
					amount = Integer.valueOf(rentS);
			} while (check == false);
		} else if (advanceC == 'N' || advanceC == 'n') {
			amount =0;
			check=true;
		} else {
			check = false;
			System.out.println("Invalid input");
		}
		}while(check==false);
		
		return amount;
	}

	boolean getAvailability() {
		boolean availability = true;

		do {
			System.out.print("\nAvailable(Y/N) : ");
			char availabilityC = scan.next().charAt(0);
			scan.nextLine();
			if (availabilityC == 'Y' || availabilityC == 'y') {
				check = true;
				availability = true;
			} else if (availabilityC == 'N' || availabilityC == 'n') {
				check = true;
				availability = false;
			} else {
				check = false;
				System.out.println("Invalid input");
			}
		} while (check == false);
		return availability;
	}

	void displayColumn(String columnName) throws SQLException {
		String query = "Select " + columnName + " from hotelroom.roomtypedetails";
		database.dbWithResult(query, "column");
	}
}
