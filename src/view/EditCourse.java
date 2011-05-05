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
public class EditCourse extends javax.swing.JFrame implements ActionListener{
	private JLabel lblName;
	private JLabel cbxName;
	private JLabel lblSemester;
	private JButton cmdSave;
	private JTextArea txtComments;
	private JLabel lblComments;
	private JComboBox cbxGrade;
	private JLabel lblGrade;
	private JLabel cbxSemester;
	private JScrollPane scrollpaneComments;
	private String course;
	private String[] semester;
	private String grade;
	private String comments;
	private ProphetController controller;
	//private TestController controller;
	/*
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
				String[] temp={"Fall","2011"};
				EditCourse inst = new EditCourse(new TestController(),"CMSC 201", temp);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public EditCourse(ProphetController controller, String course, String[] semester) {
		super();
		this.controller=controller;
		//this.controller=(TestController) controller;
		this.course=course;
		this.semester=semester;
		String[] data=controller.getCourseInfo(course, semester[0], Integer.parseInt(semester[1]));
		grade=data[0];
		comments=data[1];
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
				cbxName = new JLabel();
				getContentPane().add(cbxName);
				cbxName.setText(course);
				cbxName.setBounds(115, 11, 149, 17);
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
				cbxSemester = new JLabel();
				getContentPane().add(cbxSemester);
				cbxSemester.setText(semester[0]+" "+semester[1]);
				cbxSemester.setBounds(115, 38, 149, 19);
				cbxSemester.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblGrade = new JLabel();
				getContentPane().add(lblGrade);
				lblGrade.setText("Grade");
				lblGrade.setBounds(10, 69, 63, 14);
				lblGrade.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				ComboBoxModel cbxGradeModel = 
					new DefaultComboBoxModel(
							new String[] { "A", "B", "C", "D", "E", "Pass", "Fail"});
				cbxGrade = new JComboBox();
				getContentPane().add(cbxGrade);
				cbxGrade.setModel(cbxGradeModel);
				cbxGrade.getModel().setSelectedItem(grade);
				cbxGrade.setBounds(115, 68, 80, 20);
				cbxGrade.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblComments = new JLabel();
				getContentPane().add(lblComments);
				lblComments.setText("Comments");
				lblComments.setBounds(10, 94, 100, 14);
				lblComments.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				txtComments = new JTextArea();
				txtComments.setText(comments);
				scrollpaneComments = new JScrollPane(txtComments);
				getContentPane().add(scrollpaneComments);
				scrollpaneComments.setBounds(114, 99, 230, 119);

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
		controller.editCourse(semester[0], Integer.parseInt(semester[1]), course, (String) cbxGrade.getModel().getSelectedItem(), txtComments.getText());
		this.dispose();
	}

}
