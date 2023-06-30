/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asism
 */
public class supplierModel {
    
    String supplier_id;
    String supplier_name;
    String supplier_vatpan;
    String supplier_address;
    String supplier_city;
    String supplier_email;
    String supplier_cname;
    String supplier_cdesig;
    String supplier_cppan;
    String supplier_cemail;
    String supplier_cmob;
    
    public supplierModel(){
        
    }

    public supplierModel(String supplier_id, String supplier_name, String supplier_vatpan, String supplier_address, String supplier_city, String supplier_email, String supplier_cname, String supplier_cdesig, String supplier_cppan, String supplier_cemail, String supplier_cmob) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_vatpan = supplier_vatpan;
        this.supplier_address = supplier_address;
        this.supplier_city = supplier_city;
        this.supplier_email = supplier_email;
        this.supplier_cname = supplier_cname;
        this.supplier_cdesig = supplier_cdesig;
        this.supplier_cppan = supplier_cppan;
        this.supplier_cemail = supplier_cemail;
        this.supplier_cmob = supplier_cmob;
    }

    
    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_vatpan() {
        return supplier_vatpan;
    }

    public void setSupplier_vatpan(String supplier_vatpan) {
        this.supplier_vatpan = supplier_vatpan;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getSupplier_city() {
        return supplier_city;
    }

    public void setSupplier_city(String supplier_city) {
        this.supplier_city = supplier_city;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }

    public String getSupplier_cname() {
        return supplier_cname;
    }

    public void setSupplier_cname(String supplier_cname) {
        this.supplier_cname = supplier_cname;
    }

    public String getSupplier_cdesig() {
        return supplier_cdesig;
    }

    public void setSupplier_cdesig(String supplier_cdesig) {
        this.supplier_cdesig = supplier_cdesig;
    }

    public String getSupplier_cppan() {
        return supplier_cppan;
    }

    public void setSupplier_cppan(String supplier_cppan) {
        this.supplier_cppan = supplier_cppan;
    }

    public String getSupplier_cemail() {
        return supplier_cemail;
    }

    public void setSupplier_cemail(String supplier_cemail) {
        this.supplier_cemail = supplier_cemail;
    }

    public String getSupplier_cmob() {
        return supplier_cmob;
    }

    public void setSupplier_cmob(String supplier_cmob) {
        this.supplier_cmob = supplier_cmob;
    }
    
    
    
}
