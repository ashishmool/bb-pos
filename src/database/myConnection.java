/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author asism
 */
public class myConnection {
    
    public static Connection myDatabase()
    {
        {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pos","root","softwarica");
                    System.out.println("Database Connected!");
                    return conn;
        }
        catch (Exception e)
        
        {
        return null;    
        }
        
        }
    }
}
    

