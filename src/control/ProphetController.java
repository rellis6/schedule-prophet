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

import java.util.ArrayList;

import model.*;
import data.*;

/**
 * @author Mark Pallone
 *
 * Description: 
 *
 */
public class ProphetController {
	Plan plan;
	ArrayList<Track> TrackList = new ArrayList<Track>();
	CoursePathDAO courseDAO;
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
	
	
	public void initTrackList(String track){
		this.TrackList = courseDAO.getTrackCourses(track);
	}
	
	public void newPlan(){
		this.plan = new Plan(TrackList);
	}
	
	public void loadPlan(String filename){
		planDAO = new UserPlanDAO(filename);
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
	

	public static void main(String Args[]) {
		System.out.println("Hello, world!");
	}

}
