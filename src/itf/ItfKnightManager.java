package itf;

import java.util.ArrayList;

import model.BeanKnight_info;
import util.BaseException;

public interface ItfKnightManager {
	public ArrayList<BeanKnight_info> loadallKnight() throws BaseException;
	public void KnightAdd(BeanKnight_info p) throws BaseException;
	public void KnightMdf(BeanKnight_info p) throws BaseException;
	public void Knightdlt(BeanKnight_info p) throws BaseException;
}
