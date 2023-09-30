package com.storage;

import io.qameta.allure.Step;

public class Base {
    @Step("Генерация уникальных тестовых данных при помощи Unix time")
    public static String generateTestData(String startData) {
        long unixTime = System.currentTimeMillis();
        return startData + "-" + unixTime;
    }

    @Step("Генерация уникального email адреса для тестов при помощи Unix time")
    public static String generateTestDataEmail() {
        return generateTestData("email") + "@yandex.ru";
    }

}
