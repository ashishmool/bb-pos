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
        
    
    
    
    

