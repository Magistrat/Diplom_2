import com.storage.pojo.register.positive.RegisterPositiveRequestPojo;
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

    @After
    public void deleteUser(){
        System.out.println();
    }
}
