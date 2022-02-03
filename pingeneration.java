package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class pingeneration extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pingeneration frame = new pingeneration();
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
	
	static int png()               // method to generate the pin no
	{
		Random r=new Random();
		String str="";
		int p;
		for(int i=1;i<=4;i++)
		{
			p=1+r.nextInt(9);
			str=str+p;
		}
		return Integer.parseInt(str);
	}

	Connection con=null;
	
	
	public pingeneration()
	{
		con=(Connection) Db.dbconnect();
		setTitle("pin generation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 37));
		passwordField.setBackground(new Color(224, 255, 255));
		passwordField.setBounds(337, 186, 169, 42);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("                                        Pin Generation");
		lblNewLabel.setBorder(new LineBorder(new Color(144, 238, 144), 3));
		lblNewLabel.setBackground(new Color(0, 206, 209));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 11, 785, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("   Enter your current pin no:-");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 128, 0), 3));
		lblNewLabel_2.setBackground(new Color(64, 224, 208));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(208, 103, 418, 52);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText(null);
			}
		});
		btnNewButton.setBackground(new Color(240, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(194, 271, 207, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pin=Integer.parseInt(passwordField.getText());
				int newpin;
				while(true)
				{
					newpin=png();
					if(pin!=newpin) break;
				}	
			//	System.out.println("newpin :"+newpin);
			try
			{
				PreparedStatement pst=con.prepareStatement("select * from useraccount where pinno=?");
				pst.setInt(1, pin);
				ResultSet r=pst.executeQuery();
				r.next();
				long mobileno=r.getLong(3);
				boolean b=Sendsms.sendsms("your new pin no is "+newpin+" generated at "+new Date().toLocaleString(), Long.toString(mobileno));
				if(b==true)
				{
					JOptionPane.showMessageDialog(null, "message is successfully sended");
					pinchange.newpin(newpin);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Some thing went wrong, try again");
				}
			}catch(Exception e1)
			{
				System.out.println(e1);
			}
				
				
			}
		});
		btnNewButton_1.setBackground(new Color(240, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(450, 270, 189, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBorder(new LineBorder(new Color(152, 251, 152), 3));
		lblNewLabel_3.setBackground(new Color(72, 209, 204));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBounds(161, 80, 502, 295);
		contentPane.add(lblNewLabel_3);
		
		JToggleButton toggleButton = new JToggleButton("BACK");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		toggleButton.setForeground(new Color(175, 238, 238));
		toggleButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		toggleButton.setBackground(new Color(0, 139, 139));
		toggleButton.setBounds(29, 386, 161, 46);
		contentPane.add(toggleButton);
	}
		
		
		
	}
