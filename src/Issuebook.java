import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
//import com.toedter.calendar.JCalendar;
//import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;

public class Issuebook extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Issuebook frame = new Issuebook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Issuebook()
	{
		intialize();
		connect();
		tableupdate();
		memberId();
		memberName();
		
		//category();
	}
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	String memberName;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	SimpleDateFormat dateformat;
	SimpleDateFormat dateformat1;
	
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
public void tableupdate(){
        
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost/librarydata", "root","");
            pst=con.prepareStatement("select * from issuebook");
            rs=pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            System.out.println("ERROR");
        }
        
    }
	/*public void category()
    {
        try 
        {
            pst=con.prepareStatement("select * from requestbook");
            rs=pst.executeQuery();
            comboBox.removeAllItems();
            while(rs.next())
            {
				//String bookName = rs.getString(1);
				//bookBox.setSelectedItem(bookName);
            	//use this also
            	String bookName = rs.getString("id");
				comboBox.addItem(bookName);
            }    
        } 
        catch (SQLException ex) 
        {
        	ex.printStackTrace();
        }
     
     }*/
	
	public void memberId()
    {
        try 
        {
            pst = con.prepareStatement("SELECT * FROM requestbook");
            rs = pst.executeQuery();
            comboBox.removeAllItems();
            while(rs.next())
            {
				//String bookName = rs.getString(1);
				//bookBox.setSelectedItem(bookName);
            	//use this also
            	String bookName = rs.getString("id");
            	comboBox.addItem(bookName);
            }    
        } 
        catch (SQLException ex) 
        {
        	ex.printStackTrace();
        }
     
     }
	
	public void memberName()
    {
        try 
        {
            pst = con.prepareStatement("SELECT * FROM requestbook");
            rs = pst.executeQuery();
            comboBox_1.removeAllItems();
            while(rs.next())
            {
				//String bookName = rs.getString(1);
				//bookBox.setSelectedItem(bookName);
            	//use this also
            	String bookName = rs.getString("book");
            	comboBox_1.addItem(bookName);
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
	private void intialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					//JComboBox comboBox = (JComboBox) arg0.getSource();
					String ID = comboBox.getSelectedItem().toString();
					pst = con.prepareStatement("select * from requestbook where id = ?");
					pst.setString(1, ID);
					ResultSet rs = pst.executeQuery();

					comboBox_1.removeAllItems();
					while (rs.next()) {
						//String bookName = rs.getString(2);
						String bookName = rs.getString("book");
						comboBox_1.addItem(bookName);
					}
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
		comboBox.setBounds(82, 101, 131, 20);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(82, 159, 131, 20);
		contentPane.add(comboBox_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(275, 34, 282, 302);
		contentPane.add(scrollPane_1);
		
		scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Member ID");
		lblNewLabel.setBounds(7, 104, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book name");
		lblNewLabel_1.setBounds(7, 162, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Issue Date");
		lblNewLabel_2.setBounds(7, 217, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Returen Date");
		lblNewLabel_3.setBounds(7, 273, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Issue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String bookName,memberId,issueDate,returnDate;
					/*This is for accessing member ID from database,not from another JFrame*/
					memberId = comboBox.getSelectedItem().toString();
					pst2=con.prepareStatement("select * from requestbook where id = ?");
					pst2.setString(1, memberId);
					rs2=pst2.executeQuery();
		            if(rs2.next())
		            {
		             memberName=rs2.getString("name");
		            }
					
		            /*This is the normal processing for inserting data into database*/
					bookName = comboBox_1.getSelectedItem().toString();
					issueDate = dateformat.format(dateChooser.getDate());;
					returnDate = dateformat1.format(dateChooser_1.getDate());
					
					pst = con.prepareStatement("insert into issuebook(memberid,membername,bookname,issuedate,returndate)values(?,?,?,?,?)");
					pst.setString(1, memberId);
					pst.setString(2, memberName);
					pst.setString(3, bookName);
					pst.setString(4, issueDate);
					pst.setString(5, returnDate);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Permission Given Successfully");
					tableupdate();
					/*For removing the Combo Box index(item) which is selected to take input*/
					
					
					pst2 = con.prepareStatement("delete from requestbook where ID = ? ");
					pst2.setString(1, memberId);
					pst2.executeUpdate();
					//issueTableload();
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		btnNewButton.setBounds(10, 323, 109, 28);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Libpanel l = new Libpanel();
				l.setVisible(true);
				
			}
		});
		btnBack.setBounds(189, 326, 76, 28);
		contentPane.add(btnBack);
		
		dateChooser = new JDateChooser();
		dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		dateChooser.setBounds(82, 218, 130, 20);
		contentPane.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
		dateChooser_1.setBounds(82, 268, 130, 20);
		contentPane.add(dateChooser_1);
	}
}
