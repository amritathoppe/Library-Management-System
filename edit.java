package lbms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
//import javax.swing.JPanel;
import javax.swing.JTable;

public class edit {

	private JFrame frmEdit;
	private JTextField id;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTable table;
	String value_holder,total_copies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit window = new edit();
					window.frmEdit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public edit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEdit = new JFrame();
		frmEdit.setTitle("EDIT");
		frmEdit.setBounds(100, 100, 629, 486);
		frmEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEdit.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 593, 425);
		frmEdit.getContentPane().add(tabbedPane);
		
		Panel panel = new Panel();
		tabbedPane.addTab("ADD", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(21, 11, 46, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setBounds(106, 8, 165, 20);
		panel.add(id);
		id.setColumns(10);
		
		JLabel lblType = new JLabel("TYPE");
		lblType.setBounds(21, 42, 46, 14);
		panel.add(lblType);
		
		textField = new JTextField();
		textField.setBounds(106, 39, 165, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblAuthor = new JLabel("NAME");
		lblAuthor.setBounds(21, 95, 46, 14);
		panel.add(lblAuthor);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 92, 165, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("AUTHOR");
		lblNewLabel.setBounds(21, 126, 46, 14);
		panel.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 123, 165, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSubject = new JLabel("SUBJECT");
		lblSubject.setBounds(21, 67, 46, 14);
		panel.add(lblSubject);
		
		textField_4 = new JTextField();
		textField_4.setBounds(106, 64, 165, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "added successfully");
				book b1=new book(null, null, null, null, null, null);
				
				String Name=textField_1.getText();
				String Subject=textField_4.getText();
				//String TotalCopies=textField_3.getText();
				String Type=textField.getText();
				String ID=id.getText();
				String Author=textField_2.getText();
				b1.Add(ID,Type,Subject, Name, Author);
				/*b1.Book_ID=ID;
				b1.Book_Type=Type;
				b1.Book_Subject=Subject;
				b1.Book_Name=Name;
				b1.Book_Author=Author;
				copy c= new copy(b1);
				c.add(b1);
				*/
				
			}
		});
		btnAdd.setBounds(172, 188, 112, 43);
		panel.add(btnAdd);
		
		JButton btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarian l1=new librarian();
				  librarian.main(null);
			}
		});
		btnGoBack.setBounds(337, 188, 122, 43);
		panel.add(btnGoBack);
				
		Panel panel_1 = new Panel();
		tabbedPane.addTab("DELETE", null, panel_1, null);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			int selectedRowIndex=table.getSelectedRow();
			value_holder = (String) model.getValueAt(selectedRowIndex, 0);
			total_copies=(String) model.getValueAt(selectedRowIndex, 5);
			System.out.println(selectedRowIndex+"  "+model.getValueAt(selectedRowIndex, 0)+"    "+value_holder);
		
			
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try{       
        	FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	       
	        br.readLine();
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
        		if(total_copies.contains(borrowed_copies))
        			br.readLine();
        		else
        			tableModel.addRow(records); 
               
            }
           br.close();
         }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error");
    e.printStackTrace();

        }
		table.setBounds(49, 21, 398, 333);
		panel_1.add(table);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "deleted successfully");
				book b1=new book(value_holder, null, null, null, null, total_copies);
				b1.delete_book(value_holder);
				
			}
		});
		btnDelete.setBounds(457, 265, 102, 38);
		panel_1.add(btnDelete);
		
		JButton btnGoBack_1 = new JButton("GO BACK");
		btnGoBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarian l1=new librarian();
				  librarian.main(null);
			}
		});
		btnGoBack_1.setBounds(457, 326, 102, 38);
		panel_1.add(btnGoBack_1);
		
	}
}
