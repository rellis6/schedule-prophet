/**
 * File:    NonExistentCourseException.java
 * Project: Schedule Prophet
 * Author:  Mark Pallone
 * Date:    Apr 6, 2011
 * Section: M/W
 * Email:   markpa1@umbc.edu OR mark.c.pallone@gmail.com
 */
package model;

/**
 * @author g00gle
 *
 * Description: 
 *
 */
@SuppressWarnings("serial")
public class NonExistentCourseException extends Exception {

	/**
	 * Name:             NonExistentCourseException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 */
	public NonExistentCourseException() {
		super("Can't find course specified.");
	}

	/**
	 * Name:             NonExistentCourseException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 * @param message
	 */
	public NonExistentCourseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Name:             NonExistentCourseException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 * @param cause
	 */
	public NonExistentCourseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Name:             NonExistentCourseException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 * @param message
	 * @param cause
	 */
	public NonExistentCourseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
