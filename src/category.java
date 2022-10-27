import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class category extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	public category() 
	{
		intialize ();
		connect();
		tableupdate();
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
public void tableupdate(){
        
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost/librarydata", "root","");
            pst=con.prepareStatement("select * from category");
            rs=pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            System.out.println("ERROR");
        }
        
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					category frame = new category();
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
	private void intialize () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 105, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Active", "Deactive"}));
		comboBox.setBounds(10, 176, 134, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Category name");
		lblNewLabel.setBounds(10, 80, 98, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setBounds(10, 151, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				String st = comboBox.getSelectedItem().toString();
		         try{
		        	       
				            pst=(PreparedStatement) con.prepareStatement("insert into category(Category,Status) values (?,?)");
				            pst.setString(1,name);
				            pst.setString(2,st);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "record added");
				         }
		         catch(SQLException e1){
		         }
				         
		         tableupdate();
				}
					
		});
		btnNewButton.setBounds(10, 225, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String st = comboBox.getSelectedItem().toString();
		         try{
				            pst=(PreparedStatement) con.prepareStatement("update category set Status='"+st+"' where Category='"+name+"'");
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "record updated");
				            tableupdate(); 
	                    
				         }
				         catch(SQLException e1){
				           
				         }
			}
		});
		btnUpdate.setBounds(136, 225, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id =textField.getText();
				try {
					pst = con.prepareStatement("delete from category where Category = ? ");
					
					pst.setString(1, id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					tableupdate();
					textField.setText("");
					textField.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(10, 287, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel_2 = new JLabel("Category panel");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 614, 58);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 80, 366, 320);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Libpanel l = new Libpanel();
				l.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(136, 377, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
