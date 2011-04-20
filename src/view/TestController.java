package view;

import java.util.ArrayList;

import control.*;
import model.*;
import data.*;

public class TestController extends ProphetController{
	Plan plan;
	ArrayList<Track> TrackList = new ArrayList<Track>();
	CoursePathDAO courseDAO;
	UserPlanDAO planDAO;
	boolean delete=false;
	
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
		return;
	}
	
	public void newPlan(){
		return;
	}
	
	//filename should be the name of the plan.  GUI currently has no way of knowing the actual file path.
	public void loadPlan(String filename){
		return;
	}
	
	public void addCourse(String courseID, String season, String comments, int year){
		return;
	}	
	
	public boolean removeCourse(String courseID, String season, int year){
		return true;
	}

	public String[] getCourseInfo(String courseID, String season, int year){
		String[] array = {"A", "Boring notes for some mysterious class"};
		return array;
	}

	public void savePlan(String name){
		return;
	}

	public void addSemester(String season, int year){
		return;
	}
	
	public static void main(String Args[]) {
		System.out.println("Hello, world!");
	}
	
	
	//New methods start here
	public void createPlan(String track, String name){
		System.out.println("creating plan "+name+" in track "+track);
		return;
	}

	public String[][] getPlans() {
		String[][] string1 = { { "Katherine's Plan 3", "09/03/2010"},
		 		 { "Katherine's Hypothetical CS/IS Plan", "8/14/2010"},
				 { "Katherine's Plan 2", "07/21/2010"},
				 { "Katherine's Hypothetical IS Plan", "10/05/2008"},
				 { "Katherine Miller's CS Plan", "08/28/2007"}};
		String[][] string2 = { { "Katherine's Hypothetical CS/IS Plan", "8/14/2010"},
					 { "Katherine's Plan 2", "07/21/2010"},
					 { "Katherine's Hypothetical IS Plan", "10/05/2008"},
					 { "Katherine Miller's CS Plan", "08/28/2007"}};
		if(delete){
			return string2;
		}
		else{
			return string1;
		}
	}

	public void deletePlan(String name) {
		System.out.println("deleting: "+name);
		delete=true;
		return;
	}

	public String[] getCompletedSemesters() {
		String[] semesters={"Fall 2009", "Spring 2010", "Fall 2010"};
		return semesters;
	}

	public String[] getCourseList(String category) {
		if(category.equals("Fall 2009")){
			String[] courses={"CMSC 201", "MATH 151", "ENGL 100", "Social Science"};
			return courses;
		}
		else if(category.equals("Spring 2010")){
			String[] courses={"CMSC 202", "CMSC 203", "MATH 152", "PHYS 121", "Arts/Humanities"};
			return courses;
		}
		else if(category.equals("Fall 2010")){
			String[] courses={"CMSC 313", "CMSC 341", "MATH 221", "PHYS 122"};
			return courses;
		}
		else if(category.equals("Spring 2011")){
			String[] courses={"CMSC 331", "CMSC 345", "STAT 355", "BIOL 100", "Lab Class"};
			return courses;
		}
		else if(category.equals("Fall 2011")){
			String[] courses={"CMSC 304", "CMSC 421", "CMSC 441", "Culture", "Social Sciences"};
			return courses;
		}
		else if(category.equals("Spring 2012")){
			String[] courses={"CMSC 411", "CMSC 461", "Writing Intensive", "Social Sciences"};
			return courses;
		}
		else if(category.equals("Computer Science Core Courses")){
			String[] courses={"CMSC 201", "CMSC 202", "CMSC 203", "CMSC 313", "CMSC 331", "CMSC 341", "CMSC 345", "CMSC 411", "CMSC 421", "CMSC 441"};
			return courses;
		}
		else if(category.equals("Computer Science Upper Level Electives")){
			String[] courses={"CMSC 426", "CMSC 431", "CMSC 435", "CMSC 445"};
			return courses;
		}
		else if(category.equals("Other")){
			String[] courses={"Social Science", "Arts/Humanities", "PHED", "Culture", "Lab Class", "Writing Intensive"};
			return courses;
		}
		else{
			return null;
		}
	}

	public String[] getNeededCategories() {
		String[] categories={"Computer Science Core Courses", "Computer Science Upper Level Electives", "Other"};
		return categories;
	}

	public String[] getFutureSemesters() {
		String[] semesters={"Spring 2011", "Fall 2011", "Spring 2012"};
		return semesters;
	}

}
