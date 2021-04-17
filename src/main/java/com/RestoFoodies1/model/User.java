package com.RestoFoodies1.model;

public class User {
	private int uid;
	private String username;
	private String password;
	private String fullname;
	private String role;
	private String email;
	private String city;
	private String address;
	private String profile;
	public User() {}
	public User(int uid, String username, String password, String fullname, String role, String email, String city,
			String address, String profile) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.email = email;
		this.city = city;
		this.address = address;
		this.profile = profile;
	}
	public User(String username, String password, String fullname, String role, String email, String city,
			String address, String profile) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.email = email;
		this.city = city;
		this.address = address;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
				+ ", role=" + role + ", email=" + email + ", city=" + city + ", address=" + address + ", profile="
				+ profile + "]";
	}
}
