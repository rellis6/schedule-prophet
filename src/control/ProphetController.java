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
	 * 
	 * @param track
	 */
	private void initTrackList(String track){
		System.out.println("init: " + track);
		
		this.TrackList = courseDAO.getTrackCourses(track);
	}
	
	/**
	 * 
	 * @param track
	 */
	public void newPlan(String track){
		initTrackList(track);
		this.plan = new Plan(TrackList);
	}
	
	/**
	 * 
	 * @param track
	 * @param name
	 */
	public void newPlan(String track, String name){
		System.out.println("new: " + track);
		initTrackList(track);
		this.plan = new Plan(TrackList, name);
	}
	
	/**
	 * N
	 * @param filename
	 */
	public void loadPlan(String filename){
		planDAO = new UserPlanDAO(filename);
		plan = planDAO.getPlan();
	}
	
	/**
	 * Name: 
	 * Precondition(s): 
	 * Postcondition(s): returns array which contains grade + comments
	 * @param args                    
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
	 * Name: 
	 * Precondition(s): 
	 * Postcondition(s): returns True if other courses exist in semester
	 * @param args                    
	 */		
	public boolean removeCourse(String courseID, String season, int year){
		
		try {
			plan.removeCourse(courseID, season, year);
		} catch (NonExistentCourseException e) {
			System.out.print(e.getMessage());
			//warning/error window?
		}
		
		//not sure what return should be; boolean t/f if any other courses exist at all?
		return true;
	}

	/**
	 * Name: 
	 * Precondition(s): 
	 * Postcondition(s): returns array containing grade + comments
	 * @param args                    
	 */		
	public String[] getCourseInfo(String courseID, String season, int year){
		String[] array = {plan.getCourse(courseID, season, year).getGrade(), 
				plan.getCourse(courseID, season, year).getNotes()};
		return array;
	}
	
	public void editCourse(String season, int year, String courseID, String grade, String notes){
		try {
			plan.editCompletedCourse(season, year, courseID, grade, notes);
		} catch (NonExistentSemesterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonExistentCourseException e) {
			// TODO Auto-generated catch block
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
		System.out.println(plan.getName());
		
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
			//error window
		}
		else{
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
	 * 
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
	 * 
	 * @return an arraylist of all completed semesters
	 */
	public ArrayList<Semester> getCompletedSemesters() {
		for(Semester cur: plan.getSemesters(true))
			System.out.println(cur.getSeason() + cur.getYear());
		return plan.getSemesters(true);
	}

	/**
	 * 
	 * @return an arraylist of all uncompleted semesters
	 */
	public ArrayList<Semester> getFutureSemesters() {
		return plan.getSemesters(false);
	}	
	
	/**
	 * 
	 * @return an arraylist of future courses
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
	 * 
	 * @return
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
	 * 
	 * @return an ArrayList<Course> of ALL courses needed by the current plan
	 */
	public ArrayList<Course> getCourseList() {
		return plan.getCourses();
	}

	/**
	 * 
	 * @return returns an ArrayList<String> of unique Categories used by the current plan
	 */
	public ArrayList<String> getNeededCategories() {
		ArrayList<String> needed = new ArrayList<String>();
		
		for(Course course: plan.getCourses()){
			if(needed.contains(course.getCategory()))
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
	 * not currently implemented
	 */
	public void exportPlan() {
		// TODO Auto-generated method stub
		
	}

}
