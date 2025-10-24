package Backend;

import java.util.concurrent.atomic.AtomicInteger;

public class Student implements Records {
    private static final AtomicInteger nextID = new AtomicInteger(100);
    private int id;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private double gpa;

    // Constructor
    public Student(int id, String fullName, int age, String gender, String department, double gpa) {
        this.id = nextID.getAndIncrement();
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = gpa;

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
             new IllegalArgumentException("Full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.gpa = gpa;
    }

    public String StudentInfo() {
        return "Student ID: " + getId() + ", Name: " + getFullName() + ", Age: " + getAge() + ", Gender: " +
                getGender() + ", Department: " + getDepartment() + ", GPA: " + getGpa();
    }

    public String getSearchKey() {
        return String.valueOf(getId());
    }
}