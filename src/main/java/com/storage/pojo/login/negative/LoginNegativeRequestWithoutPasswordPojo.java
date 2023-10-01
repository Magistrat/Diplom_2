package com.storage.pojo.login.negative;

public class LoginNegativeRequestWithoutPasswordPojo {
    // Pojo для негативной авторизации пользователя (отправка JSON). Без Password

    private String email;

    public LoginNegativeRequestWithoutPasswordPojo(String email) {
        this.email = email;
    }

    public LoginNegativeRequestWithoutPasswordPojo(){}
}
