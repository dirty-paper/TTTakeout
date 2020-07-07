package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BeanBusi_discount;
import model.BeanBusi_info;
import takeoutstarter.TakeOututil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrmDiscountManagerAdd extends JDialog implements ActionListener{
	private JPanel contentPane;
	private JTextField edtId;
	private JTextField edtValue;
	private JTextField edtNeed;
	private JTextField edtddl;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	public FrmDiscountManagerAdd(JDialog f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NewId = new JLabel("\u4F18\u60E0\u5238id");
		NewId.setBounds(60, 40, 73, 15);
		contentPane.add(NewId);
		JLabel Newvalue = new JLabel("\u4F18\u60E0\u5238\u4EF7\u503C");
		Newvalue.setBounds(60, 77, 84, 15);
		contentPane.add(Newvalue);
		JLabel newneed = new JLabel("���趩����");
		newneed.setBounds(60, 123, 73, 15);
		contentPane.add(newneed);
		
		
		JLabel Newddl = new JLabel("ʹ�ý�ֹ����");
		Newddl.setBounds(60, 161, 73, 15);
		contentPane.add(Newddl);
		edtId = new JTextField();
		edtId.setBounds(192, 37, 162, 21);
		contentPane.add(edtId);
		edtId.setColumns(10);
		
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.done) {
			String id = this.edtId.getText();
			double value = Double.valueOf(this.edtValue.getText());
			int need = Integer.valueOf(this.edtNeed.getText());
			Date date = Date.valueOf(this.edtddl.getText());
			BeanBusi_discount bd = new BeanBusi_discount();
			bd.setBusi_id(BeanBusi_info.currentBusiness.getBusi_id());
			bd.setDiscount_id(id);
			bd.setDiscount_value(value);
			bd.setDiscount_collect(need);
			bd.setDiscount_end(date);
			try {
				TakeOututil.discountManager.DiscountAdd(bd);
				this.setVisible(false);

			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.cancel) {
			this.setVisible(false);
		}
	}
	
}
