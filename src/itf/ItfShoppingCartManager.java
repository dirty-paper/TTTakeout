package itf;

import java.util.ArrayList;

import model.BeanBusi_info;
import model.BeanDiscount_own;
import model.BeanForProduct;
import model.BeanFullcut_own;
import model.BeanShoppingCart;
import model.BeanUser_address;
import util.BaseException;

public interface ItfShoppingCartManager {
	public void addincart(BeanForProduct p,int i) throws BaseException;
	public ArrayList<BeanShoppingCart> loadAllproduct() throws BaseException;
	public void cartDlt(BeanShoppingCart p) throws BaseException;
	public void settle(ArrayList<BeanShoppingCart> p,BeanUser_address a,BeanDiscount_own discount,BeanFullcut_own fullcut,double total
			,double after,String rqtime) throws BaseException;
	public double gettotal(ArrayList<BeanShoppingCart> p) throws BaseException;
	public BeanFullcut_own getFullcut(double total) throws BaseException;
	public BeanDiscount_own getDiscount(double total,ArrayList<BeanShoppingCart> p) throws BaseException;
	public void checkifother(BeanBusi_info p) throws BaseException;
}
