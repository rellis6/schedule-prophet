/**
 * File:    CoursePathDAO.java
 * Project: 
 * Author:  Mark Pallone
 * Date:    Mar 31, 2011
 * Section: 
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 * Class Invariant(s):
 */
package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Course;
import model.Track;


/**
 * @author Mark Pallone
 *
 * Description: 
 *
 */
public class CoursePathDAO {
	
	/**
	 * returns an ArrayList<Track> object containing the pertaining track combos
	 * @param name Name of specified Track
	 */
	public ArrayList<Track> getTrackCourses(String name){
		if(!getMasterTrackList().contains(name))
			return null;
			
		ArrayList<Track> track = new ArrayList<Track>();
		
		if(name.equals("Computer Science Major")){
			track.add(getTrack("Computer Science Major"));
		} else if(name.equals("Information Systems Major")){
			track.add(getTrack("Information Systems Major"));
		} else if(name.equals("Information Systems Major with Computer Science Minor")){
			track.add(getTrack("Information Systems Major with Computer Science Minor"));
		} else if(name.equals("Computer Science and Information Systems Double Major")){
			track.add(getTrack("Computer Science and Information Systems Double Major"));
		}
		
		return track;
			//should Track cons take in a string and create a Track obj 
			//by parsing through the .xml?
		
	}
	
	public Course getCourse(String CourseID, ArrayList<Track> track){
		
		return null;
	}
	
	//parses through the major_list.xml file to find its respective track files,
	//then 
	private Track getTrack(String track){
		return null;
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
}
