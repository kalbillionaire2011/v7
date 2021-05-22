import java.util.Calendar;
import java.util.*;
import java.io.*;

public class Account3 {
	
     private Depositor3 info1;
     private int accNum;
     private String acc_Typ;
     private double accBalance;
     private DateInfo3 date;
     
     //Parameter ConStructor
     public Account3(int accountNum,String acc_Typ,
                        double accBal,Depositor3 info2){
          this.acc_Typ = acc_Typ;
          accNum = accountNum;
          accBalance = accBal;
          info1 = info2;
          date = new DateInfo3();
     }
     //No Arg Constructor default
     public Account3(){
          accNum = 0;
          acc_Typ = "";
          accBalance = 0.00;
          info1 = new Depositor3();
     }
    
    //Getter methods accessors
     public int getAcctnum(){
          return accNum;
     }
     public String getAcctyp(){
          return acc_Typ;
     }
     public double getAccBal(){
          return accBalance;
     }
     public Depositor3 getDepositor(){
          return info1;
     }
     public double getBalance(){
          return accBalance;
     }
     public DateInfo3 getDate3() {
    	 return date;
     }
     
    //Setter methods mutators
     public void setAcctNum(int aact_nums){
          accNum = aact_nums;
     }
     public void setAccType(String acctys ){
          acc_Typ = acctys;
     }
     public void setBalance(double acctbal ){
          accBalance = acctbal;
     }
     public void setDepositor(Depositor3 nextInfo){
          info1 = nextInfo;
     }
     public void setDate3(int month,int dayofmonth,int year) {
    	 date = new DateInfo3();
    	 
         date.setMonth(month);
         date.setDayOfMonth(dayofmonth);
         date.setYear(year);
     }
     
     public void setDate3(DateInfo3 date) {
    	 this.date = date;
     }
     
     public void makeDeposit(double acctBal){
         accBalance += acctBal; 
     }
     public void makeWithdrawal(double acctWithdrawal){
          accBalance -= acctWithdrawal;
     }
     public int clearCheck(boolean isValid, Check check){ 
    	 
    	/*if( accBalance < check.getCheckamount() ) {
    		accBalance -=2.50;
    		return 1;
    	}
    	else if(acc_Typ == "Checking"
    			&& isValid) {
    		    accBalance -= check.getCheckamount();
    		    return 2;
    	 
    	}else 
    		{return 3;} */
    	
    	if( accBalance < check.getCheckamount() ) {
    		accBalance -=2.50;
    		return 1;
    	}
    	 if(acc_Typ == "Checking" && isValid) { 
    		    accBalance -= check.getCheckamount();
    		    return 2;
    	 }else if(acc_Typ == "Checking" || isValid) {
    		    accBalance -= check.getCheckamount();
    		    return 2;
    	 
    	}else 
    		{return 3;}
    	 
  /*  	 public double makeDeposit(double amountToDeposit){
    			if(amountToDeposit <= 0)
    			else if(accountType.equals("CD")&& date.compareMaturity() == 1)
    				return amountToDeposit;//exit with not past the maturity date
    			else{
    				accBalance += amountToDeposit;
    				if(accountType.equals("CD"))
    					changeMaturity(kybd);
    			}
    		}
    	*/	

    	/* public int makeDeposit(double amount, Scanner input){
    			if(amount <= 0)
    				return 1;//exit with invalid amount error
    			else if(status.equals("closed")) 
    				return 2;//exit with closed account error
    			else if(accountType.equals("CD")&& date.compareMaturity() == 1)
    				return 3;//exit with not past the maturity date
    			else{
    				balance += amount;
    				if(accountType.equals("CD"))
    					changeMaturity(input);
    			}
    			return 0;//exit with complete deposit
    		}
    	*/	
    		/*
    		 * Method makeWithdrawal()
    		 * Input:
    		 * 	amount - refer to the withdrawal amount
    		 *	input - refrence to the test input
    		 * Process: 
    		 *  	check if the amount is valid or not. If it is valid,
    		 *  check if the amount sufficient. If it is, do the withdrawal
    		 *  process
    		 * Output:
    		 * 	 	Return 0 for complete withdrawal
    		 * 		Return 1 for invalid amount
    		 * 		Return 2 for insufficient fund
    		 *		Return 3 for closed account
    		 *		Return 4 for account did not pass maturity date
    		 */
    /*		public int makeWithdrawal(double amount, Scanner input) {
    			if(amount <= 0)
    				return 1;//exit with invalid amount error
    			else if(balance < amount)
    				return 2;//exit with insufficient fund error
    			else if(status.equals("closed")) 
    				return 3;//exit with closed account error
    			else if(accountType.equals("CD")&& date.compareMaturity() == 1)
    				return 4;//exit with not past the maturity date
    			else{
    				balance -= amount;
    				if(accountType.equals("CD"))
    					changeMaturity(input);
    				
    			} */
    //			return 0;//exit with complete withdrawal
    		}

    		/* Method changeMaturity()
    		 * Input:
    		 *	input - refrence to the test input
    		 * Process:
    		 *	Ask the user to input the month to extend. It first check if the number
    		 *  user input is at the choice of either 6, 12, 18, 24. If it is, then call the
    		 *  dateInfo class to extend the maturity date.
    		 * 
    		 * OutPut:
    		 */
    /*		public void changeMaturity(Scanner input){
    			//ask user to input time to extend
    			System.out.println("Please enter time to extend"
    				+ "(6, 12, 18, 24 months only)");
    			int month = input.nextInt();
    			while(month!= 6 && month!= 12 && month!= 18 && month!=24) {
    				System.out.println("The time you entered can "
    					+ "not be processed, please re enter");
    				month = input.nextInt();
    			}
    			date.extendMaturity(month);
    		}*/
    		
    		/*
    		 * Method clearCheck()
    		 * Input:
    		 * 		amount - refer to the check amount
    		 * 		year - refer to the year in the check
    		 * 		month - refer to the month in the check
    		 * 		day - refer to the day in the check
    		 * Process: 
    		 *  	check if the amount is valid or not. If it is valid,
    		 *  check if it is within 6 months. If it is, check if the 
    		 *  amount is sufficient. If it is, then do the clear Check process
    		 * Output:
    		 * 	 	Return 0 for complete process
    		 * 		Return 1 for invalid amount
    		 * 		Return 2 for exceed 6 months
    		 * 		Return 3 for insufficient fund
    		 */
    	/*	public int clearCheck(double amount, int year, int month, int day) {
    			if(amount <= 0)
    				return 1;//exit with invalid amount error
    			else if(status.equals("closed")) 
    				return 2;//exit with closed account error
    			else if(date.compareCheckDate(year, month, day) == 1)
    				return 3;//exit with day past 6 month error
    			else if(balance < amount)
    				return 4;//exit with insufficient fund error
    			else
    				balance -= amount;
    			return 0;
    		}
*/
    		
     }
    
     

