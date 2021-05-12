package com.RestoFoodies1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RestoFoodies1.model.Food_Item;
import com.RestoFoodies1.model.Order;
import com.RestoFoodies1.model.Order1;

public class BasketDao {
	Connection con = null;

	public BasketDao(Connection con) {
		super();
		this.con = con;
	}

	// In use - start

	public String placeOrder(Order1 order) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into order1(recipient_name,destination,contact,status,items,price,branch,rname) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, order.getRecipient_name());
			pstmt.setString(2, order.getDestination());
			pstmt.setString(3, order.getContact());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getItems());
			pstmt.setFloat(6, order.getPrice());
			pstmt.setString(7, order.getBranch());
			pstmt.setString(8, order.getRname());
			return (pstmt.executeUpdate()==1)?"Success":"Failed to place order.";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public List<Order1> getRestaurantPlacedOrdersByBranch(String branch,String rname){
		List<Order1> list = new ArrayList<Order1>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from order1 where rname=? and branch=? and status=?");
			pstmt.setString(1, rname);
			pstmt.setString(2, branch);
			pstmt.setString(3,"Placed");
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				list.add(new Order1(rst.getInt("oid"), rst.getString("recipient_name"), rst.getString("destination"), rst.getString("contact"),
						rst.getString("status"), rst.getString("items"), rst.getFloat("price"), rst.getString("branch"), rst.getString("rname")));
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}

	public String updateItems(List<Food_Item> list) {
		try {
			for(int i=0;i<list.size();i++) {
				if(!this.updateItem(list.get(i)).equals("Success"))
					return "Unable to update all items.";
			}
			return "Success";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public String rejectOrder(int oid) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update order1 set status='Rejected' where oid=?");
			pstmt.setInt(1, oid);
			return (pstmt.executeUpdate()==1)?"Success":"Failed to reject order.";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public List<Order> getOrdersByUsername(String username) {
		List<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from orders where username = ?");
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Order order = new Order(rst.getInt("oid"), rst.getString("name"), rst.getString("status"), rst.getString("location"), rst.getFloat("total_price"), rst.getString("contact"), rst.getString("username"));
				list.add(order);
			}
		} catch (Exception e) {e.printStackTrace();}
		return list;
	}

	public String addItemToOrder(int oid,Food_Item item) {
		Order order = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from orders where oid = ?");
			pstmt.setInt(1, oid);
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				order = new Order(rst.getInt("oid"), rst.getString("name"), rst.getString("status"), rst.getString("location"), rst.getFloat("total_price"), rst.getString("contact"), rst.getString("username"));
				if(order.getStatus().trim().equals("Placed"))
					return "Order has been placed already!";
				pstmt = con.prepareStatement("select * from added_items where oid=? and fid=?");
				pstmt.setInt(1, oid);
				pstmt.setInt(2, item.getFid());
				rst = pstmt.executeQuery();
				if(rst.next()) {
					int count = (int)rst.getInt("quantity");
					PreparedStatement pstmt1 = con.prepareStatement("update added_items set quantity = ? where oid=? and fid=?");
					pstmt1.setInt(1, ++count);
					pstmt1.setInt(2, oid);
					pstmt1.setInt(3, item.getFid());	
					int i = pstmt1.executeUpdate();
					if(i==1) {
						pstmt = con.prepareStatement("select total_price from orders where oid = ?");
						pstmt.setInt(1, oid);
						rst = pstmt.executeQuery();
						if(rst.next()) {
							Double price = (double) rst.getFloat("total_price");
							price = price + item.getPrice();
							pstmt = con.prepareStatement("update orders set total_price = ? where oid = ?");
							pstmt.setFloat(1, price.floatValue());
							pstmt.setInt(2, oid);
							i=pstmt.executeUpdate();
							return (i==1)?"Success":"Failed to update order price";
						}else {
							return "Order is not present";
						}
					}
					return "Failed to add item to order";
				}else {
					PreparedStatement pstmt1 = con.prepareStatement("insert into added_items(oid,fid) values(?,?)");
					pstmt1.setInt(1, oid);
					pstmt1.setInt(2, item.getFid());
					int i = pstmt1.executeUpdate();
					if(i==1) {
						pstmt1 = con.prepareStatement("select total_price from orders where oid = ?");
						pstmt1.setInt(1, oid);
						ResultSet rst1 = pstmt1.executeQuery();
						if(rst1.next()) {
							Double price = (double) rst1.getFloat("total_price");
							price = price + item.getPrice();
							pstmt1 = con.prepareStatement("update orders set total_price = ? where oid = ?");
							pstmt1.setFloat(1, price.floatValue());
							pstmt1.setInt(2, oid);
							i=pstmt1.executeUpdate();
							return (i==1)?"Success":"Failed to update order price";
						}else {
							return "Order is not present";
						}
					}else {
						return "Failed to insert item in order";
					}
				}
			}
			
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public String updateOrder(Order1 order){
		try{
			PreparedStatement pstmt = con.prepareStatement("update order1 set recipient_name=?,destination=?," +
					"contact=?,status=?,items=?,price=?,branch=?,rname=? where oid=?");
			pstmt.setString(1,order.getRecipient_name());
			pstmt.setString(2,order.getDestination());
			pstmt.setString(3,order.getContact());
			pstmt.setString(4,order.getStatus());
			pstmt.setString(5,order.getItems());
			pstmt.setFloat(6,order.getPrice());
			pstmt.setString(7,order.getBranch());
			pstmt.setString(8,order.getRname());
			pstmt.setInt(9,order.getOid());
			return (pstmt.executeUpdate()==1) ? "Success" : "Unable to update order.";
		}catch(Exception e){e.printStackTrace();}
		return "Database Error";
	}

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
	
	// In use - end

}