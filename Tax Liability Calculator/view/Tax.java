package view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tax {
	Scanner scan = new Scanner(System.in);

	void begin() {
		System.out.println("Tax Liability for Assessment Year -> 2023-24");
		int taxPayer = getTaxPayer();
		double taxLiability = 0;
		switch (taxPayer) {
		case 1:
			taxLiability = individual();
			break;
		case 2:
			taxLiability = huf();
			break;
		case 3:
			taxLiability = aop();
			break;
		case 4:
			taxLiability = domesticCompany();
			break;
		case 5:
			taxLiability = foreignCompany();
			break;
		case 6:
			taxLiability = firm();
			break;
		case 7:
			taxLiability = firm();
			break;
		case 8:
			taxLiability = cooperativeSociety();
			break;
		}
		System.out.println("\nTax Liability -> " + Math.round(taxLiability/10)*10);
	}

	private double cooperativeSociety() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));
		System.out.println("\nWhether opting for taxation under Section 115BAD?");
		String scheme = getInput("Yes/No : ", "Yes|yes|no|No");
		if (scheme.equalsIgnoreCase("yes")) {
			if (income < 10000001)
				return income * 22 * 104 / 10000;
		}
		double tax = getTax(income, 0, 10001, 10) + getTax(income, 10000, 20001, 20) + getTax(income, 20000, 30);
		return tax * 104 / 100;

	}

	private double domesticCompany() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));
		System.out.println("""
				1) Total turnover or gross receipt of the company in
				   the previous year 2019-20 does not exceed Rs.400 crores.

				2) opted to section 115BA

				3) opted to section 115BAA

				4) opted to section 115BAB
				""");
		int option = Integer.valueOf(getInput("option : ", "^[1-4]$"));
		switch (option) {
		case 1:
			return income * 25 * 104 / 10000;
		case 2:
			return income * 25 * 104 / 10000;
		case 3:
			return income * 22 * 104 * 110 / 1000000;
		case 4:
			return income * 15 * 110 * 104 / 1000000;
		default:
			return 0;
		}
	}

	private double foreignCompany() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));

		return income * 40 * 104 / 10000;
	}

	private double firm() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));

		return income * 30 * 104 / 10000;

	}

	private double huf() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));
		System.out.println("\nWhether opting for taxation under Section 115BAC?");
		String scheme = getInput("Yes/No : ", "Yes|yes|no|No");
		return getIncomeTax(income, 20, scheme);
	}

	private double aop() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));
		return getIncomeTax(income, 20, "no");
	}

	private double individual() {
		double income = Double.valueOf(getInput("net taxable income : Rs.", "[0-9]+"));
		int age = Integer.valueOf(getInput("age : ", "1[89]|[2-9][0-9]"));
		System.out.println("\nWhether opting for taxation under Section 115BAC?");
		String scheme = getInput("Yes/No : ", "Yes|yes|no|No");
		return getIncomeTax(income, age, scheme);

	}

	private double getIncomeTax(double income, int age, String scheme) {
		double tax = 0;
		if (scheme.equalsIgnoreCase("no")) {
			if (age < 60)
				tax = getTax(income, 250000, 500001, 5);
			else if (age < 80)
				tax = getTax(income, 300000, 500001, 5);
			tax += getTax(income, 500000, 1000001, 20) + getTax(income, 1000000, 30);
		} else {
			tax = getTax(income, 250000, 500001, 5) + getTax(income, 500000, 750001, 10)
					+ getTax(income, 750000, 1000001, 15) + getTax(income, 1000000, 1250001, 20)
					+ getTax(income, 1250000, 1500001, 25) + getTax(income, 1500000, 30);
		}
		if (income < 500001) // rebate
			tax = 0;
		if (income > 50000000)
			tax = getSurcharge(income, tax, 50000000, 37, 62.5, scheme, age);
		else if (income > 20000000)
			tax = getSurcharge(income, tax, 20000000, 25, 65.5, scheme, age);
		else if (income > 10000000)
			tax = getSurcharge(income, tax, 10000000, 15, 67, scheme, age);
		else if (income > 5000000)
			tax = getSurcharge(income, tax, 5000000, 10, 70, scheme, age);
		else
			tax += (tax * 4 / 100); // Health and Education Cess
		return tax;
	}

	private double getSurcharge(double income, double tax, double limit, double surchargeRate, double incomeRate, String scheme,
			int age) {
		double marginalIncome = 0, marginalTax = 0, taxWithSurcharge = 0, surcharge = 0;
		if (income > limit) {
			surcharge = (tax * surchargeRate / 100);
			taxWithSurcharge = (tax + surcharge) + ((tax + surcharge) * 4 / 100);
			marginalTax = taxWithSurcharge - getIncomeTax(limit, age, scheme);
			marginalIncome = income - limit;
		}
		if (marginalIncome < marginalTax)
			return tax + surcharge + (marginalIncome * incomeRate / 100);
		else
			return taxWithSurcharge;
	}

	private double getTax(double income, double lowerLimit, double rate) {
		double slab;
		if (income > lowerLimit) {
			slab = income - lowerLimit;
			return slab * rate / 100;
		}
		return 0;
	}

	private double getTax(double income, double lowerLimit, double higherLimit, double rate) {
		double slab;
		if (income > lowerLimit) {
			if (income < higherLimit) {
				slab = income - lowerLimit;
			} else {
				slab = higherLimit - lowerLimit - 1;
			}
			return slab * rate / 100;
		}
		return 0;
	}

	private int getTaxPayer() {
		System.out.println("""
				Choose Tax Payer
				1) Individual
				2) Hindu Undivided Family
				3) Association Of Person / Body Of Individuals
				4) Domestic Company
				5) Foreign Company
				6) Partnership Firms
				7) Limited Liability Partnership
				8) Co-operative Society
				""");
		return Integer.valueOf(getInput("option : ", "^[1-8]$"));
	}

	private String getInput(String field, String regex) {
		String input;
		System.out.print("\nEnter " + field);
		input = scan.nextLine();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches())
			return input;
		else {
			System.out.println("Invalid input!!");
			return getInput(field, regex);
		}
	}
}
