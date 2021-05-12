package com.RestoFoodies1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.RestoFoodies1.model.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RestoFoodies1.dao.RestaurantDao;
import com.RestoFoodies1.util.DBConnection;

@RestController
@CrossOrigin
public class RestaurantController {
	RestaurantDao rdao = new RestaurantDao(DBConnection.createConnection());
	// String imageLocation = "E:\\VS-Code\\Projects\\Angular\\RestoFoodies\\src\\assets\\images\\";  For Windows
	String imageLocation = "/home/sauru8887/Documents/Github/RestoFoodies/src/assets/images/";
	
	// In use - start
	
	@PostMapping("/add-restaurant")
	public String saveRestaurant(@RequestBody Restaurant restaurant) {
		try {
			return rdao.addRestaurant(restaurant);
		}catch (Exception e) {e.printStackTrace();}
		return "Server Error";
	}
	
	@PostMapping("/add-restaurant-profile")
	public String addRestaurantProfile(@RequestParam("file") MultipartFile file) {
		File f = new File(imageLocation+"restaurants/"+file.getOriginalFilename());
		try {
			if(!f.exists())f.mkdir();
			try {
				file.transferTo(f);
			}catch(FileUploadException e){}catch(IOException e){}catch(Exception e){e.printStackTrace();return "Failure in storing image.";}
			return "Success";
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}	
	
	@PostMapping("/get-items-by-fids")
	public List<Food_Item> getItemsByFids(@RequestBody List<Integer> fids){
		try {
			return rdao.getItemsByFids(fids);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/add-list-order/{rid}")
	public String addOrderToList(@RequestBody int oid,@PathVariable int rid) {
		try {
			return rdao.addOrderToList(oid,rid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	@PostMapping("/get-restaurant/{rname}")
	public Restaurant getRestaurantByRname(@PathVariable String rname) {
		try {
			return rdao.getRestaurantByRname(rname);
		}catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/get-restaurant")
	public Restaurant getRestaurantByName(@RequestBody Restaurant r) {
		try {
			return rdao.getRestaurantByName(r);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/get-restaurant-list-orders/{rid}")
	public List<Order1> getListOrdersOfRestaurantByBranch(@RequestBody String branch,@PathVariable int rid){
		try {
			return rdao.getListOrdersOfRestaurantByBranch(rid,branch);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/get-restaurant-available-items/{rid}")
	public List<Food_Item> getRestaurantAvailableItems(@PathVariable int rid){
		try {
			return rdao.getRestaurantAvailableItems(rid);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@GetMapping("/get-locations")
	public List<String> getLocations(){
		try {
			return rdao.getLocations();
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/get-location-restaurants")
	public List<Restaurant> getRestaurantsByLocation(@RequestBody String location){
		try {
			return rdao.getRestaurantsByLocation(location);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/get-all-city-items")
	public List<Food_Item> getAllCityItems(@RequestBody String city){
		try {
			return rdao.getAllItems(city);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/delete-item")
	public String deleteItem(@RequestBody int fid) {
		try {
			return rdao.deleteItem(fid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/add-food-item")
	public String addFoodItem(@RequestBody Food_Item food_item) {
		try {
			return rdao.addFoodItem(food_item);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/add-food-item-pic")
	public String addFoodItemPic(@RequestParam("file") MultipartFile file) {
		File f = new File(imageLocation+"food_items/"+file.getOriginalFilename());
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
	
	@PostMapping("/update-item")
	public String updateItem(@RequestBody Food_Item item) {
		try {
			return rdao.updateItem(item);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-user-restaurant")
	public Restaurant getRestaurantByUsername(@RequestBody String username) {
		try {
			return rdao.getRestaurantByUsername(username);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/add-category")
	public String addNewCategory(@RequestBody Category category) {
		try {
			return rdao.addNewCategory(category);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/delete-restaurant/{rid}")
	public String deleteRestaurantByRid(@PathVariable int rid) {
		try {
			return rdao.deleteRestaurant(rid);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-categories-by-cnames")
	public List<Category> getCategoriesByCnames(@RequestBody List<String> cnames){
		try {
			return rdao.getRestaurantCategories(cnames);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	@PostMapping("/update-restaurant")
	public String updateRestaurant(@RequestBody Restaurant rest) {
		try {
			return rdao.updateRestaurant(rest);
		} catch (Exception e) {e.printStackTrace();}
		return "Failure";
	}
	
	@PostMapping("/get-food-items/{rid}")
	public List<Food_Item> getFoodItems(@PathVariable int rid,@RequestBody String cname){
		List<Food_Item> list = new ArrayList<Food_Item>();
		try {
			list = (cname.trim().equals("All"))?rdao.getAllFoodItems(rid):rdao.getFoodItems(cname, rid);
		}catch(Exception e) {e.printStackTrace();}
		return list;
	}
	
	@PostMapping("/get-city-keyword-items/{keyword}")
	public List<Food_Item> getItemsOfCityKeyword(@PathVariable String keyword,@RequestBody String city){
		try {
			return rdao.getItemsOfKeyWord(keyword,city);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}

	//In Use - End
	
}
