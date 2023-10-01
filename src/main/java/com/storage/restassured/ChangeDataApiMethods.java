package com.storage.restassured;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.storage.SettingsInterface.APPLICATION_JSON;
import static com.storage.SettingsInterface.CONTENT_TYPE;
import static io.restassured.RestAssured.given;

public class ChangeDataApiMethods extends RestAssuredBaseMethods {

    @Step("Отправка запроса на API методом PATCH с Bearer Token")
    public static Response sendByPatchWithToken(String url, Object body, String bearerToken){
        return given()
                .header("Authorization", bearerToken)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .and()
                .body(body)
                .when()
                .patch(url);
    }

    public static void checkSuccessfulUpdatedUsersData(Response response, String userEmail, String userName){
        checkResponseBodyJson(response, "success", true);
        checkResponseBodyJson(response, "user.email", userEmail);
        checkResponseBodyJson(response, "user.name", userName);
    }

    public static void checkBodyAfterUnsuccessfulUpdatedUser(Response response){
        checkResponseBodyJson(response, "success", false);
        checkResponseBodyJson(response, "message", "You should be authorised");
    }
}
