		
/*09/19/19
 * H.W.#2 
 * I did EXTRA CREDIT 1 & 2*/
import java.io.*;
//needed in order to use the StringTokenizer class
import java.util.StringTokenizer;
import java.util.*;

//this code go to 
//Classes BankAccount Name2,and Depositor2

public class H_w_2 {
	public static void main(String[] args) throws IOException
	 {
	     //constant definitions
	     final int MAX_NUM = 50;
	  
	     //variable declarations
	     BankAccount [] account = new BankAccount [MAX_NUM];
	     int num_accts;         //number of accounts
	     char choice;         //menu item selected
	     boolean not_done = true;      //loop control flag
	     
	     
	     // open input test cases file
	     //File testFile = new File("f:/advance java/mytestcases.txt");
	     File testFile = new File("F:/Advance Java/BankAccounts/code#2/src/mytestcases2.txt");
	     //create Scanner object
	     Scanner kybd = new Scanner(testFile);
	     //Scanner kybd = new Scanner(System.in);

	     // open the output file
	     //PrintWriter outFile = new PrintWriter("dataoutput2.txt");
	     PrintWriter outFile = new PrintWriter("F:/Advance Java/BankAccounts/code#2/src/dataoutput.txt");
	     //PrintWriter outFile = new PrintWriter(System.out);
	     outFile.println();
	     /* first part */
	     /* fill and print initial database */
	     num_accts = readAccts(account,MAX_NUM);
	     printAccts(account,num_accts,outFile);
	    
	     
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
	                 printAccts(account,num_accts,outFile);
	                 break;
	             case 'b':
	             case 'B':
	                 balance(account,num_accts,outFile,kybd);
	                 break;
	             case 'i':
	             case 'I':
	                 accountInfo(account,num_accts,outFile,kybd);
	                 break;
	             case 'd':
	             case 'D':
	                 deposit(account,num_accts,outFile,kybd);
	                 break;
	             case 'w':
	             case 'W':
	                 withdrawal(account,num_accts,outFile,kybd);
	                 break;
	             case 'n':
	             case 'N':
	                 num_accts = newAcct(account,num_accts,outFile,kybd);
	                 break;
	             case 'x':
	             case 'X':
	                 num_accts = deleteAcct(account,num_accts,outFile,kybd);
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
	 public static int readAccts(BankAccount []account, int maxAccts) 
	     throws IOException
	 {
	     //local variables
	     int count = 0;
	     String line,tempstr;
	     
	     
	     // open database input file
	     //create File object
	     File dbFile = new File("inputdata2.txt");

	     //Create a Scanner object to read the input file
	     Scanner sc = new Scanner(dbFile);

	     while(sc.hasNext())
	     {

	          //create BankAccount,Depositor,Name objects
	          Name names = new Name();
	          Depositor customers = new Depositor();
	          BankAccount bankInfo = new BankAccount();
	     
	         //read next line of data 
	         line = sc.nextLine();
	         StringTokenizer tokenizer = new StringTokenizer(line);
	         
	         //extract the data from the line read
	         names.setFirst(tokenizer.nextToken());       //extract first name
	         names.setLast(tokenizer.nextToken());      //extract last name
	         customers.setSSN(tokenizer.nextToken());
	         customers.setName(names);
	         tempstr = tokenizer.nextToken();
	         bankInfo.setAcctNum(Integer.parseInt(tempstr));
	         bankInfo.setAccType(tokenizer.nextToken());
	         bankInfo.setBalance(Double.parseDouble(tokenizer.nextToken()));
	         bankInfo.setDepositor(customers);
	     
	         //set the Array elements
	         account[count] = bankInfo;
	         count++;            //increment the BankAccount count
	  }
	    
	    //close the input file for BankAccounts
	    sc.close();
	     
	    //return the account number count
	    return count;
	    
	   
	 }

	 /* Method printAccts:
	  * Input:
	  *  reeading from the file to the account Array
	  *  num_accts - number of active accounts
	  *  outFile - reference to the output file
	  * Process:
	  *  Prints the database of accounts Array and balances personal informtion
	  * Output:
	  *  Prints the database of accounts and balances
	 */
	 public static void printAccts(BankAccount[] account, int num_accts,
	         PrintWriter outFile)
	 {
	     //create BankAccount,Depositor objects
	     Name names = new Name();
	     Depositor customers = new Depositor();
	     BankAccount bankInfo = new BankAccount();
	     
	     //System.out.println();
	     outFile.println();
	     outFile.printf("\t\t\t\t Bank Accounts  DataBase");
	     outFile.println();
	     //Printing table of colums of headings
	     outFile.print("Names\t \t \t \t"+" SocialSecurNum \t");
	     outFile.println("AccountNum\t " + "AccountType " + "  AccountBal");
	     for(int count = 0; count < num_accts; count++)
	     {
	         names = account[count].getDepositor().getName();
	         outFile.printf("%-10s ",names.getFirst());
	         outFile.printf("%-10s",names.getLast());
	         customers = account[count].getDepositor();
	         outFile.print("\t  "+customers.getSSN());
	         bankInfo = account[count];
	         outFile.print("\t\t    "+bankInfo.getAccnum());
	         outFile.printf("\t  %9s",bankInfo.getAcctyp());
	         outFile.printf("\t  %9.2f",bankInfo.getAccBal());
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
	     System.out.println("\t     N -- New Account");
	     System.out.println("\t     B -- Balance Inquiry");
	     System.out.println("\t     I -- Account Inquiry");
	     System.out.println("\t     X -- Delete Account");
	     System.out.println("\t     Q -- Quit");
	     System.out.println();
	     System.out.print("\tEnter your selection: ");
	 }
	 
	 /* Method findAcct:
	  * Input:
	  *  acctNumArray - array of account numbers
	  *  numAccts - number of active accounts
	  *  requestedAccount - requested account requested_number
	  * Process:
	  *  Performs a linear search on the acctNunArray for the requested account
	  * Output:
	  *  If found, the index of the requested account is returned
	  *  Otherwise, returns -1
	  */
	 public static int findAcct(BankAccount[] account, int num_accts,
	                            int requestedAccount)
	 {
	      for(int index = 0; index < num_accts; index++){
	         if (account[index].getAccnum()== requestedAccount)
	             return index;
	   }
	      return -1;
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
	 public static void balance(BankAccount account [], int num_accts, 
	   PrintWriter outFile, Scanner kybd)
	 {
	     int requestedAccount;
	     int index;
	     //prompt for account number
	     System.out.println();
	     System.out.print("Enter the account number: "); 
	     requestedAccount = kybd.nextInt();  //read-in the account number
	     
	     //call findAcct to search if requestedAccount exists
	     index = findAcct(account, num_accts, requestedAccount);
	     
	     if (index == -1)                                  //invalid account
	     {
	         outFile.println("Transaction Requested: Balance Inquiry");
	         outFile.println("Error: Account number "
	                             + requestedAccount + " does not exists");
	         
	     }
	     else                                                    //valid account
	     {
	         outFile.println("Transaction Requested: Balance Inquiry");
	         outFile.println("Account Number: " + requestedAccount);
	         outFile.printf("Current Balance: $%.2f", account[index].getAccBal());
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
	 public static void deposit(BankAccount []account, int num_accts, 
	   PrintWriter outFile, Scanner kybd)
	 {
	     int requestedAccount;
	     int index;
	     double amountToDeposit;

	     System.out.println();
	     System.out.print("Enter the account number: ");//prompt for account number
	     requestedAccount = kybd.nextInt();      //read-in the account number

	     //call findAcct to search if requestedAccount exists
	     index = findAcct(account, num_accts, requestedAccount);
	     
	     if (index == -1)                                        //invalid account
	     {
	         outFile.println("Transaction Requested: Deposit");
	         outFile.println("Error: Account number " 
	                             + requestedAccount + " does not exist");
	     }
	     else                                                    //valid account
	     {
	         System.out.print("Enter amount to deposit: "); 
	         //prompt for amount to deposit
	         amountToDeposit = kybd.nextDouble(); //read-in the amount to deposit

	         if (amountToDeposit <= 0.00)                  
	         {
	          //invalid amount to deposit
	          outFile.println("Transaction Requested: Deposit");
	          outFile.println("Account Number: " + requestedAccount);
	          outFile.printf("Error: $%.2f is an invalid amount", amountToDeposit);
	          outFile.println();
	         }
	         else
	         {
	          outFile.println("Transaction Requested: Deposit");
	          outFile.println("Account Number: " + requestedAccount);
	          outFile.printf("Old Balance: $%.2f", account[index].getAccBal());
	          outFile.println();
	          outFile.println("Amount to Deposit: $" 
	                              + String.format("%.2f" , amountToDeposit));
	          account[index].setBalance(account[index].getAccBal()
	                                        +amountToDeposit);  
	          outFile.printf("New Balance: $%.2f", account[index].getAccBal());
	          outFile.println();
	         }
	     }
	     outFile.println();

	     outFile.flush();    //flush the output buffer
	 }
	 
	 //this method withdraw the amount of moneny a person wants
	 //and tells the user the error they made while doing that.
	 //also for the funds they want too.
	 public static void withdrawal(BankAccount []account, int num_accts,
	   PrintWriter outFile, Scanner kybd)
	 {
	     int requestedAccount;
	     int index;
	     double amountToWithdraw;

	     System.out.println();
	     System.out.print("Enter the account number: ");//prompt for account number
	     requestedAccount = kybd.nextInt();      //read-in the account number

	     //call findAcct to search if requestedAccount exists
	     index = findAcct(account, num_accts, requestedAccount);
	     
	     if (index == -1)                                        //invalid account
	     {
	         outFile.println("Transaction Requested: Withdrawal");
	         outFile.println("Error: Account number " + requestedAccount 
	                             + " does not exist");
	         outFile.println();
	     }
	     else                                                    //valid account
	     {
	         System.out.print("Enter amount to withdraw: "); 
	          //prompt for amount to withdraw
	         amountToWithdraw = kybd.nextDouble();//read-in the amount to withdraw

	         if (amountToWithdraw <= 0.00)                  
	         {
	             //invalid amount to withdraw
	             outFile.println("Transaction Requested: Withdrawal");
	             outFile.println("Account Number: " + requestedAccount);
	             outFile.printf("Error: $%.2f is an invalid amount",
	                         amountToWithdraw);
	             outFile.println();
	         }
	         if(amountToWithdraw > account[index].getAccBal())                  
	         {
	            //invalid amount to withdraw
	            outFile.println("Transaction Requested: Withdrawal");
	            outFile.println("Account Number: " + requestedAccount);
	            outFile.printf("Old Balance: $%.2f", account[index].getAccBal() );
	            outFile.println();
	            outFile.printf("Error: $%.2f insufficient funds", 
	                         amountToWithdraw);
	            outFile.println();
	         }
	         if(amountToWithdraw > 0.00 && 
	            amountToWithdraw <= account[index].getAccBal()) 
	         {
	            outFile.println("Transaction Requested: Withdrawal");
	            outFile.println("Account Number: " + requestedAccount);
	            outFile.printf("Old Balance: $%.2f", account[index].getAccBal());
	            outFile.println();
	            outFile.println("Amount to Withdraw: $" + String.format("%.2f" , 
	                                            amountToWithdraw));
	            account[index].setBalance(account[index].getAccBal()
	                                 -amountToWithdraw);   //make the withdrawal
	            outFile.printf("New Balance: $%.2f", account[index].getAccBal());
	            outFile.println();
	         }
	         }
	            outFile.println();

	            outFile.flush();    //flush the output buffer
	 }
	/* this method creates a account and reads the information from the keyboard
	 * then sets up the data in a file and then prints it out */
	 public static int newAcct(BankAccount [] account, int num_accts,
	   PrintWriter outFile, Scanner kybd)
	 {
	     String requestedAccount2;
	     int requestedAccount;
	     int index;
	     double requestedAccount3;

	     
	     
	     System.out.println();
	     System.out.print("Enter the account number: ");   
	     //prompt for account number
	     requestedAccount = kybd.nextInt();      //read-in the account number
	     
	     /*get number from user while
	      * looking the database if that account exists*/
	     //call findAcct to search if requestedAccount exists
	     index = findAcct(account, num_accts, requestedAccount);
	     if(index != -1)                                        
	     {
	        outFile.println("Transaction Requested: New account");
	        outFile.println("Error: Account number " + requestedAccount 
	                             + " already exists");
	        outFile.println();
	     }
	     else
	     {
	         
	         //create BankAccount,Depositor,Name objects
	         BankAccount bankInfo = new BankAccount();
	         Depositor customers = new Depositor();
	         Name names = new Name();
	         bankInfo.setAcctNum(requestedAccount);
	         System.out.println("Enter the depositor's first_name> ");   
	         //prompt for depositors firstname
	         requestedAccount2 = kybd.next();      //read-in the first name
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
	         requestedAccount2 = kybd.next();      //read-in the account type
	         bankInfo.setAccType(requestedAccount2 );
	     
	        System.out.print("Enter the depositor's Opening deposit> ");   
	        //prompt for openig deposit
	        requestedAccount3 = kybd.nextDouble();      //read-in a deposit
	        bankInfo.setBalance(requestedAccount3);
	     
	      
	        customers.setName(names);
	        bankInfo.setDepositor(customers);
	        account [num_accts] = bankInfo;
	      
	        outFile.println("Transaction Requested: New account");
	        outFile.println("Names\t SocialSecurityNum\tAccountNum"
	                               +"\tAccountType\t"+"AccountBal");
	           
	         outFile.print(bankInfo.getDepositor().getName().getFirst());
	         outFile.print("\t"+bankInfo.getDepositor().getName().getLast());
	         outFile.print("\t"+bankInfo.getDepositor().getSSN());
	      
	         outFile.print("\t"+bankInfo.getAccnum());
	         outFile.printf("\t\t %9s",bankInfo.getAcctyp());
	         outFile.printf("\t  %9.2f",bankInfo.getAccBal());
	         outFile.println();
	         outFile.println();
	      
	         num_accts++;
	     }
	     
	        return num_accts;
	 }
	/*This method deletes accounts out of the database in the position they
	 * are in, at the time of deletion.*/
	 public static int deleteAcct(BankAccount []account, int num_accts,
	   PrintWriter outFile, Scanner kybd)
	 {
	     
	     System.out.println();
	     System.out.println("Enter the account number: ");   
	     //prompt for account number
	     int requestedAccount = kybd.nextInt();      //read-in the account number
	     int index = findAcct(account, num_accts, requestedAccount);
	     if (index == -1)                                        
	     {
	         outFile.println("Transaction Requested: Delete Account"); 
	         //account deleted
	         outFile.println("Error: Account number " + requestedAccount 
	                             + " Does not exists");
	         outFile.println(); 
	     }
	     else if(account[index].getAccBal() > 0.0)
	     {
	         outFile.println("Transaction Requested: Delete Account");
	         outFile.println("Error: Account number " + requestedAccount 
	                             + " Account exist and Has a non zero balance");
	         outFile.println();
	     }
	     else
	     {
	       for ( int i = index ; i < account.length - 1 ; i++ ){
	                account[ i ] = account[ i + 1 ]; //valid account deletion
	     }
	     num_accts--;
	     }
	     return num_accts;
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

	 /*method tells account information that you requested*/
	 //this Prompts user for Social Security Number
	 public static void accountInfo(BankAccount[] account, int num_accts,
	                                PrintWriter outFile,Scanner kybd) {
	     String requestedAccount;
	     //int count=0
	     int tempSSN = -1;
	     
	    // Scanner kybd = new Scanner (System.in);
	     //Scanner kybd = new Scanner (new File("f:/advance java/mytestcases2.txt"));

	     System.out.println();
	     System.out.print("Enter the Social Security number >");   
	       //prompt for account number
	     requestedAccount = kybd.next(); //read-in the account number
	     
	     //call findAcct to search if requestedAccount exists
	     //index = findAcct(acctNumArray, numAccts, requestedAccount);
	     for(int i = 0; i < num_accts; i++){
	         BankAccount bankInfo = account[i];
	         if(requestedAccount.equals(bankInfo.
	                              getDepositor().getSSN())) 
	                tempSSN = i;
	     }
	     
	     //this the Social Security Number exists in
	     //the Array 
	         if(tempSSN == -1){
	             outFile.println("Transaction Requested: Account Inquiry");
	             outFile.println("Error: SSN " + requestedAccount 
	                             + " Does not exists");
	             outFile.println();
	         }
	         else{
	           outFile.println("Transaction Requested: Account Inquiry");
	           outFile.println("Account Information for SSN " + requestedAccount
	                               + " account requested");
	           outFile.println("Names\t SocialSecurityNum\tAccountNum"
	                               +"\tAccountType\t"+"AccountBal");
	           BankAccount bankInfo = account[tempSSN];
	           outFile.print(bankInfo.getDepositor().getName().getFirst());
	           outFile.print("\t"+bankInfo.getDepositor().getName().getLast());
	           outFile.print("\t"+bankInfo.getDepositor().getSSN());
	      
	          outFile.print("\t"+bankInfo.getAccnum());
	          outFile.printf("\t\t %9s",bankInfo.getAcctyp());
	          outFile.printf("\t  %9.2f",bankInfo.getAccBal());
	          outFile.println();
	          outFile.println();
	         }
	 }
}
