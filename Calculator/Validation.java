package Calculator;

public class Validation {
	boolean check(String expression) {
		String[] a = expression.split("");
		for (int i = 0; i < a.length; i++) {
			char c = a[i].charAt(0);
			if ((c > 57 & c < 65) || (c > 94 & c < 97) || (c > 33 & c < 37) || (c == 38) || c == 39 || (c == 44)
					|| (c == 64) || (c == 92) || (c == 124) || (c == 126))
				return false;
		}
		return true;
	}
}