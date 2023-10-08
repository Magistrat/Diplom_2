package com.storage.pojo.login.positive;

public class LoginPositiveRequestPojo {
    // Pojo для позитивной авторизации пользователя (отправка JSON)
    private String email;
    private String password;

    public LoginPositiveRequestPojo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginPositiveRequestPojo(){}
}
