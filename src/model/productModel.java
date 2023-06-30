/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



public class productModel {
    private int productid;
    private String name;
    private int barcode;
    private int price;
    private String unit;
    private int qty;
    private int supplierid;
    private String searchid;
    
    
    public productModel(String productid, String name, String barcode, String price,String unit, String qty, String supplierid,String searchid){
        
    }
    
    public  productModel(int productid,String name,int barcode,int price,String unit,int qty,int supplierid,String searchid){
        this.productid=productid;
        this.name=name;
        this.barcode=barcode;
        this.price=price;
        this.unit=unit;
        this.qty=qty;
        this.supplierid=supplierid;
        this.searchid=searchid;
        
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getSearchid() {
        return searchid;
    }

    public void setSearch(String search) {
        this.searchid = search;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }
    
   
    
  
      
    
    
}