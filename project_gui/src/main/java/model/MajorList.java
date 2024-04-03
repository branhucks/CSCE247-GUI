package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A MajorList class (Singeton Design Pattern)
 * Provides method to access and modify majors in the list
 */
public class MajorList {
    private static MajorList majors;
    private ArrayList<Major> majorList;

    /**
     * Creates a new MajorList instance
     */
    public MajorList() {
        majorList = DataLoader.getMajors();
    }

    /**
     * Get major by UUID
     * 
     * @param uuid | the major's UUID
     * @return | the major retrieved
     */
    public Major getMajorByUUID(String uuid) {
        for (Major major : majorList) {
            if (major.getUUID().equals(uuid))
                return major;
        }
        return null;
    }

    /**
     * Gets an instance of the MajorList
     * 
     * @return | MajorList instance
     */
    public static MajorList getInstance() {
        if (majors == null) {
            majors = new MajorList();
        }
        return majors;
    }

    /**
     * Validates whether the major exists
     * 
     * @param majorName | the major's name to search
     * @return | true or false depending on if it exists
     */
    public boolean haveMajor(String majorName) {
        for (Major major : majorList) {
            if (major.getMajorName().equals(majorName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a Major by the major's name
     * 
     * @param courseID | the major's name to search
     * @return | the Major object with given name
     */
    public Major getMajor(String majorName) {
        for (Major major : majorList) {
            if (major.getMajorName().equals(majorName)) {
                return major;
            }
        }
        return null;
    }

    /**
     * Get all majors
     * 
     * @return | list of all majors
     */
    public ArrayList<Major> getMajors() {
        return majorList;
    }

    /**
     * Adds a major to the list of majors
     * 
     * @param majorName        | the name of the major
     * @param requiredCourses  | the required courses of the major
     * @param electives        | the major's electives
     * @param applicationAreas | a possible application area
     * @param creditsRequired  | the total amount of required credits
     * @return | true or false depending on if the major was added successfully
     */
    public boolean addMajor(String majorName, ArrayList<String> requiredCourses,
            ArrayList<Electives> electives, ArrayList<ApplicationArea> applicationAreas, int creditsRequired) {
        if (haveMajor(majorName))
            return false;
        majorList.add(new Major((UUID.randomUUID()).toString(), majorName, requiredCourses, electives, applicationAreas,
                creditsRequired));
        return true;
    }

    /**
     * Save the majors in the list to the data file
     */
    public void saveMajors() {
        DataWriter.saveMajors();
    }
}
