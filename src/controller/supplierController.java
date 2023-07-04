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
 * @author Sujan
 */
public class supplierController {
    
    public static boolean addSupplier(supplierModel supplier){
        try{
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO supplier VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setObject(1,supplier.getSupplier_id());
            pst.setObject(2,supplier.getSupplier_name());
            pst.setObject(3,supplier.getSupplier_vatpan());
            pst.setObject(4,supplier.getSupplier_address());
            pst.setObject(5,supplier.getSupplier_city());
            pst.setObject(6,supplier.getSupplier_email());
            pst.setObject(7,supplier.getSupplier_cname());
            pst.setObject(8,supplier.getSupplier_cdesig());
            pst.setObject(9,supplier.getSupplier_cppan());
            pst.setObject(10,supplier.getSupplier_cemail());
            pst.setObject(11,supplier.getSupplier_cmob());
            
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    


}
