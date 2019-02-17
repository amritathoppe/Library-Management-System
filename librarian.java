package lbms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class librarian {

	private JFrame frmLibrarian;

	/**
	 * Launch the application.
	 */
	public static void main(String students) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					librarian window = new librarian();
					window.frmLibrarian.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public librarian() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibrarian = new JFrame();
		frmLibrarian.setTitle("LIBRARIAN");
		frmLibrarian.setBounds(100, 100, 517, 300);
		frmLibrarian.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibrarian.getContentPane().setLayout(null);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit e1=new edit();
				edit.main(null);
			}
		});
		btnUpdate.setBounds(169, 10, 136, 49);
		frmLibrarian.getContentPane().add(btnUpdate);
		
		JButton btnBorrowbook = new JButton("BORROW BOOK");
		btnBorrowbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrow b1=new borrow();
				borrow.main(null);
			}
		});
		btnBorrowbook.setBounds(10, 59, 136, 39);
		frmLibrarian.getContentPane().add(btnBorrowbook);
		
		JButton btnReturnBook = new JButton("RETURN BOOK");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return b1=new Return();
				Return.main(null);
			}
		});
		btnReturnBook.setBounds(10, 125, 136, 42);
		frmLibrarian.getContentPane().add(btnReturnBook);
		
		JButton btnBorrowJournal = new JButton("BORROW JOURNAL");
		btnBorrowJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BORROWJ bj1=new BORROWJ();
				BORROWJ.main(null);
			}
		});
		btnBorrowJournal.setBounds(314, 59, 164, 39);
		frmLibrarian.getContentPane().add(btnBorrowJournal);
		
		JButton btnReturnJournal = new JButton("RETURN JOURNAL");
		btnReturnJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnJ rj1=new ReturnJ();
				ReturnJ.main(null);
			}
		});
		btnReturnJournal.setBounds(314, 125, 164, 42);
		frmLibrarian.getContentPane().add(btnReturnJournal);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogOut.setBounds(169, 173, 136, 42);
		frmLibrarian.getContentPane().add(btnLogOut);
	}
}
