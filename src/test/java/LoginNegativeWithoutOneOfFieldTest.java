import com.storage.pojo.login.negative.LoginNegativeRequestWithoutEmailPojo;
import com.storage.pojo.login.negative.LoginNegativeRequestWithoutPasswordPojo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.storage.GenerationData.*;
import static com.storage.SettingsInterface.*;
import static com.storage.restassured.LoginApiMethods.*;


@RunWith(Parameterized.class)
public class LoginNegativeWithoutOneOfFieldTest {
    @Parameterized.Parameters
    public static Object[][] testData(){
        return new Object[][] {
                {new LoginNegativeRequestWithoutEmailPojo(generateTestData("password"))},
                {new LoginNegativeRequestWithoutPasswordPojo(generateTestDataEmail())}
        };
    }

    private final Object pojoJsonData;
    public LoginNegativeWithoutOneOfFieldTest(Object pojoJsonData){
        this.pojoJsonData = pojoJsonData;
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем (без одного из полей)")
    public void negativeTryCreateUserWithoutRequiresField(){
        Response responseWithUnLoginUser = sendByPost(LOGIN_USER_URL, pojoJsonData);

        checkResponseStatusCode(responseWithUnLoginUser, UNAUTHORIZED_STATUS_CODE);
        checkBodyAfterNegativeLogin(responseWithUnLoginUser);
    }
}
