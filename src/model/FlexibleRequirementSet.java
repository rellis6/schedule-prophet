/**
 * File:      FlexibleRequirementSet.java
 * Author:    Mark Pallone 
 */
package model;

import java.util.ArrayList;

/**
 * @author mark
 *
 */
public class FlexibleRequirementSet extends Requirement {
	
	private ArrayList<AbsoluteRequirement> requirements;
	private int numToTake;

	/**
	 * Create a FlexibleRequirementSet, with the number of AbsoluteRequirements to take set to 1.
	 * @param requirements list of absolute requirements
	 */
	public FlexibleRequirementSet(ArrayList<AbsoluteRequirement> requirements, int minCredits) {
		this.requirements = requirements;
		numToTake = 1;
		setMinCredits(minCredits);
	}
	
	/**
	 * Create a FlexibleRequirementSet.
	 * @param requirements list of absolute requirements
	 * @param numToTake number of absolute requirements to fulfill in order to fulfill this FlexibleRequirementSet
	 */
	public FlexibleRequirementSet(ArrayList<AbsoluteRequirement> requirements, int numToTake, int minCredits) {
		this.requirements = requirements;
		this.numToTake = numToTake;
		setMinCredits(minCredits);
	}
	

	/* (non-Javadoc)
	 * @see model.Requirement#isFulfilled(java.util.ArrayList)
	 */
	@Override
	public ArrayList<Course> isFulfilled(ArrayList<Course> courseList) {
		int fulfilledRequirements = 0;
		int creditCount = 0;
		ArrayList<Course> fulfillingCourses = new ArrayList<Course>();
		for (int i = 0; i < requirements.size(); i++) {
			ArrayList<Course> tempFulfillingCourses = requirements.get(i).isFulfilled(courseList);
			if (tempFulfillingCourses.size() != 0) { // remember that isFulfilled() returns an empty list if courseList doesn't satisfy the requirement
				fulfilledRequirements++;
				
				// since this AbsoluteRequirement is fulfilled, add the courses that fulfilled it to fulfilledRequirements
				for (int j = 0; j < tempFulfillingCourses.size(); j++) {
					fulfillingCourses.add(tempFulfillingCourses.get(j));
					creditCount += tempFulfillingCourses.get(j).getCredits();
				}
			}
		}
		if (fulfilledRequirements < numToTake || creditCount < getMinCredits()) {
			
			return new ArrayList<Course>();
		}
		return fulfillingCourses;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Test all requirement courses.

	}

}
