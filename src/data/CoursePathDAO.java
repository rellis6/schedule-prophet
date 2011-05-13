/**
 * File:    CoursePathDAO.java
 * Project: 
 * Author:  Katherine Miller, Mark Pallone
 * Date:    Mar 31, 2011
 * Section: 
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 * Class Invariant(s):
 */
package data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.AbsoluteRequirement;
import model.Course;
import model.FlexibleRequirement;
import model.FlexibleRequirementSet;
import model.Requirement;
import model.Track;


/**
 * @author Katherine Miller, Mark Pallone
 *
 * Description: This class handles importing the course and track data stored
 * in the filesystem into course and track objects.
 */
public class CoursePathDAO {
	private boolean PRINT=false;
	 
	private ArrayList<Course> masterCourseList;
	
	/**
	 * Default constructor that populates the master course list.
	 * @author Katherine Miller
	 */
	public CoursePathDAO () {
		// First we initialize the master list of courses.
		initializeMasterCourseList();
	}
	
	/**
	 * Parses the master course list XML file and imports the
	 * data into an ArrayList of courses.
	 * @author Katherine Miller
	 */
	private void initializeMasterCourseList() {
		
		// First, initialize the master ArrayList of all courses.
		masterCourseList = new ArrayList<Course>();
		
		try {
			// Open the master course list XML file, and create new 
			// instances of DocumentBuilderFactory and DocumentBuilder
			// that we will use to parse the XML into a DOM.
			File courseListFile = new File("src/xml/master_course_list.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document courseListDocument = builder.parse(courseListFile);
			
			// Get the list of children (all courses) from the root node.
			Element rootElement = courseListDocument.getDocumentElement();
			NodeList courseList = rootElement.getChildNodes();
			
			// Iterate through the list of all child nodes (courses).
			for (int i = 0; i < courseList.getLength(); i++) {
				
				// Only parse through node if it is an Element
				if (courseList.item(i) instanceof Element) {
					
					// Initialize a few variables
					String courseID = "";
					String courseName = "";
					String courseDescription = "";
					ArrayList<Requirement> prerequisiteList = new ArrayList<Requirement>();
					int credits = 0;
					
					// Cast the course node to an Element so we can 
					// get its name and ID, which are defined as attributes.
					Element course = (Element) courseList.item(i);
					courseName = course.getAttribute("name");
					courseID = course.getAttribute("id");
					// Right now, not all courses in the XML file have a credits attribute
					//credits = Integer.parseInt(course.getAttribute("credits"));
					
					// Course description and prerequisite lists are defined
					// as children of a course (surrounded by tags inside of
					// the course tags), so get the list of child nodes in
					// order to access these parameters.
					NodeList courseChildren = course.getChildNodes();
					int length = courseChildren.getLength();
					
					// Iterate through the children; only process those that 
					// are instances of the Element class (cast them first)
					for (int h = 0; h < length; h++) {
						if (courseChildren.item(h) instanceof Element) {
							Element courseChild = (Element) courseChildren.item(h);
							
							// Now, just in case the tags are out of order, 
							// we will test for equality with the two possibilities
							// that we expect and process accordingly
							if (courseChild.getNodeName().equals("description")) {
								// If this child is a description tag, we can simply get the
								// text inside the tag and assign to the desciption variable.
								courseDescription = courseChild.getTextContent();
							} else if (courseChild.getNodeName().equals("prerequisites")) {
								// Prerequisites are a little more complicated. They are given
								// as separate tags inside of a "prerequisites" tag, so first 
								// we need to grab the list of child nodes and iterate through it.
								NodeList prerequisites = courseChild.getChildNodes();
								for (int g = 0; g < prerequisites.getLength(); g++) {
									// Again, only process nodes that are actually Elements.
									if (prerequisites.item(g) instanceof Element) {
										// Cast node to Element and make sure it is a "prerequisite" tag.
										Element prereq = (Element) prerequisites.item(g);
										if (prereq.getNodeName().equals("prerequisite")) {
											// The text content of a prerequisite tag may simply be a
											// course ID, but if there are several equivalent courses that
											// each may satisfy the prereq, and/or certain minimum grades
											// are involved, then text may need a bit more parsing.
											Requirement req = parsePrerequisite(prereq.getTextContent());
											// After the prerequisite text has been parsed into a requirement,
											// add it to the list of requirements.
											prerequisiteList.add(req);
										}
									}
								}
							}
						}
					}
					// Create a new instance of course with all the information we just parsed...
					Course c = new Course(courseID, "", "", "", credits, courseName, "", courseDescription);
					// ...set the list of prerequisites...
					c.setPreReqs(prerequisiteList);
					// ... and add it to the master course list.
					masterCourseList.add(c);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns an ArrayList<Track> object containing the pertaining track combos
	 * @param name Name of specified Track
	 * @author Katherine Miller
	 */
	public ArrayList<Track> getTrackCourses(String name){
		if(PRINT){
			System.out.println(name);
		}
		if(!getMasterTrackList().contains(name))
			return null;
			
		ArrayList<Track> track = new ArrayList<Track>();
		
		track.add(getTrack("General Education Program"));
		if(name.equals("Computer Science Major")){
			track.add(getTrack("Computer Science Major"));
		} else if(name.equals("Information Systems Major")){
			track.add(getTrack("Information Systems Major"));
		} else if(name.equals("Information Systems Major with Computer Science Minor")){
			track.add(getTrack("Information Systems Major"));
			track.add(getTrack("Computer Science Minor"));
		} else if(name.equals("Computer Science and Information Systems Double Major")){
			track.add(getTrack("Computer Science Major"));
			track.add(getTrack("Information Systems Major"));
		}
		
		return track;
			//should Track cons take in a string and create a Track obj 
			//by parsing through the .xml?
		
	}
	
	/**
	 * Gets the name of a major based on the list of tracks--
	 * For example, if a student has both the cs major and is 
	 * major in his track list, this function will return
	 * "Computer Science and Information Systems Double Major"
	 * @param tracks ArrayList of tracks 
	 * @return The string representing the major
	 * @author Katherine Miller
	 */
	public String getTrackName(ArrayList<Track> tracks) {
		String trackName = "";
		boolean csmajor = false;
		boolean csminor = false;
		boolean ismajor = false;
		for (int i = 0; i < tracks.size(); i++) {
			// Track needs a name property with setters and getters
			// in order to make this possible.
			String name = tracks.get(i).getName();
			if (name.equals("Computer Science Major")) {
				System.out.println("cs major is true!");
				csmajor = true;
			} else if (name.equals("Computer Science Minor")) {
				csminor = true;
			} else if (name.equals("Information Systems Major")) {
				ismajor = true;
			}
		}
		
		if (ismajor && csmajor) {
			trackName = "Computer Science and Information Systems Double Major";
		} else if (ismajor && csminor) {
			trackName = "Information Systems Major with Computer Science Minor";
		} else if (ismajor) {
			trackName = "Information Systems Major";
		} else if (csmajor) {
			trackName = "Computer Science Major";
		}
		return trackName;
	}
	
	/**
	 * Gets a course using the courseID from the master course list
	 * @param courseID The course's ID
	 * @return The course object corresponding to the ID
	 * @author Katherine Miller
	 */
	public Course getCourse(String courseID){
		Course c = null;
		for (int i=0; i < masterCourseList.size(); i++) {
			if (masterCourseList.get(i).getCourseID().equals(courseID)) {
				c = masterCourseList.get(i);
			}
		}
		return c;
	}
	
	/**
	 * 
	 * @param track The name of the track we'd like to get
	 * @return A track object containing all the requirements of the track requested
	 * @author Katherine Miller
	 */
	private Track getTrack(String track){
		File trackFile = null;
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		org.w3c.dom.Document trackDoc;
		ArrayList<Requirement> trackReqs = new ArrayList<Requirement>();
		try {
			if (track.equals("Computer Science Major")) {
				// Open the cs major track XML file
				trackFile = new File("src/xml/cs_major_reqs.xml");
			} else if (track.equals("Information Systems Major")) {
				// Open the is major track XML file
				trackFile = new File("src/xml/is_major_reqs.xml");
			} else if (track.equals("Computer Science Minor")) {
				// Open the cs minor track XML file
				trackFile = new File("src/xml/cs_minor_reqs.xml");
			} else if (track.equals("General Education Program")) {
				// Open the gep track XML file
				trackFile = new File("src/xml/gep_reqs.xml");
			}
			// Create new instances of DocumentBuilder, DocumentBuilderFactory,
			// and create the DOM out of the selected trackfile
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			trackDoc = builder.parse(trackFile);
			
			// Get the list of children (all requirements) from the root node.
			Element rootElement = trackDoc.getDocumentElement();
			NodeList reqList = rootElement.getChildNodes();
			
			// Iterate through all children (base reqs)
			for (int i = 0; i < reqList.getLength(); i++) {
				
				// Only parse through node if it is an Element
				if (reqList.item(i) instanceof Element) {
					
					// Initialize a few variables
					String reqName = "";
					String reqType = "";
					int numToTake = 0;
					int numCredits = 0;
					ArrayList<Course> courseList = new ArrayList<Course>();
					int credits = 0;
					
					// Cast the course node to an Element so we can 
					// get its name (an attribute) and type
					Element req = (Element) reqList.item(i);
					reqName = req.getAttribute("name");
					reqType = req.getNodeName();
					
					// Courses are defined as children of a requirement
					NodeList courses = req.getChildNodes();
					int length = courses.getLength();
					
					// Iterate through the children; only process those that 
					// are instances of the Element class (cast them first)
					for (int h = 0; h < length; h++) {
						if (courses.item(h) instanceof Element) {
							Element course = (Element) courses.item(h);
							
							// Make sure child is a course
							if (course.getNodeName().equals("course")) {
								// Get course ID and minimum grade
								String courseID = course.getAttribute("id");
								String courseGrade = course.getAttribute("grade");
								// Add course populated with these attributes
								// to the list of courses for the requirement

								courseList.add(populateTrackCourse(courseID, courseGrade));
							} 
						}
					}
					// Now, get the parameters that depend on requirement type.
					if (reqType.equals("absolutereq")) {
						numCredits = 0 /*Integer.parseInt(req.getAttribute("credits"))*/;
						trackReqs.add(new AbsoluteRequirement(courseList, numCredits));
						
						//TODO remove
						if(PRINT){
							System.out.println("From CoursePathDAO; just added absolutereq: ");
							System.out.println(trackReqs.get(trackReqs.size() - 1));
						}
					} else if (reqType.equals("flexiblereq")) {
						numToTake = Integer.parseInt(req.getAttribute("number"));
						numCredits = 0 /*Integer.parseInt(req.getAttribute("credits"))*/;
						trackReqs.add(new FlexibleRequirement(courseList, numToTake, numCredits));
						
						//TODO remove
						if(PRINT){
							System.out.println("From CoursePathDAO; just added flexiblereq:");
							System.out.println(trackReqs.get(trackReqs.size() - 1));
						}
					} else if (reqType.equals("flexiblereqset")) {
						
						//TODO This isn't working; absReqs is getting set to null.
						if (true) {
							continue;	
						}
						
						ArrayList<AbsoluteRequirement> absReqList = new ArrayList<AbsoluteRequirement>();
						NodeList absReqs = req.getChildNodes();
						if(PRINT){
							System.out.println("absReqs (from flexiblereqset search in CoursePathDAO: " + absReqs);
						}
						// Now, we have to iterate through a whole other list of
						// child nodes, to construct the flexible requirement 
						// set's list of absolute requirements.
						for (int g = 0; g < absReqs.getLength(); g++) {
							if (absReqs.item(g) instanceof Element) {
								Element absReq = (Element) absReqs.item(g);
								
								// Make sure child is an absolute requirement
								if (absReq.getNodeName().equals("absolutereq")) {
									String aReqName = absReq.getAttribute("name");
									String aReqType = absReq.getNodeName();
									int arCredits = 0 /*Integer.parseInt(absReq.getAttribute("credits"))*/;
									ArrayList<Course> arCourseList = new ArrayList<Course>();
									
									// Courses are defined as children of a requirement
									NodeList arCourses = absReq.getChildNodes();
									
									// Iterate through the children; only process those that 
									// are instances of the Element class (cast them first)
									for (int f = 0; f < arCourses.getLength(); f++) {
										if (arCourses.item(f) instanceof Element) {
											Element arCourse = (Element) arCourses.item(f);
											
											// Make sure child is a course
											if (arCourse.getNodeName().equals("course")) {
												// Get course ID and minimum grade
												String arCourseID = arCourse.getAttribute("id");
												String arCourseGrade = arCourse.getAttribute("grade");
												// Add course populated with these attributes
												// to the list of courses for the requirement
												arCourseList.add(populateTrackCourse(arCourseID, arCourseGrade));
											} 
										}
									}
									// Finally, add to the list of absolute requirements
									absReqList.add(new AbsoluteRequirement(arCourseList, arCredits));
									
								} 
							}
						}
						// Get last parameters for flexible requirement set and add to list of track requirements
						numToTake = 0 /*Integer.parseInt(req.getAttribute("number"))*/;
						numCredits = 0 /*Integer.parseInt(req.getAttribute("credits"))*/;
						
						
						trackReqs.add(new FlexibleRequirementSet(absReqList, numToTake, numCredits));
						if(PRINT){
							System.out.println("From CoursePathDAO; just added flexiblereqset:");
							System.out.println(trackReqs.get(trackReqs.size() - 1));
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Finally, create and return track
		Track newTrack = new Track(trackReqs);
		newTrack.setName(track);
		return newTrack;
	}
	
	/**
	 * returns a list of valid String options for Tracks
	 */
	public List<String> getMasterTrackList(){
		final List<String> MTList =
	        Arrays.asList("Computer Science Major", "Information Systems Major", 
	        		"Information Systems Major with Computer Science Minor", 
	        		"Computer Science and Information Systems Double Major");
		return MTList;
	}
	
	/**
	 * 
	 * @param id The ID for the course we want to populate
	 * @param grade Minimum grade required by the track
	 * @return The course object as it appears in the master course list, with the minimum grade parameter added
	 * @author Katherine Miller
	 */
	private Course populateTrackCourse(String id, String grade) {
		Course course = null;
		Course c = getCourse(id); //what is this line used for? Should this method return c?
								  // c is the course in the catalog, course is the course in a track.
							      // They will have different information according to major. This
								  // should not return c because catalog courses' don't have minimum grades
								  // associated with them, and shouldn't, because that is major-specific.
								  // In short, this method references the information in the catalog that
								  // isn't in the major files (like description, name, etc) and fills
								  // in the track-specific info. --Katherine
		//System.out.println(c);
		//System.out.println(id);
		course = new Course(id, "", "", grade, 0/*c.getCredits()*/, c.getCourseTitle(), c.getCategory(), c.getDescription());

		return course;
	}
	
	/**
	 * 
	 * @param prereq A string in the format "courseID(grade)|courseID(grade)|...|courseID(grade)"
	 * @return A FlexibleRequirement containing the list of courses described with numToTake set to one
	 * @author Katherine Miller
	 */
	private FlexibleRequirement parsePrerequisite(String prereq) {
		FlexibleRequirement prerequisite;
		ArrayList<Course> courses = new ArrayList<Course>();
		String prereqList[] = prereq.split("\\|");
		for (int i = 0; i < prereqList.length; i++) {
			String prereqParts[] = prereqList[i].split("\\(");
			String courseID = prereqParts[0];
			String minGrade = "F";
			if (prereqParts.length > 1) {
				minGrade = prereqParts[1].split("\\)")[0];
			}
			Course course = new Course(courseID, minGrade, "", "", 0, "COURSE_TITLE", "COURSE_CATEGORY", "COURSE_DESCRIPTION"/*,""*/);
			courses.add(course);
		}
		prerequisite = new FlexibleRequirement(courses, 1, 1);
		return prerequisite;
	}

}
