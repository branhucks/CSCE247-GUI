package model;

import java.util.ArrayList;

/**
 * ApplicationArea class
 */
public class ApplicationArea {
    private ApplicationType type;
    private ArrayList<String> courses;

    /**
     * Creates a new ApplicationArea instance
     * 
     * @param type    | the application area type
     * @param courses | the list of courses
     */
    public ApplicationArea(ApplicationType type, ArrayList<String> courses) {
        this.type = type;
        this.courses = courses;
    }

    // Getters and Setters
    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

}
