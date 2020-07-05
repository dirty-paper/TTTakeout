package control;

import java.util.ArrayList;

import itf.ItfProductManager;
import model.BeanBusi_product;
import util.BaseException;

public class ProductManager implements ItfProductManager{

	@Override
	public ArrayList<BeanBusi_product> loakallproduct() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanBusi_product> result = new ArrayList<BeanBusi_product>();
		return result;
	}

	@Override
	public BeanBusi_product addProduct(String KindsId, String ProductId, String name, double price)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ProductMdf(BeanBusi_product p) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(BeanBusi_product p) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
