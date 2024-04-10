package model;

import java.util.ArrayList;

/**
 * The Advisor class represents an advisor in the system.
 */
public class Advisor extends User {
    private String uuid;
    private ArrayList<String> advisees;
    private String department;

    /**
     * Creates a new Advisor instance
     * 
     * @param uuid      | the advisor's uuid
     * @param username  | username of advisor
     * @param firstName | first name of advisor
     * @param lastName  | last name of advisor
     * @param userType  | user type of advisor
     * @param advisees  | list of advisees
     */
    public Advisor(String uuid, String username, String firstName, String lastName, String userType,
            ArrayList<String> advisees, String department) {
        super(uuid, username, firstName, lastName, userType);
        this.advisees = advisees;
        this.department = department;
    }

    /**
     * Getter for the list of advisees
     * 
     * @return | an ArrayList of students
     */
    public ArrayList<String> getAdvisees() {
        return advisees;
    }

    /**
     * Setter for the list of advisees
     * 
     * @param advisees | list of advisees
     */
    public void setAdvisees(ArrayList<String> advisees) {
        this.advisees = advisees;
    }

    /**
     * Getter for the advisor's department
     * 
     * @return | a string representation of the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * A setter for the department
     * 
     * @param department | the advisor's department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Adds an advisee to the advisee list
     * 
     * @param studentUUID | the student's UUID
     */
    public void addAdvisee(String studentUUID) {
        advisees.add(studentUUID);
    }

    /**
     * View list of advisees
     * 
     * @return | list of advisees
     */
    public ArrayList<String> viewAdvisees() {
        // TODO
        return this.advisees;
    }

    /**
     * Select a student's profile to view
     * 
     * @param studentID | the ID to find the student
     * @return | the student found
     */
    public Student selectStudentProfile(String studentID) {
        // TODO
        return null;
    }

    /**
     * Enter grades for a student's course(s)
     * 
     * @param student | the student to enter grades for
     * @param courses | the list of courses to enter grades
     */
    public void enterGrades(Student student, ArrayList<Course> courses) {
        // TODO
    }

    public String printAdviseesList(ArrayList<Student> advisees) {
        StringBuilder adviseesList = new StringBuilder();
        for (Student student : advisees) {
            adviseesList.append(student.getStudentID()).append("\t\t\t").append(student.getFirstName()).append(" ")
                    .append(student.getLastName());
            adviseesList.append("\n");
        }
        return adviseesList.toString();
    }
}
