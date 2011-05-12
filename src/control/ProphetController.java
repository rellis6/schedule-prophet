/**
 * File:    ProphetController.java
 * Project: CS 345 
 * Author:  Mark Pallone
 * Date:    Mar 31, 2011
 * Section: 
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 * Class Invariant(s):
 */
package control;

import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import view.StartMenu;

import model.*;
import data.*;

/**
 * File: ProphetController.java
 * Project: schedule-prophet
 * @author g00gle
 * Date: 
 * Description: Every action that can be taken using the GUI is called as a method 
 * in the ProphetController class.
 */
public class ProphetController {
	Plan plan;
	ArrayList<Track> TrackList = new ArrayList<Track>();
	CoursePathDAO courseDAO = new CoursePathDAO();
	UserPlanDAO planDAO;

	/**
	 * Name: initTrackList()
	 * Precondition(s): String track is a legitimate input for a track
	 * PostCondition(s): initializes TrackList based on the passed String track
	 * @param track String name of the chosen academic track
	 */
	private void initTrackList(String track){
		System.out.println("init: " + track);
		
		this.TrackList = courseDAO.getTrackCourses(track);
	}
	
	/**
	 * Name: newPlan()
	 * Precondition(s): String track is a legitimate input for a track
	 * Postcondition(s): initializes TrackList, initializes plan using TrackList
	 * @param track String name of the chosen academic track
	 */
	public void newPlan(String track){
		initTrackList(track);
		this.plan = new Plan(TrackList);
	}
	
	/**
	 * Name: newPlan()
	 * Precondition(s): String track is a legitimate input for a track
	 * Postcondition(s): initializes TrackList, initializes plan using TrackList, sets plan's
	 *  				 name to the passed String name
	 * @param track String name of the chosen academic track
	 * @param name String name of the plan
	 */
	public void newPlan(String track, String name){
		System.out.println("new: " + track);
		initTrackList(track);
		this.plan = new Plan(TrackList, name);
	}
	
	/**
	 * Name: loadPlan()
	 * Precondition(s): String filename must be the name of a plan file in the working directory
	 * Postcondition(s): initializes planDAO based on the passed filename, initializes plan
	 * using on PlanDAO
	 * @param filename the String name of the plan to be opened
	 */
	public void loadPlan(String filename){
		planDAO = new UserPlanDAO(filename);
		plan = planDAO.getPlan();
		for(int i=0; i<plan.getSemesters().size(); i++){
			try {
				plan.getSemesters().get(i).removeCourse("Dummy");
			} catch (NonExistentCourseException e) {
				// TODO Auto-generated catch block
			}
		}
	}
	
	/**
	 * Name: addCourse()
	 * Precondition(s): Plan plan is initialized
	 * 					Specified semester exists
	 * Postcondition(s): returns array which contains grade + comments	
	 * @param courseID ID of desired course
	 * @param season String season semester identifier
	 * @param comments initial comments to note about the course
	 * @param year int year semester identifier
	 */
	public void addCourse(String courseID, String season, String comments, int year){
		try {
			//duplicate course exception?
			System.out.println("TEST: " + courseDAO.getCourse(courseID).getCourseTitle());
			plan.addCourse(courseDAO.getCourse(courseID), season, year);
			for(Semester semester: plan.getSemesters(false)){
				semester.toString();
			}
		} catch (NonExistentSemesterException e) {
			System.out.print(e.toString());
			//warning/error window
		}
	}	
	
	/**
	 * Name: removeCourse()	
	 * Precondition(s): Plan plan is initialized
	 * 					courseID is a valid course identifier
	 * 					season and year are valid semester identifiers
	 * Postcondition(s): removes a given course from a specific semester by calling plan's
	 * 					 removeCourse() method
	 * @param courseID ID of desired course
	 * @param season String season semester identifier
	 * @param year int year semester identifier
	 * @return boolean true if successful, false otherwise
	 */
	public boolean removeCourse(String courseID, String season, int year){
		
		try {
			plan.removeCourse(courseID, season, year);
		} catch (NonExistentCourseException e) {
			System.out.print(e.getMessage());
			return false;
			//warning/error window?
		}
		
		//not sure what return should be; boolean t/f if any other courses exist at all?
		return true;
	}

	/**
	 * Name: getCourseInfo()
	 * Precondition(s): Plan plan is initialized
	 * 					a Course with the supplied CourseID exists in the specified Semester
	 * @param courseID ID of desired course
	 * @param season String season semester identifier
	 * @param year int year semester identifier
	 * @return A String array with two elements, the first being the Grade and the
	 * second being any notes recorded for the course
	 */
	public String[] getCourseInfo(String courseID, String season, int year){
		String[] array = {plan.getCourse(courseID, season, year).getGrade(), 
				plan.getCourse(courseID, season, year).getNotes()};
		return array;
	}
	
	/**
	 * Name: editCourse()
	 * Precondition(s):
	 * @param season String season semester identifier
	 * @param year int year semester identifier
	 * @param courseID String CourseID course identifier
	 * @param grade String grade received in specified course
	 * @param notes String notes about the specified course
	 */
	public void editCourse(String season, int year, String courseID, String grade, String notes){
		try {
			plan.editCompletedCourse(season, year, courseID, grade, notes);
		} catch (NonExistentSemesterException e) {
			e.printStackTrace();
		} catch (NonExistentCourseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Name: savePlan()
	 * Precondition(s): Plan plan is initialized
	 * Postcondition(s): Saves the current plan in the program directory for later
	 *                   retrieval through the loadPlan() function       
	 */		
	public void savePlan(){
		//TODO does not save empty semesters as the xml file is course centric
		//TODO possibly fixed by adding a dummy blank course to each empty semester during the save
		//TODO and stripping it out during loads
		
		System.out.println(plan.getName());
		Course dummy = new Course("Dummy", "E", 0, "Dummy", "Dummy", "Dummy");
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		for(int i=0; i<plan.getSemesters().size(); i++){
			plan.getSemesters().get(i).addCourse(dummy);
		}
		planDAO = new UserPlanDAO(plan.getName());
		planDAO.savePlan(plan);
	}

	/**
	 * Name: addSemester()
	 * Precondition(s): Plan plan is initialized
	 * Postcondition(s): adds a newly created semester object using the given season and
	 *                   year to plan
	 * @param season season of semester to be added
	 * @param year year of semester to be added
	 */
	public void addSemester(String season, int year){
		if(plan.addSemester(season, year) == false){
			System.out.println("ADDSEM BAAAAD");
			//error window
		}
		else{
			System.out.println("ADDSEM ELSE(GOOD)");
			//success window?
		}
	}
	
	/**
	 * Name: deletePlan()
	 * Precondition(s): Plan plan is initialized
	 * Postconditions(s): deletes the plan file which has the name String plan from
	 *                    the program directory
	 * @param plan the String name of the plan to be deleted
	 */
	public void deletePlan(String plan){
		planDAO.deletePlan(plan);
	}

	
	/**
	 * Name: getPlans()
	 * Precondition(s): none
	 * Postcondition(s): Gets the list of all files in the prophet directory
	 * (located in the user's AppData folder). If no files exist, returns an empty list.
	 * @return The ArrayList<String> of all files in the prophet directory
	 * @author Katherine Miller
	 */
	public ArrayList<String> getPlans() {
		ArrayList<String> planFiles = new ArrayList<String>();
		String prophetDirPath = System.getenv("APPDATA") + "\\prophet";
		File prophetDir = new File(prophetDirPath);
		String[] fileList = prophetDir.list();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				planFiles.add(fileList[i]);
			}
		}
		return planFiles;
	}

	
	/**
	 * Name: setSemesterCompleted()
	 * Precondition: Plan plan must contain at least one completed or uncompleted semester
	 *               depending on if that semester is being uncompleted or completed, respectively
	 * @param season season of semester to be completed
	 * @param year year of semester to be completed
	 * @param completed boolean of what the semesterCompleted value of the specified semester 
	 * should be set to
	 * @return an ArrayList of any semesters before the semester to be completed which aren't
	 * completed
	 */
	public ArrayList<String[]> setSemesterCompleted(String season, int year, boolean completed){
		return plan.setSemesterCompleted(season, year, completed);
	}
	
	/**
	 * Name: getCompletedSemesters()
	 * Precondition(s): Plan plan is initialized
	 * @return an arraylist of all completed semesters
	 */
	public ArrayList<Semester> getCompletedSemesters() {
		for(Semester cur: plan.getSemesters(true))
			System.out.println(cur.getSeason() + cur.getYear());
		return plan.getSemesters(true);
	}

	/**
	 * Name: getFutureSemesters()
	 * Precondition(s): Plan plan is initialized
	 * @return an ArrayList<Semester> of all uncompleted semesters
	 */
	public ArrayList<Semester> getFutureSemesters() {
		return plan.getSemesters(false);
	}	
	
	/**
	 * Name: getFutureCourses()
	 * Precondition(s): Plan plan is initialized
	 * @return an ArrayList<Course> of future/planned courses in the current plan
	 */
	public ArrayList<Course> getFutureCourses(){
		ArrayList<Course> future = new ArrayList<Course>();
		
		//iterates through all completed semesters
		for(Semester curSem: getFutureSemesters()){
			for(Course course: curSem.getClasses())
				future.add(course);
		}
		
		return future;
	}
	
	/**
	 * Name: getUnplannedCourses()
	 * Precondition(s): Plan plan is initialized
	 * @return An ArrayList<Course> of Courses that are neither currently completed 
	 * or uncompleted by the plan
	 */
	public ArrayList<Course> getUnplannedCourses(){
		ArrayList<Course> unplanned = plan.getCourses();
		
		//removes any planned courses(completed or future)
		for(Course course: plan.getCourses()){
			unplanned.remove(course);
		}
		
		return unplanned;
	}
	
	/**
	 * Return requirements needed to satisfy the plan.
	 * @return list of requirements
	 */
	public ArrayList<Requirement> getRequirements() {
		ArrayList<Requirement> requirements = new ArrayList<Requirement>();
		
		for (Track track : TrackList) {
			requirements.addAll(track.getRequirements());
		}
		
		return requirements;
	}
	
	/**
	 * Name: getCourseList()
	 * Precondition: Plan plan is initialized
	 * @return an ArrayList<Course> of ALL courses needed by the current plan
	 */
	public ArrayList<Course> getCourseList() {
		return plan.getCourses();
	}

	/**
	 * 
	 * @param CourseID
	 * @return
	 */
	public boolean meetsPrereqs(String CourseID, String season, int year){
		
		if(plan.meetsPrereqs(plan.getCourse(CourseID, season, year)).size() == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Name: getNeededCategories()
	 * Precondition: Plan plan is initialized
	 * @return returns an ArrayList<String> of unique Categories used by the current plan
	 */
	public ArrayList<String> getNeededCategories() {
		ArrayList<String> needed = new ArrayList<String>();
		
		for(Course course: plan.getCourses()){
			if(!needed.contains(course.getCategory()))
				needed.add(course.getCategory());
		}
		return needed;
	}	
	
	public static void main(String Args[]) {		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StartMenu inst = new StartMenu(new ProphetController());
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});	
	}

	
	/**
	 * Name: exportPlan()
	 * Precondition(s): Plan plan is initialized
	 * Postcondition(s): Exports the current plan to a CSV file on the users desktop.    
	 */
	public void exportPlan() {
		
		System.out.println(plan.getName());
		planDAO = new UserPlanDAO(plan.getName());
		planDAO.exportPlan(plan);
		
	}

}
