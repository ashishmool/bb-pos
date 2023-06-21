/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.*;
import model.*;
import view.*;
import java.sql.*;

/**
 *
 * @author asism
 */
public class loginrecoverController {
    
    loginrecoverModel model;
    loginrecoverView view;
    ResultSet rs;
    Statement stmt;
    
    public loginrecoverController(loginrecoverView view)
    {
        this.view=view;
        new RecoveryListener().actionPerformed();
        
    }
        
        class RecoveryListener{
            public void actionPerformed(){
                try{
                    
                    model=view.getUser();
                    if(checkUser(model))
                    {
                        view.setMessage("Login for Recovery Successful!");
                        view.validDisplay();
//                        view.dispose();
                    }
                    else
                    {
                        view.setMessage("Email and Security Answer Mismatch! Try Again!!");
                    }
                    
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                
            }
            
          
            
            public boolean checkUser(loginrecoverModel user) throws Exception
            {
                
                
//                new recoverController(email2).view
                
                
                Connection conn = myConnection.myDatabase();
                String sql="select * from users where email='"+user.getEmail()+"' AND sec_ans='"+user.getSec_ans()+"'";
                try{
                    stmt=conn.createStatement();
                    rs=stmt.executeQuery(sql);
                    if (rs.next()){
                        String emailpass=rs.getString(4);
                        
                        return true;
                    }
                    conn.close();
                    
                }
                catch (Exception e1){
                    System.out.println(e1.getMessage());
                    
                }
                
                return false;
            }
        }
        
    }
    
    
    
