/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import view.*;

/**
 *
 * @author asism
 */
public class saleController {
    
    private saleModel model;
    private saleView view;
    
    public saleController(saleModel model, saleView view){
        this.model=model;
        this.view=view;
    }
   
    
    
    //control model objects
    public void setCustomer(String cusomer_name){
        model.setCustomer(cusomer_name);
    }
    
    public String getCustomer(){
        return model.getCustomer();
    }
    
    public void setProduct(String product_name){
        model.setProduct(product_name);
    }
    
    public String getProduct(String product_name){
        return model.getProduct();
    }
    
    
    //control view object
    
    public void updateView(){
//        view.printSales(model.getCustomer(), model.getProduct());
    }
    
}
