/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.myConnection;
import model.*;
import view.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author asism
 */
public class registerController {
    
    registerModel model;
    registerView view;
    ResultSet rs;
    PreparedStatement pst=null;
    
    public registerController(registerView view)
    {
        this.view = view;
        
        new registerListener().actionPerformed();
    }
    
    class registerListener
    {
        public void actionPerformed(){
        
            try
            {
                model=view.getUser();
                if (checkUser(model))
                {
                    view.setMessage("Registered Successfully!");
                    
                }
//                else
//                {
//                    view.setMessage("Invalid");
//                }
                }
            catch (Exception e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        
        public boolean checkUser(registerModel user) throws Exception
        {
            try
            {
                Connection conn=myConnection.myDatabase();
                String sql="insert into users (uname,pass,re_pass,email,sec_ans) values (?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                
                pst.setString(1,user.getUname());
                pst.setString(2,user.getPass());
                pst.setString(3,user.getRe_pass());
                pst.setString(4,user.getEmail());
                pst.setString(5,user.getSec_ans());

                pst.executeUpdate();
                System.out.println("Data Inserted");
                JOptionPane.showMessageDialog(null,"Data Registered Successfully");
                loginView lv=new loginView();
                    lv.setVisible(true);
                    view.dispose();
            }
            catch (Exception e2)
            {
                System.out.println(e2.getMessage());   
            }
            return false; 
        }
    }
}
