import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BookEntry extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox bookBox;

	
	private JTextField textField_4;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookEntry frame = new BookEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public BookEntry() {
		intialize();
		connect();
		category();
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
	public void category()
    {
        try 
        {
            pst=con.prepareStatement("select * from category");
            rs=pst.executeQuery();
            bookBox.removeAllItems();
            while(rs.next())
            {
				String bookName = rs.getString(2);
				//bookBox.setSelectedItem(bookName);
            	//use this also
            	//String bookName = rs.getString("Book");
				bookBox.addItem(bookName);
            }    
        } 
        catch (SQLException ex) 
        {
        	ex.printStackTrace();
        }
     
     }
	/**
	 * Create the frame.
	 */
	private void intialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(170, 44, 203, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book");
		lblNewLabel.setBounds(81, 47, 65, 17);
		contentPane.add(lblNewLabel);
		
		bookBox = new JComboBox();
		bookBox.setBounds(170, 113, 203, 20);
		contentPane.add(bookBox);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setBounds(81, 116, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(170, 178, 203, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 232, 203, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setBounds(81, 181, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Publisher");
		lblNewLabel_3.setBounds(81, 235, 65, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(170, 296, 203, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("total");
		lblNewLabel_4.setBounds(81, 299, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("entry");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String book,category, author,publisher, total, date;
				book = textField.getText();
				category = bookBox.getSelectedItem().toString();
				author = textField_1.getText();
				publisher = textField_2.getText();
				total = textField_3.getText();
				date = textField_4.getText();
				
				try {
					pst = con.prepareStatement("insert into entry(book,category,author,publisher,total,date)values(?,?,?,?,?,?)");
					pst.setString(1, book);
					pst.setString(2, category);
					pst.setString(3, author);
					pst.setString(4, publisher);
					pst.setInt(5, Integer.parseInt(total));
					pst.setString(6, date);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Inserted Successfully!");
					textField.setText("");
				    bookBox.getSelectedItem().toString();
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(232, 380, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Libpanel b = new Libpanel();
				b.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(483, 380, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Date");
		lblNewLabel_4_1.setBounds(81, 348, 65, 14);
		contentPane.add(lblNewLabel_4_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(170, 349, 203, 20);
		contentPane.add(textField_4);
	}
}
