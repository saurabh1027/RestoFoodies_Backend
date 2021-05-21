package com.RestoFoodies1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.RestoFoodies1.model.JwtUser;
import com.RestoFoodies1.model.User;

public class UserDao {
	Connection con = null;
	
	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	// In use - start
	
	public User getUserByUsername(String username) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from user where username = ?");
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next())return new User(rst.getInt("uid"), rst.getString("username"), rst.getString("password"), rst.getString("fullname"), rst.getString("role"), rst.getString("contact"), rst.getString("email"), rst.getString("location"), rst.getString("latlng"), rst.getString("profile"));
		}catch (Exception e) {e.printStackTrace();}
		return null;
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
	
	public String updateUser(User user) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update user set password=?,fullname=?,role=?,contact=?,email=?,location=?,latlng=?,profile=?,username=? where uid=?");
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getFullname());
			pstmt.setString(3, user.getRole());
			pstmt.setString(4, user.getContact());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getLocation());
			pstmt.setString(7, user.getLatlng());
			pstmt.setString(8, user.getProfile());
			pstmt.setString(9, user.getUsername());
			pstmt.setInt(10, user.getUid());
			return (pstmt.executeUpdate()==1)?"Success":"Data updation failed!";
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
	
	public String saveUser(User user) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into user(username,password,fullname,role,contact,email,location,latlng) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, user.getUsername().trim());
			pstmt.setString(2, user.getPassword().trim());
			pstmt.setString(3, user.getFullname().trim());
			pstmt.setString(4, user.getRole().trim());
			pstmt.setString(5, user.getContact().trim());
			pstmt.setString(6, user.getEmail().trim());
			pstmt.setString(7, user.getLocation().trim());
			pstmt.setString(8, user.getLatlng().trim());
			return (pstmt.executeUpdate()==1) ? "Success":"Username Exists";
		}catch(Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public List<User> getUsersByRole(String role){
		List<User> list = new ArrayList<User>();
		try{
			PreparedStatement pstmt = con.prepareStatement("select * from user where role = ?");
			pstmt.setString(1,role);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){
				list.add(new User(rst.getInt("uid"), rst.getString("username"), rst.getString("password"), rst.getString("fullname"), rst.getString("role"), rst.getString("contact"), rst.getString("email"), rst.getString("location"), rst.getString("latlng"), rst.getString("profile")));
			}
			return list;
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	// In use - end
	
	// public boolean isLoggedIn(String username) {
	// 	try {
	// 		return (this.getUserByUsername(username)==null)?false:true;
	// 	} catch (Exception e) {e.printStackTrace();}
	// 	return false;
	// }

}
