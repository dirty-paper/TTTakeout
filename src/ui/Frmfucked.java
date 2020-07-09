package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Frmfucked extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	JLabel lblNewLabel = new JLabel("\u64CD\u4F5C\u6210\u529F");
	JButton btnNewButton = new JButton("\u68FA\u58C1");
	public Frmfucked(JDialog f,String s,boolean b) {
		super(f,s,b);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		lblNewLabel.setBounds(218, 86, 129, 15);
		getContentPane().add(lblNewLabel);
		btnNewButton.setBounds(221, 232, 97, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnNewButton)
			this.setVisible(false);
	}
}
