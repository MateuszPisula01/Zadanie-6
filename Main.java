import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int ex = menu();
                switch (ex) {
                    case 1:
                        exercise1();
                        break;
                    case 2:
                        exercise2();
                        break;
                    case 3:
                        exercise3();
                        break;
                    default:
                        return;
                }
            } catch (IOException e) {
                System.out.println("Błąd wejścia-wyjścia: " + e.getMessage());
            } catch (Student.WrongStudentNameException e) {
                System.out.println(e.getMessage());
            } catch (Student.WrongAgeException e) {
                System.out.println(e.getMessage());
            } catch (Student.WrongDateOfBirthException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                System.out.println("Błąd parsowania: " + e.getMessage());
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static void exercise1() throws IOException, ParseException, Student.WrongStudentNameException,
            Student.WrongAgeException, Student.WrongDateOfBirthException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if (name.contains(" ")) {
            throw new Student.WrongStudentNameException();
        }

        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYYY");
        String dateStr = scan.nextLine();

        Student student = Student.parse(name + " " + age + " " + dateStr);
        Service service = new Service();
        service.addStudent(student);
    }

    public static void exercise2() throws IOException {
        Service service = new Service();
        var students = service.getStudents();
        for (Student current : students) {
            System.out.println(current.toString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();

        Service service = new Service();
        var wanted = service.findStudentByName(name);
        if (wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.toString());
        }
    }
}
