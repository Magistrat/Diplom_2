import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static com.storage.GenerationData.generateTestData;
import static com.storage.GenerationData.generateTestDataEmail;
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.RegisterApiMethods.getPojoFromResponsePositiveRegisterUser;
import static com.storage.restassured.CreateOrderMethods.*;

public class CreateOrderTest {
    private String loginAccessToken;

    @Before
    public void setUp() {
        // Генерация тестовых данных, создание пользователя, получение токена пользователя, получение ингридиентов
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
    }

    @After
    public void deleteUser(){
        // Удаления пользователя
        deleteUserByBearerToken(loginAccessToken);
    }
}
