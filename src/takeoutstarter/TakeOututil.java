package takeoutstarter;

import control.SystemAdminManager;
import control.UserManager;
import itf.ItfSystemAdmin;
import itf.ItfUserManager;

public class TakeOututil {
	public static ItfSystemAdmin AdminManager = new SystemAdminManager(); 
	public static ItfUserManager UserManager = new UserManager();
}
