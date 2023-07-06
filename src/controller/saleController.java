/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.myConnection;
import java.sql.*;
import java.util.ArrayList;
import model.*;
import view.*;

/**
 *
 * @author asism
 */
public class saleController {
    
      public static boolean addSale(paymentModel payment){
        try{
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO payment VALUES(?,?,?,?,?,?,?,?)");
            pst.setObject(1,payment.getInv_id());
            pst.setObject(2,payment.getCustomer());
            pst.setObject(3,payment.getCust_id());
            pst.setObject(4,payment.getTotal_qty());
            pst.setObject(5,payment.getTotal_amount());
            pst.setObject(6,payment.getPaid_amount());
            pst.setObject(7,payment.getChange());
            pst.setObject(8,payment.getInvoicedate());
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
      
      public static ArrayList<paymentModel> getPaymentsByCustomerID(String customerID) {
        ArrayList<paymentModel> payments = new ArrayList<>();
        try {
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM payment WHERE customerid = ?");
            pst.setString(1, customerID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            int inv_id = rs.getInt("invoiceid");
            int cust_id = rs.getInt("customerid");
            String customer = rs.getString("customername");
            int total_qty = rs.getInt("total_qty");
            double total_amount = rs.getDouble("bill_total");
            double paid_amount = rs.getDouble("paid_amount");
            double change = rs.getDouble("change_amount");
            Date invoicedate = rs.getDate("invoicedate");

            paymentModel payment = new paymentModel(inv_id, customer,cust_id, total_qty, total_amount, paid_amount, change, invoicedate);
            payments.add(payment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payments;
    }
    
    public static ArrayList<paymentModel> getAllPayments() {
        ArrayList<paymentModel> payments = new ArrayList<>();
        try {
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM payment");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
            int inv_id = rs.getInt("invoiceid");
            int cust_id = rs.getInt("customerid");
            String customer = rs.getString("customername");
            int total_qty = rs.getInt("total_qty");
            double total_amount = rs.getDouble("bill_total");
            double paid_amount = rs.getDouble("paid_amount");
            double change = rs.getDouble("change_amount");
            Date invoicedate = rs.getDate("invoicedate");
            
            paymentModel payment = new paymentModel(inv_id, customer,cust_id, total_qty, total_amount, paid_amount, change, invoicedate);
            payments.add(payment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payments;
    }
      
public int loadLastInvoiceNumber(){
    
        int lastInvoice = 0;
        
        
        try {
        
        Connection connection = myConnection.myDatabase();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT invoiceid FROM payment ORDER BY invoiceid DESC LIMIT 1");
          
          if (rs.next()) {
              
              
              
              lastInvoice = rs.getInt("invoiceid");
              
              
          }
          
          rs.close();
          s.close();
          connection.close();
                    
          
      } catch (Exception e) {
      }
     return lastInvoice;
             
      }

public static productModel getProductByCode(int productCode) {
    productModel product=null;
        try{
            String sql = "SELECT * FROM product WHERE bar_code = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,productCode);
            ResultSet rset = pst.executeQuery();
            if (rset.next()){
                product = new productModel(
                rset.getString(1),
                rset.getString(2),
                rset.getString(3),
                rset.getString(4),
                rset.getDouble(5),
                rset.getInt(6),
                rset.getString(7));
            }
            pst.close();
            rset.close();
            connection.close();
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return product;
}

public static void updateProduct(productModel product) {
    // Update the product in the database with the new stock quantity
    try{
            String sql = "UPDATE product SET qty = ? WHERE barcode = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,product.getQuantity());
            pst.setString(2, product.getBarcode());

            int result = pst.executeUpdate();
            
        }
        catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
       
}
    
}
