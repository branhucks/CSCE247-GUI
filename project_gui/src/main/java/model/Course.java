package model;

import java.util.ArrayList;

/**
 * The Course class represents a course in the system.
 */
public class Course {
    private String uuid;
    private String courseName;
    private String subject;
    private String number;
    private String semester;
    private ArrayList<PrereqOptions> prerequisites;
    private ArrayList<String> corequisites;
    private int creditHours;
    private int passingGrade;

    /**
     * Creates a new Course instance
     * 
     * @param courseName    | the name of the course
     * @param courseID      | the course's identifier
     * @param requirement   | the requirement type
     * @param semester      | the semester it is available
     * @param description   | the course's description
     * @param prerequisites | the course's prerequisites
     * @param corequisites  | the course's corequisites
     * @param creditHours   | credit hours for the course
     * @param passingGrade  | the passing grade for the course
     */
    public Course(String courseName, String subject, String number, String semester,
            ArrayList<PrereqOptions> prerequisites, ArrayList<String> corequisites, int creditHours, int passingGrade) {
        this.courseName = courseName;
        this.subject = subject;
        this.number = number;
        this.semester = semester;
        this.prerequisites = prerequisites;
        this.corequisites = corequisites;
        this.creditHours = creditHours;
        this.passingGrade = passingGrade;
    }

    /**
     * Creates a new Course instance (OVERLOADED)
     * 
     * @param uuid          | the course's UUID
     * @param courseName
     * @param courseID
     * @param semester
     * @param prerequisites
     * @param creditHours
     * @param passingGrade
     */
    public Course(String uuid, String courseName, String subject, String number, String semester,
            ArrayList<PrereqOptions> prerequisites, ArrayList<String> corequisites, int creditHours, int passingGrade) {
        this.uuid = uuid;
        this.courseName = courseName;
        this.subject = subject;
        this.number = number;
        this.semester = semester;
        this.prerequisites = prerequisites;
        this.corequisites = corequisites;
        this.creditHours = creditHours;
        this.passingGrade = passingGrade;
    }

    // Getters and Setters
    public Course getCourse() {
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setPrerequisites(ArrayList<PrereqOptions> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setCorequisites(ArrayList<String> corequisites) {
        this.corequisites = corequisites;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }

    public String getUUID() {
        return uuid;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSubject() {
        return subject;
    }

    public String getNumber() {
        return number;
    }

    public String courseID() {
        return this.subject + " " + this.number;
    }

    public String getSemester() {
        return semester;
    }

    public ArrayList<PrereqOptions> getPrerequisites() {
        return prerequisites;
    }

    public ArrayList<String> getCorequisites() {
        return corequisites;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getPassingGrade() {
        return passingGrade;
    }
}