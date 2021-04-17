package com.RestoFoodies1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection createConnection() {
		Connection con = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {e.printStackTrace();}
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestoFoodies","root","root");
		}catch (SQLException e) {e.printStackTrace();}
		return con;
	}
}
