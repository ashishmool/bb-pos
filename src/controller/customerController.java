/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.*;
import database.*;
import model.*;


/**
 *
 * @author ashishmool
 */
public class customerController {
    
    //boolean function to add new customer in table: membership
    public static boolean addCustomer(customerModel customer){
        try{
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO membership VALUES(?,?,?,?,?,?,?)");
            pst.setObject(1,customer.getCustomer_id());
            pst.setObject(2,customer.getCustomer_name());
            pst.setObject(3,customer.getCustomer_mobile());
            pst.setObject(4,customer.getCustomer_billingaddress());
            pst.setObject(5,customer.getCustomer_shippingaddress());
            pst.setObject(6,customer.getCustomer_email());
            pst.setObject(7,customer.getCustomer_nationality());   
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
//boolean function to search customer in table: membership
    public static customerModel searchCustomer(String customer_id){
        
        customerModel customer=null;
        try{
            String sql = "SELECT * FROM membership WHERE c_id = '"+ customer_id+"' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
            if (rset.next()){
                customer = new customerModel(
                rset.getString(1),
                rset.getString(2),
                rset.getString(3),
                rset.getString(4),
                rset.getString(5),
                rset.getString(6),
                rset.getString(7));
            }
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return customer;
    }
    
    //function to search customer by using ID in table: membership
    public static customerModel searchCustomerbyID(String customer_id){
        
        customerModel customer=null;
        try{
            String sql = "SELECT * FROM membership WHERE c_id = '"+ customer_id+"' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
            if (rset.next()){
                customer = new customerModel(
                rset.getString(1),
                rset.getString(2),
                rset.getString(3),
                rset.getString(4),
                rset.getString(5),
                rset.getString(6),
                rset.getString(7));
            }
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return customer;
    }
    
  //boolean function to update existing customer in table: membership  
public static boolean updateCustomer(customerModel customer){
        try{
            String sql = "UPDATE membership SET full_name = ?, mob_no = ?, bill_add = ?, ship_add = ?, email = ?, nationality = ? WHERE c_id = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(7,customer.getCustomer_id());
            pst.setObject(1,customer.getCustomer_name());
            pst.setObject(2,customer.getCustomer_mobile());
            pst.setObject(3,customer.getCustomer_billingaddress());
            pst.setObject(4,customer.getCustomer_shippingaddress());
            pst.setObject(5,customer.getCustomer_email());
            pst.setObject(6,customer.getCustomer_nationality());
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
    }


//boolean function to delete existing customer from table: membership
public static boolean deleteCustomer(customerModel customer){
    try{
        Connection connection = myConnection.myDatabase();
        String sql = "DELETE FROM membership WHERE c_id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1, customer.getCustomer_id());
        int result = pst.executeUpdate();
        return (result>0);
    }
    catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
}

//function to get all exisiting customer from table membership to load into the customers table in view
public static ArrayList<customerModel> getAllCustomers(){
    ArrayList<customerModel> allCustomer = new ArrayList<>();
    try{
        String sql = "SELECT * FROM membership";
        Connection connection = myConnection.myDatabase();
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        
        while (rset.next()){
            String customer_id = rset.getString("c_id");
            String customer_name = rset.getString("full_name");
            String customer_mobile = rset.getString("mob_no");
            String customer_billingaddress = rset.getString("bill_add");
            String customer_shippingaddress = rset.getString("ship_add");
            String customer_email = rset.getString("email");
            String customer_nationality = rset.getString("nationality");
            
            customerModel customer = new customerModel(customer_id,customer_name,customer_mobile,customer_billingaddress,customer_shippingaddress,customer_email,customer_nationality);
            allCustomer.add(customer);
        }
    }
    catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
    return allCustomer;
}

//function to load last customer to display the next customer id for easy addition of customer from table: membership
public int loadLastCustomer(){
    
        int lastCustomer = 0;
        
        
        try {
        
        Connection connection = myConnection.myDatabase();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT c_id FROM membership ORDER BY c_id DESC LIMIT 1");
          
          if (rs.next()) {
              
              
              
              lastCustomer = rs.getInt("c_id");
              
              
          }
          
          rs.close();
          s.close();
          connection.close();
                    
          
      } catch (Exception e) {
      }
     return lastCustomer;
             
      }

}
