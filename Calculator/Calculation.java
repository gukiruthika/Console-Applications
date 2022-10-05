package Calculator;

import java.util.LinkedList;
import java.util.List;

public class Calculation {
	void calculate(String problem) {
		Expression expression = new Expression();
		Validation validation = new Validation();
		LinkedList<Object> terms = new LinkedList<Object>();
		if (validation.check(problem)) {						//Validating the problem
			terms = expression.toTerms(problem);				//turning the problem into expression
			double result = solve(terms);
			if ((int) result == result)								//turning the double value into integer
				System.out.println((int) result);
			else
				System.out.println(result);
		} else {
			System.out.print("Invalid problem.. try again");
		}

	}

	private Double solve(List<Object> terms) {
		int index, end;
		Double result;
		List<Object> innerTerms = new LinkedList<Object>();
		while (terms.size() > 1) {
																//Ordering as per precedence
			if (terms.contains("(")) {							//1-> Brackets
				index = terms.indexOf("(");
				end = terms.indexOf(")");
				innerTerms = terms.subList(index + 1, end + 1);
				if (innerTerms.contains("(")) {
					index = innerTerms.indexOf("(");
					end = innerTerms.indexOf(")");
					result = solve(innerTerms.subList(index + 1, end));
					innerTerms.remove("(");
					innerTerms.remove(")");
				} else {
					result = solve(terms.subList(index + 1, end));
					terms.remove("(");
					terms.remove(")");
				}

			} else if (terms.contains("cbrt")) {						//2-> Cube root
				index = terms.indexOf("cbrt");
				result = Math.cbrt(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, result);
			} else if (terms.contains("sqrt")) {						//3-> Square root
				index = terms.indexOf("sqrt");
				result = Math.sqrt(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, result);
			} else if (terms.contains("log")) {							//4-> log
				index = terms.indexOf("log");
				result = Math.log10(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, result);
			} else if (terms.contains("sin")) {							//5-> sin
				index = terms.indexOf("sin");
				result = Math.sin(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, result);
			} else if (terms.contains("cos")) {							//6-> cos
				index = terms.indexOf("cos");
				result = Math.cos(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, result);
			} else if (terms.contains("tan")) {							//7-> tan
				index = terms.indexOf("tan");
				result = Math.tan(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, result);
			} else if (terms.contains("%")) {							//8-> percentage
				index = terms.indexOf("%");
				result = (getValue(index - 1, terms)) / 100;
				toSimplify(terms, index, 2, result);
			} else if (terms.contains("!")) {							//9-> Factorial
				index = terms.indexOf("!");
				result = factorial(getValue(index - 1, terms));
				toSimplify(terms, index, 2, result);
			} else if (terms.contains("^")) {							//10-> Power
				index = terms.indexOf("^");
				result = Math.pow(getValue(index - 1, terms), getValue(index + 1, terms));
				toSimplify(terms, index, 3, result);
			} else if (terms.contains("/")) {							//11-> Divide
				index = terms.indexOf("/");
				result = getValue(index - 1, terms) / getValue(index + 1, terms);
				toSimplify(terms, index, 3, result);
			} else if (terms.contains("*")) {							//12-> Multiplication
				index = terms.indexOf("*");
				result = getValue(index - 1, terms) * getValue(index + 1, terms);
				toSimplify(terms, index, 3, result);
			} else if (terms.contains("-")) {							//13-> Subtraction
				index = terms.indexOf("-");
				result = getValue(index - 1, terms) - getValue(index + 1, terms);
				toSimplify(terms, index, 3, result);
			} else if (terms.contains("+")) {							//14-> Addition
				index = terms.indexOf("+");
				result = getValue(index - 1, terms) + getValue(index + 1, terms);
				toSimplify(terms, index, 3, result);
			}
		}
		return (Double) terms.get(0);

	}

	private Double getValue(int index, List<Object> terms) {				
		return Double.valueOf((terms.get(index)).toString());
	}

	private Double factorial(double number) {
		double result = 1.0;
		while (number > 0) {
			result *= number;
			number--;
		}
		return result;
	}

	private void toSimplify(List<Object> terms, int index, int count, Double result) {			//eliminating solved terms
		terms.add(index - 1, result);
		while (count-- > 0) {
			terms.remove(index);
		}

	}
}
