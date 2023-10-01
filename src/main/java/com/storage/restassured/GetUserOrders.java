package com.storage.restassured;

import io.restassured.response.Response;

import java.util.List;

public class GetUserOrders extends RestAssuredBaseMethods{

    public static void checkBodyAfterNegativeGetUserOrdersWithoutAuthorization(Response response){
        checkResponseBodyJson(response, "success", false);
        checkResponseBodyJson(response, "message", "You should be authorised");
    }

    public static void checkBodyAfterPositiveGetUserOrders(Response response, List<String> ingredients){
        checkResponseBodyJson(response, "success", true);
        checkResponseBodyJsonNotNullKey(response, "total");
        checkResponseBodyJsonNotNullKey(response, "totalToday");
    }

}
