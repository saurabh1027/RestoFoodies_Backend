package com.RestoFoodies1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.RestoFoodies1.model.Category;
import com.RestoFoodies1.model.Food_Item;
import com.RestoFoodies1.model.Order;
import com.RestoFoodies1.model.Restaurant;

public class RestaurantDao {
	Connection con = null;
	public RestaurantDao(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Food_Item> getItemsByFids(List<Integer> fids){
		List<Food_Item> list = new ArrayList<Food_Item>();
		try {
			for(int i=0;i<fids.size();i++) {
				PreparedStatement pstmt = con.prepareStatement("select * from food_item where fid = ?");
				pstmt.setInt(1, fids.get(i));
				ResultSet rst = pstmt.executeQuery();
				if(rst.next()) {
					list.add(new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid")));
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public Restaurant getRestaurantByRname(String rname) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from restaurant where name =?");
			pstmt.setString(1, rname);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				return new Restaurant(rst.getInt("rid"), rst.getString("name"), rst.getString("contact"), rst.getString("email"), rst.getString("branch"), rst.getString("categories"), rst.getString("latlng"), rst.getString("opening_time"), rst.getString("closing_time"), rst.getString("username"));
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public Restaurant getRestaurantByUsername(String username) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from restaurant where username =?");
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next())return new Restaurant(rst.getInt("rid"), rst.getString("name"), rst.getString("contact"), rst.getString("email"), rst.getString("branch"), rst.getString("categories"), rst.getString("latlng"), rst.getString("opening_time"), rst.getString("closing_time"), rst.getString("username"));
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public List<String> getLocations(){
		List<String> list = new ArrayList<String>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select branch from restaurant");
			while(rst.next()) {
				String str="";
				for(int i=0;i<rst.getString("branch").length();i++) {
					if(rst.getString("branch").charAt(i)==',') {
						if(!list.contains(str))
							list.add(str);
						str="";
					}else {
						str = str + rst.getString("branch").charAt(i);
					}
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public List<Restaurant> getRestaurantsByLocation(String location){
		List<Restaurant> list = new ArrayList<Restaurant>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select * from restaurant");
			while(rst.next()) {
				String str = "";
				List<String> branches=new ArrayList<String>();
				for(int i=0;i<rst.getString("branch").length();i++) {
					if(rst.getString("branch").charAt(i)==',') {
						if(!branches.contains(str))
							branches.add(str);
						str="";
					}else {
						str = str + rst.getString("branch").charAt(i);
					}
				}
				if(branches.contains(location)) {
					Restaurant rest = new Restaurant(rst.getInt("rid"), rst.getString("name"), rst.getString("contact"), rst.getString("email"), rst.getString("branch"), rst.getString("categories"), rst.getString("latlng"), rst.getString("opening_time"), rst.getString("closing_time"), rst.getString("username"));
					list.add(rest);
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public Restaurant getRestaurantByRid(int rid) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from restaurant where rid=?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				Restaurant rest = new Restaurant(rst.getInt("rid"), rst.getString("name"), rst.getString("contact"), rst.getString("email"), rst.getString("branch"), rst.getString("categories"), rst.getString("latlng"), rst.getString("opening_time"), rst.getString("closing_time"), rst.getString("username"));
				return rest;
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String addImageToRestaurantByRid(int rid,String image) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select images from restaurant where rid = ?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				String images = rst.getString("images");
				images = (images=="" || images==null) ? image : images + image;
				pstmt = con.prepareStatement("update restaurant set images = ? where rid = ?");
				pstmt.setString(1, images);
				pstmt.setInt(2, rid);
				int i = pstmt.executeUpdate();
				return (i==1) ? "Success" : "Failed to store image";
			}
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public String addRestaurant(Restaurant rest) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into restaurant(name,contact,email,branch,latlng,opening_time,closing_time,username) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, rest.getName());
			pstmt.setString(2, rest.getContact());
			pstmt.setString(3, rest.getEmail());
			pstmt.setString(4, rest.getBranch());
			pstmt.setString(5, rest.getLatlng());
			pstmt.setString(6, rest.getOpening_time());
			pstmt.setString(7, rest.getClosing_time());
			pstmt.setString(8, rest.getUsername());
			if(pstmt.executeUpdate()==1) {
				return "Restaurant added successfully";
			}else {
				return "Failed to add restaurant";
			}
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public List<Restaurant> getAllRestaurants(String username){
		List<Restaurant> list = new ArrayList<Restaurant>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from restaurant where username = ?");
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Restaurant rest = new Restaurant(rst.getInt("rid"), rst.getString("name"), rst.getString("contact"), rst.getString("email"), rst.getString("branch"), rst.getString("categories"), rst.getString("latlng"), rst.getString("opening_time"), rst.getString("closing_time"), rst.getString("username"));
				list.add(rest);
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public ArrayList<String> getAllRestaurantNames(String username) {
		ArrayList<String> names = new ArrayList<String>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select name,branch from restaurant where username = ?");
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				names.add(rst.getString("name")+"("+rst.getString("branch")+")");
			}
			return names;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public Restaurant getRestaurantByName(Restaurant r) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from restaurant where name = ? and branch = ? and username = ?");
			pstmt.setString(1, r.getName());
			pstmt.setString(2, r.getBranch());
			pstmt.setString(3, r.getUsername());
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				return new Restaurant(rst.getInt("rid"), rst.getString("name"), rst.getString("contact"), rst.getString("email"), rst.getString("branch"), rst.getString("categories"), rst.getString("latlng"), rst.getString("opening_time"), rst.getString("closing_time"), rst.getString("username"));
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String deleteRestaurantImage(String img,int rid) {
		String images = "";
		try {
			PreparedStatement pstmt = con.prepareStatement("select images from restaurant where rid = ?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				images = rst.getString("images");
				if(images.contains(img)) {
					images = images.replace(img+",", "");
					pstmt = con.prepareStatement("update restaurant set images = ? where rid = ?");
					pstmt.setString(1, images);
					pstmt.setInt(2, rid);
					int i = pstmt.executeUpdate();
					return (i==1) ? "Success" : "Failed to delete";
				}
			}else
				return "Database Error";
		} catch (Exception e) {e.printStackTrace();}
		return "Server Error";
	}
	
	public String deleteRestaurant(int rid) {
		try {
			PreparedStatement pstmt = con.prepareStatement("delete from restaurant where rid=?");
			pstmt.setInt(1, rid);
			return (pstmt.executeUpdate()==1) ? "Success" : "Failed to Delete";
		} catch (Exception e) {e.printStackTrace();}
		return "Server Error";
	}
	
	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select * from category");
			while(rst.next()) {
				Category category = new Category(rst.getInt("cid"), rst.getString("cname"), rst.getString("description"));
				list.add(category);
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}
	
	public String updateCategoriesOfRestaurant(int rid,String categories) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update restaurant set categories = ? where rid = ?");
			pstmt.setString(1, categories);
			pstmt.setInt(2, rid);
			int i = pstmt.executeUpdate();
			return (i==1) ? "Success" : "Failed to update Categories";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public List<Category> getRestaurantCategories(int rid){
		List<Category> list = new ArrayList<Category>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select categories from restaurant where rid = ?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				String categories = rst.getString("categories");
				if(categories!=null) {
					String str = "";
					for(int i=0;i<categories.length();i++) {
						if(categories.charAt(i) != ',') {
							str = str + categories.charAt(i);
						}else {
							pstmt = con.prepareStatement("select * from category where cname = ?");
							pstmt.setString(1, str);
							rst = pstmt.executeQuery();
							if(rst.next()) {
								Category category = new Category(rst.getInt("cid"), rst.getString("cname"), rst.getString("description"));
								list.add(category);
							}
							str = "";
						}
					}
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}
	
	public List<Food_Item> getFoodItems(String cname, int rid){
		List<Food_Item> list = new ArrayList<Food_Item>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from food_item where cname = ? and rid = ?");
			pstmt.setString(1, cname);
			pstmt.setInt(2, rid);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Food_Item food_item = new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid"));
				list.add(food_item);
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}
	
	public List<Food_Item> getAllFoodItems(int rid){
		List<Food_Item> list = new ArrayList<Food_Item>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from food_item where rid = ?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Food_Item food_item = new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid"));
				list.add(food_item);
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}
	
	public String addFoodItem(Food_Item food_item) {
		try {
			if(food_item.getKeywords().equals("")) {
				food_item.setKeywords(food_item.getCname().trim());
			}
			PreparedStatement pstmt = con.prepareStatement("select * from food_item where fname = ? and rid = ?");
			pstmt.setString(1, food_item.getFname());
			pstmt.setInt(2, food_item.getRid());
			ResultSet rst = pstmt.executeQuery();
			if(!rst.next()) {
				pstmt = con.prepareStatement("insert into food_item(fname,price,pic,quantity,ingredients,description,vegeterian,cname,rid,ratio,keywords) values(?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, food_item.getFname());
				pstmt.setDouble(2, food_item.getPrice());
				pstmt.setString(3, food_item.getPic());
				pstmt.setInt(4, food_item.getQuantity());
				pstmt.setString(5, food_item.getIngredients());
				pstmt.setString(6, food_item.getDescription());
				pstmt.setBoolean(7, food_item.isVegeterian());
				pstmt.setString(8, food_item.getCname());
				pstmt.setInt(9, food_item.getRid());
				pstmt.setLong(10, food_item.getRatio());
				pstmt.setString(11, food_item.getKeywords());
				int i = pstmt.executeUpdate();
				return (i==1) ? "Success" : "Failed to store data!";
			}else {
				return "Food Item is Already Present";
			}
		}catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public String updateRestaurant(Restaurant rest) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update restaurant set name=?,contact=?,email=?,branch=?,latlng=?,opening_time=?,closing_time=? where rid=?");
			pstmt.setString(1, rest.getName());
			pstmt.setString(2, rest.getContact());
			pstmt.setString(3, rest.getEmail());
			pstmt.setString(4, rest.getBranch());
			pstmt.setString(5, rest.getLatlng());
			pstmt.setString(6, rest.getOpening_time());
			pstmt.setString(7, rest.getClosing_time());
			pstmt.setInt(8, rest.getRid());
			int i = pstmt.executeUpdate();
			return (i==1)?"Success":"Failed to update restaurant";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public List<Food_Item> getTopFoodItems(){
		List<Food_Item> list = new ArrayList<Food_Item>();
		int i=0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select * from food_item order by ratio desc");
			while(i<4) {
				if(rst.next()) {
					Food_Item item = new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid"));
					list.add(item);
					i++;
				}else {
					return list;
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public List<Food_Item> getAllItems(String city){
		List<Food_Item> list = new ArrayList<Food_Item>();
		ResultSet rst = null;
		try {
			Statement stmt = con.createStatement();
			rst = stmt.executeQuery("select * from food_item where status='Available'");
			while(rst.next()) {
				if(!city.equals("")) {
					PreparedStatement pstmt = con.prepareStatement("select branch from restaurant where rid=?");
					pstmt.setInt(1, rst.getInt("rid"));
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						if(city.trim().equals(rs.getString("branch").trim())) {
							Food_Item item = new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid"));
							list.add(item);
						}
					}
				}else {
					Food_Item item = new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid"));
					list.add(item);
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public List<Food_Item> getItemsOfKeyWord(String keyword,String city){
		List<Food_Item> list = new ArrayList<Food_Item>();
		List<Food_Item> list1 = new ArrayList<Food_Item>();
		String keywords = "";
		try {
			if(keyword.trim().equals("All")) {
				list = this.getAllItems(city);
			}else {
				list1 = this.getAllItems(city);
				for(int i=0;i<list1.size();i++) {
					keywords = list1.get(i).getKeywords().trim();
					if(keywords.contains(keyword))list.add(list1.get(i));
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String addNewCategory(Category category) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into category(cname,description) values(?,?)");
			pstmt.setString(1, category.getCname());
			pstmt.setString(2, category.getdescription());
			int i = pstmt.executeUpdate();
			return (i==1)?"Success":"Failed to add category";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public String deleteItem(int fid) {
		try {
			PreparedStatement pstmt = con.prepareStatement("delete from food_item where fid = ?");
			pstmt.setInt(1, fid);
			int i = pstmt.executeUpdate();
			return (i==1)?"Success":"Failed to delete item.";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
//	Same method available in basketdao
	public String updateItem(Food_Item item) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update food_item set fname=?,price=?,pic=?,quantity=?,ingredients=?,description=?,vegeterian=?,keywords=?,status=?,cname=? where fid=?");
			pstmt.setString(1, item.getFname());
			pstmt.setDouble(2, item.getPrice());
			pstmt.setString(3, item.getPic());
			pstmt.setInt(4, item.getQuantity());
			pstmt.setString(5, item.getIngredients());
			pstmt.setString(6, item.getDescription());
			pstmt.setBoolean(7, item.isVegeterian());
			pstmt.setString(8, item.getKeywords());
			pstmt.setString(9, item.getStatus());
			pstmt.setString(10, item.getCname());
			pstmt.setInt(11, item.getFid());
			int i=pstmt.executeUpdate();
			return (i==1)?"Success":"Unable to update.";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public List<Food_Item> getRestaurantAvailableItems(int rid){
		List<Food_Item> list = new ArrayList<Food_Item>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from food_item where rid=? and status='Available'");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				list.add(new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price"), rst.getString("pic"), rst.getInt("quantity"), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid")));
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public List<Food_Item> getAvailableItemsOfRestaurant(int oid,int rid){
		List<Food_Item> list = new ArrayList<Food_Item>();
		List<Integer> fid = new ArrayList<Integer>();
		List<Integer> quantity = new ArrayList<Integer>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from added_items where oid=?");
			pstmt.setInt(1, oid);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				fid.add(rst.getInt("fid"));
				quantity.add(rst.getInt("quantity"));
			}
			for(int i=0;i<fid.size();i++) {
				pstmt = con.prepareStatement("select * from food_item where fid=? and rid=?");
				pstmt.setInt(1, fid.get(i));
				pstmt.setInt(2, rid);
				rst = pstmt.executeQuery();
				if(rst.next()) {
					list.add(new Food_Item(rst.getInt("fid"), rst.getString("fname"), rst.getDouble("price") * quantity.get(i), rst.getString("pic"), rst.getInt("quantity") * quantity.get(i), rst.getString("ingredients"), rst.getString("description"), rst.getBoolean("vegeterian"), rst.getString("ratings"), rst.getLong("ratio"), rst.getString("keywords"), rst.getString("status"), rst.getString("cname"), rst.getInt("rid")));
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String addOrderToList(int oid,int rid) {
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from list where rid=?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				pstmt = con.prepareStatement("update list set oids=? where rid=?");
				pstmt.setString(1, rst.getString("oids")+oid+",");
				pstmt.setInt(2, rid);
				if(pstmt.executeUpdate()!=1)return "Failed to insert order.";
			}else {
				PreparedStatement pstmt1 = con.prepareStatement("insert into list(oids,rid) values(?,?)");
				pstmt1.setString(1, oid+",");
				pstmt1.setInt(2, rid);
				if(pstmt1.executeUpdate()!=1)return "Failed to create list.";
			}
			pstmt = con.prepareStatement("update orders set status='Approved' where oid = ?");
			pstmt.setInt(1, oid);
			return (pstmt.executeUpdate()==1)?"Success":"Failed to update status.";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
	public List<Order> getListOrdersOfRestaurant(int rid){
		List<Order> list = new ArrayList<Order>();
		String oids = "";
		String str = "";
		List<Integer> oid = new ArrayList<Integer>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select oids from list where rid=?");
			pstmt.setInt(1, rid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next())oids = rst.getString("oids");
			for(int i=0;i<oids.length();i++) {
				if(oids.charAt(i)!=',') {
					str = str + oids.charAt(i);
				}else {
					oid.add(Integer.parseInt(str));
					str="";
				}
			}
			for(int i=0;i<oid.size();i++) {
				pstmt = con.prepareStatement("select * from orders where oid=?");
				pstmt.setInt(1, oid.get(i));
				rst = pstmt.executeQuery();
				if(rst.next()) {
					Order order = new Order(rst.getInt("oid"), rst.getString("name"), rst.getString("status"), rst.getString("location"), rst.getFloat("total_price"), rst.getString("contact"), rst.getString("username"));
					list.add(order);
				}
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String changeStatusOfItems(List<Integer> fid) {
		try {
			for(int i=0;i<fid.size();i++) {
				PreparedStatement pstmt = con.prepareStatement("update food_item set status = 'Available' where fid=?");
				pstmt.setInt(1, fid.get(i));
				pstmt.executeUpdate();
			}
			return "Success";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}
	
}
