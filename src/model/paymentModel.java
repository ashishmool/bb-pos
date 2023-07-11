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
public class paymentModel {
    String customer;
    int inv_id, cust_id,total_qty;
    double  total_amount, paid_amount,change;
    Date invoicedate;


    public paymentModel(int inv_id, String customer, int cust_id, int total_qty, double total_amount, double paid_amount, double change, Date invoicedate) {
        this.inv_id = inv_id;
        this.cust_id=cust_id;
        this.customer = customer;
        this.total_qty = total_qty;
        this.total_amount = total_amount;
        this.paid_amount = paid_amount;
        this.change = change;
        this.invoicedate = invoicedate;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
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
