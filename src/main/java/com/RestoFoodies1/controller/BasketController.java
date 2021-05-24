package com.RestoFoodies1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RestoFoodies1.dao.BasketDao;
import com.RestoFoodies1.model.Food_Item;
import com.RestoFoodies1.model.Order1;
import com.RestoFoodies1.util.DBConnection;

@CrossOrigin
@RestController
public class BasketController {
	BasketDao bdao = new BasketDao(DBConnection.createConnection());
	
	// In use - start
	
	@PostMapping("/Order")
	public String placeOrder(@RequestBody Order1 order) {
		return bdao.placeOrder(order);
	}
	
	@GetMapping("/Restaurants/{rname}/Orders")
	public List<Order1> getRestaurantPlacedOrdersByBid(@PathVariable("rname") String rname,
		@RequestParam("status") String status,@RequestParam("bid") String bid){
		return bdao.getRestaurantOrdersByBid(status,Integer.parseInt(bid),rname);
	}
	
	@PatchMapping("/Items")
	public String updateItems(@RequestBody List<Food_Item> items){
		return bdao.updateItems(items);
	}
	
	// @GetMapping("/Customers/{username}/Orders")
	// public List<Order1> getOrdersByUsername(@PathVariable("username") String username) {
	// 	return bdao.getOrdersByUsername(username);
	// }
	
	@PatchMapping("/Order")
	public String updateOrder(@RequestBody Order1 order){
		return bdao.updateOrder(order);
	}
	
	@GetMapping("/Orders")
	public List<Order1> getOrdersByContact(@RequestParam("contact") String contact){
		return bdao.getOrdersByContact(contact);
	}

	// @PostMapping("/add-order-item/{oid}")
	// public String addItemToOrder(@PathVariable int oid,@RequestBody Food_Item item) {
	// 	return bdao.addItemToOrder(oid,item);
	// }

	@GetMapping("/Delivery")
	public List<Order1> getOrdersByLocation(@RequestParam("location") String location){
		return bdao.getOrdersByLocation(location);
	}

	@GetMapping("/Delivery/{dname}/Orders")
	public List<Order1> getDeliveringOrdersByDname(@PathVariable String dname){
		return bdao.getDeliveringOrdersByDname(dname);
	}

	// In use - end

}
