import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
import com.storage.pojo.register.positive.RegisterPositiveResponseAllPojo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.storage.GenerationData.generateTestData;
import static com.storage.GenerationData.generateTestDataEmail;
import static com.storage.restassured.RegisterApiMethods.*;
import static com.storage.SettingsInterface.*;


public class RegisterTest {
    private String generatedTestEmail;
    private String generatedTestName;
    private RegisterPositiveRequestPojo positiveRegister;
    private Response responseWithCreatedUser;

    @Before
    public void setUp(){
        // Генерация тестовых данных
        RestAssured.baseURI = BASE_URL;

        generatedTestEmail = generateTestDataEmail();
        generatedTestName = generateTestData("name");

        positiveRegister = new RegisterPositiveRequestPojo(
                generatedTestEmail,
                generateTestData("password"),
                generatedTestName
        );
    }

    @Test
    @DisplayName("Создать уникального пользователя")
    public void createUniqueUserTest(){
        responseWithCreatedUser = sendByPost(REGISTER_USER_URL, positiveRegister);

        checkResponseStatusCode(responseWithCreatedUser, SUCCESS_STATUS_CODE);
        checkBodyAfterSuccessfulRegister(responseWithCreatedUser, generatedTestEmail, generatedTestName);
    }

    @Test
    @DisplayName("Создать пользователя, который уже зарегистрирован")
    public void negativeTryCreateAlreadyExistUserTest(){
        responseWithCreatedUser = sendByPost(REGISTER_USER_URL, positiveRegister);
        Response responseExitUser = sendByPost(REGISTER_USER_URL, positiveRegister);

        checkResponseStatusCode(responseExitUser, FORBIDDEN_STATUS_CODE);
        checkBodyUserAlreadyExist(responseExitUser);
    }


    @After
    public void deleteUser(){
        // Удаление пользователя
        deleteUserByBearerToken(getPojoFromResponsePositiveRegisterUser(responseWithCreatedUser).getAccessToken());
    }
}
