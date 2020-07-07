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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ProductManager;
import model.BeanBusi_product;
import model.BeanForProduct;
import takeoutstarter.TakeOututil;
import util.BaseException;
import util.DbException;

public class FrmProductManager extends JDialog implements ActionListener{

	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("���");
	private Button btnModify = new Button("�޸�");
	private Button btnDelete = new Button("ɾ��");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[]={"����","��Ʒ����","���","�����̼�","�۸�","ʣ������"};
	private Object tblData[][];
	List<BeanForProduct> product=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			product=(new ProductManager()).loakallproduct();
			tblData =new Object[product.size()][6];
			for(int i=0;i<product.size();i++){
				tblData[i][0]=product.get(i).getProduct_id();
				tblData[i][1]=product.get(i).getProduct_name();
				tblData[i][2]=product.get(i).getKinds_name();
				tblData[i][3]=product.get(i).getBusi_name();
				tblData[i][4]=product.get(i).getProduct_price();
				tblData[i][5]=product.get(i).getProduct_remain()+"";
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadSearchTable(String name){
		try {
			product=TakeOututil.productManager.loakallproductSearch(name);
			tblData =new Object[product.size()][6];
			for(int i=0;i<product.size();i++){
				tblData[i][0]=product.get(i).getProduct_id();
				tblData[i][1]=product.get(i).getProduct_name();
				tblData[i][2]=product.get(i).getKinds_name();
				tblData[i][3]=product.get(i).getBusi_name();
				tblData[i][4]=product.get(i).getProduct_price();
				tblData[i][5]=product.get(i).getProduct_remain()+"";
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmProductManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdd) {
			FrmProductManagerAdd_choose fpmac = new FrmProductManagerAdd_choose(this,"��Ʒ����",true);
			fpmac.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanForProduct bp = this.product.get(i);
			FrmProductManagerMdf fpmm = new FrmProductManagerMdf(this,"��Ʒ�޸�", true, bp);
			fpmm.setVisible(true);
			this.reloadTable();
		}
		else if (e.getSource() == this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanForProduct bp = this.product.get(i);
			try {
				TakeOututil.productManager.deleteProduct(bp);
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource()==this.btnSearch) {
			String name = this.edtKeyword.getText();
			this.loadSearchTable(name);
		}
	}

}
