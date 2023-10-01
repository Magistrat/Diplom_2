package com.storage.pojo.register.negative.requiredfields;

public class RegisterNegativeRequestWithoutEmailPojo {
    // Pojo для негативной регистрации пользователя (отправка JSON). Без ключа email

    private String password;
    private String name;

    public RegisterNegativeRequestWithoutEmailPojo(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public RegisterNegativeRequestWithoutEmailPojo(){}
}
