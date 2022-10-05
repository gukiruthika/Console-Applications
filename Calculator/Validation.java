package Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	boolean check(String expression) {
		String regex = "[@#$_~`';:?,|&<>=]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches()) {
				return false;
		}
		return true;
	}
}