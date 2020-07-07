package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BeanBusi_discount;
import model.BeanBusi_fullcut;
import model.BeanBusi_info;
import takeoutstarter.TakeOututil;
import util.BaseException;
import javax.swing.JComboBox;

public class FrmFullcutManagerMdf extends JDialog implements ActionListener{
	BeanBusi_fullcut disc = null;
	private JPanel contentPane;
	private JTextField edtValue;
	private JTextField edtNeed;
	private String id;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	private JComboBox cmbbb = null;
	public FrmFullcutManagerMdf(JDialog f,String s,boolean b,BeanBusi_fullcut p) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel Newvalue = new JLabel("\u6EE1\u503C");
		Newvalue.setBounds(60, 77, 84, 15);
		contentPane.add(Newvalue);
		JLabel newneed = new JLabel("\u51CF\u503C");
		newneed.setBounds(60, 123, 73, 15);
		contentPane.add(newneed);
		
		
		JLabel Newddl = new JLabel("\u662F\u5426\u53E0\u52A0");
		Newddl.setBounds(60, 161, 73, 15);
		contentPane.add(Newddl);
		
		edtValue = new JTextField();
		edtValue.setBounds(192, 117, 162, 21);
		contentPane.add(edtValue);
		edtValue.setColumns(10);
		
		edtNeed = new JTextField();
		edtNeed.setBounds(192, 74, 162, 21);
		contentPane.add(edtNeed);
		edtNeed.setColumns(10);
		cancel.setBounds(239, 214, 97, 23);
		contentPane.add(cancel);
		
		
		done.setBounds(76, 214, 97, 23);
		contentPane.add(done);
		this.cancel.addActionListener(this);
		this.done.addActionListener(this);
		this.edtNeed.setText(String.valueOf(p.getFullcut_fullvalue()));
		this.edtValue.setText(String.valueOf(p.getFullcut_cut()));
		String[] kk= new String[2];
		kk[0] = "可";kk[1]="不可";
		this.cmbbb = new JComboBox(kk);
		cmbbb.setBounds(202, 157, 53, 23);
		contentPane.add(cmbbb);
		if(edtValue.getText()==null||"".equals(edtValue.getText())||edtNeed.getText()==null||"".equals(edtNeed.getText())) {		
			JOptionPane.showMessageDialog(null,"不该空的不能空","提示",JOptionPane.ERROR_MESSAGE);
			return;
		}
		disc = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.done) {
			double cut = Double.valueOf(this.edtValue.getText());
			double need = Double.valueOf(this.edtNeed.getText());
			if(cmbbb.getSelectedItem().toString().equals("不可")) {
				disc.setFullcut_if(0);
			}else {
				disc.setFullcut_if(1);
			}
			disc.setBusi_id(BeanBusi_info.currentBusiness.getBusi_id());
			disc.setFullcut_fullvalue(need);;
			disc.setFullcut_cut(cut);;
			try {
				TakeOututil.fullcutManager.FullcutMdf(disc);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.cancel) {
			this.setVisible(false);
		}
	}
	public BeanBusi_fullcut getdisc() {
		return disc;
	}
}
