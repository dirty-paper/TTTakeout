package itf;

import java.util.ArrayList;

import model.BeanBusi_kinds;
import util.BaseException;

public interface ItfKindsManager {
	public BeanBusi_kinds addKinds(String kinds_id,String busi_id,String kindsname) throws BaseException;
	public void mdfKinds(BeanBusi_kinds p) throws BaseException;
	public void deleteKinds(BeanBusi_kinds p) throws BaseException;
	public ArrayList<BeanBusi_kinds> loadallKinds() throws BaseException;
}
