/**
 * File:    Track.java
 * Project: scheduleProphet
 * Author:  Mark Pallone
 * Date:    Apr 13, 2011
 * Section: M/W
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 */
package model;

import java.util.ArrayList;

/**
 * @author g00gle
 *
 * Description: A graduation track.
 *
 */
public class Track {
	
	public static final int MIN_CREDITS = 120;
	private ArrayList<Requirement> courseRequirements;
	private String name;
	
	/**
	 * Create a new Track with name set to an empty String.
	 * @param courseRequirements
	 */
	public Track(ArrayList<Requirement> courseRequirements) {
		this.courseRequirements = courseRequirements;
		name = "";
	}
	
	public String toString() {
		String s = "Track name: name";
		s += "\nnumber of courseRequirements in this track: " + courseRequirements.size();
		s += "\n";
		return s;
	}
	
	/**
	 * Create a new Track. 
	 * 
	 * @param courseRequirements list of requirements to fulfill
	 * @param name name of this track
	 */
	public Track(ArrayList<Requirement> courseRequirements, String name) {
		this.courseRequirements = courseRequirements;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	/*
	 *    fulfillsTrack(courseList):
	 *    
	 *    courseListCopy = deep copy of courseList // courseList can't be modified
	 *    
	 *    for each requirement:
	 *        if courseList doesn't fulfill requirement:
	 *            return false
	 *        if it does:
	 *            if requirement is FlexibleRequirementSet:
	 *                remove the courses from courseListCopy
	 *    return true
	 *                
	 */
	
	/**
	 * See if a list of courses fulfills this track. 
	 * 
	 * @param courses - a list of Course objects that may fulfill this track
	 */
	public boolean fulfillsTrack(ArrayList<Course> courses) {
		
		// make deep copy of courses since it can't be modified
		ArrayList<Course> courseListCopy = new ArrayList<Course>();
		for (Course c : courses) {
			courseListCopy.add(new Course(c));
		}
		
		for (Requirement requirement : courseRequirements) {
			ArrayList<Course> usedCourses = requirement.isFulfilled(courseListCopy);
			
			// doesn't fulfill requirement
			if (usedCourses.size() == 0) {
				return false;
			} else { // does fulfill
				// flexible requirement sets aren't removed, since those courses 
				// might still count for something else
				if (!(requirement instanceof FlexibleRequirementSet)) {
					for (Course course : usedCourses) {
						// make sure one course isn't counted for multiple requirements
						courseListCopy.remove(course); 
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Name:             isRequired   <br \>
	 * Precondition(s):           <br \>
	 * Postcondition(s):          <br \>
	 * @param course
	 * @return
	 */
	public boolean isRequired(Course course) {
		for (Requirement requirement : courseRequirements) {
			ArrayList<Course> thisRequirementList = requirement.getRequirements();
			if (thisRequirementList.contains(course)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the list of requirements for this course.
	 * @return list of Requirements 
	 */
	public ArrayList<Requirement> getRequirements() {
		return courseRequirements;
	}
	
	/**
	 * Get a unique list of courses needed by this track.
	 * 
	 * @return list of classes needec by this Track. No duplicates in the list
	 */
	public ArrayList<Course> getClasses() {
		ArrayList<Course> uniqueCourseList = new ArrayList<Course>();
		
		//System.out.println("Length of course requirements: " + courseRequirements.size());
		
		for (Requirement requirement : courseRequirements) {
			//System.out.println("Size of this requirement: " + requirement.getRequirements().size());
			for (Course course : requirement.getRequirements())	{
				if (!uniqueCourseList.contains(course)) {
					uniqueCourseList.add(new Course(course));
				}
			}
		}
		
		return uniqueCourseList;
	}

}
