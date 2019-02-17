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

public class ReturnJ {

	private JFrame frmReturnJournal;
	private JTable table;
	String selected_id,selected_Name,selected_Borroweddate,selected_Returndate,selected_Userid,userid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnJ window = new ReturnJ();
					window.frmReturnJournal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReturnJ() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReturnJournal = new JFrame();
		frmReturnJournal.setTitle("RETURN JOURNAL");
		frmReturnJournal.setBounds(100, 100, 711, 471);
		frmReturnJournal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReturnJournal.getContentPane().setLayout(null);
		
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
	        String columns[] =  { "Id","Name","userid","Borrowed Date","Return Date"};
	       
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
            	
            	if((records[2].contains(userid)) && (records[0].startsWith("J"))) 
               
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
		table.setBounds(10, 48, 515, 356);
		frmReturnJournal.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("RETURN JOURNAL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rjournal rb = new rjournal(selected_id,selected_Name,selected_Userid,selected_Borroweddate,selected_Returndate,null);
				rb.return_journal();
				JOptionPane.showMessageDialog(null,"successfully returned");//alert box msg here.
			}
		});
		btnNewButton.setBounds(529, 255, 157, 40);
		frmReturnJournal.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("GO BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
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
		btnNewButton_1.setBounds(529, 327, 157, 40);
		frmReturnJournal.getContentPane().add(btnNewButton_1);
	}
}
