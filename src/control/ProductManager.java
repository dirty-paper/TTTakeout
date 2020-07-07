package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import itf.ItfProductManager;
import model.BeanBusi_kinds;
import model.BeanBusi_product;
import model.BeanForProduct;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class ProductManager implements ItfProductManager{

	@Override
	public ArrayList<BeanForProduct> loakallproduct() throws BaseException {
		// TODO Auto-generated method stub
		ArrayList<BeanForProduct> result = new ArrayList<BeanForProduct>();
		Connection conn = null;
		String sql = "select * from forproduct";
		PreparedStatement pst = null;
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				BeanForProduct p = new BeanForProduct();
				p.setProduct_id(rs.getString(1));
				p.setProduct_name(rs.getString(3));
				p.setKinds_name(rs.getString(2));
				p.setBusi_name(rs.getString(4));
				p.setProduct_remain(rs.getInt(5));
				p.setProduct_price(rs.getDouble(6));
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
	public void ProductMdf(BeanForProduct p) throws BaseException {
		// TODO Auto-generated method stub
		if(p.getProduct_name()==null || "".equals(p.getProduct_name()) || p.getProduct_name().length()>10){
			throw new BusinessException("��Ʒ���Ʊ�����1-10��");
		}
		if(p.getProduct_remain()<0) {
			throw new BusinessException("�����������0");
		}
		if(p.getProduct_price()<0) {
			throw new BusinessException("�۸�������0");
		}
		
			Connection conn=null;
			PreparedStatement pst = null;
			try {
				conn = DBUtil.getConnection();
				String sql="update busi_product set product_name=?,product_price=?,product_remain=? where product_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, p.getProduct_name());
				pst.setDouble(2,p.getProduct_price());
				pst.setInt(3, p.getProduct_remain());
				pst.setString(4, p.getProduct_id());
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
	public void deleteProduct(BeanForProduct p) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String sql = "delete from busi_product where product_id = ?";
		PreparedStatement pst = null;
		try {
			conn= DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, p.getProduct_id());
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


	@Override
	public ArrayList<BeanForProduct> loakallproductSearch(String name) throws BaseException {
			// TODO Auto-generated method stub
			ArrayList<BeanForProduct> result = new ArrayList<BeanForProduct>();
			Connection conn = null;
			String sql = "select * from forproduct where product_name like ?";
			PreparedStatement pst = null;
			try {
				conn = DBUtil.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%"+name+"%");
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					BeanForProduct p = new BeanForProduct();
					p.setProduct_id(rs.getString(1));
					p.setProduct_name(rs.getString(3));
					p.setKinds_name(rs.getString(2));
					p.setBusi_name(rs.getString(4));
					p.setProduct_remain(rs.getInt(5));
					p.setProduct_price(rs.getDouble(6));
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
	public void addProduct(BeanBusi_product b) throws BaseException {
		// TODO Auto-generated method stub
		if ("".equals(b.getProduct_id())) {
			throw new BusinessException("id��������");
		}
		if ("".equals(b.getProduct_name())) {
			throw new BusinessException("������������");
		}
		if (b.getProduct_price()<0) throw new BusinessException("�������~");
		if (b.getProduct_remain()<0) throw new BusinessException("��治��Ϊ����~");
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "select * from busi_product where product_id = ?";
		try {
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, b.getProduct_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("�������Ѿ�����");
			}
			sql = "insert into busi_product(product_id,kinds_id,product_name,product_price,product_remain) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, b.getProduct_id());
			pst.setString(3, b.getProduct_name());
			pst.setString(2, b.getKinds_id());
			pst.setDouble(4, b.getProduct_price());
			pst.setInt(5, b.getProduct_remain());
			pst.execute();
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
