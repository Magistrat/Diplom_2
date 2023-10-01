package com.storage.pojo.order;

public class CreateOrderPositiveRequestPojo {
    // Pojo со списком ингридиентов для создания заказа

    private String ingredients;

    public CreateOrderPositiveRequestPojo(String ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrderPositiveRequestPojo() {}
}
