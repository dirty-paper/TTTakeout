package control;

import java.sql.Connection;
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
			throw new BusinessException("用户名不能为空");
		if(pwd1.equals(""))
			throw new BusinessException("密码不能为空");
		if(!(pwd2.equals(pwd2)))
			throw new BusinessException("两次密码输入不一致");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user_info where usr_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("该账户已存在");
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
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
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
}