package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import itf.ItfDiscountManager;
import model.BeanBusi_discount;
import model.BeanForProduct;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class DiscountManager implements ItfDiscountManager{

	@Override
	public void Discountdlt(BeanBusi_discount p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "delete from busi_discount where discount_id = ?";
		PreparedStatement pst = null;
		try {
			conn= DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getDiscount_id());
			pst.execute();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public ArrayList<BeanBusi_discount> loadDiscount(String id) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanBusi_discount> result = new ArrayList<BeanBusi_discount>();
		Connection conn = null;
		String sql = "select * from busi_discount where busi_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_discount p= new BeanBusi_discount();
				p.setDiscount_id(rs.getString(1));
				p.setBusi_id(rs.getString(2));
				p.setDiscount_value(rs.getDouble(3));
				p.setDiscount_collect(rs.getInt(4));
				p.setDiscount_end(rs.getDate(6));
				result.add(p);
				
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
		return result;
	}

	@Override
	public void DiscountMdf(BeanBusi_discount p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		if(p.getDiscount_value()<0)
			throw new BusinessException("优惠值不能为负");
		try {
			conn = DBUtil.getConnection();
			String sql;
			sql = "update busi_discount set discount_value = ?,discount_collect = ?,discount_end = ? where discount_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
		
			pst.setDouble(1, p.getDiscount_value());
			pst.setInt(2, p.getDiscount_collect());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			pst.setDate(3,(Date) p.getDiscount_end());
			pst.setString(4, p.getDiscount_id());
			pst.executeUpdate();
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
	public void DiscountAdd(BeanBusi_discount p) throws BaseException {
		// TODO Auto-generated method stub
		if(p.getDiscount_id().equals(""))
			throw new BusinessException("id不能为空");
		if(p.getDiscount_value()<0)
			throw new BusinessException("优惠值不能为负");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from busi_discount where discount_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getDiscount_id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该优惠券id已存在");
			rs.close();
			pst.close();
			sql = "insert into busi_discount values(?,?,?,?,now(),?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getDiscount_id());
			pst.setString(2, p.getBusi_id());
			pst.setDouble(3, p.getDiscount_value());
			pst.setInt(4, p.getDiscount_collect());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			pst.setDate(5,(Date) p.getDiscount_end());
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

}
