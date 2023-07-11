/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.usercontrol;

import view.*;
import controller.*;

/**
 *
 * @author asism
 */
public class recoverModel {
    String username, pass, re_pass, email, sec_ans;


    public recoverModel(String username, String pass, String re_pass, String email, String sec_ans){
        this.username=username;
        this.pass=pass;
        this.re_pass=re_pass;
        this.email=email;
        this.sec_ans=sec_ans;             
    
}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRe_pass() {
        return re_pass;
    }

    public void setRe_pass(String re_pass) {
        this.re_pass = re_pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSec_ans() {
        return sec_ans;
    }

    public void setSec_ans(String sec_ans) {
        this.sec_ans = sec_ans;
    }
    
}
