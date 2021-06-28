package final_project;

import java.io.Serializable;

// OtherStadd class having attribute speific to staff members with getter and setter methods.

public class OtherStaff extends Person implements Serializable {
    
    private String designation;
    private int employeeSince;

    public OtherStaff(String designation, int employeeSince, String name, int age, String sex) {
        super(name, age, sex);
        this.designation = designation;
        this.employeeSince = employeeSince;
    }

    public String getDesignation() {
        return designation;
    }

    public int getEmployeeSince() {
        return employeeSince;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setEmployeeSince(int employeeSince) {
        this.employeeSince = employeeSince;
    }

    @Override
    public String toString() {
        return super.toString() + "Designation: " + designation + "\nEmployee Since: " + employeeSince;
    }
}