package com.RestoFoodies1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RestoFoodies1.model.Food_Item;
// import com.RestoFoodies1.model.Order;
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
			PreparedStatement pstmt = con.prepareStatement("insert into order1(recipient_name,destination,contact,status,items,price,bid,rname,source) values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, order.getRecipient_name());
			pstmt.setString(2, order.getDestination());
			pstmt.setString(3, order.getContact());
			pstmt.setString(4, order.getStatus());
			pstmt.setString(5, order.getItems());
			pstmt.setFloat(6, order.getPrice());
			pstmt.setInt(7, order.getBid());
			pstmt.setString(8, order.getRname());
			pstmt.setString(9, order.getSource());
			return (pstmt.executeUpdate()==1)?"Success":"Failed to place order.";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	public List<Order1> getRestaurantOrdersByBid(String status,int bid,String rname){
		List<Order1> list = new ArrayList<Order1>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from order1 where bid=? and rname=? and status=?");
			pstmt.setInt(1, bid);
			pstmt.setString(2, rname);
			pstmt.setString(3, status);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){
				list.add(new Order1(rst.getInt("oid"), rst.getString("recipient_name"), rst.getString("source"), rst.getString("destination"), rst.getString("contact"),
				rst.getString("status"), rst.getString("items"), rst.getFloat("price"), rst.getInt("bid"), rst.getString("rname"), rst.getString("dname")));
			}
			return list;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}

	public String updateItems(List<Food_Item> list) {
		try {
			for(int i=0;i<list.size();i++) {
				this.updateItem(list.get(i));
			}
			return "Success";
		} catch (Exception e) {e.printStackTrace();}
		return "Database Error";
	}

	// public List<Order1> getOrdersByUsername(String username) {
	// 	List<Order1> list = new ArrayList<Order1>();
	// 	try {
	// 		PreparedStatement pstmt = con.prepareStatement("select * from orders where username = ?");
	// 		pstmt.setString(1, username);
	// 		ResultSet rst = pstmt.executeQuery();
	// 		while(rst.next()) {
	// 			Order1 order = new Order1(rst.getInt("oid"), rst.getString("recipient_name"), rst.getString("source"), rst.getString("destination"), rst.getString("contact"),
	// 					rst.getString("status"), rst.getString("items"), rst.getFloat("price"), rst.getInt("bid"), rst.getString("rname"), rst.getString("dname"));
	// 			list.add(order);
	// 		}
	// 	} catch (Exception e) {e.printStackTrace();}
	// 	return list;
	// }

	// public String addItemToOrder(int oid,Food_Item item) {
	// 	Order order = null;
	// 	try {
	// 		PreparedStatement pstmt = con.prepareStatement("select * from orders where oid = ?");
	// 		pstmt.setInt(1, oid);
	// 		ResultSet rst = pstmt.executeQuery();
	// 		if(rst.next()) {
	// 			order = new Order(rst.getInt("oid"), rst.getString("name"), rst.getString("status"), rst.getString("location"), rst.getFloat("total_price"), rst.getString("contact"), rst.getString("username"));
	// 			if(order.getStatus().trim().equals("Placed"))
	// 				return "Order has been placed already!";
	// 			pstmt = con.prepareStatement("select * from added_items where oid=? and fid=?");
	// 			pstmt.setInt(1, oid);
	// 			pstmt.setInt(2, item.getFid());
	// 			rst = pstmt.executeQuery();
	// 			if(rst.next()) {
	// 				int count = (int)rst.getInt("quantity");
	// 				PreparedStatement pstmt1 = con.prepareStatement("update added_items set quantity = ? where oid=? and fid=?");
	// 				pstmt1.setInt(1, ++count);
	// 				pstmt1.setInt(2, oid);
	// 				pstmt1.setInt(3, item.getFid());	
	// 				int i = pstmt1.executeUpdate();
	// 				if(i==1) {
	// 					pstmt = con.prepareStatement("select total_price from orders where oid = ?");
	// 					pstmt.setInt(1, oid);
	// 					rst = pstmt.executeQuery();
	// 					if(rst.next()) {
	// 						Double price = (double) rst.getFloat("total_price");
	// 						price = price + item.getPrice();
	// 						pstmt = con.prepareStatement("update orders set total_price = ? where oid = ?");
	// 						pstmt.setFloat(1, price.floatValue());
	// 						pstmt.setInt(2, oid);
	// 						i=pstmt.executeUpdate();
	// 						return (i==1)?"Success":"Failed to update order price";
	// 					}else {
	// 						return "Order is not present";
	// 					}
	// 				}
	// 				return "Failed to add item to order";
	// 			}else {
	// 				PreparedStatement pstmt1 = con.prepareStatement("insert into added_items(oid,fid) values(?,?)");
	// 				pstmt1.setInt(1, oid);
	// 				pstmt1.setInt(2, item.getFid());
	// 				int i = pstmt1.executeUpdate();
	// 				if(i==1) {
	// 					pstmt1 = con.prepareStatement("select total_price from orders where oid = ?");
	// 					pstmt1.setInt(1, oid);
	// 					ResultSet rst1 = pstmt1.executeQuery();
	// 					if(rst1.next()) {
	// 						Double price = (double) rst1.getFloat("total_price");
	// 						price = price + item.getPrice();
	// 						pstmt1 = con.prepareStatement("update orders set total_price = ? where oid = ?");
	// 						pstmt1.setFloat(1, price.floatValue());
	// 						pstmt1.setInt(2, oid);
	// 						i=pstmt1.executeUpdate();
	// 						return (i==1)?"Success":"Failed to update order price";
	// 					}else {
	// 						return "Order is not present";
	// 					}
	// 				}else {
	// 					return "Failed to insert item in order";
	// 				}
	// 			}
	// 		}
			
	// 	} catch (Exception e) {e.printStackTrace();}
	// 	return "Database Error";
	// }

	public String updateOrder(Order1 order){
		try{
			PreparedStatement pstmt = con.prepareStatement("update order1 set recipient_name=?,destination=?," +
					"contact=?,status=?,items=?,price=?,bid=?,rname=?,dname=?,source=? where oid=?");
			pstmt.setString(1,order.getRecipient_name());
			pstmt.setString(2,order.getDestination());
			pstmt.setString(3,order.getContact());
			pstmt.setString(4,order.getStatus());
			pstmt.setString(5,order.getItems());
			pstmt.setFloat(6,order.getPrice());
			pstmt.setInt(7,order.getBid());
			pstmt.setString(8,order.getRname());
			pstmt.setString(9, order.getDname());
			pstmt.setString(10, order.getSource());
			pstmt.setInt(11,order.getOid());
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

	public List<Order1> getOrdersByContact(String contact){
		List<Order1> list = new ArrayList<Order1>();
		try{
			PreparedStatement pstmt = con.prepareStatement("select * from order1 where contact=?");
			pstmt.setString(1, contact);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){
				list.add(new Order1(rst.getInt("oid"), rst.getString("recipient_name"), rst.getString("source"), rst.getString("destination"), rst.getString("contact"),
				rst.getString("status"), rst.getString("items"), rst.getFloat("price"), rst.getInt("bid"), rst.getString("rname"), rst.getString("dname")));
			}
			return list;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

	public List<Order1> getOrdersByLocation(String bname){
		List<Order1> orders = new ArrayList<Order1>();
		List<Integer> bids = new ArrayList<Integer>();
		try{
			PreparedStatement pmst = con.prepareStatement("select bid from branch where bname =?");
			pmst.setString(1,bname);
			ResultSet rst = pmst.executeQuery();
			while(rst.next()){
				bids.add(rst.getInt("bid"));
			}
			for(int i=0;i<bids.size();i++){
				pmst = con.prepareStatement("select * from order1 where bid=? and status='Finished'");
				pmst.setInt(1, bids.get(i));
				rst = pmst.executeQuery();
				while(rst.next()){
					orders.add(new Order1(rst.getInt("oid"),
						rst.getString("recipient_name"),
						rst.getString("source"),
						rst.getString("destination"),
						rst.getString("contact"),
						rst.getString("status"),
						rst.getString("items"),
						rst.getFloat("price"),
						rst.getInt("bid"),
						rst.getString("rname"),
						rst.getString("dname")));
				}
			}
			return orders;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

	public List<Order1> getDeliveringOrdersByDname(String dname){
		List<Order1> list = new ArrayList<Order1>();
		try{
			PreparedStatement pstmt = con.prepareStatement("select * from order1 where status='Delivering' and dname=?");
			pstmt.setString(1, dname);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){	
				list.add(new Order1(rst.getInt("oid"),
						rst.getString("recipient_name"),
						rst.getString("source"),
						rst.getString("destination"),
						rst.getString("contact"),
						rst.getString("status"),
						rst.getString("items"),
						rst.getFloat("price"),
						rst.getInt("bid"),
						rst.getString("rname"),
						rst.getString("dname")));
			}
			return list;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	// In use - end

}