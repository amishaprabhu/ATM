package src.Atm1;

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
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class options extends JFrame {

	private JPanel contentPane;
	 int p;         //variable for to store pinno
	 
	/**
	 * Launch the application.
	 */
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					options frame = new options();
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
	
	options(int p)           //storing the pin no inside the pinno
	{
		   this();
			this.p=p;
			
	}
	
	
	public options() {
		setTitle("My ATM");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("                                     Select your choice");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(32, 178, 170));
		lblNewLabel.setBounds(83, 36, 645, 60);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Banking");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Banking b=new Banking(p);             // opening the banking page by passing pinno
				b.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(72, 209, 204));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton.setBounds(83, 183, 284, 60);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(65, 137, 707, 23);
		contentPane.add(separator);
		
		JButton btnPinGeneration = new JButton("Pin Generation");
		btnPinGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pingeneration().setVisible(true);
			}
		});
		btnPinGeneration.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnPinGeneration.setBackground(new Color(72, 209, 204));
		btnPinGeneration.setBounds(83, 284, 284, 60);
		contentPane.add(btnPinGeneration);
		
		JButton btnMiniStatement = new JButton("Mini Statement");
		btnMiniStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MiniStatement(p).setVisible(true);
			}
		});
		btnMiniStatement.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnMiniStatement.setBackground(new Color(72, 209, 204));
		btnMiniStatement.setBounds(447, 183, 284, 60);
		contentPane.add(btnMiniStatement);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Teansfer(p).setVisible(true);             //open the transfer page by passing pinno
				}
		});
		btnTransfer.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnTransfer.setBackground(new Color(72, 209, 204));
		btnTransfer.setBounds(444, 284, 284, 60);
		contentPane.add(btnTransfer);
		
		JButton btnRegisterMobileNo = new JButton("Register mobile no");
		btnRegisterMobileNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new RegisterMobile(p).setVisible(true);
				} catch (Exception e1) {
					
					e1.printStackTrace();
					System.out.println(e1);
				}         //it will open the registered mobile no by passing pinno
				
			}
		});
		btnRegisterMobileNo.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnRegisterMobileNo.setBackground(new Color(72, 209, 204));
		btnRegisterMobileNo.setBounds(273, 367, 284, 60);
		contentPane.add(btnRegisterMobileNo);
	}
		
		
	}
