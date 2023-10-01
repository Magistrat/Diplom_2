package com.storage.restassured;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class LoginApiMethods extends RestAssuredBaseMethods{

    @Step("Проверка тела ответа после авторизации пользователя")
    public static void checkBodyAfterSuccessfulLogin(Response response, String userEmail, String userName){
        checkResponseBodyJson(response, "success", true);
        checkResponseBodyJsonNotNullKey(response, "accessToken");
        checkResponseBodyJsonNotNullKey(response, "refreshToken");
        checkResponseBodyJson(response, "user.email", userEmail);
        checkResponseBodyJson(response, "user.name", userName);
    }

}
