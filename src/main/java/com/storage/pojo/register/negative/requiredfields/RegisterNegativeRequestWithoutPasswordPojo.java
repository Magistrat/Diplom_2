package com.storage.pojo.register.negative.requiredfields;

public class RegisterNegativeRequestWithoutPasswordPojo {
    // Pojo для регистрации пользователя (отправка JSON). Без ключа password

    private String email;
    private String name;

    public RegisterNegativeRequestWithoutPasswordPojo(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public RegisterNegativeRequestWithoutPasswordPojo(){}
}
