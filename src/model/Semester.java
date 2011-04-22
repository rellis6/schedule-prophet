/**
 * File:    Semester.java
 * Project: Schedule Prophet
 * Author:  g00gle
 * Date:    Mar 31, 2011
 * Section: M/W
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 */
package model;

import java.util.ArrayList;

/**
 * @author g00gle
 *
 * Description: A college semester.
 *
 */
public class Semester {
	private String season;
	private int year;
	private ArrayList<Course> classes;
	private boolean completed;
	
	/**
	 * Create a semester.
	 * @param season "Spring", "Summer", "Fall", or "Winter"
	 * @param year the year this course was taken
	 */
	public Semester(String season, int year) {
		this.season = season;
		this.year = year;
		classes = new ArrayList<Course>();
		setCompleted(false);
	}
	
	public Semester(Semester s) {
		this.season = s.getSeason();
		this.year = year;
		this.classes = s.getClasses();
		this.completed = s.getCompleted();
	}
	
	/**
	 * Returns whether or not two semesters are the same, based on name and year.
	 * @return whether or not two semesters are the same, based on name and year
	 */
	public boolean equals(Semester otherSemester) {
		return season.equals(otherSemester.getSeason()) && 
		this.year == otherSemester.getYear();
	}
	
	/**
	 * Get a clone of the list of classes within this semester.
	 * @return list of classes (Course objects) within this semester
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Course> getClasses() {
		return (ArrayList<Course>) classes.clone();
	}
	
	/**
	 * Add a course to this semester. If that course already exists, nothing will happen.
	 * @param c any course that is taken this semester
	 */
	public void addCourse(Course c) {
		if (!classes.contains(c)) { // remember that contains() is based on that object's .equals() method
			classes.add(c);
		}
	}
	
	/**
	 * 
	 * @return whether or not this course has been completed
	 */
	public boolean getCompleted() {
		return completed;
	}
	
	/**
	 * Given a course courseID, return the courseID if it exists in this semester.
	 * @param courseID any course courseID such as "CMSC 201"
	 * @return course object that matches the given courseID
	 * @throws Exception courseID passed must exist in this semester
	 */
	public Course getCourse(String courseID) throws NonExistentCourseException {
		for (int i = 0; i < classes.size(); i++) {
			if (courseID.equals(classes.get(i).getCourseID())) {
				return new Course(classes.get(i));
			}
		}
		throw new NonExistentCourseException("Course doesn't exist in this semester.");
	}
	
	/**
	 * 
	 * @return the name of this semester
	 */
	public String getSeason() {
		return new String(this.season);
	}
	
	/**
	 * Remove first instance of a course with the courseID equal to courseID
	 * @param courseID any course courseID, eg, "CMSC 201"
	 * @throws Exception courseID must exist in Semester.
	 */
	public Course removeCourse(String courseID) throws NonExistentCourseException {
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getCourseID().equals(courseID)) {
				return classes.remove(i);
			}
		}
		throw new NonExistentCourseException("Course ID not found.");
	}
	
	/**
	 * 
	 * @return the year this semester occurs
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}
}


