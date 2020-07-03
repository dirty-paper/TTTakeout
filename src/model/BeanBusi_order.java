package model;

import java.sql.Date;

public class BeanBusi_order {
	private String order_id;
	private String discount_id;
	private String fullcut_id;
	private String usr_id;
	private String add_id;
	private String knight_id;
	private String busi_id;
	private double order_oprc;
	private double order_fprc;
	private Date order_time;
	private Date order_rqtime;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}
	public String getFullcut_id() {
		return fullcut_id;
	}
	public void setFullcut_id(String fullcut_id) {
		this.fullcut_id = fullcut_id;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getAdd_id() {
		return add_id;
	}
	public void setAdd_id(String add_id) {
		this.add_id = add_id;
	}
	public String getKnight_id() {
		return knight_id;
	}
	public void setKnight_id(String knight_id) {
		this.knight_id = knight_id;
	}
	public String getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}
	public double getOrder_oprc() {
		return order_oprc;
	}
	public void setOrder_oprc(double order_oprc) {
		this.order_oprc = order_oprc;
	}
	public double getOrder_fprc() {
		return order_fprc;
	}
	public void setOrder_fprc(double order_fprc) {
		this.order_fprc = order_fprc;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getOrder_rqtime() {
		return order_rqtime;
	}
	public void setOrder_rqtime(Date order_rqtime) {
		this.order_rqtime = order_rqtime;
	}
	
}
