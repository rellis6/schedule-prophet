package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
 * File: CreateSemester.java
 * Project: schedule-prophet
 * @author g00gle
 * Date: 
 * Description: Prompts the user for the season and year of the first semester they add to their plan.
 */
public class CreateSemester extends javax.swing.JFrame implements ActionListener{
	private JLabel lblSeason;
	private JLabel lblName;
	private JComboBox cbxSeason;
	private JTextField txtYear;
	private JButton btnSave;
	private MaintainPlan GUI;
	private ProphetController controller;
	
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CreateSemester inst = new CreateSemester(new TestController(), new MaintainPlan(new TestController()));
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	/**
	 * Create a CreateSemester object.
	 * 
	 * @param controller the ProphetController object this object should use.
	 * @param GUI the MaintainPlan object that this object should return to.
	 */
	public CreateSemester(ProphetController controller, MaintainPlan GUI) {
		super();
		this.controller=controller;
		this.GUI=GUI;
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
			this.setTitle("");
			getContentPane().setLayout(null);
			{
				lblSeason = new JLabel();
				getContentPane().add(lblSeason);
				lblSeason.setText("Season:");
				lblSeason.setBounds(10, 11, 80, 20);
				lblSeason.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Year:");
				lblName.setBounds(10, 45, 80, 20);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				ComboBoxModel cbxGradeModel = 
					new DefaultComboBoxModel(
							new String[] { "Spring", "Summer", "Fall", "Winter"});
				cbxSeason = new JComboBox();
				getContentPane().add(cbxSeason);
				cbxSeason.setModel(cbxGradeModel);
				cbxSeason.setBounds(67, 13, 110, 20);
				cbxSeason.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				txtYear = new JTextField();
				getContentPane().add(txtYear);
				txtYear.setBounds(70, 46, 50, 20);
				txtYear.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				btnSave = new JButton();
				getContentPane().add(btnSave);
				btnSave.setText("Save");
				btnSave.setBounds(50, 80, 88, 23);
				btnSave.setFont(new java.awt.Font("Tahoma",0,14));
				btnSave.setActionCommand("No");
				btnSave.addActionListener(this);
			}
			pack();
			this.setSize(210, 155);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	/**
	 * Name: actionPerformed()
	 * Precondition(s): none
	 * PostCondition(s): Responds to whatever action was performed
	 */
	public void actionPerformed(ActionEvent e) {
		try{
			controller.addSemester((String) cbxSeason.getModel().getSelectedItem(), Integer.parseInt(txtYear.getText()));
			this.dispose();
			GUI.regenerateTrees();
		}
		catch(Exception f){}
	}

}
