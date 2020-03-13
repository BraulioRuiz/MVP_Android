package com.example.mvp_act.Presents;

import com.example.mvp_act.Models.User;

public class MainActivityPresent {
    private User user;
    private View view;

    public MainActivityPresent(View view){
        this.user = new User();
        this.view = view;
    }

    public void update(String name){
        user.setName(name);
        view.updateUserTextView(user.getName());
    }

    public interface View{
        void updateUserTextView(String name);
    }
}
