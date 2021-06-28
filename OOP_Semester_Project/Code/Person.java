package final_project;

import java.io.Serializable;

// The parent class of Doctor, Nurse, OtherStaff and Patients. Containing their shared attributes.

public class Person implements Serializable {
    
    private String name;
    private int age;
    private String sex;

    public Person(){};

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String  getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Name: " +name + "\nAge: " + age + "\nGender: " + sex+"\n";
    }
}