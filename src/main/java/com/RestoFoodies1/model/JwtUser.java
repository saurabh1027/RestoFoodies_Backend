package com.RestoFoodies1.model;

public class JwtUser {
	private String token;
	private String role;
	private String message;
	public JwtUser(String token, String role, String message) {
		super();
		this.token = token;
		this.role = role;
		this.message = message;
	}
	public JwtUser() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "JwtUser [token=" + token + ", role=" + role + ", message=" + message + "]";
	}
}
