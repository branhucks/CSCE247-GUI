package model;

import java.util.ArrayList;

/**
 * Electives class
 */
public class Electives {
    private int minHours;
    private ElectiveType electiveType;
    private ArrayList<String> courses;

    /**
     * Creates a new instance of Electives
     * 
     * @param minHours
     * @param electiveType
     * @param courses      - Stores the UUID of the courses
     */
    public Electives(int minHours, ElectiveType electiveType, ArrayList<String> courses) {
        this.minHours = minHours;
        this.electiveType = electiveType;
        this.courses = courses;
    }

    // Getters and Setters
    public void setMinHours(int minHours) {
        this.minHours = minHours;
    }

    public int getMinHours() {
        return minHours;
    }

    public void setElectiveType(ElectiveType electiveType) {
        this.electiveType = electiveType;
    }

    public ElectiveType getElectiveType() {
        return electiveType;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }
}
