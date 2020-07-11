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
import java.text.ParseException;
import java.text.SimpleDateFormat;

import util.*;
public class ShoppingCartManager implements ItfShoppingCartManager{

	@Override
	public void addincart(BeanForProduct p,int i) throws BaseException {
		// TODO Auto-generated method stub
		if(i<=0) throw new BusinessException("数量不能为0");
		java.sql.Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		String sql = "select product_count,product_remain from shoppingcart,busi_product where usr_id = ? \r\n" + 
				"and shoppingcart.product_id = ? and shoppingcart.product_id = busi_product.product_id";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(2, p.getProduct_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) + i > rs.getInt(2)) {
					throw new BusinessException("您购物车内的此商品已超出店家承受能力了噢~");
				}
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
		String sql = "SELECT fullcut_id,cutvalue from fullcut_own where cutvalue =(\r\n" + 
				"				SELECT max(cutvalue) FROM fullcut_own where needvalue < ?\r\n" + 
				"				and usr_id = ? and ifused = 0) ";
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
			BeanFullcut_own fullcut, double total,double after,String rqtime)throws BaseException {
			// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "insert into busi_order(discount_id,fullcut_id,usr_id,add_id,busi_id,order_oprc,order_fprc,"
				+ "order_time,order_rqtime,order_status) values(?,?,?,?,?,?,?,now(),?,?)";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			if (discount==null) pst.setString(1, null);
			else pst.setString(1, discount.getDiscount_id());
			if (fullcut==null) pst.setString(2, null);
			else pst.setString(2, fullcut.getFullcut_id());
			pst.setString(3, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(4, a.getAdd_id());
			pst.setString(5, p.get(0).getBusi_id());
			pst.setDouble(6, total);
			pst.setDouble(7, after);
			if (rqtime==null||"".equals(rqtime)) {
				pst.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()+3600000L));
			}else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pst.setTimestamp(8, new java.sql.Timestamp(sdf.parse(rqtime).getTime()));
			}	
			pst.setString(9, "等待骑手");
			pst.execute();
			pst.close();
			sql = "select max(order_id) from busi_order";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
				break;
			}
			rs.close();
			pst.close();
			sql = "insert into order_detail(product_id,order_id,product_count,price) values(?,?,?,?)";
			for (int i = 0; i <p.size(); i++) {
				pst = conn.prepareStatement(sql);
				pst.setString(1, p.get(i).getProduct_id());
				pst.setInt(2, id);
				pst.setInt(3, p.get(i).getProduct_count());
				pst.setDouble(4, p.get(i).getProduct_price());
				pst.execute();
				pst.close();
			}
		}catch (SQLException | ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
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
	public BeanDiscount_own getDiscount(double total,ArrayList<BeanShoppingCart> p) throws BaseException {
		// TODO Auto-generated method stub
		java.sql.Connection conn = null;
		PreparedStatement pst = null;
		String sql = "SELECT discount_value,discount_id from discount_own where usr_id = ? and ifused = 0 "
				+ "and busi_id = ?\r\n" + 
				"and discount_collect<= all(\r\n" + 
				"	SELECT count(*) from busi_order where usr_id =? and busi_id = ?\r\n" + 
				")";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(2, p.get(0).getBusi_id());
			pst.setString(3, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setString(4, p.get(0).getBusi_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				BeanDiscount_own bdco = new BeanDiscount_own();
				bdco.setDiscount_id(rs.getString(2));
				bdco.setDiscount_value(rs.getDouble(1));
				pst.close();
				rs.close();
				return bdco;
			}else {
				pst.close();
				rs.close();
				return null;
			}
		} catch (Exception e) {
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
	public void checkifother(BeanBusi_info p) throws BaseException {
		// TODO Auto-generated method stub
		java.sql.Connection conn = null;
		PreparedStatement pst = null;
		String sql = "select busi_id from shoppingcart";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next())
			if (rs.getString(1).equals(p.getBusi_id())) {
				return;
			}else {
				throw new BusinessException("购物车内有其他商家的商品，请先结算或清空完噢~");
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
	


}
