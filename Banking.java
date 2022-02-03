package src.Atm1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class Banking extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banking frame = new Banking();
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
	
	int p;                         //variable to store pinno
	Banking(int p)                       // assign the pinno 
	{
		this();                              //it will call the Banking() 
		this.p=p;                           // pinno will be store inside the p
	}
	
	public Banking() {
		setResizable(false);
		setTitle("Banking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 536);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("                                    Select your choice");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel.setBounds(44, 23, 725, 60);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 105, 745, 2);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Debit amount");
		btnNewButton.setBackground(new Color(0, 206, 209));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice c=new choice(p,1);                  // passing the pinno and choice(which operation to perform) as well
				c.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnNewButton.setBounds(232, 118, 374, 60);
		contentPane.add(btnNewButton);
		
		JButton btnSavingAccount = new JButton("Credite Amount");
		btnSavingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice c=new choice(p,2);                  // passing the pinno and choice(which operation to perform) as well
				c.setVisible(true);
			}
		});
		btnSavingAccount.setBackground(new Color(0, 206, 209));
		btnSavingAccount.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnSavingAccount.setBounds(232, 187, 374, 60);
		contentPane.add(btnSavingAccount);
		
		JButton btnChangePinNo = new JButton("Check balance");
		btnChangePinNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice c=new choice(p,3);                // passing the pinno and choice(which operation to perform) as well
				c.setVisible(true);
			}
		});
		btnChangePinNo.setBackground(new Color(0, 206, 209));
		btnChangePinNo.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnChangePinNo.setBounds(232, 253, 374, 60);
		contentPane.add(btnChangePinNo);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Back");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		tglbtnNewToggleButton.setForeground(new Color(175, 238, 238));
		tglbtnNewToggleButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		tglbtnNewToggleButton.setBackground(new Color(0, 139, 139));
		tglbtnNewToggleButton.setBounds(27, 395, 152, 46);
		contentPane.add(tglbtnNewToggleButton);
		
		JButton button = new JButton("Change Pin No");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pinchange pin=new pinchange(p);                             //open the Pin change page
				pin.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 26));
		button.setBackground(new Color(0, 206, 209));
		button.setBounds(232, 324, 374, 60);
		contentPane.add(button);
	}
	}

