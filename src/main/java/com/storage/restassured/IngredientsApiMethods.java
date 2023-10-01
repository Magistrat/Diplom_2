package com.storage.restassured;

import com.storage.pojo.ingredients.IngredientsResponseAll;

import static com.storage.SettingsInterface.ALL_INGREDIENTS_URL;

public class IngredientsApiMethods extends RestAssuredBaseMethods{

    public static IngredientsResponseAll getAllIngredientsPojo(){
        return sendByGet(ALL_INGREDIENTS_URL).as(IngredientsResponseAll.class);
    }


}
