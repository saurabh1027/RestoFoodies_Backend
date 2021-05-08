package com.RestoFoodies1.model;

public class Restaurant {
	private int rid;
	private String name;
	private String contact;
	private String email;
	private String branch;
	private String categories;
	private String latlng = "0.00/0.00";
	private String username;
    private String profile;
	public Restaurant(int rid, String name, String contact, String email, String branch, String categories,
			String latlng, String profile,String username) {
		super();
		this.rid = rid;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.branch = branch;
		this.categories = categories;
		this.latlng = latlng;
        this.profile = profile;
		this.username = username;
	}
	public Restaurant(String name, String contact, String email, String branch, String categories, String profile , String latlng,
			String username) {
		super();
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.branch = branch;
		this.categories = categories;
		this.latlng = latlng;
        this.profile = profile;
		this.username = username;
	}
	public Restaurant() {
		super();
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) { this.profile = profile; }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Restaurant [rid=" + rid + ", name=" + name + ", contact=" + contact + ", email=" + email + ", branch="
				+ branch + ", categories=" + categories + ", latlng=" + latlng + ", profile=" + profile
				 + ", username=" + username + "]";
	}
}
