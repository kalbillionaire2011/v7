/*This is the last Class in the Program which is connected to Main and
 * all the other Class in the Program when the program runs*/
public class Name3 {
	 private String first_name;
     private String last_name;
     
     //No Arg constructor
     public Name3() { 
          first_name = "";
          last_name = "";
    }
     // ConStructors with parameter
     public Name3(String n1,String n2){
         first_name = n1;
         last_name = n2;
     }
     
    //Getters methods
     public String getFirst(){
       return first_name;
     }
     public String getLast(){
       return last_name;
     }
     
    //Setter methods
     public void setFirst(String first){
        first_name = first;
     }
     public void setLast(String last){
       last_name = last;
     }
}
