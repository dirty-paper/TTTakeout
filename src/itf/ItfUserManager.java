package itf;

import model.BeanUser_info;
import util.BaseException;

public interface ItfUserManager {
	public BeanUser_info UserRegister(String id,String pwd1,String pwd2) throws BaseException;
}
