package com.storage.pojo.update;

public class ChangeUserDataPositiveRequestWithEmailPojo{
    // Pojo для обновление данных авторизованного пользователя (отправка JSON). Обновление email

    private String email;

    public ChangeUserDataPositiveRequestWithEmailPojo(String email) {
        this.email = email;
    }

    public ChangeUserDataPositiveRequestWithEmailPojo() {}
}
