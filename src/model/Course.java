/**
 * File:    Course.java
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
 * Description: A college course.
 */
public class Course {
	private String courseID;
	private String grade;
	private String notes;
	private ArrayList<Requirement> preReqs;
	private String minPassingGrade;
	private int credits;
	private String courseTitle;
	private String category;
	private String description;
	
	/**
	 * Create a deep copy of a course
	 * 
	 * @param c - any Course object
	 */
	public Course(Course c) {
		courseID = c.getCourseID();
		grade = c.getGrade();
		notes = c.getNotes();
		preReqs = c.getPreReqs();
		minPassingGrade = c.getMinPassingGrade();
		credits = c.getCredits();
		courseTitle = c.getCourseTitle();
		category = c.getCategory();
		description = c.getDescription();
	}
	
	/**
	 * Create a course.
	 * 
	 * @param courseID course identification string, eg, "CMSC 201"
	 * @param grade "A", "B", ..., "F", "W", "P"
	 * @param notes any notes about the course
	 * @param minPassingGrade minimum passing grade
	 * @param credits credit value of the coursed
	 * @param completed whether or not this course has been completed
	 * @param courseTitle ex: "Introduction to Computer Science"
	 */
	public Course(String courseID, String grade, String notes, 
			String minPassingGrade, int credits, String courseTitle, String category,
			String description) {
		this.courseID = courseID;
		this.grade = grade;
		this.notes = notes;
		this.minPassingGrade = minPassingGrade;
		preReqs = new ArrayList<Requirement>();
		this.credits = credits;
		this.courseTitle = courseTitle;
		this.category = category;
		this.description = description;
	}
	
	/**
	 * 
	 * @param courseID course identification string, such as "CMSC 201"
	 * @param minPassingGrade minimum passing grade
	 * @param credits number of credits course is worth
	 * @param courseTitle ex: "Introduction to Computer Science"
	 */
	public Course(String courseID, String minPassingGrade, int credits, String courseTitle,
				  String category, String description) {
		this.courseID = courseID;
		this.minPassingGrade = minPassingGrade;
		this.grade = "";
		this.notes = "";
		preReqs = new ArrayList<Requirement>();
		this.credits = credits;
		this.courseTitle = courseTitle;
		this.category = category;
		this.description = description;
	}
	
	/**
	 * Compare courses for equality.
	 * 
	 * @param c any course
	 * @return whether or not these courses are the same
	 */
	public boolean equals(Course c) {
		return this.courseID.equals(c.getCourseID());
	}
	
	/**
	 * Return whether or not this course has met its grade requirement.
	 * 
	 * @return whether or not this course has met its grade requirement
	 */
	public boolean meetsGradeRequirement() {
		if (grade.equals("") || grade.equals("P") || grade.equals("F")) {
			return false;
		}
		
		if (grade.equals("A")) {
			return true;
		} else if (grade.equals("B")) {
			if (!minPassingGrade.equals("A")) {
				return true;
			} 
		} else if (grade.equals("C")) {
			if ((!minPassingGrade.equals("A")) && (!minPassingGrade.equals("B"))) {
				return true;
			}
		} else if (grade.equals("D")) {
			if ((!minPassingGrade.equals("A")) && (!minPassingGrade.equals("B")) && 
					(!minPassingGrade.equals("C"))) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Add a prerequisite to this course.
	 * 
	 * @param c any prerequisite course
	 */
	public void addPreReq(Requirement c) {
		if (!preReqs.contains(c)) {
			preReqs.add(c);
		}
	}
	
	/**
	 * 
	 * @param newPreReqs new list of prerequisites
	 */
	public void setPreReqs(ArrayList<Requirement> newPreReqs) {
		this.preReqs = newPreReqs;
	}
	
	/**
	 * 
	 * @return the course ID for this class
	 */
	public String getCourseID() {
		return new String(this.courseID);
	}
	
	/**
	 * 
	 * @return the grade for this course
	 */
	public String getGrade() {
		return new String(this.grade);
	}
	
	/**
	 * 
	 * @return the notes for this course
	 */
	public String getNotes() {
		return new String(this.notes);
	}
	
	/**
	 * Get a clone of prerequisite courses.
	 * @return array list of prerequisite courses
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Requirement> getPreReqs() {
		return (ArrayList<Requirement>) preReqs.clone();
	}
	
	/**
	 * Potential values: "A", "B", "C", "D", "F"
	 * @return minimum grade to pass this course
	 */
	public String getMinPassingGrade() {
		return new String(minPassingGrade);
	}
	
	/**
	 * 
	 * @param newGrade new grade for this course
	 */
	public void setGrade(String newGrade) {
		this.grade = newGrade;
	}
	
	/**
	 * 
	 * @param notes notes taken for this course
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * @return this courseID
	 */
	public String toString() {
		return new String(courseID + "\nGrade: " + grade + "\nNotes: " + notes + "\n");
	}
	
	/**
	 * 
	 * @return the amount of credits this course is worth
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * 
	 * @return the title of this course, eg, "Introduction to Computer Science"
	 */
	public String getCourseTitle() {
		return new String(courseTitle);
	}
	
	/**
	 * Set the category.
	 * @param category - the course category, eg, "Computer Science", "Biology", "GEP", etc.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Get this course's category.
	 * @return category - eg, "Computer Science", "Biology", "GEP", etc.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Set the description of the course.
	 * @param description course description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the description of the course.
	 * @return course description
	 */
	public String getDescription() {
		return description;
	}

}
