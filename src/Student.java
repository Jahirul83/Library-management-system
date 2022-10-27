import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Student extends JFrame {

	private JPanel contentPane;
	private JPanel panelmenu;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLayeredPane layeredPane;
	private JButton btnNewButton;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JLabel lblDepartment;
	private JLabel lblDepartment_1;
	private JLabel lblUsername;
	private JComboBox comre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Student()
	{
		intialize();
		connect();
		check();
		category();
	}
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
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
	public void category()
    {
        try 
        {
            pst=con.prepareStatement("select * from entry");
            rs=pst.executeQuery();
            comre.removeAllItems();
            while(rs.next())
            {
				String bookName = rs.getString(2);
				//bookBox.setSelectedItem(bookName);
            	//use this also
            	//String bookName = rs.getString("Book");
				comre.addItem(bookName);
            }    
        } 
        catch (SQLException ex) 
        {
        	ex.printStackTrace();
        }
     
     }

	private void check() {
		loginpage s = new loginpage();
		textField.setText(loginpage.n1);
		textField_1.setText(loginpage.id1);
		textField_2.setText(loginpage.dep1);
		textField_3.setText(loginpage.phn1);
		textField_4.setText(loginpage.u1);
	}
	/**
	 * Create the frame.
	 */
	private void intialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelmenu = new JPanel();
		panelmenu.setBackground(Color.GREEN);
		panelmenu.setBounds(0, 0, 165, 561);
		contentPane.add(panelmenu);
		panelmenu.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Request book");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_3);
			}
		});
		btnNewButton_2.setBounds(10, 168, 122, 23);
		panelmenu.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_4);
			}
		});
		btnNewButton_3.setBounds(25, 245, 89, 23);
		panelmenu.add(btnNewButton_3);
		
		btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage h = new HomePage();
				h.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(25, 321, 89, 23);
		panelmenu.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBounds(165, 0, 619, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 619, 561);
		panel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		layeredPane.add(panel_2, "name_26742833914300");
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(new Color(255, 255, 255));
		textField.setBorder(null);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setOpaque(false);
		textField.setEditable(false);
		textField.setBounds(84, 142, 186, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setBorder(null);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setOpaque(false);
		textField_1.setColumns(10);
		textField_1.setBounds(85, 233, 184, 20);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setBorder(null);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setOpaque(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(88, 321, 181, 20);
		panel_2.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(255, 255, 255));
		textField_3.setBorder(null);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setOpaque(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(364, 142, 186, 20);
		panel_2.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setForeground(new Color(255, 255, 255));
		textField_4.setBorder(null);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setOpaque(false);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(366, 225, 180, 20);
		panel_2.add(textField_4);
		
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(88, 110, 46, 14);
		panel_2.add(lblNewLabel);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(86, 204, 46, 14);
		panel_2.add(lblId);
		
		lblDepartment = new JLabel("Phone");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDepartment.setBounds(89, 292, 46, 14);
		panel_2.add(lblDepartment);
		
		lblDepartment_1 = new JLabel("Department");
		lblDepartment_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDepartment_1.setBounds(367, 113, 94, 14);
		panel_2.add(lblDepartment_1);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(368, 195, 93, 14);
		panel_2.add(lblUsername);
		
		JLabel lblNewLabel_1 = new JLabel("Student's Panel");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 599, 88);
		panel_2.add(lblNewLabel_1);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		layeredPane.add(panel_3, "name_26832013665600");
		panel_3.setLayout(null);
		
		comre = new JComboBox();
		comre.setBounds(214, 174, 202, 23);
		panel_3.add(comre);
		
		JButton btnNewButton_1 = new JButton("Request");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id,name, book;
				id = loginpage.id1;
				name = loginpage.n1;
				book = comre.getSelectedItem().toString();
				
				try {
					pst = con.prepareStatement("insert into requestbook(id,name,book)values(?,?,?)");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3, book);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Inserted Successfully!");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(265, 257, 118, 23);
		panel_3.add(btnNewButton_1);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 0, 128));
		layeredPane.add(panel_4, "name_26852824048300");
	}
}
