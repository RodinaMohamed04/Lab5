package Backend;
// StudentDataBase class that extends DataBase to handle student records
// Responsible for creating Student records from file lines
//Rest of the functionality is inherited from DataBase
public  class StudentDataBase extends DataBase{

    public StudentDataBase(String filename) {
        super(filename);
    }

    //to create s student record from a line in the file
    @Override
    public Records createRecord(String line){
        
        String [] parts = line.split(",");
        if(parts.length != 6) {
            new IllegalArgumentException("Invalid record format");
        }
        try
        {
            int id = Integer.parseInt(parts[0].trim());
            String fullName = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            String gender = parts[3].trim();
            String department = parts[4].trim();
            double gpa = Double.parseDouble(parts[5].trim());
            return new Student(id,fullName, age, gender, department, gpa);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid number format in record: " + line);
        }

    }

}