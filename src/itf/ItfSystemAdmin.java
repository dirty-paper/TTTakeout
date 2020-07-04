package itf;

import model.BeanAdministrator;
import util.BaseException;

public interface ItfSystemAdmin {
	public BeanAdministrator Adminlogin(String id,String pwd) throws BaseException;
	public BeanAdministrator AdminRes(String id,String pwd1,String pwd2) throws BaseException;
}
