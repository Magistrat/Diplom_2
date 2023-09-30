package com.storage.pojo.register;

public class RegisterPositivePojo {

    // Pojo для регистрации пользователя
    private String email;
    private String password;
    private String name;

    public RegisterPositivePojo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public RegisterPositivePojo(){}

}
