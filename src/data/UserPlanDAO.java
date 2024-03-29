/**
 * File:    UserPlanDAO.java
 * Project: Schedule Prophet
 * Author:  g00gle
 * Date:    Mar 31, 2011
 * Section: M/W
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 */
package data;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;  //Will need to handle files in the DAO
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Course;
import model.Requirement;
import model.Semester;
import model.Track;
import model.Plan;

/*
+getPlan(planName : String) : Plan
+savePlan(plan : Plan) : void
+exportPlan(directory : String, plan : Plan) : String // <-- full path including filename
+getSavedPlans() : List<String>
+deletePlan(name : String) : void 
 */

/**
 * @author g00gle
 *
 * Description: 
 *
 */
public class UserPlanDAO {
	private String name;
	private Plan userPlan;
	private CoursePathDAO cpd;
	
	/**
	 * Create a UserPlanDAO.
	 * Precondition: String name is a valid plan file
	 * Postcondtion: UserPlanDAO and CoursePathDAO are initialized
	 * @param name Name of the user plan.
	 * @author g00gle
	 */	
	public UserPlanDAO(String name) {
		this.name = name;
		cpd = new CoursePathDAO();
		if (!isNewPlan(name)) loadPlan(); else userPlan = new Plan(new ArrayList<Track>());
	}
	
	private boolean isNewPlan(String planName) {
		String fileDir = System.getenv("APPDATA") + "\\prophet\\";
		String filePath = fileDir + planName;
		
		if (!planName.endsWith(".xml"))
			filePath = filePath + ".xml";
			
		System.out.println(filePath);
		File planFile = new File(filePath);
		File planFileDir = new File(fileDir);
		boolean isNew = false;
		if ((!planFileDir.exists())||(!planFile.exists())) {
			isNew = true;
			System.out.println("this is a new file");
		}
		return isNew;
	}
	
	/**
	 * Loads plan from file into userPlan member.
	 * Precondition: String name member is initizalized to valid value
	 * Postcondition: userPlan is loaded
	 * @author g00gle
	 */
	private void loadPlan() {
		try {
			ArrayList<Semester> planSemesters = new ArrayList<Semester>();
		
			String fileDir = System.getenv("APPDATA") + "\\prophet\\";
			String filePath = fileDir + this.name;
			
			if (!filePath.endsWith(".xml"))
				filePath = filePath + ".xml";
			
			File planFile = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document planDoc = builder.parse(planFile);
			
			Element rootElement = planDoc.getDocumentElement();
			userPlan = new Plan(cpd.getTrackCourses(rootElement.getAttribute("track")));
			userPlan.setName(rootElement.getAttribute("name"));
			NodeList courseList = rootElement.getChildNodes();
			
			// Iterate through list of courses
			for (int i = 0; i < courseList.getLength(); i++) {
				
				// Only parse through node if it is an Element
				if (courseList.item(i) instanceof Element) {
	
					// Cast the course node to an Element so we can 
					// get its name and ID, which are defined as attributes.
					Element course = (Element) courseList.item(i);
					String courseID = course.getAttribute("id");
					String season = course.getAttribute("season");
					String year = course.getAttribute("year");
					String completed = course.getAttribute("completed");
					String grade = course.getAttribute("grade");
					String notes = course.getAttribute("notes");
					System.out.println(completed);
					System.out.println(completed.getClass());
					Course formalCourse;
					if(cpd.getCourse(courseID)==null){
						formalCourse = new Course("Dummy", "E", 0, "Dummy", "Dummy", "Dummy");
					}
					else{
						formalCourse = new Course(cpd.getCourse(courseID));
					}
					formalCourse.setGrade(grade);
					formalCourse.setNotes(notes);
					
					if (planSemesters.size() == 0) {
						// Then create a new semester and add the course
						System.out.println("YEAR: " + year);
						//TODO year is an empty string, cannot be cast to an int
						
						Semester newSemester = new Semester(season, Integer.parseInt(year));
						System.out.println(completed);
						System.out.println(completed.equals("true"));
						System.out.println();
						if (completed.equals("true")) {
							newSemester.setCompleted(true);
						}
						newSemester.addCourse(formalCourse);
						planSemesters.add(newSemester);
					} else {
						Semester lastSemester = planSemesters.get(planSemesters.size()-1);
						if ((lastSemester.getSeason().equals(season)) && (lastSemester.getYear() == Integer.parseInt(year))) {
							// Then no new semester needs to be created, just
							// add this course to the current semester
							lastSemester.addCourse(formalCourse);
						} else {
							// Then create a new semester and add the course
							Semester newSemester = new Semester(season, Integer.parseInt(year));
							System.out.println(completed);
							System.out.println(completed.equals("true"));
							System.out.println();
							if (completed.equals("true")) {
								newSemester.setCompleted(true);
							}
							newSemester.addCourse(formalCourse);
							planSemesters.add(newSemester);
						}
					}
				}
			}
			// Now add each semester to the Plan
			for (int j = 0; j < planSemesters.size(); j++) {
				// Need to be able to do this
				userPlan.addSemester(planSemesters.get(j));
				if (planSemesters.get(j).isCompleted()) {
					userPlan.setSemesterCompleted(planSemesters.get(j).getSeason(),
							planSemesters.get(j).getYear(), planSemesters.get(j).isCompleted());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open and read a plan from file, return plan object.
	 * Precondition: userPlan has been loaded
	 * Postcondition: userPlan is returned
	 * @author g00gle
	 */	
	public Plan getPlan() {
		return userPlan;
	}
	
	/**
	 * Open and save a file with the Plan information.
	 * Precondition: Plan plan is valid
	 * Postcondition: Plan is saved in XML format in AppData/prophet directory
	 * @param planName Name of the user plan file.
	 * @author g00gle
	 */	
	public void savePlan(Plan plan) {
		
		String fileText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		// Before I can do this, there needs to be a way for
		// this class to access a list of semesters from the Plan
		// class, a way to get the list of tracks from Plan class,
		// and Plan class needs to have a String name member
		fileText = fileText + "<plan name=\"" + plan.getName();
		fileText = fileText + "\" track=\"" + cpd.getTrackName(plan.getTracks());
		fileText = fileText + "\">\n";
		ArrayList<Semester> semesters = plan.getSemesters();
		for (int i = 0; i < semesters.size(); i++) {
			fileText = fileText + semesterToXML(semesters.get(i));
		}
		fileText = fileText + "</plan>\n";
		String fileDir = System.getenv("APPDATA") + "\\prophet\\";
		String filePath = fileDir + plan.getName() + ".xml";
		File planFile = new File(filePath);
		File planFileDir = new File(fileDir);
		FileWriter fwriter = null;
		
		try {
			if (planFileDir.mkdir()) System.out.println("Prophet Directory Created");
			if (planFile.createNewFile()) System.out.println("Created new file");
			System.out.println("FILEPATH: " + filePath);
			fwriter = new FileWriter(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
        BufferedWriter out = new BufferedWriter(fwriter);
        try {
			out.write(fileText);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the list of all files in the prophet directory
	 * (located in the user's AppData folder). If no files
	 * exist, returns an empty list.
	 * Precondition: None
	 * Postcondition: Returns list of plans saved in user's AppData folder, if any
	 * @return The list of all files in the prophet directory
	 * @author g00gle
	 */
	public ArrayList<String> getPlanList() {
		ArrayList<String> planFiles = new ArrayList<String>();
		String prophetDirPath = System.getenv("APPDATA") + "\\prophet";
		File prophetDir = new File(prophetDirPath);
		String[] fileList = prophetDir.list();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].substring(fileList[i].length()-4).toUpperCase().equals(".xml".toUpperCase())) {
					System.out.println(fileList[i].substring(fileList[i].length()-4));
					planFiles.add(fileList[i].substring(0,fileList[i].length()-4));
				}
			}
		}
		return planFiles;
	}
	
	/**
	 * Creates a populated course tag to be written to xml plan file.
	 * Precondition: All params are valid and correct
	 * Postcondition: Returns course in XML format
	 * @param id The id of the course
	 * @param season The season the course was taken
	 * @param year The year the course was taken
	 * @param completed If the course has been completed or not
	 * @param grade The grade obtained in the course
	 * @param notes Any notes pertaining to the course
	 * @return The string containing the XML tag
	 * @author g00gle
	 */
	private String courseToXML(String id, String season, String year, boolean completed, String grade, String notes) {
		String str = "";
		str = str + "<course id=\"" + id; 
		str = str + "\" season=\"" + season;
		str = str + "\" year=\"" + year;
		str = str + "\" grade=\"" + grade;
		str = str + "\" completed=\"" + completed;
		str = str + "\" notes=\"" + notes;
		str = str + "\"/>\n";
		return str;
	}
	
	/**
	 * Creates a semester XML tag based on a semester object
	 * to be written to a plan file.
	 * Precondition: Semester s is valid
	 * Postcondition: Returns semester in XML format
	 * @param s The semester to be written
	 * @return The string containing the equivalent xml tag
	 * @author g00gle
	 */
	private String semesterToXML(Semester s) {
		String str = "";
		ArrayList<Course> courses = s.getClasses();
		for (int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
			str = str + courseToXML(c.getCourseID(), s.getSeason(), String.valueOf(s.getYear()), s.isCompleted(), c.getGrade(), c.getNotes());
		}
		return str;
	}
	
	/**
	 * Delete a plan by the name passed
	 * @param name Name of the plan to be deleted.
	 * @throws IllegalArgumentException This will be thrown if there is a problem deleteing the file.
	 * 
	 */
	public void deletePlan(String name) throws IllegalArgumentException {
		
		String fileName = System.getenv("APPDATA") + "\\prophet\\" + name;// + ".xml";
	    // A File object to represent the filename
	    File file = new File(fileName);

	    // Make sure the file or directory exists and isn't write protected
	    if (!file.exists())
	    	throw new IllegalArgumentException( "Delete: no such file or directory: " + fileName);

	    if (!file.canWrite())
	    	throw new IllegalArgumentException("Delete: write protected: " + fileName);

	    // If it is a directory, make sure it is empty
	    if (file.isDirectory()) {
	    	String[] files = file.list();
		    if (files.length > 0)
		    	throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
		}

		// Attempt to delete it
		boolean success = file.delete();

	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
	}

	/**
	 * Save a plan to a CSV file with the Plan information. File will be saved to the desktop as "PlanName.csv".
	 * ReUse code from regular saving to XML with format changes for CSV instead of XML.
	 * Precondtion: Plan plan is valid
	 * Postcondition: Plan is saved in CSV format on the Desktop
	 * @param planName Name of the user plan file.
	 * @author g00gle
	 * 
	 */	
	public void exportPlan(Plan plan)
	{
		String name = plan.getName();
		String csvFileName = System.getProperty("user.home") + "\\desktop\\" + name + ".csv";
        String csvFileText = "Plan Name =," + name + ",\n\n";
        
        csvFileText = "Course, Semester, Year, Completed, Grade, Notes\n\n";
        
        ArrayList<Semester> semesters = plan.getSemesters();
        for (int i = 0; i < semesters.size(); i++) {
            csvFileText = csvFileText + semesterToCSV(semesters.get(i));
        }
		
		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter(csvFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedWriter out = new BufferedWriter(fwriter);
        try {
			out.write(csvFileText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a semester XML tag based on a semester object
	 * to be written to a plan file.
	 * Precondition: Semester s is valide
	 * Postcondition: Returns string containing all courses from semester in CSV format
	 * @param s The semester to be written
	 * @return The string containing the equivalent csv tag
	 * @author g00gle
	 */
	private String semesterToCSV(Semester s) {
		String str = "";
	    ArrayList<Course> courses = s.getClasses();
	    for (int i = 0; i < courses.size(); i++) {
	    	Course c = courses.get(i);
	    	if (!c.getCourseID().equals("Dummy")) {
	    		str = str + courseToCSV(c.getCourseID(), s.getSeason(), String.valueOf(s.getYear()), s.isCompleted(), c.getGrade(), c.getNotes());
	    	}
	    }
	    str = str + "\n";
	    return str;
	}
	    
	    /**
		 * Creates a populated course tag to be written to CSV plan file.
		 * Precondition: All params are correct
		 * Postcondition: Returns string with all params separated in order by commas
		 * @param id The id of the course
		 * @param season The season the course was taken
		 * @param year The year the course was taken
		 * @param completed If the course has been completed or not
		 * @param grade The grade obtained in the course
		 * @param notes Any notes pertaining to the course
		 * @return The string containing the CSV tag
		 * @author g00gle
		 * 
		 */
	private String courseToCSV(String id, String season, String year, boolean completed, String grade, String notes) {
		String str = id + ", ";
		str = str + season + ", ";
		str = str + year + ", ";
		str = str + completed + ",";
		str = str + grade + ",";
		str = str + notes + ",";
		str = str + "\n";
		return str;
	}
}
