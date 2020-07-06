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
import util.DBUtil;
import util.DbException;

public class KindsManager implements ItfKindsManager{

	@Override
	public BeanBusi_kinds addKinds(String kinds_id, String busi_id, String kindsname) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mdfKinds(BeanBusi_kinds p) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteKinds(BeanBusi_kinds p) throws BaseException {
		// TODO Auto-generated method stub
		
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
