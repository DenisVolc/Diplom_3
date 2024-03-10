package register;

import http.EndPoints;
import constants.URL;
import http.client.DeleteApi;
import http.client.PostApi;
import http.json.LoginRequestCard;
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
    PostApi postApi = new PostApi();
    DeleteApi deleteApi = new DeleteApi();


    private String email = TechClass.getRandomIndex()+"@gmail.com"; //todo сделать параметризованные тест для разных вариантов авторизации

    private String name = "User"+TechClass.getRandomIndex();
    private String password = TechClass.getRandomIndex();

    private String accessToken;
    LoginRequestCard loginCard = new LoginRequestCard(email,password);
    @Before
    public void setUp(){
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        registerPage= new RegisterPage(driver);
        openRegisterPage();

    }
    @Test
    @DisplayName("Успешная регистрация")
    public void registrationTest(){
        registerPage.nameInput(name);
        registerPage.emailInput(email);
        registerPage.passwordInput(password);
        registerPage.clickRegisterButton();
        apiAuth(200,"user.email",loginCard.getEmail());
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void wrongPasswordRegTest(){
        registerPage.nameInput(name);
        registerPage.emailInput(email);
        registerPage.passwordInput(password.substring(0,5));//первые 5 символом пароля
        registerPage.clickRegisterButton();
        assertTrue(registerPage.isWrongPasswordTitleVisible());
    }
//----------------------------------STEPS----------------------------------
    @Step("Открыть страницу авторизации")
    public void openLoginPage(){
        driver.get(URL.LOGIN_PAGE_URL);
    }
    @Step("Открыть страницу регистрации")
    public void openRegisterPage(){
        driver.get(URL.REGISTER_PAGE_URL);
    }
    @Step("Авторизация через API")
    public void apiAuth(int statusCode, String bodyParm, String equalTo){
        Response response = postApi.doPost(EndPoints.LOGIN,loginCard);
        accessToken = response.getBody().path("accessToken");
        response.then().statusCode(statusCode)
                .and().assertThat().body(bodyParm,equalTo(equalTo));
    }
    @After
    public void cleanUp(){
        driver.quit();
        if(accessToken!=null){
            deleteApi.deleteUser(accessToken).then().statusCode(202);
        }
    }


}
