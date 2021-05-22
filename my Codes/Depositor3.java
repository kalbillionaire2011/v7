/**This is the third Class in the program and
  * it is connected to the Bank, Account and the Name Class */
public class Depositor3 {
	private Name3 name2;
    private String socS_num;
    
    //No Arg Constructor
    public Depositor3() {
        name2 = new Name3();
        socS_num = "";
    }
    //Parameter Constructor
    public Depositor3(Name3 n2,String ssn3){
    			name2 = n2;
    			socS_num = ssn3; 
    }

    //Getter methods
    public String getSSN(){
      return socS_num;
    }
    public Name3 getName(){
        return name2;
    }
    //Setter methods
    public void setSSN(String ss_num){
      socS_num = ss_num;
    }
    public void setName(Name3 names_of){
        name2 = names_of;
    }
}
