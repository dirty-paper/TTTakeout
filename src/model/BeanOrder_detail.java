package model;

public class BeanOrder_detail {
	private String product_id;
	private String order_id;
	private int product_count;
	private double price;
	private double cheap;
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCheap() {
		return cheap;
	}
	public void setCheap(double cheap) {
		this.cheap = cheap;
	}
	
}
