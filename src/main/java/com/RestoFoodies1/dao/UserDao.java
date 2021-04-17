package com.RestoFoodies1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RestoFoodies1.model.JwtUser;
import com.RestoFoodies1.model.User;

public class UserDao {
	Connection con = null;
	
	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public JwtUser authenticateUser(User user) {
		JwtUser jwt = new JwtUser("","Customer","Database Error");
		try {
			if(user.getUsername()==null || user.getUsername()=="")
				jwt.setMessage("Empty Username");
			else {
				PreparedStatement pstmt = con.prepareStatement("select * from user where username=?");
				pstmt.setString(1, user.getUsername().trim());
				ResultSet rst = pstmt.executeQuery();
				if(rst.next()) {
					if(user.getPassword()==null || user.getPassword()=="")
						jwt.setMessage("Empty Password");
					else if(user.getPassword().trim().equals(rst.getString("password"))) {
						jwt.setRole(rst.getString("role").trim());
						jwt.setMessage("Valid");
					}else jwt.setMessage("Invalid Password");
				}else jwt.setMessage("Invalid Username");
			}
		}catch(Exception e) {e.printStackTrace();}
		return jwt;
	}
	
	public String saveUser(User user) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into user(username,password,fullname,role,email,address) values(?,?,?,?,?,?)");
			pstmt.setString(1, user.getUsername().trim());
			pstmt.setString(2, user.getPassword().trim());
			pstmt.setString(3, user.getFullname().trim());
			pstmt.setString(4, user.getRole().trim());
			pstmt.setString(5, user.getEmail().trim());
			pstmt.setString(6, user.getAddress().trim());
			int i = pstmt.executeUpdate();
			if(i==1) {
				if(!user.getRole().trim().equals("Customer"))return "Success";
				pstmt = con.prepareStatement("insert into basket(username) values(?)");
				pstmt.setString(1, user.getUsername());
				i = pstmt.executeUpdate();
				return (i==1) ? "Success":"Failed to add basket";
			}
			else return "Username Exists";
		}catch(Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public User getUserByUsername(String username) {
		User user;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from user where username = ?");
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				user = new User(rst.getInt(1), rst.getString("username"), rst.getString("password"), rst.getString("fullname"), rst.getString("role"), rst.getString("email"), rst.getString("city"), rst.getString("address"), rst.getString("profile"));
				return user;
			}
		}catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String updateUser(User user) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update user set password=?,fullname=?,role=?,email=?,address=?,profile=? where uid=?");
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getFullname());
			pstmt.setString(3, user.getRole());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getAddress());
			pstmt.setString(6, user.getProfile());
			pstmt.setInt(7, user.getUid());
			int i = pstmt.executeUpdate();
			return (i==1)?"Success":"Data updation failed!";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public String deleteUser(String username) {
		try {
			PreparedStatement pstmt = con.prepareStatement("delete from user where username = ?");
			pstmt.setString(1, username);
			int i = pstmt.executeUpdate();
			return (i==1)?"Success":"Unable to delete user";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

}
