/**
 * File:    NonExistentSemesterException.java
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
public class NonExistentSemesterException extends Exception {

	/**
	 * Name:             NonExistentSemesterException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 */
	public NonExistentSemesterException() {
		super("Can't find semester specified.");
	}

	/**
	 * Name:             NonExistentSemesterException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 * @param message
	 */
	public NonExistentSemesterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Name:             NonExistentSemesterException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 * @param cause
	 */
	public NonExistentSemesterException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Name:             NonExistentSemesterException - create an instant of this class<br \>
	 * Precondition(s):  None<br \>
	 * Postcondition(s): None <br \>
	 * @param message
	 * @param cause
	 */
	public NonExistentSemesterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
