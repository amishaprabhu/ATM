package src.Atm1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;



public class choice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					choice frame = new choice();
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
	
	Connection con=null;                //Connection class variable for JDBC
	int p;                                 //store the pin no
	int c;                                   //Identify the operation
	choice(int p,int c)               //user define constructor
	{
		this();
		this.p=p;
		this.c=c;
	}
	
	public choice()
	{
		setTitle("Select Account");
		setResizable(false);
		con=(Connection) Db.dbconnect();       //loading and registering Driver
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("                                     Select the account");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setBounds(34, 25, 735, 68);
		contentPane.add(lblNewLabel);
		
		JButton btnSavingAccount = new JButton("Saving Account");
		btnSavingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(c==1 || c==2)                                //It will open the operation page for to perform debit and credit operation
			{
				operation op=new operation(p,c,'s');       // pinno, operation choice and account type send operation class
				op.setVisible(true);
			}
			else                                         //it will open the balance page
			{
				try
				{
					PreparedStatement pst=con.prepareStatement("select * from useraccount where pinno=? ");
				
					pst.setInt(1,p);
					ResultSet r=pst.executeQuery();
					r.next();
					r.getInt(1);
					
					balance b=new balance();
					b.acc.setText("         Saving account balance");       // assigning text to the other label from different class
					b.bal.setText("       "+Float.toString(r.getFloat(6)));
					b.setVisible(true);
					
				}catch(Exception e2) {
					System.out.println(e2);
				}
				
				
			}
		}
		});
		btnSavingAccount.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnSavingAccount.setBackground(new Color(0, 206, 209));
		btnSavingAccount.setBounds(222, 162, 374, 60);
		contentPane.add(btnSavingAccount);
		
		JButton btnCurrentAccount = new JButton("Current Account");
		btnCurrentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c==1 || c==2)
				{
					operation op=new operation(p,c,'c');
					op.setVisible(true);
				}
				else
				{
					try
					{
						PreparedStatement pst=con.prepareStatement("select * from useraccount where pinno=? ");
					
						pst.setInt(1,p);
						ResultSet r=pst.executeQuery();
						r.next();
						r.getInt(1);
						
						balance b=new balance();
						b.acc.setText("         Current account balance");
						b.bal.setText("       "+Float.toString(r.getFloat(5)));
						b.setVisible(true);
						
					}catch(Exception e2) {
						System.out.println(e2);
					}
						
				
				}
			}
		});
		btnCurrentAccount.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnCurrentAccount.setBackground(new Color(0, 206, 209));
		btnCurrentAccount.setBounds(222, 274, 374, 60);
		contentPane.add(btnCurrentAccount);
		
		JToggleButton toggleButton = new JToggleButton("Back");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		toggleButton.setForeground(new Color(175, 238, 238));
		toggleButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		toggleButton.setBackground(new Color(0, 139, 139));
		toggleButton.setBounds(34, 381, 152, 46);
		contentPane.add(toggleButton);
	}
	}

