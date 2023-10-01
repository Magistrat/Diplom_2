package com.storage.pojo.login.negative;

public class LoginNegativeRequestWithoutEmailPojo {
    // Pojo для негативной авторизации пользователя (отправка JSON). Без Email

    private String password;

    public LoginNegativeRequestWithoutEmailPojo(String password) {
        this.password = password;
    }

    public LoginNegativeRequestWithoutEmailPojo(){}
}
