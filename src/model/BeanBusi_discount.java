package model;

import java.util.Date;

public class BeanBusi_discount {
	private String discount_id;
	private String busi_id;
	private int discount_value;
	private int discount_collect;
	private Date discount_begin;
	private Date discount_end;
	public String getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}
	public String getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}
	public int getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(int discount_value) {
		this.discount_value = discount_value;
	}
	public int getDiscount_collect() {
		return discount_collect;
	}
	public void setDiscount_collect(int discount_collect) {
		this.discount_collect = discount_collect;
	}
	public Date getDiscount_begin() {
		return discount_begin;
	}
	public void setDiscount_begin(Date discount_begin) {
		this.discount_begin = discount_begin;
	}
	public Date getDiscount_end() {
		return discount_end;
	}
	public void setDiscount_end(Date discount_end) {
		this.discount_end = discount_end;
	}
	
}
