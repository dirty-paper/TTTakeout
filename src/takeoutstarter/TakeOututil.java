package takeoutstarter;

import control.BusiManager;
import control.DiscountManager;
import control.FullcutManager;
import control.KindsManager;
import control.ProductManager;
import control.SystemAdminManager;
import control.UserManager;
import itf.ItfBusiManager;
import itf.ItfDiscountManager;
import itf.ItfFullcutManager;
import itf.ItfKindsManager;
import itf.ItfProductManager;
import itf.ItfSystemAdmin;
import itf.ItfUserManager;

public class TakeOututil {
	public static ItfSystemAdmin AdminManager = new SystemAdminManager(); 
	public static ItfUserManager UserManager = new UserManager();
	public static ItfBusiManager busiManager = new BusiManager();
	public static ItfProductManager productManager = new ProductManager();
	public static ItfKindsManager kindsManager = new KindsManager();
	public static ItfDiscountManager discountManager = new DiscountManager();
	public static ItfFullcutManager fullcutManager =  new FullcutManager();
}
