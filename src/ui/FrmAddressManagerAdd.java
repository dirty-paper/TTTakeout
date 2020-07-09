package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import control.AddressManager;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmAddressManagerAdd extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField edtprovince;
	private JTextField edtpeople;
	private JTextField edttele;
	private JTextField edtdetail;
	private JTextField edtcity;
	private JComboBox cmbadd = null;
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	private JTextField edtid;
	private JTextField edtblock;
	public FrmAddressManagerAdd(JDialog J, String s,boolean b) {
		super(J,s,b);
		setBounds(100, 100, 450, 552);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u7701");
			lblNewLabel.setBounds(71, 59, 58, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			edtprovince = new JTextField();
			edtprovince.setBounds(201, 59, 66, 21);
			contentPanel.add(edtprovince);
			edtprovince.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u5E02");
			lblNewLabel_1.setBounds(71, 124, 58, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			edtpeople = new JTextField();
			edtpeople.setBounds(201, 324, 66, 21);
			contentPanel.add(edtpeople);
			edtpeople.setColumns(10);
		}
		{
			edttele = new JTextField();
			edttele.setBounds(201, 293, 66, 21);
			contentPanel.add(edttele);
			edttele.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u7535\u8BDD");
			lblNewLabel_2.setBounds(71, 296, 75, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			edtdetail = new JTextField();
			edtdetail.setBounds(201, 238, 66, 21);
			contentPanel.add(edtdetail);
			edtdetail.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u8BE6\u7EC6\u5730\u5740");
			lblNewLabel_3.setBounds(71, 241, 89, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("\u8054\u7CFB\u4EBA");
			lblNewLabel_4.setBounds(71, 327, 89, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			edtcity = new JTextField();
			edtcity.setBounds(201, 124, 66, 21);
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
		}
		{
			JLabel lblNewLabel_5 = new JLabel("id");
			lblNewLabel_5.setBounds(71, 13, 58, 15);
			contentPanel.add(lblNewLabel_5);
		}
		{
			edtid = new JTextField();
			edtid.setBounds(201, 10, 66, 21);
			contentPanel.add(edtid);
			edtid.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("\u533A");
			lblNewLabel_6.setBounds(71, 185, 58, 15);
			contentPanel.add(lblNewLabel_6);
		}
		{
			edtblock = new JTextField();
			edtblock.setBounds(201, 182, 66, 21);
			contentPanel.add(edtblock);
			edtblock.setColumns(10);
		}
		this.okButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.okButton) {
			BeanUser_address aaa = new BeanUser_address();
			aaa.setAdd_id(edtid.getText());
			aaa.setAdd_block(edtblock.getText());
			aaa.setAdd_city(edtcity.getText());
			aaa.setAdd_detail(edtdetail.getText());
			aaa.setAdd_people(edtpeople.getText());
			aaa.setAdd_tel(edttele.getText());
			aaa.setAdd_province(edtprovince.getText());
			try {
				TakeOututil.addressManager.AddressAdd(aaa);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e1.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
			}
		}else if (e.getSource()==this.cancelButton) {
			this.setVisible(false);
		}
	}


}
