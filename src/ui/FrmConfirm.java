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

import model.BeanFullcut_own;
import model.BeanShoppingCart;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

import javax.swing.JLabel;
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
			
			lbltotal.setBounds(141, 10, 106, 15);
			contentPanel.add(lbltotal);
		}
		{
			
			lbdiscount.setBounds(141, 59, 106, 15);
			contentPanel.add(lbdiscount);
		}
		{
			
			lblfullcut.setBounds(141, 110, 106, 15);
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
			
			lblafter.setBounds(341, 191, 58, 15);
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
		double fullcut=0;
		try {
			double total = TakeOututil.shoppingCartManager.gettotal(p);
			this.lbltotal.setText(String.valueOf(total));
			double discount = 0;
			//这里还要添加优惠券
			//
			//
			//这里还要添加优惠券
			
			BeanFullcut_own fuc = TakeOututil.shoppingCartManager.getFullcut(total);
			if (fuc!=null) {
				fullcut = fuc.getValuecut();
				this.lblfullcut.setText("使用了满减券"+fuc.getFullcut_id()+"号优惠了"+String.valueOf(fuc.getValuecut())+"元");
			}else {
				fullcut = 0;
				this.lblfullcut.setText("无可用满减券");
			}
			this.lblafter.setText("合计："+String.valueOf(total-fullcut-discount));
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.okButton) {
			
		}
	}

}
