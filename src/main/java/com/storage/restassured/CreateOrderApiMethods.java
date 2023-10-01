package com.storage.restassured;


import io.restassured.response.Response;

public class CreateOrderApiMethods extends RestAssuredBaseMethods{

    public static void checkSuccessfulCreatedOrder(Response response, String userEmail, String userName){
        checkResponseBodyJson(response,"success", true);
        checkResponseBodyJsonNotNullKey(response,"name");
        checkResponseBodyJsonNotNullKey(response,"order.ingredients");
        checkResponseBodyJsonNotNullKey(response,"order._id");
        checkResponseBodyJson(response,"order.owner.name", userName);
        checkResponseBodyJson(response,"order.owner.email", userEmail);
        checkResponseBodyJsonNotNullKey(response,"order.owner.createdAt");
        checkResponseBodyJsonNotNullKey(response,"order.owner.updatedAt");
        checkResponseBodyJsonNotNullKey(response,"order.status");
        checkResponseBodyJsonNotNullKey(response,"order.name");
        checkResponseBodyJsonNotNullKey(response,"order.createdAt");
        checkResponseBodyJsonNotNullKey(response,"order.updatedAt");
        checkResponseBodyJsonNotNullKey(response,"order.number");
        checkResponseBodyJsonNotNullKey(response,"order.price");
    }

    public static void checkNegativeCreatedOrderWithoutIngredients(Response response){
        checkResponseBodyJson(response,"success", false);
        checkResponseBodyJson(response,"message", "Ingredient ids must be provided");
    }

    public static void checkNegativeCreatedOrderWithoutAuthorization(Response response){
        checkResponseBodyJson(response,"success", true);
        checkResponseBodyJsonNotNullKey(response,"name");
        checkResponseBodyJsonNotNullKey(response,"order.number");
    }
}
