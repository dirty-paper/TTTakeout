package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import itf.ItfUserManager;
import model.BeanUser_info;
import takeoutstarter.TakeOututil;
import util.BaseException;
import util.DbException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmUsrReg extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	private JPasswordField textField_2;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrmUsrReg() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NewId = new JLabel("\u8D26\u6237\u540D\u79F0");
		NewId.setBounds(75, 40, 58, 15);
		contentPane.add(NewId);
		
		JLabel NewPwd = new JLabel("\u8F93\u5165\u5BC6\u7801");
		NewPwd.setBounds(75, 98, 58, 15);
		contentPane.add(NewPwd);
		
		JLabel SurePwd = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		SurePwd.setBounds(75, 161, 58, 15);
		contentPane.add(SurePwd);
		
		textField = new JTextField();
		textField.setBounds(192, 37, 162, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(192, 95, 162, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(192, 158, 162, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		cancel.setBounds(86, 212, 97, 23);
		contentPane.add(cancel);
		
		
		done.setBounds(241, 212, 97, 23);
		contentPane.add(done);
		this.cancel.addActionListener(this);
		this.done.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.cancel) {
			this.setVisible(false);
		}
		if (e.getSource() == this.done) {
			String id = textField.getText();
			String pwd1=new String(this.textField_1.getPassword());	
			String pwd2 = new String(this.textField_2.getPassword());
			try {
				TakeOututil.UserManager.UserRegister(id, pwd1, pwd2);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
