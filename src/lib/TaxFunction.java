package lib;

public class TaxFunction {
	private final int pajak = 5 / 100;
	private final int single = 54000000;
	private final int married = 4500000;
	private final int kids = 3;
	private final int marriedkids = kids * 4500000;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		return printinfo(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible, isMarried,
				numberOfChildren);

	}

	private static int printinfo(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {
		int tax = 0;
		if (isMarried) {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible
					- (54000000 + 4500000 + (numberOfChildren * 1500000))));
		} else {
			tax = (int) Math.round(
					0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
		}

		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}
	}
}
