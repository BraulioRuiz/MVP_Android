package com.example.mvp_act.Presents;


import com.example.mvp_act.Models.UserLogin;

public class LoginPresent {
    public View view;
    public UserLogin login;

    public LoginPresent(View view){
        login = new UserLogin();
        this.view = view;
    }

    public interface View{
        void loginAlert(String text);
        void accesSuccest(String token, String Username);

    }

    public void login(String token, String Username){
        login.login(token,Username, this);

    }

}
