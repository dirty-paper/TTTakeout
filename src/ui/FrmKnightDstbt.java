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

import control.BusiManager;
import control.OrderManager;
import model.BeanBusi_info;
import model.BeanBusi_order;
import model.BeanKnight_info;
import model.BeanOrder_detail;
import util.BaseException;

public class FrmKnightDstbt extends JDialog implements ActionListener{

	private JPanel toolBar = new JPanel();
	private Button btnDistribute = new Button("分配骑手");
	private Object tblTitle[]={"订单id","商家id","客户地址"};
	private Object tblData[][];
	List<BeanBusi_order> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			pubs=(new OrderManager()).loadallOrderUnDlv();
			tblData =new Object[pubs.size()][3];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getOrder_id();
				tblData[i][2]=pubs.get(i).getAdd_id();
				tblData[i][1]=pubs.get(i).getBusi_id();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmKnightDstbt(Frame f,String s,boolean b) {
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnDistribute);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		this.setSize(842, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
		(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnDistribute.addActionListener(this);
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
		if (e.getSource() == this.btnDistribute) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择订单","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_order p = pubs.get(i);
			FrmChooseKnight fck = new FrmChooseKnight(this, "选择骑手", true, p);
			fck.setVisible(true);
			this.reloadTable();
		}
	}

}
