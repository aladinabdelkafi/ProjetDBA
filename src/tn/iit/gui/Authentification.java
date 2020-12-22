package tn.iit.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import tn.iit.connection.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;

public class Authentification extends JFrame {

	public static JPanel contentPane;
	private JTextField textField;
	private JTextField username;
	private JTextField mdp;
	private JTextField orcl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
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
	public Authentification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setForeground(new Color(0, 0, 102));
		lblLogin.setIcon(new ImageIcon("res\\userr.png"));
		lblLogin.setBounds(70, 115, 69, 26);
		contentPane.add(lblLogin);
		
		JLabel lblMdp = new JLabel("mdp");
		lblMdp.setIcon(new ImageIcon("res\\pwd.png"));
		lblMdp.setForeground(new Color(0, 0, 102));
		lblMdp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMdp.setBounds(70, 152, 84, 25);
		
		contentPane.add(lblMdp);
	
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(username.getText().equals("")){
				JOptionPane.showMessageDialog(contentPane, "Please fill all the fields!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
				
			}
				if(mdp.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please fill all the fields!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
		}
				String usernam = username.getText();
				String password = new String(mdp.getText());
				String orcll = new String(orcl.getText());
				
				DBConnection oraConn = new DBConnection(usernam, password,orcll);

				try {
					oraConn.connection(contentPane);
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(contentPane, e, "Error",
							JOptionPane.ERROR_MESSAGE);
					
				}
				System.out.println("aaaa"+oraConn.getLogin()+"   "+oraConn.getMdp());
				
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							new Interface(oraConn).setVisible(true);
						} catch (BadLocationException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});	
			}
			
			
		});
		btnValider.setBounds(175, 211, 97, 25);
		contentPane.add(btnValider);
		username = new JTextField();
		username.setBounds(149, 106, 150, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		mdp = new JTextField();
		mdp.setBounds(145, 156, 154, 20);
		contentPane.add(mdp);
		mdp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("administration base de donn\u00E9es");
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(73, 23, 289, 45);
		contentPane.add(lblNewLabel);
		
		orcl = new JTextField();
		orcl.setBounds(149, 79, 150, 20);
		JLabel lblNewLabel_1 = new JLabel("Orcl");
		lblNewLabel_1.setForeground(new Color(0, 0, 102));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(62, 79, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		contentPane.add(orcl);
		orcl.setColumns(10);
		
		
	}
}
