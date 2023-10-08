package com.storage;

import io.qameta.allure.Step;

public class GenerationData {
    @Step("Генерация уникальных тестовых данных при помощи Unix time")
    public static String generateTestData(String startData) {
        long unixTime = System.currentTimeMillis();
        return startData + "-" + unixTime;
    }

    @Step("Генерация уникального email адреса для тестов при помощи Unix time")
    public static String generateTestDataEmail() {
        return generateTestData("email") + "@yandex.ru";
    }

    @Step("Получение новых данных на основе уже сгенерированных")
    public static String newTestData(String endData){
        return "new-" + endData;
    }
}
