package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
public class AddCourseToPlan extends javax.swing.JFrame implements ActionListener{
	private JLabel lblName;
	private JComboBox cbxName;
	private JLabel lblSemester;
	private JButton cmdSave;
	private JTextArea txtComments;
	private JLabel lblComments;
	private JComboBox cbxGrade;
	private JLabel lblGrade;
	private JComboBox cbxSemester;
	private JScrollPane scrollpaneComments;
	private ProphetController controller;
	//private TestController controller;
	
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
				AddCourseToPlan inst = new AddCourseToPlan(new TestController());
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public AddCourseToPlan(ProphetController controller) {
		super();
		this.controller=controller;
		//this.controller=(TestController) controller;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Edit Course");
			getContentPane().setLayout(null);
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Name");
				lblName.setBounds(10, 11, 40, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				ComboBoxModel cbxNameModel = 
					new DefaultComboBoxModel(
							new String[] { "CMSC 202", "CMSC 203" });
				cbxName = new JComboBox();
				getContentPane().add(cbxName);
				cbxName.setModel(cbxNameModel);
				cbxName.setBounds(115, 11, 149, 20);
				cbxName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblSemester = new JLabel();
				getContentPane().add(lblSemester);
				lblSemester.setText("Semester");
				lblSemester.setBounds(10, 39, 105, 14);
				lblSemester.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				ComboBoxModel cbxSemesterModel = 
					new DefaultComboBoxModel(
							new String[] { "Fall 2009", "Spring 2010 " });
				cbxSemester = new JComboBox();
				getContentPane().add(cbxSemester);
				cbxSemester.setModel(cbxSemesterModel);
				cbxSemester.setBounds(115, 38, 149, 20);
				cbxSemester.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblGrade = new JLabel();
				getContentPane().add(lblGrade);
				lblGrade.setText("Grade");
				lblGrade.setBounds(10, 64, 63, 14);
				lblGrade.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				ComboBoxModel cbxGradeModel = 
					new DefaultComboBoxModel(
							new String[] { "A", "B" });
				cbxGrade = new JComboBox();
				getContentPane().add(cbxGrade);
				cbxGrade.setModel(cbxGradeModel);
				cbxGrade.setBounds(115, 63, 65, 20);
				cbxGrade.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblComments = new JLabel();
				getContentPane().add(lblComments);
				lblComments.setText("Comments");
				lblComments.setBounds(10, 89, 100, 14);
				lblComments.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				txtComments = new JTextArea();
				scrollpaneComments = new JScrollPane(txtComments);
				getContentPane().add(scrollpaneComments);
				scrollpaneComments.setBounds(114, 94, 230, 119);

			}
			{
				cmdSave = new JButton();
				getContentPane().add(cmdSave);
				cmdSave.setText("Save");
				cmdSave.setBounds(124, 228, 88, 23);
				cmdSave.setFont(new java.awt.Font("Tahoma",0,14));
				cmdSave.addActionListener(this);
			}
			pack();
			this.setSize(370, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
