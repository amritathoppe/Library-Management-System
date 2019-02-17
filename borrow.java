package lbms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JOptionPane;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class borrow {

	private JFrame frmBorrowBook;
	private JTable table;
	String value_holder=null;
	String userid,pswd,role;
	//int total_copies,borrowed_copies;
	
	
	String selected_id,selected_type,selected_subject,selected_name,selected_author,selected_totalcopies;
	private JButton btnGoBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					borrow window = new borrow();
					window.frmBorrowBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public borrow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBorrowBook = new JFrame();
		frmBorrowBook.setTitle("BORROW BOOK");
		frmBorrowBook.setBounds(100, 100, 464, 371);
		frmBorrowBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBorrowBook.getContentPane().setLayout(null);
		
		
			    
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
	        //br.readLine();
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
            	if(records[1].contains("Book"))
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
		table.setBounds(10, 39, 384, 211);
		frmBorrowBook.getContentPane().add(table);
		
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			        boolean check=l.oktoBorrowBook();
			        
					if(check) {
					//call borrow method here passing the value captured. 
					book b = new book(selected_id,selected_type,selected_subject,selected_name,selected_author, selected_totalcopies);
					b.borrow_book();
					/*copy c= new copy(b);
					c.Borrow(b);*/
					JOptionPane.showMessageDialog(null,"successfully borrowed");//alert box msg here.
					//System.out.println(selected_id+"is id");
					}
					else 
						JOptionPane.showMessageDialog(null,"You have exceeded Maximum Limit");//alert box msg here..
					
				
	        	   }});
		btnBorrow.setBounds(48, 272, 104, 41);
		frmBorrowBook.getContentPane().add(btnBorrow);
		
		btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book b = new book(selected_id,selected_type,selected_subject,selected_name,selected_author, selected_totalcopies);
				String role=b.currentrole();
				switch(role)
  			  {
  			  case "student":
  				  //JOptionPane.showMessageDialog(frmLogin, "successfully loggedin");
  				  function f1=new function();
  				  function.main(null);
  				  break;
  				
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
		btnGoBack.setBounds(220, 272, 124, 41);
		frmBorrowBook.getContentPane().add(btnGoBack);
		
		
	}
		}
