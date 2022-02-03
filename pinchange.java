package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
@SuppressWarnings("serial")

public class pinchange extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pinchange frame = new pinchange();
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
	
	private static int pin;
	static void newpin(int np)
	{
			pin=np;
	}
	int p;
	Connection con=null;
	pinchange(int p)
	{
		this();
		this.p=p;
	}
	
	
	public pinchange() {
		setTitle("Change PIN No");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("                               Change Pin No");
		lblNewLabel.setBorder(new LineBorder(new Color(102, 205, 170), 4));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 29));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setBounds(83, 36, 639, 71);
		contentPane.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(32, 118, 745, 13);
		contentPane.add(separator_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textField.setBackground(new Color(224, 255, 255));
		textField.setBounds(337, 264, 149, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter The New Pin No:-");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(64, 224, 208));
		lblNewLabel_1.setBounds(242, 173, 349, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBorder(new LineBorder(new Color(102, 205, 170), 3));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(32, 178, 170));
		lblNewLabel_2.setBounds(178, 142, 474, 185);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnNewButton.setBackground(new Color(64, 224, 208));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(602, 391, 141, 39);
		contentPane.add(btnNewButton);
		
		JButton btnSubmit = new JButton("Enter");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(textField.getText())==pin)
				{
					try
					{
						PreparedStatement pst=(PreparedStatement) con.prepareStatement("update useraccount set pinno=? where pinno=?");
						pst.setInt(1, Integer.parseInt(textField.getText()));
						pst.setInt(2, p);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "pin no is change successfully ");
						textField.setText(null);
						con.close();
					}catch(Exception e4)
					{
						JOptionPane.showMessageDialog(null, "Some problem is occured, Please renter the pin no ");
						textField.setText(null);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter the received pin number");
				}
			}
		});
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSubmit.setBackground(new Color(64, 224, 208));
		btnSubmit.setBounds(317, 389, 141, 39);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBack.setBackground(new Color(64, 224, 208));
		btnBack.setBounds(43, 391, 141, 39);
		contentPane.add(btnBack);
	}
		
	}

