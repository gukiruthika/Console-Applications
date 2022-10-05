package hotelRoomBookingSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	boolean name(String name) {
		String regex = "[a-zA-Z]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches())
			return true;
		else
			return false;
	}
	boolean email(String email) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches())
			return true;
		else
			return false;
	}
	boolean mobileNo(String mobileNo) {
		String regex = "^[5-9][0-9]{9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobileNo);
		if (matcher.matches())
			return true;
		else
			return false;
	}
	boolean aadharNo(String aadharNo) {
		String regex = "^[2-9][0-9]{11}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(aadharNo);
		if (matcher.matches())
			return true;
		else
			return false;
	}
	boolean roomNo(String roomNo) {
		String regex = "[0-9]{3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(roomNo);
		if (matcher.matches())
			return true;
		else
			return false;
	}
	boolean number(String no ) {
		String regex = "^[1-9][0-9]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(no);
		if (matcher.matches())
			return true;
		else
			return false;
	}

}
