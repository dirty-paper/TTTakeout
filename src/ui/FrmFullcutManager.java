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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.BeanBusi_discount;
import model.BeanBusi_fullcut;
import model.BeanBusi_info;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmFullcutManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("��������ȯ");
	private Button btnModify = new Button("�޸�����ȯ");
	private Button btnDelete = new Button("ɾ������ȯ");
	private Object tblTitle[]={"����ȯid","�̼�����","�۸�����ֵ","�Ż�ֵ","�Ƿ��Żݵ���"};
	private Object tblData[][];
	List<BeanBusi_fullcut> pubs;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(String id){
		try {
			pubs=TakeOututil.fullcutManager.loadFullcutByBusi_id(id);
			tblData =new Object[pubs.size()][5];
			for(int i=0;i<pubs.size();i++){
				tblData[i][0]=pubs.get(i).getFullcut_id();
				tblData[i][1]=pubs.get(i).getBusi_id();
				tblData[i][2]=pubs.get(i).getFullcut_fullvalue();
				tblData[i][3]=pubs.get(i).getFullcut_cut();
				tblData[i][4]=pubs.get(i).getFullcut_if();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public FrmFullcutManager(Dialog f,String s,boolean b,BeanBusi_info p) {
		super(f,s,b);
		BeanBusi_info.currentBusiness = p;
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(this.btnAdd);
		toolBar.add(this.btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable(p.getBusi_id());
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		// 屏幕居中显示
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
			FrmFullcutManagerAdd ffma = new FrmFullcutManagerAdd(this, "����ȯ����", true);
			ffma.setVisible(true);
			this.reloadTable(BeanBusi_info.currentBusiness.getBusi_id());
		}
		else if (e.getSource()==this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"��ѡ������ȯ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_fullcut bbd = this.pubs.get(i);
			FrmFullcutManagerMdf ffmm = new FrmFullcutManagerMdf(this,"�Ż�ȯ�༭", true,bbd);
			ffmm.setVisible(true);
			this.reloadTable(BeanBusi_info.currentBusiness.getBusi_id());
			
		}
		else if (e.getSource()==this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,"��ѡ������ȯ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanBusi_fullcut bbd = this.pubs.get(i);
			try {
				TakeOututil.fullcutManager.FullcutDlt(bbd);
				this.reloadTable(BeanBusi_info.currentBusiness.getBusi_id());
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
