import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_4;

	public signup()
	{
		intialize();
		connect();
	}
	/**
	 * Launch the application.
	 */
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public void intialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(110, 145, 195, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(110, 206, 195, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(110, 286, 195, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(110, 338, 195, 20);
		contentPane.add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(447, 206, 195, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(38, 209, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(38, 289, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblNewLabel_2 = new JLabel("Department");
		lblNewLabel_2.setBounds(38, 341, 77, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(380, 148, 57, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(380, 209, 57, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id,name, phone,department, username, password;
				id = textField.getText();
				name = textField_1.getText();
				phone = textField_2.getText();
				department = textField_3.getText();
				username = textField_4.getText();
				password = passwordField.getText();
				
				try {
					pst = con.prepareStatement("insert into teacher(id,name,phone,department,username,password)values(?,?,?,?,?,?)");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3, phone);
					pst.setString(4, department);
					pst.setString(5, username);
					pst.setString(6, password);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Inserted Successfully!");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}

			}
				
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(302, 386, 98, 39);
		contentPane.add(btnNewButton);
		
		textField_4 = new JTextField();
		textField_4.setBounds(447, 145, 195, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("ID");
		lblNewLabel_5.setBounds(38, 145, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				loginpage l = new loginpage();
				l.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(634, 464, 77, 23);
		contentPane.add(btnNewButton_1);
	}
}
