/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.myConnection;
import java.sql.*;
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
            PreparedStatement pst = connection.prepareStatement("INSERT INTO payment VALUES(?,?,?,?,?,?,?)");
            pst.setObject(1,payment.getInv_id());
            pst.setObject(2,payment.getCustomer());
            pst.setObject(3,payment.getTotal_qty());
            pst.setObject(4,payment.getTotal_amount());
            pst.setObject(5,payment.getPaid_amount());
            pst.setObject(6,payment.getChange());
            pst.setObject(7,payment.getInvoicedate());
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
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
    
}
