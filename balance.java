package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;



public class balance extends JFrame {

	private JPanel contentPane;
	public JLabel bal,acc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					balance frame = new balance();
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
	public balance() {
		setTitle("Account Balance");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 481);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		acc = new JLabel("");
		
		acc.setOpaque(true);
		acc.setBackground(new Color(64, 224, 208));
		acc.setFont(new Font("Times New Roman", Font.BOLD, 25));
		acc.setBounds(219, 127, 387, 46);
		contentPane.add(acc);
		
		JLabel lblNewLabel_3 = new JLabel("Your account balance is");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_3.setBounds(265, 216, 300, 37);
		contentPane.add(lblNewLabel_3);
		
		bal = new JLabel("");
		bal.setOpaque(true);
		bal.setBackground(new Color(175, 238, 238));
		bal.setFont(new Font("Times New Roman", Font.BOLD, 23));
		bal.setBounds(433, 308, 198, 37);
		contentPane.add(bal);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(32, 178, 170));
		lblNewLabel_5.setBounds(148, 108, 508, 258);
		contentPane.add(lblNewLabel_5);
		
		JToggleButton toggleButton = new JToggleButton("Back");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		toggleButton.setForeground(new Color(175, 238, 238));
		toggleButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		toggleButton.setBackground(new Color(0, 139, 139));
		toggleButton.setBounds(31, 377, 152, 46);
		contentPane.add(toggleButton);
	}
	}
