package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.ItfBusiManager;
import model.BeanBusi_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class BusiManager implements ItfBusiManager{
	@Override
	public List<BeanBusi_info> LoadAllBusi() throws BaseException {
		List<BeanBusi_info> result = new ArrayList<BeanBusi_info>();
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "select * from busi_info where busi_rmtime is null";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_info p = new BeanBusi_info();
				p.setBusi_id(rs.getString(1));
				p.setBusi_name(rs.getString(2));
				p.setBusi_level(rs.getInt(3));
				p.setBusi_average(rs.getDouble(4));
				p.setBusi_perchase(rs.getDouble(5));
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
	public BeanBusi_info AddBusi(String id, String name) throws BaseException {
		// TODO Auto-generated method stub
		if(id.equals(""))
			throw new BusinessException("id不能为空");
		if(name.equals(""))
			throw new BusinessException("商户名不能为空");
	
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from busi_info where busi_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该商户已存在");
			rs.close();
			pst.close();
			sql = "insert into busi_info(busi_id,busi_name,busi_level) values(?,?,1)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, name);
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
		return null;
	}

	@Override
	public void MdfBusi(BeanBusi_info busi) throws BaseException {
		// TODO Auto-generated method stub
		if(busi.getBusi_name()==null || "".equals(busi.getBusi_name()) || busi.getBusi_name().length()>10){
			throw new BusinessException("商户名称必须是1-10字");
		}
		if(busi.getBusi_level()<0) {
			throw new BusinessException("星级必须大于0");
		}
		
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select * from busi_info where busi_id=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, busi.getBusi_id());
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("商家不存在");
				rs.close();
				pst.close();
				sql="update busi_info set busi_name=?,busi_level=? where busi_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, busi.getBusi_name());
				pst.setInt(2,busi.getBusi_level());
				pst.setString(3, busi.getBusi_id());
				pst.execute();
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
	public void deleteBusi(BeanBusi_info busi) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "select * from busi_kinds where busi_id = ? and kinds_rmtime is null";
		PreparedStatement pst = null;
		try {
			conn= DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, busi.getBusi_id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该商户已存在类别,不能删除");
			rs.close();
			pst.close();
			sql = "update busi_info set busi_rmtime = now() where busi_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, busi.getBusi_id());
			pst.executeUpdate();
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

}
