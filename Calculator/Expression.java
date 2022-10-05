package Calculator;

import java.util.LinkedList;

public class Expression {
	LinkedList<Object> toTerms(String problem){
		String[] term = problem.split("");
		char[] type = new char[term.length];
		for (int i = 0; i < term.length; i++) {
			char value = term[i].charAt(0);
			if (value > 47 & value < 58)
				type[i] = 'N'; // number
			else if ((value > 64 & value < 91) || (value > 96 & value < 123))
				type[i] = 'L'; // alphabet
			else if (value == 46)
				type[i] = 'D'; // decimalPoint
			else if (value == 45)
				type[i] = 'M'; // minus
			else if (value == 91 | value == 123 | value == 40)
				type[i] = 'B'; // open bracket
			else if (value == 93 | value == 125 | value == 41)
				type[i] = 'C'; // close bracket
			else
				type[i] = 'O'; // others
		}

		LinkedList<Object> terms = new LinkedList<Object>();
		for (int i = 0; i < term.length; i++) {				
			if (type[i] == 'N') {						//joining numbers
				double x = Double.valueOf(term[i]);			
				if (i >= 1 && (type[i - 1] == 'M')) {	//check for negative numbers
					x = -x;
					terms.removeLast();
					if (i >= 2 && (type[i - 2] == 'N' | type[i - 2] == 'C')) {
						x = -x;
						terms.add("-");
					}
				}
				while (i < term.length - 1 && (type[i + 1] == 'N')) {
					++i;
					x = x * 10 + (Double.valueOf(term[i]));
				}
				if (i < term.length - 1 && type[i + 1] == 'D') {   //check for decimal numbers
					int dp = 0;
					++i;
					while (i < term.length - 1 && (type[i + 1] == 'N')) {
						++i;
						++dp;
						x = x + (Double.valueOf(term[i]) / (10 * dp));
					}
				}

				terms.add(x);
			} else if (type[i] == 'L') {			//joining letters
				String x = term[i];
				while (i < term.length - 1 && type[i + 1] == 'L') {
					++i;
					x = x + term[i];
				}
				if (x.equalsIgnoreCase("pi")) {
					if ((i-(x.length()-1)) >= 1 && ((type[i - (x.length())] == 'N') || (type[i - (x.length())] == 'C') )) {
						terms.add("*");
					}
					terms.add(3.1415);
					if ((i < type.length-1) && ((type[i + 1] == 'N') || (type[i + 1] == 'B'))) {
						terms.add("*");
					}
				} else if (x.equalsIgnoreCase("sqrt") | x.equalsIgnoreCase("cbrt") | x.equalsIgnoreCase("log")
						| x.equalsIgnoreCase("sin") | x.equalsIgnoreCase("cos") | x.equalsIgnoreCase("tan")) {
					x = x.toLowerCase();
					if ((i-(x.length()-1)) >= 1 && ((type[i - (x.length())] == 'N') || (type[i - (x.length())] == 'C'))) {
						terms.add("*");
					}
					terms.add(x);
				} 
				else {
					throw new NumberFormatException();
				}
			} else if (term[i].equals(" ")) {	//removing space

			} else if (type[i] == 'B') {	//turning brackets into multiplication
				if (i >= 1 && ((type[i - 1] == 'N') || (type[i - 1] == 'C'))) {
					terms.add("*");
				}
				terms.add("(");
			} else if (type[i] == 'C') {	
				terms.add(")");
				if (i < type.length - 1 && (type[i + 1] == 'N')) {
					terms.add("*");
				}
			} else {
				terms.add(term[i]);
			}
		}
		return terms;
	}
}
