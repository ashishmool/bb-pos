/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.usercontrol.recoverModel;
import java.sql.*;
import java.util.*;
import model.*;
import view.*;
import controller.*;

/**
 *
 * @author asism
 */
public class usersDAO extends myConnection {
    
    public boolean add(recoverModel rec)
    {
        PreparedStatement pst = null;
        Connection conn = myConnection.myDatabase();
        
        String sql = "INSERT INTO users(uname,pass,re_pass,email,sec_ans) VALUES (?,?,?,?,?)";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rec.getUsername());
            pst.setString(2, rec.getPass());
            pst.setString(3, rec.getRe_pass());
            pst.setString(4, rec.getEmail());
            pst.setString(5, rec.getSec_ans());
            
            pst.execute();
            
            return true;
            
        }
        catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
        return false;
    }
    
    public static void update(recoverModel rec)
    {
        
        PreparedStatement pst = null;
        Connection conn = myConnection.myDatabase();
        
        String sql = "UPDATE users SET uname=?,pass=?,re_pass=?,email=?,sec_ans=? WHERE uname=?";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rec.getUsername());
            pst.setString(2, rec.getPass());
            pst.setString(3, rec.getRe_pass());
            pst.setString(4, rec.getEmail());
            pst.setString(5, rec.getSec_ans());
            pst.setString(6, rec.getUsername());
            pst.execute();
            
           
            
        }
        catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
       
    }
    
    public static void delete(recoverModel rec)
    {
        try
        {
        Connection conn = myConnection.myDatabase();
        String sql = "DELETE FROM users WHERE email=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        System.out.println("User Deletion Reached!");
        pst.setObject(1, rec.getEmail());
        pst.executeUpdate();
        System.out.println("User Deleted Successfully!");
            
        }
        catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
       
    }
    
       public boolean search(recoverModel rec)
    {
        PreparedStatement pst = null;
        ResultSet rs= null;
        Connection conn = myConnection.myDatabase();
        
        String sql = "SELECT * FROM users WHERE uname=?";
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rec.getUsername());
           
            rs=pst.executeQuery();
            
            if (rs.next())
            {
                rec.setUsername(rs.getString(1));
                rec.setPass(rs.getString(2));
                rec.setRe_pass(rs.getString(3));
                rec.setEmail(rs.getString(4));
                rec.setSec_ans(rs.getString(5));
                
                return true;               
            }
            
            return false;
            
        }
        catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
        return false;
    }
       
       public static void loadUser(String email, recoverModel rec1){
           
           try {
        Connection conn = myConnection.myDatabase();
        String sql = "SELECT * FROM users WHERE email=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            
            rec1.setUsername(rs.getString(1));
            rec1.setPass(rs.getString(2));
            rec1.setRe_pass(rs.getString(3));
            rec1.setEmail(rs.getString(4));
            rec1.setSec_ans(rs.getString(5));

            // Perform any required operations with the recoverModel object rec

        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
           
           
       }
    
    
    
}
