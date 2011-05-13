package view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import control.ProphetController;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

/**
 * File: StartMenu.java
 * Project: schedule-prophet
 * @author g00gle
 * Date: 
 * Description: The first window the user sees.  Allows the user to choose between creating a new plan and loading a plan.
 */
public class StartMenu extends javax.swing.JFrame implements ActionListener{
	private JLabel lblSelectOption;
	private JButton cmdLoadPlan;
	private JButton cmdCreatePlan;
	private ProphetController controller;
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StartMenu inst = new StartMenu(new TestController());
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public StartMenu(ProphetController controller) {
		super();
		this.controller=controller;
		initGUI();
	}
	
	/**
	 * Name: initGUI()
	 * Precondition(s): none
	 * PostCondition(s): Initializes the window.
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("UMBC CS/IS Schedule Prophet");
			{
				lblSelectOption = new JLabel();
				getContentPane().add(lblSelectOption, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblSelectOption.setText("Select an option:");
				lblSelectOption.setBounds(14, 24, 116, 20);
				lblSelectOption.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				cmdCreatePlan = new JButton();
				getContentPane().add(cmdCreatePlan, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				cmdCreatePlan.setText("Create New Plan");
				cmdCreatePlan.setBounds(90, 55, 151, 36);
				cmdCreatePlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdCreatePlan.setActionCommand("New");
				cmdCreatePlan.addActionListener(this);
			}
			{
				cmdLoadPlan = new JButton();
				getContentPane().add(cmdLoadPlan, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				cmdLoadPlan.setText("Load Existing Plan");
				cmdLoadPlan.setBounds(90, 102, 151, 43);
				cmdLoadPlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdLoadPlan.setActionCommand("Existing");
				cmdLoadPlan.addActionListener(this);
			}
			pack();
			this.setSize(334, 218);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New")){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					SelectTrack inst = new SelectTrack(controller);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
		else if(e.getActionCommand().equals("Existing")){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					LoadPlan inst = new LoadPlan(controller);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
	}

}
