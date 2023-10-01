import com.storage.pojo.order.CreateOrderPositiveRequestPojo;
import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.storage.GenerationData.generateTestData;
import static com.storage.GenerationData.generateTestDataEmail;
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.IngredientsApiMethods.*;
import static com.storage.restassured.RegisterApiMethods.getPojoFromResponsePositiveRegisterUser;
import static com.storage.restassured.GetUserOrders.*;

public class GetUserOrdersWithAuthorizationTest {
    private String loginAccessToken;
    private List<String> testRandomIngredients;

    @Before
    public void setUp(){
        // Генерация тестовых данных, создание пользователя, получение токена пользователя, получение ингридиентов
        // создание заказа

        RestAssured.baseURI = BASE_URL;

        Response responseWithRegisterUser = sendByPost(
                REGISTER_USER_URL,
                new RegisterPositiveRequestPojo(
                        generateTestDataEmail(),
                        generateTestData("password"),
                        generateTestData("name")
                )
        );

        loginAccessToken = getPojoFromResponsePositiveRegisterUser(responseWithRegisterUser).getAccessToken();

        testRandomIngredients = getRandomIngredients(getLisiOfIngredientsId(), 2);

        CreateOrderPositiveRequestPojo pojoJsonWithOrderIngredients = new CreateOrderPositiveRequestPojo(
                testRandomIngredients
        );
        sendByPostWithToken(CREATE_ORDER_URL, pojoJsonWithOrderIngredients, loginAccessToken);
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя (авторизованный пользователь)")
    public void negativeGetUserOrdersWithoutAuthorization(){
        Response responseWithUserOrders = sendByGetWithToken(GET_USER_ORDERS_URL, loginAccessToken);

        checkResponseStatusCode(responseWithUserOrders, SUCCESS_STATUS_CODE);
        checkBodyAfterPositiveGetUserOrders(responseWithUserOrders, testRandomIngredients);
    }

    @After
    public void deleteUser(){
        // Удаление пользователя
        deleteUserByBearerToken(loginAccessToken);
    }
}
