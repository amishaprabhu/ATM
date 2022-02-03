package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class operation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int p,c; // pin no , debit , saving account (s)
	Connection con=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					operation frame = new operation();
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
	
	
	private void addrecord(String accounttype,String operation,float amount,int p)
	{
	
		Long time=System.currentTimeMillis();
		java.sql.Date d=new java.sql.Date(time);
			con=(Connection) Db.dbconnect();
			try {
				PreparedStatement pst=con.prepareStatement("Insert into transfer(pinno,amount,date,operation,account) values(?,?,?,?,?)");
				pst.setInt(1,p);
				pst.setFloat(2, amount);
				pst.setDate(3,d);
				pst.setString(4,operation);
				pst.setString(5,accounttype);
				pst.executeUpdate();
			} catch (SQLException e) {
		
				System.out.println(e);
				e.printStackTrace();
			}
	}



	float debit(float b,Float amount)
	{
		if(amount ==0)
		{
			JOptionPane.showMessageDialog(null, "Invalid amount"+b);
			return 1;
		}
		else if(amount>=b)
		{
			JOptionPane.showMessageDialog(null,"amount is greater then or equal to account balance");
			return 1;
			
		}
		else
		{
			
			
			return  b-amount;
		}
		
	}
	float credit(float b,float amount)
	{
		if(amount <=0)
		{
			JOptionPane.showMessageDialog(null, "Invalid amount"+b);
			return 1;
		}
		else
		{
			 amount=b+amount;
			
			 return amount;
		}
		
	}
	         
	char a;
	
	operation(int p,int c,char a)
	{
		this();
		this.p=p;
		this.c=c;
		this.a=a;
	}
	
	
	public operation() {
		setTitle("Transaction");
		setResizable(false);
		con=(Connection) Db.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("                               Enter the amount");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setBounds(66, 47, 685, 66);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(240, 248, 255));
		textField.setFont(new Font("Times New Roman", Font.BOLD, 30));
		textField.setBounds(285, 149, 241, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("YES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ResultSet r;
				try
				{
					float result;
					PreparedStatement pst=con.prepareStatement("select * from useraccount where pinno=?");
					pst.setInt(1, p);
					r=pst.executeQuery();
					r.next();
					operation opt=new operation();
					switch(a)
					{
					case 's':             //saving account
				
						if(c==1)
						{
						result=opt.debit(r.getFloat(6),Float.parseFloat(textField.getText()));
						if(result!=1)
						{
							opt.addrecord("Saving Account","Debit Amount",Float.parseFloat(textField.getText()),p);
						}
						
						}
						else
						{
						result=opt.credit(r.getFloat(6),Float.parseFloat(textField.getText()));               //update useraccount set cbalance=3000 where pinno=12486;
						if(result!=1)
						{
							opt.addrecord("saving Account","Credit Amount",Float.parseFloat(textField.getText()),p);
						}
						
						}
						if(result!=1)
						{
							PreparedStatement pt=con.prepareStatement("update useraccount set sbalance=? where pinno=?");
							pt.setFloat(1, result);
							pt.setInt(2, p);
							pt.executeUpdate();
							JOptionPane.showMessageDialog(null,"Transaction is done successfully !");
						}
					
						
				        break;
					case 'c':             //Current account
						float r1;
						if(c==1)
						{
						r1=opt.debit(r.getFloat(5),Float.parseFloat(textField.getText()));       //(cbalance,amount)
							if(r1!=1)
							{
								opt.addrecord("Current Account","Debit Amount",Float.parseFloat(textField.getText()),p);
							}
						
						}
						else
						{
							r1=opt.credit(r.getFloat(5),Float.parseFloat(textField.getText()));	
							if(r1!=1)
							{
								opt.addrecord("Current Account","Credit Amount",Float.parseFloat(textField.getText()),p);
							}
						
						}
						if(r1!=1)
						{
							PreparedStatement pt1=con.prepareStatement("update useraccount set cbalance=? where pinno=?");
							pt1.setFloat(1, r1);
							pt1.setInt(2, p);
							pt1.executeUpdate();
							JOptionPane.showMessageDialog(null,"Transaction is done successfully !");
						}
					}
					                
				}catch(Exception e1)
				{
					System.out.println(e1);
				}
				
			}
		});
		btnNewButton.setBackground(new Color(0, 206, 209));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton.setBounds(487, 298, 211, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnNo.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNo.setBackground(new Color(0, 206, 209));
		btnNo.setBounds(487, 370, 211, 45);
		contentPane.add(btnNo);
		
		JToggleButton tglbtnBack = new JToggleButton("BACK");
		tglbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		tglbtnBack.setForeground(new Color(175, 238, 238));
		tglbtnBack.setFont(new Font("Times New Roman", Font.BOLD, 21));
		tglbtnBack.setBackground(new Color(0, 139, 139));
		tglbtnBack.setBounds(51, 370, 152, 46);
		contentPane.add(tglbtnBack);
	}
		
		
		
	}

