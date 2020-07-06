package ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.BusiManager;
import model.BeanBusi_info;
import model.BeanBusi_kinds;

import util.BaseException;
import util.BusinessException;
import java.awt.GridLayout;

public class FrmProductManagerAdd_choose extends JDialog implements ActionListener{
	
	private JPanel contentPanel = new JPanel();
	private JButton okButton = new JButton("OK");	
	private JButton cancelButton = new JButton("Cancel");
	private JLabel labelBusi = new JLabel("PLEASE CHOOSE 商家");
	private JComboBox cmbbusi = null;
	private Map<String, BeanBusi_info> busimap_name =  new HashMap<String, BeanBusi_info>();
	private Map<String, BeanBusi_info> busimap_id =  new HashMap<String, BeanBusi_info>();
	public FrmProductManagerAdd_choose(Dialog f,String s,boolean b) {
		super(f,s,b);
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
			List<BeanBusi_info> busi = (new BusiManager()).LoadAllBusi();
			String[] strbusi = new String[busi.size()+1];
			strbusi[0]="";
			for(int i=0;i<busi.size();i++) {
				strbusi[i+1] = busi.get(i).getBusi_name();
				this.busimap_id.put(busi.get(i).getBusi_id(), busi.get(i));
				this.busimap_name.put(busi.get(i).getBusi_name(), busi.get(i));
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
			
			if(this.cmbbusi.getSelectedIndex()>0) {
				BeanBusi_info p= new BeanBusi_info();
				p = this.busimap_name.get(this.cmbbusi.getSelectedItem().toString());
				FrmProductManagerAdd fpma = new FrmProductManagerAdd(this, "选择类别", true,p);
				fpma.setVisible(true);
			}
		}
	}

}
