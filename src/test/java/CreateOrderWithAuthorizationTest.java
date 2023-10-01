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
import static com.storage.restassured.IngredientsApiMethods.getLisiOfIngredientsId;
import static com.storage.restassured.IngredientsApiMethods.getRandomOneIngredient;
import static com.storage.restassured.IngredientsApiMethods.getRandomIngredients;
import static com.storage.restassured.RegisterApiMethods.getPojoFromResponsePositiveRegisterUser;
import static com.storage.restassured.CreateOrderApiMethods.*;

public class CreateOrderWithAuthorizationTest {

    private String generatedTestEmail;
    private String generatedTestName;

    private String loginAccessToken;
    private List<String> listOfAllIngredientsId;

    @Before
    public void setUp() {
        // Генерация тестовых данных, создание пользователя, получение токена пользователя, получение ингридиентов
        RestAssured.baseURI = BASE_URL;

        generatedTestEmail = generateTestDataEmail();
        generatedTestName = generateTestData("name");

        Response responseWithRegisterUser = sendByPost(
                REGISTER_USER_URL,
                new RegisterPositiveRequestPojo(
                        generatedTestEmail,
                        generateTestData("password"),
                        generatedTestName
                )
        );

        loginAccessToken = getPojoFromResponsePositiveRegisterUser(responseWithRegisterUser).getAccessToken();

        listOfAllIngredientsId = getLisiOfIngredientsId();
    }

    @Test
    @DisplayName("Создание заказа (с авторизацией)")
    public void createPositiveOrderWithAuthorization(){
        CreateOrderPositiveRequestPojo pojoJsonWithOrderIngredients = new CreateOrderPositiveRequestPojo(
                getRandomOneIngredient(listOfAllIngredientsId)
        );

        Response responseWithOrderCreated = sendByPostWithToken(CREATE_ORDER_URL, pojoJsonWithOrderIngredients, loginAccessToken);
        checkResponseStatusCode(responseWithOrderCreated, SUCCESS_STATUS_CODE);
        checkSuccessfulCreatedOrder(responseWithOrderCreated, generatedTestEmail, generatedTestName);
    }

    @Test
    @DisplayName("Создание заказа (с ингредиентами)")
    public void createPositiveOrderWithIngredients(){
        CreateOrderPositiveRequestPojo pojoJsonWithOrderIngredients = new CreateOrderPositiveRequestPojo(
                getRandomIngredients(listOfAllIngredientsId, 4)
        );

        Response responseWithOrderCreated = sendByPostWithToken(CREATE_ORDER_URL, pojoJsonWithOrderIngredients, loginAccessToken);
        checkResponseStatusCode(responseWithOrderCreated, SUCCESS_STATUS_CODE);
        checkSuccessfulCreatedOrder(responseWithOrderCreated, generatedTestEmail, generatedTestName);
    }

    @After
    public void deleteUser(){
        // Удаления пользователя
        deleteUserByBearerToken(loginAccessToken);
    }
}
