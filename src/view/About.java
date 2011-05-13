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

/**
 * File: About.java
 * Project: schedule-prophet
 * @author g00gle
 * Date: 
 * Description: Displays some info about the program.
 */
public class About extends javax.swing.JFrame implements ActionListener{
	private JLabel lblName;
	private JButton cmdClose;
	
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
				About inst = new About();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public About() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("About");
			getContentPane().setLayout(null);
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("UMBC CS/IS Schedule Prophet");
				lblName.setBounds(10, 11, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Made by:");
				lblName.setBounds(10, 39, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Justin Ermer");
				lblName.setBounds(10, 67, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Mark Pallone");
				lblName.setBounds(10, 95, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Robert Ellis");
				lblName.setBounds(10, 123, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("John LaCourse");
				lblName.setBounds(10, 151, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName);
				lblName.setText("Katherine Miller");
				lblName.setBounds(10, 179, 350, 17);
				lblName.setFont(new java.awt.Font("Tahoma",0,14));
			}
			{
				cmdClose = new JButton();
				getContentPane().add(cmdClose);
				cmdClose.setText("Close");
				cmdClose.setBounds(124, 228, 88, 23);
				cmdClose.setFont(new java.awt.Font("Tahoma",0,14));
				cmdClose.addActionListener(this);
			}
			pack();
			this.setSize(370, 300);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
