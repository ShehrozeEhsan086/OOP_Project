package final_project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NurseData extends DataHandling implements DataManipulation{
    
    private ArrayList<Nurse> nurses;
    Scanner scan = new Scanner(System.in);
    private final String fileName = "NurseRecord";

    public NurseData() {
        nurses = new ArrayList();  
        readFromFile();
    }
    
    public void nurseTerminal(){
        String name;
        boolean menuStatus = true;
        int choice;
        do{
            System.out.println();
            System.out.println("[[NURSES]]");
            System.out.println("[1] Add New Record.");
            System.out.println("[2] Show All Records.");
            System.out.println("[3] Show Single Record.");
            System.out.println("[4] Delete All Records.");
            System.out.println("[5] Delete Single Record.");
            System.out.println("[6] Edit a Record.");
            System.out.println("[0] Return to Main Menu.");
            while(true){
                try{ // handles exception as only numeric entry is allowed.
                    System.out.print("Enter option: ");
                    choice = scan.nextInt();
                    break;
                } catch(InputMismatchException ex){
                System.out.println("System expects numeric entry!");
                scan.next();
                }
            }
            scan.nextLine();
            switch (choice) {
                case 0:
                    menuStatus = false;
                    break;
                case 1:
                    addData();
                    break;
                case 2:
                    System.out.println("[[NURSE DATA]]");
                    System.out.println();
                    showAllData();
                    break;
                case 3:
                    System.out.print("Enter name of nurse to find record: ");
                    name = scan.nextLine();
                    showSingleEntry(name);
                    break;
                case 4:
                    boolean delete = super.deleteStatusCheck(); // method that confirm if the user wants to delete.
                    if(delete)
                        deleteAllData();
                    break;
                case 5:
                    System.out.print("Enter name of nurse to delete record: ");
                    name = scan.nextLine();
                    deleteSingleEntry(name);
                    break;
                case 6:
                    System.out.print("Enter name of Nurse to edit record: ");
                    name = scan.nextLine();
                    editRecord(name);
                    break;
                default:
                    System.out.println("Wrong entry please select again.");
            }
        }while(menuStatus);
    }

    @Override
    public void addData(){ //Add new data in file and in array list
        String name,department,gender;
        int age,employeeSince;
        System.out.println();
        System.out.println("[[ADD DATA]]");
        while(true){
            System.out.print("Enter name of the Nurse: ");
            name = scan.nextLine();
            if(checkString(name)){
                break;
            }
        }
        while (true) {
            try {
                System.out.print("Enter age of the Nurse: ");
                age = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("System expects numeric entry!");
                scan.next();
            }
        }
        scan.nextLine();
        while(true){
            System.out.print("Enter gender of the Nurse (\"male\" or \"female\"): ");
            gender = scan.next().toLowerCase();
            if(gender.equals("male") || gender.equals("female")){
                break;
            }
            else
                System.out.println("Please enter suitable gender.");
        }
        scan.nextLine();
        System.out.print("Enter Department of the Nurse: ");
        department = scan.nextLine();
        while (true) {
            try {
                System.out.print("Enter year of employment: ");
                employeeSince = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("System expects numeric entry!");
                scan.next();
            }
        }
        nurses.add(new Nurse(department,employeeSince,name,age,gender));
        super.writeToFile(nurses,fileName); // calls method in the Data Handling class.
    }

    @Override
    public void editRecord(String name){ //edits record
        String new_name,department,gender;
        int age,employeeSince;
        int location = -1;
        location = super.findSingleRecord(nurses,name);
        if(location == -1){
            System.out.println("\tNurse not found\n");
        }
        else{
            System.out.println("[[EDIT]]");
            System.out.println("Old Data: ");
            showSingleEntry(nurses.get(location).getName());
            System.out.println("Enter New Data: ");
            while(true){
                System.out.print("Name: ");
                new_name = scan.nextLine();
                if(checkString(new_name)){
                    break;
                }
            }
            while (true) {
                try {
                    System.out.print("Age: ");
                    age = scan.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("System expects numeric entry!");
                    scan.next();
                }
            }
            scan.nextLine();
            while(true){
                System.out.print("Gender (\"male\" or \"female\"): ");
                gender = scan.next().toLowerCase();
                if(gender.equals("male") || gender.equals("female")){
                    break;
                }
                else
                    System.out.println("Please enter suitable gender.");
            }
            scan.nextLine();
            System.out.print("Department: ");
            department = scan.nextLine();
            while (true) {
                try {
                    System.out.print("Year of employment: ");
                    employeeSince = scan.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("System expects numeric entry!");
                    scan.next();
                }
            }

            // edits arraylist element.
            nurses.get(location).setName(new_name);
            nurses.get(location).setAge(age);
            nurses.get(location).setSex(gender);
            nurses.get(location).setDepartment(department);
            nurses.get(location).setEmployeeSince(employeeSince);

            super.writeToFile(nurses,fileName); // calls method in the Data Handling class. Writed modefied arraylist to file.
        }
    }

    @Override
    public void showSingleEntry(String name){
        super.showSingleEntry(nurses,name); // calls method in the Data Handling class.
    }

    @Override
    public void deleteAllData(){
        super.deleteAllData(nurses,fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void deleteSingleEntry(String name){
        super.deleteSingleEntry(nurses,name,fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.

    }

    @Override
    public void showAllData() {
        super.showAllData(nurses); // calls method in the Data Handling class.
    }

    @Override
    public void readFromFile(){ // method to read from file.
        if(nurses!=null){ // if the arraylist already containts data 
            nurses.clear(); // it would be cleared so the data does not stack over each other.
        }
        ObjectInputStream readNurse = null;
        try{
            readNurse = new ObjectInputStream(new FileInputStream("NurseRecord"));
            while(true){
                Nurse nurse = (Nurse) readNurse.readObject();
                nurses.add(nurse); // adds the read data to arraylist.
            }

        }catch(EOFException ex){
        }catch (FileNotFoundException ex) {
            System.out.println("Error: No File Found");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        finally{
            try {
                if (readNurse != null) {
                    readNurse.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}