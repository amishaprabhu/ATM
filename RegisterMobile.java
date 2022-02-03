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

public class RegisterMobile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMobile frame = new RegisterMobile();
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
	
	Connection con=null;
	int p;
	RegisterMobile(int p) throws Exception
	{
		this();
		this.p=p;
	}
	
	
	public RegisterMobile() {
		setTitle("Register Mobile No.");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("                               Mobile No. Registeration");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 29));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(72, 209, 204));
		lblNewLabel.setBounds(51, 11, 722, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("          Enter your Mobile No:-");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(64, 224, 208));
		lblNewLabel_4.setBounds(200, 134, 425, 46);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBackground(new Color(175, 238, 238));
		textField.setFont(new Font("Times New Roman", Font.BOLD, 26));
		textField.setBounds(310, 223, 219, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnNewButton.setBackground(new Color(64, 224, 208));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton.setBounds(217, 310, 137, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                       //9975553126
				String s=textField.getText();
				try
				{
					if(s.length()!=10)
					{
						JOptionPane.showMessageDialog(null, "Invalid Mobile Number, Please Enter it again");
						textField.setText(null);
					}
					else
					{
						PreparedStatement pst=(PreparedStatement) con.prepareStatement("update useraccount set Mobileno=? where pinno=?");
						pst.setLong(1, Long.parseLong(s));
						pst.setInt(2, p);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Mobile No Registered Successfully");
						con.close();
					}
				}catch(Exception e4)
				{
					System.out.println(e4);
					JOptionPane.showMessageDialog(null,"Something went wrong!");
				}
				
			}
		});
		btnNewButton_1.setBackground(new Color(64, 224, 208));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton_1.setBounds(470, 310, 137, 43);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBorder(new LineBorder(new Color(175, 238, 238), 3));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(32, 178, 170));
		lblNewLabel_3.setBounds(125, 109, 563, 287);
		contentPane.add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 86, 756, 9);
		contentPane.add(separator);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 20));
		button.setBackground(new Color(64, 224, 208));
		button.setBounds(21, 401, 141, 39);
		contentPane.add(button);
	}
		
	}
