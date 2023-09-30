import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.storage.Base.generateTestData;
import static com.storage.Base.generateTestDataEmail;
import static com.storage.SettingsInterface.BASE_URL;


public class RegisterTest {
    private String generatedTestEmail;
    private String generatedTestPassword;
    private String generatedTestName;

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;

        generatedTestEmail = generateTestDataEmail();
        generatedTestPassword = generateTestData("password");
        generatedTestName = generateTestData("name");
    }


}
