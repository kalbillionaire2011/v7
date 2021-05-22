//import java.util.Scanner;
//import java.io.*;
public class Bank3 {
	/* ADD YOUR CODE HERE */
    //constant definitions
    private final int MAX_NUM = 50;
    private int num_accts;
    private Account3 [] account;
    
   //NO Arg ConStructor
   public Bank3() { 
       account = new Account3[MAX_NUM];
       num_accts = 0;
   }
   
   //Setter methods 
   public void setnum_accts(int num_ofAccts){
       num_accts = num_ofAccts;
       num_accts++;
       num_accts--;
   }
   public void setAcct(Account3 accountInfo,int index){
   account [index]= accountInfo;
   }
   
   //Getter methods
   //sends index and return index position
   public Account3 getAcct(int index){
         return account[index];
   }
   
   public int getNumAccts(){
         return num_accts;
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
   public int findAcct(int requestedAccount)
   {
        for(int index = 0; index < num_accts; index++){
            if (account[index].getAcctnum()== requestedAccount)
                return index;
        }
        return -1;
   }
   
 
   //creates new account in Array
   public boolean openNewAcct(Account3  accountInfo){
       //local variable
       int index;
   
       index=findAcct(accountInfo.getAcctnum());
       if(index != -1)
          return false; //account close
    
          account[num_accts]=accountInfo;
          num_accts++;
    
       return true;   //account open
   }
   
   /*This method deletes accounts out of the database in the position they
   * are in, at the time of deletion.*/
   public int deleteAcct(int requestedAccount){
        //local variable
        int index;
        index = findAcct(requestedAccount);
        if(index != -1 ){
         //account does not exists
        for ( int i = index ; i < account.length - 1 ; i++ ){
               account[ i ] = account[ i + 1 ]; //valid account deletion
        }
        num_accts--;
        }
        return index;
   }
   public int findAcctSSN(String requestedAccount3){
        Account3 accountstuf = new Account3();
        //call findAcct to search if requestedAccount exists
        int tempSSN = -1; 
        for(int i = 0; i < num_accts;i++){
            accountstuf = account[i];
            if(requestedAccount3.equals(accountstuf.getDepositor()
                                        .getSSN())) 
               tempSSN = i;
        }
        return tempSSN;
   }
   public double balanceOf(int requestedAccount){
        int index = findAcct(requestedAccount);
        if(index != -1){
           return account[index].getBalance();
   }
   //the getBalance() method
   //called from the Account class
   return -1;
   }
   public int findAcctTyp(String requestedAccount)
   {
        for(int index = 0; index < num_accts; index++){
            if (account[index].getAcctyp() == requestedAccount)
                return index;
        }
        return -1;
   }
}
