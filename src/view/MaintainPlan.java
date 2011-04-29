package view;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Course;
import model.Semester;

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
public class MaintainPlan extends javax.swing.JFrame implements ActionListener{
	private JButton cmdAddCompletedCourse;
	private JTree treeCompletedCourses;
	private JButton cmdEditCompletedCourse;
	private JScrollPane completedScrollPane;				
	private JScrollPane futureScrollPane;
	private JTree treeNeededCourses;
	private JMenu jMenu1;
	private JMenuBar menuMainMenu;
	private JButton cmdRemoveCourse;
	private JMenu menuHelp;
	private JButton cmdAddCourseToPlan;
	private JTree treeFuturePlan;
	private JButton cmdMarkSemesterComplete;
	private JMenuItem menuAbout;
	private JSeparator menuSeparator2;
	private JSeparator menuSeparator;
	private JMenuItem menuQuit;
	private JMenuItem menuExport;
	private JMenuItem menu;
	private JMenuItem menuOpen;
	private JScrollPane neededScrollPane;
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
				MaintainPlan inst = new MaintainPlan(new TestController());
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MaintainPlan(ProphetController controller) {
		super();
		this.controller=controller;
		//this.controller=(TestController) controller;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("UMBC CS/IS Schedule Prophet");
			{
				menuMainMenu = new JMenuBar();
				setJMenuBar(menuMainMenu);
				{
					jMenu1 = new JMenu();
					menuMainMenu.add(jMenu1);
					jMenu1.setText("File");
					jMenu1.setFont(new java.awt.Font("Segoe UI",0,14));
					{
						menuOpen = new JMenuItem();
						jMenu1.add(menuOpen);
						menuOpen.setText("Open");
						menuOpen.setFont(new java.awt.Font("Tahoma",0,12));
						menuOpen.setActionCommand("Open");
						menuOpen.addActionListener(this);
					}
					{
						menu = new JMenuItem();
						jMenu1.add(menu);
						menu.setText("Save");
						menu.setFont(new java.awt.Font("Tahoma",0,12));
						menu.setActionCommand("Save");
						menu.addActionListener(this);
					}
					{
						menuSeparator = new JSeparator();
						jMenu1.add(menuSeparator);
					}
					{
						menuExport = new JMenuItem();
						jMenu1.add(menuExport);
						menuExport.setText("Export");
						menuExport.setFont(new java.awt.Font("Tahoma",0,12));
						menuExport.setActionCommand("Export");
						menuExport.addActionListener(this);
					}
					{
						menuSeparator2 = new JSeparator();
						jMenu1.add(menuSeparator2);
					}
					{
						menuQuit = new JMenuItem();
						jMenu1.add(menuQuit);
						menuQuit.setText("Quit to Start Menu");
						menuQuit.setFont(new java.awt.Font("Tahoma",0,12));
						menuQuit.setActionCommand("Quit");
						menuQuit.addActionListener(this);
					}
				}
				{
					menuHelp = new JMenu();
					menuMainMenu.add(menuHelp);
					menuHelp.setText("Help");
					menuHelp.setFont(new java.awt.Font("Segoe UI",0,14));
					{
						menuAbout = new JMenuItem();
						menuHelp.add(menuAbout);
						menuAbout.setText("About UMBC CS/IS Schedule Prophet");
						menuAbout.setActionCommand("About");
						menuAbout.addActionListener(this);
					}
				}
			}
			{
				cmdAddCompletedCourse = new JButton();
				getContentPane().add(cmdAddCompletedCourse);
				cmdAddCompletedCourse.setText("Add Completed Course");
				cmdAddCompletedCourse.setBounds(10, 11, 175, 23);
				cmdAddCompletedCourse.setFont(new java.awt.Font("Tahoma",0,14));
				cmdAddCompletedCourse.setActionCommand("Add completed");
				cmdAddCompletedCourse.addActionListener(this);
			}
			{
				cmdEditCompletedCourse = new JButton();
				getContentPane().add(cmdEditCompletedCourse);
				cmdEditCompletedCourse.setText("Edit Completed Course");
				cmdEditCompletedCourse.setBounds(195, 11, 176, 23);
				cmdEditCompletedCourse.setFont(new java.awt.Font("Tahoma",0,14));
				cmdEditCompletedCourse.setActionCommand("Edit completed");
				cmdEditCompletedCourse.addActionListener(this);
			}
			{
				DefaultMutableTreeNode completed =
			        new DefaultMutableTreeNode("Completed Courses");
				
				DefaultMutableTreeNode semester = null;
				DefaultMutableTreeNode course = null;
				
				ArrayList<Semester> semesters = controller.getCompletedSemesters();
				for(int i=0; i<semesters.size(); i++){
					String temp=semesters.get(i).getSeason().concat(" ");
					temp=temp.concat(Integer.toString(semesters.get(i).getYear()));
					semester = new DefaultMutableTreeNode(temp);
					completed.add(semester);
					System.out.println(temp);
					ArrayList<Course> tempCourses = controller.getCourseList(temp);
					String[] courses = new String[tempCourses.size()];
					for(int j=0; j<tempCourses.size(); j++){
						courses[j]=tempCourses.get(j).getCourseID();
					}
					for(int j=0; j<courses.length; j++){
						course = new DefaultMutableTreeNode(courses[j]);
						semester.add(course);
					}
				}

				treeCompletedCourses = new JTree(completed);
				completedScrollPane = new JScrollPane(treeCompletedCourses);
				treeCompletedCourses.setFont(new java.awt.Font("Tahoma",0,14));
				getContentPane().add(completedScrollPane);
				completedScrollPane.setBounds(10, 40, 361, 240);
				//treeCompletedCourses.setBounds(10, 40, 361, 240);
				
				
			}
			{
				DefaultMutableTreeNode needed = new DefaultMutableTreeNode("Courses Needed");
				
				DefaultMutableTreeNode category = null;
				DefaultMutableTreeNode course = null;
				/*
				String[] categories = controller.getNeededCategories();
				for(int i=0; i<categories.length; i++){
					category = new DefaultMutableTreeNode(categories[i]);
					needed.add(category);
					ArrayList<Course> tempCourses = controller.getCourseList(categories[i]);
					String[] courses = new String[tempCourses.size()];
					for(int j=0; j<tempCourses.size(); j++){
						courses[j]=tempCourses.get(j).getCourseID();
					}
					for(int j=0; j<courses.length; j++){
						course = new DefaultMutableTreeNode(courses[j]);
						category.add(course);
					}*/
				ArrayList<Course> categories = controller.getCourseList("bla");
				for(int i=0; i<1; i++){
					category = new DefaultMutableTreeNode("Needed courses");
					needed.add(category);
					for(int j=0; j<categories.size(); j++){
						course = new DefaultMutableTreeNode(categories.get(j).getCourseID());
						category.add(course);
					}
				}
				
				treeNeededCourses = new JTree(needed);
				neededScrollPane = new JScrollPane(treeNeededCourses);
				getContentPane().add(neededScrollPane);
				neededScrollPane.setBounds(10, 291, 361, 249);
				neededScrollPane.setSize(361, 238);
				treeNeededCourses.setFont(new java.awt.Font("Tahoma",0,14));
				treeNeededCourses.setSize(359, 238);
			}
			{
				cmdAddCourseToPlan = new JButton();
				getContentPane().add(cmdAddCourseToPlan);
				cmdAddCourseToPlan.setText("Add Course To Plan");
				cmdAddCourseToPlan.setBounds(582, 11, 158, 23);
				cmdAddCourseToPlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdAddCourseToPlan.setActionCommand("Add course");
				cmdAddCourseToPlan.addActionListener(this);
			}
			{
				cmdRemoveCourse = new JButton();
				getContentPane().add(cmdRemoveCourse);
				cmdRemoveCourse.setText("Remove Course");
				cmdRemoveCourse.setBounds(437, 11, 140, 23);
				cmdRemoveCourse.setFont(new java.awt.Font("Tahoma",0,14));
				cmdRemoveCourse.setActionCommand("Remove course");
				cmdRemoveCourse.addActionListener(this);
			}
			{
				DefaultMutableTreeNode future = new DefaultMutableTreeNode("Future Course Plan");
				
				DefaultMutableTreeNode semester = null;
				DefaultMutableTreeNode course = null;
				
				ArrayList<Semester> semesters = controller.getFutureSemesters();
				for(int i=0; i<semesters.size(); i++){
					String temp=semesters.get(i).getSeason().concat(" ");
					temp=temp.concat(Integer.toString(semesters.get(i).getYear()));
					semester = new DefaultMutableTreeNode(temp);
					future.add(semester);
					ArrayList<Course> tempCourses = controller.getCourseList(temp);
					System.out.println(temp);
					System.out.println(tempCourses);
					System.out.println(controller.getCourseList(temp));
					String[] courses = new String[tempCourses.size()];
					for(int j=0; j<tempCourses.size(); j++){
						courses[j]=tempCourses.get(j).getCourseID();
					}
					for(int j=0; j<courses.length; j++){
						course = new DefaultMutableTreeNode(courses[j]);
						semester.add(course);
					}
				}
				
				treeFuturePlan = new JTree(future);
				futureScrollPane = new JScrollPane(treeFuturePlan);
				getContentPane().add(futureScrollPane);
				futureScrollPane.setBounds(381, 70, 359, 459);
				treeFuturePlan.setFont(new java.awt.Font("Tahoma",0,14));
				treeFuturePlan.setSize(357, 450);


			}
			{
				cmdMarkSemesterComplete = new JButton();
				getContentPane().add(cmdMarkSemesterComplete);
				cmdMarkSemesterComplete.setText("Mark Semester Complete");
				cmdMarkSemesterComplete.setBounds(437, 40, 303, 23);
				cmdMarkSemesterComplete.setFont(new java.awt.Font("Tahoma",0,14));
				cmdMarkSemesterComplete.setActionCommand("Mark completed");
				cmdMarkSemesterComplete.addActionListener(this);
			}
			pack();
			this.setSize(772, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		if(e.getActionCommand().equals("Open")){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					LoadPlan inst = new LoadPlan(controller);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
		else if(e.getActionCommand().equals("Quit")){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					StartMenu inst = new StartMenu(controller);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
		else if(e.getActionCommand().equals("Export")){
			
		}
		else if(e.getActionCommand().equals("Save")){
			
		}
		else if(e.getActionCommand().equals("About")){
			
		}
		else if(e.getActionCommand().equals("Add completed")){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					EditCourse inst = new EditCourse(controller);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
		else if(e.getActionCommand().equals("Edit completed")){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					EditCourse inst = new EditCourse(controller);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
		else if(e.getActionCommand().equals("Add course")){
			
		}
		else if(e.getActionCommand().equals("Remove course")){
			
		}
		else if(e.getActionCommand().equals("Mark completed")){
			
		}
		else{
			System.out.println(e.getActionCommand());
		}
	}

}
