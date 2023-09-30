package com.storage;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.storage.SettingsInterface.*;
import static io.restassured.RestAssured.given;

public class RestAssuredBaseMethods {
    // Методы для работы с RestAssured

    @Step("Отправка запроса на API методом POST")
    public static Response sendByPost(String url, Object body){
        return given()
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    @Step("Проверка статус кода с ожидаемым")
    public static void checkResponseStatusCode(Response response, int statusCode){
        response.then().statusCode(statusCode);
    }

    @Step
    public static void deleteUserByBearerToken(String bearerToken){
        given()
                .header("Authorization", "Bearer " + bearerToken)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .delete(DELETE_USER_URL);
    }
}
