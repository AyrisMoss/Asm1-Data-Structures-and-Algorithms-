import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static class Student {
        String id;
        String name;
        double marks;

        Student(String id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        String getRanking() {
            if (marks < 5.0) return "Fail";
            else if (marks < 6.5) return "Medium";
            else if (marks < 7.5) return "Good";
            else if (marks < 9.0) return "Very Good";
            else return "Excellent";
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRanking();
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Adding students
        System.out.println("Enter the number of students:");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student ID:");
            String id = scanner.nextLine();
            System.out.println("Enter student name:");
            String name = scanner.nextLine();
            System.out.println("Enter student marks:");
            double marks = scanner.nextDouble();
            scanner.nextLine(); // consume the newline
            students.add(new Student(id, name, marks));
        }

        // Display students
        System.out.println("Student Information:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Sort by marks
        students.sort((s1, s2) -> Double.compare(s2.marks, s1.marks));
        System.out.println("\nStudents sorted by marks:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Search for a student by ID
        System.out.println("\nEnter student ID to search:");
        String searchId = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.id.equals(searchId)) {
                System.out.println("Student found: " + student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }

        // Closing scanner
        scanner.close();
    }
}
