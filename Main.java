package Backend;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StudentDataBase db = new StudentDataBase("Students");
        ManagerRole manager = new ManagerRole();

        int choice = -1;

        System.out.println("=====================================");
        System.out.println("     Welcome to Student System");
        System.out.println("=====================================");

        do {
            System.out.println("\n--------------- MENU ---------------");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Display Students Sorted by ID");
            System.out.println("4. Display Students Sorted by Name");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Search Student by Name");
            System.out.println("7. Update Student");
            System.out.println("8. Delete Student");
            System.out.println("9. Logout and Save");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.print("Enter Student Full Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Age: ");
                    int age = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Gender: ");
                    String gender = input.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = input.nextLine();

                    System.out.print("Enter GPA: ");
                    double gpa = input.nextDouble();

                    manager.addStudent(name, age, gender, dept, gpa);
                    break;

                case 2:
                    System.out.println("\n--- All Students ---");
                    ArrayList<Student> all = manager.displayStudents();
                    for (Student s : all) {
                        System.out.println(s.Info());
                    }
                    break;

                case 3:
                    System.out.println("\n--- Students Sorted by ID ---");
                    ArrayList<Student> byId = manager.displayByID();
                    for (Student s : byId) {
                        System.out.println(s.Info());
                    }
                    break;

                case 4:
                    System.out.println("\n--- Students Sorted by Name ---");
                    ArrayList<Student> byName = manager.displayByName();
                    for (Student s : byName) {
                        System.out.println(s.Info());
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID to search: ");
                    String id = input.nextLine();
                    Records r1 = manager.searchByID(id);
                    if (r1 != null) {
                        System.out.println(r1.Info());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Student Name to search: ");
                    String sName = input.nextLine();
                    Records r2 = manager.searchByName(sName);
                    if (r2 != null) {
                        System.out.println(r2.Info());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 7:
                    System.out.print("Enter Student ID to update: ");
                    String updateKey = input.nextLine();

                    System.out.print("Enter new ID: ");
                    int newID = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter new Full Name: ");
                    String newName = input.nextLine();

                    System.out.print("Enter new Age: ");
                    int newAge = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter new Gender: ");
                    String newGender = input.nextLine();

                    System.out.print("Enter new Department: ");
                    String newDept = input.nextLine();

                    System.out.print("Enter new GPA: ");
                    double newGpa = input.nextDouble();

                    manager.updateStudent(newID, newName, newAge, newGender, newDept, newGpa, updateKey);
                    break;

                case 8:
                    System.out.print("Enter Student ID to delete: ");
                    String delKey = input.nextLine();
                    manager.deleteStudent(delKey);
                    break;

                case 9:
                    manager.logout();
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (choice != 9);

        input.close();
    }
}
