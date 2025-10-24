package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

//abstract class to represent a generic database
//responsible for reading, writing, and managing records
public abstract class DataBase {
    private ArrayList<Records> records; //list to store records
    private String fileName;

    //constructor
    public DataBase(String fileName) {
        this.fileName = fileName;
        this.records = new ArrayList<>();
    }

    //abstract method to create a record from a line in the file
    //to be implemented by subclasses **because different record types have different formats**
    public abstract Records createRecord(String line);

    //method to read records from file
    public void readFromFile() {
        records.clear();
        File file = new File(fileName + ".txt");
        if (!file.exists()) {
            return;
        }
        try (Scanner scan = new Scanner(new FileReader(file))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Records x = createRecord(line);
                records.add(x);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();


        }

    }

    //method to save records to file
    public void saveToFile() {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(fileName + ".txt")) {
            for (Records record : records) { //records is the arraylist
                fileWriter.write(record.Info()+ "\n");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    //method to return all records
    public ArrayList<Records> returnAllrecords() {
        return new ArrayList<>(records);
    }

    //method to check if a record with the given key exists <handle duplicated info>
    public boolean contains(String key) {
        for (Records record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    //method to get a record by key <Using Student Id>
    public Records getRecord(String key) {
        for (Records record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    //method to insert a new record and check for duplicates **//by calling contains method
    public void insertRecord(Records record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
            saveToFile();
        }
    }

    //method to delete a record by key
    public void deleteRecord(String key) {
        Records record = null; // to hold the record to be removed
        for (Records r : records) {
            if (r.getSearchKey().equals(key)) {
                record = r; // found the record to remove
                break;
            }
        }
        if (record != null) {
            records.remove(record); // remove the record from the list
            saveToFile();
        } else {
            System.out.println("Record with key " + key + " not found.");
        }
    }

    //method to update a record by key
    public void updateRecord(String key, Records newRecord) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                records.set(i, newRecord);
                saveToFile();
                return;
            }
        }
        System.out.println("Record with key " + key + " not found.");
    }
}




