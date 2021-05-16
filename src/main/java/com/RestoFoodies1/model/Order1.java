package com.RestoFoodies1.model;

public class Order1 {
	int oid;
    String recipient_name;
    String destination;
    String contact;
    String status;
    String items;
    Float price;
    String branch;
    String rname;
	String dname;
	public Order1(int oid, String recipient_name, String destination, String contact, String status, String items,
			Float price, String branch, String rname, String dname) {
		super();
		this.oid = oid;
		this.recipient_name = recipient_name;
		this.destination = destination;
		this.contact = contact;
		this.status = status;
		this.items = items;
		this.price = price;
		this.branch = branch;
		this.rname = rname;
		this.dname = dname;
	}
	public Order1(String recipient_name, String destination, String contact, String status, String items, Float price,
			String branch, String rname, String dname) {
		super();
		this.recipient_name = recipient_name;
		this.destination = destination;
		this.contact = contact;
		this.status = status;
		this.items = items;
		this.price = price;
		this.branch = branch;
		this.rname = rname;
		this.dname = dname;
	}
	public Order1() {
		super();
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Order1 [oid=" + oid + ", recipient_name=" + recipient_name + ", destination=" + destination
				+ ", contact=" + contact + ", status=" + status + ", items=" + items + ", price=" + price + ", branch="
				+ branch + ", rname=" + rname + "]";
	}
}
