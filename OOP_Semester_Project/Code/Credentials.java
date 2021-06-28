package final_project;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Credentials implements Serializable {

    private String loginId;
    private String password;

    public Credentials() { //Reads data from file. 
        readFile();
    }

    public Credentials(String loginId,String password){
        this.loginId = loginId;
        this.password = password;
        
    }
    
    public void writeToFile(String login, String pass){ // method to write data to file.
        ObjectOutputStream writeLogin = null;
        try{
            writeLogin = new ObjectOutputStream(new FileOutputStream("LoginPass"));
            
            writeLogin.writeObject(new Credentials(login,pass));
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
           
        }
        finally{
            try {
                if (writeLogin != null) {
                    writeLogin.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }         
    }
    
    public void readFile(){ // method to read data from file.
        ObjectInputStream readLogin = null;
        String login,pass;
        try{
            
             readLogin = new ObjectInputStream(new FileInputStream("LoginPass"));
             while(true){
             Credentials c = (Credentials) readLogin.readObject();
                 login =  c.getLoginId();
                 pass = c.getPassword();
                 this.loginId = login;
                 this.password = pass;

             }
        }catch(EOFException ex){
        }catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            System.out.println("error io");
        } catch (ClassNotFoundException ex) {
            System.out.println("error");
        }
        finally{
            try {
                if (readLogin != null) {
                    readLogin.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean loginCheck(){ //checks if there is login and password available.

        if(loginId == null || password == null){
            return false;
        }
        else
            return true;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
    public boolean passwordCheck(String password){ // checks if the password has the allowed pattern. 
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Credentials{" + loginId + " " + password;
    }
}