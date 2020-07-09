package itf;

import java.util.ArrayList;

import model.BeanUser_address;
import util.BaseException;

public interface ItfAddressManager {
	public ArrayList<BeanUser_address> loadAllmine() throws BaseException;
	public void AddressMdf(BeanUser_address p) throws BaseException;
	public void Addressdlt(BeanUser_address p) throws BaseException;
	public void AddressAdd(BeanUser_address p) throws BaseException;
}
