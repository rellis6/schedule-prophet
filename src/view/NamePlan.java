package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
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
public class NamePlan extends javax.swing.JFrame implements ActionListener{
	private JButton cmd;
	private JTextField txtName;
	private JLabel lblNamePlan;
	private ProphetController controller;
	//private TestController controller;
	private String track;
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
				NamePlan inst = new NamePlan(new TestController(), "CSMajor");
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public NamePlan(ProphetController controller, String track) {
		super();
		this.controller=controller;
		//this.controller=(TestController) controller;
		this.track=track;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Create a New Plan");
			{
				cmd = new JButton();
				getContentPane().add(cmd);
				cmd.setText("Create Plan");
				cmd.setBounds(129, 85, 123, 23);
				cmd.setFont(new java.awt.Font("Tahoma",0,14));
				cmd.addActionListener(this);
			}
			{
				txtName = new JTextField();
				getContentPane().add(txtName);
				txtName.setBounds(34, 54, 309, 20);
				txtName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblNamePlan = new JLabel();
				getContentPane().add(lblNamePlan);
				lblNamePlan.setText("Enter the name of your plan:");
				lblNamePlan.setBounds(34, 26, 198, 22);
				lblNamePlan.setFont(new java.awt.Font("Tahoma",0,14));
			}
			pack();
			this.setSize(400, 167);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
		controller.newPlan(track,txtName.getText());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MaintainPlan inst = new MaintainPlan(controller);
				inst.setSelf(inst);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

}
