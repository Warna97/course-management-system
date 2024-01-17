package courseManagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int id;
    private Map<Course, String> enrolledCourses;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter for ID (No setter, as student ID should not change)

    public int getId() {
        return id;
    }

    // Getter for enrolledCourses
    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses.keySet());
    }

    // Method to enroll students in courses
    public void enrollInCourse(Course course) {
        enrolledCourses.put(course, null);
    }

    // Method to assign grades to students
    public void assignGrade(Course course, String grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
        }
    }
 // Add a method to get the grade for a specific course
    public String getGradeForCourse(Course course) {
        return enrolledCourses.get(course);
    }
}


