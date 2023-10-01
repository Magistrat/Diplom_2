package com.storage.pojo.ingredients;

import java.util.List;

public class IngredientsResponseAll {
    // POJO для хранения всех Ингридиентов (получение JSON)

    private String success;
    private List<IngredientsResponseData> data;

    public IngredientsResponseAll(String success, List<IngredientsResponseData> data) {
        this.success = success;
        this.data = data;
    }

    public IngredientsResponseAll() {}

    public List<IngredientsResponseData> getData() {
        return data;
    }
}
