package lbms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class function {

	private JFrame frmStudent;

	/**
	 * Launch the application.
	 */
	public static void main(String students) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					function window = new function();
					window.frmStudent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public function() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudent = new JFrame();
		frmStudent.setTitle("STUDENT");
		frmStudent.setBounds(100, 100, 450, 300);
		frmStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudent.getContentPane().setLayout(null);
		
		JButton borrowbook = new JButton("BORROW BOOK");
		borrowbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow b1=new borrow();
				borrow.main(null);
			}
		});
		borrowbook.setFont(new Font("Tahoma", Font.BOLD, 11));
		borrowbook.setBounds(44, 35, 153, 54);
		frmStudent.getContentPane().add(borrowbook);
		
		JButton returnbook = new JButton("RETURN BOOK");
		returnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return b1=new Return();
				Return.main(null);
			}
		});
		returnbook.setFont(new Font("Tahoma", Font.BOLD, 11));
		returnbook.setBounds(249, 35, 144, 54);
		frmStudent.getContentPane().add(returnbook);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogOut.setBounds(157, 138, 131, 54);
		frmStudent.getContentPane().add(btnLogOut);
	}
}
