package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.BusiManager;
import control.KindsManager;
import control.ProductManager;
import model.BeanBusi_info;
import model.BeanBusi_kinds;
import model.BeanBusi_product;
import takeoutstarter.TakeOututil;
import util.BaseException;
import javax.swing.SwingConstants;

public class FrmProductManagerAdd extends JDialog implements ActionListener{
	private BeanBusi_product product=null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelId = new JLabel("商品条码");
	private JLabel labelName = new JLabel("商品名称");
	private JLabel labelPrice = new JLabel("商品价格");
	private JLabel labelKinds = new JLabel("类别");
	
	private JTextField edtId = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private JTextField edtRemain = new JTextField(20);
	private Map<String,BeanBusi_kinds> kindsMap_name=new HashMap<String,BeanBusi_kinds>();
	private Map<String,BeanBusi_kinds> kindsMap_id=new HashMap<String,BeanBusi_kinds>();
	
	private JComboBox cmbkinds = null;
	private JLabel lblNewLabel = new JLabel("\u4F59\u91CF");
	private final JLabel label = new JLabel("\u7C7B\u522B");

	public FrmProductManagerAdd(JDialog f, String s, boolean b,BeanBusi_info p) {
		super(f, s, b);
		workPane.add(labelKinds);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelId.setBounds(34, 24, 79, 15);
		workPane.add(labelId);
		edtId.setBounds(158, 21, 126, 21);
		workPane.add(edtId);
		labelName.setBounds(34, 55, 79, 15);
		workPane.add(labelName);
		edtName.setBounds(158, 52, 126, 21);
		workPane.add(edtName);
		labelPrice.setBounds(34, 91, 79, 15);
		workPane.add(labelPrice);
		edtPrice.setBounds(158, 83, 126, 21);
		this.edtPrice.setText("0");
		this.edtRemain.setText("0");
		workPane.add(edtPrice);
		edtRemain.setBounds(158, 123, 126, 21);
		workPane.add(edtRemain);
		try {
			List<BeanBusi_kinds> kinds=(new KindsManager()).loadallKindsbyBusiId(p.getBusi_id());
			String[] strpubs=new String[kinds.size()+1];
			strpubs[0]="";
			for(int i=0;i<kinds.size();i++){
				strpubs[i+1]=kinds.get(i).getKinds_name();
				this.kindsMap_name.put(kinds.get(i).getKinds_name(),kinds.get(i));
				this.kindsMap_id.put(kinds.get(i).getBusi_id(), kinds.get(i));
			}
			this.cmbkinds=new JComboBox(strpubs);
			cmbkinds.setBounds(125, 154, 58, 21);
			workPane.add(this.cmbkinds);
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setBounds(34, 126, 79, 15);
		
		workPane.add(lblNewLabel);
		label.setBounds(34, 157, 58, 15);
		
		workPane.add(label);
		this.setSize(360, 289);
		// 灞忓箷灞呬腑鏄剧ず
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btnOk) {
			String id = this.edtId.getText();
			String name = this.edtName.getText();
			double price = 0;
			int remain = 0;
			try {
				price = Double.valueOf(this.edtPrice.getText());
				remain = Integer.valueOf(this.edtRemain.getText());
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "单价或余量输入不正确","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_product pro = new BeanBusi_product();
			pro.setProduct_id(id);
			pro.setProduct_name(name);
			pro.setProduct_price(price);
			pro.setProduct_remain(remain);
			if (this.cmbkinds.getSelectedIndex()>0) {
				BeanBusi_kinds kk = this.kindsMap_name.get(this.cmbkinds.getSelectedItem().toString());
				if(kk.getKinds_id().equals("")){
					JOptionPane.showMessageDialog(null, "请选择商品类别","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				pro.setKinds_id(kk.getKinds_id());
			}
			try {
				(new ProductManager()).addProduct(pro);
				this.product = pro;
				this.setVisible(false);
			} catch (BaseException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null,e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}
	}
	public BeanBusi_product getproduct() {
		return product;
	}

}
