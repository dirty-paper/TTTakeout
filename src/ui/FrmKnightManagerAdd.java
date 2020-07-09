package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BeanKnight_info;
import model.BeanUser_info;
import takeoutstarter.TakeOututil;

public class FrmKnightManagerAdd extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtid;
	private JTextField edtName;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrmKnightManagerAdd(JDialog f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ∆Ô ÷id = new JLabel("\u9A91\u624Bid");
		∆Ô ÷id.setBounds(75, 40, 58, 15);
		contentPane.add(∆Ô ÷id);
		
		JLabel NewPwd = new JLabel("\u9A91\u624B\u59D3\u540D");
		NewPwd.setBounds(75, 98, 58, 15);
		contentPane.add(NewPwd);
		
		edtid = new JTextField();
		edtid.setBounds(192, 37, 162, 21);
		contentPane.add(edtid);
		edtid.setColumns(10);
		
		edtName = new JTextField();
		edtName.setBounds(192, 95, 162, 21);
		contentPane.add(edtName);
		edtName.setColumns(10);
		
		
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
			String id = edtid.getText();
			String name=new String(this.edtName.getText());	
			BeanKnight_info p = new BeanKnight_info();
			p.setKnight_id(id);
			p.setKnight_name(name);
			try {
				TakeOututil.knightManager.KnightAdd(p);
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
