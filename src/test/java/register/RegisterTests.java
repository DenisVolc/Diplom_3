package register;

import http.client.DeleteApi;
import http.client.PostApi;
import http.json.LoginRequestCard;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.RegisterPage;
import tech.TechClass;
import io.qameta.allure.Step;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;


public class RegisterTests {
    WebDriver driver;
    RegisterPage registerPage;
    TechClass browser = new TechClass();
    DeleteApi deleteApi = new DeleteApi();
    PostApi postApi = new PostApi();

    private String email = "testUser"+TechClass.getRandomIndex()+"@gmail.com";
    private String name = "testUser"+TechClass.getRandomIndex();
    private String password = TechClass.getRandomIndex();

    private String accessToken;
    LoginRequestCard loginCard = new LoginRequestCard(email,password);
    @Before
    public void setUp(){
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        registerPage= new RegisterPage(driver);
        registerPage.openRegisterPage();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Severity(value = SeverityLevel.CRITICAL)
    public void registrationTest(){
        registerPage.doRegister(name,email,password);
        apiAuth(200,"user.email",loginCard.getEmail());
    }
    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Severity(value = SeverityLevel.NORMAL)
    public void wrongPasswordRegTest(){
        registerPage.doRegister(name,email,password.substring(0,5));//первые 5 символом пароля
        assertTrue(registerPage.isWrongPasswordTitleVisible());
    }
//----------------------------------STEPS----------------------------------
    @Step("Авторизация")
    public void apiAuth(int statusCode, String bodyParm, String equalTo){
        Response response = postApi.apiAuth(loginCard);
        accessToken = response.getBody().path("accessToken");
        response.then().statusCode(statusCode)
                .and().assertThat().body(bodyParm,equalTo(equalTo));
    }
    @After
    public void cleanUp(){
        driver.quit();
        deleteApi.deleteUser(accessToken);
    }
}
