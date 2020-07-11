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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import control.OrderManager;
import model.BeanBusi_info;
import model.BeanBusi_order;
import model.BeanUser_info;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmMyOrderManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnevaluate = new Button("评价");
	private Button btndetail = new Button("查看详情");
	private Button btnconfirmArrive = new Button("确认送达");
	private Object tblTitle[]={"订单id","商家id","下单时间","实际价格","订单状态"};
	private Object tblData[][];
	List<BeanBusi_order> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			pubs=(new OrderManager()).loadallOrderbyUser(BeanUser_info.currentBeanUser.getUsr_id());
			tblData =new Object[pubs.size()][5];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getOrder_id();
				tblData[i][2]=pubs.get(i).getOrder_time();
				tblData[i][1]=pubs.get(i).getBusi_id();
				tblData[i][3]=pubs.get(i).getOrder_fprc();
				tblData[i][4]=pubs.get(i).getOrder_status();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmMyOrderManager(Frame f,String s,boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnevaluate);
		toolBar.add(this.btndetail);
		toolBar.add(this.btnconfirmArrive);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		this.setSize(842, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
		(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnevaluate.addActionListener(this);
		this.btndetail.addActionListener(this);
		this.btnconfirmArrive.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.reloadTable();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btnconfirmArrive) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择订单","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_order p=this.pubs.get(i);
			if(JOptionPane.showConfirmDialog(this,"确定收到这单了吗?订单金额将不会退回。","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					TakeOututil.orderManager.confirmArrive(p);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}

}
