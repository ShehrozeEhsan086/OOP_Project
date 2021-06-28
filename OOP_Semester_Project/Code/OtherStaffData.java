package final_project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OtherStaffData extends DataHandling implements DataManipulation{
    
    private ArrayList<OtherStaff> otherStaffs;
    private final String fileName = "OtherStaffRecord";
    Scanner scan = new Scanner(System.in);

    public OtherStaffData() {
        otherStaffs = new ArrayList();  
        readFromFile();
    }
    
    public void otherStaffTerminal(){
        String name;
        boolean menuStatus = true;
        int choice;
        do{
            System.out.println();
            System.out.println("[[STAFF DATA]]");
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
                    System.out.println("[[STAFF DATA]]");
                    showAllData();
                    break;
                case 3:
                    System.out.print("Enter name of Staff member to find record: ");
                    name = scan.nextLine();
                    showSingleEntry(name);
                    break;
                case 4:
                    boolean delete = super.deleteStatusCheck(); // method that confirm if the user wants to delete.
                    if(delete)
                        deleteAllData();
                    break;
                case 5:
                    System.out.print("Enter name of Staff member to delete record: ");
                    name = scan.nextLine();
                    deleteSingleEntry(name);
                    break;
                case 6:
                    System.out.print("Enter name of Staff Member to edit record: ");
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
        String name,designation, gender;
        int age,employeeSince;
        System.out.println();
        System.out.println("[[ADD DATA]]");
        while(true){
            System.out.print("Enter name of Staff member: ");
            name = scan.nextLine();
            if(checkString(name)){
                break;
            }
        }
        while (true) {
            try {
                System.out.print("Enter age of the Staff member: ");
                age = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("System expects numeric entry!");
                scan.next();
            }
        }
        while(true){
            System.out.print("Enter gender of the Staff member (\"male\" or \"female\"): ");
            gender = scan.next().toLowerCase();
            if(gender.equals("male") || gender.equals("female")){
                break;
            }
            else
                System.out.println("Please enter suitable Gender");
        }
        scan.nextLine();
        while(true){
            System.out.print("Enter Designation of the Staff member: ");
            designation = scan.nextLine();
            if(checkString(designation)){
                break;
            }
        }
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
        otherStaffs.add(new OtherStaff(designation,employeeSince,name,age,gender));
        super.writeToFile(otherStaffs,fileName);// calls method in the Data Handling class.
    }

    @Override
    public void editRecord(String name){ //edits the record
        String new_name,designation, gender;
        int age,employeeSince;
        int location = -1;
        location = super.findSingleRecord(otherStaffs,name);
        if(location == -1){
            System.out.println("\tStaff Member not found\n");
        }
        else{
            System.out.println("[[EDIT]]");
            System.out.println("Old Data: ");
            showSingleEntry(otherStaffs.get(location).getName());
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
            while(true){
                System.out.print("Gender: ");
                gender = scan.next().toLowerCase();
                if(gender.equals("male") || gender.equals("female")){
                    break;
                }
                else
                    System.out.println("Please enter suitable Gender");
            }
            scan.nextLine();
            while(true){
                System.out.print("Designation: ");
                designation = scan.nextLine();
                if(checkString(designation)){
                    break;
                }
            }
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
            otherStaffs.get(location).setName(new_name);
            otherStaffs.get(location).setAge(age);
            otherStaffs.get(location).setDesignation(designation);
            otherStaffs.get(location).setEmployeeSince(employeeSince);
            otherStaffs.get(location).setSex(gender);

            super.writeToFile(otherStaffs,fileName); // calls method in the Data Handling class. Writed modefied arraylist to file.
        }
    }
    
    @Override
    public void showSingleEntry(String name){
        super.showSingleEntry(otherStaffs,name); // calls method in the Data Handling class.
    }
    
    @Override
    public void deleteAllData(){
        super.deleteAllData(otherStaffs, fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void deleteSingleEntry(String name){
        super.deleteSingleEntry(otherStaffs,name,fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void showAllData() {
        super.showAllData(otherStaffs); // calls method in the Data Handling class.
    }

    @Override
    public void readFromFile(){ // method to read from file.
        if(otherStaffs!=null){ // if the arraylist already containts data 
            otherStaffs.clear(); // it would be cleared so the data does not stack over each other.
        }
        ObjectInputStream readOtherStaff = null;
        try{
            readOtherStaff = new ObjectInputStream(new FileInputStream("OtherStaffRecord"));
            while(true){
                OtherStaff otherStaff = (OtherStaff) readOtherStaff.readObject();
                otherStaffs.add(otherStaff); // adds the read data to arraylist.
            }
            
        }catch(EOFException ex){
        }catch (FileNotFoundException ex) {
            System.out.println("Error: No File Found");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        finally{
            try {
                if (readOtherStaff != null) {
                    readOtherStaff.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}