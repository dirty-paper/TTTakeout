package itf;

import java.util.ArrayList;

import model.BeanBusi_fullcut;
import util.BaseException;

public interface ItfFullcutManager {
	public ArrayList<BeanBusi_fullcut> loadFullcutByBusi_id(String id) throws BaseException;
	public void FullcutAdd(BeanBusi_fullcut p) throws BaseException;
	public void FullcutMdf(BeanBusi_fullcut p) throws BaseException;
	public void FullcutDlt(BeanBusi_fullcut p) throws BaseException;
	public ArrayList<BeanBusi_fullcut> loadFullcutAll() throws BaseException;
}
