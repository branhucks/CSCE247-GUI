package model;

import java.util.ArrayList;

/**
 * SemesterPlan class
 */
public class SemesterPlan {
    private ArrayList<StudentCourse> studentCourses;

    /**
     * Creates a new SemesterPlan instance
     * 
     * @param courses | the courses in the semester plan
     */
    public SemesterPlan(ArrayList<StudentCourse> courses) {
        this.studentCourses = courses;
    }

    /**
     * Adds a course to the semester plan
     * 
     * @param subject  | the course's subject
     * @param number   | the course's number
     * @param semester | the semester the student plans to take the course
     */
    public void addCourse(String subject, String number, int semester) {
        StudentCourse newCourse = new StudentCourse(subject + " " + number, semester, "Planned", 0, false);
        studentCourses.add(newCourse);
    }

    /**
     * Removes a course from the semester plan
     * 
     * @param subject | the course's subject
     * @param number  | the course's number
     */
    public void removeCourse(String subject, String number) {
        for (StudentCourse course : studentCourses) {
            if (course.getCourseID().equals(subject + " " + number))
                studentCourses.remove(course);
        }
    }

    // Getters and setters
    public void setStudentCourses(ArrayList<StudentCourse> courses) {
        this.studentCourses = courses;
    }

    public ArrayList<StudentCourse> getStudentCourses() {
        return studentCourses;
    }
}
