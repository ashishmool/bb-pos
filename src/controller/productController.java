/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.*;
import database.*;
import model.*;


/**
 *
 * @author asism
 */
public class productController {
    
    public static boolean addProduct(productModel product){
        try{
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?,?,?)");
            pst.setObject(1,product.getProduct_id());
            pst.setObject(2,product.getName());
            pst.setObject(3,product.getBarcode());
            pst.setObject(4,product.getUnit());
            pst.setObject(5,product.getPrice());
            pst.setObject(6,product.getQuantity());
            pst.setObject(7,product.getSupplier_id());   
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public static productModel searchProduct(String product_id){
        
        productModel product=null;
        try{
            String sql = "SELECT * FROM product WHERE pid = '"+ product_id+"' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
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
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return product;
    }
    
public static boolean updateProduct(productModel product){
        try{
            String sql = "UPDATE product SET product_des = ?, bar_code = ?, unit = ?, price = ?, qty = ?, supp_id = ? WHERE pid = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(7,product.getProduct_id());
            pst.setObject(1,product.getName());
            pst.setObject(2,product.getBarcode());
            pst.setObject(3,product.getUnit());
            pst.setObject(4,product.getPrice());
            pst.setObject(5,product.getQuantity());
            pst.setObject(6,product.getSupplier_id());
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
    }

public static int getQuantity(String product_id) {
        int quantity = 0;
        try {
            String sql = "SELECT qty FROM product WHERE bar_code = ?";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, product_id);
            ResultSet rset = pst.executeQuery();

            if (rset.next()) {
                quantity = rset.getInt("qty");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quantity;
    }

public static boolean updateProductAfterSale(productModel product) {
    try {
        String sql = "UPDATE product SET qty = ? WHERE bar_code = ?";
        Connection connection = myConnection.myDatabase();
        PreparedStatement pst = connection.prepareStatement(sql);
        
        pst.setObject(1, product.getQuantity());
        pst.setObject(2, product.getBarcode());
        
        
        int result = pst.executeUpdate();
        return (result > 0);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return false;
}

public static boolean deleteProduct(productModel product){
    try{
        Connection connection = myConnection.myDatabase();
        String sql = "DELETE FROM product WHERE pid = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1, product.getProduct_id());
        int result = pst.executeUpdate();
        return (result>0);
    }
    catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
}

public static ArrayList<productModel> getAllProducts(){
    ArrayList<productModel> allProduct = new ArrayList<>();
    try{
        String sql = "SELECT * FROM product";
        Connection connection = myConnection.myDatabase();
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        
        while (rset.next()){
            String product_id = rset.getString("pid");
            String product_name = rset.getString("product_des");
            String product_code = rset.getString("bar_code");
            String product_unit = rset.getString("unit");
            double product_price = rset.getDouble("price");
            int product_qty = rset.getInt("qty");
            String product_supp_id = rset.getString("supp_id");
            
            productModel product = new productModel(product_id,product_name,product_code,product_unit,product_price,product_qty,product_supp_id);
            allProduct.add(product);
        }
    }
    catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
    return allProduct;
}


public static productModel getProduct(String name){
        
        productModel product=null;
        try{
            String sql = "SELECT * FROM product WHERE product_des = '"+ name+"' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
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
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return product;
    }
    
}
