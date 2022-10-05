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
			double ans = solve(terms);
			if ((int) ans == ans)								//turning the double value into integer
				System.out.println((int) ans);
			else
				System.out.println(ans);
		} else {
			System.out.print("Invalid problem.. try again");
		}

	}

	private Double solve(List<Object> terms) {
		int index, j;
		Double ans;
		List<Object> innerTerms = new LinkedList<Object>();
		while (terms.size() > 1) {
																//Ordering as per precedence
			if (terms.contains("(")) {							//1-> Brackets
				index = terms.indexOf("(");
				j = terms.indexOf(")");
				innerTerms = terms.subList(index + 1, j + 1);
				if (innerTerms.contains("(")) {
					index = innerTerms.indexOf("(");
					j = innerTerms.indexOf(")");
					ans = solve(innerTerms.subList(index + 1, j));
					innerTerms.remove("(");
					innerTerms.remove(")");
				} else {
					ans = solve(terms.subList(index + 1, j));
					terms.remove("(");
					terms.remove(")");
				}

			} else if (terms.contains("cbrt")) {						//2-> Cube root
				index = terms.indexOf("cbrt");
				ans = Math.cbrt(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("sqrt")) {						//3-> Square root
				index = terms.indexOf("sqrt");
				ans = Math.sqrt(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("log")) {							//4-> log
				index = terms.indexOf("log");
				ans = Math.log10(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("sin")) {							//5-> sin
				index = terms.indexOf("sin");
				ans = Math.sin(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("cos")) {							//6-> cos
				index = terms.indexOf("cos");
				ans = Math.cos(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("tan")) {							//7-> tan
				index = terms.indexOf("tan");
				ans = Math.tan(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("%")) {							//8-> percentage
				index = terms.indexOf("%");
				ans = (getValue(index - 1, terms)) / 100;
				toSimplify(terms, index, 2, ans);
			} else if (terms.contains("!")) {							//9-> Factorial
				index = terms.indexOf("!");
				ans = factorial(getValue(index - 1, terms));
				toSimplify(terms, index, 2, ans);
			} else if (terms.contains("^")) {							//10-> Power
				index = terms.indexOf("^");
				ans = Math.pow(getValue(index - 1, terms), getValue(index + 1, terms));
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("/")) {							//11-> Divide
				index = terms.indexOf("/");
				ans = getValue(index - 1, terms) / getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("*")) {							//12-> Multiplication
				index = terms.indexOf("*");
				ans = getValue(index - 1, terms) * getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("-")) {							//13-> Subtraction
				index = terms.indexOf("-");
				ans = getValue(index - 1, terms) - getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("+")) {							//14-> Addition
				index = terms.indexOf("+");
				ans = getValue(index - 1, terms) + getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			}
		}
		return (Double) terms.get(0);

	}

	private Double getValue(int index, List<Object> terms) {				
		return Double.valueOf((terms.get(index)).toString());
	}

	private Double factorial(double n) {
		double a = 1.0;
		while (n > 0) {
			a *= n;
			n--;
		}
		return a;
	}

	private void toSimplify(List<Object> a, int i, int n, Double ans) {			//eliminating solved terms
		a.add(i - 1, ans);
		while (n-- > 0) {
			a.remove(i);
		}

	}
}
