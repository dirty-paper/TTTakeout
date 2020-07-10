package takeoutstarter;

import control.AddressManager;
import control.BusiManager;
import control.DiscountManager;
import control.DiscountOwn;
import control.FullcutManager;
import control.FullcutOwn;
import control.KindsManager;
import control.KnightManager;
import control.ProductManager;
import control.ShoppingCartManager;
import control.SystemAdminManager;
import control.UserManager;
import itf.ItfAddressManager;
import itf.ItfBusiManager;
import itf.ItfDiscountManager;
import itf.ItfDiscount_own;
import itf.ItfFullcutManager;
import itf.ItfFullcut_own;
import itf.ItfKindsManager;
import itf.ItfKnightManager;
import itf.ItfProductManager;
import itf.ItfShoppingCartManager;
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
	public static ItfDiscount_own discount_own = new DiscountOwn();
	public static ItfFullcut_own fullcut_own = new FullcutOwn();
	public static ItfAddressManager addressManager = new AddressManager();
	public static ItfKnightManager knightManager = new KnightManager();
	public static ItfShoppingCartManager shoppingCartManager =new ShoppingCartManager();
}
