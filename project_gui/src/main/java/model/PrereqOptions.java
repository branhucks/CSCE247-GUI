package model;

import java.util.ArrayList;

/**
 * PrereqOptions class
 */
public class PrereqOptions {
    private int choices;
    private int minGrade;
    private ArrayList<String> courses;

    /**
     * Creates a new PrereqOptions instance
     * 
     * @param course | the prerequisite course
     */
    public PrereqOptions(int choices, int minGrade, ArrayList<String> courses) {
        this.choices = choices;
        this.minGrade = minGrade;
        this.courses = courses;
    }

    // Getter and setter methods for course HashMap
    public void setChoices(int choices) {
        this.choices = choices;
    }

    public void setMinGrade(int minGrade) {
        this.minGrade = minGrade;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public int getChoices() {
        return choices;
    }

    public int getMinGrade() {
        return minGrade;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }
}
