package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import control.BusiManager;
import model.BeanBusi_info;
import util.BaseException;
import javax.swing.JButton;
import javax.swing.SwingConstants;



public class FrmBusiManager extends JFrame implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加商家");
	private Button btnModify = new Button("修改商家");
	private Button btnDelete = new Button("删除商家");
	private Object tblTitle[]={"商家id","商家名称","商家星级","平均价格","总销售额"};
	private Object tblData[][];
	List<BeanBusi_info> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private JButton refresh = new JButton("刷新");
	private void reloadTable(){
		try {
			pubs=(new BusiManager()).LoadAllBusi();
			tblData =new Object[pubs.size()][5];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getBusi_id()+"";
				tblData[i][1]=pubs.get(i).getBusi_name();
				tblData[i][2]=pubs.get(i).getBusi_level();
				tblData[i][3]=pubs.get(i).getBusi_average();
				tblData[i][4]=pubs.get(i).getBusi_perchase();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmBusiManager() {
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnAdd);
		toolBar.add(this.btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(this.refresh);
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
		if (e.getSource()==this.btnAdd) {
			FrmBusiAdd fbm = new FrmBusiAdd();
			fbm.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"请选择商家","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_info p = this.pubs.get(i);
			FrmBusiMdf ppBusiMdf = new FrmBusiMdf(p);
			ppBusiMdf.setVisible(true);
			if (ppBusiMdf.getPub()!=null) {
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
				JOptionPane.showMessageDialog(null,"请选择商家","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_info p = this.pubs.get(i);
			if (JOptionPane.showConfirmDialog(this,"确定删除"+p.getBusi_name()+"吗","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				try {
					(new BusiManager()).deleteBusi(p);
					this.reloadTable();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
