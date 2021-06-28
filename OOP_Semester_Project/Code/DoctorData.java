package final_project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorData extends DataHandling implements DataManipulation{

    private ArrayList<Doctor> doctors;
    private final String fileName = "DoctorRecord";
    Scanner scan = new Scanner(System.in);

    public DoctorData() {
        doctors = new ArrayList();
        readFromFile();
    }

    public void doctorTerminal(){
        String name;
        boolean menuStatus = true;
        int choice;
        do{
            System.out.println();
            System.out.println("[[DOCTORS]]");
            System.out.println("[1] Add New Record.");
            System.out.println("[2] Show All Records.");
            System.out.println("[3] Show Single Record.");
            System.out.println("[4] Delete All Records.");
            System.out.println("[5] Delete Single Record.");
            System.out.println("[6] Edit a Record.");
            System.out.println("[0] Return to Main Menu.");
            while (true) {
                try { // handles exception as only numeric entry is allowed.
                    System.out.print("Enter option: ");
                    choice = scan.nextInt();
                    break;
                } catch (InputMismatchException ex) {
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
                    System.out.println("[[DOCTORS DATA]]");
                    showAllData();
                    break;
                case 3:
                    System.out.print("Enter name of doctor to find record: ");
                    name = scan.nextLine();
                    showSingleEntry(name);
                    break;
                case 4:
                    boolean delete = super.deleteStatusCheck(); // method that confirm if the user wants to delete.
                    if(delete)
                        deleteAllData();
                    break;
                case 5:
                    System.out.print("Enter name of Doctor to delete record: ");
                    name = scan.nextLine();
                    deleteSingleEntry(name);
                    break;
                case 6:
                    System.out.print("Enter name of Doctor to edit record: ");
                    name = scan.nextLine();
                    editRecord(name);
                    break;
                default:
                    System.out.println("Wrong entry please select again.");
            }
        }while(menuStatus);
    }

    @Override
    public void addData(){ // adds new data.
        String name,specialization,gender;
        int age,employeeSince;
        System.out.println();
        System.out.println("[[ADD DATA]]");
        while(true){
            System.out.print("Enter name of the Doctor: ");
            name = scan.nextLine();
            if(checkString(name)){
                break;
            }
        }
        while (true) {
            try {
                System.out.print("Enter age of the Doctor: ");
                age = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("System expects numeric entry!");
                scan.next();
            }
        }
        scan.nextLine();
        while(true){
            System.out.print("Enter gender of the Doctor (\"male\" or \"female\"): ");
            gender = scan.next().toLowerCase();
            if(gender.equals("male") || gender.equals("female")){
                break;
            }
            else
                System.out.println("Please enter suitable gender.");
        }
        scan.nextLine();
        while(true){
            System.out.print("Enter Specialization of the Doctor: ");
            specialization = scan.nextLine();
            if(checkString(specialization)){
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
        doctors.add(new Doctor(specialization,employeeSince,name,age,gender)); // adds to arraylist.
        super.writeToFile(doctors, fileName); // calls method in the Data Handling class.
    }

    @Override
    public void editRecord(String name){ // edits data
        String new_name,specialization,gender;
        int age,employeeSince;
        int location = -1;
        location = super.findSingleRecord(doctors,name);
        if(location == -1){
            System.out.println("\tDoctor not found\n");
        }
        else{
            System.out.println("[[EDIT]]");
            System.out.println("Old Data: ");
            showSingleEntry(doctors.get(location).getName());
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
            while(true){
                System.out.print("Enter Specialization of the Doctor: ");
                specialization = scan.nextLine();
                if(checkString(specialization)){
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
            
            // edits arraylist element.
            doctors.get(location).setName(new_name);
            doctors.get(location).setAge(age);
            doctors.get(location).setSex(gender);
            doctors.get(location).setSpecialization(specialization);
            doctors.get(location).setEmployeeSince(employeeSince);

            super.writeToFile(doctors, fileName); // calls method in the Data Handling class. Writed modefied arraylist to file.
        }
    }

    @Override
    public void showSingleEntry(String name){
        super.showSingleEntry(doctors,name); // calls method in the Data Handling class.
    }

    @Override
    public void deleteAllData(){
        super.deleteAllData(doctors, fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void deleteSingleEntry(String name){ 
        super.deleteSingleEntry(doctors,name,fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void showAllData() {
        super.showAllData(doctors); // calls method in the Data Handling class.
    }

    @Override
    public void readFromFile(){ // method to read from file.
        if(doctors!=null){ // if the arraylist already containts data 
            doctors.clear(); // it would be cleared so the data does not stack over each other.
        }
        ObjectInputStream readDoctor = null;
        try{
            readDoctor = new ObjectInputStream(new FileInputStream("DoctorRecord"));
            while(true){
                Doctor doctor = (Doctor) readDoctor.readObject();
                this.doctors.add(doctor); // adds the read data to arraylist. 
            }
        }catch(EOFException ex){
        }catch (FileNotFoundException ex) {
            System.out.println("Error: No File Found");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        finally{
            try {
                if (readDoctor != null) {
                    readDoctor.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}