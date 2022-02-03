package src.Atm1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")

public class MiniStatement extends JFrame {

	private JPanel contentPane;
	private final JPanel print = new JPanel();
	private int p;
	private JTextArea txtrStatementAmountDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiniStatement frame = new MiniStatement();
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
	ResultSet r;
	MiniStatement(int p)
	{
		this();
		this.p=p;
	}
	 
	void add(String s)
	{
		
		txtrStatementAmountDate.setText(txtrStatementAmountDate.getText()+s);
	}
	@SuppressWarnings("unchecked")
	
	public MiniStatement() {
		con=(Connection) Db.dbconnect();
		setTitle("Mini Statement\r\n");
		setResizable(false);setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		print.setBackground(new Color(72, 209, 204));
		print.setBounds(90, 11, 536, 413);
		contentPane.add(print);
		print.setLayout(null);
		
		txtrStatementAmountDate = new JTextArea();
		txtrStatementAmountDate.setBackground(new Color(255, 245, 238));
		txtrStatementAmountDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtrStatementAmountDate.setBounds(10, 11, 517, 391);
		print.add(txtrStatementAmountDate);
		
		JToggleButton toggleButton = new JToggleButton("BACK");
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		toggleButton.setForeground(new Color(175, 238, 238));
		toggleButton.setFont(new Font("Times New Roman", Font.BOLD, 21));
		toggleButton.setBackground(new Color(47, 79, 79));
		toggleButton.setBounds(24, 434, 152, 46);
		contentPane.add(toggleButton);
		
		JToggleButton tglbtnPrint = new JToggleButton("PRINT");
		tglbtnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtrStatementAmountDate.print();
				} catch (PrinterException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
				
			}

		});
		tglbtnPrint.setForeground(new Color(175, 238, 238));
		tglbtnPrint.setFont(new Font("Times New Roman", Font.BOLD, 21));
		tglbtnPrint.setBackground(new Color(47, 79, 79));
		tglbtnPrint.setBounds(563, 434, 152, 46);
		contentPane.add(tglbtnPrint);
		
		JToggleButton tglbtnAdd = new JToggleButton("View");
		tglbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int count=0;
					PreparedStatement pst=(PreparedStatement) con.prepareStatement("select pinno,amount,date,operation,account from transfer order by date  desc");
					r=pst.executeQuery();
					
					txtrStatementAmountDate.setText("*****************************************************************************\n");
					txtrStatementAmountDate.setText(txtrStatementAmountDate.getText()+"\t\t  Mini Statement\n");
					txtrStatementAmountDate.setText(txtrStatementAmountDate.getText()+"*****************************************************************************\n");
					txtrStatementAmountDate.setText(txtrStatementAmountDate.getText()+"Statement\t\t\t      amount\t      Date\n");
					txtrStatementAmountDate.setText(txtrStatementAmountDate.getText()+"***************************************************************************\n");
					while(r.next() && count<5)
					{
						int pin_no=r.getInt(1);
					
						
						if(pin_no==p)
						{
							String account=r.getString(5);
							String operation=r.getString(4);
							String amount=String.valueOf(r.getFloat(2));
							txtrStatementAmountDate.setText(txtrStatementAmountDate.getText()+operation+"ed amount from "+account+"     "+amount+"        "+r.getDate(3)+"\n\n\n");
						    
						    System.out.println(operation+" amount from "+account+"                   "+amount+"         \t\t\t"+r.getDate(3)+"\n");
						   
						    				
						    count++;
						}
					}
					
					
				} catch (Exception e1) {
					
					System.out.println(e1);
					e1.printStackTrace();
				}
				
				
			}
		});
		tglbtnAdd.setForeground(new Color(175, 238, 238));
		tglbtnAdd.setFont(new Font("Times New Roman", Font.BOLD, 21));
		tglbtnAdd.setBackground(new Color(47, 79, 79));
		tglbtnAdd.setBounds(292, 435, 152, 46);
		contentPane.add(tglbtnAdd);
		
}
}
