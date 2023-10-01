package com.storage.restassured;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class RegisterApiMethods extends RestAssuredBaseMethods{

    @Step("Проверка тела ответа после регистрации пользователя")
    public static void checkBodyAfterSuccessfulRegister(Response response, String userEmail, String userName){
        checkResponseBodyJson(response, "success", true);
        checkResponseBodyJson(response, "user.email", userEmail);
        checkResponseBodyJson(response, "user.name", userName);
        checkResponseBodyJsonNotNullKey(response, "accessToken");
        checkResponseBodyJsonNotNullKey(response, "refreshToken");
    }
}
