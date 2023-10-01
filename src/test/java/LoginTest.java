import com.storage.pojo.login.positive.LoginPositiveRequestPojo;
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
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.RegisterApiMethods.getPojoFromResponsePositiveRegisterUser;
import static com.storage.restassured.LoginApiMethods.*;

public class LoginTest {

    private String generatedTestEmail;
    private String generatedTestPassword;
    private String generatedTestName;

    private Response responseWithCreatedUser;

    @Before
    public void setUp(){
        // Генерация тестовых данных, создание пользователя

        RestAssured.baseURI = BASE_URL;

        generatedTestEmail = generateTestDataEmail();
        generatedTestPassword = generateTestData("password");
        generatedTestName = generateTestData("name");

        responseWithCreatedUser = sendByPost(
                REGISTER_USER_URL,
                new RegisterPositiveRequestPojo(generatedTestEmail, generatedTestPassword, generatedTestName)
        );
    }

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void positiveLogin(){
        LoginPositiveRequestPojo pojoJsonForLogin = new LoginPositiveRequestPojo(
                generatedTestEmail,
                generatedTestPassword
        );

        Response responseWithLoginUser = sendByPost(LOGIN_USER_URL, pojoJsonForLogin);
        checkResponseStatusCode(responseWithLoginUser, SUCCESS_STATUS_CODE);
        checkBodyAfterSuccessfulLogin(responseWithLoginUser, generatedTestEmail, generatedTestName);
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void negativeLoginWrongPassword(){
        LoginPositiveRequestPojo pojoJsonForLogin = new LoginPositiveRequestPojo(
                generatedTestEmail,
                "Wrong_password"
        );

        Response responseWithUnLoginUser = sendByPost(LOGIN_USER_URL, pojoJsonForLogin);
        checkResponseStatusCode(responseWithUnLoginUser, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterNegativeLogin(responseWithUnLoginUser);
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void negativeLoginWrongEmail(){
        LoginPositiveRequestPojo pojoJsonForLogin = new LoginPositiveRequestPojo(
                "wrong-email-839908424023@yandex.ru",
                generatedTestPassword
        );

        Response responseWithUnLoginUser = sendByPost(LOGIN_USER_URL, pojoJsonForLogin);
        checkResponseStatusCode(responseWithUnLoginUser, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterNegativeLogin(responseWithUnLoginUser);
    }


    @After
    public void deleteUser(){
        // Удаления пользователя

        RegisterPositiveResponseAllPojo pojo = getPojoFromResponsePositiveRegisterUser(responseWithCreatedUser);
        deleteUserByBearerToken(pojo.getAccessToken());
    }
}
