import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static com.storage.SettingsInterface.*;
import static com.storage.restassured.GetUserOrders.*;
import static com.storage.restassured.LoginApiMethods.checkBodyAfterNegativeLogin;

public class GetUserOrdersWithoutAuthorization {
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя (неавторизованный пользователь)")
    public void negativeGetUserOrdersWithoutAuthorization(){
        Response responseGetOrdersWithoutAuthorization = sendByGet(GET_USER_ORDERS_URL);

        checkResponseStatusCode(responseGetOrdersWithoutAuthorization, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterNegativeGetUserOrdersWithoutAuthorization(responseGetOrdersWithoutAuthorization);
    }

}
