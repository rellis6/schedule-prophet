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
	private String name;
	
	/**
	 * Determine whether or not a list of Courses fulfills this requirement.
	 * 
	 * @param courseList list of courses
	 * @return an empty list if courseList doesn't fulfill the requirements; if courseList does
	 * fulfill this requirement, a list of courses that fulfill this requirement (taken from courseList)
	 * will be returned. This is done so that users of this class can make sure one course isn't used to fulfill multiple requirements.
	 */
	public abstract ArrayList<Course> isFulfilled(ArrayList<Course> courseList);
	
	public String toString() {
		String s = "Requirement\n";
		s += "Length: " + requirements.size();
		s += "\nMin Credits: " + minCredits;
		s += "\n";
		return s;
	}
	
	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(ArrayList<Course> requirements) {
		this.requirements = requirements;
		requirements.remove(null);
	}

	/**
	 * @return the requirements
	 */
	public ArrayList<Course> getRequirements() {
		if(requirements == null)
			System.out.println("FUUUUUUUUUUUUUUUUUUCK");
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
