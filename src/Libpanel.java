
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Libpanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Libpanel frame = new Libpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Libpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("category");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				category c = new category();
				c.setVisible(true);
			}
		});
		btnNewButton.setBounds(156, 45, 105, 23);
		contentPane.add(btnNewButton);
		
		JButton btnAuther = new JButton("Author");
		btnAuther.setBounds(156, 79, 105, 23);
		contentPane.add(btnAuther);
		
		JButton btnPabliser = new JButton("Publisher");
		btnPabliser.setBounds(156, 113, 105, 23);
		contentPane.add(btnPabliser);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BookEntry b = new BookEntry();
				b.setVisible(true);
			}
		});
		btnBook.setBounds(156, 147, 105, 23);
		contentPane.add(btnBook);
		
		JButton btnMember = new JButton("Member");
		btnMember.setBounds(156, 181, 105, 23);
		contentPane.add(btnMember);
		
		JButton btnIssueBook = new JButton("Issue book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Issuebook l = new Issuebook();
				l.setVisible(true);
				
			}
			
		});
		btnIssueBook.setBounds(156, 215, 105, 23);
		contentPane.add(btnIssueBook);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				returnbook r = new returnbook();
				r.setVisible(true);
				
			}
		});
		btnReturnBook.setBounds(156, 249, 105, 23);
		contentPane.add(btnReturnBook);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage h = new HomePage();
				h.setVisible(true);
			}
		});
		btnLogout.setBounds(156, 279, 105, 23);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel(" Librarian panel");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 424, 39);
		contentPane.add(lblNewLabel);
	}

}
