package model;

/**
 * StudentCourse class
 */
public class StudentCourse {
    private String courseID;
    private int semesterNum;
    private String status;
    private int grade;
    private boolean passed;

    /**
     * Creates a new StudentCourse instance
     * 
     * @param courseID    | the course's ID
     * @param semesterNum | the semester the student plans to take the course
     * @param status      | whether the course is planned/completed/incomplete
     * @param grade       | the student's grade
     * @param passed      | whether the course was passed or not
     */
    public StudentCourse(String courseID, int semesterNum, String status, int grade, boolean passed) {
        this.courseID = courseID;
        this.semesterNum = semesterNum;
        this.status = status;
        this.grade = grade;
        this.passed = passed;
    }

    // Getters and Setters
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getSemesterNum() {
        return semesterNum;
    }

    public void setSemesterNum(int semesterNum) {
        this.semesterNum = semesterNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
