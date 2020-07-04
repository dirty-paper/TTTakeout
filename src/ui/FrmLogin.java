package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import control.SystemAdminManager;
import model.BeanAdministrator;
import takeoutstarter.TakeOututil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class FrmLogin extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnRegister = new JButton("用户注册");
	private JButton btnUsrLogin = new JButton("用户登录");
	private JButton btnAdminLogin = new JButton("管理员登录");
	
	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private FrmAdminMain dlgAdminMain = null;
	private JButton AdminReg = new JButton("\u7BA1\u7406\u5458\u6CE8\u518C");
	
	public FrmLogin() {
	
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(this.btnRegister);
		toolBar.add(this.btnUsrLogin);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelUser.setBounds(19, 8, 60, 15);
		workPane.add(labelUser);
		edtUserId.setBounds(89, 5, 126, 21);
		workPane.add(edtUserId);
		labelPwd.setBounds(19, 66, 60, 15);
		workPane.add(labelPwd);
		edtPwd.setBounds(89, 63, 126, 21);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
				btnAdminLogin.setBounds(60, 115, 230, 57);
				workPane.add(btnAdminLogin);
				AdminReg.setBounds(229, 32, 97, 23);
				
				workPane.add(AdminReg);
		
				this.btnAdminLogin.addActionListener(this);
		this.setSize(391, 252);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnUsrLogin.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.AdminReg.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdminLogin) {
			String userid=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {	
				BeanAdministrator.currentloginAdministrator = TakeOututil.AdminManager.Adminlogin(userid, pwd);
				if (BeanAdministrator.currentloginAdministrator!=null) {
					dlgAdminMain=new FrmAdminMain();
				dlgAdminMain.setVisible(true);
				this.setVisible(false);
				}
				
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnUsrLogin) {
			//System.exit(0);
		} else if(e.getSource()==this.btnRegister){
		FrmUsrReg dlg=new FrmUsrReg();
		dlg.setVisible(true);
		}
		else if (e.getSource()==this.AdminReg) {
			
		}
	}

	/**
	 * Create the frame.
	 */
	
}
