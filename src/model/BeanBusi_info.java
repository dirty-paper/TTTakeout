package model;

public class BeanBusi_info {
	private String busi_id;
	private String busi_name;
	private int busi_level;
	private double busi_average;
	private double busi_perchase;
	public static BeanBusi_info currentBusiness = null;
	public String getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}
	public String getBusi_name() {
		return busi_name;
	}
	public void setBusi_name(String busi_name) {
		this.busi_name = busi_name;
	}
	public int getBusi_level() {
		return busi_level;
	}
	public void setBusi_level(int busi_level) {
		this.busi_level = busi_level;
	}
	public double getBusi_average() {
		return busi_average;
	}
	public void setBusi_average(double busi_average) {
		this.busi_average = busi_average;
	}
	public double getBusi_perchase() {
		return busi_perchase;
	}
	public void setBusi_perchase(double busi_perchase) {
		this.busi_perchase = busi_perchase;
	}
	
}
