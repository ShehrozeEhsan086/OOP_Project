package final_project;

import java.io.Serializable;

// Doctor class having attribute speific to doctors with getter and setter methods.

public class Doctor extends Person implements Serializable {
    
    private String specialization;
    private int employeeSince;

    public Doctor(){
        super();
    }

    public Doctor(String specialization, int employeeSince, String name, int age, String  sex) {
        super(name, age, sex);
        this.specialization = specialization;
        this.employeeSince = employeeSince;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getEmployeeSince() {
        return employeeSince;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setEmployeeSince(int employeeSince) {
        this.employeeSince = employeeSince;
    }

    @Override
    public String toString() {
        return super.toString() + "Specialization: " + specialization + "\nEmployee Since: " + employeeSince;
    }
}