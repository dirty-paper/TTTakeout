package control;

import java.util.ArrayList;

import itf.ItfKindsManager;
import model.BeanBusi_kinds;
import util.BaseException;

public class KindsManager implements ItfKindsManager{

	@Override
	public BeanBusi_kinds addKinds(String kinds_id, String busi_id, String kindsname) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mdfKinds(BeanBusi_kinds p) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteKinds(BeanBusi_kinds p) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<BeanBusi_kinds> loadallKinds() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanBusi_kinds> result = new ArrayList<BeanBusi_kinds>();
		return result;
	}
	
}
