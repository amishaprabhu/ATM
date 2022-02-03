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
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Teansfer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teansfer frame = new Teansfer();
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
	
	int p;
	Teansfer(int p)
	{
		this();
		this.p=p;
		
	}
	
	
	public Teansfer() {
		setResizable(false);
		setTitle("Transfer\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("                                               Transfer");
		lblNewLabel.setBorder(new LineBorder(new Color(144, 238, 144), 3));
		lblNewLabel.setBackground(new Color(72, 209, 204));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel.setBounds(10, 11, 784, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("                    Account To Account Transfer");
		lblNewLabel_1.setBackground(new Color(144, 238, 144));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(108, 81, 567, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("              Please Select the Account Type");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(175, 238, 238));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_2.setBounds(174, 163, 446, 52);
		contentPane.add(lblNewLabel_2);
		
		
		//current account option for sender
		JButton btnNewButton = new JButton("Current Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// a.setVisible();
				new Ata(p,'c').setVisible(true);
				
			}
		});
		
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnNewButton.setBorder(new LineBorder(null));
		btnNewButton.setBackground(new Color(154, 205, 50));
		btnNewButton.setBounds(250, 326, 268, 44);
		contentPane.add(btnNewButton);
		
		//saving account option for sender
		JButton btnNewButton_1 = new JButton("Saving Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				new Ata(p,'s').setVisible(true);
			}
		});
		
		
		
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 21));
		btnNewButton_1.setBorder(new LineBorder(null));
		btnNewButton_1.setBackground(new Color(154, 205, 50));
		btnNewButton_1.setBounds(250, 260, 274, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(64, 224, 208));
		lblNewLabel_4.setBounds(132, 137, 520, 282);
		contentPane.add(lblNewLabel_4);
		
		JToggleButton toggleButton = new JToggleButton("BACK");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		toggleButton.setForeground(new Color(175, 238, 238));
		toggleButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		toggleButton.setBackground(new Color(0, 139, 139));
		toggleButton.setBounds(3, 391, 119, 46);
		contentPane.add(toggleButton);
	}
	}
