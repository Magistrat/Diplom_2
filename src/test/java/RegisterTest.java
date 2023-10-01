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
    private Response response;

    @Before
    public void setUp(){
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
        response = sendByPost(REGISTER_USER_URL, positiveRegister);

        checkResponseStatusCode(response, SUCCESS_STATUS_CODE);
        checkBodyAfterSuccessfulRegister(response, generatedTestEmail, generatedTestName);
    }

    @Test
    @DisplayName("Создать пользователя, который уже зарегистрирован")
    public void negativeTryCreateAlreadyExistUserTest(){
        response = sendByPost(REGISTER_USER_URL, positiveRegister);
        Response responseExitUser = sendByPost(REGISTER_USER_URL, positiveRegister);

        checkResponseStatusCode(responseExitUser, FORBIDDEN_STATUS_CODE);
        checkBodyUserAlreadyExist(responseExitUser);
    }


    @After
    public void deleteUser(){
        RegisterPositiveResponseAllPojo pojo = getPojoFromResponsePositiveRegisterUser(response);
        deleteUserByBearerToken(pojo.getAccessToken());
    }
}
