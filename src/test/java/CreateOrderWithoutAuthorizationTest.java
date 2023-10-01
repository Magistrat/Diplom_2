import com.storage.pojo.order.CreateOrderPositiveRequestPojo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.storage.SettingsInterface.*;
import static com.storage.restassured.IngredientsApiMethods.getLisiOfIngredientsId;
import static com.storage.restassured.IngredientsApiMethods.getRandomIngredients;
import static com.storage.restassured.CreateOrderApiMethods.*;

public class CreateOrderWithoutAuthorizationTest {
    private List<String> listOfAllIngredientsId;

    @Before
    public void setUp() {
        // Получение ингридиентов
        RestAssured.baseURI = BASE_URL;

        listOfAllIngredientsId = getLisiOfIngredientsId();
    }

    @Test
    @DisplayName("Создание заказа (без авторизации)")
    public void negativeCreateOrderWithoutAuthorization(){
        CreateOrderPositiveRequestPojo pojoJsonWithOrderIngredients = new CreateOrderPositiveRequestPojo(
                getRandomIngredients(listOfAllIngredientsId, 5)
        );

        Response responseWithOrderCreated = sendByPost(CREATE_ORDER_URL, pojoJsonWithOrderIngredients);
        checkResponseStatusCode(responseWithOrderCreated, SUCCESS_STATUS_CODE);
        checkNegativeCreatedOrderWithoutAuthorization(responseWithOrderCreated);
    }
}
