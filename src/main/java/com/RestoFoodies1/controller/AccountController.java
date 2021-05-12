package com.RestoFoodies1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RestoFoodies1.dao.UserDao;
import com.RestoFoodies1.model.JwtUser;
import com.RestoFoodies1.model.User;
import com.RestoFoodies1.util.DBConnection;
import com.RestoFoodies1.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin
@RestController
public class AccountController {
	UserDao udao = new UserDao(DBConnection.createConnection());
	@Autowired
	private JwtUtil jwtUtil;
	String imageLocation= "E:\\VS-Code\\Projects\\Angular\\RestoFoodies\\src\\assets\\images\\";
	
	// In use - start
	
	@PostMapping("/ValidateToken")
	public User getUserByToken(@RequestBody String token) {
		try {
			return udao.getUserByUsername(jwtUtil.extractUsername(token));
		}catch(ExpiredJwtException e) {System.out.println("Jwt Expired:this print statement is located at accountController-78");}
		catch(Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/authenticate-user")
	public JwtUser authenticate(@RequestBody User user) {
		JwtUser jwt = new JwtUser();
		try {
			jwt = udao.authenticateUser(user);
			if(jwt.getMessage().trim().equals("Valid")) {
				UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername().trim(),user.getPassword().trim(),new ArrayList<>());
				jwt.setToken(jwtUtil.generateToken(userDetails));
			}
		}catch(Exception e) {e.printStackTrace();}
		return jwt;
	}
	
	@PostMapping("/update-user")
	public String updateUser(@RequestBody User user) {
		try {
			return udao.updateUser(user);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/update-user-pic")
	public String updateUserPic(@RequestParam("file") MultipartFile file) {
		File f = new File(imageLocation+"user_pictures//"+file.getOriginalFilename());
		try {
			if(!f.exists()) {
				f.mkdir();
			}
			try {
				file.transferTo(f);
			}catch(FileUploadException e){}catch(IOException e){}catch(Exception e){e.printStackTrace();return "Failure in storing image.";}
			return "Success";
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-user")
	public User getUserProfile(@RequestBody String username) {
		User user;
		try {
			user = udao.getUserByUsername(username.trim());
			if(user!=null) {
				return user;
			}
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/delete-user")
	public String deleteUser(@RequestBody String username) {
		try {
			return udao.deleteUser(username);
		} catch (Exception e) {e.printStackTrace();}
		return "Server Error";
	}
	
	@PostMapping("/save-user")
	public JwtUser saveUser(@RequestBody User user) {
		JwtUser jwt = new JwtUser();
		String message="";
		try {
			message = udao.saveUser(user);
			if(message.trim()=="Success") {
				UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername().trim(),user.getPassword().trim(),new ArrayList<>());
				return new JwtUser(jwtUtil.generateToken(userDetails), user.getRole().trim(), "Success");
			}else return new JwtUser("","",message.trim());
		}catch(Exception e) {e.printStackTrace();}
		return jwt;
	}

	// In use - end
	
	// @PostMapping("/isLoggedIn")
	// public boolean isLoggedIn(@RequestBody String token) {
	// 	try {
	// 		return udao.isLoggedIn(this.jwtUtil.extractUsername(token));
	// 	} catch (Exception e) {e.printStackTrace();}
	// 	return false;
	// }

}