package com.storage.pojo.update.positive;

public class ChangeUserDataPositiveRequestWithEmailPojo implements CommonGetterInterface{
    // Pojo для обновление данных авторизованного пользователя (отправка JSON). Обновление email

    private String email;

    public ChangeUserDataPositiveRequestWithEmailPojo(String email) {
        this.email = email;
    }

    public ChangeUserDataPositiveRequestWithEmailPojo() {}

    @Override
    public String getUpdateValue(){
        return email;
    }
}
