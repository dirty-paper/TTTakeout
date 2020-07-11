package model;

import java.sql.Date;

public class BeanDiscount_own {
	private String discount_id;
	private String usr_id;
	private int count;
	private double discount_value;
	private Date discount_end;
	private String busi_id;
	private int discount_collect;
	public String getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(double discount_value) {
		this.discount_value = discount_value;
	}
	public Date getDiscount_end() {
		return discount_end;
	}
	public void setDiscount_end(Date discount_end) {
		this.discount_end = discount_end;
	}
	public String getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}
	public int getDiscount_collect() {
		return discount_collect;
	}
	public void setDiscount_collect(int discount_collect) {
		this.discount_collect = discount_collect;
	}
	
}
