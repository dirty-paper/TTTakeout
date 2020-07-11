package itf;

import model.BeanBusi_discount;
import model.BeanDiscount_own;
import util.BaseException;
import util.DbException;
import java.util.ArrayList;

public interface ItfDiscount_own {
	public void DiscountGet(BeanBusi_discount p) throws BaseException;
	public ArrayList<BeanDiscount_own> loadallmine() throws BaseException;
	public void usediscount(BeanDiscount_own p) throws BaseException;
}
