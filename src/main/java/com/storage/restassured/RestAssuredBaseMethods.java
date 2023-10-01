package com.storage.restassured;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.storage.SettingsInterface.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public abstract class RestAssuredBaseMethods {
    // Базовый абстрактный класс для работы с RestAssured

    @Step("Отправка запроса на API методом POST")
    public static Response sendByPost(String url, Object body){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    @Step("Отправка запроса на API методом PATCH")
    public static Response sendByPatch(String url, Object body){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .patch(url);
    }

    @Step("Удаление пользователя при помощи Bearer Token")
    public static void deleteUserByBearerToken(String bearerToken){
        given()
                .header("Authorization", bearerToken)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .delete(DELETE_USER_URL);
    }

    @Step("Проверка статус кода с ожидаемым")
    public static void checkResponseStatusCode(Response response, int statusCode){
        response.then().statusCode(statusCode);
    }

    @Step("Проверка наличия в ответе от API нужного ключа со значением")
    public static void checkResponseBodyJson(Response response, String jsonKey, String jsonValue){
        response.then().assertThat().body(jsonKey, equalTo(jsonValue));
    }

    @Step("Проверка наличия в ответе от API нужного ключа со значением")
    public static void checkResponseBodyJson(Response response, String jsonKey, boolean jsonValue){
        response.then().assertThat().body(jsonKey, equalTo(jsonValue));
    }
    @Step("Проверка наличия в ответе от API, что значение ключа является строкой")
    public static void checkResponseBodyJsonNotNullKey(Response response, String jsonKey){
        response.then().assertThat().body(jsonKey, notNullValue());
    }
}
