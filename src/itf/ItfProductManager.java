package itf;

import java.util.ArrayList;

import model.BeanBusi_product;
import model.BeanForProduct;
import util.BaseException;

public interface ItfProductManager {
	public ArrayList<BeanForProduct> loakallproduct() throws BaseException;
	public void addProduct(BeanBusi_product b) throws BaseException;
	public void ProductMdf(BeanForProduct p) throws BaseException;
	public void deleteProduct(BeanBusi_product p) throws BaseException;
	public ArrayList<BeanBusi_product> loakallproductSearch(String id) throws BaseException;
}
