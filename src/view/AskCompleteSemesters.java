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
public class AskCompleteSemesters extends javax.swing.JFrame implements ActionListener{
	private JLabel lblMessage1;
	private JLabel lblMessage2;
	private JLabel temp;
	private JLabel lblQuestion1;
	private JLabel lblQuestion2;
	private JButton btnYes;
	private JButton btnNo;
	private ArrayList<String[]> prerequisites;
	private MaintainPlan GUI;
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
				ArrayList<String[]> temp=new ArrayList<String[]>();
				String[] temp2={"Spring","2011"};
				String[] temp3={"Fall","2011"};
				String[] temp4={"Spring","2012"};
				temp.add(temp2);
				temp.add(temp3);
				temp.add(temp4);
				AskCompleteSemesters inst = new AskCompleteSemesters(new TestController(),temp, new MaintainPlan(new TestController()));
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public AskCompleteSemesters(ProphetController controller, ArrayList<String[]> prerequisites, MaintainPlan GUI) {
		super();
		this.controller=controller;
		//this.controller=(TestController) controller;
		this.prerequisites=prerequisites;
		this.GUI=GUI;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Prerequisites Needed");
			getContentPane().setLayout(null);
			{
				lblMessage1 = new JLabel();
				getContentPane().add(lblMessage1);
				lblMessage1.setText("The following semesters must be completed");
				lblMessage1.setBounds(10, 11, 350, 20);
				lblMessage1.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblMessage2 = new JLabel();
				getContentPane().add(lblMessage2);
				lblMessage2.setText("before you may complete the selected semester:");
				lblMessage2.setBounds(10, 39, 350, 20);
				lblMessage2.setFont(new java.awt.Font("Tahoma",0,14));
			}
			
			int i;
			for(i=0; i<prerequisites.size()-1; i++){
				temp = new JLabel();
				getContentPane().add(temp);
				String[] semester=prerequisites.get(i);
				temp.setText("- "+semester[0]+" "+semester[1]);
				temp.setBounds(10, 67+28*i, 350, 20);
				temp.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblQuestion1 = new JLabel();
				getContentPane().add(lblQuestion1);
				lblQuestion1.setText("Do you want to mark all these semesters");
				lblQuestion1.setBounds(10, 67+28*i, 350, 20);
				lblQuestion1.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblQuestion2 = new JLabel();
				getContentPane().add(lblQuestion2);
				lblQuestion2.setText("complete as well?");
				lblQuestion2.setBounds(10, 95+28*i, 350, 20);
				lblQuestion2.setFont(new java.awt.Font("Tahoma",0,14));
			}
			i+=1;
			{
				btnYes = new JButton();
				getContentPane().add(btnYes);
				btnYes.setText("Yes");
				btnYes.setBounds(89, 95+28*i, 88, 23);
				btnYes.setFont(new java.awt.Font("Tahoma",0,14));
				btnYes.setActionCommand("Yes");
				btnYes.addActionListener(this);
			}
			{
				btnNo = new JButton();
				getContentPane().add(btnNo);
				btnNo.setText("No");
				btnNo.setBounds(194, 95+28*i, 88, 23);
				btnNo.setFont(new java.awt.Font("Tahoma",0,14));
				btnNo.setActionCommand("No");
				btnNo.addActionListener(this);
			}
			pack();
			this.setSize(370, 150+28*i);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
		if(e.getActionCommand().equals("Yes")){
			GUI.completeSemesters(prerequisites);
		}
	}

}
