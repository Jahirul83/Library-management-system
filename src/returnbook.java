import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class returnbook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox comboBox;

	public returnbook() 
	{
		intialize();
		connect();
		//check();
		//category();
		
	}
	
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	String memberId,bookName;
	
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returnbook frame = new returnbook();
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
	
	private void intialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					//JComboBox comboBox = (JComboBox) arg0.getSource();
					String ID = textField.getText();
					pst = con.prepareStatement("select * from issuebook where memberid = ?");
					pst.setString(1, ID);
					ResultSet rs = pst.executeQuery();

					comboBox.removeAllItems();
					while (rs.next()) {
						//String bookName = rs.getString(2);
						String bookName = rs.getString("bookname");
						comboBox.addItem(bookName);
					}
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		textField.setBounds(188, 104, 192, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(188, 233, 192, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(118, 107, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblBook = new JLabel("Book");
		lblBook.setBounds(118, 171, 46, 14);
		contentPane.add(lblBook);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(118, 236, 46, 14);
		contentPane.add(lblDate);
		
		JButton btnNewButton = new JButton("confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id,bookname, date,issue,book;
				
				try {
						//JComboBox comboBox = (JComboBox) arg0.getSource();
						/*String ID = comboBox.getSelectedItem().toString();
						pst2 = con.prepareStatement("select issuedate from issuebook where bookname = ?");
						pst2.setString(1, ID);
						ResultSet rs2 = pst2.executeQuery();

						while(rs2.next())
						{
							bookName = rs2.getString(4);
							textField_3.setText(bookName);
						}*/
					
					id = textField.getText();
					bookname = comboBox.getSelectedItem().toString();
					date = textField_2.getText();
					//issue = textField_3.getText();
					pst = con.prepareStatement("insert into returnbook(id,bookname,date)values(?,?,?)");
					pst.setString(1, id);
					pst.setString(2, bookname);
					pst.setString(3, date);
					//pst.setString(4, bookName);
					pst.executeUpdate();
					
					
					JOptionPane.showMessageDialog(null, "Inserted Successfully!");
					/*pst2 = con.prepareStatement("delete from issuebook where memberid = ? ");
					pst2.setString(1, id);
					pst2.executeUpdate();*/
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}

			}
			
		});
		btnNewButton.setBounds(224, 338, 89, 23);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				/*try {
					//JComboBox comboBox = (JComboBox) arg0.getSource();
					String ID = comboBox.getSelectedItem().toString();
					pst = con.prepareStatement("select * from issuebook where bookname = ?");
					pst.setString(1, ID);
					ResultSet rs = pst.executeQuery();

					comboBox.removeAllItems();
					if (rs.next()) {
						//String bookName = rs.getString(2);
						String bookName = rs.getString("issuedate");
						textField_3.setText(bookName);
					}
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}*/
			}
		});
		comboBox.setBounds(188, 168, 192, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Return Book");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 11, 510, 54);
		contentPane.add(lblNewLabel_1);
	}
}
