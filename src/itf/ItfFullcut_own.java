package itf;

import java.util.ArrayList;

import model.BeanBusi_fullcut;
import model.BeanFullcut_own;
import util.BaseException;
import util.DbException;

public interface ItfFullcut_own {
	public void FullcutGet(BeanBusi_fullcut p) throws BaseException;
	public ArrayList<BeanFullcut_own> loadfullcutmine() throws BaseException;
	public void usefullcut(BeanFullcut_own p) throws BaseException;
}
