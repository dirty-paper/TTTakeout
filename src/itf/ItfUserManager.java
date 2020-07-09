package itf;

import java.sql.Date;

import model.BeanUser_info;
import util.BaseException;

public interface ItfUserManager {
	public BeanUser_info UserRegister(String id,String pwd1,String pwd2) throws BaseException;
	public BeanUser_info UserLogin(String id,String pwd) throws BaseException;
	public void UserMdf(BeanUser_info p) throws BaseException;
	public void openVip() throws BaseException;
	public Date returnendDate() throws BaseException;
}
