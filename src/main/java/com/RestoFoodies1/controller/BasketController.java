package com.RestoFoodies1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.RestoFoodies1.dao.BasketDao;
import com.RestoFoodies1.model.Food_Item;
import com.RestoFoodies1.model.Order;
import com.RestoFoodies1.util.DBConnection;

@CrossOrigin
@RestController
public class BasketController {
	BasketDao bdao = new BasketDao(DBConnection.createConnection());
	
	@PostMapping("/get-orders")
	public List<Order> getOrdersByUsername(@RequestBody String username) {
		try {
			return bdao.getOrdersByUsername(username);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/add-order")
	public String addOrder(@RequestBody Order order) {
		try {
			return bdao.addOrder(order);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/delete-order")
	public String deleteOrder(@RequestBody int oid) {
		try {
			return bdao.deleteOrder(oid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-order/{name}")
	public Order getOrder(@PathVariable String name,@RequestBody String username) {
		try {
			return bdao.getOrder(name,username);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/get-food-items-of-order")
	public List<Food_Item> getFoodItemsOfOrder(@RequestBody int oid){
		try {
			return bdao.getFoodItemsOfOrder(oid);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/remove-order-item/{fid}")
	public String removeFoodItemFromOrder(@RequestBody int oid,@PathVariable int fid) {
		try {
			return bdao.removeFoodItemFromOrder(fid,oid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/place-order")
	public String placeOrder(@RequestBody int oid) {
		try {
			return bdao.placeOrder(oid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/cancel-order")
	public String cancelOrder(@RequestBody int oid) {
		try {
			return bdao.cancelOrder(oid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/add-order-item/{oid}")
	public String addItemToOrder(@PathVariable int oid,@RequestBody Food_Item item) {
		try {
			return bdao.addItemToOrder(oid,item);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-restaurant-placed-orders")
	public List<Order> getPlacedOrdersOfRestaurant(@RequestBody int rid){
		try {
			return bdao.getPlacedOrdersOfRestaurant(rid);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/reject-order")
	public String rejectOrder(@RequestBody int oid) {
		try {
			return bdao.rejectOrder(oid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
}
