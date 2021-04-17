package com.RestoFoodies1.model;

public class Food_Item {
	private int fid;
	private String fname;            
	private double price;
	private String pic;
	private int quantity;
	private String ingredients;
	private String description;
	private boolean vegeterian;
	private String ratings;
	private long ratio;
	private String keywords;
	private String status;
	private String cname;
	private int rid;
	public Food_Item() {
		super();
	}
	public Food_Item(int fid, String fname, double price, String pic, int quantity, String ingredients,
			String description, boolean vegeterian, String ratings, long ratio, String keywords, String status,
			String cname, int rid) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.price = price;
		this.pic = pic;
		this.quantity = quantity;
		this.ingredients = ingredients;
		this.description = description;
		this.vegeterian = vegeterian;
		this.ratings = ratings;
		this.ratio = ratio;
		this.keywords = keywords;
		this.status = status;
		this.cname = cname;
		this.rid = rid;
	}
	public Food_Item(String fname, double price, String pic, int quantity, String ingredients, String description,
			boolean vegeterian, String ratings, long ratio, String keywords, String status, String cname, int rid) {
		super();
		this.fname = fname;
		this.price = price;
		this.pic = pic;
		this.quantity = quantity;
		this.ingredients = ingredients;
		this.description = description;
		this.vegeterian = vegeterian;
		this.ratings = ratings;
		this.ratio = ratio;
		this.keywords = keywords;
		this.status = status;
		this.cname = cname;
		this.rid = rid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isVegeterian() {
		return vegeterian;
	}
	public void setVegeterian(boolean vegeterian) {
		this.vegeterian = vegeterian;
	}
	public String getRatings() {
		return ratings;
	}
	public void setRatings(String ratings) {
		this.ratings = ratings;
	}
	public long getRatio() {
		return ratio;
	}
	public void setRatio(long ratio) {
		this.ratio = ratio;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "Food_Item [fid=" + fid + ", fname=" + fname + ", price=" + price + ", pic=" + pic + ", quantity="
				+ quantity + ", ingredients=" + ingredients + ", description=" + description + ", vegeterian="
				+ vegeterian + ", ratings=" + ratings + ", ratio=" + ratio + ", keywords=" + keywords + ", status="
				+ status + ", cname=" + cname + ", rid=" + rid + "]";
	}
}
