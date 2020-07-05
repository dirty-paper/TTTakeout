package takeoutstarter;

import control.BusiManager;
import control.SystemAdminManager;
import control.UserManager;
import itf.ItfBusiManager;
import itf.ItfSystemAdmin;
import itf.ItfUserManager;

public class TakeOututil {
	public static ItfSystemAdmin AdminManager = new SystemAdminManager(); 
	public static ItfUserManager UserManager = new UserManager();
	public static ItfBusiManager busiManager = new BusiManager();
}
