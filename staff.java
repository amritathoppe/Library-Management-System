package lbms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class staff {

	private JFrame frmStaff;

	/**
	 * Launch the application.
	 */
	public static void main(String students) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staff window = new staff();
					window.frmStaff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public staff() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStaff = new JFrame();
		frmStaff.setTitle("STAFF");
		frmStaff.setBounds(100, 100, 450, 300);
		frmStaff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStaff.getContentPane().setLayout(null);
		
		JButton btnBorrowBook = new JButton("BORROW BOOK");
		btnBorrowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow b1=new borrow();
				borrow.main(null);
			}
		});
		btnBorrowBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBorrowBook.setBounds(22, 25, 149, 66);
		frmStaff.getContentPane().add(btnBorrowBook);
		
		JButton btnReturnBook = new JButton("RETURN BOOK");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return b1=new Return();
				Return.main(null);
			}
		});
		btnReturnBook.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReturnBook.setBounds(236, 31, 149, 60);
		frmStaff.getContentPane().add(btnReturnBook);
		
		JButton btnBorrowJournal = new JButton("BORROW JOURNAL");
		btnBorrowJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BORROWJ bj1=new BORROWJ();
				BORROWJ.main(null);
				
			}
		});
		btnBorrowJournal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBorrowJournal.setBounds(22, 101, 149, 65);
		frmStaff.getContentPane().add(btnBorrowJournal);
		
		JButton btnNewButton = new JButton("RETURN JOURNAL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnJ rj1=new ReturnJ();
				ReturnJ.main(null);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(235, 100, 150, 66);
		frmStaff.getContentPane().add(btnNewButton);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogOut.setBounds(149, 176, 111, 52);
		frmStaff.getContentPane().add(btnLogOut);
	}
}
