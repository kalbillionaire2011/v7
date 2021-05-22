/**/
//HoMeWork#3

import java.io.*;
//import java.time.Year;
//needed in order to use the StringTokenizer class
//this is used for Processing ArrayList
//in java.
//import java.util.ArrayList;
import java.util.*;

public class My_H_W_03 {
	 public static void main(String[] args) throws IOException
	 {
	      //created a new object for bank class
	      Bank3 bankStuf = new Bank3();
	     
	      
	     //declared variables
	     char choice;         //menu item selected
	     boolean not_done = true;      //loop control flag
	     
	     
	     // open input test cases file
	     //File testFile = new File("mytestcases3.txt");
	    File testFile = new File
	    ("F:\\Advance Java\\BankAccounts\\code#03\\src\\mytestcases3.txt");
	     //create Scanner object
	   Scanner kybd = new Scanner(testFile);
	//  Scanner kybd = new Scanner(System.in);

	     // open the output file
	     //PrintWriter outFile = new PrintWriter("myoutput3.txt");
	     PrintWriter outFile = new PrintWriter
	     ("F:\\Advance Java\\BankAccounts\\code#03\\src\\myoutput3.txt");
	    // PrintWriter outFile = new PrintWriter(System.out);
	    // outFile.println();
	     
	     /* first part */
	     /* fill and print initial database */
	     readAccts(bankStuf);
	     printAccts(bankStuf,outFile);
	    
	     
	     /* second part */
	     /* prompts for a transaction and then */
	     /* call functions to process the requested transaction */
	     do {
	         menu();
	         choice = kybd.next().charAt(0);
	         switch(choice)
	         {
	             case 'q':
	             case 'Q':
	                 not_done = false;
	                 printAccts(bankStuf,outFile);
	                 break;
	             case 'b':
	             case 'B':
	                 balance(bankStuf,outFile,kybd);
	                 break;
	             case 'i':
	             case 'I':
	            	 accountInfo(bankStuf,outFile,kybd);
	              break;
	
	             case 'd':
	             case 'D':
	                 deposit(bankStuf,outFile,kybd);
	                 break;
	             case 'w':
	             case 'W':
	                 withdrawal(bankStuf,outFile,kybd);
	                 break;
	            case 'c':
	            case 'C':
	                 clearCheck(bankStuf,outFile,kybd);
	                 break; 
	             case 'n':
	             case 'N':
	            	 newAcct(bankStuf,outFile,kybd);
	                 break;
	             case 'x':
	             case 'X':
	                 deleteAcct(bankStuf,outFile,kybd);
	                 
	                 break;
	             default:
	                  outFile.println("Error: " + choice 
	                                   + " is an invalid selection -  try again");
	                  outFile.println();
	                  outFile.flush();
	                 break;
	      }
	               // give user a chance to look at output before printing menu
	         //pause(kybd);
	     } while (not_done);
	     
	     //close the output file
	     outFile.close();
	     
	     //close the test cases input file
	     kybd.close();
	     
	     System.out.println();
	     System.out.println("The program is terminating");
	 }
	 
	 /* Method readAccts() - using a StringTokenizer object */
	 /* Method readAccts()
	  * Input:
	  *  acctNumArray - reference to array of account numbers
	  *  balanceArray - reference to array of account balances
	  *  maxAccts - maximum number of active accounts allowed
	  * Process:
	  *  Reads the initial database of accounts and balances
	  * Output:
	  *  Fills in the initial account and balance arrays and returns the number 
	  * of active accounts
	  */
	 public static int readAccts(Bank3 bankStuf) 
	     throws IOException
	 {
	     //local variables
	     int count = 0;
	     String line,tempstr;
	     
	     
	     // open database input file
	     //create File object
	     //File dbFile = new File("myinput3.txt");
	     File dbFile = new File("F://Advance Java//BankAccounts//code#03//src//myinput3.txt");
	     
	     //Create a Scanner object to read the input file
	     Scanner sc = new Scanner(dbFile);
         
         
	     while(sc.hasNext())
	     {
		     //create BankAccount,Depositor,Name objects
		     Name3 names = new Name3();
		     Depositor3 customers = new Depositor3();
		     Account3 accountInfo = new Account3();
		     Check check = new Check();
		     
		     //read next line of data 
		     line = sc.nextLine();
		     StringTokenizer tokenizer = new StringTokenizer(line);
		     //
			 DateInfo3 accountDate = new DateInfo3();
		     accountInfo.setDate3(accountDate);
		         
		     //extract the data from the line read
		     names.setFirst(tokenizer.nextToken());       //extract first name
		     names.setLast(tokenizer.nextToken());      //extract last name
		     customers.setSSN(tokenizer.nextToken());
		     customers.setName(names);
		     tempstr = tokenizer.nextToken();
		     accountInfo.setAcctNum(Integer.parseInt(tempstr));
		     accountInfo.setAccType(tokenizer.nextToken());
		     if (accountInfo.getAcctyp().equals("CD")) {
		    	 Calendar tempMaturity = Calendar.getInstance();
	
		    	 tempMaturity.set(Calendar.MONTH, Integer.parseInt(tokenizer.nextToken()));
		    	 tempMaturity.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokenizer.nextToken()));
		    	 tempMaturity.set(Calendar.YEAR, Integer.parseInt(tokenizer.nextToken()));
		    	 accountInfo.getDate3().setMaturity(tempMaturity);
		     }
		     accountInfo.setBalance(Double.parseDouble(tokenizer.nextToken()));
		     accountInfo.setDepositor(customers);
		     
		     //set the Array elements
		     bankStuf.openNewAcct(accountInfo);
		  }
	    
	    //close the input file for BankAccounts
	    sc.close();
	     
	    //return the account number count
	    return count;
	    
	 }

	 /* Method printAccts:
	  * Input:
	  *  reading from the file to the account Array
	  *  num_accts - number of active accounts
	  *  outFile - reference to the output file
	  * Process:
	  *  Prints the database of accounts Array and balances personal informtion
	  * Output:
	  *  Prints the database of accounts and balances
	 */
	 public static void printAccts(Bank3 bankStuf,
	         PrintWriter outFile)
	 {
	     //create BankAccount,Depositor objects
	     Name3 names = new Name3();
	     Depositor3 customers = new Depositor3();
	     Account3 accountInfo = new Account3();
	     
	     //System.out.println();
	     outFile.println();
	     outFile.println("\t\t \t Bank Accounts  DataBase");
	     outFile.println();
	     //Printing table of colums of headings
	     outFile.println("Names\t   \t\t SocialSecurityNum\t" 
	                         +"AccountNum\tAccountType\t" + "  AccountBal");
	     for (int count = 0; count < bankStuf.getNumAccts(); count++)
	     {
	         names = bankStuf.getAcct(count).getDepositor().getName();
	         outFile.printf("%-10s ",names.getFirst());
	         outFile.printf("%-10s",names.getLast());
	         customers = bankStuf.getAcct(count).getDepositor();
	         outFile.print("\t  "+customers.getSSN());
	         accountInfo = bankStuf.getAcct(count);
	         outFile.print("\t\t    "+accountInfo.getAcctnum());
	         outFile.printf("\t  %9s",accountInfo.getAcctyp());
	         outFile.printf("\t  %9.2f",accountInfo.getAccBal());
	         outFile.println();
	      }
	      outFile.println();

	     //flush the output file
	     outFile.flush();
	 }
	 
	 /* Method menu()
	  * Input:
	  *  none
	  * Process:
	  *  Prints the menu of transaction choices
	  * Output:
	  *  Prints the menu of transaction choices
	  */
	 public static void menu()
	 {
	     System.out.println();
	     System.out.println("Select one of the following transactions:");
	     System.out.println("\t****************************");
	     System.out.println("\t    List of Choices         ");
	     System.out.println("\t****************************");
	     System.out.println("\t     W -- Withdrawal");
	     System.out.println("\t     D -- Deposit");
	     System.out.println("\t     C -- Clear Check");
	     System.out.println("\t     N -- New Account");
	     System.out.println("\t     B -- Balance Inquiry");
	     System.out.println("\t     I -- Account Inquiry");
	     System.out.println("\t     X -- Delete Account");
	     System.out.println("\t     Q -- Quit");
	     System.out.println();
	     System.out.print("\tEnter your selection: ");
	 }
	 
	 /* Method balance:
		* Input:
		*  account Array 
		*  num_accts - number of active accounts
		*  outFile - reference to output file
		*  kybd - reference to the "test cases" input file
		* Process:
		*  Prompts for the requested account
		*  Calls findAcct() to see if the account exists
		*  If the account exists, the balance is printed
		*  Otherwise, an error message is printed
		* Output:
		*  If the account exists, the balance is printed
		*  Otherwise, an error message is printed
		*/
	 public static void balance (Bank3 bankStuf,PrintWriter outFile, Scanner kybd) {
		 int requestedAccount,index;
		 
		//prompt for account number
         System.out.println();
         System.out.print("Enter the account number: "); 
         requestedAccount = kybd.nextInt();
         //read-in the account number
         //call findAcct to search if requestedAccount exists
         index = bankStuf.findAcct(requestedAccount);
     if(index == -1)    //invalid account
     {
          outFile.println("Transaction Requested: Balance Inquiry");
          outFile.println("Error: Account number "
                     + requestedAccount + " does not exists");
 
     }
     else //valid account
     {
         outFile.println("Transaction Requested: Balance Inquiry");
         outFile.println("Account Number: " + requestedAccount);
         outFile.printf("Current Balance: $%.2f"
                          ,bankStuf.balanceOf(requestedAccount));
         outFile.println();
     }
     	 outFile.println();

         outFile.flush();    //flush the output buffer
	 }
	 
	 /* Method deposit:
		* Input:
		*  account Array - array 
		*  numAccts - number of active accounts
		*  outFile - reference to the output file
		*  kybd - reference to the "test cases" input file
		* Process:
		*  Prompts for the requested account
		*  Calls findacct() to see if the account exists
		*  If the account exists, prompts for the amount to deposit
		*  If the amount is valid, it makes the deposit and prints the new balance
		*  Otherwise, an error message is printed
		* Output:
		*  For a valid deposit, the deposit transaction is printed
		*  Otherwise, an error message is printed
		*/
public static void deposit(Bank3 bankStuf,PrintWriter outFile, Scanner kybd) {
	 int requestedAccount,index;
	 int month,day,dayofmonth;
	 double  amountToDeposit;
	 Account3 accountInfo = new Account3();
	 
	 System.out.println();
     System.out.print("Enter the account number: ");
     //prompt for account number
     requestedAccount = kybd.nextInt(); 
     //read-in the account number
     //call findAcct to search if requestedAccount exists
    
     index = bankStuf.findAcct(requestedAccount);
if(index == -1) //invalid account
{
   outFile.println("Transaction Requested: Deposit");
   outFile.println("Error: Account number " 
                 + requestedAccount + " does not exist");
}
else      //valid account
{
	//read-in new Maturity Date 
/*	System.out.print("Enter New Maturity Date amount to deposit: "); 
	   month = kybd.nextInt();
	   day =  kybd.nextInt();
	   dayofmonth = kybd.nextInt();
	   */
   System.out.print("Enter amount to deposit: "); 
   //prompt for amount to deposit
   amountToDeposit = kybd.nextDouble();
   //read-in the amount to deposit
  //  bankStuf.getAcct(index).makeDeposit(amountToDeposit);
   
   if(amountToDeposit <= 0.00 )  {
   //invalid amount to deposit
	   outFile.println("Transaction Requested: Deposit");
	   outFile.println("Account Number: " + requestedAccount);
	   outFile.printf("Error: $%.2f is an invalid amount"
	                 , amountToDeposit);
	   outFile.println();
   } else {
	   outFile.println("Transaction Requested: Deposit");
	   outFile.println("Account Number: " + requestedAccount);
	   outFile.printf("Old Balance: $%.2f"
	                 , bankStuf.balanceOf(requestedAccount));
	   outFile.println();
	   outFile.println("Amount to Deposit: $" 
	                  + String.format("%.2f" , amountToDeposit));
	   accountInfo = bankStuf.getAcct(index);
	   accountInfo.setBalance(accountInfo.getAccBal()
	                            +amountToDeposit);  
	   outFile.printf("New Balance: $%.2f", accountInfo.getAccBal());
	   outFile.println();
   }
   
   
  /*amount = input.nextDouble(); // read-in the amount
	result = bank.getAcct(index).makeDeposit(amount, input);
	
	outFile.println("Account Number: " + requestedAccount);
	outFile.println("Account Type: "+ 
			bank.getAcct(index).getType());
	outFile.println("Amount to Deposit: $" + amount);
	outFile.printf("Old Balance: $%.2f", oldBalance);
	outFile.println();
	if (result == 1) { //invalid amount
		outFile.printf("Error: $%.2f is an invalid amount", amount);
		outFile.println();
		trans = new Transaction("Deposit", amount, "Failed", 
				"invalid amount");
		bank.getAcct(index).setTransaction(trans);
	} else if(result == 2) {//closed account
		outFile.println("Error: Account is closed");
		trans = new Transaction("Clear Check", amount, "Failed",
				"account is closed");
	} else if(result == 3){//did not past maturity date
		outFile.println("Maturity date: "+ 
			bank.getAcct(index).getDate().getFull());
		outFile.println("Error: Account did not past mauturity date");
		trans = new Transaction("Deposit", "Failed", 
			"did not past maturity date");
		bank.getAcct(index).setTransaction(trans);
	}else {
		outFile.printf("New Balance: $%.2f", 
				bank.getAcct(index).getBalance());
		if(bank.getAcct(index).getType().equals("CD"))
			outFile.println("New mauturity date: " 
				+ bank.getAcct(index).getDate().getFull());
		outFile.println();
		trans = new Transaction("Deposit", "Success", "");
		bank.getAcct(index).setTransaction(trans);
	}
*/
}
	outFile.println();
}

//this method withdraws the amount of money a person wants
//and tells the user the error they made while doing that.
//also for the funds they want too.
public static void withdrawal(Bank3 bankStuf,PrintWriter outFile, Scanner kybd) {
	int requestedAccount,index;
	 double amountToWithdraw = -1;
	 int month;
	 Account3 accountInfo = new Account3();
	 DateInfo3 date = new DateInfo3();
	 boolean isMature = true;
	 Calendar c1 = Calendar.getInstance();
	 
	System.out.println();
    System.out.print("Enter the account number: ");
    //prompt for account number
    requestedAccount = kybd.nextInt(); 
    //read-in the account number
    
    //
     accountInfo.setDate3(date);

    //call findAcct to search if requestedAccount exists
   index = bankStuf.findAcct(requestedAccount);

if (index == -1)  //invalid account
{
  outFile.println("Transaction Requested: Withdrawal");
  outFile.println("Error: Account number " + requestedAccount 
                + " does not exist");
}
else    //valid account
{
	accountInfo = bankStuf.getAcct(index);
	if(bankStuf.getAcct(index).getAcctyp().equals("CD")) {
//		System.out.println("associated maturity: " + accountInfo.getDate3().getMaturity());
//		System.out.println("todays date: " + c1);
		//System.out.println(accountInfo.getDate3().getMaturity());
		isMature = accountInfo.getDate3().getMaturity().before(c1);
		//System.out.println(accountInfo);
		// Check account maturity
		//accountInfo.getDate3().getMaturity().add
	}
	//if its maturitor withdraw
    if (isMature) {
    	System.out.print("Enter amount to withdraw: "); 
    	//prompt for amount to withdraw
    	amountToWithdraw = kybd.nextDouble();
    	//read-in the amount to withdraw
    	System.out.println(isMature);
    }


		if (amountToWithdraw <= 0.00 && !bankStuf.getAcct(index).getAcctyp().equals("CD") )                  
		{
			//invalid amount to withdraw
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Error: $%.2f is an invalid amount",
					amountToWithdraw);
			outFile.println();
		}
		if(bankStuf.getAcct(index).getAcctyp().equals("CD") && !isMature) 
		{
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Account Number: " + requestedAccount);
			outFile.print("Account not pass Maturity date yet!! ");
			outFile.println();
			
		}
	//valid amount to withdraw
   accountInfo = bankStuf.getAcct(index);
   if(amountToWithdraw > 0.00 && 
		   amountToWithdraw <= accountInfo.getAccBal() && isMature) 
   {
	   outFile.println("Transaction Requested: Withdrawal");
	   outFile.println("Account Number: " + requestedAccount);
	   outFile.printf("Old Balance: $%.2f", accountInfo.getAccBal());
	   outFile.println();
	   outFile.println("Amount to Withdraw: $" + String.format("%.2f" , 
                               amountToWithdraw));
      accountInfo.makeWithdrawal(amountToWithdraw);
      //make the withdrawal
      bankStuf.setAcct(accountInfo, index);
      outFile.printf("New Balance: $%.2f", accountInfo.getAccBal());
      outFile.println();
   }

	if (bankStuf.getAcct(index).getAcctyp().equals("CD")
			&& isMature) {
		//Date maturity = ; //mkw maturity
		Calendar maturity = Calendar.getInstance();
		 
    	//read in New Maturity Date for Withdrawal
    	System.out.print("Enter Maturity Date amount to Withdrawal: ");
    	outFile.println("Enter a New Maturity month choices are 6,12,18 and 24");
    	month = kybd.nextInt();
    	maturity.add(Calendar.MONTH, month);
    	accountInfo.getDate3().setMaturity(maturity);
    	//accountInfo.setDate3(maturity.get(Calendar.MONTH),maturity.get(Calendar.DAY_OF_MONTH),maturity.get(Calendar.YEAR));
    	outFile.println("Your New Date of Maturity is ");
    	outFile.println((maturity.get(Calendar.MONTH)+1) + "/" + maturity.get(Calendar.DAY_OF_MONTH) 
    	+ "/" + maturity.get(Calendar.YEAR) );
		}
	}
	outFile.println();
}
	 //this method test to see if a check is valid or not
	 /*if the check is not then account owner is charged a $2.50 service Fee*/
	 public static void clearCheck(Bank3 bankStuf,PrintWriter outFile, Scanner kybd) 
	 {
		 int requestedAccount,index,year,month,dayofmonth,message;
		 double amountToWithdraw;
		 Account3 accountInfo = new Account3();
		 Check check = new Check();
		 
		 Calendar todays_date = Calendar.getInstance(); 
		
		 Calendar dateOfcheck = Calendar.getInstance(),
				  tForesight = Calendar.getInstance(),
				  tHindsight = Calendar.getInstance();
		 long anchor, future, past;
		
				 
		
		 System.out.println();
         System.out.print("Enter the account number: ");   
         //prompt for account number
         requestedAccount = kybd.nextInt();  //read-in the account number
         
         index = bankStuf.findAcct(requestedAccount);
         //read-in the amount to withdraw
         System.out.println("Please Enter Check date: ");

         year = kybd.nextInt();
         month = kybd.nextInt();
         dayofmonth = kybd.nextInt();
         
         check.setDateOfcheck(month, dayofmonth, year);
         
         dateOfcheck.set(Calendar.YEAR,year);
         dateOfcheck.set(Calendar.MONTH,month);
         dateOfcheck.set(Calendar.DAY_OF_MONTH,dayofmonth);
         
         tForesight.add(Calendar.MONTH, 6);
         tHindsight.add(Calendar.MONTH, -6);
         future = tForesight.getTimeInMillis();
         past = tHindsight.getTimeInMillis();
         anchor = Math.abs(todays_date.getTimeInMillis() - dateOfcheck.getTimeInMillis());
         
         boolean isValid = anchor < future && anchor < past;
         
       //  DateInfo3 date = new DateInfo3();
         
         //date.compareDate(month,dayofmonth,year);
       //  accountInfo.setDate3( month, dayofmonth, year);
         
         System.out.print("Enter amount to withdraw from Check: "); 
         //prompt for amount to withdraw
         amountToWithdraw = kybd.nextDouble();
         
         /*get number from user while
         * looking through the database if that account exists*/
         //call findAcct to search if requestedAccount exists
         
         index = accountInfo.clearCheck(isValid, check);

         if (index == -1)  //invalid account
         {
           outFile.println("Transaction Requested: Clear Check");
           outFile.println("Error: Account number " + requestedAccount 
                         + " does not exist");
         }
         else if(bankStuf.getAcct(index).getAccBal() < check.getCheckamount())
         {
        	//insufficient funds 
       	  outFile.println("Transaction Requested: Checking Account");
       	  outFile.println("Account Number: " + requestedAccount);
       	  outFile.println("Date on Check -> " + check.dateOfcheck().getMonth() 
           + "/" + check.dateOfcheck().getDayOfMonth() + "/" + check.dateOfcheck().getYear());
       	 outFile.println("The Current Date is: " + todays_date.getTime()); 
       	  outFile.println("Check amount $" + amountToWithdraw);
       	  outFile.printf("Old Balance: $%.2f", accountInfo.getAccBal());
       	  outFile.println();
       	 accountInfo.makeWithdrawal(amountToWithdraw);
       	 //make the withdrawal
       	  bankStuf.setAcct(accountInfo, index);
       	  outFile.printf("Error: $%.2f is an invalid amount",
       	            amountToWithdraw);
       	  outFile.println();
             bankStuf.setAcct(accountInfo, index);
             outFile.printf("New Balance: $%.2f", accountInfo.getAccBal());
             outFile.println();
             
          outFile.println("Error: Account number " + requestedAccount+ " already exists");
          outFile.println(); 
         //if(bankStuf.getAcct(index).getAcctyp() == "Checking")
          }
         //System.out.print("Enter the  Account Type> ");   
          //prompt for account type
       // requestedAccount2 = kybd.next();   //read-in the account type
          // clear check() withdrawal funds from account 
         accountInfo = bankStuf.getAcct(index);
          
          switch(index) {
          
          case 1://insufficient funds
        	  outFile.println("Transaction Requested: Checking Account");
        	  outFile.println("Account Number: " + requestedAccount);
        	  outFile.println("Date on Check -> " + check.dateOfcheck().getMonth() 
        	  + "/" + check.dateOfcheck().getDayOfMonth() + "/" + check.dateOfcheck().getYear());
        	  outFile.println("The Current Date is: " + todays_date.getTime());
        	  outFile.println("Check amount $" + amountToWithdraw);
        	  outFile.printf("Old Balance: $%.2f", accountInfo.getAccBal());
        	  outFile.println();
        	  
             outFile.printf("New Balance: $%.2f", accountInfo.getAccBal());
             outFile.println();
        	  break;
          case 2: //withdrawal successful
        	  outFile.println("Transaction Requested: Checking Account");
        	  outFile.println("Account Number: " + requestedAccount);
        	  outFile.println("Date on Check -> " + check.dateOfcheck().getMonth() 
        	  + "/" + check.dateOfcheck().getDayOfMonth() + "/" + check.dateOfcheck().getYear());
        	  outFile.println("The Current Date is: " + todays_date.getTime()); 
     		 System.out.println();
        	  outFile.println("Check amount $" + amountToWithdraw);
        	  outFile.printf("Old Balance: $%.2f", accountInfo.getAccBal());
        	  outFile.println();
        	  outFile.printf("Error: $%.2f is an invalid amount",
        	            amountToWithdraw);
        	  outFile.println();
              bankStuf.setAcct(accountInfo, index);
              outFile.printf("New Balance: $%.2f", accountInfo.getAccBal());
              outFile.println();
        	  break;
          case 3: //Check is 6 months old 
        	  // post date check 6 months before
        	  outFile.println("Transaction Requested: Clear Check");
        	  outFile.println("Account Number: " + requestedAccount);
        	  outFile.println("Date on Check -> " + check.dateOfcheck().getMonth() 
               + "/" + check.dateOfcheck().getDayOfMonth() + "/" + check.dateOfcheck().getYear());
        	  outFile.println("The Current Date is: " + todays_date.getTime());
        	  outFile.println("Error: Check is more than 6 months old");
        	  outFile.println();
        	  break;
          }
          
          
      }
 
	 
	 /*method tells account information that you requested*/
     //this Prompts user for Social Security Number
	 public static void accountInfo(Bank3 bankStuf,PrintWriter outFile, Scanner kybd) {
		 int index = -1;
		 String requestedAccount2;
		 Account3 accountInfo = new Account3();
		 
         System.out.println();
         System.out.print("Enter the Social Security number >");   
         //prompt for account number
         requestedAccount2 = kybd.next(); //read-in the account number
         index = bankStuf.findAcctSSN(requestedAccount2);

         //this the Social Security Number exists in
         //the Array 
     if(index == -1){
        outFile.println("Transaction Requested: Account Inquiry");
        outFile.println("Error: SSN " + requestedAccount2 
                     + " Does not exists");
        outFile.println();
     }
     else
     {
    	 accountInfo = bankStuf.getAcct(index);
        outFile.println("Account Information for SSN " 
                         + requestedAccount2 + " account requested");
        outFile.println("Names\t SocialSecurityNum\tAccountNum"
                       +"\tAccountType\t"+"AccountBal");
        outFile.print(accountInfo.getDepositor().getName().getFirst());
        outFile.print("\t"
                    +accountInfo.getDepositor().getName().getLast());
        outFile.print("\t"+accountInfo.getDepositor().getSSN());
        outFile.print("\t"+accountInfo.getAcctnum());
        outFile.printf("\t %9s  ",accountInfo.getAcctyp());
        outFile.printf("\t  %9.2f ",accountInfo.getAccBal());
        outFile.println();
        outFile.println();
        }
	 }
	 
	 /* this method creates a account and reads the information from the keyboard
		 * then sets up the data in a file and then prints it out */
	 public static void newAcct(Bank3 bankStuf,PrintWriter outFile, Scanner kybd){
		 int requestedAccount,index;
		 double requestedAccount3;
		 String requestedAccount2;
		 Account3 accountInfo = new Account3();
	      Depositor3 customers = new Depositor3();
	      Name3 names = new Name3();
	      
		             System.out.println();
		             System.out.print("Enter the account number: ");   
		             //prompt for account number
		             requestedAccount = kybd.nextInt();  //read-in the account number
		     
		             /*get number from user while
		             * looking the database if that account exists*/
		             //call findAcct to search if requestedAccount exists
		             index = bankStuf.findAcct(requestedAccount);
		          if(index != -1)                                        
		          {
		              outFile.println("Transaction Requested: New account");
		              outFile.println("Error: Account number " + requestedAccount 
		                              + " already exists");
		              outFile.println();
		          }
		          else
		          {
		         
		             //create Account,Depositor,Name objects
		             accountInfo = new Account3();
		             customers = new Depositor3();
		             names = new Name3();
		             //bankStuf.setAcctNum(requestedAccount);
		             System.out.println("Enter the depositor's first_name> ");   
		             //prompt for depositors first name
		             requestedAccount2 = kybd.next();  //read-in the first name
		             names.setFirst(requestedAccount2); //sets user Enter
		        
		             System.out.println();
		             System.out.println("Enter the depositor's last_name> ");   
		             //prompt for account number
		             requestedAccount2 = kybd.next();      //read-in the last name
		             names.setLast(requestedAccount2);      //sets user enters
		        
		             System.out.print("Enter the depositor's social_SecNum> ");   
		             //prompt for SS number
		             //read-in the social security number
		             requestedAccount2 = kybd.next(); 
		             customers.setSSN(requestedAccount2); // sets user chose
		       
		             System.out.print("Enter the depositor's  Account Type> ");   
		             //prompt for account type
		             requestedAccount2 = kybd.next();   //read-in the account type
		             accountInfo.setAccType(requestedAccount2 );
		     
		             System.out.print("Enter the depositor's Opening deposit> ");   
		             //prompt for opening deposit
		             requestedAccount3 = kybd.nextDouble();    //read-in a deposit
		             accountInfo.setBalance(requestedAccount3);
		             accountInfo.setAcctNum(requestedAccount);
		      
		             customers.setName(names);
		             accountInfo.setDepositor(customers);
		             bankStuf.openNewAcct(accountInfo);
		             /*Prints new Transaction Information
		             * to the database*/
		             outFile.println("Transaction Requested: New Account");
		             outFile.println("Names\t\tSocialSecurityNum\tAccountNum"
		                               +"\tAccountType\t"+"AccountBal");
		           index = bankStuf.findAcct(requestedAccount);
		        if(index != -1 ){
		             accountInfo = bankStuf.getAcct(index);
		        }
		             outFile.print(accountInfo.getDepositor().getName().getFirst());
		             outFile.print("\t"
		                            +accountInfo.getDepositor().getName().getLast());
		             outFile.print("\t"+accountInfo.getDepositor().getSSN());
		             outFile.print("\t\t"+accountInfo.getAcctnum());
		             outFile.printf("\t\t %9s",accountInfo.getAcctyp());
		             outFile.printf("\t  %9.2f",accountInfo.getAccBal());
		             outFile.println();
		             outFile.println();
		        }
	 }
	 public static void deleteAcct(Bank3 bankStuf,PrintWriter outFile, Scanner kybd){
		 int requestedAccount,index;
		 Account3 accountInfo = new Account3();
		 
		 System.out.println();
         System.out.println("Enter the account number: ");   
         //prompt for account number
         requestedAccount = kybd.nextInt();//read-in the account number
         index = bankStuf.deleteAcct(requestedAccount);
 if (index == -1)                                        
{
        outFile.println("Transaction Requested: Delete Account"); 
        //account deleted
        outFile.println("Error: Account number " + requestedAccount 
                        + " Does not exists");
        outFile.println(); 
}
else if(accountInfo.getAcctnum() > 0.0)
{
  
       outFile.println("Transaction Requested: Delete Account");
       outFile.println("Error: Account number " + requestedAccount 
                        + " Account exist and Has a non zero balance");
       outFile.println();
}
           
	 }
	 /* Method pause() */
	 public static void pause(Scanner keyboard)
	 {
	     String tempstr;
	     System.out.println();
	     System.out.print("press ENTER to continue");
	     tempstr = keyboard.nextLine();  //flush previous ENTER
	     tempstr = keyboard.nextLine();  //wait for ENTER
	 }
}
