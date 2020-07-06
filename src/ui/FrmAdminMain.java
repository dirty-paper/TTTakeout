package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.SystemAdminManager;
import model.BeanAdministrator;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmAdminMain extends JFrame implements ActionListener {
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("系统管理");
    private JMenu menu_Buy=new JMenu("买");
    private JMenu menu_search=new JMenu("查询统计");
    private JMenuItem  menuItem_UserManager=new JMenuItem("用户管理");
    private JMenuItem  menuItem_BusiManager=new JMenuItem("商家管理");
    private JMenuItem  menuItem_KnightrManager=new JMenuItem("骑手管理");
    private JMenuItem  menuItem_ProductManager=new JMenuItem("商品管理");
    private JMenuItem  menuItem_KindsManager=new JMenuItem("商品类别管理");
    
    private JMenuItem  menuItem_Buysth=new JMenuItem("购买");
    
    private JMenuItem  menuItem_UserPerchaseSearch=new JMenuItem("用户购买查询");
    private JMenuItem  menuItem_BusiSaleSearch=new JMenuItem("商家售出查询");
    private JMenuItem  menuItem_ProductSaleStatic=new JMenuItem("商品售出统计");
    private JMenuItem  menuItem_BusiSaleStatic=new JMenuItem("商家出售统计");
    
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	public FrmAdminMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统");
		if(BeanAdministrator.currentloginAdministrator!=null)
	    if("管理员".equals(BeanAdministrator.currentloginAdministrator.getAdm_name())){
	    	menu_Manager.add(menuItem_UserManager);
	    	menuItem_UserManager.addActionListener(this);
	    	menu_Manager.add(menuItem_BusiManager);
	    	menuItem_BusiManager.addActionListener(this);
	    	menu_Manager.add(menuItem_KindsManager);
	    	menuItem_KindsManager.addActionListener(this);
	    	menu_Manager.add(menuItem_ProductManager);
	    	menuItem_ProductManager.addActionListener(this);
	    	menu_Manager.add(menuItem_KnightrManager);
	    	menuItem_KnightrManager.addActionListener(this);
	    	menubar.add(menu_Manager);
	    }
	    menu_Buy.add(menuItem_Buysth);
	    menuItem_Buysth.addActionListener(this);
	    menubar.add(menu_Buy);
	    menu_search.add(this.menuItem_UserPerchaseSearch);
	    menuItem_UserPerchaseSearch.addActionListener(this);
	    menu_search.add(this.menuItem_BusiSaleSearch);
	    menuItem_BusiSaleSearch.addActionListener(this);
	    menu_search.add(this.menuItem_ProductSaleStatic);
	    menuItem_ProductSaleStatic.addActionListener(this);
	    menu_search.add(this.menuItem_BusiSaleStatic);
	    menuItem_BusiSaleStatic.addActionListener(this);
	    menubar.add(this.menu_search);
	    this.setJMenuBar(menubar);
	    //鐘舵�佹爮
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("你好!");
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.menuItem_BusiManager) {
			FrmBusiManager fbm = new FrmBusiManager(this,"商户管理",true);
			fbm.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_KindsManager) {
			FrmKindsManager fkm = new FrmKindsManager(this,"商品类别管理",true);
			fkm.setVisible(true);
		}
		else if (e.getSource()==this.menuItem_ProductManager) {
			FrmProductManager fpm = new FrmProductManager(this, "商品管理", true);
			fpm.setVisible(true);
		}
	}
}