/**
 * File:    Plan.java
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
 * Description: A college plan.
 *
 */
public class Plan {

	private ArrayList<Semester> completedSemesters;
	private ArrayList<Semester> futureSemesters;
	private ArrayList<Track> tracks;
	private int totalCredits;
	
	//why does addCourse take a Course but removeCourse takes a String?
	
	/**
	 * Create a plan.
	 * @param tracks Tracks this plan is meant to fulfill.
	 */
	public Plan(ArrayList<Track> tracks) {
		this.tracks = tracks;
		completedSemesters = new ArrayList<Semester>();
		futureSemesters = new ArrayList<Semester>();
		totalCredits = 0;
	}
	
	/**
	 * Add a course to a semester in this plan.
	 * @param course any course object
	 * @param season any of the four seasons, "Summer", etc.
	 * @param year the year of the semester
	 * @throws NonExistentSemesterException This will be thrown if the season and year don't correspond
	 * to a semester already created in this Plan.
	 */
	public void addCourse(Course course, String season, int year) throws NonExistentSemesterException {
		getSemester(season, year).addCourse(course);
		totalCredits += course.getCredits();
	}
	
	/**
	 * 
	 * @return The total credits in this plan.
	 */
	public int getTotalCredits() {
		return totalCredits;
	}
	
	/**
	 * Remove a course from the specified semester.
	 * 
	 * @param courseID ID for the course, eg, "CMSC 201"
	 * @param season "Summer", "Fall", etc. of the semester
	 * @param year year of the semester
	 * @throws Exception courseID must exist in the semester specified by season and year
	 */
	public void removeCourse(String courseID, String season, int year) throws NonExistentCourseException {
		Semester semester = null;
		try {
			semester = getSemester(season, year);
		} catch (NonExistentSemesterException e) {
			System.out.println(e.getMessage());
			//warning/error window in gui?
			
		}
		Course removedCourse = semester.removeCourse(courseID);
		totalCredits -= removedCourse.getCredits();
	}
	
	
	/**
	 * See if this plan meets a Course's prerequisites.
	 * @param course see if this course's prereqs have been met
	 * @return an empty ArrayList if course meets its prereqs, and an ArrayList filled with Requirements
	 *  that still need to be fulfilled otherwise
	 */
	public ArrayList<Requirement> meetsPrereqs(Course course) {
		ArrayList<Requirement> unfulfilledReqs = new ArrayList<Requirement>();
		ArrayList<Course> courses = getCourses();
		ArrayList<Requirement> courseReqs = course.getPreReqs();
		
		for (Requirement req : courseReqs) {
			if (req.isFulfilled(courses).size() != 0) {
				unfulfilledReqs.add(req);
			}
		}
		
		return unfulfilledReqs;
	}
	
	/**
	 * get all of the courses within this plan. 
	 * @return list of courses in this plan
	 */
	private ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		for (Semester semester : completedSemesters) {
			courses.addAll(semester.getClasses());
		}
		for (Semester semester : futureSemesters) {
			courses.addAll(semester.getClasses());
		}
		return courses;
	}
	
	/**
	 * Find a semester in this plan from either completedSemesters or futureSemesters.
	 * 
	 * @param season "Spring", "Summer", "Fall", or "Winter"
	 * @param year the year the semester was taken
	 * @return the semester that occurs in the specified time
	 * @throws Exception season and year must lead to a valid semester
	 */
	private Semester getSemester(String season, int year) throws NonExistentSemesterException {
		Semester tempSemester = new Semester(season, year);
		
		for (int i = 0; i < completedSemesters.size(); i++) {
			if (tempSemester.equals(completedSemesters.get(i))) {
				return completedSemesters.get(i);
			}
		}
		for (int i = 0; i < futureSemesters.size(); i++) {
			if (tempSemester.equals(futureSemesters.get(i))) {
				return futureSemesters.get(i);
			}
		}
		
		throw new NonExistentSemesterException("No semester " + season + ", " + year + " exists.");
	}
	
	
	/**
	 * if not already completed, sets isCompleted to true,
	 * removes from futureSemesters, and adds to completedSemesters
	 * @param season season of semester
	 * @param year year of semester
	 * @return true if successfully completed, false if already completed
	 */
	
	public boolean completeSemester(String season, int year){
		Semester semester = null;
		try {
			semester = getSemester(season, year);
		} catch (NonExistentSemesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(semester.getCompleted() == true)
			return false;
		semester.setCompleted(true);
		futureSemesters.remove(semester);
		completedSemesters.add(semester);
		
		return true;
		
	}
	/**
	 * Adds newly created semester to the end of the futureSemester list,
	 * can be dragged to completedSemesters in GUI if completed
	 * @param season season of semester to be added
	 * @param year year of season to be added
	 * @return returns true if successfully added, false if already exists
	 */
	public boolean addSemester(String season, int year){
		try {
			getSemester(season, year);
			return false;
		} catch (NonExistentSemesterException e) {
			futureSemesters.add(new Semester(season, year));
			return true;
		} 
		
	}
	
	public Course getCourse(String courseID, String season, int year){
		try {
			return getSemester(season, year).getCourse(courseID);
		} catch (NonExistentCourseException e) {
			// error/warning?
			e.printStackTrace();
		} catch (NonExistentSemesterException e) {
			// error/warning?
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Track> getTracks() {
		return (ArrayList<Track>) tracks.clone();
	}
}
