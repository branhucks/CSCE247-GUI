package model;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Student class represents a student in the system
 */
public class Student extends User {
    private String uuid;
    private String classYear;
    private String advisor;
    private double gpa;
    private String major;
    private String studentID;
    private boolean hasScholarship;
    private int majorProgress;
    private SemesterPlan eightSemesterPlan;
    private String noteFromAdvisor;
    private ApplicationType applicationType;

    /**
     * Creates a new Student instance
     * 
     * @param uuid            | the student's uuid
     * @param username        | username of the student
     * @param firstName       | first name of the student
     * @param lastName        | last name of the student
     * @param userType        | user type of the student
     * @param studentID       | student's ID
     * @param advisor         | the student's advisor
     * @param major           | the UUID of the student's major
     * @param classYear       | the student's year
     * @param gpa             | the student's gpa
     * @param hasScholarship  | whether the student has a scholarship or not
     * @param majorProgress   | the student's major progress
     * @param semesterPlan    | the student's semester plan
     * @param noteFromAdvisor | the notes the advisor places on the student's
     *                        account
     * @param applicationType | the application type of the student's application
     *                        area
     */
    public Student(String uuid, String username, String firstName, String lastName, String userType, String studentID,
            String advisor, String major, String classYear, double gpa, boolean hasScholarship, int majorProgress,
            SemesterPlan eightSemesterPlan, String noteFromAdvisor, ApplicationType applicationType) {
        super(uuid, username, firstName, lastName, userType);
        this.studentID = studentID;
        this.advisor = advisor;
        this.major = major;
        this.classYear = classYear;
        this.gpa = gpa;
        this.hasScholarship = hasScholarship;
        this.majorProgress = majorProgress;
        this.eightSemesterPlan = eightSemesterPlan;
        this.noteFromAdvisor = noteFromAdvisor;
        this.applicationType = applicationType;
    }

    // Getters and Setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (major == null) {
            this.major = null;
        }
        this.major = major;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }

    public double getGPA() {
        return gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public boolean getHasScholarship() {
        return hasScholarship;
    }

    public void setHasScholarship(boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }

    public int getMajorProgress() {
        return majorProgress;
    }

    public void setMajorProgress(int majorProgress) {
        this.majorProgress = majorProgress;
    }

    public SemesterPlan getEightSemesterPlan() {
        if (eightSemesterPlan == null) {
            return new SemesterPlan(new ArrayList<StudentCourse>());
        }
        return eightSemesterPlan;
    }

    public void setSemesterPlan(SemesterPlan eightSemesterPlan) {
        this.eightSemesterPlan = eightSemesterPlan;
    }

    public void setNoteFromAdvisor(String noteFromAdvisor) {
        this.noteFromAdvisor = noteFromAdvisor;
    }

    public String getNoteFromAdvisor() {
        return noteFromAdvisor;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public ApplicationType getApplicationType() {
        if (applicationType == null) {
            return ApplicationType.NULL;
        }
        return applicationType;
    }

    /**
     * Prints the student's degree progression to the console
     * 
     * @param eightSemesterPlan | the student's eight semester plan
     * @param requiredCourses   | the major's required courses
     */
    public void viewProgress(SemesterPlan eightSemesterPlan, ArrayList<Course> requiredCourses,
            ArrayList<Electives> majorElectives, ArrayList<ApplicationArea> applicationAreas, CourseList courseList,
            MajorList majorList) {
        System.out.println("Student: " + getFirstName() + " " + getLastName() + "\tMajor: "
                + majorList.getMajorByUUID(getMajor()).getMajorName() + "\t\tClassification: "
                + this.classYear);
        // Print completed required courses
        System.out.println("\n********* Completed Courses **********");
        for (Course requiredCourse : requiredCourses) {
            for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                if (requiredCourse.courseID().equals(studentCourse.getCourseID())
                        && studentCourse.getStatus().equals("Completed")) {
                    System.out
                            .println("Course ID: " + requiredCourse.courseID() + "\tGrade: " + studentCourse.getGrade()
                                    + "\tPassed: " + studentCourse.getPassed());
                }
            }
        }
        // Print incomplete required courses
        System.out.println("\n********* Incomplete Courses **********");
        for (Course requiredCourse : requiredCourses) {
            for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                if (requiredCourse.courseID().equals(studentCourse.getCourseID())
                        && studentCourse.getStatus().equals("Planned")) {
                    System.out.println("Course ID: " + requiredCourse.courseID());
                }
            }
        }
        // Print Electives progress
        System.out.println("\n********* Elective Progress **********");
        for (Electives electives : majorElectives) {
            String formatString = "%-20s Credits Needed: %d";
            System.out.printf(formatString, electives.getElectiveType() + " Elective", electives.getMinHours());
            System.out.println();
            for (String electiveUUID : electives.getCourses()) {
                for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                    if (studentCourse.getCourseID().equals(courseList.getCourseIDByCourseUUID(electiveUUID))
                            && studentCourse.getStatus().equals("Completed")) {
                        System.out.println("\tCourse ID: " + studentCourse.getCourseID() + "\tGrade: "
                                + studentCourse.getGrade() + "\tPassed " + studentCourse.getPassed());
                    }
                }
            }
        }
        // Print Application Area Progress
        System.out.println("\n********** Application Area Progress **********");
        if (getApplicationType().toString().equals("NULL")) {
            System.out.println("No Application Area Selected.\tCredits Needed: 9");
            return;
        } else {
            System.out.println("Application Area: " + getApplicationType().toString() + "\tCredits Needed: 9");
        }
        for (ApplicationArea applicationArea : applicationAreas) {
            if (applicationArea.getType().toString().equals(getApplicationType().toString())) {
                for (String aaCourseUUID : applicationArea.getCourses()) {
                    for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                        if (studentCourse.getCourseID().equals(courseList.getCourseIDByCourseUUID(aaCourseUUID))
                                && studentCourse.getStatus().equals("Completed")) {
                            System.out.println("Course ID: " + studentCourse.getCourseID() + "\tGrade: "
                                    + studentCourse.getGrade() + "\tPassed " + studentCourse.getPassed());
                        }
                    }
                }
            }
        }
    }

    /**
     * Prints the eight semester plan beautifully to a file
     */
    public void printEightSemesterPlan(String classYear) {
        try {
            FileWriter writer = new FileWriter(getStudentID() + "_SemesterPlan.txt");
            for (int semesterNum = 1; semesterNum <= 8; semesterNum++) {
                writer.write("Semester " + semesterNum + "\n");
                for (StudentCourse course : eightSemesterPlan.getStudentCourses()) {
                    if (course.getSemesterNum() == semesterNum) {
                        if (course.getStatus().equals("Completed")) {
                            String formatString = "Course ID: %-10s Grade: %-5s Passed: %-7s Status: %s\n";
                            writer.write(String.format(formatString, course.getCourseID(), course.getGrade(),
                                    course.getPassed(), course.getStatus()));
                        } else if (course.getStatus().equals("Planned")) {
                            String formatString = "Course ID: %-10s Status: %s\n";
                            writer.write(String.format(formatString, course.getCourseID(), course.getStatus()));
                        }
                    }
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public String printSemesterPlan(String classYear) {
        StringBuilder semesterInfo = new StringBuilder();
        for (int semesterNum = 1; semesterNum <= 8; semesterNum++) {
            semesterInfo.append("Semester ").append(semesterNum).append("\n");
            for (StudentCourse course : eightSemesterPlan.getStudentCourses()) {
                if (course.getSemesterNum() == semesterNum) {
                    if (course.getStatus().equals("Completed")) {
                        String formatString = "Course ID: %-10s Grade: %-5s Passed: %-7s Status: %s%n";
                        semesterInfo.append(String.format(formatString, course.getCourseID(), course.getGrade(),
                                course.getPassed(), course.getStatus()));
                    } else if (course.getStatus().equals("Planned")) {
                        String formatString = "Course ID: %-10s Status: %s%n";
                        semesterInfo.append(String.format(formatString, course.getCourseID(), course.getStatus()));
                    }
                }
            }
            semesterInfo.append("\n");
        }
        return semesterInfo.toString();
    }

    /**
     * Prints the student's completed courses
     * 
     * @param eightSemesterPlan | the student's eight semester plan
     * @param requiredCourses   | the required courses of the student's major
     */
    public String printCompletedCourses(SemesterPlan eightSemesterPlan, ArrayList<Course> requiredCourses) {
        StringBuilder completedCourses = new StringBuilder();
        for (Course requiredCourse : requiredCourses) {
            for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                if (requiredCourse.courseID().equals(studentCourse.getCourseID())
                        && studentCourse.getStatus().equals("Completed")) {
                    completedCourses.append("Course ID: ").append(requiredCourse.courseID())
                            .append("\t\tGrade: ").append(studentCourse.getGrade())
                            .append("\t\t\tPassed: ").append(studentCourse.getPassed())
                            .append("\n");
                }
            }
        }
        return completedCourses.toString();
    }

    /**
     * Returns a string representation of the student's incomplete courses
     * 
     * @param eightSemesterPlan | the student's eight semester plan
     * @param requiredCourses   | the required courses of the student's major
     * @return a string representation of the student's incomplete courses
     */
    public String printIncompleteCourses(SemesterPlan eightSemesterPlan, ArrayList<Course> requiredCourses) {
        StringBuilder incompleteCourses = new StringBuilder();
        for (Course requiredCourse : requiredCourses) {
            for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                if (requiredCourse.courseID().equals(studentCourse.getCourseID())
                        && studentCourse.getStatus().equals("Planned")) {
                    incompleteCourses.append("Course ID: ").append(requiredCourse.courseID()).append("\n");
                }
            }
        }
        return incompleteCourses.toString();
    }

    /**
     * Returns a string representation of the student's electives progress
     * 
     * @param majorElectives | the electives for the student's major
     * @param courseList     | the list of courses
     * @return a string representation of the student's electives progress
     */
    public String printElectiveProgress(ArrayList<Electives> majorElectives, CourseList courseList) {
        StringBuilder electiveProgress = new StringBuilder();
        for (Electives electives : majorElectives) {
            String formatString = "%-20s Credits Needed: %d\n";
            electiveProgress.append(
                    String.format(formatString, electives.getElectiveType() + " Elective", electives.getMinHours()));
            for (String electiveUUID : electives.getCourses()) {
                for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                    if (studentCourse.getCourseID().equals(courseList.getCourseIDByCourseUUID(electiveUUID))
                            && studentCourse.getStatus().equals("Completed")) {
                        electiveProgress.append("\tCourse ID: ").append(studentCourse.getCourseID())
                                .append("\t\tGrade: ").append(studentCourse.getGrade())
                                .append("\t\tPassed: ").append(studentCourse.getPassed()).append("\n");
                    }
                }
            }
        }
        return electiveProgress.toString();
    }

    /**
     * Returns a string representation of the student's application area progress
     * 
     * @param applicationAreas | the possible application areas
     * @param courseList       | the list of courses
     * @return a string representation of the student's application area progress
     */
    public String printApplicationAreaProgress(ArrayList<ApplicationArea> applicationAreas, CourseList courseList) {
        StringBuilder applicationAreaProgress = new StringBuilder();
        if (getApplicationType().toString().equals("NULL")) {
            applicationAreaProgress.append("No Application Area Selected.\tCredits Needed: 9\n");
            return applicationAreaProgress.toString();
        } else {
            applicationAreaProgress.append("Application Area: ").append(getApplicationType().toString())
                    .append("\tCredits Needed: 9\n");
        }
        for (ApplicationArea applicationArea : applicationAreas) {
            if (applicationArea.getType().toString().equals(getApplicationType().toString())) {
                for (String aaCourseUUID : applicationArea.getCourses()) {
                    for (StudentCourse studentCourse : eightSemesterPlan.getStudentCourses()) {
                        if (studentCourse.getCourseID().equals(courseList.getCourseIDByCourseUUID(aaCourseUUID))
                                && studentCourse.getStatus().equals("Completed")) {
                            applicationAreaProgress.append("Course ID: ").append(studentCourse.getCourseID())
                                    .append("\tGrade: ").append(studentCourse.getGrade())
                                    .append("\t\tPassed ").append(studentCourse.getPassed()).append("\n");
                        }
                    }
                }
            }
        }
        return applicationAreaProgress.toString();
    }

}
