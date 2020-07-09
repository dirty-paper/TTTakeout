package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import itf.ItfFullcut_own;
import model.BeanBusi_discount;
import model.BeanBusi_fullcut;
import model.BeanDiscount_own;
import model.BeanFullcut_own;
import model.BeanUser_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class FullcutOwn implements ItfFullcut_own{

	@Override
	public void FullcutGet(BeanBusi_fullcut p) throws BaseException{
		// TODO Auto-generated method stub
			Connection conn = null;
			PreparedStatement pst = null;
			String sql = "select * from fullcut_own where fullcut_id = ? and usr_id = ?"; 
			try {
				conn = DBUtil.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, p.getFullcut_id());
				pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
				ResultSet rs = pst.executeQuery();
				if(rs.next()) throw new BusinessException("已经领过这张券了噢~");
				rs.close();
				pst.close();
				sql = "Insert into fullcut_own values(?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, p.getFullcut_id());
				pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
				pst.setDouble(3, p.getFullcut_fullvalue());
				pst.setDouble(4, p.getFullcut_cut());
				pst.setInt(5,0);
				pst.setString(6, p.getBusi_id());
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
	public ArrayList<BeanFullcut_own> loadfullcutmine() throws DbException {
		// TODO Auto-generated method stub
		ArrayList<BeanFullcut_own> result = new ArrayList<BeanFullcut_own>();
		Connection conn = null;
		String sql = "select * from fullcut_own where usr_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanFullcut_own p= new BeanFullcut_own();
				p.setFullcut_id(rs.getString(1));
				p.setUsr_id(rs.getString(2));
				p.setNeedValue(rs.getDouble(3));
				p.setValuecut(rs.getDouble(4));
				p.setBusi_id(rs.getString(6));
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
