package control;

import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;
import itf.ItfShoppingCartManager;
import model.BeanBusi_info;
import model.BeanDiscount_own;
import model.BeanForProduct;
import model.BeanFullcut_own;
import model.BeanShoppingCart;
import model.BeanUser_address;
import model.BeanUser_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;
import java.sql.*;
import util.*;
public class ShoppingCartManager implements ItfShoppingCartManager{

	@Override
	public void addincart(BeanForProduct p,int i) throws BaseException {
		// TODO Auto-generated method stub
		if(i<=0) throw new BusinessException("数量不能为0");
		java.sql.Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		String sql = "select * from shoppingcart where usr_id = ? and product_id = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(2, p.getProduct_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				sql = "update shoppingcart set product_count = product_count + ? where usr_id = ? and product_id = ?";
				pst1 = conn.prepareStatement(sql);
				pst1.setInt(1, i);
				pst1.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
				pst1.setString(3, p.getProduct_id());
				pst1.executeUpdate();
				pst1.close();
				rs.close();
				return;
			}
			rs.close();
			pst.close();
			sql = "insert into shoppingcart values(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(2, p.getBusi_id());
			pst.setString(3,p.getKinds_id());
			pst.setString(4, p.getProduct_id());
			pst.setString(5, p.getBusi_name());
			pst.setInt(6, i);
			pst.setDouble(7, p.getProduct_price());
			pst.execute();
			pst.close();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if(conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public ArrayList<BeanShoppingCart> loadAllproduct() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanShoppingCart> result = new ArrayList<BeanShoppingCart>();
		java.sql.Connection conn = null;
		String sql = "select * from shoppingcart where usr_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanShoppingCart p = new BeanShoppingCart();
				p.setUsr_id(BeanUser_info.currentBeanUser.getUsr_id());
				p.setProduct_id(rs.getString(4));
				p.setProduct_name(rs.getString(5));
				p.setProduct_count(rs.getInt(6));
				p.setProduct_price(rs.getDouble(7));
				p.setKinds_id(rs.getString(3));
				p.setBusi_id(rs.getString(2));
				result.add(p);
				
			}
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null)
				try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void cartDlt(BeanShoppingCart p) throws BaseException {
		// TODO Auto-generated method stub
		java.sql.Connection conn = null;
		String sql = "delete from shoppingcart where usr_id = ? and product_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(2, p.getProduct_id());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null)
				try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public double gettotal(ArrayList<BeanShoppingCart> p) throws BaseException{
		double total = 0;
		for (int i = 0; i < p.size(); i++) {
			total+=((p.get(i).getProduct_price())*p.get(i).getProduct_count());
		}
			return total;
	}

	@Override
	public BeanFullcut_own getFullcut(double total) throws BaseException {
		// TODO Auto-generated method stub
		java.sql.Connection conn = null;
		PreparedStatement pst = null;
		BeanFullcut_own p = new BeanFullcut_own();
		String sql = "SELECT fullcut_id,cutvalue from fullcut_own where cutvalue in(\r\n" + 
				"	SELECT max(cutvalue) FROM fullcut_own where needvalue <? \r\n" + 
				") and usr_id = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setDouble(1, total);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {		
				p.setFullcut_id(rs.getString(1));
				p.setUsr_id(BeanUser_info.currentBeanUser.getUsr_id());
				p.setValuecut(rs.getDouble(2));
				rs.close();
				pst.close();
				return p;
			}
			else {
				rs.close();
				pst.close();
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null)
				try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void settle(ArrayList<BeanShoppingCart> p, BeanUser_address a,BeanDiscount_own discount, 
			BeanFullcut_own fullcut, double total,String rqtime)throws BaseException {
			// TODO Auto-generated method stub
		
	}
	


}
