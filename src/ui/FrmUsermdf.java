package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.AddressManager;
import control.BusiManager;
import model.BeanBusi_info;
import model.BeanUser_address;
import model.BeanUser_info;
import takeoutstarter.TakeOututil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrmUsermdf extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField edtname;
	private JTextField edtgt;
	private JTextField edttele;
	private JTextField edtmail;
	private JTextField edtcity;
	JLabel vipendTime = new JLabel("nothing");
	private JComboBox cmbadd = null;
	JButton btnvip = new JButton("开通vip");
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	private Map<String, BeanUser_address> addmap_name =  new HashMap<String, BeanUser_address>();
	private Map<String, BeanUser_address> addmap_id =  new HashMap<String, BeanUser_address>();
	public FrmUsermdf(JFrame J, String s,boolean b) {
		super(J,s,b);
		setBounds(100, 100, 450, 552);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u6635\u79F0");
			lblNewLabel.setBounds(71, 59, 58, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			edtname = new JTextField();
			edtname.setBounds(201, 59, 66, 21);
			contentPanel.add(edtname);
			edtname.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u6027\u522B");
			lblNewLabel_1.setBounds(71, 127, 58, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			edtgt = new JTextField();
			edtgt.setBounds(201, 124, 66, 21);
			contentPanel.add(edtgt);
			edtgt.setColumns(10);
		}
		{
			edttele = new JTextField();
			edttele.setBounds(201, 193, 66, 21);
			contentPanel.add(edttele);
			edttele.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u7535\u8BDD");
			lblNewLabel_2.setBounds(71, 196, 58, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			edtmail = new JTextField();
			edtmail.setBounds(201, 254, 66, 21);
			contentPanel.add(edtmail);
			edtmail.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u90AE\u7BB1");
			lblNewLabel_3.setBounds(71, 257, 58, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("\u57CE\u5E02");
			lblNewLabel_4.setBounds(71, 327, 58, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			edtcity = new JTextField();
			edtcity.setBounds(201, 324, 66, 21);
			contentPanel.add(edtcity);
			edtcity.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			try {
				this.vipendTime.setText(String.valueOf(TakeOututil.UserManager.returnendDate()));
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			List<BeanUser_address> add = (new AddressManager()).loadAllmine();
			String[] strbusi = new String[add.size()+1];
			strbusi[0]="";
			for(int i=0;i<add.size();i++) {
				strbusi[i+1] = add.get(i).getAdd_id();
				this.addmap_id.put(add.get(i).getAdd_id(), add.get(i));
			}
			this.cmbadd = new JComboBox(strbusi);
			cmbadd.setBounds(196, 10, 81, 21);
			contentPanel.add(cmbadd);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPanel.add(cmbadd);
		{
			JLabel lblNewLabel_5 = new JLabel("\u5730\u5740\u9009\u62E9");
			lblNewLabel_5.setBounds(71, 13, 58, 15);
			contentPanel.add(lblNewLabel_5);
		}
		{
						btnvip.setBounds(71, 385, 75, 23);
						contentPanel.add(btnvip);
						
						btnvip.setActionCommand("开通vip");
					}
		{
			
			vipendTime.setBounds(201, 389, 133, 15);
			contentPanel.add(vipendTime);
		}
		btnvip.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.okButton) {
			BeanUser_info p =new BeanUser_info();
			p.setUsr_id(BeanUser_info.currentBeanUser.getUsr_id());
			p.setAdd_id(this.addmap_id.get(this.cmbadd.getSelectedItem().toString()).getAdd_id());
			p.setUsr_name(this.edtname.getText());
			p.setUsr_eml(this.edtmail.getText());
			p.setUsr_gt(this.edtgt.getText());
			p.setUsr_tel(this.edttele.getText());
			p.setUsr_city(this.edtcity.getText());
			try {
				TakeOututil.UserManager.UserMdf(p);
				Frmfucked ff = new Frmfucked(this, "操作成功", true);
				ff.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if (e.getSource()==this.btnvip) {
			try {	
				TakeOututil.UserManager.openVip();
				Frmfucked ff = new Frmfucked(this, "开通成功", true);
				ff.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
