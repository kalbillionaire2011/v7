import java.util.Calendar;
import java.util.*;

public class DateInfo3 {
	 private int year;
	 private int month;
	 private int dayOfMonth;
	 private Calendar maturity_date;
	 
	 Calendar date = Calendar.getInstance();
	 
	 Calendar calendar = Calendar.getInstance(); 
	
	 
	 public DateInfo3(){
		 year = Calendar.YEAR;
		 month = Calendar.MONTH;
		 dayOfMonth= Calendar.DAY_OF_MONTH;
	 }
	 
	 
	 
	 //Getters accessors methods
	 public int getYear(){ return year;}
	 public int getMonth() { return month; }
	 public int getDayOfMonth() {return dayOfMonth; }
	 
	 //setters methods mutators 
	 public void setYear(int year) {
		//date.set(Calendar.YEAR,year);
		this.year = year;
	 }
	 public void setMonth(int month) {
		 this.month = month;
	 }
	 public void setDayOfMonth(int dayofmonths) {
		 this.dayOfMonth = dayofmonths;
	 }
	 public void setMaturity(Calendar maturity) {
		 this.maturity_date = maturity;
	 }
	 public Calendar getMaturity() {
	 //Maturity date 6,12,18 and 24 months
	 //if(maturity_date <= 24)
	 return maturity_date;
	 }
	 
	 public String toString() {
		 String output = this.dayOfMonth + "/" + this.month + "/" + this.year;
		 
		 return output;
	 }
	 
	
	/* public int compareCheckDate(int checkYear, int checkMonth, 
				int checkDay) {
			Calendar today = Calendar.getInstance();
			int thisYear = today.get(Calendar.YEAR);
			int thisMonth = today.get(Calendar.MONTH)+1;
			int thisDay = today.get(Calendar.DATE);
			int monthPassed = 0;
			monthPassed += (thisMonth - checkMonth);
			monthPassed += ((thisYear - checkYear)*12);
			if(checkDay > thisDay) {
				monthPassed --;
			}
			if(monthPassed >= 6) {
				return 1;//1 stands for the account is pass 6 months
			}else {
				return 0;//0 stands for the account did not pass 6 months
			}
*/
}
