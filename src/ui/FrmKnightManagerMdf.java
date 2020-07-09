package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.BeanKnight_info;
import takeoutstarter.TakeOututil;

public class FrmKnightManagerMdf extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtName;
	private JButton cancel = new JButton("\u53D6\u6D88");
	private JButton done = new JButton("\u63D0\u4EA4");
	private JComboBox cmblevel = null;
	private JLabel lblNewLabel;
	private String iddd;
	/**
	 * Launch the application.
	 */
	String[] strlevel = new String[4];
	/**
	 * Create the frame.
	 */
	public FrmKnightManagerMdf(JDialog f,String s,boolean b,BeanKnight_info p) {
		super(f,s,b);
		iddd = p.getKnight_id();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		strlevel[0]="";
		strlevel[1]="新人";
		strlevel[2]="正式员工";
		strlevel[3]="单王";
		this.cmblevel = new JComboBox(strlevel);
		cmblevel.setBounds(195, 145, 81, 21);
		contentPane.add(cmblevel);
		
		JLabel NewPwd = new JLabel("\u9A91\u624B\u59D3\u540D");
		NewPwd.setBounds(75, 98, 58, 15);
		contentPane.add(NewPwd);
		
		edtName = new JTextField();
		edtName.setBounds(192, 95, 162, 21);
		contentPane.add(edtName);
		edtName.setColumns(10);
		edtName.setText(p.getKnight_name());
		
		cancel.setBounds(86, 212, 97, 23);
		contentPane.add(cancel);
		
		
		done.setBounds(241, 212, 97, 23);
		contentPane.add(done);
		
		lblNewLabel = new JLabel("\u7B49\u7EA7");
		lblNewLabel.setBounds(75, 148, 58, 15);
		contentPane.add(lblNewLabel);
		this.cancel.addActionListener(this);
		this.done.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.cancel) {
			this.setVisible(false);
		}
		if (e.getSource() == this.done) {
			String name=new String(this.edtName.getText());	
			String level = strlevel[this.cmblevel.getSelectedIndex()];
			BeanKnight_info p = new BeanKnight_info();
			p.setKnight_name(name);
			p.setKnight_level(level);
			p.setKnight_id(iddd);
			try {
				TakeOututil.knightManager.KnightMdf(p);
				System.out.println("aarea");
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
