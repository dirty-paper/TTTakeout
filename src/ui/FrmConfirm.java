package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BeanDiscount_own;
import model.BeanFullcut_own;
import model.BeanShoppingCart;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmConfirm extends JDialog implements ActionListener{
	private ArrayList<BeanShoppingCart> p= null;
	private BeanUser_address aa = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField edtTime;
	private JLabel lbltotal = new JLabel("New label");
	private JLabel lbdiscount = new JLabel("New label");
	private JLabel lblfullcut = new JLabel("New label");
	private JLabel lblNewLabel_3 = new JLabel("\u6311\u4E2A\u65F6\u95F4\u5427~");
	private JLabel lblafter = new JLabel("New label");
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	BeanDiscount_own bd = null;
	double total=0;
	double discount=0;
	double fullcut=0;
	double after = 0;
	BeanFullcut_own fuc=null;
	public FrmConfirm(JDialog f,String s,boolean b,BeanUser_address aa,ArrayList<BeanShoppingCart> p) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		this.aa = aa;
		this.p = p;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			
			lbltotal.setBounds(86, 10, 161, 15);
			contentPanel.add(lbltotal);
		}
		{
			
			lbdiscount.setBounds(81, 59, 272, 15);
			contentPanel.add(lbdiscount);
		}
		{
			
			lblfullcut.setBounds(81, 110, 272, 15);
			contentPanel.add(lblfullcut);
		}
		{	
			
			lblNewLabel_3.setBounds(86, 149, 106, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			edtTime = new JTextField();
			edtTime.setBounds(213, 146, 140, 21);
			contentPanel.add(edtTime);
			edtTime.setColumns(10);
		}
		{
			
			lblafter.setBounds(293, 191, 106, 15);
			contentPanel.add(lblafter);
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
		this.okButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
	
		try {
			this.total = TakeOututil.shoppingCartManager.gettotal(p);
			this.lbltotal.setText("原价："+String.valueOf(total)+"元");
			this.bd = TakeOututil.shoppingCartManager.getDiscount(this.total, this.p);
			if (bd!=null) {
				this.discount = bd.getDiscount_value();
				this.lbdiscount.setText("使用了优惠券"+this.bd.getDiscount_id()+"号优惠了"+String.valueOf(this.bd.getDiscount_value())+"元");
			}else {
				this.discount=0;
				this.lbdiscount.setText("无可用优惠券");
			}
		
			this.fuc = TakeOututil.shoppingCartManager.getFullcut(this.total);
			if (fuc!=null) {
				this.fullcut = this.fuc.getValuecut();
				this.lblfullcut.setText("使用了满减券"+this.fuc.getFullcut_id()+"号优惠了"+String.valueOf(this.fuc.getValuecut())+"元");
			}else {
				this.fullcut = 0;
				this.lblfullcut.setText("无可用满减券");
			}
			this.after = this.total-this.fullcut-this.discount;
			if (this.after < 0) {
				this.after = 0;
			}
			this.lblafter.setText("合计："+String.valueOf(this.after));
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.okButton) {
			String rqtime = this.edtTime.getText();
			try {
				TakeOututil.shoppingCartManager.settle(p, aa, bd, fuc, total, after, rqtime);
				if(bd!=null) {
					TakeOututil.discount_own.usediscount(bd);
				}
				if (fuc!=null) {
					TakeOututil.fullcut_own.usefullcut(fuc);
				}
				Frmfucked ff = new Frmfucked(this, "下单成功提醒", true);
				ff.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if (e.getSource()==this.cancelButton) {
			this.setVisible(false);
		}
	}

}
