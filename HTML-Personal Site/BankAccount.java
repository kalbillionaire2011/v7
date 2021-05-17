/**
 * Auto Generated Java Class.
 */
/*this Class is the first that is seen by main in the program
 * and it is the first Class that is created, seen all the other Classes in 
 the program*/
/* this Class is from Homework #2*/
public class BankAccount {
  
     private Depositor info1;
     private int accNum;
     private String acc_Typ;
     private double accBalance;
     
     //Parameter ConStructor
     public BankAccount(int accountNum,String acc_Typ,
                        double accBal,Depositor info2){
          this.acc_Typ = acc_Typ;
         accNum = accountNum;
         accBalance = accBal;
         info1 = info2;
     }
     //No Arg Constructor
     public BankAccount(){
          accNum = 0;
          acc_Typ = "";
          accBalance = 0.0;
          info1 = new Depositor();
     }
     
     //Getter methods
     public int getAccnum(){
          return accNum;
     }
     public String getAcctyp(){
          return acc_Typ;
     }
     public double getAccBal(){
          return accBalance;
     }
     public Depositor getDepositor(){
          return info1;
     }
     
     //Setter methods
     public void setAcctNum(int aact_nums){
          accNum = aact_nums;
     }
     public void setAccType(String acctys ){
          acc_Typ = acctys;
     }
     public void setBalance(double acctbal ){
          accBalance = acctbal;
     }
     public void setDepositor(Depositor nextInfo){
          info1 = nextInfo;
     }
     
}
