package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import itf.ItfFullcutManager;
import model.BeanBusi_discount;
import model.BeanBusi_fullcut;
import model.BeanFullcut_own;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class FullcutManager implements ItfFullcutManager{

	@Override
	public ArrayList<BeanBusi_fullcut> loadFullcutByBusi_id(String id) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanBusi_fullcut> result = new ArrayList<BeanBusi_fullcut>();
		Connection conn = null;
		String sql = "select * from busi_fullcut where busi_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_fullcut p= new BeanBusi_fullcut();
				p.setFullcut_id(rs.getString(1));
				p.setBusi_id(rs.getString(2));
				p.setFullcut_fullvalue(rs.getDouble(3));
				p.setFullcut_cut(rs.getDouble(4));
				p.setFullcut_if(rs.getInt(5));
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
	public void FullcutAdd(BeanBusi_fullcut p) throws BaseException {
		// TODO Auto-generated method stub
		if(p.getFullcut_id().equals(""))
			throw new BusinessException("id不能为空");
		if(p.getFullcut_cut()<0)
			throw new BusinessException("优惠值不能为负");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from busi_fullcut where fullcut_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getFullcut_id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该优惠券id已存在");
			rs.close();
			pst.close();
			sql = "insert into busi_fullcut values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getFullcut_id());
			pst.setString(2, p.getBusi_id());
			pst.setDouble(3, p.getFullcut_fullvalue());
			pst.setDouble(4, p.getFullcut_cut());
			pst.setInt(5,p.getFullcut_if());
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
	public void FullcutMdf(BeanBusi_fullcut p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		if(p.getFullcut_cut()<0)
			throw new BusinessException("优惠值不能为负");
		try {
			conn = DBUtil.getConnection();
			String sql;
			sql = "update busi_fullcut set fullcut_cut = ?,fullcut_fullvalue = ?,fullcut_if = ? where fullcut_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, p.getFullcut_cut());
			pst.setDouble(2, p.getFullcut_fullvalue());
			pst.setInt(3,p.getFullcut_if());
			pst.setString(4, p.getFullcut_id());
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
	public void FullcutDlt(BeanBusi_fullcut p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "delete from busi_fullcut where fullcut_id = ?";
		PreparedStatement pst = null;
		try {
			conn= DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getFullcut_id());
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
	public ArrayList<BeanBusi_fullcut> loadFullcutAll() throws BaseException {
		ArrayList<BeanBusi_fullcut> result = new ArrayList<BeanBusi_fullcut>();
		Connection conn = null;
		String sql = "select * from busi_fullcut";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_fullcut p = new BeanBusi_fullcut();
				p.setFullcut_id(rs.getString(1));
				p.setBusi_id(rs.getString(2));
				p.setFullcut_fullvalue(rs.getDouble(3));
				p.setFullcut_cut(rs.getDouble(4));
				p.setFullcut_if(rs.getInt(5));
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

}
