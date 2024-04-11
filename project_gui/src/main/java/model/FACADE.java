package model;

import java.util.ArrayList;

public class FACADE {
    private User user;
    private Student student;
    private Advisor advisor;
    private UserList userList;
    private CourseList courseList;
    private MajorList majorList;
    private static FACADE facade;

    /**
     * Initializes the JSON instances
     */
    FACADE() {
        userList = UserList.getInstance();
        courseList = CourseList.getInstance();
        majorList = MajorList.getInstance();
    }

    /**
     * Singleton contructor
     * 
     * @return | FACADE instance
     */
    public static FACADE getInstance() {
        if (facade == null)
            facade = new FACADE();
        return facade;
    }

    /**
     * Getter for the CourseList
     * 
     * @return | the CourseList
     */
    public CourseList getCourseList() {
        return this.courseList;
    }

    /**
     * Getter for the UserList
     * 
     * @return | the UserList
     */
    public UserList getUserList() {
        return this.userList;
    }

    /**
     * Getter for the MajorList
     * 
     * @return | the MajorList
     */
    public MajorList getMajorList() {
        return this.majorList;
    }

    /**
     * Get Major by the major's UUID
     * 
     * @param UUID | the UUID of the major
     * @return | the Major
     */
    public Major getMajorByUUID(String UUID) {
        return this.majorList.getMajorByUUID(UUID);
    }

    /**
     * Get Course by the course's UUID
     * 
     * @param UUID | the UUID of the course
     * @return | the Course
     */
    public Course getCourseByUUID(String UUID) {
        return this.courseList.getCourseByUUID(UUID);
    }

    /**
     * Getter for User
     * 
     * @return | a User
     */
    public User getUser() {
        return user;
    }

    /**
     * Login to the system with given username
     * 
     * @param username |username of the user logging in
     * @return | true or false depending on if login is successful
     */
    public boolean login(String username) {
        if (!userList.haveUser(username))
            return false;
        user = userList.getUser(username);
        if (user instanceof Student) {
            student = (Student) user;
        } else if (user instanceof Advisor) {
            advisor = (Advisor) user;
        }
        return true;
    }

    /**
     * Getter for Student
     * 
     * @return | a Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Getter for Advisor
     * 
     * @return | an Advisor
     */
    public Advisor getAdvisor() {
        return advisor;
    }

    /**
     * Logout and save all user, major, and course objects
     */
    public void logout() {
        System.out.println("Logging out...");
        userList.saveUsers();
        majorList.saveMajors();
        courseList.saveCourses();
    }

    /**
     * Saves users to the system
     */
    public void saveUsers() {
        userList.saveUsers();
    }

    /**
     * Register an advisor to the system
     * 
     * @param username   | the username of the advisor
     * @param firstName  | the first name of the advisor
     * @param lastName   | the last name of the advisor
     * @param userType   | the type of user
     * @param department | the department the advisor works for
     * @return | true or false depending on if the advisor was successfully added
     */
    public boolean registerAdvisor(String username, String firstName, String lastName, String userType,
            String department) {
        return userList.addAdvisor(username, firstName, lastName, userType, department);
    }

    /**
     * Register an student to the system
     * 
     * @param username  | the username of the student
     * @param firstName | the first name of the student
     * @param lastName  | the last name of the student
     * @param userType  | the type of user
     * @return | true or false depending on if the advisor was successfully added
     */
    public boolean registerStudent(String username, String firstName, String lastName, String userType,
            String major, String classYear) {
        return userList.addStudent(username, firstName, lastName, userType, major, classYear);
    }

    /**
     * Adds a course to the system
     * 
     * @param courseName    | the course's name
     * @param subject       | the course's subject
     * @param number        | the course's number
     * @param semester      | the course's semester availability
     * @param prerequisites | the course's prerequisites
     * @param corequisites  | the course's corequisites
     * @param creditHours   | the course's credit hours
     * @param passingGrade  | the passing grade for the course
     * @return | true or false depending on if the course was successfully added
     */
    public boolean addCourse(String courseName, String subject, String number, String semester,
            ArrayList<PrereqOptions> prerequisites, ArrayList<String> corequisites, int creditHours, int passingGrade) {
        return courseList.addCourse(courseName, subject, number, semester, prerequisites, corequisites, creditHours,
                passingGrade);
    }

    /**
     * Get all courses
     * 
     * @return | list of all courses
     */
    public ArrayList<Course> getAllCourses() {
        return this.courseList.getCourses();
    }

    /**
     * Adds a Major to the system
     * 
     * @param majorName        | the name of the major
     * @param requiredCourses  | the major's required courses
     * @param electives        | the major's elective courses
     * @param applicationAreas | a possible application area
     * @param creditsRequired  | total amount of credits
     * @return | true or false depending on if the major was successfully added
     */
    public boolean addMajor(String majorName, ArrayList<String> requiredCourses,
            ArrayList<Electives> electives, ArrayList<ApplicationArea> applicationAreas,
            int creditsRequired) {
        return majorList.addMajor(majorName, requiredCourses, electives, applicationAreas, creditsRequired);
    }

    /**
     * Gets all of the majors
     * 
     * @return | a list of majors
     */
    public ArrayList<Major> getAllMajors() {
        return this.majorList.getMajors();
    }

    /**
     * Get a course from the list of courses by the course ID
     * 
     * @param courseID | the course's ID to search
     * @return | the course
     */
    public Course getCourseByCode(String courseID) {
        return this.courseList.getCourse(courseID);
    }

    /**
     * Gets the courses the student has selected
     * 
     * @return | a list of courses
     */
    public ArrayList<String> getStudentCourses() {
        String studentMajorUUID = student.getMajor();
        Major studentsMajor = majorList.getMajorByUUID(studentMajorUUID);
        return studentsMajor.getRequiredCourses();
    }

    /**
     * Gets the student's eight semester plan
     * 
     * @return | a Semester Plan
     */
    public SemesterPlan getEightSemesterPlan() {
        return student.getEightSemesterPlan();
    }

    /**
     * Creates a transcript of the student's data
     */
    public void printEightSemesterPlan() {
        student.printEightSemesterPlan(student.getClassYear());
    }

    /**
     * Gets the student's application type
     * 
     * @return | the type of application area
     */
    public ApplicationType getApplicationType() {
        return student.getApplicationType();
    }

    /**
     * Sets the student's application type
     * 
     * @param applicationType | the type of application area
     */
    public void setApplicationType(ApplicationType applicationType) {
        student.setApplicationType(applicationType);
    }

    /**
     * List all students in the Advisor's advisees list
     */
    public void listAdvisees() {
        for (String studentUUID : advisor.getAdvisees()) {
            Student student = userList.getStudentByUUID(studentUUID);
            System.out.println("Name: " + student.getFirstName() + " " + student.getLastName() + " StudentID: "
                    + student.getStudentID());
        }
    }

    /**
     * Add a student by student ID to the Advisor's list of advisees
     * 
     * @param studentID | the student's ID to be added
     */
    public void addAdvisee(String studentID) {
        String studentUUID = userList.getStudentUUIDByStudentID(studentID);
        advisor.addAdvisee(studentUUID);
        for (Student student : userList.getStudents()) {
            if (advisor.getAdvisees().contains(student.getUUID())) {
                student.setAdvisor(advisor.getUUID());
            }
        }
    }

    /**
     * View student's degree progress as an advisor
     * 
     * @param studentID | the student to check their progress
     */
    public void viewStudentProgress(String studentID) {
        Student student = userList.getStudentByStudentID(studentID);
        SemesterPlan eightSemesterPlan = student.getEightSemesterPlan();
        String majorUUID = student.getMajor();
        Major studentMajor = majorList.getMajorByUUID(majorUUID);
        ArrayList<String> requiredCoursesUUIDs = studentMajor.getRequiredCourses();
        ArrayList<Course> requiredCourses = new ArrayList<>();
        for (String uuid : requiredCoursesUUIDs) {
            requiredCourses.add(getCourseByUUID(uuid));
        }
        student.viewProgress(eightSemesterPlan, requiredCourses, studentMajor.getElectives(),
                studentMajor.getApplicationAreas(), courseList, majorList);
    }

    /**
     * Makes a note to the student
     * 
     * @param studentID | the student to write the note
     */
    public void makeNote(String studentID, String note) {
        Student student = userList.getStudentByStudentID(studentID);
        student.setNoteFromAdvisor(note);

    }

    /**
     * Views the note from the advisor
     * 
     * @return | a string representation of the note from the advisor
     */
    public String viewNote() {
        return student.getNoteFromAdvisor();
    }

    /**
     * Prints a student's degree progress to the console
     */
    public void viewProgress() {
        SemesterPlan eightSemesterPlan = student.getEightSemesterPlan();
        String majorUUID = student.getMajor();
        Major studentMajor = majorList.getMajorByUUID(majorUUID);
        ArrayList<String> requiredCoursesUUIDs = studentMajor.getRequiredCourses();
        ArrayList<Course> requiredCourses = new ArrayList<>();
        for (String uuid : requiredCoursesUUIDs) {
            requiredCourses.add(getCourseByUUID(uuid));
        }
        student.viewProgress(eightSemesterPlan, requiredCourses, studentMajor.getElectives(),
                studentMajor.getApplicationAreas(), courseList, majorList);
    }

    /**
     * Prints a student's completed courses
     */
    public String printCompletedCourses() {
        SemesterPlan eightSemesterPlan = student.getEightSemesterPlan();
        String majorUUID = student.getMajor();
        Major studentMajor = majorList.getMajorByUUID(majorUUID);
        ArrayList<String> requiredCoursesUUIDs = studentMajor.getRequiredCourses();
        ArrayList<Course> requiredCourses = new ArrayList<>();
        for (String uuid : requiredCoursesUUIDs) {
            requiredCourses.add(getCourseByUUID(uuid));
        }
        for (String uuid : requiredCoursesUUIDs) {
            requiredCourses.add(getCourseByUUID(uuid));
        }
        return student.printCompletedCourses(eightSemesterPlan, requiredCourses);
    }

    /**
     * Prints a student's incomplete courses
     */
    public String printIncompleteCourses() {
        SemesterPlan eightSemesterPlan = student.getEightSemesterPlan();
        String majorUUID = student.getMajor();
        Major studentMajor = majorList.getMajorByUUID(majorUUID);
        ArrayList<String> requiredCoursesUUIDs = studentMajor.getRequiredCourses();
        ArrayList<Course> requiredCourses = new ArrayList<>();
        for (String uuid : requiredCoursesUUIDs) {
            requiredCourses.add(getCourseByUUID(uuid));
        }
        for (String uuid : requiredCoursesUUIDs) {
            requiredCourses.add(getCourseByUUID(uuid));
        }
        return student.printIncompleteCourses(eightSemesterPlan, requiredCourses);
    }

    /**
     * Prints a student's elective progress
     */
    public String printElectiveProgress() {
        String majorUUID = student.getMajor();
        Major studentMajor = majorList.getMajorByUUID(majorUUID);
        return student.printElectiveProgress(studentMajor.getElectives(), courseList);
    }

    /**
     * Prints a student's application area progress
     */
    public String printApplicationAreaProgress() {
        String majorUUID = student.getMajor();
        Major studentMajor = majorList.getMajorByUUID(majorUUID);
        return student.printApplicationAreaProgress(studentMajor.getApplicationAreas(), courseList);
    }

    /**
     * Prints the advisees list to the console
     * 
     * @param adviseesUUIDs | the array of UUIDs for each student
     * @return | a string representation of the advisees list
     */
    public String printAdviseesList(ArrayList<String> adviseesUUIDs) {
        ArrayList<Student> advisees = new ArrayList<>();
        for (String adviseeUUID : adviseesUUIDs) {
            advisees.add(userList.getStudentByUUID(adviseeUUID));
        }
        return advisor.printAdviseesList(advisees);
    }

    /**
     * Prints the list of electives for the major
     * 
     * @param majorUUID | the given major
     * @return | a string representation of the list of electives
     */
    public String printMajorElectives(String majorUUID) {
        Major major = getMajorByUUID(majorUUID);
        return major.printElectives(courseList);
    }

    public void addElectiveCourses(String subject1, String num1, String subject2, String num2, int sem1, int sem2) {
        getEightSemesterPlan().addCourse(subject1, num1, sem1);
        getEightSemesterPlan().addCourse(subject2, num2, sem2);
    }
}
