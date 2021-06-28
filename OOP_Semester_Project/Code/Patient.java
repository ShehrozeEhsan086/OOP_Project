package final_project;

import java.io.Serializable;

// Patient class having attribute speific to patients with getter and setter methods.

public class Patient extends Person implements Serializable {
    
    private String disease;

    public Patient(String disease, String name, int age, String sex) {
        super(name, age, sex);
        this.disease = disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public String toString() {
        return super.toString() + "Disease: " + disease;
    }
}