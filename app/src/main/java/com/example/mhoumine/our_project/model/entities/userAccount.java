package com.example.mhoumine.our_project.model.entities;

/**
 * Created by mhoumine on 27/11/2016.
 */

public class userAccount {

    private String username;
    private String password;

    public userAccount() {
    }

    public userAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
