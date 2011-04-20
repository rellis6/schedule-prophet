/**
 * File:      Requirement.java
 * Author:    Mark Pallone 
 */
package model;

import java.util.ArrayList;

/**
 * @author g00gle
 *
 */
public abstract class Requirement {
	private ArrayList<Course> requirements;
	private int minCredits;
	
	/**
	 * 
	 * @param courseList list of courses
	 * @return an empty list if courseList doesn't fulfill the requirements; if courseList does
	 * fulfill this requirement, a list of courses that fulfill this requirement (taken from courseList)
	 * will be returned.
	 */
	public abstract ArrayList<Course> isFulfilled(ArrayList<Course> courseList);
	
	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(ArrayList<Course> requirements) {
		this.requirements = requirements;
	}

	/**
	 * @return the requirements
	 */
	public ArrayList<Course> getRequirements() {
		return requirements;
	}

	/**
	 * @param minCredits the minCredits to set
	 */
	public void setMinCredits(int minCredits) {
		this.minCredits = minCredits;
	}

	/**
	 * @return the minCredits
	 */
	public int getMinCredits() {
		return minCredits;
	}


}
