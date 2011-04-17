package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.WindowConstants;
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
public class SelectTrack extends javax.swing.JFrame implements ActionListener{
	private JLabel lblSelectTrack;
	private JButton cmdCSMajor;
	private JButton cmdISMajorCSMinor;
	private JButton cmdCSISDoubleMajor;
	private JButton cmdISMajor;

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
				SelectTrack inst = new SelectTrack();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SelectTrack() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("UMBC CS/IS Schedule Prophet");
			getContentPane().setLayout(null);
			{
				lblSelectTrack = new JLabel();
				getContentPane().add(lblSelectTrack);
				lblSelectTrack.setText("Select track:");
				lblSelectTrack.setBounds(10, 11, 119, 17);
				lblSelectTrack.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				cmdCSMajor = new JButton();
				getContentPane().add(cmdCSMajor);
				cmdCSMajor.setText("Computer Science Major");
				cmdCSMajor.setBounds(53, 39, 388, 25);
				cmdCSMajor.setFont(new java.awt.Font("Tahoma",0,14));
				cmdCSMajor.setActionCommand("CSMajor");
				cmdCSMajor.addActionListener(this);
			}
			{
				cmdISMajor = new JButton();
				getContentPane().add(cmdISMajor);
				cmdISMajor.setText("Information Systems Major");
				cmdISMajor.setBounds(53, 75, 388, 25);
				cmdISMajor.setFont(new java.awt.Font("Tahoma",0,14));
				cmdISMajor.setActionCommand("ISMajor");
				cmdISMajor.addActionListener(this);
			}
			{
				cmdCSISDoubleMajor = new JButton();
				getContentPane().add(cmdCSISDoubleMajor);
				cmdCSISDoubleMajor.setText("Computer Science / Information Systems Double Major");
				cmdCSISDoubleMajor.setBounds(53, 111, 388, 25);
				cmdCSISDoubleMajor.setFont(new java.awt.Font("Tahoma",0,14));
				cmdCSISDoubleMajor.setActionCommand("CSISDouble");
				cmdCSISDoubleMajor.addActionListener(this);
			}
			{
				cmdISMajorCSMinor = new JButton();
				getContentPane().add(cmdISMajorCSMinor);
				cmdISMajorCSMinor.setText("Information System Major with Computer Science Minor");
				cmdISMajorCSMinor.setBounds(53, 147, 388, 23);
				cmdISMajorCSMinor.setFont(new java.awt.Font("Tahoma",0,14));
				cmdISMajorCSMinor.setActionCommand("ISCSMinor");
				cmdISMajorCSMinor.addActionListener(this);
			}
			pack();
			this.setSize(502, 230);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CSMajor")){
			this.dispose();
			placeHolder();
		}
		else if(e.getActionCommand().equals("ISMajor")){
			this.dispose();
			placeHolder();
		}
		else if(e.getActionCommand().equals("CSISDouble")){
			this.dispose();
			placeHolder();
		}
		else if(e.getActionCommand().equals("ISCSMinor")){
			this.dispose();
			placeHolder();
		}
	}
	
	private void placeHolder(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NamePlan inst = new NamePlan();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

}
