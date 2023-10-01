package com.storage.pojo.update.positive;

public class ChangeUserDataPositiveRequestWithNamePojo implements CommonGetterInterface {
    // Pojo для обновление данных авторизованного пользователя (отправка JSON). Обновление name

    private String name;

    public ChangeUserDataPositiveRequestWithNamePojo(String name) {
        this.name = name;
    }

    public ChangeUserDataPositiveRequestWithNamePojo() {}

    @Override
    public String getUpdateValue(){
        return name;
    }
}
