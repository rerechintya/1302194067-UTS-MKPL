package lib;

public class TaxFunction {
	private static final int pajak = 5 / 100;
	private static final int single = 54000000;
	private static final int married = 4500000;
	private static final int kids = 3;
	private static final int bulan = 12;
	private static final int marriedkids = kids * 4500000;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		int tax = 0;

		if (numberOfMonthWorking > bulan) {
			System.err.println("More than 12 month working per year");
		}

		if (numberOfChildren > kids) {
			numberOfChildren = kids;
		}

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
