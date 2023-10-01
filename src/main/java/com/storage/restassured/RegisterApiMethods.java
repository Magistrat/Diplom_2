package com.storage.restassured;

import com.storage.pojo.register.positive.RegisterPositiveResponseAllPojo;
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

    @Step("Проверка тела ответа если пользователь уже существует")
    public static void checkBodyUserAlreadyExist(Response response){
        checkResponseBodyJson(response, "success", false);
        checkResponseBodyJson(response, "message", "User already exists");
    }


    @Step("Полчучение ответа из тела после регистрации пользователя (в виде POJO)")
    public static RegisterPositiveResponseAllPojo getPojoFromResponsePositiveRegisterUser(Response response){
        return response.as(RegisterPositiveResponseAllPojo.class);
    }
}
