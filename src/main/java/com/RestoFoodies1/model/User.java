package com.RestoFoodies1.model;

public class User {
	private int uid;
	private String username;
	private String password;
	private String fullname;
	private String role;
	private String contact;
	private String email;
	private String location;
	private String latlng;
	private String profile;

	public User() {}

	public User(int uid,String username,String password,String fullname,String role,String contact,String email,String location,String latlng,String profile){
		this.uid = uid;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.latlng = latlng;
        this.profile = profile;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", role=" + role + ", contact=" + contact + ", email=" + email + ", location=" + location + ", latlng=" + 
				latlng + ", profile=" + profile + "]";
	}
}
