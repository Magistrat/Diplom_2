package com.storage.restassured;

import com.storage.pojo.ingredients.IngredientsResponseAll;
import com.storage.pojo.ingredients.IngredientsResponseData;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.storage.SettingsInterface.ALL_INGREDIENTS_URL;

public class IngredientsApiMethods extends RestAssuredBaseMethods{

    @Step("Получение Pojo со всеми ингридиентами заказа из API")
    public static IngredientsResponseAll getAllIngredientsPojo(){
        return sendByGet(ALL_INGREDIENTS_URL).as(IngredientsResponseAll.class);
    }

    @Step("Получение списка всех хешей ингридиентов")
    public static List<String> getLisiOfIngredientsId(){
        List<String> listOfAllId = new ArrayList<>();

        List<IngredientsResponseData> listOfAllIngredientsData = getAllIngredientsPojo().getData();
        for (IngredientsResponseData ingredient: listOfAllIngredientsData){
            listOfAllId.add(ingredient.getId());
        }
        return listOfAllId;
    }

    @Step("Получение одного рандомного хеша ингридиента")
    public static List<String> getRandomOneIngredient() {
        List<String> listOfAllId = getLisiOfIngredientsId();
        List<String> randomOneIngredient = new ArrayList<>();

        int randomIndex = new Random().nextInt(listOfAllId.size());
        randomOneIngredient.add(listOfAllId.get(randomIndex));
        return randomOneIngredient;
    }

    @Step("Получение несколиких рандомных хешей ингридиентов")
    public static List<String> getRandomIngredients(int count){
        List<String> listOfAllId = getLisiOfIngredientsId();

        if (listOfAllId.size() < count || listOfAllId.isEmpty() || count <= 0){
            return getRandomOneIngredient();
        }

        List<String> randomIngredients = new ArrayList<>();
        for (int i = 0; i <= count; i++){
            int randomIndex = new Random().nextInt(listOfAllId.size());
            randomIngredients.add(listOfAllId.get(randomIndex));
        }
        return randomIngredients;
    }


}
