package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.BeanBusi_discount;
import model.BeanBusi_info;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmAddressManager extends JDialog implements ActionListener{

	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加地址");
	private Button btnModify = new Button("修改地址");
	private Button btnDelete = new Button("删除地址");
	private Object tblTitle[]={"地址id","省","市","区","详细","联系人","电话"};
	private Object tblData[][];
	List<BeanUser_address> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			pubs=TakeOututil.addressManager.loadAllmine();
			tblData =new Object[pubs.size()][7];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getAdd_id();
				tblData[i][1]=pubs.get(i).getAdd_province();
				tblData[i][2]=pubs.get(i).getAdd_city();
				tblData[i][3]=pubs.get(i).getAdd_block();
				tblData[i][4]=pubs.get(i).getAdd_detail();
				tblData[i][5]=pubs.get(i).getAdd_people();
				tblData[i][6]=pubs.get(i).getAdd_tel();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public FrmAddressManager(JFrame f,String s,boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnAdd);
		toolBar.add(this.btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		// 灞忓箷灞呬腑鏄剧ず
		this.setSize(842, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
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
			if (e.getSource()==this.btnAdd) {
				FrmAddressManagerAdd fama = new FrmAddressManagerAdd(this, "添加地址", true);
				fama.setVisible(true);
				this.reloadTable();
			}else if (e.getSource()==this.btnModify) {
				int i =this.dataTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null,"请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				BeanUser_address aaa = pubs.get(i);
				FrmAddressManagermdf famm = new FrmAddressManagermdf(this, "修改地址", true, aaa);
				famm.setVisible(true);
				this.reloadTable();
			}else if (e.getSource()==this.btnDelete) {
				int i =this.dataTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null,"请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				BeanUser_address aaa = pubs.get(i);
				try {
					TakeOututil.addressManager.Addressdlt(aaa);
					this.reloadTable();
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	

}
