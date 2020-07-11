package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import itf.ItfDiscount_own;
import model.BeanBusi_discount;
import model.BeanBusi_fullcut;
import model.BeanDiscount_own;
import model.BeanUser_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class DiscountOwn implements ItfDiscount_own{

	@Override
	public void DiscountGet(BeanBusi_discount p) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "select * from discount_own where discount_id = ? and usr_id = ? and ifused = 0";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getDiscount_id());
			pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("你已经领过这张券了噢~");
			}
			rs.close();
			pst.close();
			sql = "Insert into discount_own values(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getDiscount_id());
			pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
			pst.setInt(3, 0);
			pst.setDouble(4, p.getDiscount_value());
			pst.setDate(5, (Date) p.getDiscount_end());
			pst.setString(6, p.getBusi_id());
			pst.setInt(7, p.getDiscount_collect());
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

	@Override
	public ArrayList<BeanDiscount_own> loadallmine() throws DbException {
		// TODO Auto-generated method stub
		ArrayList<BeanDiscount_own> result = new ArrayList<BeanDiscount_own>();
		Connection conn = null;
		String sql = "select * from discount_own where usr_id = ? and ifused = 0";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanDiscount_own p= new BeanDiscount_own();
				p.setDiscount_id(rs.getString(1));
				p.setBusi_id(rs.getString(6));
				p.setDiscount_value(rs.getDouble(4));
				p.setDiscount_end(rs.getDate(5));
				
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
	public void usediscount(BeanDiscount_own p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "update discount_own set ifused = 1 where discount_id = ? and usr_id = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getDiscount_id());
			pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
			pst.executeUpdate();
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

}













