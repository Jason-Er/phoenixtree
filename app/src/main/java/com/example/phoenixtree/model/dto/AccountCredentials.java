package com.example.phoenixtree.model.dto;

/**
 * Created by ej on 12/28/2017.
 */

public class AccountCredentials {
    public String username;
    public String password;

    public AccountCredentials(String email, String password) {
        this.username = email;
        this.password = password;
    }
}
