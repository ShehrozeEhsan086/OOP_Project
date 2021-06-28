package final_project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientData extends DataHandling implements DataManipulation{
    
    private ArrayList<Patient> patients;
    private final String fileName = "PatientRecord";

    Scanner scan = new Scanner(System.in);

    public PatientData() {
        patients = new ArrayList();  
        readFromFile();
    }
    
    public void patientTerminal(){
        String name; 
        boolean menuStatus = true;
        int choice;
        do{
            System.out.println();
            System.out.println("[[PATIENT DATA]]");
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
                    System.out.println("[[PATIENT DATA]]");
                    System.out.println();
                    showAllData();
                    break;
                case 3:
                    System.out.print("Enter name of Patient to find record: ");
                    name = scan.next();
                    showSingleEntry(name);
                    break;
                case 4:
                    boolean delete = super.deleteStatusCheck(); // method that confirm if the user wants to delete.
                    if(delete)
                        deleteAllData();
                    break;
                case 5:
                    System.out.print("Enter name of Patient to delete record: ");
                    name = scan.nextLine();
                    deleteSingleEntry(name);
                    break;
                case 6:
                    System.out.print("Enter name of Patient to edit record: ");
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
        String name,disease, gender;
        int age;
        System.out.println();
        System.out.println("[[ADD DATA]]");
        while(true){
            System.out.print("Enter name of Patient: ");
            name = scan.nextLine();
            if(checkString(name)){
                break;
            }
        }
        while (true) {
            try {
                System.out.print("Enter age of Patient: ");
                age = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.print("System expects numeric entry!");
                scan.next();
            }
        }
        while(true){
            System.out.print("Enter gender of the Patient (\"male\" or \"female\"): ");
            gender = scan.next().toLowerCase();
            if(gender.equals("male") || gender.equals("female")){
                break;
            }
            else
                System.out.println("Please enter suitable Gender");
        }
        scan.nextLine();
        System.out.print("Enter disease of Patient: ");
        disease = scan.nextLine();
        patients.add(new Patient(disease,name,age,gender));
        super.writeToFile(patients,fileName); // calls method in the Data Handling class.
    }

    @Override
    public void editRecord(String name){ //edits the reocrd
        String new_name,disease, gender;
        int age;
        
        int location = -1;
        location = super.findSingleRecord(patients,name);
        if(location == -1){
            System.out.println("\tPatient not found\n");
        } else{
            System.out.println("[[EDIT]]");
            System.out.println("Old Data: ");
            showSingleEntry(patients.get(location).getName());
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
                    System.out.print("System expects numeric entry!");
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
                    System.out.println("Please enter suitable gender.");
            }
            scan.nextLine();
            System.out.print("Disease: ");
            disease = scan.nextLine();
            
            // edits arraylist element.
            patients.get(location).setName(new_name);
            patients.get(location).setAge(age);
            patients.get(location).setSex(gender);
            patients.get(location).setDisease(disease);

            super.writeToFile(patients,fileName); // calls method in the Data Handling class. Writed modefied arraylist to file.
        }
    }

    @Override
    public void showSingleEntry(String name){
        super.showSingleEntry(patients,name); // calls method in the Data Handling class.
    }

    @Override
    public void deleteAllData(){ 
        super.deleteAllData(patients,fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void deleteSingleEntry(String name){
        super.deleteSingleEntry(patients,name,fileName); // calls method in the Data Handling class.
        readFromFile(); // Reads to update its existing arraylist.
    }

    @Override
    public void showAllData() {
        super.showAllData(patients); // calls method in the Data Handling class.
    }

    @Override
    public void readFromFile(){ // method to read from file.
        if(patients!=null){ // if the arraylist already containts data 
            patients.clear(); // it would be cleared so the data does not stack over each other.
        }
        ObjectInputStream readPatient = null;
        try{
            readPatient = new ObjectInputStream(new FileInputStream("PatientRecord"));
            while(true){
                Patient patient = (Patient) readPatient.readObject();
                patients.add(patient); // adds the read data to arraylist.
            }
        }catch(EOFException ex){
        }catch (FileNotFoundException ex) {
            System.out.println("Error: No File Found");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        finally{
            try {
                if (readPatient != null) {
                    readPatient.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}