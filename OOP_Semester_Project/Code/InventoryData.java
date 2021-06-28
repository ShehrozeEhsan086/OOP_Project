package final_project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryData extends DataHandling implements DataManipulation{

    private ArrayList<Inventory> inventory;
    private final String fileName = "InventoryRecord";
    Scanner scan = new Scanner(System.in);


    public InventoryData() {
        inventory = new ArrayList();
        readFromFile();
    }

    public void inventoryTerminal(){
        String itemName;
        boolean menuStatus = true;
        int choice;
        do{
            System.out.println();
            System.out.println("[[INVENTORY]]");
            System.out.println("[1] Add New Record.");
            System.out.println("[2] Show All Records.");
            System.out.println("[3] Show Single Record.");
            System.out.println("[4] Delete All Records.");
            System.out.println("[5] Delete Single Record.");
            System.out.println("[6] Edit Single Record.");
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
                    System.out.println("[[INVENTORY DATA]]");
                    this.showAllData();
                    break;
                case 3:
                    System.out.print("Enter name of item to find record: ");
                    itemName = scan.nextLine();
                    this.showSingleEntry(itemName);
                    break;
                case 4: 
                    boolean delete = super.deleteStatusCheck(); // method that confirm if the user wants to delete.
                    if(delete)
                        deleteAllData();
                    break;
                case 5:
                    System.out.print("Enter name of item to delete record: ");
                    itemName = scan.nextLine();
                    this.deleteSingleEntry(itemName);
                    break;
                case 6:
                    System.out.print("Enter name of item to edit: ");
                    itemName = scan.nextLine();
                    this.editRecord(itemName);
                    break;
                default:
                    System.out.println("Wrong entry please select again.");

            }
        }while(menuStatus);
    }

    @Override
    public void addData(){ //Add new data in the file by first adding it in arraylist then in file
        String itemName;
        int numOfItems;
        System.out.println();
        System.out.println("[[ADD DATA]]");
        while(true){
            System.out.print("Name of item: ");
            itemName = scan.nextLine();
            if(checkString(itemName)){
                break;
            }
        }
        while (true) {
            try {
                System.out.print("How many "+ itemName +" do you have: ");
                numOfItems = scan.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("System expects numeric entry!");
                scan.next();
            }
        }
        inventory.add(new Inventory(itemName,numOfItems));
        super.writeToFile(inventory,fileName);
    }

    @Override
    public void editRecord(String name){ //Edits the record
        String newItemName;
        int numOfItems;
        int location = -1;
        location = findSingleRecord(name);
        if(location == -1){
            System.out.println("\tItem not found\n");
        }
        else{
            System.out.println("[[EDIT]]");
            System.out.println("Old Data: ");
            showSingleEntry(inventory.get(location).getName());
            System.out.println("Enter New Data: ");
            while(true){
                System.out.print("Name: ");
                newItemName = scan.nextLine();
                if(checkString(newItemName)){
                    break;
                }
            }
            while (true) {
                try {
                    System.out.print("Current number of "+inventory.get(location).getName()+": ");
                    numOfItems = scan.nextInt();
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("System expects numeric entry!");
                    scan.next();
                }
            }

            inventory.get(location).setName(newItemName);
            inventory.get(location).setNumOfItems(numOfItems);

            super.writeToFile(inventory,fileName);
        }
    }

    @Override
    public void showSingleEntry(String itemName){ //show single entry in file
        int location = -1;
        location = findSingleRecord(itemName);
        if(location == -1){
            System.out.println("\tItem not found\n");
        }
        else{
            System.out.println();
            System.out.println("--------------------------");
            System.out.println(inventory.get(location));
            System.out.println("--------------------------");
        }
    }

    @Override
    public void deleteAllData(){ //delete all data from the file
        super.deleteAllData(inventory,fileName);
        readFromFile();
    }

    @Override
    public void deleteSingleEntry(String itemName){ //Deletes a specific entry by findinf its location by name
        int location = -1;
        location = findSingleRecord(itemName);
        if(location == -1){
            System.out.println("\tItem not found\n");
        }
        else{
            inventory.remove(location);
            super.writeToFile(inventory,fileName);
            System.out.println("\tData successfully deleted\n");

        }
    }

    @Override
    public void showAllData() {
        super.showAllData(inventory); // calls method in the Data Handling class.
    }


    public int findSingleRecord(String itemName) {
        int location = 0;
        boolean status = false;
        if(itemName.isEmpty()){
            status = false;
        } else{
            for(int i=0;i<inventory.size();i++){
                if(inventory.get(i).getName().equals(itemName)){
                    status = true;
                    break;
                }
                location++;
            }
        }
        if(status)
            return location;
        else
            return -1;
    }

    public void readFromFile(){ // method to read from file.
        if(inventory!=null){ // if the arraylist already containts data 
            inventory.clear();// it would be cleared so the data does not stack over each other.
        }
        ObjectInputStream readInventory = null;
        try{
            readInventory = new ObjectInputStream(new FileInputStream("InventoryRecord"));
            while(true){
                Inventory invent = (Inventory) readInventory.readObject();
                this.inventory.add(invent); // adds the read data to arraylist. 
            }
        }catch(EOFException ex){
        }catch (FileNotFoundException ex) {
            System.out.println("Error: No File Found");
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        finally{
            try {
                if (readInventory != null) {
                    readInventory.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}