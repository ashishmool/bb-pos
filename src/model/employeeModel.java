/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class employeModel {

   //define variables
    private String employee_id;
    private String employee_name;
    private String employee_mobile;
    private String employee_temporaryaddress;
    private String employee_permanentaddress;
    private String employee_email;
    private String employee_ppannnumber;
    private String employee_fullname;
    private String employee_mobilenumber;
    private String employee_relationship;
    private String employee_alternatenumber;

    //this is employeeModel constructor
    public employeModel(String employee_id, String employee_name, String employee_mobile, String employee_temporaryaddress, String employee_permanentaddress, String employee_email, String employee_ppannnumber, String employee_fullname, String employee_mobilenumber, String employee_relationship, String employee_alternatenumber) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_mobile = employee_mobile;
        this.employee_temporaryaddress = employee_temporaryaddress;
        this.employee_permanentaddress = employee_permanentaddress;
        this.employee_email = employee_email;
        this.employee_ppannnumber = employee_ppannnumber;
        this.employee_fullname = employee_fullname;
        this.employee_mobilenumber = employee_mobilenumber;
        this.employee_relationship = employee_relationship;
        this.employee_alternatenumber = employee_alternatenumber;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setEmployee_mobile(String employee_mobile) {
        this.employee_mobile = employee_mobile;
    }

    public void setEmployee_temporaryaddress(String employee_temporaryaddress) {
        this.employee_temporaryaddress = employee_temporaryaddress;
    }

    public void setEmployee_permanentaddress(String employee_permanentaddress) {
        this.employee_permanentaddress = employee_permanentaddress;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public void setEmployee_ppannnumber(String employee_ppannnumber) {
        this.employee_ppannnumber = employee_ppannnumber;
    }

    public void setEmployee_fullname(String employee_fullname) {
        this.employee_fullname = employee_fullname;
    }

    public void setEmployee_mobilenumber(String employee_mobilenumber) {
        this.employee_mobilenumber = employee_mobilenumber;
    }

    public void setEmployee_relationship(String employee_relationship) {
        this.employee_relationship = employee_relationship;
    }

    public void setEmployee_alternatenumber(String employee_alternatenumber) {
        this.employee_alternatenumber = employee_alternatenumber;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_mobile() {
        return employee_mobile;
    }

    public String getEmployee_temporaryaddress() {
        return employee_temporaryaddress;
    }

    public String getEmployee_permanentaddress() {
        return employee_permanentaddress;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public String getEmployee_ppannnumber() {
        return employee_ppannnumber;
    }

    public String getEmployee_fullname() {
        return employee_fullname;
    }

    public String getEmployee_mobilenumber() {
        return employee_mobilenumber;
    }

    public String getEmployee_relationship() {
        return employee_relationship;
    }

    public String getEmployee_alternatenumber() {
        return employee_alternatenumber;
    }
    
    

   
    
    
}
