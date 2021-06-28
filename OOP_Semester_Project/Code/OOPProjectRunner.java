package final_project;

public class OOPProjectRunner {
    public static void main(String[] args) {
        
        //Below commented code is used to initilize login password for the first time. DONOT un-comment unless there is some issue with data file containing login password. 
        //Credentials firstSet = new Credentials();  //ONLY TO BE USED BY DEVELOPERS.
        //firstSet.writeToFile("admin","Admin@123"); //ONLY TO BE USED BY DEVELOPERS.
        
        Menu m = new Menu();
        m.menu();
       
    }
}