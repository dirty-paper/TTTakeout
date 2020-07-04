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
}