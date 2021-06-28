package final_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Data handling class which do different filing and data checking operations for classes
public abstract class DataHandling {

    public abstract void readFromFile(); //Abstract method which should be implemented in child classes
    
    /*write to file Method which writes array list of any type of object using wild cards with ArrayList<?> we can 
      generlize arraylist of anytype.
    */
    public void writeToFile(ArrayList<?> objects,String fileName){
        ObjectOutputStream write = null; //Object output strea object to wtire objects in file
        try{
            write = new ObjectOutputStream(new FileOutputStream(fileName)); // file output stream object to create new file
            for (int i = 0; i<objects.size() ; i++){ //loop till size of arraylist that is passed
                write.writeObject(objects.get(i)); //write each object that is stored in the arraylist
            }
        } catch (FileNotFoundException ex) { //exceptions that can occur when handlin file
            System.out.println();
            System.out.println("Error: File Not Found.");
        } catch (IOException ex) {
            System.out.println();
            System.out.println("IO Error.");
        }
        
        //after writing file will be closed
        finally{
            try {
                if (write != null) {
                    write.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Show single record in file using the arraylist respective to that file, ArrayList<? extends Person> is used to get, get functions in person class
    public void showSingleEntry(ArrayList<? extends Person> objects,String name){
        int location = -1;
        location = findSingleRecord(objects,name); //Searches the record and assigne -1  if the reocrd is not found
        if(location == -1){
            System.out.println("\tRecord not found\n"); //if statement to tell the user if record is not found
        }
        else{ // Else will print all the information
            System.out.println();
            System.out.println("--------------------------");
            System.out.println(objects.get(location));
            System.out.println("--------------------------");
        }
    }

    //It will search the record in the array list useing the name of the parson the name should be exactly same
    public int findSingleRecord(ArrayList<? extends Person> objects,String name) {
        int location = 0; //location currantly is 0
        boolean status = false; // boolean variable to deal with if the object found or not
        if(objects.isEmpty()){ // if array list is empty it will turn status to false
            status = false;
        } else{ //else will iterate over the arraylist until it find the location or not found
            for(int i=0;i<objects.size();i++){
                if(objects.get(i).getName().equals(name)){ //if found
                    status = true; //status  becomse true and loop will be break
                    break;
                }
                location++;
            }
        }
        if(status) // location found then it will return the location where data can be found
            return location;
        else // else return -1 to tell that location is not found
            return -1;
    }
    
    //delete single record from the file using array list and then writing that array to file again
    public void deleteSingleEntry(ArrayList<? extends Person> objects,String name,String fileName){
        int location = -1;
        location = findSingleRecord(objects,name); //finds the record location
        if(location == -1){ // location is -1 it means record not found
            System.out.println("\n\tRecord not found\n");
        }
        else{ //else removes that object from arraylist
            objects.remove(location);
            writeToFile(objects,fileName); //write that aray list in the file again with the entry being deleted
            System.out.println("\nRecord is successfully deleted\n");
        }
    }
    
    //deletes all data of a specific file mentioned by the user in function calling
    public void deleteAllData(ArrayList<?> objects,String fileName){
        if(objects.isEmpty()){ //If already empty then will say not found any record
            System.out.println("\n\tThere is no record.\n");
           return;
        } 
        objects.clear(); //else clear all the obejcts from arraylist provided
        writeToFile(objects,fileName); //and write that empty array list which in result empty the file
        System.out.println("\n\tDataBase has been cleared\n");
    }
    
    //shows all data of file of specific objects provided by the user
    public void showAllData(ArrayList<?> objects) {
        if(objects.isEmpty()){ // if not found then simply will say no record
            System.out.println("\nThere is no record.\n");
            return;
        }else{ //else prints all the objects data
            for(int i = 0;i<objects.size();i++){
                System.out.println("--------------------------");
                System.out.println(objects.get(i));
            }
            System.out.println("--------------------------");
        }
    }
    
    //Method which will verfiy if the user want to delete all data or was it a mistake
    public boolean deleteStatusCheck(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Are you sure you want to delete all of the records? This cannot be undone");
        boolean status = true; // boolean variables to handle that operation
        boolean delete = false;
        do{ //until user say correct information
            System.out.print("Choose [Y/N]: ");
            String selection = scan.next();
            if(selection.toLowerCase().equals("y")){ //if y then deletestatus will be true
                delete = true;
            } else if(selection.toLowerCase().equals("n")){ //else it remains false
                System.out.println("Action Aborted.");
            } else{ //else if the user enter anyother chice which is not provided
                System.out.println("Unrecognized input please enter \"Y\" for \"Yes\" and \"N\" for \"No\".");
                status = false;
            }
        } while(!status);
        return delete; //retruns the status
    }

    //checkString method to check if the string includes number or not for example Name does not need digits
    public boolean checkString(String input){
        char[] inputChar = input.toCharArray(); //char array which stroes the string
        for(char c : inputChar){
            if(Character.isDigit(c)){ //if digit found then return false
                System.out.println("Invalid Entry, Must Only Contain Alphabets!");
                return false;
            }
        }
        return true; //else rturn true
    }
}