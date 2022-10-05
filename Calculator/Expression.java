package Calculator;

import java.util.LinkedList;

public class Expression {
	LinkedList<Object> toTerms(String problem){
		String[] term = problem.split("");
		String[] type = new String[term.length];
		for (int i = 0; i < term.length; i++) {
			char value = term[i].charAt(0);
			if (value > 47 & value < 58)		//[0 to 9]
				type[i] = "Number";  
			else if ((value > 64 & value < 91) || (value > 96 & value < 123))	//[A to Z] [a to z]
				type[i] = "Letter"; 
			else if (value == '.')
				type[i] = "Point"; 
			else if (value == '+')
				type[i] = "Plus"; 
			else if (value == '-')
				type[i] = "Minus"; 
			else if (value == '(' | value == '{' | value == '[')
				type[i] = "Open Bracket"; 
			else if (value == ')' | value == '}' | value == ']')
				type[i] = "Close Bracket"; 
			else
				type[i] = "Others"; 
		}

		LinkedList<Object> terms = new LinkedList<Object>();
		for (int i = 0; i < term.length; i++) {				
			if (type[i] == "Number") {						//joining numbers
				double number = Double.valueOf(term[i]);			
				if (i >= 1 && (type[i - 1] == "Minus")) {	//check for negative numbers
					number = -number;
					terms.removeLast();
					if (i >= 2 && (type[i - 2] == "Number" | type[i - 2] == "Close Bracket")) {
						number = -number;
						terms.add("-");
					}
				}
				if (i >= 1 && (type[i - 1] == "Plus")) {	//check for positive numbers
					terms.removeLast();
					if (i >= 2 && (type[i - 2] == "Number" | type[i - 2] == "Close Bracket")) {
						terms.add("+");
					}
				}
				while (i < term.length - 1 && (type[i + 1] == "Number")) {
					++i;
					number = number * 10 + (Double.valueOf(term[i]));
				}
				if (i < term.length - 1 && type[i + 1] == "Point") {   //check for decimal numbers
					int decimalPoint = 0;
					++i;
					while (i < term.length - 1 && (type[i + 1] == "Number")) {
						++i;
						++decimalPoint;
						number = number + (Double.valueOf(term[i]) / (10 * decimalPoint));
					}
				}

				terms.add(number);
			} else if (type[i] == "Letter") {			//joining letters
				String word = term[i];
				while (i < term.length - 1 && type[i + 1] == "Letter") {
					++i;
					word = word + term[i];
				}
				if (word.equalsIgnoreCase("pi")) {
					if ((i-(word.length()-1)) >= 1 && ((type[i - (word.length())] == "Number") || (type[i - (word.length())] == "Close Bracket") )) {
						terms.add("*");
					}
					terms.add(3.1415);
					if ((i < type.length-1) && ((type[i + 1] == "Number") || (type[i + 1] == "Open Bracket"))) {
						terms.add("*");
					}
				} else if (word.equalsIgnoreCase("sqrt") | word.equalsIgnoreCase("cbrt") | word.equalsIgnoreCase("log")
						| word.equalsIgnoreCase("sin") | word.equalsIgnoreCase("cos") | word.equalsIgnoreCase("tan")) {
					word = word.toLowerCase();
					if ((i-(word.length()-1)) >= 1 && ((type[i - (word.length())] == "Number") || (type[i - (word.length())] == "Close Bracket"))) {
						terms.add("*");
					}
					terms.add(word);
				} 
				else {
					throw new NumberFormatException();
				}
			} else if (term[i].equals(" ")) {	//removing space

			} else if (type[i] == "Open Bracket") {	
				if (i >= 1 && ((type[i - 1] == "Number") || (type[i - 1] == "Close Bracket"))) {		//turning brackets into multiplication
					terms.add("*");
				}
				terms.add("(");
			} else if (type[i] == "Close Bracket") {	
				terms.add(")");
				if (i < type.length - 1 && (type[i + 1] == "Number")) {
					terms.add("*");
				}
			} else {
				terms.add(term[i]);
			}
		}
		return terms;
	}
}
