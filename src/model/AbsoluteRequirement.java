/**
 * File:      AbsoluteRequirement.java
 * Author:    g00gle
 */
package model;

import java.util.ArrayList;

/**
 * Requirements that must be fulfilled entirely.
 * 
 * @author g00gle
 */
public class AbsoluteRequirement extends Requirement {
	private String name;
	
	/**
	 * Create and AbsoluteRequirement.
	 * @param requirements required courses
	 */
	public AbsoluteRequirement(ArrayList<Course> requirements, int minCredits, String name) {
		this.setRequirements(requirements);
		this.setMinCredits(minCredits);
		this.setName(name);
	}
	
	public String toString() {
		String s = "\nAbsoluteRequirement";
		s += "\nLength of requirements: " + getRequirements().size();
		s += "Courses required: " + getRequirements();
		s += "\nMin Credits: " + getMinCredits();
		s += "\n";
		return s;
	}

	/* (non-Javadoc)
	 * @see model.Requirement#isFulfilled(java.util.ArrayList)
	 */
	@Override
	public ArrayList<Course> isFulfilled(ArrayList<Course> courseList) {
		int creditCount = 0;
		ArrayList<Course> usedCourses = new ArrayList<Course>();
		// use a deep copy of courseList so it's not modified
		ArrayList<Course> courseListCopy = new ArrayList<Course>();
		for (Course c : courseList) {
			courseListCopy.add(new Course(c));
		}
		// don't bother with courses that don't meet min grade requirement
		/*
		for (Course c : courseListCopy) {
			if (!c.meetsGradeRequirement()) {
				courseListCopy.remove(c);
			}
		}
		*/
		ArrayList<Course> requirements = getRequirements();
		
		for (int i = 0; i < requirements.size(); i++) {
			if (!courseListCopy.contains(requirements.get(i))) {
				return new ArrayList<Course>();
			} else {
				int index = courseListCopy.indexOf(requirements.get(i));
				usedCourses.add(courseListCopy.get(index));
				creditCount += courseListCopy.get(index).getCredits();
			}
		}
		
		if (creditCount < getMinCredits()) {
			return new ArrayList<Course>();
		}
		
		return usedCourses;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
