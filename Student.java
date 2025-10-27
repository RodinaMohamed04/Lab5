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
    public Student(String fullName, int age, String gender, String department, double gpa) {
        this.id = nextID.getAndIncrement();
        setFullName(fullName);
        setAge(age);
        setGender(gender);
        setDepartment(department);
        setGpa(gpa);

    }

    // Overloaded Constructor with ID (for loading from file)
    public Student(int id, String fullName, int age, String gender, String department, double gpa) {
        this.id = id;
        setFullName(fullName);
        setAge(age);
        setGender(gender);
        setDepartment(department);
        setGpa(gpa);
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

        if (age < 17 || age > 25) {
            new IllegalArgumentException("Age must be between 17 and 25");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            new IllegalArgumentException("Gender cannot be empty");
        }
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            new IllegalArgumentException("Department cannot be empty");
        }
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

    @Override
public String Info() {
    return getId() + "," + getFullName() + "," + getAge() + "," + getGender() + "," + getDepartment() + "," + getGpa();
}


    @Override
    public String getSearchKey() {
        return String.valueOf(getId());
    }
}
