package com.storage.restassured;

import io.restassured.response.Response;

public class GetUserOrders extends RestAssuredBaseMethods{

    public static void checkBodyAfterNegativeGetUserOrdersWithoutAuthorization(Response response){
        checkResponseBodyJson(response, "success", false);
        checkResponseBodyJson(response, "message", "You should be authorised");
    }

}
