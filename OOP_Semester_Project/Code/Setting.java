package final_project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Setting extends Credentials{

    Scanner userInput = new Scanner(System.in);

    public void settingTerminal(){
        int selection;
        boolean menuStatus = true; //Used to keep the selection in loop unless 0 is pressed. 
        do{
            System.out.println();
            System.out.println("[[SETTINGS]]");
            System.out.println("[1] Change Password. ");
            System.out.println("[2] Details.");
            System.out.println("[0] Return to Main Menu.");
            while(true){ // If an exception occurs the system will loop and ask for the correct entry once again. 
                try{ //Handling Input mismatch exception where the user can enter a string while only integer is allowed.
                    System.out.print("Enter option: ");
                    selection = userInput.nextInt();
                    break;
                } catch(InputMismatchException ex){
                System.out.println("System expects numeric entry!");
                userInput.next();
                }
            }
            if(selection == 1){
                changePassword();
            } else if(selection == 2){
                details();
            } else if(selection == 0){
                menuStatus = false; // Breaks the loop. 
            } else{
                System.out.println("Unrecognized Entry.");
            }

        } while(menuStatus);
    }

    private void changePassword(){
        System.out.println();
        System.out.println("[[Change Password]]");
        //Below counts are used to have a limit on wrong inputs. 
        int wrongAttemptCountOne = 0;
        int wrongAttemptCountTwo = 0;
        int wrongAttemptCountThree = 0;
        System.out.print("Enter Old Password: ");
        String oldPassword = userInput.next();
        if(oldPassword.equals(getPassword())) { // checks if the old password entered is correct or not.
            boolean matchStatus = false;
            boolean status = false;
            do{
                System.out.print("Enter New Password: ");
                String newPasswordOne = userInput.next();
                if(newPasswordOne.equals(oldPassword)){ // checks if the user enteres the old password again. 
                    System.out.println("You have entered the old password once again. If you wish to keep the current password you can do so.");
                    System.out.println("[1] Keep old password.\n[2] Proceed and change password.");
                    int selection;
                    while(true){
                        try{ // Handles exception as only integer input is allowed.
                            System.out.print("What do you want to do?[1/2]: ");
                            selection = userInput.nextInt();
                            break;
                        } catch (InputMismatchException ex){
                            System.out.println("System excepts numeric entry!");
                        }
                    }
                    System.out.println();
                    if (selection==1){ // if user wishes to keep old password loop will break and nothing will be changed.
                        status = true;
                    } else if(selection==2){ // if user still wants to change password the system will loop and ask for the new password once again. 
                        status = false;
                    } else{
                        System.out.println("Unknown option, Old Password will be kept."); 
                        status = true;
                    }
                } else{
                    if (super.passwordCheck(newPasswordOne)) { // checks if the new password is accoring to the allowed syntax. 
                        System.out.print("Confirm New Password: "); 
                        String newPasswordTwo = userInput.next();
                        if (newPasswordOne.equals(newPasswordTwo)) { // confirms new password a second time. 
                            matchStatus = true;
                            boolean passwordStatus = true;
                            do { // At the end system ask for confirmation so that the system can change password. 
                                System.out.print("Are you sure you want to change password?[Y/N]: ");
                                String selection = userInput.next();
                                if (selection.toLowerCase().equals("y")) {
                                    setPassword(newPasswordOne); // Here the password is actually changed.
                                    writeToFile(getLoginId(),getPassword());  // New data is written to file.
                                    passwordStatus = true;
                                    System.out.println();
                                } else if (selection.toLowerCase().equals("n")) { // The system will abort action and no changes will be made.
                                    System.out.println("Action Aborted");
                                    System.out.println();
                                    passwordStatus = true;
                                    System.out.println();
                                } else {
                                    System.out.println("Unexpected input."); // any entry other than Y,y,N,n this section will run.
                                    System.out.println();
                                    passwordStatus = false;
                                    wrongAttemptCountOne++;
                                    if(wrongAttemptCountOne==3){ // if wrong attempts reach 3 the system will abort action and no changes will be made. 
                                        System.out.println("Action has been aborted due to multiple wrong attempts."); 
                                        System.out.println();
                                        break;
                                    }
                                }
                            }while(!passwordStatus); 
                        } else{ // when the 2 new password do not match each other. 
                            System.out.println("Passwords do not match. Try Again.");
                            System.out.println();
                            wrongAttemptCountTwo++;
                            if(wrongAttemptCountTwo==3){ // limits the amount of loops to 3 and after that system will not take more inputs.
                                System.out.println("Action has been aborted due to multiple wrong attempts.");
                                System.out.println();
                                break;
                            }
                        }
                    } else{ // when the new password entered is not according to the required pattern.
                        System.out.println();
                        System.out.println("Wrong Password Pattern.");
                        System.out.println("Required Pattern: \nMust have at least one numeric character.\nMust have at least one lowercase character.\nMust have at least one uppercase character.\nMust have at least one special symbol among @#$%.\nPassword length should be between 8 and 20.");
                        System.out.println();
                        wrongAttemptCountThree++;
                        if(wrongAttemptCountThree==3){
                            System.out.println("Action has been aborted due to multiple wrong attempts.");
                            System.out.println();
                            break;
                        }
                    }
                }
            } while(!matchStatus && !status);
        } else{ // when the old password entered is not correct.
            System.out.println("Admin Access Denied, Incorrect Password!");
            System.out.println();
        }
    }

    private void details(){ // Prints details about the system and its developers. 
        System.out.println();
        System.out.println("Hospital Management System Version 1.0");
        System.out.println("Project for Object Oriented Programming. By:");
        System.out.println("1. Shehroze Ehsan SP20-BSE-086\n2. Talha Rizwan SP20-BSE-093\n3. Rana Ali Uzair SP20-BSE-080.");
        System.out.println("Presented to Sir Mukhtar Azeem.");
        System.out.println();
    }
}