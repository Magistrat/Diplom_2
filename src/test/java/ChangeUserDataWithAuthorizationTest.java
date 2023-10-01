import com.storage.pojo.login.positive.LoginPositiveRequestPojo;
import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
import com.storage.pojo.update.ChangeUserDataPositiveRequestWithEmailPojo;
import com.storage.pojo.update.ChangeUserDataPositiveRequestWithNamePojo;
import com.storage.pojo.update.ChangeUserDataPositiveRequestWithPasswordPojo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.storage.GenerationData.*;
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.ChangeDataApiMethods.*;
import static com.storage.restassured.LoginApiMethods.getPojoFromResponsePositiveLoginUser;


public class ChangeUserDataWithAuthorizationTest {
    private String generatedTestEmail;
    private String generatedTestPassword;
    private String generatedTestName;
    private String loginAccessToken;

    @Before
    public void setUp(){
        // Генерация тестовых данных, создание пользователя, авторизация под ним, получения токена
        RestAssured.baseURI = BASE_URL;

        generatedTestEmail = generateTestDataEmail();
        generatedTestPassword = generateTestData("password");
        generatedTestName = generateTestData("name");

        sendByPost(
                REGISTER_USER_URL,
                new RegisterPositiveRequestPojo(generatedTestEmail, generatedTestPassword, generatedTestName)
        );

        Response responseWithLoginUser = sendByPost(
                LOGIN_USER_URL,
                new LoginPositiveRequestPojo(generatedTestEmail, generatedTestPassword)
        );

        loginAccessToken = getPojoFromResponsePositiveLoginUser(responseWithLoginUser).getAccessToken();
    }

    @Test
    @DisplayName("Изменение данных пользователя (с авторизацией) для ключа email")
    public void changeUserDataPositiveForEmail(){
        ChangeUserDataPositiveRequestWithEmailPojo pojoJsonData = new ChangeUserDataPositiveRequestWithEmailPojo(
                newTestData(generatedTestEmail)
        );

        Response responseWithUpdatedUser = sendByPatchWithToken(UPDATE_USER_URL, pojoJsonData, loginAccessToken);
        checkResponseStatusCode(responseWithUpdatedUser, SUCCESS_STATUS_CODE);
        checkSuccessfulUpdatedUsersData(responseWithUpdatedUser, newTestData(generatedTestEmail), generatedTestName);
    }

    @Test
    @DisplayName("Изменение данных пользователя (с авторизацией) для password")
    public void changeUserDataPositiveForPassword(){
        ChangeUserDataPositiveRequestWithPasswordPojo pojoJsonData = new ChangeUserDataPositiveRequestWithPasswordPojo(
                newTestData(generatedTestPassword)
        );

        Response responseWithUpdatedUser = sendByPatchWithToken(UPDATE_USER_URL, pojoJsonData, loginAccessToken);
        checkResponseStatusCode(responseWithUpdatedUser, SUCCESS_STATUS_CODE);
        checkSuccessfulUpdatedUsersData(responseWithUpdatedUser, generatedTestEmail, generatedTestName);
    }

    @Test
    @DisplayName("Изменение данных пользователя (с авторизацией) для name")
    public void changeUserDataPositiveForName(){
        ChangeUserDataPositiveRequestWithNamePojo pojoJsonData = new ChangeUserDataPositiveRequestWithNamePojo(
                newTestData(generatedTestName)
        );

        Response responseWithUpdatedUser = sendByPatchWithToken(UPDATE_USER_URL, pojoJsonData, loginAccessToken);
        checkResponseStatusCode(responseWithUpdatedUser, SUCCESS_STATUS_CODE);
        checkSuccessfulUpdatedUsersData(responseWithUpdatedUser, generatedTestEmail, newTestData(generatedTestName));
    }

    @After
    public void deleteUser(){
        // Удаления пользователя
        deleteUserByBearerToken(loginAccessToken);
    }
}
