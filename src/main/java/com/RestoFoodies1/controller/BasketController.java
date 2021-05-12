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
import com.RestoFoodies1.model.Order1;
import com.RestoFoodies1.util.DBConnection;

@CrossOrigin
@RestController
public class BasketController {
	BasketDao bdao = new BasketDao(DBConnection.createConnection());
	
	// In use - start
	
	@PostMapping("/place-order")
	public String placeOrder(@RequestBody Order1 order) {
		try {
			return bdao.placeOrder(order);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-restaurant-placed-orders/{rname}")
	public List<Order1> getRestaurantPlacedOrdersByBranch(@PathVariable String rname,@RequestBody String branch){
		try {
			return bdao.getRestaurantPlacedOrdersByBranch(branch,rname);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/update-items")
	public String updateItems(@RequestBody List<Food_Item> items){
		try {
			return bdao.updateItems(items);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/reject-order")
	public String rejectOrder(@RequestBody int oid) {
		try {
			return bdao.rejectOrder(oid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-orders")
	public List<Order> getOrdersByUsername(@RequestBody String username) {
		try {
			return bdao.getOrdersByUsername(username);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/add-order-item/{oid}")
	public String addItemToOrder(@PathVariable int oid,@RequestBody Food_Item item) {
		try {
			return bdao.addItemToOrder(oid,item);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/update-order")
	public String updateOrder(@RequestBody Order1 order){
		try{
			return bdao.updateOrder(order);
		}catch(Exception e){e.printStackTrace();}
		return "Failure";
	}

	// In use - end

}
