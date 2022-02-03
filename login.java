package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class login {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con=null;                                                                      //Connection variable   
	
	private void initialize() {
		con=Db.dbconnect();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 523);
		frame.setResizable(false);
		frame.setTitle("My ATM");
		frame.getContentPane().setBackground(new Color(0, 139, 139));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("                Enter your Pin No and Account No");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(64, 224, 208));
		lblNewLabel.setBounds(137, 84, 552, 64);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pin no:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel_1.setBounds(215, 216, 150, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblAccountNo = new JLabel("Account No:");
		lblAccountNo.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblAccountNo.setBounds(215, 286, 150, 36);
		frame.getContentPane().add(lblAccountNo);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		passwordField.setBackground(new Color(175, 238, 238));
		passwordField.setBounds(434, 218, 167, 36);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBackground(new Color(173, 216, 230));
		textField.setBounds(434, 286, 167, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText(null);
				textField.setText(null);
			}
		});
		btnNewButton.setBackground(new Color(175, 238, 238));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnNewButton.setBounds(226, 369, 139, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
																	// JDBC
					PreparedStatement pst=con.prepareStatement("select * from useraccount where pinno=? and Accountno=? ");
				
					pst.setInt(1,Integer.parseInt(passwordField.getText()) );
					pst.setLong(2,Long.parseLong(textField.getText()) );

					ResultSet r=pst.executeQuery();                    //Data table (useraccount)      and Record
					r.next();                               
					r.getLong(4);
					r.getInt(1);
					
					options o=new options(r.getInt(1));
					o.setVisible(true);
					
					
				} catch (Exception e1) {
				
					  System.out.println(e1);                                                           
					JOptionPane.showMessageDialog(null,"Wrong pin or account no");
					
				}
				
	
			}
		});
		btnEnter.setBackground(new Color(175, 238, 238));
		btnEnter.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnEnter.setBounds(434, 369, 139, 44);
		frame.getContentPane().add(btnEnter);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 67, 770, 11);
		frame.getContentPane().add(separator);
		
		
		
	}

}
