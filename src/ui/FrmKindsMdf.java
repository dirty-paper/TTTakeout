package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.BusiManager;
import control.KindsManager;
import model.BeanBusi_info;
import model.BeanBusi_kinds;
import util.BaseException;

public class FrmKindsMdf extends JFrame implements ActionListener{
	private BeanBusi_kinds pub = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("\u7C7B\u578B\u540D\u79F0");
	
	private JTextField edtName = new JTextField(20);
	public FrmKindsMdf(BeanBusi_kinds p) {
		super();
		this.pub=p;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		edtName.setBounds(89, 53, 126, 21);
		workPane.add(edtName);
		labelName.setBounds(25, 56, 54, 15);
		workPane.add(labelName);
		this.edtName.setText(p.getKinds_name());
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmKindsMdf.this.pub=null;
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.pub=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			pub.setKinds_name(this.edtName.getText());
			
			try {
				(new KindsManager()).mdfKinds(pub);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	public BeanBusi_kinds getPub() {
		return pub;
	}
}
