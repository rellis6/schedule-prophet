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
 * @author Mark Pallone
 *
 * Description: Every action that can be taken using the GUI is called as a method 
 * in the ProphetController class.
 */
public class ProphetController {
	Plan plan;
	ArrayList<Track> TrackList = new ArrayList<Track>();
	CoursePathDAO courseDAO = new CoursePathDAO();
	UserPlanDAO planDAO;
	
	/**
	 * We shouldn't need these two since adding a completed course is exactly the same as adding an uncompleted
	 * course except the semester it's added to is marked as completed
	void addCompletedCourse(Course course){	
	}	
	void editCompletedCourse(String courseID, String season, int year, String grade, String comments){
	}
	**/
	
	
	//how does TrackList get populated? Does clicking a button on the gui pass a designated
	//string with the track name to PC, PC checks it's valid, then adds?
	
	//only called for creating new plans
	private void initTrackList(String track){
		System.out.println("init: " + track);
		
		this.TrackList = courseDAO.getTrackCourses(track);
	}
	
	public void newPlan(String track){
		initTrackList(track);
		this.plan = new Plan(TrackList);
	}
	
	public void newPlan(String track, String name){
		System.out.println("new: " + track);
		initTrackList(track);
		this.plan = new Plan(TrackList, name);
	}
	
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
			plan.addCourse(courseDAO.getCourse(courseID), season, year);
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

	/**
	 * Name: 
	 * Precondition(s): 
	 * Postcondition(s): 
	 * @param args                    
	 */		
	public void savePlan(){
		planDAO.savePlan(plan);
	}

	/**
	 *  Adds a new semester
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
	
	public void deletePlan(String plan){
		planDAO.deletePlan(plan);
	}
	
//	public ArrayList<String> getPlans(){
//		return planDAO.getPlanList();
//	}
	
	/**
	 * Gets the list of all files in the prophet directory
	 * (located in the user's AppData folder). If no files
	 * exist, returns an empty list.
	 * @return The list of all files in the prophet directory
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
	 * @return
	 */
	public ArrayList<Semester> getCompletedSemesters() {
		return plan.getSemesters(true);
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Semester> getFutureSemesters() {
		return plan.getSemesters(false);
	}	
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public ArrayList<Course> getCourseList(String string) {
		return plan.getCourses();
	}

	/**
	 * 
	 * @return
	 */
	public String[] getNeededCategories() {
		return null;
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

}
