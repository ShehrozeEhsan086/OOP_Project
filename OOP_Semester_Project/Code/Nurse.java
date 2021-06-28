package final_project;

import java.io.Serializable;

// Nurse class having attribute speific to nurses with getter and setter methods.

public class Nurse extends Person implements Serializable{
    
    private String department;
    private int employeeSince;

    public Nurse(String department, int employeeSince, String name, int age, String sex) {
        super(name, age, sex);
        this.department = department;
        this.employeeSince = employeeSince;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmployeeSince(int employeeSince) {
        this.employeeSince = employeeSince;
    }

    public String getDepartment() {
        return department;
    }

    public int getEmployeeSince() {
        return employeeSince;
    }

    @Override
    public String toString() {
        return super.toString() + "Department: " + department + "\nEmployee Since: " + employeeSince;
    }
}