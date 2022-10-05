package Calculator;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.operate();

	}

	private void operate() {
		try (Scanner scan = new Scanner(System.in)) {
			Calculation calculation = new Calculation();
			System.out.println("Calculator opened");
			do {
				System.out.println("\n------------------------------------------------------------------------");
				System.out.println("| + | - | * | / | ^ | % | ! | pi | sqrt | cbrt | log | sin | cos | tan |");
				System.out.println("------------------------------------------------------------------------");
				System.out.println("Enter the problem..");
				try {
					String problem = scan.nextLine();
					calculation.calculate(problem);
				} catch (NumberFormatException e) {
					System.out.print("Invalid problem.. try again");
				}
				catch (IllegalArgumentException e) {
					System.out.print("Invalid problem.. try again");
				}
				catch (StringIndexOutOfBoundsException e) {
					System.out.print("Problem has no terms.. try again");
				}
				catch (ClassCastException e) {
					System.out.print("Invalid problem.. try again");
				}
				System.out.println("\nEnter 1 to Continue..");
			} while (scan.nextLine().equals("1"));
			System.out.print("\nCalculator closed");

		}
	}

}
