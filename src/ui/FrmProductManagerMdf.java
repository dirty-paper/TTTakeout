package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ProductManager;
import model.BeanBusi_info;
import model.BeanBusi_product;
import model.BeanForProduct;
import util.BaseException;

public class FrmProductManagerMdf extends JDialog implements ActionListener{
	private BeanForProduct pub = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("名称");
	private JLabel labelLevel = new JLabel("价格");
	private JLabel labelremian = new JLabel("余量");
	private JTextField edtremain = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	private JTextField edtprice = new JTextField(20);
	public FrmProductManagerMdf(JDialog f,String s,Boolean b,BeanForProduct p) {
		super(f,s,b);
		this.pub=p;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName,BorderLayout.NORTH);
		this.edtName.setText(p.getProduct_name());
		workPane.add(labelLevel);
		workPane.add(edtprice,BorderLayout.CENTER);
		this.edtprice.setText(String.valueOf(p.getProduct_price()));
		this.edtremain.setText(String.valueOf(p.getProduct_remain()));
		workPane.add(labelremian);
		workPane.add(edtremain,BorderLayout.SOUTH);
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
				FrmProductManagerMdf.this.pub=null;
			}
		});
		this.setSize(265, 250);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.pub=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			pub.setProduct_name(this.edtName.getText());
			try {
				pub.setProduct_id(pub.getProduct_id());
				pub.setProduct_price(Double.valueOf(this.edtprice.getText()));
				pub.setProduct_remain(Integer.valueOf(this.edtremain.getText()));
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				(new ProductManager()).ProductMdf(pub);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	public BeanForProduct getPub() {
		return pub;
	}

}
