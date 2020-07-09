package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
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

import control.BusiManager;
import control.KindsManager;
import model.BeanBusi_info;
import model.BeanBusi_kinds;
import util.BaseException;

public class FrmKindsManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加类别");
	private Button btnModify = new Button("修改类别");
	private Button btnDelete = new Button("删除类别");
	private Object tblTitle[]={"类别名称","所属商家","包含货物数量"};
	private Object tblData[][];
	List<BeanBusi_kinds> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private JButton refresh = new JButton("刷新");
	private void reloadTable(){
		try {
			pubs=(new KindsManager()).loadallKinds();
			tblData =new Object[pubs.size()][3];
			for(int i=0;i<pubs.size();i++){
				tblData[i][1]=pubs.get(i).getBusi_id()+"";
				tblData[i][0]=pubs.get(i).getKinds_id();
				tblData[i][2]=pubs.get(i).getKinds_count();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmKindsManager(JFrame f,String s,boolean b) {
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnAdd);
		toolBar.add(this.btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(this.refresh);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

		this.setSize(842, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.refresh.addActionListener(this);
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
		if (e.getSource()==this.btnAdd) {
			FrmKindsAdd fbm = new FrmKindsAdd(this,"添加类别",true);
			fbm.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择商家","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_kinds p = this.pubs.get(i);
			FrmKindsMdf fkm = new FrmKindsMdf(this,"修改类别",true,p);
			fkm.setVisible(true);
			if (fkm.getPub()!=null) {
				this.reloadTable();
			}
			this.reloadTable();
			
		}
		else if(e.getSource()==this.refresh) {
			this.reloadTable();
		}
		else if (e.getSource()==this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_kinds p = this.pubs.get(i);
			if (JOptionPane.showConfirmDialog(this,"确定删除"+p.getKinds_name()+"吗","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				try {
					(new KindsManager()).deleteKinds(p);
					this.reloadTable();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}