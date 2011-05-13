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
	private String name;
	
	//why does addCourse take a Course but removeCourse takes a String?
	// ^ I wasn't sure if Course objects would always be known. It would probably work just fine if they both took Course objects. 
	// This way is a little more flexible.
	
	/**
	 * Create a plan with 'name' set to an empty string.
	 * @param tracks Tracks this plan is meant to fulfill.
	 */
	public Plan(ArrayList<Track> tracks) {
		this.tracks = tracks;
		completedSemesters = new ArrayList<Semester>();
		futureSemesters = new ArrayList<Semester>();
		totalCredits = 0;
		this.name = "";
	}
	
	public String toString() {
		String s = "\nPlan name: " + name;
		s += "\nNumber of completed semesters: " + completedSemesters.size();
		s += "\nNumber of future semesters: " + futureSemesters.size();
		s += "\nNumber of credits: " + totalCredits;
		s += "Track for this plan: ";
		for (Track track : tracks) {
			s += "\n" + track;
		}
		return s;
	}
	
	/**
	 * 
	 * @param tracks ArrayList of desired Tracks for the plan
	 * @param name initial name of the plan
	 */
	public Plan(ArrayList<Track> tracks, String name) {
		this(tracks);
		setName(name);
	}
	
	/**
	 * 
	 * @param name sets the plan name equal to String parameter name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return returns String name of plan
	 */
	public String getName() {
		return name;
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
		//if prereqs not met, don't add
		//if(meetsPrereqs(course).size() > 0)
		//	return;
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
		
		System.out.println("Checking to see if " + course.getCourseID() + 
				" has met its prereqs.");
		System.out.println("Prerequisites: ");
		System.out.println(course.getPreReqs());
		
		ArrayList<Requirement> unfulfilledReqs = new ArrayList<Requirement>();
		ArrayList<Course> courses = getTakenAndPlannedCourses();
		ArrayList<Requirement> courseReqs = course.getPreReqs();
		
		for (Requirement req : courseReqs) {
			if (req.isFulfilled(courses).size() == 0) { // remember that isFulfilled() returns an empty list if it's not fulfilled
				System.out.println("Adding an unfulfilled requirement!: " + req);
				unfulfilledReqs.add(req);
			}
		}
		
		return unfulfilledReqs;
	}
	
	/**
	 * returns an ArrayList<course> of unplanned, completed, and planned courses for 
	 * all tracks used by the plan 
	 * @return list of courses in this plan
	 */
	public ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		/*for (Semester semester : completedSemesters) {
			courses.addAll(semester.getClasses());
		}
		for (Semester semester : futureSemesters) {
			courses.addAll(semester.getClasses());
		}
		return courses;*/
		for(Track track: tracks)
			for(Course course: track.getClasses())
				if (!courses.contains(course)) {
					courses.add(course);
				}
		return courses;
	}
	
	/**
	 * Get courses in the user's plan.
	 * @return
	 */
	public ArrayList<Course> getTakenAndPlannedCourses() {
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
	 * 
	 * @return an ArrayList<Course> of all courses not currently completed or planned
	 */
	public ArrayList<Course> getUnplannedCourses(){
		ArrayList<Course> unplanned = getCourses();
		
		for(Semester semester: completedSemesters){
			for(Course course: semester.getClasses())
				unplanned.remove(course);
		}
		
		for(Semester semester: futureSemesters){
			for(Course course: semester.getClasses())
				unplanned.remove(course);
		}
		return unplanned;
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
	 * @return returns an array list of String Arrays which contain the season and year of
	 * any semesters that need to be changed
	 */
	public ArrayList<String[]> setSemesterCompleted(String season, int year, boolean completed){
		//initializes and finds the specified semester
		Semester semester = null;
		try {
			semester = getSemester(season, year);
		} catch (NonExistentSemesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		ArrayList<Semester> allSemesters = getSemesters();
		ArrayList<Semester> needChanged = new ArrayList<Semester>();
		
		//test for completing a semester
		if(completed == true){
			//adds any prior uncompleted semesters to needChanged
			for(Semester current: allSemesters){
				//breaks when it gets to itself
				if(current.equals(semester))
					break;
				if(current.isCompleted() != completed)
					needChanged.add(current);
			}
			
			//if none need to be changed, complete
			if(needChanged.size() == 0){
				
				//do not complete if a prereq is unfulfilled
				for(Course course: semester.getClasses()){
					if(meetsPrereqs(course).size() > 0)
						return null;
				}
				
				semester.setCompleted(true);
				futureSemesters.remove(semester);
				completedSemesters.add(semester);
			}
		}
		//test for uncompleting semesters
		else{
			Semester current = null;
		
			//if not last semester
			if(allSemesters.indexOf(semester) != completedSemesters.size() - 1){
				System.out.println("INDEXOFSEM: " + allSemesters.indexOf(semester));
				System.out.println("ALLSEMSIZE: " + allSemesters.size());
				System.out.println("SEM.EQ(ALL.GET): " + semester.equals(allSemesters.get(0)));
				System.out.println("SEM == ALL.GET: " + (semester == allSemesters.get(0)));
				//TODO the semester in allSemesters the semester we need to uncomplete are copies 
				//of each other, not the same object, so it doesn't recognise that the semester is the last one
				//and doesn't uncomplete it. iterates through allSemesters after semester's index, adding 
				//each to needChanged until an uncompleted semester is hit or it reaches the end
				for(int i = allSemesters.indexOf(new Semester(season, year)) + 1; i < allSemesters.size(); i++){
					current = allSemesters.get(i);
					
					//breaks if an uncompleted semester is found
					if(current.isCompleted() == false)
						break;
					if(current.isCompleted() != completed)
						needChanged.add(current);
				}
			}
			//if last semester
			else{
				System.out.println("bla");
				semester.setCompleted(false);
				futureSemesters.add(0, semester);
				System.out.println("COMSEM SIZE: " + completedSemesters.size());
				completedSemesters.remove(semester);
				System.out.println("COMSEM SIZE: " + completedSemesters.size());
			}
		}
		
		ArrayList<String[]> pairs = new ArrayList<String[]>();
		
		//create and return season-year String pairs
		for(Semester current: allSemesters){
			String[] pair = new String[2];
			pair[0] = current.getSeason();
			pair[1] = Integer.toString(current.getYear());
			pairs.add(pair);
		}
		
		return pairs;
		
	}
	
	/**
	 * Change a completed course's grade and notes.
	 * 
	 * @param season of the semester
	 * @param year of the semester
	 * @param courseID eg, "CMSC 201"
	 * @param grade "A", "B", derp
	 * @param notes user's notes about the grade
	 * @throws NonExistentSemesterException semester specified must exist
	 * @throws NonExistentCourseException course must exist in specifed semester
	 */
	public void editCompletedCourse(String season, int year, String courseID, String grade,
			String notes) throws NonExistentSemesterException, NonExistentCourseException {
		Semester s = getSemester(season, year);
		Course c = s.getCourse(courseID);
		s.removeCourse(c.getCourseID());
		
		c.setGrade(grade);
		c.setNotes(notes);
		
		s.addCourse(c);
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
	
	public boolean addSemester(Semester newSemester) {
		String season = newSemester.getSeason();
		int year = newSemester.getYear();
		
		try {
			getSemester(season, year);
			return false;
		} catch (NonExistentSemesterException e) {
			futureSemesters.add(newSemester);
			return true;
		}
	}

	/**
	 * 
	 * @return returns an ArrayList of both completed and future semesters
	 */
	public ArrayList<Semester> getSemesters() {
		ArrayList<Semester> allSemesters = new ArrayList<Semester>();
		
//		for (Semester s : completedSemesters) {
//			allSemesters.add(new Semester(s));
//		}
//		for (Semester s : futureSemesters) {
//			allSemesters.add(new Semester(s));
//		}
		
		for (Semester s : completedSemesters) {
			allSemesters.add(s);
		}
		for (Semester s : futureSemesters) {
			allSemesters.add(s);
		}
		
		return allSemesters;
	}
	
	/**
	 * 
	 * @param completed boolean for to determine whether completed or uncompleted
	 * semesters will be returned
	 * @return an ArrayList of all completed semesters if completed is true, or
	 * all future semesters if completed is false
	 */
	public ArrayList<Semester> getSemesters(boolean completed) {
		ArrayList<Semester> Semesters = new ArrayList<Semester>();

		if(completed == true)
			for (Semester s : completedSemesters) {
				Semesters.add(new Semester(s));
			}
		if(completed == false)
			for (Semester s : futureSemesters) {
				Semesters.add(new Semester(s));
			}

		return Semesters;
	}
	
	/**
	 * 
	 * @param courseID String identifier of the Course
	 * @param season String season identifier of the semester the Course is in
	 * @param year int year identifier of the semester the Course is in
	 * @return
	 */
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
	
	
	public void completePriorSemesters(String season, int year){
		ArrayList<Semester> allSemesters = this.getSemesters();
		for(int i = 0; i <= allSemesters.indexOf(new Semester(season, year)); i++){
			allSemesters.get(i).setCompleted(true);
		}
	}
	//uncompletes from specified to latest completed
	public void uncompleteFutureSemesters(String season, int year){
		ArrayList<Semester> allSemesters = this.getSemesters();
		for(int i = allSemesters.indexOf(new Semester(season, year)); i < allSemesters.size(); i++){
			allSemesters.get(i).setCompleted(false);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Track> getTracks() {
		return (ArrayList<Track>) tracks.clone();
	}
}
