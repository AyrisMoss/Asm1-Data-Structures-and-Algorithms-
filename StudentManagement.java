import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagement {
    static class Student {
        String id;
        String name;
        ArrayList<Double> marks;  // Danh sách điểm của học sinh
        double averageMark;       // Điểm trung bình của học sinh

        // Constructor nhận vào ID và tên
        // Constructor nhận vào ID và tên
        // Constructor nhận vào ID và tên
        Student(String id, String name) {
            this.id = id;
            this.name = name;
            this.marks = new ArrayList<>();
            this.averageMark = 0.0; // Khởi tạo điểm trung bình
        }

        // Tính điểm trung bình
        void calculateAverageMark() {
            if (marks.isEmpty()) {
                averageMark = 0.0; // Nếu không có điểm nào, điểm trung bình là 0
            } else {
                double total = 0;
                for (double mark : marks) {
                    total += mark; // Tính tổng điểm
                }
                averageMark = total / marks.size(); // Tính điểm trung bình
            }
        }

        String getRanking() {
            calculateAverageMark();  // Tính điểm trung bình trước khi xếp hạng
            if (averageMark < 5.0) return "Fail";                       // [0 – 5.0)
            else if (averageMark >= 5.0 && averageMark < 6.5) return "Medium"; // [5.0 – 6.5)
            else if (averageMark >= 6.5 && averageMark < 7.5) return "Good";   // [6.5 – 7.5)
            else if (averageMark >= 7.5 && averageMark < 9.0) return "Very Good"; // [7.5 – 9.0)
            else return "Excellent";                                      // [9.0 – 10.0]
        }

        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Average Marks: " + averageMark + ", Rank: " + getRanking();
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nStudent Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent(students, scanner);
                    break;
                case 2:
                    editStudent(students, scanner);
                    break;
                case 3:
                    deleteStudent(students, scanner);
                    break;
                case 4:
                    sortStudents(students);
                    break;
                case 5:
                    searchStudent(students, scanner);
                    break;
                case 6:
                    displayStudents(students);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    public static void addStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.println("Enter student ID:");
        String id = scanner.nextLine();
        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        Student student = new Student(id, name);  // Khởi tạo sinh viên mới

        // Nhập điểm cho các môn học
        System.out.println("Enter number of subjects:");
        int numberOfSubjects = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng mới còn lại

        for (int i = 0; i < numberOfSubjects; i++) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.println("Enter marks for subject " + (i + 1) + ":");
                    double mark = scanner.nextDouble();
                    scanner.nextLine(); // Đọc bỏ dòng mới còn lại
                    student.marks.add(mark); // Thêm điểm vào danh sách
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for marks.");
                    scanner.next(); // Clear invalid input
                }
            }
        }

        student.calculateAverageMark(); // Tính điểm trung bình ngay sau khi thêm điểm
        students.add(student);
        System.out.println("Student added successfully!");
    }


    public static void editStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.println("Enter student ID to edit:");
        String id = scanner.nextLine();

        for (Student student : students) {
            if (student.id.equals(id)) {
                System.out.println("Enter new student name:");
                student.name = scanner.nextLine();

                // Xóa điểm cũ và nhập lại điểm mới
                student.marks.clear();
                System.out.println("Enter number of subjects:");
                int numberOfSubjects = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng mới còn lại

                for (int i = 0; i < numberOfSubjects; i++) {
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.println("Enter marks for subject " + (i + 1) + ":");
                            double mark = scanner.nextDouble();
                            scanner.nextLine(); // Đọc bỏ dòng mới còn lại
                            student.marks.add(mark);
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for marks.");
                            scanner.next(); // Clear invalid input
                        }
                    }
                }

                System.out.println("Student details updated successfully!");
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
    }

    public static void deleteStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.println("Enter student ID to delete:");
        String id = scanner.nextLine();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id.equals(id)) {
                students.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
    }

    public static void sortStudents(ArrayList<Student> students) {
        students.sort((s1, s2) -> Double.compare(s2.averageMark, s1.averageMark));
        System.out.println("Students sorted by average marks successfully!");
    }

    public static void searchStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.println("Enter student ID to search:");
        String id = scanner.nextLine();

        for (Student student : students) {
            if (student.id.equals(id)) {
                System.out.println("Student found: " + student);
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
    }

    public static void displayStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("\nStudent Information:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
