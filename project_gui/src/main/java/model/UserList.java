package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class UserList {
    public static UserList users;
    public ArrayList<Student> students;
    public ArrayList<Advisor> advisors;
    public ArrayList<Major> majors;

    /**
     * Creates a new CourseList instance
     */
    public UserList() {
        students = DataLoader.getStudents();
        advisors = DataLoader.getAdvisors();
        majors = DataLoader.getMajors();
    }

    /**
     * Get student by UUID
     * 
     * @param uuid | the student's UUID
     * @return | the student retrived
     */
    public Student getStudentByUUID(String uuid) {
        for (Student student : students) {
            if (student.getUUID().equals(uuid))
                return student;
        }
        return null;
    }

    /**
     * Get student by Student ID
     * 
     * @param studentID | the student's ID to be searched
     * @return | the student UUID retrieved
     */
    public String getStudentUUIDByStudentID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student.getUUID();
            }
        }
        return null;
    }

    /**
     * Get student by Student ID
     * 
     * @param studentID | the student's ID to be searched
     * @return | the student retrieved
     */
    public Student getStudentByStudentID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Get advisor by UUID
     * 
     * @param uuid | the advisor's UUID
     * @return | the advisor retrieved
     */
    public Advisor getAdvisorByUUID(String uuid) {
        for (Advisor advisor : advisors) {
            if (advisor.getUUID().equals(uuid))
                return advisor;
        }
        return null;
    }

    /**
     * Gets an instance of the UserList
     * 
     * @return | UserList instance
     */
    public static UserList getInstance() {
        if (users == null) {
            users = new UserList();
        }
        return users;
    }

    /**
     * Validates whether the user exists
     * 
     * @param username | the users's username to search
     * @return | true or false depending on if it exists
     */
    public boolean haveUser(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return true;
            }
        }
        for (Advisor advisor : advisors) {
            if (advisor.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a User by the user's username
     * 
     * @param username | the user's username to search
     * @return | the User object with given username
     */
    public User getUser(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        for (Advisor advisor : advisors) {
            if (advisor.getUsername().equals(username)) {
                return advisor;
            }
        }
        return null;
    }

    /**
     * Get all students
     * 
     * @return | list of all students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Get all advisors
     * 
     * @return | list of all advisors
     */
    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    /**
     * Adds a student to the system
     * 
     * @param username  | the username of the student
     * @param firstName | the first name of the student
     * @param lastName  | the last name of the student
     * @param userType  | the type of user
     * @param major     | the student's major
     * @param classYear | the student's class year
     * @return | true or false depending on if the student was successfully added
     */
    public boolean addStudent(String username, String firstName, String lastName, String userType, String major,
            String classYear) {
        if (haveUser(username)) {
            return false;
        }
        students.add(new Student((UUID.randomUUID()).toString(), username, firstName, lastName, userType,
                generateStudentID(), null, getMajorUUIDByName(major),
                classYear, 0.0, false, 0, null, null, null));
        return true;
    }

    /**
     * Adds an advisor to the system
     * 
     * @param username   | the username of the advisor
     * @param firstName  | the first name of the advisor
     * @param lastName   | the last name of the advisor
     * @param userType   | the type of user
     * @param department | the department the advisor works for
     * @return | true or false depending on if the advisor was successfully added
     */
    public boolean addAdvisor(String username, String firstName, String lastName, String userType, String department) {
        if (haveUser(username)) {
            return false;
        }
        advisors.add(
                new Advisor((UUID.randomUUID()).toString(), username, firstName, lastName, userType,
                        new ArrayList<String>(), department));
        return true;
    }

    public String generateStudentID() {
        // Generate a random uppercase letter
        char randomLetter = generateRandomUppercaseLetter();
        // Generate eight random digits
        String randomDigits = generateRandomDigits(8);
        // Combine the letter and digits to form the student ID
        return randomLetter + randomDigits;
    }

    private char generateRandomUppercaseLetter() {
        Random random = new Random();
        // ASCII values for uppercase letters: A=65, Z=90
        int randomAscii = random.nextInt(26) + 'A';
        return (char) randomAscii;
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // ASCII values for digits: 0=48, 9=57
            int randomDigit = random.nextInt(10) + '0';
            digits.append((char) randomDigit);
        }
        return digits.toString();
    }

    /**
     * Save the courses in the list to the data file
     */
    public void saveUsers() {
        DataWriter.saveUsers(students, advisors);
    }

    public String getMajorUUIDByName(String majorName) {
        for (Major major : majors) {
            if (major.getMajorName().equals(majorName))
                return major.getUUID();
        }
        return null;
    }
}
