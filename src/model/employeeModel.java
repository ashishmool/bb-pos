/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author asism
 */
public class employeeModel {
    
    String employee_id;
    String employee_name;
    String employee_ppan;
    String employee_padd;
    String employee_tadd;
    String employee_mob;
    String employee_email;
    String employee_cname;
    String employee_crela;
    String employee_cmob;
    String employee_caltnum;
    
    public employeeModel(){
        
    }

    public employeeModel(String employee_id, String employee_name, String employee_ppan, String employee_padd, String employee_tadd, String employee_mob, String employee_email, String employee_cname, String employee_crela, String employee_cmob, String employee_caltnum) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_ppan = employee_ppan;
        this.employee_padd = employee_padd;
        this.employee_tadd = employee_tadd;
        this.employee_mob = employee_mob;
        this.employee_email = employee_email;
        this.employee_cname = employee_cname;
        this.employee_crela = employee_crela;
        this.employee_cmob = employee_cmob;
        this.employee_caltnum = employee_caltnum;
    }
    
    

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_ppan() {
        return employee_ppan;
    }

    public void setEmployee_ppan(String employee_ppan) {
        this.employee_ppan = employee_ppan;
    }

    public String getEmployee_padd() {
        return employee_padd;
    }

    public void setEmployee_padd(String employee_padd) {
        this.employee_padd = employee_padd;
    }

    public String getEmployee_tadd() {
        return employee_tadd;
    }

    public void setEmployee_tadd(String employee_tadd) {
        this.employee_tadd = employee_tadd;
    }

    public String getEmployee_mob() {
        return employee_mob;
    }

    public void setEmployee_mob(String employee_mob) {
        this.employee_mob = employee_mob;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_cname() {
        return employee_cname;
    }

    public void setEmployee_cname(String employee_cname) {
        this.employee_cname = employee_cname;
    }

    public String getEmployee_crela() {
        return employee_crela;
    }

    public void setEmployee_crela(String employee_crela) {
        this.employee_crela = employee_crela;
    }

    public String getEmployee_cmob() {
        return employee_cmob;
    }

    public void setEmployee_cmob(String employee_cmob) {
        this.employee_cmob = employee_cmob;
    }

    public String getEmployee_caltnum() {
        return employee_caltnum;
    }

    public void setEmployee_caltnum(String employee_caltnum) {
        this.employee_caltnum = employee_caltnum;
    }
    
    
    
}
