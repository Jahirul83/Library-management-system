import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import libmghw.StudentProfile;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class loginpage extends JFrame {

	private JPanel contentPane;
	private JTextField logusername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginpage frame = new loginpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String id1,n1,phn1,dep1,u1,p1;

	public loginpage() 
	{
		intialize ();
		connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/librarydata","root","");
			
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	/**
	 * Create the frame.
	 */
	private void intialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logusername = new JTextField();
		logusername.setBounds(129, 61, 178, 20);
		contentPane.add(logusername);
		logusername.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String uname = logusername.getText();
					@SuppressWarnings("deprecation")
					String pass = passwordField.getText();
					
					pst = con.prepareStatement("select id,name,phone,department,username,password from studentinfo where username = ? and password = ?");
					pst.setString(1, uname);
					pst.setString(2, pass);
					
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true) {
						
						//name = rs.getString(1);
						//mbl = rs.getString(2);
						id1 = rs.getString(1);
						n1 = rs.getString(2);
						phn1 = rs.getString(3);
						dep1 = rs.getString(4);
						u1 = rs.getString(5);
						p1 = rs.getString(6);

						JOptionPane.showMessageDialog(null, "Logging in Successfully");
						
						Student p = new Student();
						p.setVisible(true);
						dispose();
					}
		
				else {
					JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					logusername.setText("");
					passwordField.setText("");
				}
			}
				catch(SQLException ex) {
					
				}
			}
		});
		btnNewButton.setBounds(180, 182, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(49, 64, 70, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(49, 117, 70, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 114, 178, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("sign in");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Student s = new Student();
				s.setVisible(true);
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(129, 216, 186, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage l = new HomePage();
				l.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(354, 217, 70, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student's login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 414, 39);
		contentPane.add(lblNewLabel_2);
	}
}
