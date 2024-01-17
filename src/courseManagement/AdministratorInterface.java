package courseManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        while (true) {
            System.out.println("\nCourse Enrollment and Grade Management System");
            System.out.println("1. Add a new course");
            System.out.println("2. Enroll a student");
            System.out.println("3. Assign grades");
            System.out.println("4. Calculate overall course grades");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1-5).");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter maximum capacity: ");
                    int maxCapacity;
                    try {
                        maxCapacity = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (Exception e) {
                        System.out.println("Invalid input for maximum capacity.");
                        scanner.nextLine(); // Consume the invalid input
                        continue;
                    }
                    Course newCourse = new Course(courseCode, courseName, maxCapacity);
                    courses.add(newCourse);
                    System.out.println("Course added successfully.");
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int studentId;
                    try {
                        studentId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (Exception e) {
                        System.out.println("Invalid input for student ID.");
                        scanner.nextLine(); // Consume the invalid input
                        continue;
                    }

                    Student enrolledStudent = findStudentById(students, studentId);

                    if (enrolledStudent != null) {
                        System.out.print("Enroll a student in a course (course code): ");
                        String enrollCourseCode = scanner.nextLine();
                        Course enrollCourse = findCourseByCode(courses, enrollCourseCode);

                        if (enrollCourse != null) {
                            if (enrollCourse.enrollStudent()) {
                                System.out.println("Student enrolled successfully in " + enrollCourseCode);
                            } else {
                                System.out.println("Enrollment failed. Course is at maximum capacity.");
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int studentIdForGrades;
                    try {
                        studentIdForGrades = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (Exception e) {
                        System.out.println("Invalid input for student ID.");
                        scanner.nextLine(); // Consume the invalid input
                        continue;
                    }

                    Student studentForGrades = findStudentById(students, studentIdForGrades);

                    if (studentForGrades != null) {
                        // Add code to assign grades to courses for this student
                        assignGradesToCourses(studentForGrades, courses, scanner);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID to calculate overall grade: ");
                    int studentIdForOverallGrade;
                    try {
                        studentIdForOverallGrade = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (Exception e) {
                        System.out.println("Invalid input for student ID.");
                        scanner.nextLine(); // Consume the invalid input
                        continue;
                    }

                    Student studentForOverallGrade = findStudentById(students, studentIdForOverallGrade);

                    if (studentForOverallGrade != null) {
                        // Add code to calculate and display the overall course grade for this student
                        calculateAndDisplayOverallGrade(studentForOverallGrade, scanner);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please select a valid option (1-5).");
            }
        }
    }

    // Helper method to find a course by course code
    private static Course findCourseByCode(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null; // Course not found
    }

    // Helper method to find a student by ID
    private static Student findStudentById(List<Student> students, int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null; // Student not found
    }

 // Add a method to assign grades to courses for a student
    private static void assignGradesToCourses(Student student, List<Course> courses, Scanner scanner) {
        System.out.println("Assigning Grades to Courses for Student: " + student.getName() + " (ID: " + student.getId() + ")");
        System.out.println("Enrolled Courses for " + student.getName() + ":");

        List<Course> enrolledCourses = student.getEnrolledCourses();

        for (int i = 0; i < enrolledCourses.size(); i++) {
            Course course = enrolledCourses.get(i);
            System.out.println((i + 1) + ". " + course.getName() + " (Course Code: " + course.getCourseCode() + ")");
        }

        System.out.print("Select the course to assign a grade (1-" + enrolledCourses.size() + "): ");
        
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (courseChoice >= 1 && courseChoice <= enrolledCourses.size()) {
            Course selectedCourse = enrolledCourses.get(courseChoice - 1);

            System.out.print("Enter the grade (A, B, C, D, F) for " + selectedCourse.getName() + ": ");
            String grade = scanner.nextLine();

            // Check that the grade is valid (A, B, C, D, or F)
            if (isValidGrade(grade)) {
                CourseManagement.assignGrade(student, selectedCourse, grade);
                System.out.println("Grade assigned successfully.");
            } else {
                System.out.println("Invalid grade. Please enter a valid grade (A, B, C, D, F).");
            }
        } else {
            System.out.println("Invalid course selection.");
        }
    }

    // Helper method to check if a grade is valid
    private static boolean isValidGrade(String grade) {
        return grade != null && grade.matches("[ABCDF]");
    }

    // Add a method to calculate and display the overall course grade for a student
    private static void calculateAndDisplayOverallGrade(Student student, Scanner scanner) {
        System.out.println("Calculating Overall Course Grade for Student: " + student.getName() + " (ID: " + student.getId() + ")");
        
        double overallGrade = CourseManagement.calculateOverallGrade(student);
        System.out.println("Overall Course Grade for " + student.getName() + ": " + overallGrade);
    }
}
