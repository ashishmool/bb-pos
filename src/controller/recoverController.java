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
import javax.swing.JOptionPane;

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
    
  
    
    public recoverController(recoverModel m, recoverView v){
        model=m;
        view=v;
        initrecoverView();
    }
    
    private void initrecoverView(){
        view.getUser().setUsername(model.getUsername());
//        System.out.println("Retrieved Username"+);
        
    }
    
    public void initrecoverController(){
        
        view.getBtnDeleteAcc().addActionListener(e -> updateUser());
        
    }
    
    
    private void updateUser(){
        
        Connection conn = myConnection.myDatabase();
        
        String sql = "UPDATE users SET uname=?,pass=?,re_pass=?,email=?,sec_ans=? WHERE uname='"+view.getUser()+"'";
        try
        {
            pst = conn.prepareStatement(sql);
            
            model.setUsername(view.getTxtUsername().getText());
            
            JOptionPane.showMessageDialog(null, "Username Saved:: "+model.getUsername(),"Update Information",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("Username Updated::: "+model.getUsername());
            
//            pst.setString(1,txtUsername.getText());
//            pst.setString(2, txtPassword.getText());
//            pst.setString(3, txtPasswordCheck.getText());
//            pst.setString(4, txtEmail.getText());
//            pst.setString(5, txtSecurity.getText());
            pst.execute();
        }
        catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
        
    }
    
    public recoverController(recoverView view)
    {
        this.view=view;
        new recoveryListener().actionPerformed();
        
    }

   
    
        class recoveryListener
        {
            public void actionPerformed()
            {
                model=view.getUser(); 

            }
             
        }

   
   
   
    }
        
    
    
    
    

