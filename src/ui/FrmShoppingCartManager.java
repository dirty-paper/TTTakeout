package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ProductManager;
import model.BeanBusi_info;
import model.BeanForProduct;
import model.BeanShoppingCart;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmShoppingCartManager extends JDialog implements ActionListener{

	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("结算");
	private JTextField edtKeyword = new JTextField(10);
	private Button btndelete = new Button("删除");
	private Object tblTitle[]={"商品名称","剩余数量","总价"};
	private Object tblData[][];
	List<BeanShoppingCart> product=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			product=TakeOututil.shoppingCartManager.loadAllproduct();
			tblData =new Object[product.size()][3];
			for(int i=0;i<product.size();i++){
				tblData[i][0]=product.get(i).getProduct_name();
				tblData[i][2]=product.get(i).getProduct_price();
				tblData[i][1]=product.get(i).getProduct_count();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmShoppingCartManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btndelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnAdd.addActionListener(this);
		this.btndelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btndelete) {
			int i =this.dataTable.getSelectedRow();
			BeanShoppingCart p = product.get(i);
			try {
				TakeOututil.shoppingCartManager.cartDlt(p);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if (e.getSource() == this.btnAdd) {
			try {
				ArrayList<BeanShoppingCart> pkg = TakeOututil.shoppingCartManager.loadAllproduct();
				FrmChooseAddress fca = new FrmChooseAddress(this, "地址选择", true, pkg);
				fca.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
