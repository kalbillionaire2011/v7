
public class Check {
	private double checkamount;
	private DateInfo3 dateOfcheck;
	
	public Check() {
		checkamount = 0.0;
		dateOfcheck = new DateInfo3();
	}
	
	public Check(double checkamount) {
		this.checkamount = checkamount;
		dateOfcheck = new DateInfo3();
	}
	public double getCheckamount() {
		return checkamount;
	}
	public DateInfo3 dateOfcheck() {
		return dateOfcheck;
	}
	
	public void setDateOfcheck(int month, int day, int year) {
		dateOfcheck.setYear(year);
		dateOfcheck.setMonth(day);
		dateOfcheck.setDayOfMonth(month);
	}
}
