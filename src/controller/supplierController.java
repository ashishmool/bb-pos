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
    public static supplierModel searchSupplier(String supplier_id){
        
        supplierModel supplier=null;
        try{
            String sql = "SELECT * FROM supplier WHERE s_id = '"+ supplier_id+"' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
            if (rset.next()){
                supplier = new supplierModel(
                rset.getString(1),
                rset.getString(2),
                rset.getString(3),
                rset.getString(4),
                rset.getString(5),
                rset.getString(6),
                rset.getString(7),
                rset.getString(8),
                rset.getString(9),
                rset.getString(10),
                rset.getString(11));
            }
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return supplier;
    }

    public static boolean updateSupplier(supplierModel supplier){
        try{
            String sql = "UPDATE supplier SET s_cname = ?, vat_pan = ?, sup_add = ?, city = ?, email = ?, scon_name = ?, scon_des = ?, sppan = ?, scon_email = ?, scon_mob = ?  WHERE s_id = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(11,supplier.getSupplier_id());
            pst.setObject(1,supplier.getSupplier_name());
            pst.setObject(2,supplier.getSupplier_vatpan());
            pst.setObject(3,supplier.getSupplier_address());
            pst.setObject(4,supplier.getSupplier_city());
            pst.setObject(5,supplier.getSupplier_email());
            pst.setObject(6,supplier.getSupplier_cname());
            pst.setObject(7,supplier.getSupplier_cdesig());
            pst.setObject(8,supplier.getSupplier_cppan());
            pst.setObject(9,supplier.getSupplier_cemail());
            pst.setObject(10,supplier.getSupplier_cmob());
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
    }
    


}
