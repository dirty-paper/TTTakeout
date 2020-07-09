package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.BusiManager;
import control.KindsManager;
import model.BeanBusi_info;
import model.BeanBusi_kinds;

public class FrmKindsAdd extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	private JTextField textField_2;
	private JLabel NewPwd = new JLabel("\u7C7B\u578Bid");
	private JLabel NewId = new JLabel("\u5546\u6237id");
	private Map<String,BeanBusi_info> busiMap_name=new HashMap<String,BeanBusi_info>();
	private Map<String,BeanBusi_info> busiMap_id=new HashMap<String,BeanBusi_info>();
	private JComboBox cmbbusi = null;
	public FrmKindsAdd(JDialog f,String s,Boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		NewId.setBounds(75, 40, 58, 15);
		contentPane.add(NewId);
		
		
		NewPwd.setBounds(75, 98, 58, 15);
		contentPane.add(NewPwd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 95, 162, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		cancel.setBounds(86, 212, 97, 23);
		contentPane.add(cancel);
		
		
		done.setBounds(241, 212, 97, 23);
		contentPane.add(done);
		
		JLabel label = new JLabel("\u7C7B\u578B\u540D\u79F0");
		label.setBounds(75, 164, 58, 15);
		contentPane.add(label);
		
		textField_2 = new JTextField();
		textField_2.setBounds(192, 161, 162, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		this.cancel.addActionListener(this);
		this.done.addActionListener(this);
		try {
			List<BeanBusi_info> busi=(new BusiManager().LoadAllBusi());
			String[] strpubs=new String[busi.size()+1];
			strpubs[0]="";
			for(int i=0;i<busi.size();i++){
				strpubs[i+1]=busi.get(i).getBusi_name();
				this.busiMap_name.put(busi.get(i).getBusi_name(),busi.get(i));
				this.busiMap_id.put(busi.get(i).getBusi_id(), busi.get(i));
			}
			this.cmbbusi=new JComboBox(strpubs);
			cmbbusi.setBounds(200, 35, 58, 21);
			contentPane.add(this.cmbbusi);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.cancel) {
			this.setVisible(false);
		}
		if (e.getSource() == this.done) {
			String busi_id = this.busiMap_name.get(this.cmbbusi.getSelectedItem().toString()).getBusi_id();
			String name=textField_2.getText();	
			String kinds_id = textField_1.getText();
			try {
				KindsManager b = new KindsManager();
				b.addKinds(busi_id, kinds_id,name);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
