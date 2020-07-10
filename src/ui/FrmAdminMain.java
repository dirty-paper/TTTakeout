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
import model.BeanUser_info;

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
    private JMenuItem  menuItem_DiscountManager = new JMenuItem("优惠券管理");
    private JMenuItem  menuItem_FullcunManager = new JMenuItem("满减券管理");
    private JMenuItem  menuItem_Buysth=new JMenuItem("商店");
    private JMenuItem  menuItem_changemyself=new JMenuItem("修改个人信息");
    private JMenuItem  menuItem_Myaddress=new JMenuItem("我的地址");
    private JMenuItem  menuItem_getDiscount=new JMenuItem("获取优惠券");
    private JMenuItem  menuItem_getFullcut=new JMenuItem("获取满减券");
    private JMenuItem  menuItem_shopCart=new JMenuItem("购物车");
    private JMenuItem  menuItem_mydiscount=new JMenuItem("我的优惠券");
    private JMenuItem  menuItem_myfullcut=new JMenuItem("我的满减券");
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
	    	menu_Manager.add(menuItem_FullcunManager);
	    	menuItem_FullcunManager.addActionListener(this);
	    	menu_Manager.add(menuItem_DiscountManager);
	    	menuItem_DiscountManager.addActionListener(this);
	    	menu_Manager.add(menuItem_KnightrManager);
	    	menuItem_KnightrManager.addActionListener(this);
	    	menubar.add(menu_Manager);
	    }
		if(BeanUser_info.currentBeanUser!=null) {
	    menu_Buy.add(menuItem_getDiscount);
	    menuItem_getDiscount.addActionListener(this);
	    menu_Buy.add(menuItem_getFullcut);
	    menuItem_getFullcut.addActionListener(this);
	    menu_Buy.add(menuItem_changemyself);
	    menuItem_changemyself.addActionListener(this); 
	    menu_Buy.add(menuItem_shopCart);
	    menuItem_shopCart.addActionListener(this);
	    menu_Buy.add(menuItem_Buysth);
	    menuItem_Buysth.addActionListener(this);
	    menu_Buy.add(menuItem_mydiscount);
	    menuItem_mydiscount.addActionListener(this);
	    menu_Buy.add(menuItem_Myaddress);
	    menuItem_Myaddress.addActionListener(this);
	    menu_Buy.add(menuItem_myfullcut);
	    menuItem_myfullcut.addActionListener(this);
	    menubar.add(menu_Buy);
		} 
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
		else if (e.getSource()==this.menuItem_DiscountManager) {
			FrmDiscountManagerChoose fdmc = new FrmDiscountManagerChoose(this, "商家选择", true);
			fdmc.setVisible(true);
		}else if (e.getSource()==this.menuItem_FullcunManager) {
			FrmFullcutManagerChoose ffmc = new FrmFullcutManagerChoose(this,"商家选择",true);
			ffmc.setVisible(true);
		}else if (e.getSource()==this.menuItem_getDiscount) {
			FrmDiscountChoose fdc = new FrmDiscountChoose(this, "优惠券选择", true);
			fdc.setVisible(true);
		}else if (e.getSource()==this.menuItem_getFullcut) {
			FrmFullcutChoose fdc = new FrmFullcutChoose(this, "优惠券选择", true);
			fdc.setVisible(true);
		}else if (e.getSource()==this.menuItem_mydiscount) {
			FrmMyDiscount fmd = new FrmMyDiscount(this, "我的优惠券", true);
			fmd.setVisible(true);
		}else if(e.getSource()==this.menuItem_myfullcut){
			FrmMyFullcut fmf = new FrmMyFullcut(this, "我的满减券", true);
			fmf.setVisible(true);
		}else if (e.getSource()==this.menuItem_changemyself) {
			FrmUsermdf fum = new FrmUsermdf(this,"修改用户",true);
			fum.setVisible(true);
		}else if (e.getSource() == this.menuItem_Myaddress) {
			FrmAddressManager fam = new FrmAddressManager(this, "地址管理", true);
			fam.setVisible(true);
		}else if (e.getSource()==this.menuItem_KnightrManager) {
			FrmKnightManager fkm = new FrmKnightManager(this, "骑手管理", true);
			fkm.setVisible(true);
		}else if (e.getSource()== this.menuItem_Buysth) {
			FrmChooseBusi fpma = new FrmChooseBusi(this, "请选择商家", true);
			fpma.setVisible(true);
		}else if (e.getSource()==this.menuItem_shopCart) {
			FrmShoppingCartManager fscm = new FrmShoppingCartManager(this, "我的购物车", true);
			fscm.setVisible(true);
		}
	}
}