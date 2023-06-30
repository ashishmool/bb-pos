/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.*;
import database.*;
//import view.*;
import model.*;

public class productController {

    public static boolean addProduct(productModel product) {

        try {
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?,?,?)");
            pst.setObject(1, product.getProductid());
            pst.setObject(2, product.getName());
            pst.setObject(3, product.getBarcode());
            pst.setObject(4, product.getPrice());
            pst.setObject(5, product.getUnit());
            pst.setObject(6, product.getQty());
            pst.setObject(7, product.getSupplierid());
            int result = pst.executeUpdate();
            return (result > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static productModel searchProduct(String productid) {

        productModel product = null;
        try {
            String sql = "SELECT * FROM product WHERE pid = '" + productid + "' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
            if (rset.next()) {
                product = new productModel(
                        rset.getString(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4),
                        rset.getString(5),
                        rset.getString(6),
                        rset.getString(7));
            }
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
        return product;
    }

    public static boolean updateProduct(productModel product) {
        try {
            String sql = "UPDATE product SET  Product_Name = ?, Bar_code = ?, Price = ?,Unit=?, Qty=? , Sid = ? WHERE p_id = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(7, product.getProductid());
            pst.setObject(1, product.getName());
            pst.setObject(2, product.getBarcode());
            pst.setObject(3, product.getPrice());
            pst.setObject(4, product.getUnit());
            pst.setObject(5, product.getQty());
            pst.setObject(6, product.getSupplierid());
            int result = pst.executeUpdate();
            return (result > 0);
        } catch (SQLException e3) {
            System.out.println(e3.getMessage());
        }
        return false;
    }

    public static boolean deleteProduct(productModel product) {
        try {
            Connection connection = myConnection.myDatabase();
            String sql = "DELETE FROM product WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(1, product.getProductid());
            int result = pst.executeUpdate();
            return (result > 0);
        } catch (SQLException e3) {
            System.out.println(e3.getMessage());
        }
        return false;
    }
    
    public static ArrayList<productModel> getAllCustomers(){
    ArrayList<productModel> allProduct = new ArrayList<>();
    try{
        String sql = "SELECT * FROM product";
        Connection connection = myConnection.myDatabase();
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        
        while (rset.next()){
            String productid = rset.getString("pid");
            String name = rset.getString("Product_Name");
            String barcode = rset.getString("Bar_code");
            String price = rset.getString("Price");
            String unit=rset.getString("Unit");
            String qty = rset.getString("Qty");
            String supplierid = rset.getString("Sid");
       
           
            
            productModel product = new productModel(productid,name,barcode,price,unit,qty,supplierid);
            allProduct.add(product);
        }
    }
    catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
    return allProduct;
}

    public static ArrayList<productModel> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static ArrayList<productModel> getAllPoducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
