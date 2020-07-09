package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.ItfKindsManager;
import model.BeanBusi_info;
import model.BeanBusi_kinds;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class KindsManager implements ItfKindsManager{

	@Override
	public BeanBusi_kinds addKinds(String busi_id, String kinds_id, String kindsname) throws BaseException {
		// TODO Auto-generated method stub
		if ("".equals(kinds_id)) {
			throw new BusinessException("id输入有误");
		}
		if ("".equals(kindsname)) {
			throw new BusinessException("名称输入有误");
		}
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "select * from busi_kinds where kinds_id = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, kinds_id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("该类别已经存在");
			}
			sql = "insert into busi_kinds(kinds_id,busi_id,kinds_name,kinds_count) values(?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, kinds_id);
			pst.setString(2, busi_id);
			pst.setString(3, kindsname);
			pst.setInt(4, 0);
			pst.execute();
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
		return null;
	}

	@Override
	public void mdfKinds(BeanBusi_kinds p) throws BaseException {
		if("".equals(p.getKinds_name())) {
			throw new BusinessException("输入信息错误");
		}
		
			Connection conn=null;
			PreparedStatement pst = null;
			try {
				conn = DBUtil.getConnection();
				String sql="update busi_product set kinds_name=? where kinds_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, p.getKinds_name());
				pst.setString(2, p.getKinds_id());
				pst.executeUpdate();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
			finally{
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
	public void deleteKinds(BeanBusi_kinds p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "select * from busi_product where kinds_id = ?";
		PreparedStatement pst = null;
		try {
			conn= DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getKinds_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("该类别存在商品，无法删除");
			}
			rs.close();
			pst.close();
			sql = "delete from busi_kind where kinds_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getKinds_id());
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
	public ArrayList<BeanBusi_kinds> loadallKinds() throws BaseException {
		// TODO Auto-generated method stub
			ArrayList<BeanBusi_kinds> result = new ArrayList<BeanBusi_kinds>();

			Connection conn = null;
			String sql = "select * from busi_kinds";
			PreparedStatement pst = null;
			try {
				conn = DBUtil.getConnection();
				pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					BeanBusi_kinds p = new BeanBusi_kinds();
					p.setKinds_id(rs.getString(1));
					p.setBusi_id(rs.getString(2));
					p.setKinds_name(rs.getString(3));
					p.setKinds_count(rs.getInt(4));
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
	public ArrayList<BeanBusi_kinds> loadallKindsbyBusiId(String id) throws BaseException {
		ArrayList<BeanBusi_kinds> result = new ArrayList<BeanBusi_kinds>();
		Connection conn = null;
		String sql = "select * from busi_kinds where busi_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_kinds p = new BeanBusi_kinds();
				p.setKinds_id(rs.getString(1));
				p.setBusi_id(rs.getString(2));
				p.setKinds_name(rs.getString(3));
				p.setKinds_count(rs.getInt(4));
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
