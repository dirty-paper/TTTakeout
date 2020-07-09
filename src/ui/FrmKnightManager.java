package ui;

import java.awt.BorderLayout;
import java.awt.Button;
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

import model.BeanKnight_info;
import model.BeanUser_address;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmKnightManager extends JDialog implements ActionListener{

	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加骑手");
	private Button btnModify = new Button("修改骑手");
	private Button btnDelete = new Button("删除骑手");
	private Object tblTitle[]={"id","骑手昵称","骑手等级","加入时间"};
	private Object tblData[][];
	List<BeanKnight_info> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			pubs=TakeOututil.knightManager.loadallKnight();
			tblData =new Object[pubs.size()][4];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getKnight_id();
				tblData[i][1]=pubs.get(i).getKnight_name();
				tblData[i][2]=pubs.get(i).getKnight_level();
				tblData[i][3]=pubs.get(i).getKnight_join();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public FrmKnightManager(JFrame f,String s,boolean b) {
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
				FrmKnightManagerAdd fkma = new FrmKnightManagerAdd(this, "添加骑手", true);
				fkma.setVisible(true);
				this.reloadTable();
			}else if (e.getSource()==this.btnModify) {
				int i = this.dataTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null,"请选择骑手","提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				BeanKnight_info v = pubs.get(i);
				FrmKnightManagerMdf fkmm = new FrmKnightManagerMdf(this,"修改骑手", true, v);
				fkmm.setVisible(true);
				this.reloadTable();
				
			}else if (e.getSource()==this.btnDelete) {
				int i = this.dataTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null,"请选择骑手","提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				BeanKnight_info v = pubs.get(i);
				try {
					TakeOututil.knightManager.Knightdlt(v);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				this.reloadTable();
			}
		}

}
