package itf;

import java.util.ArrayList;

import model.BeanBusi_order;
import model.BeanKnight_info;
import model.BeanOrder_detail;
import util.BaseException;

public interface ItfOrderManager {
	public ArrayList<BeanBusi_order> loadallOrder() throws BaseException;
	public ArrayList<BeanBusi_order> loadallOrderbyBusi(String id) throws BaseException;
	public ArrayList<BeanBusi_order> loadallOrderbyUser(String id) throws BaseException;
	public ArrayList<BeanBusi_order> loadallOrderUnDlv() throws BaseException;
	public void distributeKnight(BeanBusi_order p,BeanKnight_info bb) throws BaseException;
	public void confirmArrive(BeanBusi_order p) throws BaseException;
}
