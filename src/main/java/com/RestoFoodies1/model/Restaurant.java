package com.RestoFoodies1.model;

public class Restaurant {
	private int rid;
	private String name;
	private String contact;
	private String email;
	private String categories;
    private String profile;
	private int uid;
	public Restaurant(int rid, String name, String contact, String email, String categories, String profile, int uid) {
		super();
		this.rid = rid;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.categories = categories;
        this.profile = profile;
		this.uid = uid;
	}
	public Restaurant() {
		super();
	}
	public int getRid() {
		return rid;
	}
	public String getName() {
		return name;
	}
	public String getContact() {
		return contact;
	}
	public String getEmail() {
		return email;
	}
	public String getCategories() {
		return categories;
	}
    public String getProfile() {
        return profile;
    }
	public int getUid() {
		return uid;
	}
}
