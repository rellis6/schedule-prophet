package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class LoadPlan extends javax.swing.JFrame implements ActionListener{
	private JLabel lblSelectPlan;
	private JTable tblSavedPlans;
	private JButton cmdDeletePlan;
	private JButton cmdCancel;
	private JButton cmdSelectPlan;

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
				LoadPlan inst = new LoadPlan();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public LoadPlan() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("UMBC CS/IS Schedule Prophet");
			{
				lblSelectPlan = new JLabel();
				getContentPane().add(lblSelectPlan);
				lblSelectPlan.setText("Select a plan:");
				lblSelectPlan.setBounds(10, 11, 128, 19);
				lblSelectPlan.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				String rows[][] = { { "Katherine's Plan 3", "09/03/2010"},
				 		 { "Katherine's Hypothetical CS/IS Plan", "8/14/2010"},
						 { "Katherine's Plan 2", "07/21/2010"},
						 { "Katherine's Hypothetical IS Plan", "10/05/2008"},
						 { "Katherine Miller's CS Plan", "08/28/2007"}};
				String headers[] = { "Plan Name", "Date Created"};
				tblSavedPlans = new JTable(rows,headers);
				tblSavedPlans.setBounds(20, 41, 360, 142);
				tblSavedPlans.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				tblSavedPlans.setFont(new java.awt.Font("Tahoma",0,14));
				tblSavedPlans.setPreferredSize(new java.awt.Dimension(358, 111));

				JScrollPane scrollPane = new JScrollPane(tblSavedPlans);
				scrollPane.setBounds(20, 41, 360, 139);
				scrollPane.setFont(new java.awt.Font("Tahoma",0,14));
				getContentPane().add(scrollPane);
			}
			{
				cmdSelectPlan = new JButton();
				getContentPane().add(cmdSelectPlan);
				cmdSelectPlan.setText("Select Plan");
				cmdSelectPlan.setBounds(20, 191, 115, 23);
				cmdSelectPlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdSelectPlan.setActionCommand("Select");
				cmdSelectPlan.addActionListener(this);
			}
			{
				cmdDeletePlan = new JButton();
				getContentPane().add(cmdDeletePlan);
				cmdDeletePlan.setText("Delete Plan");
				cmdDeletePlan.setBounds(145, 191, 116, 23);
				cmdDeletePlan.setFont(new java.awt.Font("Tahoma",0,14));
				cmdDeletePlan.setActionCommand("Delete");
				cmdDeletePlan.addActionListener(this);
			}
			{
				cmdCancel = new JButton();
				getContentPane().add(cmdCancel);
				cmdCancel.setText("Cancel");
				cmdCancel.setBounds(271, 191, 109, 23);
				cmdCancel.setFont(new java.awt.Font("Tahoma",0,14));
				cmdCancel.setActionCommand("Cancel");
				cmdCancel.addActionListener(this);
			}
			pack();
			this.setSize(415, 266);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Select")){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					MaintainPlan inst = new MaintainPlan();
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
		else if(e.getActionCommand().equals("Delete")){
			//delete stuff
		}
		else if(e.getActionCommand().equals("Cancel")){
			this.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					StartMenu inst = new StartMenu();
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
			});
		}
	}

}
