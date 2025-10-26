package Backend;

import java.util.ArrayList;
import java.util.Collections;

public class ManagerRole {

    private StudentDataBase db;

    public ManagerRole() {
        db = new StudentDataBase("Students");
        System.out.println("Hello from reading");
        try {
            db.readFromFile();
        } catch (Exception e) {
            System.out.println("Error reading file."); // show error if file not found or empty
        }

    }

    public void refresh() {
        db.readFromFile();
    }

    public void addStudent(String name, int age, String gender, String dept, double gpa) {
        Student s = new Student(name, age, gender, dept, gpa);
        db.insertRecord(s);
        System.out.println("Student added successfully!");
    }
    //used in ViewStudents.frontEnd

    public ArrayList<Student> displayStudents() {
        ArrayList<Records> ArrayRecord = db.returnAllrecords();  // get all students from the database (ArrayList)
        ArrayList<Student> ArrayStudent = new ArrayList<>();
        for (Records r : ArrayRecord) {
            ArrayStudent.add((Student) r);  //casting from record(parent) to Student
        }
        return ArrayStudent;
    }

    public ArrayList<Student> displayByID() {
        ArrayList<Records> ArrayRec = db.returnAllrecords();  // get all students from the database (ArrayList)
        ArrayList<Student> ArrayStu = new ArrayList<>();
        for (Records m : ArrayRec) {
            ArrayStu.add((Student) m);  //casting from record(parent) to Student
        }
        Collections.sort(ArrayStu, (x, y) -> Integer.compare(x.getId(), y.getId()));
        return ArrayStu;
    }

    public ArrayList<Student> displayByName() {
        ArrayList<Records> Arrayrecord = db.returnAllrecords();  // get all students from the database (ArrayList)
        ArrayList<Student> Arraystudent = new ArrayList<>();
        for (Records n : Arrayrecord) {
            Arraystudent.add((Student) n);  //casting from record(parent) to Student
        }
        Collections.sort(Arraystudent, (x, y) -> x.getFullName().compareToIgnoreCase(y.getFullName()));
        return Arraystudent;
    }

    public void updateStudent(int id, String name, int age, String gender, String dept, double gpa, String key) {

        Student newS = new Student(id, name, age, gender, dept, gpa);
        db.updateRecord(key, newS);
        System.out.println("Student updated successfully!");
    }

    public void deleteStudent(String key) {
        db.deleteRecord(key);
        System.out.println("Student removed successfully!");
    }

    public Records searchByID(String id) {
        return db.getRecord(id);
    }

    public Records searchByName(String name) {
        return db.getRecord(name);
    }

    public void logout() {
        db.saveToFile();
        System.out.println("All data saved successfully. Logging out...");
    }

}
