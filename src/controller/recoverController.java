/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.*;
import model.*;
import view.*;
import java.sql.*;
import java.util.HashSet;

/**
 *
 * @author asism
 */
public class recoverController {
    
    recoverModel model;
    loginrecoverModel logmodel;
    recoverView view;
    loginrecoverView recview;
    ResultSet rs;
    Statement stmt;
    
    public recoverController(recoverView view)
    {
        this.view=view;
        new RecoveryListener().actionPerformed();
        
    }
    
        class RecoveryListener
        {
            public void actionPerformed()
            {
                

            }
            
          
        }
    }
        
    
    
    
    

