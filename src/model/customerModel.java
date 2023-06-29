/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asism
 */
public class customerModel {
    
    private String customer_id;
    private String customer_name;
    private String customer_mobile;
    private String customer_billingaddress;
    private String customer_shippingaddress;
    private String customer_email;
    private String customer_nationality;
    
    public customerModel(){
        
    }

    public customerModel(String customer_id, String customer_name, String customer_mobile, String customer_billingaddress, String customer_shippingaddress, String customer_email, String customer_nationality) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_mobile = customer_mobile;
        this.customer_billingaddress = customer_billingaddress;
        this.customer_shippingaddress = customer_shippingaddress;
        this.customer_email = customer_email;
        this.customer_nationality = customer_nationality;
    }
    
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }

    public String getCustomer_billingaddress() {
        return customer_billingaddress;
    }

    public void setCustomer_billingaddress(String customer_billingaddress) {
        this.customer_billingaddress = customer_billingaddress;
    }

    public String getCustomer_shippingaddress() {
        return customer_shippingaddress;
    }

    public void setCustomer_shippingaddress(String customer_shippingaddress) {
        this.customer_shippingaddress = customer_shippingaddress;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_nationality() {
        return customer_nationality;
    }

    public void setCustomer_nationality(String customer_nationality) {
        this.customer_nationality = customer_nationality;
    }
    
    
   
}
