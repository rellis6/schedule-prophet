/**
 * File:      FlexibleRequirement.java
 * Author:    g00gle
 */
package model;

import java.util.ArrayList;

/**
 * Flexible requirements--for when X number of courses out of a list of courses must be taken to fulfill a requirement.
 * 
 * @author g00gle
 *
 */
public class FlexibleRequirement extends Requirement {
	
	/** this number of courses must be taken from courseList */
	private int numToTake;
	
	
	/**
	 * 
	 * @param courseList list of courses that make up this requirement
	 * @param courseCount how many courses out of courseList need to be taken
	 */
	public FlexibleRequirement(ArrayList<Course> courseList, int courseCount, int minCredits) {
		setRequirements(courseList);
		this.numToTake = courseCount;
		setMinCredits(minCredits);
	}
	
	public String toString() {
		String s = "\nFlexible Requirement\n";
		s += "Length of Requirements: " + getRequirements().size();
		s += "\nMin Credits: " + getMinCredits();
		s += "\nNum To Take: " + numToTake + "\n";
		return s;
	}


	/* (non-Javadoc)
	 * @see model.Requirement#isFulfilled(java.util.ArrayList)
	 */
	@Override
	public ArrayList<Course> isFulfilled(ArrayList<Course> courseList) {
		ArrayList<Course> fulfillingCourses = new ArrayList<Course>();
		int creditCount = 0;
		for (int i = 0; i < courseList.size(); i++) {
			if (getRequirements().contains(courseList.get(i)) && 
					courseList.get(i).meetsGradeRequirement()) {
				fulfillingCourses.add(new Course(courseList.get(i)));
				creditCount += courseList.get(i).getCredits();
			}
		}
		if (fulfillingCourses.size() < numToTake || creditCount < getMinCredits()) {
			return new ArrayList<Course>();
		} 
		return fulfillingCourses;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
