package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.BusiManager;
import model.BeanUser_info;
import takeoutstarter.TakeOututil;

public class FrmBusiAdd extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrmBusiAdd() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NewId = new JLabel("\u5546\u6237id");
		NewId.setBounds(75, 40, 58, 15);
		contentPane.add(NewId);
		
		JLabel NewPwd = new JLabel("\u5546\u6237\u540D\u79F0");
		NewPwd.setBounds(75, 98, 58, 15);
		contentPane.add(NewPwd);
		
		textField = new JTextField();
		textField.setBounds(192, 37, 162, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 95, 162, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
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
			String name=textField_1.getText();	
			try {
				BusiManager b = new BusiManager();
				b.AddBusi(id, name);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}

}
