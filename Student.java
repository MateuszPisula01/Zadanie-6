import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String name;
    private int age;
    private Date dateOfBirth;

    public Student(String name, int age, Date dateOfBirth) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(dateOfBirth);
        return name + " " + Integer.toString(age) + " " + formattedDate;
    }

    public static Student parse(String str) throws ParseException, WrongAgeException, WrongDateOfBirthException {
        String[] data = str.split(" ");
        if (data.length != 3) {
            throw new ParseException("Invalid student data format", 0);
        }

        String name = data[0];

        int age;
        try {
            age = Integer.parseInt(data[1]);
            if (age <= 0) {
                throw new WrongAgeException();
            }
        } catch (NumberFormatException e) {
            throw new WrongAgeException();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        Date dateOfBirth;
        try {
            dateOfBirth = sdf.parse(data[2]);
        } catch (ParseException e) {
            throw new WrongDateOfBirthException();
        }

        return new Student(name, age, dateOfBirth);
    }

    static class WrongStudentNameException extends Exception {
        public WrongStudentNameException() {
            super("Błędne imię studenta!");
        }
    }

    static class WrongAgeException extends Exception {
        public WrongAgeException() {
            super("Błędny wiek studenta!");
        }
    }

    static class WrongDateOfBirthException extends Exception {
        public WrongDateOfBirthException() {
            super("Błędna data urodzenia studenta!");
        }
    }
}
