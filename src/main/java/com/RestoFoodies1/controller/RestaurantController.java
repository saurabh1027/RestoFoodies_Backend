package com.RestoFoodies1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.RestoFoodies1.model.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	//not sure
	@PostMapping("/get-items-by-fids")
	public List<Food_Item> getItemsByFids(@RequestBody List<Integer> fids){
		return rdao.getItemsByFids(fids);
	}

	@GetMapping("/get-locations")
	public List<String> getLocations(){
		return rdao.getLocations();
	}
	
	@PostMapping("/get-all-city-items")
	public List<Food_Item> getAllCityItems(@RequestBody String city){
		return rdao.getAllItems(city);
	}
	
	// In use - start
	
	@PostMapping("/Restaurant")
	public String saveRestaurant(@RequestBody Restaurant restaurant) {
		return rdao.addRestaurant(restaurant);
	}
	
	@PostMapping("/Restaurant-Profile")
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
	
	@GetMapping("/Restaurants/{rname}")
	public Restaurant getRestaurantByName(@PathVariable("rname") String rname) {
		return rdao.getRestaurantByName(rname);
	}
	
	@GetMapping("/Restaurants/{rid}/Items")
	public List<Food_Item> getRestaurantItems(@PathVariable int rid,@RequestParam("status") String status){
		return rdao.getRestaurantItems(rid,status);
	}
	
	@GetMapping("/Restaurants")
	public List<Restaurant> getRestaurantsByLocation(@RequestParam("location") String location){
		return rdao.getRestaurantsByLocation(location);
	}
	
	@DeleteMapping("/Item/{fid}")
	public String deleteItem(@PathVariable("fid") int fid) {
		return rdao.deleteItem(fid);
	}
	
	@PostMapping("/Item")
	public String addFoodItem(@RequestBody Food_Item food_item) {
		return rdao.addFoodItem(food_item);
	}
	
	@PostMapping("/Item-Profile")
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
	
	@PatchMapping("/Item")
	public String updateItem(@RequestBody Food_Item item) {
		return rdao.updateItem(item);
	}

	@GetMapping("/Users/{username}/Restaurant")
	public Restaurant getRestaurantByUsername(@PathVariable String username) {
		return rdao.getRestaurantByUsername(username);
	}

	//Above are done
	
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
