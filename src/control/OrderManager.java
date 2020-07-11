package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import itf.ItfOrderManager;
import model.BeanBusi_order;
import model.BeanKnight_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class OrderManager implements ItfOrderManager{

	@Override
	public ArrayList<BeanBusi_order> loadallOrder() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BeanBusi_order> loadallOrderbyBusi(String id) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BeanBusi_order> loadallOrderbyUser(String id) throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanBusi_order> result = new ArrayList<BeanBusi_order>();
		Connection conn = null;
		String sql = "select * from busi_order where usr_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_order p = new BeanBusi_order();
				p.setOrder_id(rs.getInt(1));
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				p.setOrder_time(rs.getTimestamp(10));
				p.setBusi_id(rs.getString(7));
				p.setOrder_fprc(rs.getDouble(9));
				p.setOrder_status(rs.getString(12));
				result.add(p);
			}
			return result;
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
	public void distributeKnight(BeanBusi_order p,BeanKnight_info bb) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "update busi_order set order_status = '������',knight_id = ? where order_id = ?";
		String sql1 =  "update knight_info set knight_status = 'æ' where knight_id = ?";
		PreparedStatement pst1 = null;
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst1 = conn.prepareStatement(sql1);
			pst.setString(1, bb.getKnight_id());
			pst.setInt(2, p.getOrder_id());
			pst1.setString(1, bb.getKnight_id());
			pst.executeUpdate();
			pst1.executeUpdate();
			pst1.close();
			pst.close();
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
	public void confirmArrive(BeanBusi_order p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "select knight_id from busi_order where order_id = ? and order_status = '������'";
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		String knight = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getOrder_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				knight = rs.getString(1);
			}else {
				throw new BusinessException("���Ķ�����û�б�������~����ȷ���ʹ�");
			}
			rs.close();
			sql = "update busi_order set order_status = '���ʹ�' where order_id = ?";
			String sql1 ="update knight_info set knight_status = '����' where knight_id = ?";
			pst = conn.prepareStatement(sql);
			pst1 = conn.prepareStatement(sql1);
			pst.setInt(1, p.getOrder_id());
			pst1.setString(1, knight);
			pst.executeUpdate();
			pst1.executeUpdate();
			pst1.close();
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
	public ArrayList<BeanBusi_order> loadallOrderUnDlv() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanBusi_order> result = new ArrayList<BeanBusi_order>();
		Connection conn = null;
		String sql = "select * from busi_order where order_status = '�ȴ�����'";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanBusi_order p = new BeanBusi_order();
				p.setOrder_id(rs.getInt(1));
				p.setAdd_id(rs.getString(5));
				p.setBusi_id(rs.getString(7));
				result.add(p);
			}
			return result;
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
