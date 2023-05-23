import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

    public void addStudent(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", true))) {
            writer.write(student.toString());
            writer.newLine();
        }
    }

    public Collection<Student> getStudents() throws IOException {
        Collection<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Student student = Student.parse(line);
                    students.add(student);
                } catch (Exception e) {
                    // Ignoruj błędne linie w pliku
                }
            }
        }
        return students;
    }

    public Student findStudentByName(String name) throws IOException {
        Collection<Student> students = getStudents();
        for (Student current : students) {
            if (current.getName().equals(name)) {
                return current;
            }
        }
        return null;
    }
}
