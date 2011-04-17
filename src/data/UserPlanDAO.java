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
import java.io.File;  //Will need to handle files in the DAO
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
	
	/**
	 * Create a UserPlanDAO.
	 * @param name Name of the user plan.
	 */	
	public UserPlanDAO(String name) {
		this.name = name;
	}
	
	/**
	 * Open and read a plan from file, return plan object.
	 * @param planName Name of the user plan file.
	 */	
	public Plan getPlan(String planName) {
		return null;
	}
	
	/**
	 * Open and save a file with the Plan information.
	 * @param planName Name of the user plan file.
	 */	
	public void savePlan(Plan plan) {
		
	}
	
	/**
	 * Delete a plan by the name passed
	 * @param name Name of the plan to be deleted.
	 * @throws IllegalArgumentException This will be thrown if there is a problem deleteing the file.
	 * 
	 */
	public void deletePlan(String name) throws IllegalArgumentException {
		
		//This will need to be updated with a path of where we are planning to store the plans
		//along with a corrected filename. 
	    String fileName = "FILL IN FILE PATH/NAME HERE";
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

}
