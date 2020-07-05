package model;

import java.sql.Date;

public class BeanUser_info {
	private String usr_id;
	private String add_id;
	private String usr_name;
	private String usr_gt;
	private String usr_pwd;
	private String usr_tel;
	private String usr_eml;
	private String usr_city;
	private Date usr_join;
	private int usr_ifvip;
	private Date usr_vipend;
	public static BeanUser_info currentBeanUser = null;
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
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getUsr_gt() {
		return usr_gt;
	}
	public void setUsr_gt(String usr_gt) {
		this.usr_gt = usr_gt;
	}
	public String getUsr_pwd() {
		return usr_pwd;
	}
	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}
	public String getUsr_tel() {
		return usr_tel;
	}
	public void setUsr_tel(String usr_tel) {
		this.usr_tel = usr_tel;
	}
	public String getUsr_eml() {
		return usr_eml;
	}
	public void setUsr_eml(String usr_eml) {
		this.usr_eml = usr_eml;
	}
	public String getUsr_city() {
		return usr_city;
	}
	public void setUsr_city(String usr_city) {
		this.usr_city = usr_city;
	}
	public Date getUsr_join() {
		return usr_join;
	}
	public void setUsr_join(Date usr_join) {
		this.usr_join = usr_join;
	}
	public int getUsr_ifvip() {
		return usr_ifvip;
	}
	public void setUsr_ifvip(int usr_ifvip) {
		this.usr_ifvip = usr_ifvip;
	}
	public Date getUsr_vipend() {
		return usr_vipend;
	}
	public void setUsr_vipend(Date usr_vipend) {
		this.usr_vipend = usr_vipend;
	}
	
}
