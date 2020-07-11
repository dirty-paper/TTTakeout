package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BeanBusi_order;
import model.BeanKnight_get;
import model.BeanKnight_info;
import model.BeanShoppingCart;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmChooseKnight extends JDialog implements ActionListener{
	private BeanBusi_order ppp= null;
	private JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("OK");	
	private JButton cancelButton = new JButton("Cancel");
	private JLabel labelBusi = new JLabel("选择收货地址");
	private JComboBox cmbbusi = null;
	private Map<String,BeanKnight_info> knightmap_name =  new HashMap<String, BeanKnight_info>();
	private Map<String, BeanKnight_info> knightmap_id =  new HashMap<String, BeanKnight_info>();
	public FrmChooseKnight(JDialog f,String s,boolean b,BeanBusi_order p) {
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
			ArrayList<BeanKnight_info> knight = TakeOututil.knightManager.loadallfreeKnight();
			String[] strbusi = new String[knight.size()+1];
			strbusi[0]="";
			for(int i=0;i<knight.size();i++) {
				strbusi[i+1] = knight.get(i).getKnight_name();
				this.knightmap_id.put(knight.get(i).getKnight_id(), knight.get(i));
				this.knightmap_name.put(knight.get(i).getKnight_name(), knight.get(i));
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
			if (this.cmbbusi.getSelectedIndex()<0) {
				JOptionPane.showMessageDialog(null,"请选择骑手","提示",JOptionPane.ERROR_MESSAGE);
				return;
			} 
			else {	
				BeanKnight_info aa = this.knightmap_name.get(this.cmbbusi.getSelectedItem().toString());
				try {
					TakeOututil.orderManager.distributeKnight(ppp, aa);
					} catch (BaseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
					this.setVisible(false);
			}
		}
		else if (e.getSource()==this.cancelButton) {
			this.setVisible(false);
		}
	}

}
