package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import itf.ItfAddressManager;
import model.BeanForProduct;
import model.BeanUser_address;
import model.BeanUser_info;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class AddressManager implements ItfAddressManager{

	@Override
	public ArrayList<BeanUser_address> loadAllmine() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanUser_address> result = new ArrayList<BeanUser_address>();
		Connection conn = null;
		String sql = "select * from user_address where usr_id = ?";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser_info.currentBeanUser.getUsr_id());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanUser_address p = new BeanUser_address();
				p.setAdd_id(rs.getString(1));
				p.setAdd_province(rs.getString(3));
				p.setAdd_city(rs.getString(4));
				p.setAdd_block(rs.getString(5));
				p.setAdd_detail(rs.getString(6));
				p.setAdd_people(rs.getString(7));
				p.setAdd_tel(rs.getString(8));
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
	public void AddressMdf(BeanUser_address p) throws BaseException {
		// TODO Auto-generated method stub
		if(p.getAdd_province()==null || "".equals(p.getAdd_province())){
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_city()==null || "".equals(p.getAdd_city())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_block()==null || "".equals(p.getAdd_block())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_detail()==null || "".equals(p.getAdd_detail())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_people()==null || "".equals(p.getAdd_people())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_tel()==null || "".equals(p.getAdd_tel())) {
			throw new BusinessException("不该空的不能空！");
		}
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="update user_address set add_province=?,add_city=?,add_block=?,add_detail=?,add_people=?,add_tel=? where add_id = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, p.getAdd_province());
				pst.setString(2, p.getAdd_city());
				pst.setString(3, p.getAdd_block());
				pst.setString(4, p.getAdd_detail());
				pst.setString(5, p.getAdd_people());
				pst.setString(6, p.getAdd_tel());
				pst.setString(7, p.getAdd_id());
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
	public void Addressdlt(BeanUser_address p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from user_address where add_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);	
			pst.setString(1, p.getAdd_id());
			pst.execute();
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
	public void AddressAdd(BeanUser_address p) throws BaseException {
		// TODO Auto-generated method stub
		if(p.getAdd_province()==null || "".equals(p.getAdd_province())){
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_city()==null || "".equals(p.getAdd_city())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_block()==null || "".equals(p.getAdd_block())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_detail()==null || "".equals(p.getAdd_detail())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_people()==null || "".equals(p.getAdd_people())) {
			throw new BusinessException("不该空的不能空！");
		}
		if(p.getAdd_tel()==null || "".equals(p.getAdd_tel())) {
			throw new BusinessException("不该空的不能空！");
		}
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="insert into user_address values(?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, p.getAdd_id());
				pst.setString(2, BeanUser_info.currentBeanUser.getUsr_id());
				pst.setString(3, p.getAdd_province());
				pst.setString(4, p.getAdd_city());
				pst.setString(5, p.getAdd_block());
				pst.setString(6, p.getAdd_detail());
				pst.setString(7, p.getAdd_people());
				pst.setString(8, p.getAdd_tel());
				pst.execute();
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

}
