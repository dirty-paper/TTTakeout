package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.BusiManager;
import model.BeanBusi_info;
import model.BeanShoppingCart;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmChooseAddress extends JDialog implements ActionListener{
	private ArrayList<BeanShoppingCart> ppp= null;
	private JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("OK");	
	private JButton cancelButton = new JButton("Cancel");
	private JLabel labelBusi = new JLabel("选择收货地址");
	private JComboBox cmbbusi = null;
	private Map<String,BeanUser_address> busimap_name =  new HashMap<String, BeanUser_address>();
	private Map<String, BeanUser_address> busimap_id =  new HashMap<String, BeanUser_address>();
	public FrmChooseAddress(JDialog f,String s,boolean b,ArrayList<BeanShoppingCart> p) {
		super(f,s,b);
		this.ppp = p;
		setBounds(100, 100, 450, 300);
		contentPanel.setBounds(0, 0, 436, 263);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		labelBusi.setBounds(103, 23, 200, 15);
		contentPanel.add(labelBusi);
		okButton.setBounds(89, 203, 81, 23);
		contentPanel.add(okButton);
		cancelButton.setBounds(222, 203, 81, 23);
		contentPanel.add(cancelButton);
		try {
			List<BeanUser_address> busi = TakeOututil.addressManager.loadAllmine();
			String[] strbusi = new String[busi.size()+1];
			strbusi[0]="";
			for(int i=0;i<busi.size();i++) {
				strbusi[i+1] = busi.get(i).getAdd_id();
				this.busimap_id.put(busi.get(i).getAdd_id(), busi.get(i));
				this.busimap_name.put(busi.get(i).getAdd_province(), busi.get(i));
			}
			this.cmbbusi = new JComboBox(strbusi);
			cmbbusi.setBounds(175, 109, 81, 21);
			contentPanel.add(cmbbusi);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPanel.add(cmbbusi);
		this.okButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		getContentPane().setLayout(null);
		this.getContentPane().add(contentPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.okButton) {
			if (this.cmbbusi.getSelectedIndex()>0) {
				BeanUser_address aa = this.busimap_id.get(this.cmbbusi.getSelectedItem().toString());
				FrmConfirm fcf = new FrmConfirm(this, "订单确认", true, aa, ppp);
				fcf.setVisible(true);
			}
		}
		else if (e.getSource()==this.cancelButton) {
			this.setVisible(false);
		}
	}

}
