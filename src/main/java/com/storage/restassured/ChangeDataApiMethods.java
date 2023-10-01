package com.storage.restassured;

import io.restassured.response.Response;

public class ChangeDataApiMethods extends RestAssuredBaseMethods {
    public static void checkSuccessfulUpdatedUsersData(Response response, String userEmail, String userName){
        checkResponseBodyJson(response, "success", true);
        checkResponseBodyJson(response, "user.email", userEmail);
        checkResponseBodyJson(response, "user.name", userName);
    }
}
