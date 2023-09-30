import com.storage.pojo.register.RegisterPositivePojo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.storage.GenerationData.generateTestData;
import static com.storage.GenerationData.generateTestDataEmail;
import static com.storage.RestAssuredBaseMethods.checkResponseStatusCode;
import static com.storage.RestAssuredBaseMethods.sendByPost;
import static com.storage.SettingsInterface.*;


public class RegisterTest {
    private RegisterPositivePojo positiveRegister;
    private Response response;

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;

        positiveRegister = new RegisterPositivePojo(
                generateTestDataEmail(),
                generateTestData("password"),
                generateTestData("name")
        );
    }

    @Test
    @DisplayName("Создать уникального пользователя")
    public void createUniqueUserTest(){

        Response response = sendByPost(REGISTER_USER_URL, positiveRegister);
        checkResponseStatusCode(response, SUCCESS_STATUS_CODE);
    }

    @After
    public void deleteUser(){
        System.out.println();
    }
}
