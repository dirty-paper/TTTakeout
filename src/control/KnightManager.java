package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import itf.ItfKnightManager;
import model.BeanBusi_kinds;
import model.BeanKnight_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class KnightManager implements ItfKnightManager{
	public ArrayList<BeanKnight_info> loadallfreeKnight() throws BaseException{
		ArrayList<BeanKnight_info> result = new ArrayList<BeanKnight_info>();
		Connection conn = null;
		String sql = "select * from knight_info where knight_status = '空闲'";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanKnight_info p = new BeanKnight_info();
				p.setKnight_name(rs.getString(2));
				p.setKnight_id(rs.getString(1));
				p.setKnight_level(rs.getString(4));
				p.setKnight_join(rs.getDate(3));
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
	public ArrayList<BeanKnight_info> loadallKnight() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanKnight_info> result = new ArrayList<BeanKnight_info>();
		Connection conn = null;
		String sql = "select * from knight_info";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanKnight_info p = new BeanKnight_info();
				p.setKnight_name(rs.getString(2));
				p.setKnight_id(rs.getString(1));
				p.setKnight_level(rs.getString(4));
				p.setKnight_join(rs.getDate(3));
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
	public void KnightAdd(BeanKnight_info p) throws BaseException {
		// TODO Auto-generated method stub
		if ("".equals(p.getKnight_id())) {
			throw new BusinessException("id输入有误");
		}
		if ("".equals(p.getKnight_name())) {
			throw new BusinessException("名称输入有误");
		}
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "select * from knight_info where knight_id = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getKnight_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("该骑手id已经存在");
			}
			sql = "insert into knight_info values(?,?,now(),'新人','空闲')";
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getKnight_id());
			pst.setString(2,p.getKnight_name());
			pst.execute();
		} catch (SQLException e) {
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
	public void KnightMdf(BeanKnight_info p) throws BaseException {
		// TODO Auto-generated method stub
		if("".equals(p.getKnight_name())) {
			throw new BusinessException("输入信息错误");
		}
		
			Connection conn=null;
			PreparedStatement pst = null;
			try {
				conn = DBUtil.getConnection();
				String sql="update knight_info set knight_name=? ,knight_level = ? where knight_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, p.getKnight_name());
				pst.setString(2, p.getKnight_level());
				pst.setString(3, p.getKnight_id());
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
	public void Knightdlt(BeanKnight_info p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "delete from knight_info where knight_id = ?";
		PreparedStatement pst = null;
		try {
			conn= DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getKnight_id());
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

}
