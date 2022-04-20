package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee extends Data {

	private Data pribadiemployee;

	private Tanggalmasuk employee;

	private boolean isForeigner;
	private boolean gender; // true = Laki-laki, false = Perempuan

	private Gaji karyawaan;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(Data pribadiemployee,
			Tanggalmasuk employee, boolean isForeigner, boolean gender) {
		this.setPribadiemployee(pribadiemployee);
		this.setEmployee(employee);
		this.isForeigner = isForeigner;
		this.gender = gender;

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	public Data getPribadiemployee() {
		return pribadiemployee;
	}

	public void setPribadiemployee(Data pribadiemployee) {
		this.pribadiemployee = pribadiemployee;
	}

	public Tanggalmasuk getEmployee() {
		return employee;
	}

	public void setEmployee(Tanggalmasuk employee) {
		this.employee = employee;
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	public void setMonthlySalary(int grade, int monthlySalary) {

		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (5000000 * 1.5);
			}
		} else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (7000000 * 1.5);
			}
		}
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = pribadiemployee.getIdNumber();
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax(int monthWorkingInYear) {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();

		if (date.getYear() == employee.getYearJoined()) {
			monthWorkingInYear = date.getMonthValue() - employee.getMonthJoined();
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(karyawaan.getMonthlySalary(), karyawaan.getOtherMonthlyIncome(),
				employee.getMonthWorkingInYear(), karyawaan.getAnnualDeductible(),
				spouseIdNumber.equals(""), childIdNumbers.size());
	}

}
