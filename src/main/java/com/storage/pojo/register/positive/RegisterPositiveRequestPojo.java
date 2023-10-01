package com.storage.pojo.register.positive;

public class RegisterPositiveRequestPojo {

    // Pojo для регистрации пользователя (отправка JSON)
    private String email;
    private String password;
    private String name;

    public RegisterPositiveRequestPojo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public RegisterPositiveRequestPojo(){}

}
