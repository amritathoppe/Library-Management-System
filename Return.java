package lbms;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Return {

	private JFrame frmReturnBook;
	private JTable table;
	String selected_id,selected_Name,selected_Borroweddate,selected_Returndate,selected_Userid,userid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return window = new Return();
					window.frmReturnBook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Return() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReturnBook = new JFrame();
		frmReturnBook.setTitle("RETURN BOOK");
		frmReturnBook.setBounds(100, 100, 706, 503);
		frmReturnBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReturnBook.getContentPane().setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			int selectedRowIndex=table.getSelectedRow();
			selected_id=(String) model.getValueAt(selectedRowIndex, 0);
			selected_Name=(String) model.getValueAt(selectedRowIndex, 1);
			selected_Userid=(String) model.getValueAt(selectedRowIndex, 2);
			selected_Borroweddate=(String) model.getValueAt(selectedRowIndex, 3);
			selected_Returndate=(String) model.getValueAt(selectedRowIndex, 4);
			//selected_remdays=(String) model.getValueAt(selectedRowIndex, 4);
		   
			System.out.println("\n"+selected_id+"\t"+selected_Name+"\t"+selected_Userid+"\t"+selected_Borroweddate+"\t"+selected_Returndate+"\n");
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try{       
        	FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\Order.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	        //br.readLine();
	        String columns[] =  {"Id","Name","userid","Borrowed Date","Return Date"};
	       
    		DefaultTableModel tableModel;
    	    // specify number of columns
    	    tableModel = new DefaultTableModel(0,4); 
    	    tableModel.setColumnIdentifiers(columns);
    	    tableModel.addRow(columns);
    	    table.setModel(tableModel);
    	    
            while((strLine = br.readLine()) != null) 
            {
            	String[] records = strLine.split("\\t+");
            	try//inserts user logged in details
            	{
        		  System.out.println("try");
        		  FileInputStream fstream1 = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserLoggedIn.txt");
			        BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
			        String strLine1;
			       
			        // Read File Line By Line
			        while ((strLine1 = br1.readLine())!= null)
			         {
			            //System.out.println("while");
			        	strLine1 = strLine1.trim();
			        	if (strLine1.length()!=0)
			            {
			                String[] user = strLine1.split("\\t+");
			                userid=user[0];
			                
			                System.out.println(userid+"\t");
			            }
			        	}
			        br1.close();
            	}
            	catch(IOException ioe)
            	{
            	    System.err.println("IOException: " + ioe.getMessage());
            	}
            	
            	if((records[2].contains(userid)) && (records[0].startsWith("B"))) 
               
            		tableModel.addRow(records);
            	else
            		br.readLine();
                     
            }
           
           br.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
            }
		table.setBounds(44, 33, 457, 394);
		frmReturnBook.getContentPane().add(table);
		
		JButton btnReturnBook = new JButton("RETURN BOOK");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rbook rb = new rbook(selected_id,selected_Name,selected_Userid,selected_Borroweddate,selected_Returndate,null);
				rb.return_book();
				JOptionPane.showMessageDialog(null,"successfully returned");//alert box msg here.
				}			}
		);
		btnReturnBook.setBounds(523, 308, 120, 41);
		frmReturnBook.getContentPane().add(btnReturnBook);
		
		JButton btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbook rb = new rbook(selected_id,selected_Name,selected_Userid,selected_Borroweddate,selected_Returndate,null);
				String role=rb.currentrole();
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
		btnGoBack.setBounds(523, 372, 120, 41);
		frmReturnBook.getContentPane().add(btnGoBack);
	}
}
