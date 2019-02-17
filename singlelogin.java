package lbms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class singlelogin {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					singlelogin window = new singlelogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public singlelogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("LOGIN");
		frmLogin.setBounds(100, 100, 459, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblLibraryManagementSystem = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblLibraryManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLibraryManagementSystem.setBounds(65, 33, 293, 22);
		frmLogin.getContentPane().add(lblLibraryManagementSystem);
		
		JLabel lblUserid = new JLabel("USERID");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserid.setBounds(65, 102, 46, 14);
		frmLogin.getContentPane().add(lblUserid);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(65, 160, 81, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(177, 100, 113, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try {
					
					 FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserDetails.txt");
				        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				        String strLine;
				       String role="null";
				        br.readLine();
				        				        // Read File Line By Line
				        while ((strLine = br.readLine()) != null)
				         {
				            strLine = strLine.trim();

				            if (strLine.length()!=0)
				            {
				                String[] students = strLine.split("\\t+");
				                String userid=textField.getText();
				                @SuppressWarnings("deprecation")
								String pswd=passwordField.getText();
				                if((userid.contains(students[0]))&&(pswd.contains(students[1])))
					          { 
				                	 role=students[2];
				                	try//inserts user logged in details
					            	{
					            	    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserLoggedIn.txt";
					            	    FileWriter fw = new FileWriter(filename);
					            	    fw.write(userid+"\t"+pswd+"\t"+ role+"\n");
					            	    System.out.println("Done insertion");
					            	    fw.close();
					            	}
					            	catch(IOException ioe)
					            	{
					            	    System.err.println("IOException: " + ioe.getMessage());
					            	}
				                	
				        			 switch(role)
				        			  {
				        			  case "student":
				        				  JOptionPane.showMessageDialog(frmLogin, "successfully loggedin");
				        				  function f1=new function();
				        				  function.main(null);
				        				  break;
				        				
				        			  case "instructor":
				        				  
				        				  JOptionPane.showMessageDialog(frmLogin, "successfully loggedin");
				        				  staff s1=new staff();
				        				  staff.main(null);
				        				  break;
				        				  
				        			  case "librarian":
				        				  //System.out.println("librarian");
				        				 
				        				  JOptionPane.showMessageDialog(frmLogin, "successfully loggedin");
				        				  librarian l1=new librarian();
				        				  librarian.main(null);
				        				  break;
				        				  
				        			 default:
				        				 JOptionPane.showMessageDialog(frmLogin, "invalid user");
				        				 break;
				        			  }
				        	 }   } }br.close();
				        	 if(role.equalsIgnoreCase("null"))
				                	JOptionPane.showMessageDialog(frmLogin, "invalid userr");
				          }
					
					catch(Exception E)
					{System.err.println("Error: " + E.getMessage());}
				
			}
			
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBounds(291, 207, 89, 23);
		frmLogin.getContentPane().add(btnSubmit);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				passwordField.setText(null);
				
			
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReset.setBounds(177, 208, 89, 23);
		frmLogin.getContentPane().add(btnReset);
		passwordField = new JPasswordField();
		passwordField.setBounds(178, 147, 113, 20);
		frmLogin.getContentPane().add(passwordField);
		
		
	}
}
