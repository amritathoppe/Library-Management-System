package lbms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BORROWJ {

	private JFrame frmBorrowJournal;
	private JTable table;
	String userid,pswd,role;
	String selected_id,selected_type,selected_subject,selected_name,selected_author,selected_totalcopies;
	private JButton btnGoBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BORROWJ window = new BORROWJ();
					window.frmBorrowJournal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BORROWJ() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBorrowJournal = new JFrame();
		frmBorrowJournal.setTitle("BORROW JOURNAL");
		frmBorrowJournal.setBounds(100, 100, 606, 478);
		frmBorrowJournal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBorrowJournal.getContentPane().setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			int selectedRowIndex=table.getSelectedRow();
			selected_id=(String) model.getValueAt(selectedRowIndex, 0);
			selected_type=(String) model.getValueAt(selectedRowIndex, 1);
			selected_subject=(String) model.getValueAt(selectedRowIndex, 2);
			selected_name=(String) model.getValueAt(selectedRowIndex, 3);
			selected_author=(String) model.getValueAt(selectedRowIndex, 4);
			selected_totalcopies=(String) model.getValueAt(selectedRowIndex, 5);
		   
			System.out.println(selected_id+"\t"+selected_type+"\t"+selected_subject+"\t"+selected_name+"\t"+selected_author+"\t"+selected_totalcopies);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try{       
        	FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	      //  br.readLine();
	        String columns[] =  { "Id","Type","Subject","Name","Authors","Totalcopies","Borrowedcopies"};
	       
    		DefaultTableModel tableModel;
    	    // specify number of columns
    	    tableModel = new DefaultTableModel(0,6); 
    	    tableModel.setColumnIdentifiers(columns);
    	    tableModel.addRow(columns);
    	    table.setModel(tableModel);
    	    
            while((strLine = br.readLine()) != null) 
            {
            	String[] records = strLine.split("\\t+");
            	String borrowed_copies=records[6];
        		String total_copies=records[5];
            	if(records[1].contains("Journal"))
               {
            		
            		if(total_copies.contains(borrowed_copies))
            			br.readLine();
            		else
            			tableModel.addRow(records); 
               }       
            }
           
           br.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
            }
		table.setBounds(31, 42, 437, 361);
		frmBorrowJournal.getContentPane().add(table);
		
		JButton btnBorrow = new JButton("BORROW");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call borrow method here passing the value captured. 
				
				
	        	  try//inserts user logged in details
	            	{
	        		  //System.out.println("try");
	        		  FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserLoggedIn.txt");
				        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				        String strLine;
				       
				        // Read File Line By Line
				        while ((strLine = br.readLine())!= null)
				         {
				            //System.out.println("while");
				        	strLine = strLine.trim();
				        	if (strLine.length()!=0)
				            {
				                String[] user = strLine.split("\\t+");
				                userid=user[0];
				                //@SuppressWarnings("deprecation")
								pswd=user[1];
				                role=user[2];
				                System.out.println(userid+"\t"+pswd+"\t"+role);
				            }
				        	}
				        br.close();
	            	}
	            	catch(IOException ioe)
	            	{
	            	    System.err.println("IOException: " + ioe.getMessage());
	            	}
	        	//filter first whether Library Member borrowed Maximum Limit
					Library_member l=new Library_member(userid,pswd,role);
			        boolean check=l.oktoBorrowJournal();
			        
					if(check) {
					//call borrow method here passing the value captured. 
					journal j = new journal(selected_id,selected_type,selected_subject,selected_name,selected_author, selected_totalcopies);
					j.borrow_Journal();
				/*	copy c= new copy(j);
					c.Borrow(j);*/
					JOptionPane.showMessageDialog(null,"successfully borrowed");//alert box msg here.
					
					//System.out.println(selected_id+"is id");
					}
					else 
						JOptionPane.showMessageDialog(null,"You have exceeded Maximum Limit");//alert box msg here..
					
			}
		});
		btnBorrow.setBounds(478, 282, 102, 48);
		frmBorrowJournal.getContentPane().add(btnBorrow);
		
		btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				journal j = new journal(selected_id,selected_type,selected_subject,selected_name,selected_author, selected_totalcopies);
				String role=j.currentrole();
				switch(role)
  			  {
  			  
  				
  			  case "instructor":
  				  
  				  //JOptionPane.showMessageDialog(frmLogin, "successfully loggedin");
  				  staff s1=new staff();
  				  staff.main(null);
  				  break;
  			  case "librarian":
  				  //System.out.println("librarian");
  				 
  				  //JOptionPane.showMessageDialog(frmLogin, "successfully loggedin");
  				  librarian l1=new librarian();
  				  librarian.main(null);
  				  
  				  break;
  			 default:
  				 //JOptionPane.showMessageDialog(frmLogin, "invalid user");
  				 break;
  			  }
				
				
			}
		});
		btnGoBack.setBounds(478, 340, 102, 48);
		frmBorrowJournal.getContentPane().add(btnGoBack);
		
		
	}
}
