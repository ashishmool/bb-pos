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
    
    
    public productModel(){
        
    }
    
    public  productModel(int productid,String name,int barcode,int price,String unit,int qty,int supplierid){
        this.productid=productid;
        this.name=name;
        this.barcode=barcode;
        this.price=price;
        this.unit=unit;
        this.qty=qty;
        this.supplierid=supplierid;
        
    }

    public int getProductid() {
        return productid;
    }

    public void setSearchid(int productid) {
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