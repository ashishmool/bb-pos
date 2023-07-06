/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author asism
 */
public class saleModel {
    String customer, product, unit;
    int qty, product_code, inv_id, total_qty;
    double  unit_price, total_price, total_amount, paid_amount,change;
    Date invoicedate;

    
  

    public saleModel(int inv_id, String customer, String product, String unit, int qty, int product_code, int total_qty,  double unit_price, double total_price, double total_amount, double paid_amount, double change, Date invoicedate) {
        this.inv_id = inv_id;
        this.customer = customer;
        this.product = product;
        this.unit = unit;
        this.qty = qty;
        this.product_code = product_code;
        this.total_qty = total_qty;
        
        this.unit_price = unit_price;
        this.total_price = total_price;
        this.total_amount = total_amount;
        this.paid_amount = paid_amount;
        this.change = change;
        this.invoicedate = invoicedate;
    }
    


    public Date getInvoicedate(){
        return invoicedate;
    }
    
    public void setInvoicedate(Date invoicedate){
        this.invoicedate = invoicedate;
    }
    
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public int getProduct_code() {
        return product_code;
    }

    public void setProduct_code(int product_code) {
        this.product_code = product_code;
    }

    public int getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(int total_qty) {
        this.total_qty = total_qty;
    }
    
    public int getInv_id() {
        return inv_id;
    }

    public void setInv_id(int inv_id) {
        this.inv_id = inv_id;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
    
    
    
    
}
