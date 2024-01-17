package courseManagement;

public class Course {
    private String courseCode;
    private String name;
    private int maximumCapacity;
    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String name, int maximumCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maximumCapacity = maximumCapacity;
    }

    // Getters for courseCode, name, and maximumCapacity
    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    // Static method to retrieve the total number of enrolled students
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    // Method to enroll a student in the course
    public boolean enrollStudent() {
        if (totalEnrolledStudents < maximumCapacity) {
            totalEnrolledStudents++;
            return true; // Enrollment successful
        } else {
            return false; // Enrollment failed due to reaching maximum capacity
        }
    }

    // Method to drop a student from the course
    public void dropStudent() {
        if (totalEnrolledStudents > 0) {
            totalEnrolledStudents--;
        }
    }
}
