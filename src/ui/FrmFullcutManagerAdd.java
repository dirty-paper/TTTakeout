package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BeanBusi_fullcut;
import model.BeanBusi_info;
import takeoutstarter.TakeOututil;
import util.BaseException;

public class FrmFullcutManagerAdd extends JDialog implements ActionListener{
	BeanBusi_fullcut disc = null;
	private JPanel contentPane;
	private JTextField edtValue;
	private JTextField edtNeed;
	private JTextField edtId;
	private String id;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	private JComboBox cmbbb = null;
	private JTextField edtid;
	public FrmFullcutManagerAdd(JDialog f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel NewId = new JLabel("新增id");
		NewId.setBounds(60, 41, 84, 15);
		contentPane.add(NewId);
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
		edtValue.setBounds(192, 74, 162, 21);
		contentPane.add(edtValue);
		edtValue.setColumns(10);
		
		edtNeed = new JTextField();
		edtNeed.setBounds(192, 120, 162, 21);
		contentPane.add(edtNeed);
		edtNeed.setColumns(10);
		cancel.setBounds(239, 214, 97, 23);
		contentPane.add(cancel);
		
		
		done.setBounds(76, 214, 97, 23);
		contentPane.add(done);
		this.cancel.addActionListener(this);
		this.done.addActionListener(this);
		String[] kk= new String[2];
		kk[0] = "可";kk[1]="不可";
		this.cmbbb = new JComboBox(kk);
		cmbbb.setBounds(192, 157, 63, 23);
		contentPane.add(cmbbb);
		
		edtid = new JTextField();
		edtid.setBounds(192, 38, 162, 21);
		contentPane.add(edtid);
		edtid.setColumns(10);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.done) {
			disc = new BeanBusi_fullcut();
			double cut = Double.valueOf(this.edtValue.getText());
			double need = Double.valueOf(this.edtNeed.getText());
			if(cmbbb.getSelectedItem().toString().equals("不可")) {
				disc.setFullcut_if(0);
			}else {
				disc.setFullcut_if(1);
			}
			disc.setFullcut_id(edtid.getText());
			disc.setBusi_id(BeanBusi_info.currentBusiness.getBusi_id());
			disc.setFullcut_fullvalue(need);;
			disc.setFullcut_cut(cut);;
			try {
				TakeOututil.fullcutManager.FullcutAdd(disc);
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
