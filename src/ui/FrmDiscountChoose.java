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
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmDiscountChoose extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("获取优惠券");
	private Object tblTitle[]={"优惠券id","商家名称","优惠价值","需要单数","截止日期"};
	private Object tblData[][];
	List<BeanBusi_discount> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			pubs=TakeOututil.discountManager.loadDiscountall();
			tblData =new Object[pubs.size()][5];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getDiscount_id();
				tblData[i][1]=pubs.get(i).getBusi_id();
				tblData[i][2]=pubs.get(i).getDiscount_value();
				tblData[i][3]=pubs.get(i).getDiscount_collect();
				tblData[i][4]=pubs.get(i).getDiscount_end();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public FrmDiscountChoose(JFrame f,String s,boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnAdd);
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
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_discount bbd = this.pubs.get(i);
			try {
				TakeOututil.discount_own.DiscountGet(bbd);
				Frmfucked fuck = new Frmfucked(this, "确认界面", true);
				fuck.setVisible(true);
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
