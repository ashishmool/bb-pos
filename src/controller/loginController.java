/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.myConnection;
import model.*;
import view.*;
import java.sql.*;

/**
 *
 * @author asism
 */
public class loginController {
    
    loginModel model;
    loginView view;
    ResultSet rs;
    Statement stmt;
    
    public loginController(loginView view)
    {
        this.view=view;
        
        new LoginListener().actionPerformed();
        
        
    }
    class LoginListener
    {
        public void actionPerformed() {
            try
            {
                model=view.getUser();
                if(checkUser(model))
                {
                    view.setMessage("Login Successfully");
                    dashboardView d=new dashboardView();
                    d.setVisible(true);
                    view.dispose();
                }
                else
                {
                    view.setMessage("Invalid username or password");
                    
                }
            }
            catch(Exception e1)
            {
                System.out.println(e1.getMessage());
            }
            
            finally {
                System.out.println("Login System Executed Successfully!");
            }
        }
        
    public boolean checkUser(loginModel user) throws Exception
        {
//          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn=myConnection.myDatabase();
          String sql="select * from users where uname='"+user.getUsername()+"' AND pass='"+user.getPassword()+"'";
          try
          {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            if(rs.next())
             {
                 return true;
             }
             conn.close();

          }
          catch(Exception e2)
          {
              System.out.println(e2.getMessage());
          }         
            
            return false;
        }

        }
    
}
