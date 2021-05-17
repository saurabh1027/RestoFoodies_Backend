package com.RestoFoodies1.model;

public class Branch {
    private int bid;
    private String bname;
    private String location;
    private int rid;
    public Branch(int bid,String bname,String location,int rid){
        this.bid = bid;
        this.bname = bname;
        this.location = location;
        this.rid = rid;
    }
    public Branch(){}
    public int getBid(){
        return bid;
    }
    public String getBname(){
        return bname;
    }
    public String getLocation(){
        return location;
    }
    public int getRid(){
        return rid;
    }
}