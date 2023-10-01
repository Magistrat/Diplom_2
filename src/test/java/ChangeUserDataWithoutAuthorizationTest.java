import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
import com.storage.pojo.update.positive.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.storage.GenerationData.generateTestData;
import static com.storage.GenerationData.generateTestDataEmail;
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.ChangeDataApiMethods.*;
import static com.storage.restassured.RegisterApiMethods.getPojoFromResponsePositiveRegisterUser;


@RunWith(Parameterized.class)
public class ChangeUserDataWithoutAuthorizationTest {

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][] {
                {new ChangeUserDataPositiveRequestWithEmailPojo(generateTestDataEmail())},
                {new ChangeUserDataPositiveRequestWithNamePojo(generateTestData("name"))},
                {new ChangeUserDataPositiveRequestWithPasswordPojo(generateTestData("password"))},
        };
    }
    private final Object pojoJsonData;
    public ChangeUserDataWithoutAuthorizationTest(Object pojoJsonData){
        this.pojoJsonData = pojoJsonData;
    }

    private String loginAccessToken;

    @Before
    public void setUp(){
        // Генерация тестовых данных, создание пользователя, получения токена
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

    @Test
    @DisplayName("Изменение данных пользователя (без авторизации)")
    public void changeUserDataNegative(){
        Response responseWithoutUpdatedUser = sendByPatch(UPDATE_USER_URL, pojoJsonData);

        checkResponseStatusCode(responseWithoutUpdatedUser, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterUnsuccessfulUpdatedUser(responseWithoutUpdatedUser);
    }

    @After
    public void deleteUser(){
        // Удаления пользователя
        deleteUserByBearerToken(loginAccessToken);
    }
}
