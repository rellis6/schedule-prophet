package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
 * File: LoadPlan.java
 * Project: schedule-prophet
 * @author g00gle
 * Date: 
 * Description: Allows the user to select a plan to load.
 */
public class LoadPlan extends javax.swing.JFrame implements ActionListener{
	private JLabel lblSelectPlan;
	private JTable tblSavedPlans;
	private JButton cmdDeletePlan;
	private JButton cmdCancel;
	private JButton cmdSelectPlan;
	private JScrollPane scrollPane;
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
				LoadPlan inst = new LoadPlan(new TestController());
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	/**
	 * Create a LoadPlan object.
	 * 
	 * @param controller the ProphetController object this object should use.
	 */
	public LoadPlan(ProphetController controller) {
		super();
		this.controller=controller;
		this.GUI=null;
		initGUI();
	}
	
	/**
	 * Create a LoadPlan object.
	 * 
	 * @param controller the ProphetController object this object should use.
	 * @param GUI the MaintainPlan object that this window must either return 
	 * to if the user hits the cancel button, or close if the user hits the select button.
	 */
	public LoadPlan(ProphetController controller, MaintainPlan GUI) {
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
			getContentPane().setLayout(null);
			this.setTitle("UMBC CS/IS Schedule Prophet");
			int numRows;
			{
				lblSelectPlan = new JLabel();
				getContentPane().add(lblSelectPlan);
				lblSelectPlan.setText("Select a plan:");
				lblSelectPlan.setBounds(10, 11, 128, 19);
				lblSelectPlan.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				ArrayList<String> tempRows = controller.getPlans();
				String rows[][] = new String[tempRows.size()][1];
				for(int i=0; i<tempRows.size(); i++){
					String temp[] = new String[1];
					temp[0]=tempRows.get(i);
					rows[i]=temp;
				}
				String headers[] = { "Plan Name"};
				numRows=rows.length;
				if(numRows<1){
					numRows=1;
				}
				numRows++;
				numRows=6;
				tblSavedPlans = new JTable(rows,headers);
				tblSavedPlans.setBounds(20, 41, 360, 20*numRows);
				tblSavedPlans.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				tblSavedPlans.setFont(new java.awt.Font("Tahoma",0,14));
				tblSavedPlans.setPreferredSize(new java.awt.Dimension(358, 111));

				scrollPane = new JScrollPane(tblSavedPlans);
				scrollPane.setBounds(20, 41, 360, 20*numRows);
				scrollPane.setFont(new java.awt.Font("Tahoma",0,14));
				getContentPane().add(scrollPane);
			}
			{
				cmdSelectPlan = new JButton();
				getContentPane().add(cmdSelectPlan);
				cmdSelectPlan.setText("Select Plan");
				cmdSelectPlan.setBounds(20, 20*numRows+50, 115, 23);
				cmdSelectPlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdSelectPlan.setActionCommand("Select");
				cmdSelectPlan.addActionListener(this);
			}
			{
				cmdDeletePlan = new JButton();
				getContentPane().add(cmdDeletePlan);
				cmdDeletePlan.setText("Delete Plan");
				cmdDeletePlan.setBounds(145, 20*numRows+50, 116, 23);
				cmdDeletePlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdDeletePlan.setActionCommand("Delete");
				cmdDeletePlan.addActionListener(this);
			}
			{
				cmdCancel = new JButton();
				getContentPane().add(cmdCancel);
				cmdCancel.setText("Cancel");
				cmdCancel.setBounds(271, 20*numRows+50, 109, 23);
				cmdCancel.setFont(new java.awt.Font("Tahoma",0,14));
				cmdCancel.setActionCommand("Cancel");
				cmdCancel.addActionListener(this);
			}
			pack();
			this.setSize(415, 20*numRows+125);
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
		int row=tblSavedPlans.getSelectedRow();
		if(e.getActionCommand().equals("Select") && row>=0){
			controller.loadPlan((String) tblSavedPlans.getValueAt(row, 0));
			if(GUI!=null){
				GUI.dispose();
			}
			this.dispose();
			final MaintainPlan inst = new MaintainPlan(controller);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					inst.setSelf(inst);
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
			inst.regenerateTrees();
		}
		else if(e.getActionCommand().equals("Delete") && row>=0){
			controller.deletePlan((String) tblSavedPlans.getValueAt(row, 0));
			scrollPane.setVisible(false);
			ArrayList<String> tempRows = controller.getPlans();
			String rows[][] = new String[tempRows.size()][2];
			for(int i=0; i<tempRows.size(); i++){
				String temp[] = new String[2];
				temp[0]=tempRows.get(i);
				temp[1]="bla";
				rows[i]=temp;
			}
			String headers[] = { "Plan Name", "Date Created"};
			tblSavedPlans = new JTable(rows,headers);
			tblSavedPlans.setBounds(20, 41, 360, 142);
			tblSavedPlans.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			tblSavedPlans.setFont(new java.awt.Font("Tahoma",0,14));
			tblSavedPlans.setPreferredSize(new java.awt.Dimension(358, 111));

			scrollPane = new JScrollPane(tblSavedPlans);
			scrollPane.setBounds(20, 41, 360, 139);
			scrollPane.setFont(new java.awt.Font("Tahoma",0,14));
			getContentPane().add(scrollPane);
		}
		else if(e.getActionCommand().equals("Cancel")){
			this.dispose();
			if(GUI==null){
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						StartMenu inst = new StartMenu(controller);
						inst.setLocationRelativeTo(null);
						inst.setVisible(true);
					}
				});
			}
		}
	}

}
