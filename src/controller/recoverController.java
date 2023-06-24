/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.*;
import model.*;
import view.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author asism
 */
public class recoverController {
    
   
    recoverView view;
    recoverModel model;
    ResultSet rs;
    PreparedStatement pst = null;
    Statement stmt;
    
    String email_check;
    
    
    public recoverController(recoverView view)
    {
        this.view=view;
        new recoveryListener().actionPerformed();
        
    }

      public recoverController(String Email)
    {
        email_check=Email;
        
    }
    
        class recoveryListener
        {
            public void actionPerformed()
            {
                model=view.getUser(); 

            }
             
        }
   public void deleteUser(){

        try {
            Connection conn = myConnection.myDatabase();
            
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE email = '"+email_check+"'");
            
            System.out.println("User Deleted Successfully!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
   
   
   
    }
        
    
    
    
    

