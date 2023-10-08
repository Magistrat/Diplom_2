package com.storage.pojo.register.negative.requiredfields;

public class RegisterNegativeRequestWithoutNamePojo {
    // Pojo для негативной регистрации пользователя (отправка JSON). Без ключа name

    private String email;
    private String password;

    public RegisterNegativeRequestWithoutNamePojo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RegisterNegativeRequestWithoutNamePojo(){}

}
