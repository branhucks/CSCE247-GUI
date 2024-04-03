package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is responsible for writing the data to the JSON files.
 * The class is composed of three major methods: saving the list of Students and
 * Advisors to the users JSON file, saving the list of Courses to the courses
 * JSON file, and saving the list of Majors to the majors JSON file.
 */
public class DataWriter extends DataConstants {

    /**
     * Saves the list of users (composed of Students and Advisors) to a JSON file.
     */
    public static void saveUsers(ArrayList<Student> students, ArrayList<Advisor> advisors) {
        JSONArray jsonUsers = new JSONArray();

        // Creation of Objects in JSON
        for (Student student : students) {
            jsonUsers.add(getStudentJSON(student));
        }
        for (Advisor advisor : advisors) {
            jsonUsers.add(getAdvisorJSON(advisor));
        }

        // Write to the JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSONObject from the given User object
     * 
     * @param student | the Student object to create the JSONObect from
     * @return | the JSONObject created from the User object
     */
    public static JSONObject getStudentJSON(Student student) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_UUID, student.getUUID());
        userDetails.put(USER_USERNAME, student.getUsername());
        userDetails.put(USER_FIRST_NAME, student.getFirstName());
        userDetails.put(USER_LAST_NAME, student.getLastName());
        userDetails.put(USER_USER_TYPE, student.getUserType());
        userDetails.put(STUDENT_STUDENT_ID, student.getStudentID());
        userDetails.put(STUDENT_ADVISOR, student.getAdvisor());
        userDetails.put(STUDENT_MAJOR, student.getMajor());
        userDetails.put(STUDENT_CLASS_YEAR, student.getClassYear());
        userDetails.put(STUDENT_GPA, student.getGPA());
        userDetails.put(STUDENT_HAS_SCHOLARSHIP, student.getHasScholarship());
        userDetails.put(STUDENT_MAJOR_PROGRESS, student.getMajorProgress());
        JSONArray semesterPlanArray = new JSONArray();
        for (StudentCourse course : student.getEightSemesterPlan().getStudentCourses()) {
            JSONObject courseObject = new JSONObject();
            courseObject.put(STUDENT_COURSE_COURSEID, course.getCourseID());
            courseObject.put(STUDENT_COURSE_SEMESTERNUM, course.getSemesterNum());
            courseObject.put(STUDENT_COURSE_STATUS, course.getStatus());
            courseObject.put(STUDENT_COURSE_GRADE, course.getGrade());
            courseObject.put(STUDENT_COURSE_PASSED, course.getPassed());
            semesterPlanArray.add(courseObject);
        }
        userDetails.put(STUDENT_SEMESTER_PLAN, semesterPlanArray);
        userDetails.put(STUDENT_NOTE, student.getNoteFromAdvisor());
        userDetails.put(STUDENT_APPLICATION_TYPE, student.getApplicationType().toString());
        return userDetails;
    }

    /**
     * Creates a JSONObject from the given User object
     * 
     * @param advisor | the Advisor object to create the JSONObect from
     * @return | the JSONObject created from the User object
     */
    public static JSONObject getAdvisorJSON(Advisor advisor) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_UUID, advisor.getUUID());
        userDetails.put(USER_USERNAME, advisor.getUsername());
        userDetails.put(USER_FIRST_NAME, advisor.getFirstName());
        userDetails.put(USER_LAST_NAME, advisor.getLastName());
        userDetails.put(USER_USER_TYPE, advisor.getUserType());
        JSONArray adviseesArray = new JSONArray();
        if (advisor.getAdvisees() == null) {
            userDetails.put(ADVISOR_ADVISEES, new ArrayList<String>());
        } else {
            for (String uuid : advisor.getAdvisees()) {
                JSONObject adviseeObject = new JSONObject();
                adviseeObject.put(USER_UUID, uuid);
                adviseesArray.add(adviseeObject);
            }
            userDetails.put(ADVISOR_ADVISEES, adviseesArray);
        }
        return userDetails;
    }

    /**
     * Saves the list of courses to a JSON file.
     */
    public static void saveCourses() {
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> courseList = courses.getCourses();
        JSONArray jsonCourses = new JSONArray();

        // Creation of Objects in JSON
        for (int i = 0; i < courseList.size(); i++) {
            jsonCourses.add(getCourseJSON(courseList.get(i)));
        }

        // Write the JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
            file.write(jsonCourses.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSONObject from the given Course object
     * 
     * @param course | the Course object to create the JSONObject from
     * @return | the JSONObject created from the Course object
     */
    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getUUID());
        courseDetails.put(COURSE_COURSE_NAME, course.getCourseName());
        courseDetails.put(COURSE_SUBJECT, course.getSubject());
        courseDetails.put(COURSE_NUMBER, course.getNumber());
        courseDetails.put(COURSE_SEMESTER, course.getSemester());
        JSONArray prerequisitesArray = new JSONArray();
        for (PrereqOptions prereq : course.getPrerequisites()) {
            JSONObject prereqObject = new JSONObject();
            prereqObject.put(PREREQOPTIONS_CHOICES, prereq.getChoices());
            prereqObject.put(PREREQOPTIONS_MINGRADE, prereq.getMinGrade());
            JSONArray coursesArray = new JSONArray();
            for (String courseUUID : prereq.getCourses()) {
                JSONObject courseObject = new JSONObject();
                courseObject.put(COURSE_ID, courseUUID);
                coursesArray.add(courseObject);
            }
            prereqObject.put(PREREQOPTIONS_COURSES, coursesArray);
            prerequisitesArray.add(prereqObject);
        }
        courseDetails.put(COURSE_PREREQUISITES, prerequisitesArray);
        JSONArray corequisitesArray = new JSONArray();
        for (String uuid : course.getCorequisites()) {
            JSONObject corequisiteObject = new JSONObject();
            corequisiteObject.put(COURSE_ID, uuid);
            corequisitesArray.add(corequisiteObject);
        }
        courseDetails.put(COURSE_COREQUISITES, corequisitesArray);
        courseDetails.put(COURSE_CREDIT_HOURS, course.getCreditHours());
        courseDetails.put(COURSE_PASSING_GRADE, course.getPassingGrade());
        return courseDetails;
    }

    /**
     * Saves the list of majors to a JSON file.
     */
    public static void saveMajors() {
        MajorList majors = MajorList.getInstance();
        ArrayList<Major> majorList = majors.getMajors();
        JSONArray jsonMajors = new JSONArray();

        // Creation of Objects in JSON
        for (int i = 0; i < majorList.size(); i++) {
            jsonMajors.add(getMajorJSON(majorList.get(i)));
        }

        // Write the JSON file
        try (FileWriter file = new FileWriter(MAJOR_FILE_NAME)) {
            file.write(jsonMajors.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.println("Error writing to majors.json");
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSONObject from the given Major object
     * 
     * @param major | the Major object to create the JSONObject from
     * @return | the JSONObject created from the Major object
     */
    public static JSONObject getMajorJSON(Major major) {
        JSONObject majorDetails = new JSONObject();
        majorDetails.put(MAJOR_ID, major.getUUID());
        majorDetails.put(MAJOR_MAJOR_NAME, major.getMajorName());
        JSONArray requiredCoursesArray = new JSONArray();
        for (String uuid : major.getRequiredCourses()) {
            JSONObject courseObject = new JSONObject();
            courseObject.put(COURSE_ID, uuid);
            requiredCoursesArray.add(courseObject);
        }
        majorDetails.put(MAJOR_REQUIRED_COURSES, requiredCoursesArray);
        majorDetails.put(MAJOR_ELECTIVES, major.getElectives());
        JSONArray electivesArray = new JSONArray();
        for (Electives elective : major.getElectives()) {
            JSONObject electiveObject = new JSONObject();
            electiveObject.put(ELECTIVE_MINHOURS, elective.getMinHours());
            electiveObject.put(ELECTIVE_TYPE, elective.getElectiveType().toString());
            JSONArray coursesArray = new JSONArray();
            for (String uuid : elective.getCourses()) {
                JSONObject courseObject = new JSONObject();
                courseObject.put(COURSE_ID, uuid);
                coursesArray.add(courseObject);
            }
            electiveObject.put(ELECTIVE_COURSES, coursesArray);
            electivesArray.add(electiveObject);
        }
        majorDetails.put(MAJOR_ELECTIVES, electivesArray);
        JSONArray applicationAreasArray = new JSONArray();
        for (ApplicationArea applicationArea : major.getApplicationAreas()) {
            JSONObject applicationAreaObject = new JSONObject();
            applicationAreaObject.put(APPLICATION_AREA_TYPE, applicationArea.getType().toString());
            JSONArray coursesArray = new JSONArray();
            for (String uuid : applicationArea.getCourses()) {
                JSONObject courseObject = new JSONObject();
                courseObject.put(COURSE_ID, uuid);
                coursesArray.add(courseObject);
            }
            applicationAreaObject.put(APPLICATION_AREA_COURSES, coursesArray);
            applicationAreasArray.add(applicationAreaObject);
        }
        majorDetails.put(MAJOR_APPLICATION_AREAS, applicationAreasArray);
        majorDetails.put(MAJOR_CREDITS_REQUIRED, major.getCreditsRequired());
        return majorDetails;
    }
}