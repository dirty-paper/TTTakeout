package control;

import java.sql.Connection;
import java.sql.SQLException;

import itf.ItfSystemAdmin;
import util.*;
import model.BeanAdministrator;

public class SystemAdminManager implements ItfSystemAdmin{
	public BeanAdministrator Adminlogin(String id,String pwd) throws BaseException {
		BeanAdministrator b = new BeanAdministrator();
		Connection conn = null;
		
		try {
			String sql = "SELECT adm_id,adm_name,adm_pwd FROM administrator " + 
				"where adm_id = ?";
			conn = DBUtil.getConnection();
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new BusinessException("µÇÂ½ÕËºÅ²» ´æÔÚ");
			b.setAdm_id(rs.getString(1));
			b.setAdm_pwd(rs.getString(3));
			b.setAdm_name(rs.getString(2));
			if(!b.getAdm_pwd().equals(pwd)) throw new BusinessException("wrong password!");

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
	public BeanAdministrator AdminRes(String id, String pwd1, String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
}
