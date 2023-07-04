/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.*;
import database.*;
import model.*;


/**
 *
 * @author Binita
 */
public class employeeController {
    
    public static boolean addEmployee(employeeModel employee){
        try{
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setObject(1,employee.getEmployee_id());
            pst.setObject(2,employee.getEmployee_name());
            pst.setObject(3,employee.getEmployee_ppan());
            pst.setObject(4,employee.getEmployee_padd());
            pst.setObject(5,employee.getEmployee_tadd());
            pst.setObject(6,employee.getEmployee_mob());
            pst.setObject(7,employee.getEmployee_email());
            pst.setObject(8,employee.getEmployee_cname());
            pst.setObject(9,employee.getEmployee_crela());
            pst.setObject(10,employee.getEmployee_cmob());
            pst.setObject(11,employee.getEmployee_caltnum());
            
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public static employeeModel searchEmployee(String employee_id){
        
        employeeModel employee=null;
        try{
            String sql = "SELECT * FROM employee WHERE eid = '"+ employee_id+"' ";
            Connection connection = myConnection.myDatabase();
            Statement stm = connection.createStatement();
            ResultSet rset = stm.executeQuery(sql);
            if (rset.next()){
                employee = new employeeModel(
                rset.getString(1),
                rset.getString(2),
                rset.getString(3),
                rset.getString(4),
                rset.getString(5),
                rset.getString(6),
                rset.getString(7),
                rset.getString(8),
                rset.getString(9),
                rset.getString(10),
                rset.getString(11));
            }
        }
        catch (SQLException e2){
            System.out.println(e2.getMessage());
        }
        return employee;
    }

    public static boolean updateEmployee(employeeModel employee){
        try{
            String sql = "UPDATE employee SET e_name = ?, e_ppan = ?, e_padd = ?, e_tadd = ?, e_mob = ?, e_email = ?, e_econname = ?, e_rel = ?, e_emob = ?, e_ealtmob = ?  WHERE eid = ? ";
            Connection connection = myConnection.myDatabase();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(11,employee.getEmployee_id());
            pst.setObject(1,employee.getEmployee_name());
            pst.setObject(2,employee.getEmployee_ppan());
            pst.setObject(3,employee.getEmployee_padd());
            pst.setObject(4,employee.getEmployee_tadd());
            pst.setObject(5,employee.getEmployee_mob());
            pst.setObject(6,employee.getEmployee_email());
            pst.setObject(7,employee.getEmployee_cname());
            pst.setObject(8,employee.getEmployee_crela());
            pst.setObject(9,employee.getEmployee_cmob());
            pst.setObject(10,employee.getEmployee_caltnum());
            int result = pst.executeUpdate();
            return(result>0);
        }
        catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
    }
    
    public static boolean deleteEmployee(employeeModel employee){
        try{
            Connection connection = myConnection.myDatabase();
            String sql = "DELETE FROM employee WHERE eid = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(1, employee.getEmployee_id());
            int result = pst.executeUpdate();
            return (result>0);
        }
        catch (SQLException e3){
                System.out.println(e3.getMessage());
            }
            return false;
    }
}