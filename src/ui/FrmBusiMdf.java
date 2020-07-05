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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import control.BusiManager;
import model.BeanBusi_info;
import util.BaseException;
import util.BusinessException;

public class FrmBusiMdf extends JFrame implements ActionListener{
	private BeanBusi_info pub = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("名称");
	private JLabel labelLevel = new JLabel("星级");
	
	private JTextField edtName = new JTextField(20);
	private JTextField edtLevel = new JTextField(20);
	public FrmBusiMdf(BeanBusi_info p) {
		super();
		this.pub=p;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(edtName);
		workPane.add(labelName);
		this.edtName.setText(p.getBusi_name());
		workPane.add(edtLevel);
		workPane.add(labelLevel);
		this.edtLevel.setText(String.valueOf(p.getBusi_level()));
		workPane.add(edtLevel);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		// 灞忓箷灞呬腑鏄剧ず
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmBusiMdf.this.pub=null;
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
			pub.setBusi_name(this.edtName.getText());
			try {
				pub.setBusi_level(Integer.valueOf(this.edtLevel.getText()));
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				(new BusiManager()).MdfBusi(pub);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	public BeanBusi_info getPub() {
		return pub;
	}
}
