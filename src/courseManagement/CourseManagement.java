package courseManagement;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();

    // Private constructor to prevent instantiation
    private CourseManagement() {
    }

    // Static method to add a new course
    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courses.add(newCourse);
    }

    // Static method to enroll a student in a course
    public static boolean enrollStudent(Student student, Course course) {
        return course.enrollStudent();
    }

    // Static method to assign a grade to a student for a course
    public static void assignGrade(Student student, Course course, String grade) {
        student.assignGrade(course, grade);
    }

    // Static method to calculate the overall course grade for a student
    public static double calculateOverallGrade(Student student) {
        List<Course> enrolledCourses = student.getEnrolledCourses();
        double totalPoints = 0;
        int totalCourses = enrolledCourses.size();

        for (Course course : enrolledCourses) {
            String grade = student.getGradeForCourse(course);
            if (grade != null) {
                totalPoints += calculateGradePoints(grade);
            }
        }

        return totalCourses > 0 ? totalPoints / totalCourses : 0;
    }

    // Helper method to calculate grade points
    private static double calculateGradePoints(String grade) {
        //  (e.g., A=4.0, B=3.0, C=2.0, etc.)
        // let's assume a basic grading scale here:
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0; // Default to 0.0 for unknown grades
        }
    }
}


