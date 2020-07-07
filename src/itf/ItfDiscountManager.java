package itf;

import java.util.ArrayList;

import model.BeanBusi_discount;
import util.BaseException;

public interface ItfDiscountManager {
	public void Discountdlt(BeanBusi_discount p) throws BaseException;
	public ArrayList<BeanBusi_discount> loadDiscount(String id) throws BaseException;
	public void DiscountMdf(BeanBusi_discount p) throws BaseException;
	public void DiscountAdd(BeanBusi_discount p) throws BaseException;
}
