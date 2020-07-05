package itf;

import java.util.ArrayList;

import model.BeanBusi_product;
import util.BaseException;

public interface ItfProductManager {
	public ArrayList<BeanBusi_product> loakallproduct() throws BaseException;
	public BeanBusi_product addProduct(String KindsId,String ProductId,String name,double price) throws BaseException;
	public void ProductMdf(BeanBusi_product p) throws BaseException;
	public void deleteProduct(BeanBusi_product p) throws BaseException;
}
