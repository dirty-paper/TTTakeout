package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import control.BusiManager;
import model.BeanBusi_info;
import model.BeanForProduct;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmChooseCount extends JDialog implements ActionListener{
	private BeanForProduct ppp = null;
	private JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("OK");	
	private JButton cancelButton = new JButton("Cancel");
	private JLabel labelBusi = new JLabel("请选择需要的数量");
	private JComboBox cmbbusi = null;
	private Map<String, BeanBusi_info> busimap_name =  new HashMap<String, BeanBusi_info>();
	private Map<String, BeanBusi_info> busimap_id =  new HashMap<String, BeanBusi_info>();
	public FrmChooseCount(JDialog f,String s,boolean b,BeanForProduct p) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		this.ppp = p;
		contentPanel.setBounds(0, 0, 436, 263);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		labelBusi.setBounds(103, 23, 200, 15);
		contentPanel.add(labelBusi);
		okButton.setBounds(89, 203, 81, 23);
		contentPanel.add(okButton);
		cancelButton.setBounds(222, 203, 81, 23);
		contentPanel.add(cancelButton);
		String[] strbusi = new String[p.getProduct_remain()+1];
		for(int i=0;i<=p.getProduct_remain();i++) {
			strbusi[i] = String.valueOf(i); 
		}
		this.cmbbusi = new JComboBox(strbusi);
		cmbbusi.setBounds(175, 109, 81, 21);
		contentPanel.add(cmbbusi);
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
			
			if(this.cmbbusi.getSelectedIndex()>0) {
				int i = this.cmbbusi.getSelectedIndex();//采购数量
				try {
					TakeOututil.shoppingCartManager.addincart(ppp, i);
					Frmfucked ff = new Frmfucked(this, "操作成功", true);
					ff.setVisible(true);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if (e.getSource()==this.cancelButton) {
			this.setVisible(false);
		}
	}

}
