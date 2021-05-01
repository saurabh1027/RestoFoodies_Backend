package com.RestoFoodies1.model;

public class Order {
	private int oid;
	private String name;
	private String status;
	private String location;
	private float total_price;
	private String contact;
	private String username;
	public Order(int oid, String name, String status, String location, float total_price, String contact , String username) {
		super();
		this.oid = oid;
		this.name = name;
		this.status = status;
		this.location = location;
		this.total_price = total_price;
		this.contact = contact;
		this.username = username;
	}
	public Order(String name, String status, String location, float total_price, String contact , String username) {
		super();
		this.name = name;
		this.status = status;
		this.location = location;
		this.total_price = total_price;
		this.contact = contact;
		this.username = username;
	}
	public Order() {
		super();
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", name=" + name + ", status=" + status + ", location=" + location
				+ ", total_price=" + total_price + ", contact=" + contact + ", username=" + username + "]";
	}
}
