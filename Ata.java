package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Ata extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEnterTheAmount;
	private JTextField textField_1;
	private JLabel lblPleaseSelectThe;
	private JButton button;
	private JButton btnCurrentAccount;
	private JToggleButton tglbtnNewToggleButton;
	private JToggleButton tglbtnCancel;
	private static float balance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ata frame = new Ata();
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
	private int p;
	private char a;                          //store the account type of sender
	private int select;                  //store the account type of receiver
	Ata(int p,char a)            
	{
		this();
		this.p=p;
		this.a=a;
	}
	
	boolean from(float balance,float amount)               //subtraction
	{
	
		if(amount>=balance)
		{
			JOptionPane.showMessageDialog(null,"account balance is less than or equal to the entered amount!");
			return false;
		}
		else
		{
			this.balance=balance-amount;
			return true;
		}
		
	}

	
	public Ata() {
		con=(Connection) Db.dbconnect();
		setResizable(false);
		setTitle("Transfer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("                                         Please Enter receiver Account Number");
		lblNewLabel.setBorder(new LineBorder(new Color(152, 251, 152), 3));
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel.setBounds(20, 11, 773, 46);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(152, 251, 152), 2));
		textField.setBackground(new Color(224, 255, 255));
		textField.setFont(new Font("Times New Roman", Font.BOLD, 30));
		textField.setBounds(255, 68, 298, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblEnterTheAmount = new JLabel("                                                              Enter the Amount");
		lblEnterTheAmount.setOpaque(true);
		lblEnterTheAmount.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblEnterTheAmount.setBorder(new LineBorder(new Color(152, 251, 152), 3));
		lblEnterTheAmount.setBackground(new Color(32, 178, 170));
		lblEnterTheAmount.setBounds(20, 125, 773, 46);
		contentPane.add(lblEnterTheAmount);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(new Color(152, 251, 152), 2));
		textField_1.setBackground(new Color(224, 255, 255));
		textField_1.setBounds(314, 182, 186, 46);
		contentPane.add(textField_1);
		
		lblPleaseSelectThe = new JLabel("                                          Please Select the Receiver Account Type");
		lblPleaseSelectThe.setOpaque(true);
		lblPleaseSelectThe.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblPleaseSelectThe.setBorder(null);
		lblPleaseSelectThe.setBackground(new Color(64, 224, 208));
		lblPleaseSelectThe.setBounds(20, 239, 773, 46);
		contentPane.add(lblPleaseSelectThe);
		
		button = new JButton("Saving Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select=1;
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 21));
		button.setBorder(new LineBorder(null));
		button.setBackground(new Color(0, 250, 154));
		button.setBounds(279, 296, 274, 44);
		contentPane.add(button);
		
		btnCurrentAccount = new JButton("Current Account");
		btnCurrentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select=2;
			}
		});
		btnCurrentAccount.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnCurrentAccount.setBorder(new LineBorder(null));
		btnCurrentAccount.setBackground(new Color(0, 250, 154));
		btnCurrentAccount.setBounds(279, 351, 274, 44);
		contentPane.add(btnCurrentAccount);
		
		tglbtnNewToggleButton = new JToggleButton("Confirm");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					long account_no=Long.parseLong(textField.getText());
					float amount=Float.parseFloat(textField_1.getText());
					Ata a1=new Ata();
					if(amount<=0)
					{
						JOptionPane.showMessageDialog(null, "Invalid amount, Please Enter it again!");
						textField_1.setText(null);
					}
					else
					{
						boolean condition=false;              //flag
						
						PreparedStatement pst,pst1,pst2,pst3;
						pst=(PreparedStatement) con.prepareStatement("select * from useraccount where pinno=?"); 
						pst.setInt(1, p);
						ResultSet r=pst.executeQuery();
						r.next();
						switch(a)                                 // makes changes in the useraccount or from account
						{
						case 'c':
							condition=a1.from(r.getFloat(5),amount);
							if(condition==true)
							{
								pst1=(PreparedStatement) con.prepareStatement("update useraccount set cbalance=? where pinno=? ");
								pst1.setFloat(1, balance);
								pst1.setInt(2, p);
							    pst1.executeUpdate();
							}
							break;
						case 's':
							condition=a1.from(r.getFloat(6),amount);
							if(condition==true)
							{
							pst1=(PreparedStatement) con.prepareStatement("update useraccount set sbalance=? where pinno=? ");
							pst1.setFloat(1, balance);
							pst1.setInt(2, p);
							pst1.executeUpdate();
							}
							break;
						}
						// Now making changes in to account or receiver account
						if(condition==true)
						{
						pst2=(PreparedStatement) con.prepareStatement("select * from useraccount where Accountno=?"); 
						pst2.setLong(1, account_no);
						ResultSet r1=pst2.executeQuery();
						r1.next();
						
					
						switch(select)
						{
						case 1:                             //saving account
							
							pst3=(PreparedStatement) con.prepareStatement("update useraccount set sbalance=? where Accountno=?");  
							float b=r1.getFloat(6)+amount;
							pst3.setFloat(1,b);
							pst3.setLong(2, account_no);
							pst3.executeUpdate();
							
							break;
						case 2:                                 //current account
							pst3=(PreparedStatement) con.prepareStatement("update useraccount set cbalance=? where Accountno=?");  
							float b1=r1.getFloat(5)+amount;
							pst3.setFloat(1,b1);
							pst3.setLong(2, account_no);
							pst3.executeUpdate();
						
							break;
						}
						JOptionPane.showMessageDialog(null, "Transfer is done successfully!");
						
					}
					}
				}
				catch(Exception e4)
				{
					JOptionPane.showMessageDialog(null, "Please Enter valid amount");
				}
				
			}
		});
		tglbtnNewToggleButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tglbtnNewToggleButton.setBackground(new Color(64, 224, 208));
		tglbtnNewToggleButton.setBounds(198, 406, 177, 44);
		contentPane.add(tglbtnNewToggleButton);
		
		tglbtnCancel = new JToggleButton("Cancel");
		tglbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		tglbtnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tglbtnCancel.setBackground(new Color(64, 224, 208));
		tglbtnCancel.setBounds(446, 406, 177, 44);
		contentPane.add(tglbtnCancel);
		
	}
}
		
