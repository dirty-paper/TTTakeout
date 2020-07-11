package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BeanBusi_discount;
import model.BeanBusi_info;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmDiscountManagerMdf extends JDialog implements ActionListener{
	BeanBusi_discount disc = null;
	private JPanel contentPane;
	private JTextField edtValue;
	private JTextField edtNeed;
	private JTextField edtddl;
	private String id;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	public FrmDiscountManagerMdf(JDialog f,String s,boolean b,BeanBusi_discount p) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel Newvalue = new JLabel("\u4F18\u60E0\u5238\u4EF7\u503C");
		Newvalue.setBounds(60, 77, 84, 15);
		contentPane.add(Newvalue);
		JLabel newneed = new JLabel("所需订单数");
		newneed.setBounds(60, 123, 73, 15);
		contentPane.add(newneed);
		
		
		JLabel Newddl = new JLabel("使用截止日期");
		Newddl.setBounds(60, 161, 73, 15);
		contentPane.add(Newddl);
		
		edtValue = new JTextField();
		edtValue.setBounds(192, 74, 162, 21);
		contentPane.add(edtValue);
		edtValue.setColumns(10);
		
		edtNeed = new JTextField();
		edtNeed.setBounds(192, 120, 162, 21);
		contentPane.add(edtNeed);
		edtNeed.setColumns(10);
		
		edtddl = new JTextField();
		edtddl.setBounds(192, 158, 162, 21);
		contentPane.add(edtddl);
		edtddl.setColumns(10);
		cancel.setBounds(239, 214, 97, 23);
		contentPane.add(cancel);
		
		
		done.setBounds(76, 214, 97, 23);
		contentPane.add(done);
		this.cancel.addActionListener(this);
		this.done.addActionListener(this);
		this.edtValue.setText(String.valueOf(p.getDiscount_value()));
		this.edtNeed.setText(String.valueOf(p.getDiscount_collect()));
		if(edtValue.getText()==null||"".equals(edtValue.getText())||edtNeed.getText()==null||"".equals(edtNeed.getText())) {		
			JOptionPane.showMessageDialog(null,"不该空的不能空","提示",JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.edtddl.setText(String.valueOf(p.getDiscount_end()));
		disc = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.done) {
			double value = Double.valueOf(this.edtValue.getText());
			int need = Integer.valueOf(this.edtNeed.getText());
			String date = this.edtddl.getText();
			disc.setBusi_id(BeanBusi_info.currentBusiness.getBusi_id());
			disc.setDiscount_value(value);
			disc.setDiscount_collect(need);
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				disc.setDiscount_end(sdf.parse(date));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				TakeOututil.discountManager.DiscountMdf(disc);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.cancel) {
			this.setVisible(false);
		}
	}
	public BeanBusi_discount getdisc() {
		return disc;
	}
}
