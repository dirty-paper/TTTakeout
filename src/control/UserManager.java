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
}