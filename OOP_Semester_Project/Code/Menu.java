package final_project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void menu(){
        String pass,login;
        int passCount = 0, choice;
        boolean check;
        Scanner scan = new Scanner(System.in);
        System.out.println("!------------------- Islamabad Hospital -------------------!");
        System.out.println("[[LOGIN]]");
        while(true){ // loop for login terminal.
            System.out.print("Username: ");
            login = scan.next();
            System.out.print("Password: ");
            pass = scan.next();
            check = checkLogin(login, pass);
            if (check) { // if true means login and passsword are correct.
                break;
            } else { // counts the amount of wrong enteries. 
                if(passCount == 2){
                    System.out.println("Multiple wrong entries exiting system. If you are unable to get in contact developers to regain access.");
                    System.exit(0);
                }
                System.out.println("You entered wrong Username or Password! Try again.");
                System.out.println();
                passCount++;
            }
        }
        while(true){ // The Main Menu.
                System.out.println();
                System.out.println("[[MAIN MENU]]");
                System.out.println("[1] Employees Data.");
                System.out.println("[2] Patients Data.");
                System.out.println("[3] Inventory.");
                System.out.println("[4] Settings.");
                System.out.println("[0] Exit.");
                while(true){
                    System.out.print("Enter Option: ");
                    try{
                        choice = scan.nextInt();
                        break;
                    } catch(InputMismatchException ex){
                        System.out.println("System expects numeric entry only! Try again.");
                        scan.next();
                    }
                }
                switch(choice){
                    case 0:
                        System.out.println("System Exiting..."); 
                        System.exit(0);
                    case 1:
                        EmployeeData.showEmployeeData();
                        break;
                    case 2:
                        this.patientData();
                        break;
                    case 3:
                        this.inventoryData();
                        break;
                    case 4:
                        this.settings();
                        break;
                    default:
                        System.out.println("Unrecognized entry! Try Again.");
                        System.out.println();
                }
        }
    }
    
    public boolean checkLogin(String login, String pass){ // checks if login password are correct.
        Credentials check = new Credentials();
        return login.equals(check.getLoginId()) && pass.equals(check.getPassword());
    }
    
    public void patientData(){ // shows patient terminal.
        PatientData patientData = new PatientData();
        patientData.patientTerminal();
    }

    public void inventoryData(){ // shows inventory terminal.
        InventoryData inventoryData = new InventoryData();
        inventoryData.inventoryTerminal();
    }

    public void settings(){ // shows settings.
        Setting settingAccess = new Setting();
        settingAccess.settingTerminal();
    }
}