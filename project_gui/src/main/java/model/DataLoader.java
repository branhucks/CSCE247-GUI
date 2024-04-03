package model;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class loads the data from the JSON files and converts them into
 * corresponding objects (i.e., Student, Advisor, Course, Major).
 */
public class DataLoader extends DataConstants {

    /**
     * Loads the students from the users JSON file
     * 
     * @return | an ArrayList of Student objects
     */
    public static ArrayList<Student> getStudents() {
        ArrayList<Student> studentList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject) peopleJSON.get(i);

                if (personJSON.get(USER_USER_TYPE).equals("Student")) {
                    String id = (String) personJSON.get(USER_UUID);
                    String username = (String) personJSON.get(USER_USERNAME);
                    String firstName = (String) personJSON.get(USER_FIRST_NAME);
                    String lastName = (String) personJSON.get(USER_LAST_NAME);
                    String userType = (String) personJSON.get(USER_USER_TYPE);
                    String studentID = (String) personJSON.get(STUDENT_STUDENT_ID);
                    String advisor = (String) personJSON.get(STUDENT_ADVISOR);
                    String major = (String) personJSON.get(STUDENT_MAJOR);
                    String classYear = (String) personJSON.get(STUDENT_CLASS_YEAR);
                    double gpa = (double) personJSON.get(STUDENT_GPA);
                    boolean hasScholarship = (boolean) personJSON.get(STUDENT_HAS_SCHOLARSHIP);
                    int majorProgress = ((Long) personJSON.get(STUDENT_MAJOR_PROGRESS)).intValue();
                    JSONArray eightSemesterPlanJSON = (JSONArray) personJSON.get(STUDENT_SEMESTER_PLAN);
                    ArrayList<StudentCourse> semesterCourses = new ArrayList<>();
                    for (int j = 0; j < eightSemesterPlanJSON.size(); j++) {
                        JSONObject courseJSON = (JSONObject) eightSemesterPlanJSON.get(j);
                        String courseID = (String) courseJSON.get(STUDENT_COURSE_COURSEID);
                        int semesterNum = ((Long) courseJSON.get(STUDENT_COURSE_SEMESTERNUM)).intValue();
                        String status = (String) courseJSON.get(STUDENT_COURSE_STATUS);
                        int grade = ((Long) courseJSON.get(STUDENT_COURSE_GRADE)).intValue();
                        boolean passed = (boolean) courseJSON.get(STUDENT_COURSE_PASSED);
                        semesterCourses.add(new StudentCourse(courseID, semesterNum, status, grade,
                                passed));
                    }
                    SemesterPlan eightSemesterPlan = new SemesterPlan(semesterCourses);
                    String noteFromAdvisor = (String) personJSON.get(STUDENT_NOTE);
                    ApplicationType applicationType = ApplicationType
                            .valueOf((String) personJSON.get(STUDENT_APPLICATION_TYPE));
                    studentList.add(
                            new Student(id, username, firstName, lastName, userType, studentID, advisor, major,
                                    classYear, gpa, hasScholarship, majorProgress, eightSemesterPlan, noteFromAdvisor,
                                    applicationType));
                }
            }
            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the advisors from the users JSON file
     * 
     * @return | an ArrayList of Advisor objects
     */
    public static ArrayList<Advisor> getAdvisors() {
        ArrayList<Advisor> advisorList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject) peopleJSON.get(i);

                if (personJSON.get(USER_USER_TYPE).equals("Advisor")) {
                    String id = (String) personJSON.get(USER_UUID);
                    String username = (String) personJSON.get(USER_USERNAME);
                    String firstName = (String) personJSON.get(USER_FIRST_NAME);
                    String lastName = (String) personJSON.get(USER_LAST_NAME);
                    String userType = (String) personJSON.get(USER_USER_TYPE);
                    JSONArray adviseesJSON = (JSONArray) personJSON.get(ADVISOR_ADVISEES);
                    ArrayList<String> advisees = new ArrayList<>();
                    if (adviseesJSON == null) {
                        advisees = new ArrayList<String>();
                    } else {
                        for (int j = 0; j < adviseesJSON.size(); j++) {
                            JSONObject studentJSON = (JSONObject) adviseesJSON.get(j);
                            String studentUUID = (String) studentJSON.get(USER_UUID);
                            advisees.add(studentUUID);
                        }
                    }
                    String department = (String) personJSON.get(ADVISOR_DEPARTMENT);
                    Advisor advisor = new Advisor(id, username, firstName, lastName, userType, advisees, department);
                    advisorList.add(advisor);
                }
            }
            return advisorList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the majors from the majors JSON file
     * 
     * @return | an ArrayList of Major objects
     */
    public static ArrayList<Major> getMajors() {
        ArrayList<Major> majorList = new ArrayList<Major>();

        try {
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONArray majorsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < majorsJSON.size(); i++) {
                JSONObject majorJSON = (JSONObject) majorsJSON.get(i);
                String id = (String) majorJSON.get(MAJOR_ID);
                String majorName = (String) majorJSON.get(MAJOR_MAJOR_NAME);
                JSONArray coursesJSON = (JSONArray) majorJSON.get(MAJOR_REQUIRED_COURSES);
                ArrayList<String> requiredCourses = new ArrayList<>();
                for (int j = 0; j < coursesJSON.size(); j++) {
                    JSONObject courseJSON = (JSONObject) coursesJSON.get(j);
                    String courseUUID = (String) courseJSON.get(COURSE_ID);
                    requiredCourses.add(courseUUID);
                }
                JSONArray electivesJSON = (JSONArray) majorJSON.get(MAJOR_ELECTIVES);
                ArrayList<Electives> electives = new ArrayList<>();
                for (int j = 0; j < electivesJSON.size(); j++) {
                    JSONObject electiveJSON = (JSONObject) electivesJSON.get(j);
                    int minHours = ((Long) electiveJSON.get(ELECTIVE_MINHOURS)).intValue();
                    ElectiveType electiveType = ElectiveType.valueOf((String) electiveJSON.get(ELECTIVE_TYPE));
                    JSONArray coursesArray = (JSONArray) electiveJSON.get(ELECTIVE_COURSES);
                    ArrayList<String> courses = new ArrayList<>();
                    for (int k = 0; k < coursesArray.size(); k++) {
                        JSONObject courseJSON = (JSONObject) coursesArray.get(k);
                        String courseUUID = (String) courseJSON.get(COURSE_ID);
                        courses.add(courseUUID);
                    }
                    electives.add(new Electives(minHours, electiveType, courses));
                }
                JSONArray applicationAreasJSON = (JSONArray) majorJSON.get(MAJOR_APPLICATION_AREAS);
                ArrayList<ApplicationArea> applicationAreas = new ArrayList<>();
                for (int j = 0; j < applicationAreasJSON.size(); j++) {
                    JSONObject applicationAreaJSON = (JSONObject) applicationAreasJSON.get(j);
                    ApplicationType applicationType = ApplicationType
                            .valueOf((String) applicationAreaJSON.get(APPLICATION_AREA_TYPE));
                    JSONArray coursesArray = (JSONArray) applicationAreaJSON.get(APPLICATION_AREA_COURSES);
                    ArrayList<String> courses = new ArrayList<>();
                    for (int k = 0; k < coursesArray.size(); k++) {
                        JSONObject courseJSON = (JSONObject) coursesArray.get(k);
                        String courseUUID = (String) courseJSON.get(COURSE_ID);
                        courses.add(courseUUID);
                    }
                    applicationAreas.add(new ApplicationArea(applicationType, courses));
                }
                int creditsRequired = ((Long) majorJSON.get(MAJOR_CREDITS_REQUIRED)).intValue();
                majorList.add(new Major(id, majorName, requiredCourses, electives, applicationAreas, creditsRequired));
            }
            return majorList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the courses from the courses JSON file
     * 
     * @return | an ArrayList of Course objects
     */
    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courseList = new ArrayList<Course>();

        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONArray coursesJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < coursesJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) coursesJSON.get(i);
                String id = (String) courseJSON.get(COURSE_ID);
                String courseName = (String) courseJSON.get(COURSE_COURSE_NAME);
                String subject = (String) courseJSON.get(COURSE_SUBJECT);
                String number = (String) courseJSON.get(COURSE_NUMBER);
                String semester = (String) courseJSON.get(COURSE_SEMESTER);
                JSONArray prerequisitesJSON = (JSONArray) courseJSON.get(COURSE_PREREQUISITES);
                ArrayList<PrereqOptions> prerequisites = new ArrayList<>();
                for (int j = 0; j < prerequisitesJSON.size(); j++) {
                    JSONObject prereqJSON = (JSONObject) prerequisitesJSON.get(j);
                    int choices = ((Long) prereqJSON.get(PREREQOPTIONS_CHOICES)).intValue();
                    int minGrade = ((Long) prereqJSON.get(PREREQOPTIONS_MINGRADE)).intValue();
                    JSONArray coursesArray = (JSONArray) prereqJSON.get(PREREQOPTIONS_COURSES);
                    ArrayList<String> courses = new ArrayList<>();
                    for (int k = 0; k < coursesArray.size(); k++) {
                        JSONObject course2JSON = (JSONObject) coursesArray.get(k);
                        String courseUUID = (String) course2JSON.get(COURSE_ID);
                        courses.add(courseUUID);
                    }
                    prerequisites.add(new PrereqOptions(choices, minGrade, courses));
                }
                JSONArray corequisitesJSON = (JSONArray) courseJSON.get(COURSE_COREQUISITES);
                ArrayList<String> corequisites = new ArrayList<>();
                for (int j = 0; j < corequisitesJSON.size(); j++) {
                    JSONObject corequisiteJSON = (JSONObject) corequisitesJSON.get(j);
                    String corequisite = (String) corequisiteJSON.get(COURSE_ID);
                    corequisites.add(corequisite);
                }
                int creditHours = ((Long) courseJSON.get(COURSE_CREDIT_HOURS)).intValue();
                int passingGrade = ((Long) courseJSON.get(COURSE_PASSING_GRADE)).intValue();
                courseList.add(new Course(id, courseName, subject, number, semester, prerequisites, corequisites,
                        creditHours, passingGrade));
            }
            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}