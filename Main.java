import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// College Management System - inspired by the class structure shown in the video.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Student student = new Student("Aman", "CS2026", "Computer Science", 2);
        Course course = new Course("Java Programming", "CS-201", 4);
        Admission admission = new Admission("ADM-1001", student, "26-Oct-2026");
        Library library = new Library();
        library.addBook("Core Java");
        library.addBook("Data Structures");

        System.out.println("====================================");
        System.out.println("   COLLEGE MANAGEMENT SYSTEM");
        System.out.println("====================================");

        while (true) {
            System.out.println("\\n----------- MAIN MENU -----------");
            System.out.println("1. Student Information");
            System.out.println("2. Course Information");
            System.out.println("3. Admission Information");
            System.out.println("4. Library Information");
            System.out.println("5. Register New Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String rawChoice = input.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(rawChoice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the menu.");
                continue;
            }

            switch (choice) {
                case 1:
                    student.displayDetails();
                    break;
                case 2:
                    course.displayDetails();
                    break;
                case 3:
                    admission.displayDetails();
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    Student newStudent = registerStudent(input);
                    if (newStudent != null) {
                        student = newStudent;
                        admission = new Admission(
                            "ADM-" + student.getRollNumber(), student, "Not assigned");
                        System.out.println("Student registered successfully.");
                    }
                    break;
                case 0:
                    System.out.println("Thank you for using College Management System.");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Student registerStudent(Scanner input) {
        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();
        System.out.print("Enter roll number: ");
        String rollNumber = input.nextLine().trim();
        System.out.print("Enter department: ");
        String department = input.nextLine().trim();
        System.out.print("Enter semester (1-8): ");
        String semesterText = input.nextLine().trim();

        if (name.isEmpty() || rollNumber.isEmpty() || department.isEmpty()) {
            System.out.println("Name, roll number, and department cannot be empty.");
            return null;
        }

        try {
            int semester = Integer.parseInt(semesterText);
            if (semester < 1 || semester > 8) {
                System.out.println("Semester must be between 1 and 8.");
                return null;
            }
            return new Student(name, rollNumber, department, semester);
        } catch (NumberFormatException e) {
            System.out.println("Semester must be a number.");
            return null;
        }
    }
}

class Admission {
    private final String admissionId;
    private final Student student;
    private final String admissionDate;

    public Admission(String admissionId, Student student, String admissionDate) {
        this.admissionId = admissionId;
        this.student = student;
        this.admissionDate = admissionDate;
    }

    public void displayDetails() {
        System.out.println("\\n------ ADMISSION DETAILS ------");
        System.out.println("Admission ID : " + admissionId);
        System.out.println("Student Name : " + student.getName());
        System.out.println("Roll Number  : " + student.getRollNumber());
        System.out.println("Date         : " + admissionDate);
    }
}

class Student {
    private final String name;
    private final String rollNumber;
    private final String department;
    private final int semester;

    public Student(String name, String rollNumber, String department, int semester) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
        this.semester = semester;
    }

    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getDepartment() { return department; }
    public int getSemester() { return semester; }

    public void displayDetails() {
        System.out.println("\\n-------- STUDENT DETAILS --------");
        System.out.println("Name       : " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Department : " + department);
        System.out.println("Semester   : " + semester);
    }
}

class Course {
    private final String courseName;
    private final String courseCode;
    private final int credits;

    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    public void displayDetails() {
        System.out.println("\\n--------- COURSE DETAILS ---------");
        System.out.println("Course Name : " + courseName);
        System.out.println("Course Code : " + courseCode);
        System.out.println("Credits     : " + credits);
    }
}

class Library {
    private final List<String> books = new ArrayList<>();

    public void addBook(String bookName) {
        if (bookName != null && !bookName.trim().isEmpty()) {
            books.add(bookName.trim());
        }
    }

    public void displayBooks() {
        System.out.println("\\n--------- LIBRARY DETAILS ---------");
        if (books.isEmpty()) {
            System.out.println("No books are currently available.");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}
