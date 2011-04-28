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
	
	public TestController(){
		System.out.println("hello world");
	}
	
	
	public void initTrackList(String track){
		System.out.println("initTrackList");
		return;
	}
	
	public void newPlan(){
		System.out.println("newPlan");
		return;
	}
	
	//filename should be the name of the plan.  GUI currently has no way of knowing the actual file path.
	public void loadPlan(String filename){
		System.out.println("loadPlan");
		return;
	}
	
	public void addCourse(String courseID, String season, String comments, int year){
		System.out.println("addCourse");
		return;
	}	
	
	public boolean removeCourse(String courseID, String season, int year){
		System.out.println("removeCourse");
		return true;
	}

	public String[] getCourseInfo(String courseID, String season, int year){
		System.out.println("getCourseInfo");
		String[] array = {"A", "Boring notes for some mysterious class"};
		return array;
	}

	public void savePlan(String name){
		System.out.println("savePlan");
		return;
	}

	public void addSemester(String season, int year){
		System.out.println("addSemester");
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

	public ArrayList<String> getPlans() {
		System.out.println("getPlans");
		ArrayList<String> string1 = new ArrayList<String>();
		string1.add("Katherine's Plan 3");
		string1.add("Katherine's Hypothetical CS/IS Plan");
		string1.add("Katherine's Plan 2");
		string1.add("Katherine's Hypothetical IS Plan");
		string1.add("Katherine Miller's CS Plan");
		ArrayList<String> string2 = (ArrayList<String>) string1.clone();
		string2.remove(0);
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

	public ArrayList<Semester> getCompletedSemesters() {
		System.out.println("getCompletedSemesters");
		ArrayList<Semester> semesters=new ArrayList<Semester>();
		semesters.add(new Semester("Fall", 2009));
		semesters.add(new Semester("Spring", 2010));
		semesters.add(new Semester("Fall", 2010));
		return semesters;
	}

	public ArrayList<Course> getCourseList(String category) {
		if(category.equals("Fall 2009")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 201", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("MATH 151", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("ENGL 100", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Social Science", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Spring 2010")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 202", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 203", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("MATH 152", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("PHYS 121", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Arts/Humanities", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Fall 2010")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 313", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 341", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("MATH 221", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("PHYS 122", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Spring 2011")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 331", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 345", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("STAT 355", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("BIOL 100", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Lab Class", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Fall 2011")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 304", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 421", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 441", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Culture", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Social Sciences", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Spring 2012")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 411", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 461", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Writing Intensive", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Social Sciences", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Computer Science Core Courses")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 201", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 202", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 203", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 313", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 331", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 341", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 345", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 411", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 421", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 441", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Computer Science Upper Level Electives")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("CMSC 426", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 431", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 435", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("CMSC 445", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else if(category.equals("Other")){
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(new Course("Social Science", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Arts/Humanities", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("PHED", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Culture", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Lab Class", "Z", 1000000000, "class of awesome"));
			courses.add(new Course("Writing Intensive", "Z", 1000000000, "class of awesome"));
			return courses;
		}
		else{
			return null;
		}
	}

	public String[] getNeededCategories() {
		System.out.println("getNeededCategories");
		String[] categories={"Computer Science Core Courses", "Computer Science Upper Level Electives", "Other"};
		return categories;
	}

	public ArrayList<Semester> getFutureSemesters() {
		System.out.println("getFutureSemesters");
		ArrayList<Semester> semesters=new ArrayList<Semester>();
		semesters.add(new Semester("Spring", 2011));
		semesters.add(new Semester("Fall", 2011));
		semesters.add(new Semester("Spring", 2012));
		return semesters;
	}

}
