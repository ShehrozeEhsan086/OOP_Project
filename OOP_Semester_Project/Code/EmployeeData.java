package final_project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeData {
    public static void showEmployeeData(){
        Scanner userInput = new Scanner(System.in);
        boolean menuStatus = true;
        do{
            System.out.println();
            System.out.println("[[EMPLOYEES]]");
            System.out.println("[1] Medical Staff.");
            System.out.println("[2] Non-Medical Staff.");
            System.out.println("[0] Return to Main menu.");
            int selection;
            while(true){
                try{ // handles exception of wrong input.
                    System.out.print("ENTER CHOICE: ");
                    selection = userInput.nextInt();
                    break;
                } catch (InputMismatchException ex){
                    System.out.println("Only numeric entry in allowed.");
                    userInput.next();
                }
            }
            boolean medicalStaffStatus = true;
            if (selection == 1){
                do{
                    System.out.println();
                    System.out.println("[[MEDICAL STAFF]]");
                    System.out.println("[1] Doctor Data.");
                    System.out.println("[2] Nurse Data.");
                    System.out.println("[0] Return.");
                    int selectionTwo;
                    while(true){
                        try{
                            System.out.print("ENTER CHOICE: ");
                            selectionTwo = userInput.nextInt();
                            break;
                        } catch (InputMismatchException ex){
                            System.out.println("Only numeric entry is allowed! Try again.");
                            userInput.next();
                        }
                    }
                    if(selectionTwo==1){
                        doctorData();
                        medicalStaffStatus = true;
                    } else if(selectionTwo==2){
                        nurseData();
                        medicalStaffStatus = true;
                    } else if(selectionTwo==0){
                        medicalStaffStatus = true;
                    } else{
                        System.out.println("Unrecognized Entry. Try again.");
                        medicalStaffStatus = false;
                    }
                    menuStatus = true;
                } while(!medicalStaffStatus);
            } else if(selection==2){
                otherStaff();
                menuStatus = true;
            } else if(selection==0){
                menuStatus = false;
            } else{
                System.out.println("Unrecognized Entry. Try again.");
                menuStatus = true;
            }
        } while(menuStatus);
    }

    private static void doctorData(){ // calls the doctors terminal.
        DoctorData d = new DoctorData();
        d.doctorTerminal();       
    }

    private static void nurseData(){ // calls the nurse terminal. 
        NurseData n = new NurseData();
        n.nurseTerminal();       
    }

    private static void otherStaff(){ // calls the otherstaff terminal.
        OtherStaffData oS = new OtherStaffData();
        oS.otherStaffTerminal();
    }
}