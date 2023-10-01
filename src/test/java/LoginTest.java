import com.storage.pojo.login.positive.LoginPositiveRequestPojo;
import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
import com.storage.pojo.register.positive.RegisterPositiveResponseAllPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.storage.GenerationData.*;
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

        RegisterPositiveRequestPojo positiveRegister = new RegisterPositiveRequestPojo(
                generatedTestEmail,
                generatedTestPassword,
                generatedTestName
        );

        responseWithCreatedUser = sendByPost(REGISTER_USER_URL, positiveRegister);
    }

    @Test
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
    public void negativeLoginWrongPassword(){
        LoginPositiveRequestPojo pojoJsonForLogin = new LoginPositiveRequestPojo(
                generatedTestEmail,
                "123" + generatedTestPassword
        );

        Response responseWithLoginUser = sendByPost(LOGIN_USER_URL, pojoJsonForLogin);
        checkResponseStatusCode(responseWithLoginUser, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterNegativeLogin(responseWithLoginUser);
    }

    @Test
    public void negativeLoginWrongEmail(){
        LoginPositiveRequestPojo pojoJsonForLogin = new LoginPositiveRequestPojo(
                "123" + generatedTestEmail,
                generatedTestPassword
        );

        Response responseWithLoginUser = sendByPost(LOGIN_USER_URL, pojoJsonForLogin);
        checkResponseStatusCode(responseWithLoginUser, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterNegativeLogin(responseWithLoginUser);
    }


    @After
    public void deleteUser(){
        // Удаления пользователя

        RegisterPositiveResponseAllPojo pojo = getPojoFromResponsePositiveRegisterUser(responseWithCreatedUser);
        deleteUserByBearerToken(pojo.getAccessToken());
    }
}
