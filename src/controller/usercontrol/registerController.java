/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.usercontrol;

import view.usercontrol.registerView;
import view.usercontrol.loginView;
import model.usercontrol.registerModel;
import database.myConnection;
import model.*;
import view.*;
import javax.swing.*;
import java.sql.*;
import java.util.regex.Pattern;
import junit.framework.Assert;

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
                Assert.assertEquals(true,checkUser(model));
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
            
            
            
            String pass1 = user.getPass();
            String pass2 = user.getRe_pass();
            
            String u_name = user.getUname();
            String emailfound = user.getEmail();
            
            
            
            if (emailfound==""){
                JOptionPane.showMessageDialog(null, "Fill Required Details");
            }
            else{
                    if (pass1.equals(pass2)){

                        try
                    {
                        Connection conn=myConnection.myDatabase();
                        String sql="insert into users (uname,pass,re_pass,email,sec_ans,accessrole) values (?,?,?,?,?,?)";
                        pst = conn.prepareStatement(sql);

                        pst.setString(1,user.getUname());
                        pst.setString(2,user.getPass());
                        pst.setString(3,user.getRe_pass());
                        pst.setString(4,user.getEmail());
                        pst.setString(5,user.getSec_ans());
                        pst.setString(6, user.getUser_accesscontrol());

                        pst.executeUpdate();
                        System.out.println("Data Inserted");
                        JOptionPane.showMessageDialog(null,"User Registered Successfully");
                        loginView lv=new loginView();
                            lv.setVisible(true);
                            view.dispose();
                    }
                    catch (Exception e2)
                    {
                        System.out.println(e2.getMessage());   
                    }
                    }

                    else{
                        view.setMessage("Password Verification Error!");
                    }
            }

                    return false; 
                }
    }
    
}