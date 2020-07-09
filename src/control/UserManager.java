package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import itf.ItfUserManager;
import model.BeanUser_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class UserManager implements ItfUserManager{
	public BeanUser_info UserRegister(String userid,String pwd1,String pwd2) throws BaseException {
		if(userid.equals(""))
			throw new BusinessException("�û�������Ϊ��");
		if(pwd1.equals(""))
			throw new BusinessException("���벻��Ϊ��");
		if(!(pwd2.equals(pwd2)))
			throw new BusinessException("�����������벻һ��");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user_info where usr_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("���˻��Ѵ���");
			rs.close();
			pst.close();
			sql = "insert into user_info(usr_id,usr_pwd,usr_join) values(?,?,now())";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd1);
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
	public BeanUser_info UserLogin(String id, String pwd) throws BaseException {
		// TODO Auto-generated method stub\
		BeanUser_info b = new BeanUser_info();
		Connection conn = null;
		
		try {
			String sql = "SELECT usr_id,usr_pwd FROM user_info " + 
				"where usr_id = ?";
			conn = DBUtil.getConnection();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų� ����");
			b.setUsr_id(rs.getString(1));
			b.setUsr_pwd(rs.getString(2));
			if(!b.getUsr_pwd().equals(pwd)) throw new BusinessException("wrong password!");
			
			pst.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return b;
	}

	@Override
	public void UserMdf(BeanUser_info p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			String sql = "insert into user_info(add_id,usr_name,usr_gt,usr_tel,usr_eml,usr_city) values(?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,p.getAdd_id());
			pst.setString(2, p.getUsr_name());
			pst.setString(3, p.getUsr_gt());
			pst.setString(4, p.getUsr_tel());
			pst.setString(5, p.getUsr_eml());
			pst.setString(6, p.getUsr_city());
			pst.execute();
			pst.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e);
		}
		finally {
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
	public void openVip() throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			String sql = "update user_info set usr_ifvip = 1,usr_vipend = now()+2592000000L where usr_id = ?";
			conn = DBUtil.getConnection();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			pst.execute();
			pst.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e);
		}
		finally {
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
	public Date returnendDate() throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Date ddd = null;
		try {
			String sql = "select usr_vipend from user_info where usr_id = ?";
			conn = DBUtil.getConnection();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ddd = rs.getDate(1);
			}
			pst.close();
			rs.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e);
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return ddd;
	}
}