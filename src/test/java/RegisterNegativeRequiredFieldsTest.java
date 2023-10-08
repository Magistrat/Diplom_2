import com.storage.pojo.register.negative.requiredfields.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.storage.GenerationData.generateTestData;
import static com.storage.GenerationData.generateTestDataEmail;
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.RegisterApiMethods.*;

@RunWith(Parameterized.class)
public class RegisterNegativeRequiredFieldsTest {

    @Parameterized.Parameters
    public static Object[][] testData(){
        return new Object[][] {
                {new RegisterNegativeRequestWithoutEmailPojo(generateTestData("password"), generateTestData("name"))},
                {new RegisterNegativeRequestWithoutNamePojo(generateTestDataEmail(), generateTestData("password"))},
                {new RegisterNegativeRequestWithoutPasswordPojo(generateTestDataEmail(), generateTestData("name"))}
        };
    }

    private final Object pojoJsonData;

    public RegisterNegativeRequiredFieldsTest(Object pojoJsonData){
        this.pojoJsonData = pojoJsonData;
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Создать пользователя и не заполнить одно из обязательных полей")
    public void negativeTryCreateUserWithoutRequiresField(){
        Response response = sendByPost(REGISTER_USER_URL, pojoJsonData);

        checkResponseStatusCode(response, FORBIDDEN_STATUS_CODE);
        checkBodyUserRequiredField(response);
    }
}
