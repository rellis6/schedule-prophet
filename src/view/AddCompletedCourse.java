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
public class AddCompletedCourse extends javax.swing.JFrame implements ActionListener{
        private JLabel lblName;
        private JLabel cbxName;
        private JLabel lblSemester;
        private JLabel lblSemesterName;
        private JButton cmdSave;
        private JTextArea txtComments;
        private JLabel lblComments;
        private JComboBox cbxGrade;
        private JLabel lblGrade;
        private JComboBox cbxSeason;
        private JTextField txtYear;
        private JScrollPane scrollpaneComments;
        private String needed;
        private String[] future;
        private MaintainPlan GUI;
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
                                String[] temp={"Spring","2011"};
                                AddCompletedCourse inst = new AddCompletedCourse(new TestController(), "CMSC 201", temp, new MaintainPlan(new TestController()));
                                inst.setLocationRelativeTo(null);
                                inst.setVisible(true);
                        }
                });
        }
        
        public AddCompletedCourse(ProphetController controller, String needed, String[] future, MaintainPlan GUI) {
                super();
                this.controller=controller;
                //this.controller=(TestController) controller;
                this.needed=needed;
                this.future=future;
                this.GUI=GUI;
                initGUI();
        }
        
        private void initGUI() {
                try {
                        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        this.setTitle("Add Completed Course");
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
                                cbxName.setText(needed);
                                cbxName.setBounds(120, 11, 240, 17);
                                cbxName.setFont(new java.awt.Font("Tahoma",0,14));
                        }
                        {
                                lblSemester = new JLabel();
                                getContentPane().add(lblSemester);
                                lblSemester.setText("Semester");
                                lblSemester.setBounds(10, 39, 105, 14);
                                lblSemester.setFont(new java.awt.Font("Tahoma",0,14));
                        }
                        if(future==null){
                                ComboBoxModel cbxSemesterModel = 
                                        new DefaultComboBoxModel(
                                                        new String[] { "Fall", "Winter", "Spring", "Summer"});
                                cbxSeason = new JComboBox();
                                getContentPane().add(cbxSeason);
                                cbxSeason.setModel(cbxSemesterModel);
                                cbxSeason.setBounds(115, 38, 110, 20);
                                cbxSeason.setFont(new java.awt.Font("Tahoma",0,14));
                        }
                        if(future==null){
                                txtYear = new JTextField();
                                getContentPane().add(txtYear);
                                txtYear.setBounds(235, 38, 50, 20);
                                txtYear.setFont(new java.awt.Font("Tahoma",0,14));
                        }
                        if(future!=null){
                                lblSemester = new JLabel();
                                getContentPane().add(lblSemester);
                                lblSemester.setText(future[0]+" "+future[1]);
                                lblSemester.setBounds(120, 39, 235, 19);
                                lblSemester.setFont(new java.awt.Font("Tahoma",0,14));
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
                                scrollpaneComments = new JScrollPane(txtComments);
                                getContentPane().add(scrollpaneComments);
                                scrollpaneComments.setBounds(114, 99, 230, 119);

                        }
                        {
                                cmdSave = new JButton();
                                getContentPane().add(cmdSave);
                                cmdSave.setText("Save");
                                cmdSave.setBounds(141, 233, 88, 23);
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
                try{
                        if(future==null){
                                controller.addCourse(needed, (String) cbxSeason.getModel().getSelectedItem(), txtComments.getText(), Integer.parseInt(txtYear.getText()));
                        }
                        else{
                                controller.addCourse(needed, future[0], txtComments.getText(), Integer.parseInt(future[1]));
                        }
                        controller.editCourse(future[0], Integer.parseInt(future[1]), needed, (String) cbxGrade.getModel().getSelectedItem(), txtComments.getText());
                        this.dispose();
                        GUI.regenerateTrees();
                }
                catch (Exception f) {
                }
        }

}