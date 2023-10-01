package com.storage.pojo.order;

import java.util.List;

public class CreateOrderPositiveRequestPojo {
    // Pojo со списком ингридиентов для создания заказа

    private List<String> ingredients;

    public CreateOrderPositiveRequestPojo(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrderPositiveRequestPojo() {}
}
