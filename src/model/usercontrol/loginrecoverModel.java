/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.usercontrol;

/**
 *
 * @author asism
 */
public class loginrecoverModel {
    String email, sec_ans;
    
    public loginrecoverModel(String email, String sec_ans){
        
        this.email=email;
        this.sec_ans=sec_ans;
    
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
