package com.storage.pojo.update.positive;

public class ChangeUserDataPositiveRequestWithPasswordPojo implements CommonGetterInterface {
    // Pojo для обновление данных авторизованного пользователя (отправка JSON). Обновление password

    private String password;

    public ChangeUserDataPositiveRequestWithPasswordPojo(String password) {
        this.password = password;
    }

    public ChangeUserDataPositiveRequestWithPasswordPojo() {}

    @Override
    public String getUpdateValue(){
        return password;
    }
}
