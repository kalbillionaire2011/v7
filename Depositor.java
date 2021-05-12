
public class Depositor {
	private Name name2;
    private String socS_num;
    
    //No Arg Constructor
    public Depositor() {
        name2 = new Name();
        socS_num = "";
    }
    //Parameter Constructor
    public Depositor(Name n2,String ssn3){
     name2 = n2;
     socS_num = ssn3; 
    }

    //Getter methods
    public String getSSN(){
      return socS_num;
    }
    public Name getName(){
        return name2;
    }
    //Setter methods
    public void setSSN(String ss_num){
      socS_num = ss_num;
    }
    public void setName(Name names_of){
        name2 = names_of;
    }
}
