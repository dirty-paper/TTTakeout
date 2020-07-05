package itf;

import java.util.List;

import model.BeanBusi_info;
import util.BaseException;

public interface ItfBusiManager {
	public List<BeanBusi_info> LoadAllBusi() throws BaseException;
	public BeanBusi_info AddBusi(String id,String name) throws BaseException;
	public void MdfBusi(BeanBusi_info busi) throws BaseException;
	public void deleteBusi(BeanBusi_info busi) throws BaseException;
}
